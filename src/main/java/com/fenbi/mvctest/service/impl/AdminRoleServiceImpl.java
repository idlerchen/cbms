package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.AdminRole;
import com.fenbi.mvctest.mapper.AdminRoleMapper;
import com.fenbi.mvctest.service.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public List<AdminRole> selectById(int adminId) {
		return adminRoleMapper.selectById(adminId);
	}

	
	@Override
	public void update(int adminId, int[] roles) {
		adminRoleMapper.delete(adminId);
		for(int roleId:roles) {
			adminRoleMapper.insert(adminId,roleId);
		}
	}

	@Override
	public void insert(int adminId, int roleId) {
		
	}

	@Override
	public void delete(int adminId) {
		
	}


}







