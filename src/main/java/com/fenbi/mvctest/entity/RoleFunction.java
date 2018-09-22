package com.fenbi.mvctest.entity;


public class RoleFunction {
	
	private int id;
	private int functionId;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public RoleFunction(int id, int functionId, int roleId) {
		super();
		this.id = id;
		this.functionId = functionId;
		this.roleId = roleId;
	}
	public RoleFunction() {
		super();
	}
	@Override
	public String toString() {
		return "RoleFunction [id=" + id + ", roleId=" + roleId + ", functionId=" + functionId + "]";
	}
	
	

}
