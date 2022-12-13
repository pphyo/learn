package com.pos.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.pos.dao.MemberDao;
import com.pos.entity.Member;
import com.pos.entity.Role;
import com.pos.util.ApplicationException;
import com.pos.util.CommonUtil;
import com.pos.util.ConnectionManager;

public class MemberDaoImpl implements MemberDao {

	private static final String SELECT = "select * from members where 1 = 1 and del_flag = 0 ";

	@Override
	public Member login(String loginID, String password) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT.concat("and login_name = ? and password = ?"))) {

			stmt.setString(1, loginID);
			stmt.setString(2, CommonUtil.encodePassword(password));

			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				return getObjectFromResultSet(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void save(Member member) {
		String insertSql = "insert into members(login_name, username, password, role, phone, del_flag) values(?, ?, ?, ?, ?, ?)";
		String updateSql = "update members set login_name = ?, username = ?, password = ?, role = ?, phone = ?, del_flag = ? where id = ?";

		check(member);

		boolean isUpdate = member.getId() > 0;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(isUpdate ? updateSql : insertSql)) {

			stmt.setString(1, member.getLoginID());
			stmt.setString(2, member.getUsername());
			stmt.setString(3, CommonUtil.encodePassword(member.getPassword()));
			stmt.setString(4, member.getRole().name());
			stmt.setString(5, member.getPhone());
			stmt.setBoolean(6, member.isDelete());

			if (isUpdate)
				stmt.setInt(7, member.getId());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Member member) {
		String sql = "update members set del_flag = ? where id = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setBoolean(1, member.isDelete());
			stmt.setInt(2, member.getId());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> search(String username, Role role) {
		StringBuilder sb = new StringBuilder(SELECT);
		List<Member> members = new LinkedList<>();
		List<Object> params = new ArrayList<>();

		if (null != username && !username.isEmpty()) {
			sb.append("and username regexp ? ");
			params.add(username);
		}

		if (null != role) {
			sb.append("and role = ? ");
			params.add(role.name());
		}

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {

			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				members.add(getObjectFromResultSet(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return members;
	}

	private Member getObjectFromResultSet(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setLoginID(rs.getString("login_name"));
		member.setUsername(rs.getString("username"));
		member.setPassword(rs.getString("password"));
		member.setRole(Role.valueOf(rs.getString("role")));
		member.setPhone(rs.getString("phone"));
		member.setDelete(rs.getBoolean("del_flag"));
		return member;
	}

	@Override
	public long getCount() {
		String sql = "select count(*) from members where del_flag = 0";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				return rs.getLong(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void check(Member member) {
		if(null == member.getLoginID() || member.getLoginID().isEmpty())
			throw new ApplicationException("Please enter login name");
		
		if(null == member.getUsername() || member.getUsername().isEmpty())
			throw new ApplicationException("Please enter username");
		
		if(null == member.getPassword() || member.getPassword().isEmpty())
			throw new ApplicationException("Password can't be null or empty");
		
	}
}
