package com.pos.entity;

import java.time.format.DateTimeFormatter;

import static com.pos.util.CommonUtil.DECIMAL_FORMAT;

public class SaleOrder {
	private int id;
	private Invoice invoice;
	private Item item;
	private int unitPrice;
	private int quantity;
	private int total;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSaleDateStr() {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a").format(invoice.getInvoiceDate());
	}
	
	public String getSalePerson() {
		return invoice.getMember().getUsername();
	}
	
	public String getUnitPriceStr() {
		return DECIMAL_FORMAT.format(unitPrice);
	}
	
	public String getQuantityStr() {
		return DECIMAL_FORMAT.format(quantity);
	}
	
	public String getTotalStr() {
		return DECIMAL_FORMAT.format(total);
	}
}
