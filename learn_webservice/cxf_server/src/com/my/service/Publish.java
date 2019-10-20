package com.my.service;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * ע�⣺1���������ϲ���Ҫ��ע��
 * 2����������û�з�������Ҳ���ᱨ������wsdl�ĵ���Ч
 * 3��ȱ�㣺���ɵ�wsdl�ĵ��е��������淶��
 * 4��JaxWsServerFactoryBean��������ʱ���������ϱ����@WebServiceע�⣬���ɵ�wsdl�ĵ�������ʽ�ǹ淶�ģ�
 * �����ķ���������з�������������û�з�������Ҳ���ᱨ������wsdl�ĵ���Ч���������ϲ���ע�ⷢ��Ҳ���ᱨ���������ɵ�wsdl�ĵ���Ч
 * 
 * @author Administrator
 *
 */
public class Publish {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		publisherWSServerwithJaxWsServerFactoryBean();
	}
	/**
	 * ��������ķ�ʽ����WSserver
	 * 
	 */
	public static   void   publisherWSServerwithFactoryBean(){
		//1������CXF��������
		ServerFactoryBean sfb=new ServerFactoryBean();
		//2�����÷������
		sfb.setServiceClass(PersonService.class);
		//3.���÷����ַ
		sfb.setAddress("http://192.168.1.100:8990/hello");
		//4.���÷��������
		sfb.setServiceBean(new PersonService());
		//5.��������
		sfb.create();
		System.out.println("�����ɹ�");
	}
	/**
	 * ��������ķ�ʽ����WSserver
	 * 
	 */
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1������CXF��������
	   
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2�����÷������
		sfb.setServiceClass(PersonService1.class);
		//3.���÷����ַ
		sfb.setAddress("http://192.168.1.100:9999/hello");
		//4.���÷��������
		sfb.setServiceBean(new PersonService1());
		//5.��������
		sfb.create();
		System.out.println("�����ɹ�");
	}

}
