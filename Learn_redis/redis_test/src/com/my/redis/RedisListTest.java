package com.my.redis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

/**
 * redis列表类型的用法
 * 
 * 
 * @author Administrator
 *
 */
public class RedisListTest {
	
	/**
	 * rpush向列表右边增加元素
	 */
	@Test
	public void testJedisRpush() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.rpush("studentNo", new String[]{"1","2","4","3","3","3"});
		System.out.println(num);
		js.close();
	}
	
	/**
	 * lpop:命令从列表左边弹出一个元素，会分两步完成，
	 * 第一步是将列表左边的元素从列表中移除，第二步是返回被移除的元素值。 
	 */
	@Test
	public void testJedisLpop() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		String num=js.lpop("studentNo");
		System.out.println(num);
		js.close();
	}
	
	
	/**
	 * llen:获取列表中元素的个数
	 */
	@Test
	public void testJedisLlen() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.llen("studentNo");
		System.out.println(num);
		js.close();
	}
	
	
	/**
	 * lrange:命令是列表类型最常用的命令之一，获取列表中的某一片段，将返回start、stop之间的所有元素
	 * （包含两端的元素），索引从0开始。索引可以是负数，如：“-1”代表最后边的一个元素。 
	 */
	@Test
	public void testJedisLrange() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> studentNo=js.lrange("studentNo", 0, -1);
		System.out.println(studentNo);
		js.close();
	}
	
	
	/**
	 * lrem:命令会删除列表中前count个值为value的元素，返回实际删除的元素个数。
	 * 根据count值的不同，该命令的执行方式会有所不同： 
	 * 当count>0时， LREM会从列表左边开始删除。 
	 * 当count<0时， LREM会从列表后边开始删除。 
	 * 当count=0时， LREM删除所有值为value的元素。
	 */
	@Test
	public void testJedisLrem() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.lrem("studentNo", 1, "3");
		System.out.println(num);
		js.close();
	}
	
	/**
	 * lindex:获得指定索引的元素值  
	 */
	@Test
	public void testJedisLindex() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		String studentNo=js.lindex("studentNo", 0);
		System.out.println(studentNo);
		js.close();
	}
	
	
	/**
	 * lset:设定指定索引的元素值  
	 */
	@Test
	public void testJedisLset() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		String studentNo=js.lset("studentNo", 0, "88");
		System.out.println(studentNo);
		js.close();
	}
	
	
	
	/**
	 * ltrim:删除指定范围外的元素
	 */
	@Test
	public void testJedisLtrim() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		String studentNo=js.ltrim("studentNo", 0, 2);
		System.out.println(studentNo);
		js.close();
	}
	
	
	/**
	 * linsert:该命令首先会在列表中从左到右查找值为pivot的元素，
	 * 然后根据第二个参数是BEFORE还是AFTER来决定将value插入到该元素的前面还是后面。
	 */
	@Test
	public void testJedisLinsert() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long studentNo=js.linsert("studentNo", LIST_POSITION.BEFORE, "3","99");
		System.out.println(studentNo);
		js.close();
	}
}

