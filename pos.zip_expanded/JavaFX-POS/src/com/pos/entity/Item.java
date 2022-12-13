package com.pos.entity;

import java.time.LocalDateTime;

import com.pos.excel.Column;
import com.pos.util.CommonUtil;

public class Item {
	private int id;
	@Column("ITEM")
	private String name;
	@Column
	private Category category;
	@Column
	private int price;
	private LocalDateTime creationDate;
	private String creator;
	private boolean delete;
	
	public Item() {}
	
	public Item(String[] array) {
		this.name = array[0];
		this.category = new Category(array[1]);
		this.price = CommonUtil.convertStringToInt(array[2]);
	}

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return name;
	}
}
