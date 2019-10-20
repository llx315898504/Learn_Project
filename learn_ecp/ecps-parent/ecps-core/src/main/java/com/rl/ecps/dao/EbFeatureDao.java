package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbFeature;

public interface EbFeatureDao {
	

	/**
	 * 查询商品普通属性列表
	 * 
	 * @return
	 */
	public List<EbFeature> selectCommFeature();

	/**
	 * 查询商品特殊属性列表
	 * 
	 * @return
	 */
	public List<EbFeature> selectSpecFeature();
	
	
	/**
	 * 查询商品的筛选属性
	 * 
	 * @return
	 */
	public List<EbFeature> qrySelectFeature();


	

}
