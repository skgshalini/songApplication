package com.user.musicMeta.dao;



import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import com.user.musicMeta.bean.Artist;
import com.user.musicMeta.bean.Song;
import com.user.musicMeta.bean.User;

public class SongDao  {

	
	
	private static final String INSERT_SONG_SQL="INSERT INTO songs"+"(sng_name,sng_dor,sng_cover,usr_id) VALUES"+"(?,?,?,?);";
	private static final String AUTHENTICATE_USER = "select usr_id,usr_name from users where usr_email=? and usr_password=?";
	private static final String SELECT_SONG_ID ="select sng_id from songs order by sng_id desc limit 1 ;";
	private static final String TOP_SONGS ="select * from songs as s inner join (select sng_id,sum(rating) from usr_sng_rating group by sng_id order by sum(rating) desc limit 10) as r on s.sng_id=r.sng_id ";
	   
	
	private Connection connection=null;
	
	
	/*
	 * private static final String SELECT_ALL_USERS="select * from users"; private
	 * static final String DELETE_USERS_SQL="delete from users where id=?"; private
	 * static final String
	 * UPDATE_USERS_SQL="update users set name =?,email=?,country=?where id=?";
	 */
	
	public SongDao() {
		
		 connection=Conn.getConnection();
	}
	
	public int songId() throws SQLException {
		System.out.println(SELECT_SONG_ID);
		int sng_id=0;
		// try-with-resource statement will auto close the connection.
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SONG_ID)) {
			
			ResultSet rs=preparedStatement.executeQuery();
			if (rs.next()) {
				sng_id = Integer.parseInt(rs.getString("sng_id"));
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return sng_id;
	}
	
	public List<Song> topSongs() throws SQLException {
		System.out.println(TOP_SONGS);
		// try-with-resource statement will auto close the connection.
		List<Song> songs = new ArrayList<>();
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(TOP_SONGS)) {
			
			System.out.println(preparedStatement);
			ResultSet rs=preparedStatement.executeQuery();
			while (rs.next()) {
				int sng_id=rs.getInt("sng_id");
				String sng_name  = rs.getString("sng_name");
				String sng_dor = rs.getString("sng_dor");
				Blob blob = rs.getBlob("sng_cover");
				byte[] data = blob.getBytes(1, (int) blob.length());
				BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				ImageIO.write(img, "png", output);
				String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
				
				songs.add(new Song(sng_id,sng_name,sng_dor,imageAsBase64));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return songs;
	}
	public void saveSong(Song song) throws SQLException {
		System.out.println(INSERT_SONG_SQL);
		// try-with-resource statement will auto close the connection.
		try (
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SONG_SQL)) {
			preparedStatement.setString(1, song.getSng_name());
			preparedStatement.setString(2, song.getSng_dor());
			
			
			preparedStatement.setBlob(3, song.getSng_cover());
			preparedStatement.setInt(4, song.getUsr_id());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
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
