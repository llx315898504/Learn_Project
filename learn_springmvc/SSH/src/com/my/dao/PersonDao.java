package com.my.dao;

import com.my.model.Person;

public interface PersonDao {
	
	/**
	 * 新增用户
	 * 
	 * @param person
	 */
	public void savePerson(Person person);
}
