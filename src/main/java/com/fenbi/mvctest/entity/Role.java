package com.fenbi.mvctest.entity;

import java.sql.Timestamp;

public class Role {
	
	private int id;
	private String name;
	private Timestamp createTime;
	public Role(int id, String name, Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}
	public Role() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", createTime=" + createTime + "]";
	}
	
	

}
