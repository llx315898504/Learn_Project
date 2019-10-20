package com.rl.ecps.model;

import java.util.List;

import org.activiti.engine.task.Task;

import lombok.Data;

@Data
public class TaskBean {
	
	private EbOrder ebOrder;
	
	private Task task;
	
	private  String businessKey;
	
	//动态按钮
	private List<String> outcome;
	
	private String income;
}
