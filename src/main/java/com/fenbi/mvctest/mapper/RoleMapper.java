package com.fenbi.mvctest.mapper;


import java.util.List;


import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.entity.Role;


public interface RoleMapper {
	
	
	public int selectCount(String key);
	public List<Role> selectAll(Page page);
	public void delete(int id);
	
	public void insert(Role role);
	
	public List<Role> select();
	
	
	public void update(Role role);
	
	
	public Role selectById(int id);
}
