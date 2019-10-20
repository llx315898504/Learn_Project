package com.my.person;

import com.my.web.person.PersonService;
import com.my.web.person.PersonServiceService;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonServiceService  pss=new PersonServiceService();
		PersonService ps=pss.getPersonServicePort();
		System.out.println(ps.sayLove("õõ²õ"));

	}

}
