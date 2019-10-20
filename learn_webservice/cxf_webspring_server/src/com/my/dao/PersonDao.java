package com.my.dao;

import java.util.List;

import com.my.service.model.Person;

public interface PersonDao {

	public void addPerson(String password, Person p);

	public List<Person> listPerson();
}
