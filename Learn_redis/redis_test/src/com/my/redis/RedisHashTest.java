package com.my.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis散列类型的用法
 * 
 * 
 * @author Administrator
 *
 */
public class RedisHashTest {
	
	/**
	 * hset一次只存储一个字段的值
	 */
	@Test
	public void testJedisHset() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		js.hset("user", "username", "唐霞");	
		System.out.println("保存数据成功");
		js.close();
	}
	
	/**
	 * hget一次只获取一个字段的值
	 */
	@Test
	public void testJedisHget() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		String username=js.hget("user", "username");
		System.out.println(username);
		js.close();
	}
	
	/**
	 * hmset一次存储多个字段的值
	 */
	@Test
	public void testJedisHMset() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Map <String,String>usermap=new HashMap<String, String>();
		js.flushAll();
		usermap.put("username", "唐霞");
		usermap.put("age", "32");
		usermap.put("address", "安康");
		js.hmset("user", usermap);
		js.close();
	}
	
	
	/**
	 * hmget一次获取多个字段的值
	 */
	@Test
	public void testJedisHMget() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> list=js.hmget("user", new String[]{"username","age","address"});
		System.out.println(list);
		js.close();
	}
	
	
	/**
	 * exists是否存在某一个记录
	 */
	@Test
	public void testJedisExists() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.exists("person");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hexists是否存在某一个字段
	 */
	@Test
	public void testJedisHexists() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.hexists("user", "username");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hsetnx当字段不存在时赋值，类似HSET，区别在于如果字段已经存在，该命令不执行任何操作。
	 */
	@Test
	public void testJedisHsetnx() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hsetnx("user", "sex", "男");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * Hdel可以删除一个或多个字段，返回值是被删除的字段个数 
	 */
	@Test
	public void testJedisHdel() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hdel("user", "sex", "男");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * Hdel多个字段，返回值是被删除的字段个数 
	 */
	@Test
	public void testJedisHdelMany() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hdel("user", new String[]{"username","age"});
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hkeys获取字段名
	 */
	@Test
	public void testJedisHkeys() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> user=js.hkeys("user");
		System.out.println(user);
		js.close();
	}
	
	
	/**
	 * hvals获取字段值
	 */
	@Test
	public void testJedisHvals() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> user=js.hvals("user");
		System.out.println(user);
		js.close();
	}
	
	/**
	 * hgetAll获取z字段名和字段的值
	 */
	@Test
	public void testJedishgetAll() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Map<String, String> user=js.hgetAll("user");
		System.out.println(user);
		js.close();
	}
	
	
	
	/**
	 * hlen获取字段的数量
	 */
	@Test
	public void testJedishHlen() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.hlen("user");
		System.out.println(num);
		js.close();
	}
}