package com.my.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 字符串类型的用法
 * 
 * @author Administrator
 *
 */
public class RedisStringTest {

	@Test
	/**
	 * 测试set方式给字符串赋值
	 */
	public void testStringset() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.set("address", "beijing");
		System.out.println("保存数据成功");
		jedis.close();
	}

	@Test
	/**
	 * 从连接池中获取一个连接
	 */
	public void testJedispool() {
		// 1、创建redis连接池配置对象
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxIdle(20);
		jpc.setMaxTotal(50);
		// 创建连接池对象
		JedisPool jP = new JedisPool(jpc, "192.168.49.128", 6379);
		// 从连接池获取一个连接
		Jedis js = jP.getResource();
		String adrress = js.get("address");
		System.out.println(adrress);
		// 释放连接
		jP.returnResource(js);
	}

	@Test
	/**
	 * 测试get方式获取字符串的值
	 */
	public void testStringget(){
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}

	@Test
	/**
	 * 测试append方式修改字符串的内容
	 */
	public void testStringAppend() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.append("name", " helloWWorld");
		System.out.println("修改name的值成功");
		jedis.close();
	}

	
	@Test
	/**
	 * 测试strlen方式获取字符串的长度
	 */
	public void testStringStrleb() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		long lenth=jedis.strlen("name");
		System.out.println("字符串的长度是： "+lenth);
		jedis.close();
	}
	
	
	
	@Test
	/**
	 * 测试mset方式储存多个字符串
	 */
	public void testStringMset() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.mset(new String[]{"name","唐霞","age","32","address","安康市"});
		System.out.println("数据保存成功");
		jedis.close();
	}
}