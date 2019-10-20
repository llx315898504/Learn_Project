package com.my.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.my.dao.UserDao;
import com.my.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);

	}

}
