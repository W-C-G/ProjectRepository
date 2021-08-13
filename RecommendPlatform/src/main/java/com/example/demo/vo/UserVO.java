package com.example.demo.vo;

public class UserVO {
	private int uno;
	private String id;
	private String pwd;
	private String uname;
	private String phone;
	private String email;
	
	public UserVO() {}
	
	public UserVO(int uno, String id, String pwd, String uname, String phone, String email) {
		this.uno = uno;
		this.id = id;
		this.pwd = pwd;
		this.uname = uname;
		this.phone = phone;
		this.email = email;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
