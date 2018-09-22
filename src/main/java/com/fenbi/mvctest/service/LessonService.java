package com.fenbi.mvctest.service;

import java.util.List;

import com.fenbi.mvctest.entity.Lesson;
import com.fenbi.mvctest.entity.Page;


public interface LessonService {
	
	public List<Lesson> selectAll(String key,int num);
	
	public int selectCount(String key);
	
	public Lesson selectById(int id);
	
	public List<Lesson> selectByCourseId(int courseId);
	
	public void insert(Lesson lesson);
	
	public void update(Lesson lesson);
	
	public void delete(int id);
}
