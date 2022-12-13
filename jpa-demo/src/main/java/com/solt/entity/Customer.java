package com.solt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;

@Entity
@SecondaryTables({ @SecondaryTable(name = "contact"),
				   @SecondaryTable(name = "address") })
public class Customer extends Account {

	private static final long serialVersionUID = 1L;
	
	@Column(table = "contact")
	private String email;
	@Column(table = "contact")
	private String phone;

	@Column(table = "address")
	private String adress;
	@Column(table = "address")
	private String division;
	@Column(table = "address")
	private String township;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}