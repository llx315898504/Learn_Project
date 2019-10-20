package com.my.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName="RLPersonService",portName="RLPersonServicePort",name="RLPersonService")
public class PersonService {

	private List<Person> plist = new ArrayList<Person>();

	public void deletePerson(@WebParam(name="id")int id) {
		for (int i = 0; i <plist.size(); i++) {
			Person person = plist.get(i);
			if (person.getId() == id) {
				plist.remove(i);
			}
		}
	};

	public void addperson(Person person) {
		plist.add(person);
	};

	public @WebResult(name="result") List<Person> queryPerson(Person person) {
		return plist;
	};

}
