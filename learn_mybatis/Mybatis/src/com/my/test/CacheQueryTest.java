package com.my.test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.my.model.Person;

/**
 * Mybatis的一级缓存的使用。
 * 一级缓存的特点：一级缓存不受我们配置，由Mybatis自己管理。
 * @author Administrator
 *
 */
public class CacheQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
	/**
	 * 一级缓存：在session范围之内
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById() {
		//创建SqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			//第一次查询的是数据库
			//首先去缓存中根据结果集的唯一标识（Mybatis自己的策略）去查找数据，
			//若缓存中存在改数据就使用该数据，若缓存中没有该数据就去数据库中
			Person	person = sqlSession.selectOne("com.my.mapper.PersonMapper.queryPersonById", 1);
			//第二次查询的是缓存
			Person	person1 = sqlSession.selectOne("com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
			System.out.println(person1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 一级缓存：跨session不能使用一级缓存
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById1() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// 第一次查询的是数据库
			Person person = sqlSession.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		try {
			// 第一次查询的是数据库
			Person person = sqlSession1.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession1.close();
		}

	}
	/**
	 * 一级缓存：在session范围之内
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById2() {
		//创建SqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		try {
			//第一次查询的是数据库
			//首先去缓存中根据结果集的唯一标识（Mybatis自己的策略）去查找数据，
			//若缓存中存在改数据就使用该数据，若缓存中没有该数据就去数据库中
			Person person = sqlSession.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
			person.setId(8);
			person.setName("貂蝉2");
			person.setAddress("四川");
			person.setGender(2);
			person.setBirthday(new Date());
			//数据的变更会刷新缓存（清除缓存），只对当前修改数据有影响。
			int count = sqlSession.update(
					"com.my.mapper.PersonMapper.dynamicUpdatePerson", person);
			sqlSession.commit();
			System.out.println(count);
			// 第二次查询的是数据库
			Person person1 = sqlSession.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		List<Person> pList,pList1;
		try {
			pList = sqlSession
					.selectList("com.my.mapper.PersonMapper.selectPersonAll");
			pList1 = sqlSession
					.selectList("com.my.mapper.PersonMapper.selectPersonAll");
			for (Person person : pList) {
				System.out.println(person);
			}
			for (Person person : pList1) {
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public void selectPersonAll1() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Person> pList,pList1;
		try {
			pList = sqlSession
					.selectList("com.my.mapper.PersonMapper.selectPersonAll");
			Person p=new Person();
			p.setName("黄月英");
			p.setGender(2);
			p.setAddress("无锡");
			p.setBirthday(new Date());
			//插入和删除会对结果集的缓存数据刷新(清楚缓存)
			Integer count=sqlSession.insert("com.my.mapper.PersonMapper.insertPerson", p);
			//数据库的变更操作都要提交事务
			sqlSession.commit();
			System.out.println(count);
			pList1 = sqlSession
					.selectList("com.my.mapper.PersonMapper.selectPersonAll");
			for (Person person : pList) {
				System.out.println(person);
			}
			for (Person person : pList1) {
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}