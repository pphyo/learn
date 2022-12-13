package com.pos.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pos.dao.InvoiceDao;
import com.pos.dao.SaleOrderDao;
import com.pos.entity.Invoice;
import com.pos.entity.Member;
import com.pos.entity.SaleOrder;
import com.pos.util.ApplicationException;
import com.pos.util.ConnectionManager;

public class InvoiceDaoImpl implements InvoiceDao {
	
	private SaleOrderDao saleDao;
	
	public InvoiceDaoImpl() {
		saleDao = SaleOrderDao.getInstance();
	}

	@Override
	public void create(Invoice invoice, List<SaleOrder> saleOrders) {
		String sql = "insert into invoice(invoice_date, subtotal, tax, total, members_id) values(?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setTimestamp(1, Timestamp.valueOf(invoice.getInvoiceDate()));
			stmt.setInt(2, invoice.getSubTotal());
			stmt.setInt(3, invoice.getTax());
			stmt.setInt(4, invoice.getTotal());
			stmt.setInt(5, invoice.getMember().getId());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next())
				invoice.setId(rs.getInt(1));
			
			saleOrders.forEach(order -> {
				order.setInvoice(invoice);
				saleDao.create(order);
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Invoice> search(String name, LocalDate dateFrom, LocalDate dateTo) {
		String sql = "select inv.id, inv.invoice_date, inv.subtotal, inv.tax, inv.total, m.username from invoice inv "
				+ "join members m on inv.members_id = m.id and m.del_flag = 0 where 1 = 1 ";
		
		StringBuilder sb = new StringBuilder(sql);
		List<Object> params = new ArrayList<>();
		List<Invoice> invoices = new ArrayList<>();
		
		if(null != name && !name.isEmpty()) {
			sb.append("and m.username regexp ? ");
			params.add(name);
		}
		
		if(null != dateFrom && null != dateTo && dateFrom.isAfter(dateTo)) {
			throw new ApplicationException("End date must be greater than start date");
		}
		
		if(null != dateFrom) {
			sb.append("and date(inv.invoice_date) >= ? ");
			params.add(dateFrom);
		}
		
		if(null != dateTo) {
			sb.append("and date(inv.invoice_date) >= ? ");
			params.add(dateTo);
		}
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())){
			
			
			for (int i = 0; i < params.size(); i++) {
				Object obj = params.get(i);
				
				if(obj instanceof LocalDate) {
					LocalDate date = (LocalDate) obj;
					stmt.setDate(i + 1, Date.valueOf(date));
				}else {
					stmt.setObject(i + 1, obj);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt("id"));
				Member member = new Member();
				member.setUsername(rs.getString("username"));
				invoice.setMember(member);
				
				invoice.setInvoiceDate(rs.getTimestamp("invoice_date").toLocalDateTime());
				invoice.setSubTotal(rs.getInt("subtotal"));
				invoice.setTax(rs.getInt("tax"));
				invoice.setTotal(rs.getInt("total"));
				
				invoices.add(invoice);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invoices;
	}

}
