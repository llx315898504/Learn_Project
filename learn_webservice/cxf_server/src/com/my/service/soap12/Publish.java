package com.my.service.soap12;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * ע�⣺1���������ϲ���Ҫ��ע��
 * 2����������û�з�������Ҳ���ᱨ������wsdl�ĵ���Ч
 * 3��ȱ�㣺���ɵ�wsdl�ĵ��е��������淶��
 * 4��JaxWsServerFactoryBean��������ʱ���������ϱ����@WebServiceע�⣬���ɵ�wsdl�ĵ�������ʽ�ǹ淶�ģ�
 * �����ķ���������з�������������û�з�������Ҳ���ᱨ������wsdl�ĵ���Ч���������ϲ���ע�ⷢ��Ҳ���ᱨ���������ɵ�wsdl�ĵ���Ч
 * 5��wsimport��ʽ����֧��soap12�汾�����ķ������ɿͻ��˴���ʧЧ����Ҫʹ��wsdl2java�����ɿͻ��˴��룬������֧��soap11��Ҳ֧��soap12
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
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1������CXF��������
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2�����÷������
		sfb.setServiceClass(UserService.class);
		//3.���÷����ַ
		sfb.setAddress("http://192.168.1.100:1111/hello");
		//4.���÷��������
		sfb.setServiceBean(new UserServiceImpl());
		//5.��������
		sfb.create();
		System.out.println("�����ɹ�");
	}

}
