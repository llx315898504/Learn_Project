package com.my.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redis��������set���÷�
 * 
 * 
 * @author Administrator
 *
 */
public class RedisSetTest {
	
	
	/**
	 * sadd�򼯺������Ԫ��
	 */
	@Test
	public void testJedishSadd() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		js.flushAll();
		Long num=js.sadd("myset", new String[]{"a","b","c","d"});
		System.out.println(num);
		js.close();
	}
	
	/**
	 * sremɾ�������е�Ԫ��
	 */
	@Test
	public void testJedishSrem() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.srem("myset", new String[]{"a","c","d"});
		System.out.println(num);
		js.close();
	}

	/**
	 * smembers��ȡ�����е�����Ԫ��
	 */
	@Test
	public void testJedishSmembers() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> set=js.smembers("myset");
		System.out.println(set);
		js.close();
	}
	
	
	
	/**
	 * sismember�ж�Ԫ���Ƿ��ڼ����У����ۼ������ж���Ԫ�ض����Լ��ٵķ��ؽ���� 
	 */
	@Test
	public void testSismember() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.sismember("myset", "f");
		System.out.println(flag);
		js.close();
	}
}