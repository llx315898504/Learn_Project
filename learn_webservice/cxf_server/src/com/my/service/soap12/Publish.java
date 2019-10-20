package com.my.service.soap12;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 注意：1、服务类上不需要加注解
 * 2、服务类中没有方法发布也不会报错，但是wsdl文档无效
 * 3、缺点：生成的wsdl文档中的命名不规范。
 * 4、JaxWsServerFactoryBean发布服务时，服务类上必须加@WebService注解，生成的wsdl文档命名格式是规范的，
 * 发布的服务类必须有方法。服务类中没有方法发布也不会报错，但是wsdl文档无效；服务类上不加注解发布也不会报错，但是生成的wsdl文档无效
 * 5、wsimport方式（不支持soap12版本发布的服务）生成客户端代码失效，需要使用wsdl2java来生成客户端代码，该命令支持soap11，也支持soap12
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
	public static   void   publisherWSServerwithJaxWsServerFactoryBean(){
		//1、创建CXF发布对象
		JaxWsServerFactoryBean sfb=new JaxWsServerFactoryBean();
		//2、设置服务的类
		sfb.setServiceClass(UserService.class);
		//3.设置服务地址
		sfb.setAddress("http://192.168.1.100:1111/hello");
		//4.设置服务类对象
		sfb.setServiceBean(new UserServiceImpl());
		//5.发布服务
		sfb.create();
		System.out.println("发布成功");
	}

}
