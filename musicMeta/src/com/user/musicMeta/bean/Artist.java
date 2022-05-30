package com.user.musicMeta.bean;

public class Artist {
	private int usr_id;
	private String artst_name;
	private String artst_dob;
	private String artst_bio;
	private int artst_id;
	public Artist(int usr_id, String artst_name, String artst_dob, String artst_bio) {
		
		this.usr_id = usr_id;
		this.artst_name = artst_name;
		this.artst_dob = artst_dob;
		this.artst_bio = artst_bio;
	}
	public Artist(int usr_id, String artst_name, String artst_dob, String artst_bio, int artst_id) {
	
		this.usr_id = usr_id;
		this.artst_name = artst_name;
		this.artst_dob = artst_dob;
		this.artst_bio = artst_bio;
		this.artst_id = artst_id;
	}
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public String getArtst_name() {
		return artst_name;
	}
	public void setArtst_name(String artst_name) {
		this.artst_name = artst_name;
	}
	public String getArtst_dob() {
		return artst_dob;
	}
	public void setArtst_dob(String artst_dob) {
		this.artst_dob = artst_dob;
	}
	public String getArtst_bio() {
		return artst_bio;
	}
	public void setArtst_bio(String artst_bio) {
		this.artst_bio = artst_bio;
	}
	public int getArtst_id() {
		return artst_id;
	}
	public void setArtst_id(int artst_id) {
		this.artst_id = artst_id;
	}
	
	
}
