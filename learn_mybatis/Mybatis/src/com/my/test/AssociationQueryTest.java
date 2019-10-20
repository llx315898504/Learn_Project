package com.my.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.my.model.IdCard;
import com.my.model.Order;
import com.my.model.Person;
import com.my.model.Role;

/**
 * Mybatis的多表关联查询
 * 
 * @author Administrator
 *
 */
public class AssociationQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
		/**
		 * 根据用户信息查询其订单信息(一对多关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectOrderByPersonId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Person  person;
			try {
				person = sqlSession.selectOne(
						"com.my.mapper.PersonMapper.selectOrderByPersonId", 1);
				System.out.println(person);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		
		/**
		 * 根据用户信息查询其订单信息和订单明细信息(一对多关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectOrderandOrderDetailByPersonId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Person  person;
			try {
				person = sqlSession.selectOne(
						"com.my.mapper.PersonMapper.selectOrderandOrderDetailByPersonId", 1);
				System.out.println(person);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据用户编号查询角色信息(多对多关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectRoleByPersonId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Person  person;
			try {
				person = sqlSession.selectOne(
						"com.my.mapper.PersonMapper.selectRoleByPersonId", 4);
				System.out.println(person);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据角色编号查询用户信息(多对多关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByRole() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Role  role;
			try {
				role = sqlSession.selectOne(
						"com.my.mapper.RoleMapper.selectPersonByRole", 1);
				System.out.println(role);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		/**
		 * 根据订单编号查询用户信息(多对一关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByOrderId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Order  order;
			try {
				order = sqlSession.selectOne(
						"com.my.mapper.OrderMapper.selectPersonByOrderId", 1);
				System.out.println(order);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据订单编号查询用户信息及订单明细信息(多对一和一对多的联合查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonAndDetailByOrderId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Order  order;
			try {
				order = sqlSession.selectOne(
						"com.my.mapper.OrderMapper.selectPersonAndDetailByOrderId", 1);
				System.out.println(order);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		/**
		 * 根据用户编号查询身份证信息(一对一的关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectIdCardByPersonId() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Person  person;
			try {
				person = sqlSession.selectOne(
						"com.my.mapper.PersonMapper.selectIdCardByPersonId", 1);
				System.out.println(person);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
		
		
		
		/**
		 * 根据用户编号查询用户信息(一对一的关联查询)
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByIdCard() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			IdCard  idCard;
			try {
				idCard = sqlSession.selectOne(
						"com.my.mapper.IdCardMapper.selectPersonByIdCard", 1);
				System.out.println(idCard);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
}