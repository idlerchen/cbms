package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.entity.Teacher;
import com.fenbi.mvctest.exception.UpdateCourseStatusFailException;
import com.fenbi.mvctest.mapper.CourseMapper;
import com.fenbi.mvctest.mapper.TeacherMapper;
import com.fenbi.mvctest.service.CourseService;
import com.fenbi.mvctest.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public List<Teacher> selectAll(String keyword, int num) {
		Page page = new Page(num);
		page.put("name", keyword);
		return teacherMapper.selectAll(page);
	}

	@Override
	public void insert(Teacher teacher) {
		teacherMapper.insert(teacher);
	}

	@Override
	public int countAll(String keyword) {
		return teacherMapper.selectCount(keyword);
	}

	@Override
	public void updateStatus(int teacherId, int status) {
		Teacher teacher = teacherMapper.selectById(teacherId);
		teacher.setStatus(status);
		teacherMapper.updateStatus(teacher);
	}

	@Override
	public Teacher getTeacherById(int id) {
		return teacherMapper.selectById(id);
	}

	@Override
	public void update(Teacher teacher) {
		teacherMapper.update(teacher);
	}

	@Override
	public List<Teacher> selectNames() {
		return teacherMapper.selectNames();
	}
	

}







