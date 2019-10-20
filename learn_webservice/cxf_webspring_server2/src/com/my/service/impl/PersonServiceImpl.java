package com.my.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PersonDao;
import com.my.service.model.Person;
import com.my.ws.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	@Override
	public String addPerson(String password, Person p) {
		if ("123".equals(password)) {
			personDao.addPerson(password, p);
			return "SUCCESS";
		}
		return "Fail";
	}

	@Override
	public List<Person> listPerson() {
		return personDao.listPerson();
	}
}
