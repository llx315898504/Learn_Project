package com.my.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * redis排序集合类型的用法
 * 
 * 
 * @author Administrator
 *
 */
public class RedisSortedSetTest {
	
	/**
	 * zadd向有序集合中增加元素
	 */
	@Test
	public void testJedisZadd() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Map<String, Double> map=new HashMap<String, Double>();
		map.put("zhangsan", 80d);
		map.put("lisi", 86d);
		map.put("wangwu", 70d);
		map.put("guanyu", 99d);
		map.put("lvbu", 100d);
		Long num=js.zadd("myscore", map);
		System.out.println(num);
		js.close();
	}
	
	
	
	/**
	 * zscore获取有序集合中元素的分数
	 */
	@Test
	public void testJedisZscore() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		double score=js.zscore("myscore", "lisi");
		System.out.println(score);
		js.close();
	}
	
	
	/**
	 * zrange获得排名在某个范围的元素列表(从小到大的顺序)
	 */
	@Test
	public void testJedisZrange() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> set =js.zrange("myscore", 0, 3);
		System.out.println(set);
		js.close();
	}
	
	
	/**
	 * zrevrange获得排名在某个范围的元素列表(从大到小的顺序)
	 */
	@Test
	public void testJedisZrevrange() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> set =js.zrevrange("myscore", 0, 3);
		System.out.println(set);
		js.close();
	}
	
	
	/**
	 * zrevrangeWithScores获得排名在某个范围的元素及分数列表(从大到小的顺序)
	 */
	@Test
	public void testJedisZrevrangeWithScores() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<Tuple> set =js.zrevrangeWithScores("myscore", 0, 3);
		for (Tuple t:set) {
		String element=	t.getElement();
		double score=t.getScore();
		System.out.println("元素:"+element+"分数:"+score);
		}
		js.close();
	}
	
	
	
	/**
	 * zrangeByScoreWithScores获得指定分数范围的元素及分数
	 */
	@Test
	public void testJedisZrangeByScoreWithScores() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<Tuple> set =js.zrangeByScoreWithScores("myscore", 70, 100);
		for (Tuple t:set) {
		String element=	t.getElement();
		double score=t.getScore();
		System.out.println("元素:"+element+"分数:"+score);
		}
		js.close();
	}
	
	
	
	/**
	 * zincrby增加某个元素的分数，返回值是更改后的分数
	 */
	@Test
	public void testJedisZincrby() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		double score =js.zincrby("myscore",  5d,"lisi");
		System.out.println(score);
		js.close();
	}
	
	
	/**
	 * zcard获得集合中元素的数量 
	 */
	@Test
	public void testJediszcard() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num =js.zcard("myscore");
		System.out.println(num);
		js.close();
	}
	
	
	
	/**
	 * zcount获得指定分数范围内的元素个数 
	 */
	@Test
	public void testJediszcount() {
		//获取jedis对象
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num =js.zcount("myscore", 70, 100);
		System.out.println(num);
		js.close();
	}
}

