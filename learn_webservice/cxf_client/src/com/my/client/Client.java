package com.my.client;

import com.my.hello.PersonService1;
import com.my.hello.PersonService1Service;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonService1Service  pss=new PersonService1Service();
		PersonService1 ps=pss.getPersonService1Port();
		System.out.println(ps.sayHello("õõ²õ"));

	}

}
