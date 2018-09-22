package com.fenbi.mvctest.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.mvctest.entity.RoleFunction;


public interface RoleFunctionMapper {
	
	public void insert(@Param("functionId")int functionId,@Param("roleId") int roleId);
	
	public void delete(int roleId);
	
	public List<RoleFunction> selectById(int roleId);
}
