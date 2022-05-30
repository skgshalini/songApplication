package com.user.musicMeta.bean;

public class SongRating {
private int sng_id;
private int usr_id;
private int rating;
public int getSng_id() {
	return sng_id;
}
public void setSng_id(int sng_id) {
	this.sng_id = sng_id;
}
public int getUsr_id() {
	return usr_id;
}
public void setUsr_id(int usr_id) {
	this.usr_id = usr_id;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
public SongRating(int sng_id, int usr_id, int rating) {
	
	this.sng_id = sng_id;
	this.usr_id = usr_id;
	this.rating = rating;
}




}
