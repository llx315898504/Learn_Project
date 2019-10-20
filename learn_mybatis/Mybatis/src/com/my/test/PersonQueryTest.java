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

import com.my.model.Person;
import com.my.model.QueryCondition;
/**
 * Mybatis的简单查询
 * 
 * @author Administrator
 *
 */
public class PersonQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
	/**
	 * 根据用户编号查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById() {
		//创建SqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		Person person;
		try {
			//selectOne：第一个参数：要执行的sql，命名空间.sql的id
			//第二个参数是传递给sql的实际参数
			person = sqlSession.selectOne("com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	
	/**
	 * 根据用户编号查询用户测试表信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonTestById() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		com.my.model.PersonTest person;
		try {
			person = sqlSession.selectOne("com.my.mapper.PersonTestMapper.queryPersonTestById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 查询用户测试表的总记录数
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonTestCount() {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		Integer Count=0;
		try {
			Count = sqlSession.selectOne("com.my.mapper.PersonTestMapper.queryPersonTestCount");
			System.out.println(Count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	/**
	 * 查询所有用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void selectPersonAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Person> pList;
		try {
			pList = sqlSession
					.selectList("com.my.mapper.PersonMapper.selectPersonAll");
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
	 *以对象的方式多条件查询用户信息
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void selectPersonByParams() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Person> pList;
		try {
			QueryCondition qc = new QueryCondition();
			qc.setGender(2);
			qc.setBirthday(new Date());
			pList = sqlSession.selectList(
					"com.my.mapper.PersonMapper.selectPersonByParams", qc);
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
		 * 以map的方式多条件查询用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByMapParams() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Person> pList;
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("gender", 2);
				map.put("birthday", new Date());
				pList = sqlSession.selectList(
						"com.my.mapper.PersonMapper.selectPersonByMapParams", map);
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
		 * 根据用户性别模糊查询用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByGender() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("gender",2);
				List<Person>pList = sqlSession.selectList("com.my.mapper.PersonMapper.selectPersonByGender",map);
				for (Person person : pList) {
					System.out.println(person);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
		}
}