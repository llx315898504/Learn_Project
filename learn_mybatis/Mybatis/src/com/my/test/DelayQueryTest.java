package com.my.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.my.model.Order;
import com.my.model.Person;

/**
 * Mybatis的延迟加载查询
 * 
 * @author Administrator
 *
 */
public class DelayQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
		
		
		
		/**
		 * 根据用户信息查询订单信息（一对多的延迟加载）
		 * 
		 */
		@Test
		public void selectOrderByPersonLazy() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Person  person;
			try {
				//发出主sql
				person  = sqlSession.selectOne(
						"com.my.mapper.PersonMapper.selectOrderByPersonLazy", 1);
				//发出子sql
				System.out.println(person.getOrderList());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据订单编号查询用户信息信息（多对一的延迟加载）
		 * 
		 */
		@Test
		public void selectPersonByorderlazy() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Order  order;
			try {
				//发出主sql
				order  = sqlSession.selectOne(
						"com.my.mapper.OrderMapper.selectPersonByorderlazy", 1);
				//发出子sql
				System.out.println(order.getPerson());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据订单编号查询用户信息信息和订单明细信息（多对一和一对多的联合延迟加载）
		 * 
		 */
		@Test
		public void selectPersonAndDetailByorderlazy() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Order  order;
			try {
				//发出主sql
				order  = sqlSession.selectOne(
						"com.my.mapper.OrderMapper.selectPersonAndDetailByorderlazy", 1);
				//发出子sql
				System.out.println(order.getPerson());
				//发出子sql
				System.out.println(order.getOrderDetailList());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
}