package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.EbFeature;

/**
 * 商品品牌接口
 * 
 * @author 86150
 *
 */
public interface EbFeatureService {
	
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
