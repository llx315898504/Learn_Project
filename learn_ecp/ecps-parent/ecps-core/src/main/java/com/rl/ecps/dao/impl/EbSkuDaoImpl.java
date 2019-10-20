package com.rl.ecps.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.util.ECPSUtils;

import redis.clients.jedis.Jedis;

@Repository
public class EbSkuDaoImpl extends SqlSessionDaoSupport implements EbSkuDao{

	String ns = "com.rl.ecps.mapper.EbSkuMapper.";
	String ns1 = "com.rl.ecps.mapper.EbSpecValueMapper.";

	@Override
	public void saveEbSku(List<EbSku> ebSkuList, Long itemId) {
		// 获得到会话，整个集合用同一次数据的链接，节省资源
		SqlSession session = this.getSqlSession();
		for (EbSku ebSku : ebSkuList) {
			ebSku.setItemId(itemId);
			session.insert(ns + "saveEbSku", ebSku);
			List<EbSpecValue> ebSpecList = ebSku.getEbSpecList();
			for (EbSpecValue ebSpecValue : ebSpecList) {
				ebSpecValue.setSkuId(ebSku.getSkuId());
				session.insert(ns1+"saveSpecValue", ebSpecValue);
			}
		}
	}

	@Override
	public EbSku selectSkuById(Long skuId) {
		return this.getSqlSession().selectOne(ns+"selectSkuById", skuId);
	}

	@Override
	public List<EbSku> selectSkuList() {
		return this.getSqlSession().selectList(ns+"selectSkuList");
	}

	@Override
	public List<EbSku> selectSkuDetailList() {
		return this.getSqlSession().selectList(ns+"selectSkuDetailList");
	}

	@Override
	public int updateStock(Map<String, Object> map) {
		return this.getSqlSession().update(ns+"updateStock", map);
	}

	@Override
	public void updateStockRedis(Map<String, Object> map) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		Long skuId=(Long) map.get("skuId");
		Integer quantity=(Integer) map.get("quantity");
		jedis.hset("sku:" +skuId, "stockInventory", (new Integer(jedis.hget("sku:" +skuId, "stockInventory"))-quantity) + "");
	}


}