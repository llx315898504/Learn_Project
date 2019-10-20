package com.my.model;


public class Employee {
	
	private Integer empId;
	
	private String name;
	
	private String gender;
	
	private Integer age;
	
	private Team team;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", team=" + team + "]";
	}
}