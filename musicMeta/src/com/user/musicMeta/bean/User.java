package com.user.musicMeta.bean;

public class User {

	private int usr_id;
	private String usr_name;
	private String usr_email;
	private String usr_password;
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getUsr_email() {
		return usr_email;
	}
	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	public String getUsr_password() {
		return usr_password;
	}
	public void setUsr_password(String usr_password) {
		this.usr_password = usr_password;
	}
	public User(int usr_id, String usr_name, String usr_email, String usr_password) {
		super();
		this.usr_id = usr_id;
		this.usr_name = usr_name;
		this.usr_email = usr_email;
		this.usr_password = usr_password;
	}
	public User(String usr_name, String usr_email, String usr_password) {
		super();
		this.usr_name = usr_name;
		this.usr_email = usr_email;
		this.usr_password = usr_password;
	}
	
	
}
