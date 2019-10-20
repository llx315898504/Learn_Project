package com.rl.ecps.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.query.ItemReqVo;

@Repository
public class EbItemDaoImpl extends SqlSessionDaoSupport implements EbItemDao{

	String ns = "com.rl.ecps.mapper.EbItemMapper.";

	@Override
	public List<EbItem> queryItemByCondtion(ItemReqVo itemReqVo) {
		return this.getSqlSession().selectList(ns+"queryItemByCondtion", itemReqVo);
	}

	@Override
	public Integer queryItemByCondtionCount(ItemReqVo itemReqVo) {
		return this.getSqlSession().selectOne(ns+"queryItemByCondtionCount", itemReqVo);
	}

	@Override
	public void saveItem(EbItem ebItem) {
		this.getSqlSession().insert(ns+"saveItem", ebItem);
	}

	@Override
	public void updateEbItem(EbItem ebItem) {
		this.getSqlSession().update(ns+"updateEbItem", ebItem);
	}

	@Override
	public List<EbItem> selectIsSelectItemList() {
		return this.getSqlSession().selectList(ns+"selectIsSelectItemList");
	}

	@Override
	public EbItem selectItemDetailById(Long itemId) {
		return this.getSqlSession().selectOne(ns+"selectItemDetailById", itemId);
	}
}