package com.fenbi.mvctest.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.entity.FenbiResult;
import com.fenbi.mvctest.entity.Teacher;
import com.fenbi.mvctest.exception.UpdateCourseStatusFailException;
import com.fenbi.mvctest.mapper.TeacherMapper;
import com.fenbi.mvctest.service.CourseService;
import com.fenbi.mvctest.service.TeacherService;
import com.fenbi.mvctest.utils.DateUtils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	public TeacherService teacherService;
	
	@RequestMapping("/addTeacher")
	@ResponseBody
	public FenbiResult addTeacher(Teacher teacher,MultipartFile file,HttpServletRequest request) throws IOException {
		  String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
        String fileName = file.getOriginalFilename();
        String saveFileName = System.currentTimeMillis()+ fileName.substring(fileName.indexOf("."),fileName.length());
        File dir = new File(path, saveFileName);
        if (!dir.getParentFile().exists()) {
            dir.getParentFile().mkdirs();
        }
        //调用file.transferTo()直接保存文件
        file.transferTo(dir);
        String url = "/cbms/uploadfiles/" + saveFileName;
	    teacher.setPhotoUrl(url); 
		teacher.setCreateTime(new Timestamp(System.currentTimeMillis()));
		teacher.setScore(10.0);
		teacherService.insert(teacher);
		return new FenbiResult();
	}
	
	
	@RequestMapping("/teachers")
	@ResponseBody
	public FenbiResult selectAll(String keyword,@RequestParam(defaultValue="1")int num) {
		List<Teacher> teachers = teacherService.selectAll(keyword, num);
		return new FenbiResult(teachers);
	}
	
	/*
	 * @Description: 分页器获取所有记录
	* @return:返回总共记录条数
	 */
	@RequestMapping("/countAll")
	@ResponseBody
	public FenbiResult countAll(String keyword) {
		int counts = teacherService.countAll(keyword);
		return new FenbiResult(counts);
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public FenbiResult updateStatus(int teacherId,int status) {
		teacherService.updateStatus(teacherId, status);
		return new FenbiResult();
	}
	
	@RequestMapping("/getTeacherById")
	@ResponseBody
	public FenbiResult getTeacherById(int teacherId) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		return new FenbiResult(teacher);
	}
	
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public FenbiResult updateTeacher(Teacher teacher,MultipartFile file,HttpServletRequest request) throws IOException {
		  String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
		  if(file != null) {
		        String fileName = file.getOriginalFilename();
		        String saveFileName = System.currentTimeMillis()+ fileName.substring(fileName.indexOf("."),fileName.length());
		        File dir = new File(path, saveFileName);
		        if (!dir.getParentFile().exists()) {
		            dir.getParentFile().mkdirs();
		        }
		        //调用file.transferTo()直接保存文件
		        file.transferTo(dir);
		        String url = "/cbms/uploadfiles/" + saveFileName;
			    teacher.setPhotoUrl(url); 
		  }
		teacherService.update(teacher);
		return new FenbiResult();
	}

}
