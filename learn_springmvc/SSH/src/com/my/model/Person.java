package com.my.model;

// default package

import java.io.Serializable;
import java.util.Date;

/**
 * TPerson entity. @author MyEclipse Persistence Tools
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer gender;
	private Integer age;
	private String address;
	private Date birthday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + ", address=" + address + ", birthday="
				+ birthday + "]";
	}

}
