package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbItem;
import com.rl.ecps.query.ItemReqVo;

public interface EbItemDao {
	

	/**
	 * 条件查询商品列表
	 * 
	 * @param itemReqVo
	 * @return
	 */
	public List<EbItem> queryItemByCondtion(ItemReqVo itemReqVo);

	/**
	 * 条件查询商品总记录数 
	 * 
	 * @param itemReqVo
	 * @return
	 */
	public Integer queryItemByCondtionCount(ItemReqVo itemReqVo);
	
	/**
	 * 添加商品
	 * 
	 * @param ebItem
	 */
	void saveItem(EbItem ebItem);
	
	
	/**
	 * 修改商品
	 * 
	 * @param ebItem
	 */
	void updateEbItem(EbItem ebItem);
	
	
	/**
	 * 查询商品的集合（用于首页筛选查询）
	 * 
	 * @return
	 */
	public List<EbItem> selectIsSelectItemList();
	
	
	/**
	 * 根据商品ID查询商品详情
	 * 
	 * @param itemId
	 * @return
	 */
	public EbItem selectItemDetailById(Long itemId);
	
	

}
