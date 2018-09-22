package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.Function;


public interface FunctionService {
	
	public List<Function> select();
	
	public List<Function> selectByadminId(int id);
}
