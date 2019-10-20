package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.common.Page;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.query.ItemReqVo;

/**
 * 商品接口
 * 
 * @author 86150
 *
 */
public interface EbItemService {
	
	/**
	 * 条件查询商品列表
	 * 
	 * @param itemReqVo
	 * @return
	 */
	public Page queryItemByCondtion(ItemReqVo itemReqVo);
	
	
	/**
	 * 添加商品
	 * 
	 * @param ebItem
	 * @param ebItemClob
	 * @param ebParaList
	 * @param ebSkuList
	 */
	public  void saveItem(EbItem ebItem,EbItemClob ebItemClob,List<EbParaValue> ebParaList,List<EbSku> ebSkuList);
	
	
	
	/**
	 * 商品审核
	 * 
	 * @param itemId
	 * @param auditStatus
	 * @param itemNote
	 */
	public  void auditItem(Long itemId,Short auditStatus,String itemNote);
	
	
	/**
	 * 商品上下架
	 * 
	 * @param itemId
	 * @param showStatus
	 * @param itemNote
	 */
	public  void showItem(Long itemId,Short showStatus,String itemNote);
	
	
	/**
	 * 根据索引查询商品记录
	 * 
	 * @param price
	 * @param brandId
	 * @param keyWords
	 * @param paraVals
	 * @return
	 * @throws Exception 
	 */
	public List<EbItem> selectEbItemByIndex(String price,Long brandId,String keyWords,String paraVals) throws Exception;
	
	
	/**
	 * 根据商品ID查询商品详情
	 * 
	 * @param itemId
	 * @return
	 */
	public EbItem selectItemDetailById(Long itemId);
	
	
	/***
	 * 根据项目Id生产详情页的静态页面
	 * 
	 * @param itemId
	 * @param passWord
	 * @return
	 */
	public String publishItem(Long itemId,String passWord);
	
}
