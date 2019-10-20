package com.rl.ecps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbOrderDao;
import com.rl.ecps.model.EbOrder;
@Repository
public class EbOrderDaoImpl extends SqlSessionDaoSupport implements EbOrderDao {

	String ns = "com.rl.ecps.mapper.EbOrderMapper.";

	public void saveOrder(EbOrder order) {
		this.getSqlSession().insert(ns+"insert", order);
	}

	@Override
	public void updateOrder(EbOrder order) {
		this.getSqlSession().update(ns+"updateOrder", order);
	}

	@Override
	public EbOrder selectOrderById(Long orderId) {
		return this.getSqlSession().selectOne(ns+"selectOrderById", orderId);
	}

	@Override
	public EbOrder selectOrderDetailById(Long orderId) {
		return this.getSqlSession().selectOne(ns+"selectOrderDetailById", orderId);
	}

	

}
