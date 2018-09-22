package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.exception.UpdateCourseStatusFailException;


public interface CourseService {
	
	/**
	 * 查询所有课程
	 * @param keyword 模糊查询字段  num第几页
	 * @return
	 */
	public List<Course> findAll(String keyword,int num);
	
	/**
	 * 插入新的课程
	 */
	public void insert(Course course);

	public int countAll(String keyword);

	public void updateStatus(int courseId, int status) throws UpdateCourseStatusFailException;

	public Course getCourseById(int id);

	public void update(Course course);
	
	public List<Course> selectTitles();
	
}
