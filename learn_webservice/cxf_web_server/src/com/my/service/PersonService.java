package com.my.service;

import javax.jws.WebService;


@WebService
public class PersonService {
	public  String  sayLove(String name){
		
		return name+" love";
	}

}
