package com.my.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class UserServiceIMpl implements UserService {

	@Override
	public  String sayLove(String name) {
		return name+"I love you ";
	}

	@Override
	@WebMethod(exclude=true)
	public  String sayHello(String name) {
		return name +"ÄãºÃ";
	}

	
}
