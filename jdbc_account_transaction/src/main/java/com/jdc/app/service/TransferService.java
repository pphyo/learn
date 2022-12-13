package com.jdc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdc.app.domain.TransferLog;
import com.jdc.app.util.ConnectionManager;

public class TransferService {
	
	public TransferLog transfer(int from, int to, int amount) {
		
		try(var conn = ConnectionManager.getConnection()) {
			
			try {
				conn.setAutoCommit(false);
				// get amount from (from)
				int fromAmount = get(conn, from);
				
				// check amount
				if(fromAmount < amount)
					throw new IllegalArgumentException("Not enough amount to transfer.");
				
				// get amount from (to)
				int toAmount = get(conn, to);
				
				// update amount (from)
				int updateFromAmount = update(conn, from, fromAmount - amount);
				
				// update amount (to)
				int updateToAmount = update(conn, to, toAmount + amount);
				
				// insert into transfer log
				var logId = createTransferLog(conn, from, to, amount, updateFromAmount, updateToAmount);
				
				// select transfer log data
				var log = getTransferLog(conn, logId);
				
				conn.commit();
				
				return log;
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private TransferLog getTransferLog(Connection conn, int id) throws SQLException {
		
		String sql = """
					select f.name, t.name, l.amount, l.transfer_date, l.from_amount, l.to_amount
					from transfer_log l
					join account f on f.id = l.from_account
					join account t on t.id = l.to_account
					where l.id = ?
				""";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			return new TransferLog(
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getTimestamp(4).toLocalDateTime(),
						rs.getInt(5),
						rs.getInt(6)
					);
		}
		
		return null;
	}
	
	private int createTransferLog(Connection conn, int from, int to, int amount, int fromAmount, int toAmount) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("""
				insert into transfer_log (from_account, to_account, amount, from_amount, to_amount)
				values (?, ?, ?, ?, ?)
				""", Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, from);
		stmt.setInt(2, to);
		stmt.setInt(3, amount);
		stmt.setInt(4, fromAmount);
		stmt.setInt(5, toAmount);
		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		while(rs.next())
			return rs.getInt(1);
		
		return 0;
	}

	private int update(Connection conn, int id, int amount) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("update account set amount = ? where id = ?");
		stmt.setInt(1, amount);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		
		return amount;
	}

	private int get(Connection conn, int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select amount from account where id = ?");
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			return rs.getInt(1);
		
		return 0;
	}

}
