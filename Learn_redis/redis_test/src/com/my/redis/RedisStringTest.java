package com.my.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * �ַ������͵��÷�
 * 
 * @author Administrator
 *
 */
public class RedisStringTest {

	@Test
	/**
	 * ����set��ʽ���ַ�����ֵ
	 */
	public void testStringset() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.set("address", "beijing");
		System.out.println("�������ݳɹ�");
		jedis.close();
	}

	@Test
	/**
	 * �����ӳ��л�ȡһ������
	 */
	public void testJedispool() {
		// 1������redis���ӳ����ö���
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxIdle(20);
		jpc.setMaxTotal(50);
		// �������ӳض���
		JedisPool jP = new JedisPool(jpc, "192.168.49.128", 6379);
		// �����ӳػ�ȡһ������
		Jedis js = jP.getResource();
		String adrress = js.get("address");
		System.out.println(adrress);
		// �ͷ�����
		jP.returnResource(js);
	}

	@Test
	/**
	 * ����get��ʽ��ȡ�ַ�����ֵ
	 */
	public void testStringget(){
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}

	@Test
	/**
	 * ����append��ʽ�޸��ַ���������
	 */
	public void testStringAppend() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.append("name", " helloWWorld");
		System.out.println("�޸�name��ֵ�ɹ�");
		jedis.close();
	}

	
	@Test
	/**
	 * ����strlen��ʽ��ȡ�ַ����ĳ���
	 */
	public void testStringStrleb() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		long lenth=jedis.strlen("name");
		System.out.println("�ַ����ĳ����ǣ� "+lenth);
		jedis.close();
	}
	
	
	
	@Test
	/**
	 * ����mset��ʽ�������ַ���
	 */
	public void testStringMset() {
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		jedis.mset(new String[]{"name","��ϼ","age","32","address","������"});
		System.out.println("���ݱ���ɹ�");
		jedis.close();
	}
}