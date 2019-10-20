package com.my.client;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.my.user.Person;
import com.my.user.PersonService;
import com.my.user.PersonServiceService;

public class PersonServiceTest {
private PersonService ps;
	
	@Before
	public void setup() {
		PersonServiceService pss=new PersonServiceService();
		ps=pss.getPersonServicePort();
	}
		
	@Test
	public void testDeletePerson() {
		ps.deletePerson(2);
	}

	@Test
	public void testAddperson() {
		Person person=new Person();
		person.setId(3);
		person.setName("¹ØÓð");
		person.setAdress("Êñ¹ú");
		person.setAge(20);
		person.setGender(2);
		ps.addperson(person);
	}

	@Test
	public void testQueryPerson() {
		List<Person> personslist=ps.queryPerson(null);
		for(Person p:personslist){
			System.out.println("ÐÕÃû£º"+p.getName()+"id:"+p.getId());
		}
	}

}

