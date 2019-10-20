package com.rl.ecps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbStudentDao;
import com.rl.ecps.model.EbStudent;
@Repository
public class EbStudentDaoImpl extends SqlSessionDaoSupport implements EbStudentDao {

	String ns = "com.rl.ecps.mapper.EbStudentMapper.";


	@Override
	public void save(EbStudent ebStudent) {
		// TODO Auto-generated method stub
		
	}
	
	
}
