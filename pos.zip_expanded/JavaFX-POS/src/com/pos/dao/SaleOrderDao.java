package com.pos.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.pos.daoimpl.SaleOrderDaoImpl;
import com.pos.entity.SaleOrder;

public interface SaleOrderDao {
	
	static SaleOrderDao getInstance() {
		return new SaleOrderDaoImpl();
	}
	
	void create(SaleOrder order);
	List<SaleOrder> search(String username, LocalDate dateFrom, LocalDate dateTo);
	Map<String, Map<String, Integer>> find(LocalDate dateFrom, LocalDate dateTo);
}
