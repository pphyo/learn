package com.pos.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pos.dao.CategoryDao;
import com.pos.dao.SaleOrderDao;
import com.pos.entity.Category;
import com.pos.entity.Invoice;
import com.pos.entity.Item;
import com.pos.entity.Member;
import com.pos.entity.SaleOrder;
import com.pos.util.ApplicationException;
import com.pos.util.ConnectionManager;

public class SaleOrderDaoImpl implements SaleOrderDao {
	
	private CategoryDao catDao;
	
	public SaleOrderDaoImpl() {
		catDao = CategoryDao.getInstance();
	}

	@Override
	public void create(SaleOrder order) {
		String sql = "insert into sale_order(unit_price, quantity, total_price, item_id, invoice_id) values(?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setInt(1, order.getUnitPrice());
			stmt.setInt(2, order.getQuantity());
			stmt.setInt(3, order.getTotal());
			stmt.setInt(4, order.getItem().getId());
			stmt.setInt(5, order.getInvoice().getId());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<SaleOrder> search(String username, LocalDate dateFrom, LocalDate dateTo) {
		String sql = "select inv.invoice_date, m.username, i.name, od.unit_price, od.quantity, od.total_price from sale_order od "
				+ "join invoice inv on inv.id = od.invoice_id "
				+ "join members m on m.id = inv.members_id and m.del_flag = 0 "
				+ "join item i on i.id = od.item_id and i.del_flag = 0 where 1 = 1 ";
		
		StringBuilder sb = new StringBuilder(sql);
		List<SaleOrder> orders = new ArrayList<>();
		List<Object> params = new ArrayList<>();
		
		if(null != username && !username.isEmpty()) {
			sb.append("and m.username regexp ? ");
			params.add(username);
		}
		
		if(null != dateFrom && null != dateTo && dateFrom.isAfter(dateTo)) {
			throw new ApplicationException("End date must be greater than start date");
		}
		
		if(null != dateFrom) {
			sb.append("and date(inv.invoice_date) >= ? ");
			params.add(dateFrom);
		}
		
		if(null != dateTo) {
			sb.append("and date(inv.invoice_date) <= ? ");
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
				invoice.setInvoiceDate(rs.getTimestamp("invoice_date").toLocalDateTime());
				
				Member member = new Member();
				member.setUsername(rs.getString("username"));
				invoice.setMember(member);
				
				Item item = new Item();
				item.setName(rs.getString("name"));
				
				SaleOrder order = new SaleOrder();
				order.setItem(item);
				order.setInvoice(invoice);
				order.setUnitPrice(rs.getInt("unit_price"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotal(rs.getInt("total_price"));
				
				orders.add(order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public Map<String, Map<String, Integer>> find(LocalDate dateFrom, LocalDate dateTo) {
		Map<String, Map<String, Integer>> orders = new LinkedHashMap<>();
		List<Category> categories = catDao.search(null);
		
		for (Category category : categories) {
			orders.put(category.getName(), findData(category, dateFrom, dateTo));
		}
		
		return orders;
	}

	private Map<String, Integer> findData(Category category, LocalDate dateFrom, LocalDate dateTo) {
		String sql = "select sum(od.quantity) from sale_order od "
				+ "join item i on i.id = od.item_id and i.del_flag = 0 "
				+ "join category c on c.id = i.category_id "
				+ "join invoice inv on inv.id = od.invoice_id where c.id = ? and date(inv.invoice_date) between ? and ? "
				+ "group by c.id";
		
		Map<String, Integer> map = new LinkedHashMap<>();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
			try(Connection conn = ConnectionManager.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql)){
				
				while(dateFrom.compareTo(dateTo) <= 0) {

					stmt.setInt(1, category.getId());
					stmt.setDate(2, Date.valueOf(dateFrom));
					stmt.setDate(3, Date.valueOf(dateFrom.plusDays(1)));
					
					ResultSet rs = stmt.executeQuery();
					int value = 0;
					
					while(rs.next()) {
						Long count = rs.getLong(1);
						value = count.intValue();
					}
				
					map.put(dateFrom.format(df),value);
					dateFrom = dateFrom.plusDays(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return map;
	}

}
