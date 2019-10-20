package com.rl.ecps.service;

/**
 * 商品索引管理接口
 * 
 * @author 86150
 *
 */
public interface EbIndexService {
	
	/**
	 * 导入索引
	 * @throws Exception 
	 * 
	 */
	public  void  importIndex() throws Exception;
	
	/**
	 * 添加索引
	 * 
	 */
	public  void  addtIndex();
	
	/**
	 * 删除索引
	 * 
	 */
	public  void  deleteIndex();

}
