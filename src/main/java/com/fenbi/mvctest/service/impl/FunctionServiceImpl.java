package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.Function;
import com.fenbi.mvctest.mapper.FunctionMapper;
import com.fenbi.mvctest.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionMapper functionMapper;

	@Override
	public List<Function> select() {
		return functionMapper.select();
	}

	@Override
	public List<Function> selectByadminId(int id) {
		return functionMapper.selectByadminId(id);
	}


}







