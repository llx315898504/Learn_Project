package com.my.service.soap12;

import javax.jws.WebService;

@WebService
public class UserServiceImpl implements UserService {
	@Override
	public String sayLove(String name) {
		return "love you "+name;
	}

	
}
