package com.rl.ecps.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.model.TaskBean;
import com.rl.ecps.service.EbOrderFlowService;

@Service
public class EbOrderFlowServiceImpl implements EbOrderFlowService {
	
	@Autowired
	private  RepositoryService repositoryService;
	@Autowired
	private  RuntimeService runtimeService;
	@Autowired
	private  TaskService taskService;


	@Override
	public void deployOrderFlow() {
			DeploymentBuilder db = repositoryService.createDeployment();
			db.addClasspathResource("com/rl/ecps/activiti/OrderFlow.bpmn")
			.addClasspathResource("com/rl/ecps/activiti/OrderFlow.png");
			db.deploy();
	}

	@Override
	public String startInstance(Long orderId) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("OrderFlow", orderId+"");
		return processInstance.getId();
	}

	@Override
	public void completeTaskByPid(String processInstanceId, String outcome) {
		Task task = taskService.createTaskQuery().processDefinitionKey("OrderFlow")
		         .processInstanceId(processInstanceId).singleResult();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("outcome", outcome);
		taskService.complete(task.getId(), map);
	}

	@Override
	public List<TaskBean> selectTaskByAssignee(String assignee) {
		List<TaskBean> tbList=new ArrayList<TaskBean>();
		List<Task> taskList = taskService.createTaskQuery().processDefinitionKey("OrderFlow")
		.taskAssignee(assignee)
		.orderByTaskCreateTime()
		.desc()
		.list();
		ProcessInstanceQuery pq = runtimeService.createProcessInstanceQuery();
		for (Task task : taskList) {
			TaskBean tb=new TaskBean();
			ProcessInstance pi = pq.processInstanceId(task.getProcessInstanceId()).singleResult();
			tb.setTask(task);
			tb.setBusinessKey(pi.getBusinessKey());
			tbList.add(tb);
		}
		return tbList;
	}

	@Override
	public TaskBean selectTaskBeanByTaskId(String taskId) {
		TaskBean tb = new TaskBean();
		Task task = taskService.createTaskQuery().processDefinitionKey("OrderFlow").taskId(taskId).singleResult();
		tb.setTask(task);
		tb.setOutcome(this.getOutcomes(task));
		return tb;
	}
	
	
	public List<String> getOutcomes(Task task){
		List<String> outcomeList = new ArrayList<String>();
		//获得流程定义的对象
		ProcessDefinitionEntity pe =  (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionKey("OrderFlow")
		.processInstanceId(task.getProcessInstanceId()).singleResult();
		ActivityImpl ai = pe.findActivity(pi.getActivityId());
		//获得向外走的线的对象
		List<PvmTransition> ptList = ai.getOutgoingTransitions();
		for(PvmTransition pt : ptList){
			String outcome = (String) pt.getProperty("name");
			outcomeList.add(outcome);
		}
		return outcomeList;
	}

	public void completeTaskByTId(String taskId, String outcome) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("outcome", outcome);
		taskService.complete(taskId, map);
	}

	@Override
	public void UpdateCompleteTaskByTid(String taskId, String outcome) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("outcome", outcome);
		taskService.complete(taskId, map);
	}
}
