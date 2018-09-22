package com.fenbi.mvctest.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.entity.Role;
import com.fenbi.mvctest.mapper.AdminMapper;
import com.fenbi.mvctest.mapper.RoleFunctionMapper;
import com.fenbi.mvctest.mapper.RoleMapper;
import com.fenbi.mvctest.service.AdminService;
import com.fenbi.mvctest.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;

	@Override
	public int selectCount(String key) {
		return roleMapper.selectCount(key);
	}
	
	@Transactional(transactionManager="transactionManager",rollbackFor=Exception.class)
	@Override
	public void delete(int id) {//删除role，同时删除role的function
		roleMapper.delete(id);
		//int i =10/0;   事务测试
		roleFunctionMapper.delete(id);
		
	}

	@Override
	public List<Role> selectAll(String key, int num) {
		Page page = new Page(num);
		page.put("name", key);
		return roleMapper.selectAll(page);
	}

	@Override
	public List<Role> select() {
		return roleMapper.select();
	}
	
	@Transactional(transactionManager="transactionManager",rollbackFor=Exception.class)
	@Override
	public void insert(String name,int[] functions) {
		Role role = new Role();
		role.setName(name);
		role.setCreateTime(new Timestamp(System.currentTimeMillis()));
		roleMapper.insert(role);
		
		//int i = 10/0;
		int roleId = role.getId();
		for(int function:functions)
			roleFunctionMapper.insert(function, roleId);
	}

	@Override
	public void update(Role role) {
		roleMapper.update(role);
	}

	@Override
	public Role selectById(int roleId) {
		return roleMapper.selectById(roleId);
	}

	
	
	

}







