package com.my.model;

import java.io.Serializable;

public class IdCard  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String cardCode;
    
    private Person person;

    public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
}