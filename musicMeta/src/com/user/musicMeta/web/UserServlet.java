package com.user.musicMeta.web;


import java.io.*;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.user.musicMeta.bean.Artist;
import com.user.musicMeta.bean.Song;
import com.user.musicMeta.bean.SongRating;
import com.user.musicMeta.bean.SongArtist;
import com.user.musicMeta.bean.User;
import com.user.musicMeta.dao.ArtistDao;
import com.user.musicMeta.dao.SongArtistDao;
import com.user.musicMeta.dao.SongRatingDao;
import com.user.musicMeta.dao.SongDao;
import com.user.musicMeta.dao.UserDao;







@WebServlet("/")
@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao UserDao;
	private ArtistDao artistDao ;
	private SongDao songDao ;
	private SongArtistDao songArtistDao;
	private SongRatingDao songRatingDao;
	public void init() {
		UserDao = new UserDao();
		artistDao=new ArtistDao();
		songDao=new SongDao();
		songArtistDao=new SongArtistDao();
		songRatingDao=new SongRatingDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			
			case "/updateRating":
				updateRating(request, response);
				break;
			case "/topSongs":
				topSongs(request, response);
				break;
			case "/insertUser":
				insertUser(request, response);
				break;
			case "/register":
				register(request, response);
				break;
			
			case "/saveSong":
				saveSong(request, response);
				break;
			
			case "/listArtist":
				listArtist(request, response);
				break;
			case "/addArtist":
				addArtist(request, response);
				break;
			
			case "/authenticate":
				authenticateUser(request, response);
				break;
			case "/addsong":
				addSong(request, response);
				break;
         	default:
				login(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void updateRating(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int usr_id = Integer.parseInt(request.getParameter("usr_id"));
		int sng_id = Integer.parseInt(request.getParameter("sng_id"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		SongRating songRating = new SongRating( sng_id,usr_id, rating);
		songRatingDao.updateRating(songRating);
		/*
		 * RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp"); User
		 * user =new User(usr_id,null,null,null); request.setAttribute("user", user);
		 * List<Song> topSongs = songDao.topSongs();
		 * 
		 * request.setAttribute("topSongs", topSongs); dispatcher.forward(request,
		 * response);
		 */
		
	}
	private void topSongs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		  List<Song> topSongs = songDao.topSongs();
		  
			request.setAttribute("topSongs", topSongs);
			String id = request.getParameter("usr_id");
			request.setAttribute("usr_id", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		  
			/*
			 * JsonObject jsonResponse = new JsonObject(); JsonArray topSong= new
			 * JsonArray(); for(Song song : topSongs) {
			 * 
			 * System.out.println("sng_name"+song.getSng_name()); JsonObject objectRow = new
			 * JsonObject();
			 * 
			 * objectRow.addProperty("sng_name", song.getSng_name());
			 * objectRow.addProperty("sng_dor", song.getSng_dor());
			 * //objectRow.addProperty("sng_cover", song.getSng_cover());
			 * 
			 * topSong.add(objectRow); }
			 * 
			 * jsonResponse.add("topSongs", topSong); return jsonResponse;
			 */
	}
	private void listArtist(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Artist> listArtist = artistDao.listArtist();
		
		
		JsonObject jsonResponse = new JsonObject();
		JsonArray labListObjectRowsContainer= new JsonArray();
		request.setAttribute("listArtist", listArtist);
		for(Artist artist : listArtist) {
			System.out.println("jhgjh"+artist.getArtst_id());

			JsonObject objectRow = new JsonObject();
			
	        objectRow.addProperty("artst_id",  String.valueOf(artist.getArtst_id()));
	        objectRow.addProperty("artst_name",artist.getArtst_name() );

			labListObjectRowsContainer.add(objectRow);
		}

		jsonResponse.add("artistList", labListObjectRowsContainer);

		 response.setContentType("application/Json");
		 response.getWriter().print(jsonResponse);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
		dispatcher.forward(request, response);
	}
	private void addSong(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Artist> listArtist = artistDao.listArtist();
		request.setAttribute("listArtist", listArtist);
		String id = request.getParameter("id");
		request.setAttribute("usr_id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("song.jsp");
		dispatcher.forward(request, response);
	}
	
	private void authenticateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		 String usr_email = request.getParameter("usr_email");
		 String usr_password= request.getParameter("usr_password");
		User existingUser = UserDao.authenticateUser(usr_email,usr_password);
		if(existingUser!=null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		request.setAttribute("user", existingUser);
		List<Song> topSongs = songDao.topSongs();
		  
		request.setAttribute("topSongs", topSongs);
		dispatcher.forward(request, response);}
		else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("failed", "yes");
			dispatcher.forward(request, response);
		}

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String usr_name = request.getParameter("usr_name");
		String usr_email = request.getParameter("usr_email");
		String usr_password = request.getParameter("usr_password");
		User newUser = new User(usr_name, usr_email, usr_password);
		UserDao.insertUser(newUser);
		response.sendRedirect("login");
	}
	private void addArtist(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		System.out.println("I am here");
		String artst_name = request.getParameter("artst_name");
		String artst_dob = request.getParameter("artst_dob");
		String artst_bio = request.getParameter("artst_bio");
		int usr_id=Integer.parseInt(request.getParameter("usr_id"));
		Artist artist = new Artist(usr_id,artst_name, artst_dob, artst_bio);
		  artistDao.addArtist(artist);
		  
	
		
	}
	private void saveSong(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		System.out.println("I am here");
		String sng_name = request.getParameter("sng_name");
		String sng_dor = request.getParameter("sng_dor");
		//String sng_cover = request.getParameter("sng_cover");
		
		Part filePart=null;
		try {
			filePart = request.getPart("sng_cover");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	     
	    InputStream fileContent = filePart.getInputStream(); 

      
     //  OutputStream out = new FileOutputStream(file);
    // Write your data
    //out.close();
     //  sng_cover="musicMeta\\images"+sng_cover;
     //  System.out.println("Song Cover"+sng_cover);
		int usr_id=Integer.parseInt(request.getParameter("usr_id"));
		String artst_id=request.getParameter("artst_id");
		
		
		System.out.println("artst_id"+artst_id);
		Song song = new Song(sng_name, sng_dor, fileContent,usr_id);
		
		  songDao.saveSong(song);
		  int  sng_id= songDao.songId();
		   
		    SongRating songRating = new SongRating( sng_id,usr_id,1);
			songRatingDao.saveRating(songRating);
		  String ar_id[]=artst_id.split(",");
		  for(int i=0;i<ar_id.length;i++) {
			  SongArtist songArtist=new SongArtist(sng_id,Integer.parseInt(ar_id[i]));
			  songArtistDao.addSongArtist(songArtist);
		  }
		  List<Artist> listArtist = artistDao.listArtist();
			request.setAttribute("listArtist", listArtist);
		  String id = request.getParameter("usr_id");
			request.setAttribute("usr_id", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("song.jsp");
			dispatcher.forward(request, response);
		  
		
	}	
	
	
}