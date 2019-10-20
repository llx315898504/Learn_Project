package com.my.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装查询条件
 * 
 * @author Administrator
 * 
 */
public class QueryCondition implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Integer gender;

	private Date birthday;
	private String name;
	private String address;

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
