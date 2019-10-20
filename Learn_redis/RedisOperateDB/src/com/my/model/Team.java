package com.my.model;

import java.util.Set;

public class Team {
	
	private Integer teamId;
	
	private String name;
	
	private String address;
	
	/**
	 * 指定emp类和dept类的关系
	 */
	private Set<Employee> empSet;
	
	

	public Set<Employee> getEmpSet() {
		return empSet;
	}

	public void setEmpSet(Set<Employee> empSet) {
		this.empSet = empSet;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", name=" + name + ", address="
				+ address + ", empSet=" + empSet + "]";
	}
}