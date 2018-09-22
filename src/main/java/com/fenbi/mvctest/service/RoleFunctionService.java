package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.RoleFunction;


public interface RoleFunctionService {
	
	public List<RoleFunction> selectById(int roleId);
	
	public void delete(int roleId);
	
	public void insert(int[] functions, int roleId);
	
	public void update(int roleId,int[] functions);
}
