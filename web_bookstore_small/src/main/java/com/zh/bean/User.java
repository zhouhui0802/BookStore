package com.zh.bean;



/*
 CREATE TABLE users
 (
 id VARCHAR(40) PRIMARY KEY, #主键
 username VARCHAR(20) UNIQUE, #用户名
 PASSWORD VARCHAR(30), #密码
 email VARCHAR(40) #邮箱
 );
 */
public class User {

	private String id;
	private String username;
	private String password;
	private String email;

	public User(String id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + "]";
	}

}

