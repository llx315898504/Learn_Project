package com.my.service;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 注意：注解必须加在接口上
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
	 * 接口实现类的方式发布WSserver
	 * 
	 */
	public static   void   publisherWSServerwithFactoryBean(){
		//1、创建CXF发布对象
		ServerFactoryBean sfb=new ServerFactoryBean();
		//2、设置服务的类
		sfb.setServiceClass(UserService.class);
		//3.设置服务地址
		sfb.setAddress("http://192.168.1.100:7777/hello");
		//4.设置服务类对象
		sfb.setServiceBean(new UserServiceImpl());
		//5.发布服务
		sfb.create();
		System.out.println("发布成功");
	}
	
	/**
	 * 接口实现类的方式发布WSserver
	 * 
	 */
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1、创建CXF发布对象
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2、设置服务的接口类
		sfb.setServiceClass(UserService.class);
		//3.设置服务地址
		sfb.setAddress("http://localhost:6666/user");
		//4.设置接口的实现类对象
		sfb.setServiceBean(new UserServiceImpl());
		//设置输入输出的消息拦截器
		sfb.getInInterceptors().add(new LoggingInInterceptor());
		sfb.getOutInterceptors().add(new LoggingOutInterceptor());
		//5.发布服务
		sfb.create();
		System.out.println("发布成功");
	}

}
