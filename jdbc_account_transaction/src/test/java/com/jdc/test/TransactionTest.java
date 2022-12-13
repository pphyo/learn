package com.jdc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdc.app.service.TransferService;
import com.jdc.app.util.ConnectionManager;

public class TransactionTest {
	
	static TransferService service;
	
	@BeforeAll
	static void init() {
		service = new TransferService();
		
		try(var conn = ConnectionManager.getConnection()) {
			
			conn.prepareStatement("set foreign_key_checks = 0").execute();
			
			conn.prepareStatement("truncate table account").execute();
			conn.prepareStatement("truncate table transfer_log").execute();
			conn.prepareStatement("insert into account (name, amount) values ('James', 20000)").execute();
			conn.prepareStatement("insert into account (name, amount) values ('George', 20000)").execute();
			
			conn.prepareStatement("set foreign_key_checks = 1").execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void test_for_transfer_amount() {
		
		var result = service.transfer(1, 2, 5000);
		
		assertEquals("James", result.from());
		assertEquals("George", result.to());
		assertEquals(5000, result.amount());
		assertEquals(15000, result.fromAmount());
		assertEquals(25000, result.toAmount());
		
	}
	
}
