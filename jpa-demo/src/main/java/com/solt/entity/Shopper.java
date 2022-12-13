package com.solt.entity;

import javax.persistence.Entity;

@Entity
public class Shopper extends Account {

	private static final long serialVersionUID = 1L;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}