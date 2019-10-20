package com.rl.ecps.dao;

import com.rl.ecps.model.EbOrderDetail;

public interface EbOrderDetailDao {
	
	/**
	 * 创建订单明细
	 * 
	 * @param detail
	 */
	public void saveOrderDetail(EbOrderDetail detail);
}
