package com.pos.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static String url;
	private static String user;
	private static String password;
	
	static {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			url = props.getProperty("database.url");
			user = props.getProperty("database.username");
			password = props.getProperty("database.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ConnectionManager() {}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
}
