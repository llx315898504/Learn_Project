package com.my.model;

import java.io.Serializable;
import java.util.Date;

public class PersonTest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer personId;

	private String realName;

	private Integer gender;

	private Integer age;

	private String address;

	private Date birthday;

	private Integer status;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PersonTest [personId=" + personId + ", realName=" + realName
				+ ", gender=" + gender + ", age=" + age + ", address="
				+ address + ", birthday=" + birthday + ", status=" + status
				+ "]";
	}

}