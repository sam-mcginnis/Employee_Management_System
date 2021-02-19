package com.cognixia.advancedjava.ems.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static Connection conn = null;
	
	private static void makeConnection() {
		
//		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//	} catch (ClassNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("./resources/config.properties"));
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			conn = DriverManager.getConnection(url, username, password);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (conn == null) {
			makeConnection();
		}
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

}
