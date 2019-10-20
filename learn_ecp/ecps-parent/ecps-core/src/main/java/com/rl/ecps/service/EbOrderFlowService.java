package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.TaskBean;

/**
 * 订单工作流程接口
 * 
 * @author 86150
 *
 */
public interface EbOrderFlowService {
	
	/**
	 * 部署流程
	 * 
	 */
	public void deployOrderFlow();
	
	
	/**
	 * 启动流程实例
	 * 
	 * @param orderId
	 * @return
	 */
	public String startInstance(Long orderId);
	
	
	/**
	 * 完成任务
	 * 
	 * @param processInstanceId
	 * @param outcome
	 */
	public void completeTaskByPid(String processInstanceId,String outcome);
	
	
	/**
	 * 查询办理人下的任务
	 * 
	 * @param assignee
	 * @return
	 */
	public List<TaskBean> selectTaskByAssignee(String assignee);
	
	
	/**
	 * 根据任务ID查询任务数据
	 * 
	 * 
	 * @param taskId
	 * @return
	 */
	public TaskBean selectTaskBeanByTaskId(String taskId);
	
	
	
	/**
	 * 根据任务Id完成任务
	 * 
	 * 
	 * @param taskId
	 * @param outcome
	 */
	public void UpdateCompleteTaskByTid(String taskId,String outcome);
	


}
