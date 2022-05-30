package com.user.musicMeta.bean;

public class SongArtist {
private int sng_id;
private int artst_id;
public int getSng_id() {
	return sng_id;
}
public void setSng_id(int sng_id) {
	this.sng_id = sng_id;
}
public int getArtst_id() {
	return artst_id;
}
public void setArtst_id(int artst_id) {
	this.artst_id = artst_id;
}
public SongArtist(int sng_id, int artst_id) {
	
	this.sng_id = sng_id;
	this.artst_id = artst_id;
}



}
