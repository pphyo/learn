package com.pos.entity;

import java.time.LocalDateTime;

import static com.pos.util.CommonUtil.*;

public class Invoice {
	private int id;
	private LocalDateTime invoiceDate;
	private Member member;
	private int subTotal;
	private int tax;
	private int total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getSalePerson() {
		return member.getUsername();
	}
	
	public String getInvoiceDateStr() {
		return DATE_TIME_FORMATTER.format(invoiceDate);
	}
	
	public String getSubTotalStr() {
		return DECIMAL_FORMAT.format(subTotal);
	}
	
	public String getTaxStr() {
		return DECIMAL_FORMAT.format(tax);
	}
	
	public String getTotalStr() {
		return DECIMAL_FORMAT.format(total);
	}
}
