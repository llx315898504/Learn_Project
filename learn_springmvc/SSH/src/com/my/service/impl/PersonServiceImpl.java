package com.my.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PersonDao;
import com.my.model.Person;
import com.my.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;

	@Override
	public void savePerson(Person person) {
		personDao.savePerson(person);

	}

}
