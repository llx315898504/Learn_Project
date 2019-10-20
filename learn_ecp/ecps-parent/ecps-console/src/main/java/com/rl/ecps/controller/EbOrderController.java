package com.rl.ecps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.TaskBean;
import com.rl.ecps.service.EbOrderService;

@Controller
@RequestMapping("/order")
public class EbOrderController {

	@Autowired
	private EbOrderService orderService;
	
	/**
	 * 查询待付款单列表
	 * 
	 * @param assignee
	 * @param isCall
	 * @param model
	 * @return
	 */
	@RequestMapping("/listPayOrder.do")
	public String listPayOrder(String assignee,Short isCall,Model model){
		List<TaskBean> tbList = orderService.selectTaskBeanByAssignee(assignee, isCall);
		model.addAttribute("tbList", tbList);
		model.addAttribute("isCall", isCall);
		return "order/orderPay/orderPay";
	}
	
	
	/**
	 * 查看订单详情
	 * 
	 * @param orderId
	 * @param taskId
	 * @param dirName
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewDetail.do")
	public String viewDetail(Long orderId,String taskId,String dirName,Model model){
		TaskBean tb = orderService.selectTaskBeanOrderDetail(orderId, taskId);
		model.addAttribute("tb", tb);
		return "order/"+dirName+"/detail";
	}
	
	
	/**
	 * 外呼完成
	 * 
	 * @param orderId
	 * @param taskId
	 * @param dirName
	 * @param model
	 * @return
	 */
	@RequestMapping("/completeCall.do")
	public String completeCall(Long orderId, Model model){
		orderService.completeCall(orderId);
		return "redirect:listPayOrder.do?assignee=noPaidOrderer&isCall=0";
	}
	
	
	/**
	 * 查询待付款单列表
	 * 
	 * @param assignee
	 * @param dirName
	 * @param model
	 * @return
	 */
	@RequestMapping("/listTaskByAssignee.do")
	public String listTaskByAssignee(String assignee,String dirName, Model model){
		List<TaskBean> tbList = orderService.selectTaskBeanByAssignee(assignee);
		model.addAttribute("tbList", tbList);
		return "order/"+dirName+"/"+dirName;
	}
	
	
	
	/**
	 * 完成任务
	 * 
	 * @param taskId
	 * @param orderId
	 * @param outcome
	 * @return
	 */
	@RequestMapping("/completeTask.do")
	public String completeTask(String taskId,Long orderId,String outcome){
		orderService.updateCompeteTask(taskId, orderId, outcome);
		return "redirect:listTaskByAssignee.do?assignee=paidOrderer&dirName=orderCall";
	}
}
