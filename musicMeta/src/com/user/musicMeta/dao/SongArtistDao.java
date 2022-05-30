package com.user.musicMeta.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.musicMeta.bean.Artist;
import com.user.musicMeta.bean.SongArtist;
import com.user.musicMeta.bean.User;
public class SongArtistDao {

	private static final String INSERT_SONGARTIST_SQL="INSERT INTO artst_sng"+"(artst_id,sng_id) VALUES"+"(?,?);";
	private static final String LIST_ARTISTS = "select artst_id,artst_name from artists ";
    private Connection connection=null;
	
	
	/*
	 * private static final String SELECT_ALL_USERS="select * from users"; private
	 * static final String DELETE_USERS_SQL="delete from users where id=?"; private
	 * static final String
	 * UPDATE_USERS_SQL="update users set name =?,email=?,country=?where id=?";
	 */
	
	public SongArtistDao() {
		
		 connection=Conn.getConnection();
	}
	
	public List<Artist> listArtist() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Artist> artists = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(LIST_ARTISTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int artst_id = rs.getInt("artst_id");
				String artst_name = rs.getString("artst_name");
				
				artists.add(new Artist(0, artst_name,null,null,artst_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return artists;
	}
	
	
	public void addSongArtist(SongArtist songArtist) throws SQLException {
		System.out.println(INSERT_SONGARTIST_SQL);
		// try-with-resource statement will auto close the connection.
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SONGARTIST_SQL)) {
			preparedStatement.setInt(1, songArtist.getArtst_id());
			preparedStatement.setInt(2, songArtist.getSng_id());
		
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
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
