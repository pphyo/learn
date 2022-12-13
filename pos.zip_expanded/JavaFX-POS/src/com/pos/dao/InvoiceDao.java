package com.pos.dao;

import java.time.LocalDate;
import java.util.List;

import com.pos.daoimpl.InvoiceDaoImpl;
import com.pos.entity.Invoice;
import com.pos.entity.SaleOrder;

public interface InvoiceDao {
	
	static InvoiceDao getInstance() {
		return new InvoiceDaoImpl();
	}
	
	void create(Invoice invoice, List<SaleOrder> saleOrders);
	List<Invoice> search(String name, LocalDate dateFrom, LocalDate dateTo);
}
