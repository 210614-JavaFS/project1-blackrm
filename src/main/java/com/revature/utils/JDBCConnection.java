package com.revature.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCConnection {
	
	private static Logger logger = LoggerFactory.getLogger(JDBCConnection.class);;

	static Connection conn;
	public static Connection makeConnection() throws SQLException{
		// TODO Auto-generated method stub
		//configure

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String url = "jdbc:postgresql://training.cfqhajquodzh.us-east-2.rds.amazonaws.com/postgres";
//		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "postgres";
		
		//connect
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("Connection made");
//			
//		}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DriverManager.getConnection(url, user, password);
		
		
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
