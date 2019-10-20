package com.my.client;

import com.my.user.UserService;
import com.my.user.UserServiceService;

public class Client1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceService  uss=new UserServiceService();
		UserService us=uss.getUserServicePort();
		System.out.println(us.sayLove("õõ²õ"));

	}

}
