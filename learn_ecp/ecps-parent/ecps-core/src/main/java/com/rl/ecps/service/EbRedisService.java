package com.rl.ecps.service;

public interface EbRedisService {
     
	 /**
	  * 将最小销售单元数据导入redis
	  * 
	  */
	 public void importEbSkuToRedis();
	 
	 
	 /**
	  * 将收货地址导入redis数据库
	  * 
	  */
	 public void importEbShipAddrToRedis();
}
