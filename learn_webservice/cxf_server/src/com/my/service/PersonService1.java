package com.my.service;

import javax.jws.WebService;

@WebService
public class PersonService1 {
	
	public String sayHello(String Name){
		return  "hello "+Name;
	}

}
