package com.pos.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.pos.dao.CategoryDao;
import com.pos.dao.ItemDao;
import com.pos.entity.Category;
import com.pos.entity.Item;
import com.pos.util.ApplicationException;
import com.pos.util.ConnectionManager;
import com.pos.util.Security;

public class ItemDaoImpl implements ItemDao {
	
	private CategoryDao categoryDao;
	
	public ItemDaoImpl() {
		categoryDao = CategoryDao.getInstance();
	}

	@Override
	public void save(Item item) {
		String insertSql = "insert into item(name, category_id, price, creation_date, creator, del_flag) values(?, ?, ?, ?, ?, ?)";
		String updateSql = "update item set name = ?, category_id = ?, price = ?, creation_date = ?, creator = ?, del_flag = ? where id = ?";
		
		check(item);
		
		boolean isUpdate = item.getId() > 0;
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(isUpdate ? updateSql : insertSql)){
			
			stmt.setString(1, item.getName());
			
			List<Category> cateList = categoryDao.search(item.getCategory().getName());
			Category category = null;
			
			if(cateList.isEmpty()) {
				categoryDao.save(item.getCategory());
				category = item.getCategory();
			}else {
				category = cateList.get(0);
			}
			
			stmt.setInt(2, category.getId());
			stmt.setInt(3, item.getPrice());
			stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(5, Security.getLoginMember().getLoginID());
			stmt.setBoolean(6, item.isDelete());
			
			if(isUpdate)
				stmt.setInt(7, item.getId());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getCount() {
		String sql = "select count(*) from item where del_flag = 0";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
				return rs.getLong(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<Item> findAll() {
		return search(null, null, 0, 0);
	}

	@Override
	public List<Item> search(Category category, String name, int priceFrom, int priceTo) {
		String sql = "select c.id cId, c.name cName, i.id iId, i.name iName, i.price, i.creation_date iCDate, i.creator iC "
				+ "from item i "
				+ "join category c on c.id = i.category_id where i.del_flag = 0 ";
		
				StringBuilder sb = new StringBuilder(sql);
		List<Object> params = new ArrayList<>();
		List<Item> items = new LinkedList<>();
		
		if(null != category) {
			sb.append("and i.category_id = ? ");
			params.add(category.getId());
		}
		
		if(null != name && !name.isEmpty()) {
			sb.append("and i.name regexp ? ");
			params.add(name);
		}
		
		if(priceFrom > 0) {
			sb.append("and i.price >= ?");
			params.add(priceFrom);
		}
		
		if((priceTo > 0) && (priceTo > priceFrom)) {
			sb.append("and i.price <= ?");
			params.add(priceTo);
		}
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())){
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Category cat = new Category();
				cat.setId(rs.getInt("cId"));
				cat.setName(rs.getString("cName"));
				
				Item item = new Item();
				item.setCategory(cat);
				item.setId(rs.getInt("iId"));
				item.setName(rs.getString("iName"));
				item.setPrice(rs.getInt("price"));
				item.setCreationDate(rs.getTimestamp("iCdate").toLocalDateTime());
				item.setCreator(rs.getString("iC"));
				
				items.add(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}

	private void check(Item item) {
		if(null == item)
			throw new ApplicationException("Null value can't save");
		
		if(null == item.getCategory())
			throw new ApplicationException("Select one category");
		
		if(null == item.getName() || item.getName().trim().isEmpty())
			throw new ApplicationException("Name can't empty");
		
		if(item.getPrice() < 1)
			throw new ApplicationException("Price must be greater than 0");
		
	}
}
