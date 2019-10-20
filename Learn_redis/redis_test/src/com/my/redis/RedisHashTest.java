package com.my.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * redisɢ�����͵��÷�
 * 
 * 
 * @author Administrator
 *
 */
public class RedisHashTest {
	
	/**
	 * hsetһ��ֻ�洢һ���ֶε�ֵ
	 */
	@Test
	public void testJedisHset() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		js.hset("user", "username", "��ϼ");	
		System.out.println("�������ݳɹ�");
		js.close();
	}
	
	/**
	 * hgetһ��ֻ��ȡһ���ֶε�ֵ
	 */
	@Test
	public void testJedisHget() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		String username=js.hget("user", "username");
		System.out.println(username);
		js.close();
	}
	
	/**
	 * hmsetһ�δ洢����ֶε�ֵ
	 */
	@Test
	public void testJedisHMset() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Map <String,String>usermap=new HashMap<String, String>();
		js.flushAll();
		usermap.put("username", "��ϼ");
		usermap.put("age", "32");
		usermap.put("address", "����");
		js.hmset("user", usermap);
		js.close();
	}
	
	
	/**
	 * hmgetһ�λ�ȡ����ֶε�ֵ
	 */
	@Test
	public void testJedisHMget() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> list=js.hmget("user", new String[]{"username","age","address"});
		System.out.println(list);
		js.close();
	}
	
	
	/**
	 * exists�Ƿ����ĳһ����¼
	 */
	@Test
	public void testJedisExists() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.exists("person");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hexists�Ƿ����ĳһ���ֶ�
	 */
	@Test
	public void testJedisHexists() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		boolean flag=js.hexists("user", "username");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hsetnx���ֶβ�����ʱ��ֵ������HSET��������������ֶ��Ѿ����ڣ������ִ���κβ�����
	 */
	@Test
	public void testJedisHsetnx() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hsetnx("user", "sex", "��");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * Hdel����ɾ��һ�������ֶΣ�����ֵ�Ǳ�ɾ�����ֶθ��� 
	 */
	@Test
	public void testJedisHdel() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hdel("user", "sex", "��");
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * Hdel����ֶΣ�����ֵ�Ǳ�ɾ�����ֶθ��� 
	 */
	@Test
	public void testJedisHdelMany() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long flag=js.hdel("user", new String[]{"username","age"});
		System.out.println(flag);
		js.close();
	}
	
	
	/**
	 * hkeys��ȡ�ֶ���
	 */
	@Test
	public void testJedisHkeys() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Set<String> user=js.hkeys("user");
		System.out.println(user);
		js.close();
	}
	
	
	/**
	 * hvals��ȡ�ֶ�ֵ
	 */
	@Test
	public void testJedisHvals() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> user=js.hvals("user");
		System.out.println(user);
		js.close();
	}
	
	/**
	 * hgetAll��ȡz�ֶ������ֶε�ֵ
	 */
	@Test
	public void testJedishgetAll() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Map<String, String> user=js.hgetAll("user");
		System.out.println(user);
		js.close();
	}
	
	
	
	/**
	 * hlen��ȡ�ֶε�����
	 */
	@Test
	public void testJedishHlen() {
		//��ȡjedis����
		Jedis js=new Jedis("192.168.49.128", 6379);
		Long num=js.hlen("user");
		System.out.println(num);
		js.close();
	}
}