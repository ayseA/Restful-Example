package com.ak.restfulExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBServices {

	private static final String CONN = "jdbc:mysql://localhost:3306/restfulExample?relaxAutoCommit=true";
	private static final String USERNAME = "root";
	private static final String PASSWD = "admin";

	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(CONN, USERNAME, PASSWD); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { 
			e.printStackTrace();  // TODO: log these
		}	
		return conn;
	}

	/**
	 * inserts the new item into items table. 
	 * @throws IllegalArgumentException if item is null or already exists
	 * */
	public static void insertNewItem(Item item) {
		if (item==null) 
			throw new IllegalArgumentException("Can't insert null item");

		String q = "insert into items ("
				+ "id, "
				+ "name, "
				+ "brand, "  // 3
				+ "createdAtTime, "  // 4
				+ "description"  // 5
				+ ") values (?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement pStat = conn.prepareStatement(q)) {
			pStat.setString(1, item.getId());
			pStat.setString(2, item.getName());
			pStat.setString(3, item.getBrand());
			pStat.setLong(4, item.getCreatedAtTime());
			pStat.setString(5, item.getDescription());
			pStat.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();  // log
		}

		if (item.getTags()==null || item.getTags().isEmpty())
			return;			

		q = "insert into tags (id, tag) values (?, ?)";
		try (Connection conn = getConnection(); PreparedStatement pStat = conn.prepareStatement(q)) {
			for (String tag:item.getTags()) {
				pStat.setString(1, item.getId());
				pStat.setString(2, tag);
				pStat.addBatch();
			}
			pStat.executeBatch();  // can't be more than 1000 tags/item(?)
		} catch (Exception e) {
			e.printStackTrace();  // log
			//TODO: delete item if error here?
		}

	}

}
