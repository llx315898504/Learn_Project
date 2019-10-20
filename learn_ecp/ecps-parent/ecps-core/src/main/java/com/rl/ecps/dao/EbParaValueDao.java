package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbParaValue;

public interface EbParaValueDao {
	
	/**
	 * 添加商品的普通属性
	 * 
	 * @param ebParaList
	 * @param itemId
	 */
	void saveParaValue(List<EbParaValue> ebParaList,Long itemId);
	
	

}
