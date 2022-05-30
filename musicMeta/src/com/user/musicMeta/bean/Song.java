package com.user.musicMeta.bean;

import java.io.InputStream;

import java.awt.image.BufferedImage;
public class Song {
	private int sng_id;
	private String sng_name;
	private String sng_dor;
	private String  sng_cov;
	private InputStream sng_cover;
	public Song( int  sng_id,String sng_name, String sng_dor, String sng_cov) {
		
		this.sng_name = sng_name;
		this.sng_dor = sng_dor;
		this.sng_cov = sng_cov;
		this.sng_id=sng_id;
	}
	private int usr_id;
	public int getSng_id() {
		return sng_id;
	}
	public void setSng_id(int sng_id) {
		this.sng_id = sng_id;
	}
	public String getSng_name() {
		return sng_name;
	}
	public void setSng_name(String sng_name) {
		this.sng_name = sng_name;
	}
	public String getSng_cov() {
		return sng_cov;
	}
	public void setSng_cov(String sng_cov) {
		this.sng_cov = sng_cov;
	}
	public String getSng_dor() {
		return sng_dor;
	}
	public void setSng_dor(String sng_dor) {
		this.sng_dor = sng_dor;
	}
	public InputStream getSng_cover() {
		return sng_cover;
	}
	public void setSng_cover(InputStream sng_cover) {
		this.sng_cover = sng_cover;
	}
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public Song(int sng_id, String sng_name, String sng_dor, InputStream sng_cover, int usr_id) {
		super();
		this.sng_id = sng_id;
		this.sng_name = sng_name;
		this.sng_dor = sng_dor;
		this.sng_cover = sng_cover;
		this.usr_id = usr_id;
	}
	public Song(String sng_name, String sng_dor, InputStream fileContent, int usr_id) {
		
		this.sng_name = sng_name;
		this.sng_dor = sng_dor;
		this.sng_cover = fileContent;
		this.usr_id = usr_id;
	}
	
	
	
}
