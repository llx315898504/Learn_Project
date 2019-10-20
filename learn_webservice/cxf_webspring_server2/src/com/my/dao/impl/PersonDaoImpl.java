package com.my.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.my.dao.PersonDao;
import com.my.service.model.Person;
@Repository
public class PersonDaoImpl implements PersonDao {
	public List<Person> pList=new ArrayList<Person>();
	@Override
	public void addPerson(String password, Person p) {
		pList.add(p);
	}
	@Override
	public List<Person> listPerson() {
		
		return pList;
	}

}
