package com.fenbi.mvctest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.AdminRole;


public interface AdminRoleService {
	
	public List<AdminRole> selectById(int adminId);
	
	public void delete(int adminId);
	
	public void insert(int adminId, int roleId);
	
	public void update(int adminId,int[] roles);
}
