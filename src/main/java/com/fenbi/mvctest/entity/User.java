package com.fenbi.mvctest.entity;

import java.sql.Timestamp;
import java.util.List;

public class User {
	
	private int id;
	private String username;
	private String password;
	private Timestamp createTime;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createTime=" + createTime
				+ "]";
	}
	public User(int id, String username, String password, Timestamp createTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createTime = createTime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

}
