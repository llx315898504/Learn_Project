package com.my.service;

import javax.jws.WebService;


@WebService
public class PersonServce {
	
	
	public String  sayHello(String name){
	
	return name+" welcome to ws!";
	
	}
	

}
