package com.rl.ecps.dao;

import java.util.List;
import java.util.Map;

import com.rl.ecps.model.EbSku;

public interface EbSkuDao {
	
	/**
	 * 添加最小销售单元数据
	 * 
	 * @param ebParaList
	 * @param itemId
	 */
	void saveEbSku(List<EbSku> ebSkuList,Long itemId);
	
	
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
	 * 查询最小销售单元的详细信息
	 * 
	 * @return
	 */
	public List<EbSku> selectSkuDetailList();
	
	
	/**
	 * 扣减数据库库存
	 * 
	 * @param map
	 * @return
	 */
	public int updateStock(Map<String,Object> map);
	
	
	/**
	 * 扣减缓存数据库库存
	 * 
	 * @param map
	 */
	public void updateStockRedis(Map<String,Object> map);

}
