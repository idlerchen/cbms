package com.fenbi.mvctest.mapper;


import java.util.List;

import com.fenbi.mvctest.entity.Function;


public interface FunctionMapper {
	
	
	public List<Function> select();
	
	public List<Function> selectByadminId(int id);
}
