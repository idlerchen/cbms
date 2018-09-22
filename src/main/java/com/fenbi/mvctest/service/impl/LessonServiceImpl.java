package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.Lesson;
import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.mapper.LessonMapper;
import com.fenbi.mvctest.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private LessonMapper lessonMapper;

	@Override
	public List<Lesson> selectAll(String key,int num) {
		Page page = new Page(num);
		page.put("title", key);
		return lessonMapper.selectAll(page);
	}

	@Override
	public int selectCount(String key) {
		return lessonMapper.selectCount(key);
	}

	@Override
	public Lesson selectById(int id) {
		return lessonMapper.selectById(id);
	}

	@Override
	public void insert(Lesson lesson) {
		lessonMapper.insert(lesson);
	}

	@Override
	public void update(Lesson lesson) {
		lessonMapper.update(lesson);
	}

	@Override
	public void delete(int id) {
		lessonMapper.delete(id);
	}

	@Override
	public List<Lesson> selectByCourseId(int courseId) {
		return lessonMapper.selectByCourseId(courseId);
	}


}







