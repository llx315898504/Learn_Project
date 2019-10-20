package com.my.model;

import java.io.Serializable;
import java.util.List;

public class Role implements  Serializable{
	private static final long serialVersionUID = 4150462538348024145L;

	private Integer roleId;

    private String roleName;

    private String roleDesc;
    private List<Person> personList;
    
    public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}