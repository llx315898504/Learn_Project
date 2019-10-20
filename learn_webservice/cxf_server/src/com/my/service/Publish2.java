package com.my.service;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * ע�⣺ע�������ڽӿ���
 *
 * @author Administrator
 *
 */
public class Publish2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		publisherWSServerwithJaxWsServerFactoryBean();
	}
	/**
	 * �ӿ�ʵ����ķ�ʽ����WSserver
	 * 
	 */
	public static   void   publisherWSServerwithFactoryBean(){
		//1������CXF��������
		ServerFactoryBean sfb=new ServerFactoryBean();
		//2�����÷������
		sfb.setServiceClass(UserService.class);
		//3.���÷����ַ
		sfb.setAddress("http://192.168.1.100:7777/hello");
		//4.���÷��������
		sfb.setServiceBean(new UserServiceImpl());
		//5.��������
		sfb.create();
		System.out.println("�����ɹ�");
	}
	
	/**
	 * �ӿ�ʵ����ķ�ʽ����WSserver
	 * 
	 */
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1������CXF��������
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2�����÷���Ľӿ���
		sfb.setServiceClass(UserService.class);
		//3.���÷����ַ
		sfb.setAddress("http://localhost:6666/user");
		//4.���ýӿڵ�ʵ�������
		sfb.setServiceBean(new UserServiceImpl());
		//���������������Ϣ������
		sfb.getInInterceptors().add(new LoggingInInterceptor());
		sfb.getOutInterceptors().add(new LoggingOutInterceptor());
		//5.��������
		sfb.create();
		System.out.println("�����ɹ�");
	}

}
