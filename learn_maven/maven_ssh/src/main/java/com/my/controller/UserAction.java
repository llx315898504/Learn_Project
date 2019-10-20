package com.my.controller;

import com.my.model.User;
import com.my.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private UserService UserService;
	private User user;
	

	public UserService getUserService() {
		return UserService;
	}


	public void setUserService(UserService userService) {
		UserService = userService;
	}




	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String execute() throws Exception {
		UserService.saveUser(user);
		return "success";
	}

}
