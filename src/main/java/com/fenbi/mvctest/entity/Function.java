package com.fenbi.mvctest.entity;


public class Function {
	
	private int id;
	private String name;
	private String keyword;
	private String url;
	public Function(int id, String name, String keyword, String url) {
		super();
		this.id = id;
		this.name = name;
		this.keyword = keyword;
		this.url = url;
	}
	public Function() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Function [id=" + id + ", name=" + name + ", keyword=" + keyword + ", url=" + url + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
