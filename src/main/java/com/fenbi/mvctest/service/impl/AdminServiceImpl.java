package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.mapper.AdminMapper;
import com.fenbi.mvctest.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminmapper;

	@Override
	public void insert(Admin admin) {
		adminmapper.insert(admin);
		
	}

	@Override
	public List<Admin> select() {
		return adminmapper.select();
	}

	@Override
	public Admin login(String username, String password) {
		return adminmapper.login(username, password);
	}

	@Override
	public int selectCount(String key) {
		return adminmapper.selectCount(key);
	}

	@Override
	public void update(Admin admin) {
		adminmapper.update(admin);
	}

	@Override
	public void delete(int id) {
		adminmapper.delete(id);
	}

	@Override
	public List<Admin> selectAll(String key,int num) {
		Page page = new Page(num);
		page.put("name", key);
		return adminmapper.selectAll(page);
	}

	@Override
	public Admin selectById(int id) {
		return adminmapper.selectById(id);
	}
	

}







