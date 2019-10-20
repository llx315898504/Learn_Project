package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.EbSku;


/**
 * 区域地址管理接口
 * 
 * @author 86150
 *
 */
public interface EbSkuService {
	
	/**
	 * 根据最小销售单元ID查询最小销售单元数据
	 * 
	 * @param skuId
	 * @return
	 */
	public EbSku selectSkuById(Long skuId);
	
	
	/**
	 * 根据最小销售单元列表 
	 * 
	 * @return
	 */
	public List<EbSku> selectSkuList();
	
	
	/**
	 * 根据最小销售单元ID查询最小销售单元数据
	 * 
	 * @param skuId
	 * @return
	 */
	public EbSku selectSkuByIdWithRedis(Long skuId);
	
	
	/**
	 * 根据最小销售单元ID查询最小销售单元详情数据
	 * 
	 * @param skuId
	 * @return
	 */
	public EbSku selectSkuDetailByIdWithRedis(Long skuId);
	

}
