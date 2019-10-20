package com.my.test;

import java.io.InputStream;
import java.util.ArrayList;
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
 * Mybatis的动态sql操作
 * 
 * @author Administrator
 *
 */
public class PersonDynamicQueryTest {
     SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
	}
	
		/**
		 * 动态多条件查询用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonBycondition() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Person> pList;
			try {
				QueryCondition qc=new QueryCondition();
				qc.setName("黄");
				qc.setAddress("江");
				qc.setGender(1);
				qc.setBirthday(new Date());
				pList = sqlSession.selectList(
						"com.my.mapper.PersonMapper.selectPersonBycondition", qc);
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
		 * 动态更新用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void dynamicUpdatePerson() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				Person p=new Person();
				p.setId(8);
				p.setName("貂蝉2");
				p.setAddress("四川");
				p.setGender(2);
				p.setBirthday(new Date());
				int count=sqlSession.update("com.my.mapper.PersonMapper.dynamicUpdatePerson", p);
				sqlSession.commit();
				System.out.println(count);
			} catch (Exception e) {
				e.printStackTrace();
				sqlSession.rollback();
			} finally {
				sqlSession.close();
			}
		}
	
		/**
		 * 根据多个用户编号查询用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void selectPersonByIn() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Person> pList;
			try {
				int [] ids={6,7,8};
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("ids", ids);
				pList = sqlSession.selectList(
						"com.my.mapper.PersonMapper.selectPersonByIn", map);
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
		 * 批量新增用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void batchInsert() {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				List<Person> pList=new ArrayList<Person>();
				for (int i =0; i <=1000008; i++) {
					Person p=new Person();
					p.setName("黄月英"+i);
					p.setGender(2);
					p.setAddress("无锡");
					p.setBirthday(new Date());
					pList.add(p);
					if(i%100==0){
						map.put("personList", pList);
						//每100条新增一次
						sqlSession.delete("com.my.mapper.PersonMapper.batchInsert", map);
						//清空java内存
						pList.clear();
					}
				}
				//非100整数倍的条数，插入数据库
				map.put("personList", pList);
				sqlSession.insert("com.my.mapper.PersonMapper.batchInsert", map);
				//数据库的变更操作都要提交事务
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				e.printStackTrace();
			}finally{
				sqlSession.close();
			}
		}
		
		
		/**
		 * 批量删除用户信息
		 * 
		 * @author Administrator
		 * 
		 */
		@Test
		public void batchDelete() {
			SqlSession sqlSession=sqlSessionFactory.openSession();
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				List<Integer> idList=new ArrayList<Integer>();
				for (int i =109 ; i <=1000116; i++) {
					idList.add(i);
					if(i%100==0){
						map.put("ids", idList);
						//每100条删除一次
						sqlSession.insert("com.my.mapper.PersonMapper.batchDelete", map);
						//清空java内存
						idList.clear();
					}
				}
				//非100整数倍的条数，删除
				map.put("ids", idList);
				sqlSession.insert("com.my.mapper.PersonMapper.batchDelete", map);
				//数据库的变更操作都要提交事务
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				e.printStackTrace();
			}finally{
				sqlSession.close();
			}
		}
}