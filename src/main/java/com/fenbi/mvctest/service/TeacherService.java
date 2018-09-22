package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.Teacher;


public interface TeacherService {
	
	public List<Teacher> selectAll(String keyword,int num);
	
	public void insert(Teacher teacher);

	public int countAll(String keyword);

	public void updateStatus(int teacherId, int status) ;

	public Teacher getTeacherById(int id);

	public void update(Teacher teacher);
	
	public List<Teacher> selectNames();
}
