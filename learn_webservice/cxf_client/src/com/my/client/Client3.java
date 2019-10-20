package com.my.client;

import com.my.user2.UserService;
import com.my.user2.UserServiceService;





public class Client3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceService  uss=new UserServiceService();
		UserService us=uss.getUserServicePort();
		System.out.println(us.sayLove("õõ²õ"));

	}

}
