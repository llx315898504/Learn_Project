package com.rl.ecps.dao;

import com.rl.ecps.model.EbOrder;

public interface EbOrderDao {
	
	/**
	 * 创建订单
	 * 
	 * @param order
	 */
	public void saveOrder(EbOrder order);
	
	
	/**
	 * 更新订单
	 * 
	 * @param order
	 */
	public void updateOrder(EbOrder order);
	
	
	/**
	 * 根据订单号查询订单数据
	 * 
	 * @param orderId
	 * @return
	 */
	public EbOrder selectOrderById(Long orderId);
	
	
	
	/**
	 * 根据订单Id查询订单及订单明细数据
	 * 
	 * @param orderId
	 * @return
	 */
	public EbOrder selectOrderDetailById(Long orderId);

}