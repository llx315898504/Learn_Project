package com.rl.ecps.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rl.ecps.model.EbOrder;
import com.rl.ecps.model.EbOrderDetail;
import com.rl.ecps.model.TaskBean;


public interface EbOrderService {
	
	/**
	 * 保存订单信息
	 * 
	 * @param order
	 * @param detailList
	 * @param request
	 * @param response
	 */
	public String saveOrder(EbOrder order, List<EbOrderDetail> detailList,HttpServletRequest request, HttpServletResponse response);
	
	
	/**
	 * 确认支付
	 * 
	 * @param processInstanceId
	 * @param orderId
	 */
	public void  updatePayOrder(String processInstanceId,Long orderId);
	
	
	/**
	 * 根据办理人及是否呼出查询订单任务
	 * 
	 * @param assignee
	 * @param isCall
	 * @return
	 */
	public List<TaskBean> selectTaskBeanByAssignee(String assignee,Short isCall);
	
	
	
	/**
	 * 根据办理人查询订单任务
	 * 
	 * @param assignee
	 * @return
	 */
	public List<TaskBean> selectTaskBeanByAssignee(String assignee);
	
	
	
	/**
	 * 查看订单详情数据
	 * 
	 * @param orderId
	 * @param taskId
	 * @return
	 */
	public TaskBean selectTaskBeanOrderDetail(Long orderId,String taskId);
	
	
	
	/**
	 * 完成外呼
	 * 
	 * @param orderId
	 */
	public void  completeCall(Long orderId);
	
	/**
	 * 完成任务
	 * 
	 * @param taskId
	 * @param orderId
	 * @param outcome
	 */
	public void updateCompeteTask(String taskId, Long orderId, String outcome);
}
