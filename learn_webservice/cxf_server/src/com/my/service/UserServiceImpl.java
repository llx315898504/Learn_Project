package com.my.service;


public class UserServiceImpl implements UserService {

	@Override
	public String sayLove(String name) {
		return "love you "+name;
	}

	
}
