package com.rl.ecps.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.util.ECPSUtils;

import redis.clients.jedis.Jedis;
@Service
public class EbSkuServiceImpl implements EbSkuService {
	
	@Autowired
	private EbSkuDao dao;

	@Override
	public EbSku selectSkuById(Long skuId) {
		return dao.selectSkuById(skuId);
	}

	@Override
	public List<EbSku> selectSkuList() {
		return dao.selectSkuList();
	}

	@Override
	public EbSku selectSkuByIdWithRedis(Long skuId) {
		// 创建redis链接
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
        String skuPrice= jedis.hget("sku:"+skuId, "skuPrice");
        String marketPrice= jedis.hget("sku:"+skuId, "marketPrice");
        String stockInventory= jedis.hget("sku:"+skuId, "stockInventory");
        EbSku ebSku=new EbSku();
        ebSku.setSkuId(skuId);
        ebSku.setMarketPrice(new BigDecimal(marketPrice));
        ebSku.setSkuPrice(new BigDecimal(skuPrice));
        ebSku.setStockInventory(new Integer(stockInventory));
		return ebSku;
	}

	@Override
	public EbSku selectSkuDetailByIdWithRedis(Long skuId) {
		// 创建redis链接
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		//从redis中取sku数据
		String skuPrice = jedis.hget("sku:" + skuId, "skuPrice");
		String marketPrice = jedis.hget("sku:" + skuId, "marketPrice");
		String stockInventory = jedis.hget("sku:" + skuId, "stockInventory");
		String itemId = jedis.hget("sku:" + skuId, "itemId");
		EbSku ebSku = new EbSku();
		ebSku.setSkuId(skuId);
		ebSku.setMarketPrice(new BigDecimal(marketPrice));
		ebSku.setSkuPrice(new BigDecimal(skuPrice));
		ebSku.setStockInventory(new Integer(stockInventory));
		ebSku.setItemId(new Long(itemId));
		//从redis中取item数据
		String itemName = jedis.hget("sku:" + skuId+":item:"+ebSku.getItemId(), "itemName");
		String itemNo = jedis.hget("sku:" + skuId+":item:"+ebSku.getItemId(), "itemNo");
		String imgs = jedis.hget("sku:" + skuId+":item:"+ebSku.getItemId(), "imgs");
		EbItem ebItem=new EbItem();
		ebItem.setImgs(imgs);
		ebItem.setItemName(itemName);
		ebItem.setItemNo(itemNo);
		ebItem.setItemId(new Long(itemId));
		ebSku.setEbItem(ebItem);
		//从redis中取规格集合的过程
		List<String> specIds = jedis.lrange("sku:"+ebSku.getSkuId()+"specList", 0, -1);
		List<EbSpecValue> ebSpecList=new ArrayList<EbSpecValue>();
		for (String specId : specIds) {
			String specValue = jedis.hget("sku:" + skuId+":spec:"+specId, "specValue");
			EbSpecValue ebSpecValue=new EbSpecValue();
			ebSpecValue.setSpecValue(specValue);
			ebSpecValue.setSkuId(skuId);
			ebSpecValue.setSpecId(new Long(specId));
			ebSpecList.add(ebSpecValue);
		}
		ebSku.setEbSpecList(ebSpecList);
		return ebSku;
	}

	

}
