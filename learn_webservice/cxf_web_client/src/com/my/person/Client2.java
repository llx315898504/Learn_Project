package com.my.person;


import java.util.List;

import com.my.spring.person.Person;
import com.my.spring.person.PersonService;
import com.my.spring.person.PersonServiceService;

public class Client2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		save();
	}
	public static void save(){
	   PersonServiceService  pss=new PersonServiceService();
		PersonService ps=pss.getPersonServicePort();
		Person p=new Person();
		p.setId(2);
		p.setName("zhaoyun");
		p.setGender(1);
	    ps.addPerson("123",p);
	}
	public static void listPerson(){
		   PersonServiceService  pss=new PersonServiceService();
			PersonService ps=pss.getPersonServicePort();
			List<Person> list=ps.listPerson();
			for (Person p:list) {
				System.out.println(p);
			}
	}
}
