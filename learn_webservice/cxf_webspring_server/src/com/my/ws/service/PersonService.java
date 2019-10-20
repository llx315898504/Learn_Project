package com.my.ws.service;

import java.util.List;
import javax.jws.WebService;

import com.my.service.model.Person;
@WebService
public interface PersonService {
	
	public String addPerson(String password, Person p);

	public List<Person> listPerson();
}
