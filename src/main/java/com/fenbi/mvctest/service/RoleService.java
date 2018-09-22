package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.Role;


public interface RoleService {
	
	public int selectCount(String key);
	
	
	public void delete(int id);
	
	public List<Role> selectAll(String key,int num);
	
	public List<Role> select();
	
	public void insert(String name,int[] functions);
	
	public void update(Role role);
	
	public Role selectById(int roleId);
}
