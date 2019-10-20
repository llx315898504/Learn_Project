package com.my.service.model;

public class Person {
	private int id;
	private String name;
	private int gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "Person [gender=" + gender + ", id=" + id + ", name=" + name
				+ "]";
	}

	

}
