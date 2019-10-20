package com.my.test;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.my.dao.PersonDao;
import com.my.model.Person;
import com.my.model.QueryCondition;

/**
 * Mybatis注解形式开发
 * 
 * @author Administrator
 *
 */
public class AnnotationTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
		//注册接口类
		sqlSessionFactory.getConfiguration().addMapper(PersonDao.class);
	}
	
	
	
	/**
	 * 根据用户编号查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			Person person=pDao.queryPersonById(1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 多条件查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonByParam() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			QueryCondition qc=new QueryCondition();
			qc.setGender(1);
			qc.setBirthday(new Date());
			List<Person> pList=pDao.queryPersonByParam(qc);
			for (Person person : pList) {
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	
	/**
	 * 根据姓名查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonByName() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			QueryCondition qc=new QueryCondition();
			qc.setName("叶子");
			List<Person> pList=pDao.queryPersonByName(qc);
			for (Person person : pList) {
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 添加用户
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void addPerson() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			Person p=new Person();
			p.setName("火神");
			p.setAge(30);
			p.setGender(1);
			p.setAddress("魔界");
			p.setBirthday(new Date());
			pDao.addPerson(p);
			//数据库的变更操作都需要提交事务。
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	/**
	 * 修改用户
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void updatePerson() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			Person p=new Person();
			p.setId(8);
			p.setName("旭凤");
			p.setAge(30);
			p.setAddress("天界");
			pDao.updatePerson(p);
			//数据库的变更操作都需要提交事务。
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 删除用户
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void deletePerson() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			pDao.deletePerson(6);
			//数据库的变更操作都需要提交事务。
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * 动态多条件组合查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void selectPersonByDynamic() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("name", "小");
			map.put("gender", 1);
			map.put("address", "四");
			map.put("birthday", new Date());
			List<Person> pList=pDao.selectPersonByDynamic(map);
			for (Person person : pList) {
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	
	
	/**
	 * 根据用户编号查询用户订单信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void selectOrderByPersonId() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//获得所注册接口的实现类
			PersonDao pDao=sqlSession.getMapper(PersonDao.class);
			Person person=pDao.selectOrderByPersonId(1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}