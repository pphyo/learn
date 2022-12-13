package com.pos.dao;

import java.util.List;

import com.pos.daoimpl.ItemDaoImpl;
import com.pos.entity.Category;
import com.pos.entity.Item;

public interface ItemDao {
	
	static ItemDao getInstance() {
		return new ItemDaoImpl();
	}
	
	void save(Item item);
	long getCount();
	List<Item> findAll();
	List<Item> search(Category category, String name, int priceFom, int priceTo);
}
