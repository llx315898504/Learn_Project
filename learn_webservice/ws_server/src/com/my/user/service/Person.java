package com.my.user.service;

public class Person {

	private  int id;
	private String name;
	private String adress;
	private int age;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", adress=" + adress
				+ ", age=" + age + ", gender=" + gender + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getAdress()="
				+ getAdress() + ", getAge()=" + getAge() + ", getGender()="
				+ getGender() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
