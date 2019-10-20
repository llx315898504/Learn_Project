package com.my.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HiberUtils {
	
	private static SessionFactory factory;
	
	static{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//创建服务的注册
		StandardServiceRegistry build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		//创建sessionFactory
		 factory = cfg.buildSessionFactory(build);
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static void closeRes(Session session){
		session.close();
		factory.close();
	}

}
