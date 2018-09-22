package com.fenbi.mvctest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.exception.UpdateCourseStatusFailException;
import com.fenbi.mvctest.mapper.CourseMapper;
import com.fenbi.mvctest.service.CourseService;


/**
 * 当前Service将会被加入Spring容器
 * @author durh
 *
 */
@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public List<Course> findAll(String keyword,int num) {
		Page page = new Page(num);
		page.put("title", keyword);
		return courseMapper.selectAll(page);
	}

	@Override
	public void insert(Course course) {
		courseMapper.insert(course);
	}

	@Override
	public int countAll(String keyword) {
		return courseMapper.selectCount(keyword);
	}

	@Override
	public void updateStatus(int courseId, int status) throws UpdateCourseStatusFailException {
		Course course = courseMapper.selectById(courseId);
		if(course== null) {
			throw new UpdateCourseStatusFailException("课程状态一致，不需要修改");
		}else {
			course.setStatus(status);
			courseMapper.updateStatus(course);
		}
	}

	@Override
	public Course getCourseById(int id) {
		return courseMapper.selectById(id);
	}

	@Override
	public void update(Course course) {
		courseMapper.update(course);
	}

	@Override
	public List<Course> selectTitles() {
		return courseMapper.selectTitles();
	}

}







