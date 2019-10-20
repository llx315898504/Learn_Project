package com.my.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis集合类型set的用法
 * 
 * 
 * @author Administrator
 *
 */
public class RedisSetTest {
	
	
	/**
	 * sadd向集合中添加元素
	 */
	@Test
	public void testJedishSadd() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		js.flushAll();
		Long num=js.sadd("myset", new String[]{"a","b","c","d"});
		System.out.println(num);
		js.close();
	}
	
	/**
	 * srem删除集合中的元素
	 */
	@Test
	public void testJedishSrem() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.srem("myset", new String[]{"a","c","d"});
		System.out.println(num);
		js.close();
	}

	/**
	 * smembers获取集合中的所有元素
	 */
	@Test
	public void testJedishSmembers() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> set=js.smembers("myset");
		System.out.println(set);
		js.close();
	}
	
	
	
	/**
	 * sismember判断元素是否在集合中，无论集合中有多少元素都可以极速的返回结果。 
	 */
	@Test
	public void testSismember() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.sismember("myset", "f");
		System.out.println(flag);
		js.close();
	}
}