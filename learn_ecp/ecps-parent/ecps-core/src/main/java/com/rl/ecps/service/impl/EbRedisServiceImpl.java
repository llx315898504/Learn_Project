package com.rl.ecps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.service.EbRedisService;
import com.rl.ecps.util.ECPSUtils;

import redis.clients.jedis.Jedis;

@Service
public class EbRedisServiceImpl implements EbRedisService {

	@Autowired
	private EbSkuDao ebSkuDao;
	
	@Autowired
	private EbShipAddrDao ebShipAddrDao;

	@Override
	public void importEbSkuToRedis() {
		// 创建redis链接
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		// 查询最小销售单元的详细信息
		List<EbSku> skuList = ebSkuDao.selectSkuDetailList();
		for (EbSku ebSku : skuList) {
			// 把每个skuId存储到list中
			jedis.lpush("skuList", ebSku.getSkuId() + "");
			jedis.hset("sku:" + ebSku.getSkuId(), "skuId", ebSku.getSkuId() + "");
			jedis.hset("sku:" + ebSku.getSkuId(), "skuPrice", ebSku.getSkuPrice() + "");
			jedis.hset("sku:" + ebSku.getSkuId(), "marketPrice", ebSku.getMarketPrice() + "");
			jedis.hset("sku:" + ebSku.getSkuId(), "stockInventory", ebSku.getStockInventory() + "");
			jedis.hset("sku:" + ebSku.getSkuId(), "itemId", ebSku.getItemId() + "");
			// 同步商品信息
			jedis.hset("sku:" + ebSku.getSkuId() + ":item:" + ebSku.getEbItem().getItemId(), "itemId",
					ebSku.getEbItem().getItemId() + "");
			jedis.hset("sku:" + ebSku.getSkuId() + ":item:" + ebSku.getEbItem().getItemId(), "itemNo",
					ebSku.getEbItem().getItemNo() + "");
			jedis.hset("sku:" + ebSku.getSkuId() + ":item:" + ebSku.getEbItem().getItemId(), "itemName",
					ebSku.getEbItem().getItemName() + "");
			jedis.hset("sku:" + ebSku.getSkuId() + ":item:" + ebSku.getEbItem().getItemId(), "imgs",
					ebSku.getEbItem().getImgs() + "");
			// 同步规格集合的过程
			for (EbSpecValue spec : ebSku.getEbSpecList()) {
				jedis.lpush("sku:" + spec.getSkuId() + "specList", spec.getSpecId() + "");
				jedis.hset("sku:" + ebSku.getSkuId() + ":spec:" + spec.getSpecId(), "specValue",
						spec.getSpecValue() + "");
				jedis.hset("sku:" + ebSku.getSkuId() + ":spec:" + spec.getSpecId(), "skuId", spec.getSkuId() + "");
				jedis.hset("sku:" + ebSku.getSkuId() + ":spec:" + spec.getSpecId(), "specId", spec.getSpecId() + "");
			}
		}

	}

	@Override
	public void importEbShipAddrToRedis() {
		// 创建redis链接
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		List<EbShipAddr> addressList= ebShipAddrDao.selectAllAddress();
		for (EbShipAddr ebShipAddr : addressList) {
			jedis.lpush("user:"+ebShipAddr.getPtlUserId()+":addrList", ebShipAddr.getShipAddrId()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "shipAddrId", ebShipAddr.getShipAddrId()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "shipName", ebShipAddr.getShipName()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "province", ebShipAddr.getProvince()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "city", ebShipAddr.getCity()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "district", ebShipAddr.getDistrict()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "addr", ebShipAddr.getAddr()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "zipCode", ebShipAddr.getZipCode()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "phone", ebShipAddr.getPhone()+"");
			jedis.hset("user:"+ebShipAddr.getPtlUserId()+":addr:"+ebShipAddr.getShipAddrId(), "defaultAddr", ebShipAddr.getDefaultAddr()+"");
		}
	}

}
