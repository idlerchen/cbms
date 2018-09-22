package com.fenbi.mvctest.entity;


public class AdminRole {
	
	private int id;
	private int adminId;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public AdminRole(int id, int adminId, int roleId) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.roleId = roleId;
	}
	public AdminRole() {
		super();
	}
	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", adminId=" + adminId + ", roleId=" + roleId + "]";
	}
	
	

}
