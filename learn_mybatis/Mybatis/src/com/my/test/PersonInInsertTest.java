package com.my.test;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.my.model.Person;

/**
 * 用户表的变更操作
 * 
 * @author Administrator
 *
 */
public class PersonInInsertTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
	/**
	 * 插入用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void insertPerson() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			Person p=new Person();
			p.setName("黄月英");
			p.setGender(2);
			p.setAddress("无锡");
			p.setBirthday(new Date());
			Integer count=sqlSession.insert("com.my.mapper.PersonMapper.insertPerson", p);
			//数据库的变更操作都要提交事务
			sqlSession.commit();
			System.out.println(count);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 修改用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void updatePerson() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			Person p=new Person();
			p.setId(3);
			p.setName("大乔美女");
			p.setGender(2);
			p.setAddress("无锡");
			p.setAge(20);
			p.setAddress("杭州");
			p.setBirthday(new Date());
			Integer count=sqlSession.insert("com.my.mapper.PersonMapper.updatePerson", p);
			//数据库的变更操作都要提交事务
			sqlSession.commit();
			System.out.println(count);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	/*
	 * 删除用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void deletePerson() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			Integer count=sqlSession.delete("com.my.mapper.PersonMapper.deletePerson", 5);
			//数据库的变更操作都要提交事务
			sqlSession.commit();
			System.out.println(count);
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
}