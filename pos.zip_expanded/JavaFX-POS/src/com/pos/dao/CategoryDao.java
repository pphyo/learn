package com.pos.dao;

import java.util.List;

import com.pos.daoimpl.CategoryDaoImpl;
import com.pos.entity.Category;

public interface CategoryDao {
	
	static CategoryDao getInstance() {
		return new CategoryDaoImpl();
	}

	void save(Category category);
	long getCount();
	List<Category> search(String name);
}
