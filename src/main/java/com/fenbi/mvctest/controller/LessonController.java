package com.fenbi.mvctest.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.entity.FenbiResult;
import com.fenbi.mvctest.entity.Lesson;
import com.fenbi.mvctest.entity.Teacher;
import com.fenbi.mvctest.service.CourseService;
import com.fenbi.mvctest.service.LessonService;
import com.fenbi.mvctest.service.TeacherService;
import com.fenbi.mvctest.utils.DateUtils;

@Controller
@RequestMapping("/lesson")
public class LessonController {
	
	@Autowired
	public CourseService courseService;
	@Autowired
	public TeacherService teacherService;
	@Autowired
	public LessonService lessonService;
	
	@RequestMapping("/addLesson")
	@ResponseBody
	public FenbiResult addLesson(Lesson lesson,String startTimeString,String endTimeString) {
		lesson.setCreateTime(new Timestamp(System.currentTimeMillis()));
		lesson.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		lesson.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		lessonService.insert(lesson);
		return new FenbiResult();
	}
	
	
	@RequestMapping("/lessons")
	@ResponseBody
	public FenbiResult selectAll(String keyword,@RequestParam(defaultValue="1")int num) {
		List<Lesson> lessons = lessonService.selectAll(keyword, num);
		return new FenbiResult(lessons);
	}
	
	@RequestMapping("/alessons.do")
	@ResponseBody
	public FenbiResult selectByCourseId(int courseId) {
		List<Lesson> lessons = lessonService.selectByCourseId(courseId);
		return new FenbiResult(lessons);
	}
	
	/*
	 * @Description: 分页器获取所有记录
	* @return:返回总共记录条数
	 */
	@RequestMapping("/lessonCount")
	@ResponseBody
	public FenbiResult lessonCount(String keyword) {
		int counts = lessonService.selectCount(keyword);
		return new FenbiResult(counts);
	}
	
	
	@RequestMapping("/getLessonById")
	@ResponseBody
	public FenbiResult getLessonById(int id) {
		Lesson lesson = lessonService.selectById(id);
		return new FenbiResult(lesson);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public FenbiResult updateLesson(Lesson lesson,String startTimeString,String endTimeString) {
		lesson.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		lesson.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		lessonService.update(lesson);
		return new FenbiResult();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public FenbiResult deleteLesson(int id) {
		lessonService.delete(id);
		return new FenbiResult();
	}
	
	@RequestMapping("/coursesAndTeachers")
	@ResponseBody
	public FenbiResult getCourseAndTeacher() {
		List<Course> courses = courseService.selectTitles();
		List<Teacher> teachers = teacherService.selectNames();
		HashMap<String,List> map = new HashMap<>();
		map.put("courses", courses);
		map.put("teachers", teachers);
		return new FenbiResult(map);
	}

}
