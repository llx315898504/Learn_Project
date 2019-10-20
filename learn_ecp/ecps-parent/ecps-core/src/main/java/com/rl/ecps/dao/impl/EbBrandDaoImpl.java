package com.rl.ecps.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.model.EbBrand;

@Repository
public class EbBrandDaoImpl extends SqlSessionDaoSupport implements EbBrandDao {

	String ns = "com.rl.ecps.mapper.EbBrandMapper.";

	@Override
	public void saveBrand(EbBrand brand) {
		this.getSqlSession().insert(ns + "insert", brand);
	}

	@Override
	public List<EbBrand> selectBrandAll() {
		return this.getSqlSession().selectList(ns + "selectBrandAll");
	}

	@Override
	public List<EbBrand> selectBrandByName(String brandName) {
		return this.getSqlSession().selectList(ns + "selectBrandByName", brandName);
	}

	@Override
	public void deleteBrandById(Long brandId) {
		this.getSqlSession().delete(ns+"deleteBrandById",brandId);
	}

	@Override
	public void updateBrand(EbBrand ebBrand) {
		this.getSqlSession().update(ns+"updateBrand", ebBrand);
	}


	@Override
	public EbBrand selectBrandById(Long brandId) {
		return this.getSqlSession().selectOne(ns+"selectBrandById", brandId);
	}
	
	

}