package com.user.musicMeta.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Conn {
	private static Conn single_instance=null;
	
	private String jdbcURL= "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String jdbcUsername="root";
	private String jdbcPassword="root";
	private String jdbcDriver="com.mysql.jdbc.Driver";
	private static Connection connection = null;

	private Conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		if(single_instance==null) {
			single_instance=new Conn();
		}
		
		return connection;
		
	}
	
}
