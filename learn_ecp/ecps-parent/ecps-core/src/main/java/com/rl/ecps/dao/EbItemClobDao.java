package com.rl.ecps.dao;

import com.rl.ecps.model.EbItemClob;

public interface EbItemClobDao {
	
	/**
	 * 添加商品大字段数据
	 * 
	 * @param ebItem
	 */
	void saveClobItem(EbItemClob ebItemClob);
	
	

}
