package com.my.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3066/hb_student_tracker?useSSL=false";
		String user = "root";
		String pass = "root";
		
		try {
			System.out.println("Connecting to database : "+jdbcUrl);
			Connection myconn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
}
