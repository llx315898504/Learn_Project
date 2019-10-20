package com.my.service;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 注意：1、服务类上不需要加注解
 * 2、服务类中没有方法发布也不会报错，但是wsdl文档无效
 * 3、缺点：生成的wsdl文档中的命名不规范。
 * 4、JaxWsServerFactoryBean发布服务时，服务类上必须加@WebService注解，生成的wsdl文档命名格式是规范的，
 * 发布的服务类必须有方法。服务类中没有方法发布也不会报错，但是wsdl文档无效；服务类上不加注解发布也不会报错，但是生成的wsdl文档无效
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
	 * 服务类类的方式发布WSserver
	 * 
	 */
	public static   void   publisherWSServerwithFactoryBean(){
		//1、创建CXF发布对象
		ServerFactoryBean sfb=new ServerFactoryBean();
		//2、设置服务的类
		sfb.setServiceClass(PersonService.class);
		//3.设置服务地址
		sfb.setAddress("http://192.168.1.100:8990/hello");
		//4.设置服务类对象
		sfb.setServiceBean(new PersonService());
		//5.发布服务
		sfb.create();
		System.out.println("发布成功");
	}
	/**
	 * 服务类类的方式发布WSserver
	 * 
	 */
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1、创建CXF发布对象
	   
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2、设置服务的类
		sfb.setServiceClass(PersonService1.class);
		//3.设置服务地址
		sfb.setAddress("http://192.168.1.100:9999/hello");
		//4.设置服务类对象
		sfb.setServiceBean(new PersonService1());
		//5.发布服务
		sfb.create();
		System.out.println("发布成功");
	}

}
