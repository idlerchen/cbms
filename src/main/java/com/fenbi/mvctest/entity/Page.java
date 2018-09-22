package com.fenbi.mvctest.entity;

import java.util.HashMap;

/**
 * @version: 
* @Description: 分页器的实体类，是一个map集合，有当前页，每页多少个，开始位置属性，去填充limit中的begin, size
* @author: cgq  
* @date: 2018年9月13日 下午9:55:04
 */
public class Page extends HashMap<String, Object> {
	private static final long serialVersionUID = -6180377661907437656L;
	//每页显示多少条数据
	public static final int PAGE_SIZE = 5;
	
	public Page(int current) {
		super();
		this.put("current", current);
		this.put("size", PAGE_SIZE);
		this.put("begin", (current - 1) * PAGE_SIZE);
	}

}
