package com.my.model;

import java.io.Serializable;

public class PersonRole implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer roleId;

    private Integer id;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}