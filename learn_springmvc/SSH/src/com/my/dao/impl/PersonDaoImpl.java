package com.my.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.my.dao.PersonDao;
import com.my.model.Person;
@Repository
public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

	@Autowired
	public void setMysessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public void savePerson(Person person) {
		this.getSession().save(person);
		
	}

}
