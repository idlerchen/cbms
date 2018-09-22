package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.RoleFunction;
import com.fenbi.mvctest.mapper.RoleFunctionMapper;
import com.fenbi.mvctest.service.RoleFunctionService;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;

	@Override
	public List<RoleFunction> selectById(int roleId) {
		return roleFunctionMapper.selectById(roleId);
	}

	@Override
	public void delete(int roleId) {
		roleFunctionMapper.delete(roleId);
	}

	@Override
	public void insert(int[] functions, int roleId) {
		for(int function:functions)
			roleFunctionMapper.insert(function, roleId);
	}

	@Override
	public void update(int roleId, int[] functions) {
		roleFunctionMapper.delete(roleId);
		if(functions!= null) {
			for(int function:functions) {
				roleFunctionMapper.insert(function, roleId);
			}
		}
	}



}







