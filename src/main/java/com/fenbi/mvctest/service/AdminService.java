package com.fenbi.mvctest.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fenbi.mvctest.entity.Admin;


public interface AdminService {
	
	public void insert(Admin admin);
	
	public List<Admin> select();
	
	public Admin login(String username,String password);
	
	public int selectCount(String key);
	
	public void update(Admin admin);
	
	public void delete(int id);
	
	public List<Admin> selectAll(String key,int num);
	
	public Admin selectById(int id);
}
