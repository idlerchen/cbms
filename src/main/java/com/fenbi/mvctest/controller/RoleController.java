package com.fenbi.mvctest.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.mvctest.entity.FenbiResult;
import com.fenbi.mvctest.entity.Function;
import com.fenbi.mvctest.entity.Role;
import com.fenbi.mvctest.entity.RoleFunction;
import com.fenbi.mvctest.service.FunctionService;
import com.fenbi.mvctest.service.RoleFunctionService;
import com.fenbi.mvctest.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	public RoleService roleService;
	@Autowired
	public FunctionService functionService;
	@Autowired
	public RoleFunctionService roleFunctionService;
	
	/*
	 * @Description: 增加role   ，  返回主键   增加role_function联系表
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/add")
	@ResponseBody
	public FenbiResult addRoleAndRoleFunction(String name,int[] functions) {
		roleService.insert(name, functions);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 给用户配角色时查询所有角色
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/All")
	@ResponseBody
	public FenbiResult select() {
		List<Role> roles = roleService.select();
		return new FenbiResult(roles);
	}
	
	/*
	 * @Description: 修改角色时 根据id查出角色信息 回显
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/getRole")
	@ResponseBody
	public FenbiResult getRole(int roleId) {
		Role role = roleService.selectById(roleId);
		return new FenbiResult(role);
	}
	
	/*
	 * @Description: 给角色配功能时查询所有功能
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/getFunction")
	@ResponseBody
	public FenbiResult getFunction() {
		List<Function> functions = functionService.select();
		return new FenbiResult(functions);
	}
	
	/*
	 * @Description: 修改角色功能时，根据roleId查询role对应的function
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/getRoleFunction")
	@ResponseBody
	public FenbiResult getRoleFunction(int roleId) {
		List<RoleFunction> roleFunctions = roleFunctionService.selectById(roleId);
		return new FenbiResult(roleFunctions);
	}
	
	/*
	 * @Description: 角色管理中查询中一页的角色
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/roles")
	@ResponseBody
	public FenbiResult selectAll(String keyword,@RequestParam(defaultValue="1")int num) {
		List<Role> roles = roleService.selectAll(keyword, num);
		return new FenbiResult(roles);
	}
	
	/*
	 * @Description: 查有多少个角色，用于分页
	* @param keyword
	* @return:
	 */
	@RequestMapping("/count")
	@ResponseBody
	public FenbiResult roleCount(String keyword) {
		int counts = roleService.selectCount(keyword);
		return new FenbiResult(counts);
	}
	
	
	/*
	 * @Description: 删出某一个角色， 以及删除role_function联系表改角色的所有记录
	* @param id
	* @return:
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public FenbiResult deleteRole(int id) {
		roleService.delete(id);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 删出某一个角色， 以及删除role_function联系表改角色的所有记录
	* @param id
	* @return:
	 */
	@RequestMapping("/update")
	@ResponseBody
	public FenbiResult update(Role role,int[] functions) {
		roleService.update(role);
		roleFunctionService.update(role.getId(), functions);
		return new FenbiResult();
	}

}
