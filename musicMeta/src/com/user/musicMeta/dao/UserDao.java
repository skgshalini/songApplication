package com.user.musicMeta.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.musicMeta.bean.User;

public class UserDao  {

	
	
	private static final String INSERT_USERS_SQL="INSERT INTO users"+"(usr_name,usr_email,usr_password) VALUES"+"(?,?,?);";
	private static final String AUTHENTICATE_USER = "select usr_id,usr_name from users where usr_email=? and usr_password=?";
    private Connection connection=null;
	
	
	/*
	 * private static final String SELECT_ALL_USERS="select * from users"; private
	 * static final String DELETE_USERS_SQL="delete from users where id=?"; private
	 * static final String
	 * UPDATE_USERS_SQL="update users set name =?,email=?,country=?where id=?";
	 */
	
	public UserDao() {
		
		 connection=Conn.getConnection();
	}
	
	
	
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUsr_name());
			preparedStatement.setString(2, user.getUsr_email());
			preparedStatement.setString(3, user.getUsr_password());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User authenticateUser(String usr_email, String usr_password) {
		User user = null;
		// Step 1: Establishing a Connection
		try (
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_USER);) {
			preparedStatement.setString(1, usr_email);
			preparedStatement.setString(2, usr_password);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			if (rs.next()) {
				int usr_id = Integer.parseInt(rs.getString("usr_id"));
				String usr_name = rs.getString("usr_name");
				user = new User(usr_id, usr_name, usr_email, usr_password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}



	private void printSQLException(SQLException ex) {
		for(Throwable e: ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+ ((SQLException)e).getSQLState());
				System.err.println("Error Code: "+((SQLException)e).getErrorCode());
				System.err.println("Message: "+e.getMessage());
				Throwable t=ex.getCause();
				while(t!=null) {
					System.out.println("Cause: "+t);
					t=t.getCause();
				}
						
				
				
				
			}
		}
	}
	
}
