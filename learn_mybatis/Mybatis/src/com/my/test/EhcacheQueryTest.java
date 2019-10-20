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
 * Mybatis的二级缓存的使用。
 * 二级缓存的特点：我们可以管理配置二级缓存
 * @author Administrator
 *
 */
public class EhcacheQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
	
	
	/**
	 * 二级缓存：库表查询，可以在同一个sqlSessionFactory内跨session
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById() {
		// 创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//首先去查询二级缓存，若有数据就从其中拿出独居就可以；如果没有数据就去一级缓存中查；
			//若以及缓存中也没有数据，就去查数据库
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
	 * 二级缓存:库表变更
	 * 
	 * @author Administrator
	 * 
	 */
	@Test
	public void queryPersonById1() {
		// 创建SqlSession
		Person person =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//首先去查询二级缓存，若有数据就从其中拿出独居就可以；如果没有数据就去一级缓存中查；
			//若以及缓存中也没有数据，就去查数据库
			person = sqlSession.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		try {
			person.setName("小叶子");
			person.setAddress("四川");
			person.setGender(2);
			person.setBirthday(new Date());
			//和一级缓存同理，数据库变更会清掉所影响的缓存
			sqlSession1.update(
					"com.my.mapper.PersonMapper.dynamicUpdatePerson", person);
			sqlSession1.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession1.close();
		}
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		try {
			// 第一次查询的是数据库
			person = sqlSession2.selectOne(
					"com.my.mapper.PersonMapper.queryPersonById", 1);
			System.out.println(person);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession2.close();
		}

	}
}