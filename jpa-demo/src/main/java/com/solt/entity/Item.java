package com.solt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Transient;
import javax.persistence.ManyToMany;

@Entity
@SecondaryTables({ @SecondaryTable(name = "item_price"), @SecondaryTable(name = "item_tag") })
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Status {
		Avialable, OutOfStock
	}

	public enum Currency {
		USD, JPY, MMK
	}

	@Id
	private int id;

	@Embedded
	private Security security;

	@Lob
	private byte[] image;

	private String name;

	@Transient
	private boolean check;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	@Enumerated
	@Column(table = "item_price")
	private Currency currency;

	@Column(table = "item_price")
	private int price;

	@Column(table = "item_tag")
	private String tag;
	
	@ManyToMany(mappedBy = "items")
	private List<Order> orders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}