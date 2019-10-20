package com.my.client;

import com.my.user1.UserService;
import com.my.user1.UserServiceService;




public class Client2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceService  uss=new UserServiceService();
		UserService us=uss.getUserServicePort();
		System.out.println(us.sayLove("õõ²õ"));

	}

}
