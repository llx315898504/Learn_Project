package com.my.person;

import com.my.web.user.UserService;
import com.my.web.user.UserServiceService;

public class Client1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceService  uss=new UserServiceService();
		UserService us=uss.getUserServicePort();
		System.out.println(us.sayHate("ÂÀ²¼"));

	}

}
