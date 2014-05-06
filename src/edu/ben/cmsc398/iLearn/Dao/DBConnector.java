package edu.ben.cmsc398.iLearn.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnector {
	private Connection conn;
	private String database;
	private String username;
	private String password;

	public DBConnector() {
		this.database = "iLearn";
		this.username = "root";
		this.password = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ database + "?user=" + username + "&password=" + password);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement SetprepareStatement(String sql)
			throws SQLException {
		return conn.prepareStatement(sql);
	}
}