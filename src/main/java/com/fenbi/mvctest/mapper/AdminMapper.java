package com.fenbi.mvctest.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.Page;


public interface AdminMapper {
	
	
	public void insert(Admin admin);
	
	public List<Admin> select();
	
	public Admin login(@Param("username")String username,@Param("password")String password);
	
	public int selectCount(String key);
	
	public void update(Admin admin);
	
	public void delete(int id);
	
	public List<Admin> selectAll(Page page);
	
	public Admin selectById(int id);
}
