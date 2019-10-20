package com.my.client;

import com.my.user.stub.UserServiceIMpl;
import com.my.user.stub.UserServiceIMplService;

public class client1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserServiceIMplService pss=new UserServiceIMplService();
		UserServiceIMpl ps=pss.getUserServiceIMplPort();
		String result=ps.sayLove("ежеж");
		System.out.println(result);
	
		

	}

}
