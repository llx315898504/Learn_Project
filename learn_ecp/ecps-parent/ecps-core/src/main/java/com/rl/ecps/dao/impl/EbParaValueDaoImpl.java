package com.rl.ecps.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbParaValueDao;
import com.rl.ecps.model.EbParaValue;

@Repository
public class EbParaValueDaoImpl extends SqlSessionDaoSupport implements EbParaValueDao{

	String ns = "com.rl.ecps.mapper.EbParaValueMapper.";

	@Override
	public void saveParaValue(List<EbParaValue> ebParaList,Long itemId) {
		//获得到会话，整个集合用同一次数据的链接，节省资源
		SqlSession session = this.getSqlSession();
		for (EbParaValue ebParaValue : ebParaList) {
			ebParaValue.setItemId(itemId);
			session.insert(ns+"saveParaValue", ebParaValue);
		}
		
	}


}