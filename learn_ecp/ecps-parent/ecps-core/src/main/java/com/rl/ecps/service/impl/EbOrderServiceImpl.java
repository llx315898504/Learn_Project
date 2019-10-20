package com.rl.ecps.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbOrderDao;
import com.rl.ecps.dao.EbOrderDetailDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.exception.EbStockException;
import com.rl.ecps.model.EbOrder;
import com.rl.ecps.model.EbOrderDetail;
import com.rl.ecps.model.TaskBean;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.service.EbOrderFlowService;
import com.rl.ecps.service.EbOrderService;

@Service
public class EbOrderServiceImpl implements EbOrderService {

	
	@Autowired
	private EbSkuDao skuDao;
	@Autowired
	private EbOrderDao orderDao;
	@Autowired
	private EbOrderDetailDao orderDetailDao;
	@Autowired
	private EbCartService cartService;
	@Autowired
	private EbOrderFlowService flowService;

	public String saveOrder(EbOrder order, List<EbOrderDetail> detailList, HttpServletRequest request,
			HttpServletResponse response) {
		//保存订单信息
		orderDao.saveOrder(order);
		Map<String, Object> map = new HashMap<String, Object>();
		for (EbOrderDetail detail : detailList) {
			detail.setOrderId(order.getOrderId());
			//保存订单明细数据
			orderDetailDao.saveOrderDetail(detail);
			map.put("skuId", detail.getSkuId());
			map.put("quantity", detail.getQuantity());
			//扣减数据库库存
			int flag = skuDao.updateStock(map);
			if (flag == 0) {
				throw new EbStockException();
			}
		}
		cartService.clearCart(request, response);
		String processInstanceId = flowService.startInstance(order.getOrderId());
		return processInstanceId;
	}

	@Override
	public void updatePayOrder(String processInstanceId, Long orderId) {
		EbOrder order=new EbOrder();
		order.setOrderId(orderId);
		order.setIsPaid(new Short("1"));
		//更新订单信息
		orderDao.updateOrder(order);
		flowService.completeTaskByPid(processInstanceId, "付款");
	}

	@Override
	public List<TaskBean> selectTaskBeanByAssignee(String assignee, Short isCall) {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		List<TaskBean> tbList = flowService.selectTaskByAssignee(assignee);
		if (isCall == null) {
			for (TaskBean taskBean : tbList) {
				String businessKey = taskBean.getBusinessKey();
				EbOrder order = orderDao.selectOrderById(new Long(businessKey));
				taskBean.setEbOrder(order);
				taskList.add(taskBean);
				}
		} else {
			for (TaskBean taskBean : tbList) {
				String businessKey = taskBean.getBusinessKey();
				EbOrder order = orderDao.selectOrderById(new Long(businessKey));
				if (order.getIsCall().shortValue() == isCall.shortValue()) {
					taskBean.setEbOrder(order);
					taskList.add(taskBean);
				}
			}
		}
		return taskList;
	}

	@Override
	public TaskBean selectTaskBeanOrderDetail(Long orderId, String taskId) {
		EbOrder order = orderDao.selectOrderDetailById(orderId);
		TaskBean taskBean = flowService.selectTaskBeanByTaskId(taskId);
		taskBean.setEbOrder(order);
		return taskBean;
	}

	@Override
	public void completeCall(Long orderId) {
		EbOrder order=new EbOrder();
		order.setOrderId(orderId);
		order.setIsCall(new Short("1"));
		//更新订单信息
		orderDao.updateOrder(order);
	}

	@Override
	public List<TaskBean> selectTaskBeanByAssignee(String assignee) {
		List<TaskBean> tbList = flowService.selectTaskByAssignee(assignee);
			for (TaskBean taskBean : tbList) {
				String businessKey = taskBean.getBusinessKey();
				EbOrder order = orderDao.selectOrderById(new Long(businessKey));
				taskBean.setEbOrder(order);
			}
		return tbList;
	}

	@Override
	public void updateCompeteTask(String taskId, Long orderId, String outcome) {
		EbOrder order = new EbOrder();
		order.setOrderId(orderId);
		order.setUpdateTime(new Date());
		orderDao.updateOrder(order);
		flowService.UpdateCompleteTaskByTid(taskId, outcome);
	}
	
	
}