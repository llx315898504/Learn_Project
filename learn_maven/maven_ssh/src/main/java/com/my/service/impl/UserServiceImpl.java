package com.my.service.impl;

import com.my.dao.UserDao;
import com.my.model.User;
import com.my.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}
