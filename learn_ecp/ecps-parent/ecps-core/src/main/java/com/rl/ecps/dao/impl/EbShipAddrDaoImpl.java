package com.rl.ecps.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.model.EbShipAddr;
@Repository
public class EbShipAddrDaoImpl extends SqlSessionDaoSupport implements EbShipAddrDao {
	
	String ns = "com.rl.ecps.mapper.EbShipAddrMapper.";

	@Override
	public List<EbShipAddr> selectAddressByUserId(Long userId) {
		return this.getSqlSession().selectList(ns+"selectAddressByUserId", userId);
	}

	@Override
	public EbShipAddr selectAddrById(Long shipAddrId) {
		return this.getSqlSession().selectOne(ns+"selectAddrById", shipAddrId);
	}

	@Override
	public void insert(EbShipAddr addr) {
		this.getSqlSession().insert(ns+"insert", addr);
	}

	@Override
	public void update(EbShipAddr addr) {
		this.getSqlSession().update(ns+"update", addr);
	}

	@Override
	public void updateDefaultAddrByUserId(Long userId) {
		this.getSqlSession().update(ns+"updateDefaultAddrByUserId", userId);
	}

	@Override
	public void deleteAddrById(Map<String, Object> map) {
		this.getSqlSession().delete(ns+"deleteAddrById", map);
	}

	@Override
	public List<EbShipAddr> selectAllAddress() {
		return this.getSqlSession().selectList(ns+"selectAllAddress");
	}

}
