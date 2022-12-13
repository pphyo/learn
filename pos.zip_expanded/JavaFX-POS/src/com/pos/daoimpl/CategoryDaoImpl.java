package com.pos.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.pos.dao.CategoryDao;
import com.pos.entity.Category;
import com.pos.util.ConnectionManager;
import com.pos.util.Security;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void save(Category category) {
		String insertSql = "insert into category(name, creation_date, creator) values(?, ?, ?)";
		String updateSql = "update category set name = ?, creation_date = ?, creator = ? where id = ?";
		boolean isNew = category.getId() == 0;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(isNew ? insertSql : updateSql,
						Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, category.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(3, Security.getLoginMember().getLoginID());

			if (!isNew) {
				stmt.setInt(4, category.getId());
			}

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				category.setId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getCount() {
		String sql = "select count(*) from category";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Category> search(String name) {
		String sql = "select * from category where 1 = 1 ";
		Predicate<String> pred = str -> null != str && !str.isEmpty();
		List<Category> categories = new LinkedList<>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn
						.prepareStatement(pred.test(name) ? sql.concat("and name regexp ?") : sql)) {

			if (pred.test(name)) {
				stmt.setString(1, name);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
				category.setCreator(rs.getString("creator"));

				categories.add(category);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categories;
	}

}
