package com.fenbi.mvctest.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.mvctest.entity.AdminRole;


public interface AdminRoleMapper {
	
	
	public List<AdminRole> selectById(int adminId);
	
	public void delete(int adminId);
	
	public void insert(@Param("adminId") int adminId,@Param("roleId") int roleId);
}
