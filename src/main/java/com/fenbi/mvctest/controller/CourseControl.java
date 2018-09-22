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
import com.fenbi.mvctest.exception.UpdateCourseStatusFailException;
import com.fenbi.mvctest.service.CourseService;
import com.fenbi.mvctest.utils.DateUtils;

@Controller
@RequestMapping("/course")
public class CourseControl {
	
	@Autowired
	public CourseService courseService;
	
	/*
	 * @Description: 添加课程， 
	* @param course  课程相关信息
	* @param startTimeString   额外需要处理的两条日期信息
	* @param endTimeString
	* @return:  返回json给客户端
	 */
	@RequestMapping("/addCourse")
	@ResponseBody
	public FenbiResult addCourse(Course course,String startTimeString,String endTimeString) {
		course.setCreateTime(new Timestamp(System.currentTimeMillis()));
		course.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		course.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		course.setStatus(0);
		courseService.insert(course);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 富文本编辑器中上传图片处理
	* @param file  文件处理类
	* @param request 通过request对象获取路径
	* @return  返回上传后存储的路径给客户端写入数据库
	* @throws IOException: IO操作可能有异常
	 */
	@RequestMapping("/upload.do")
	@ResponseBody
	 public String upload(MultipartFile file, HttpServletRequest request)
	            throws IOException {
	        String path = request.getSession().getServletContext().getRealPath("/uploadfiles");
	        //  2.jpg  -->  .jpg
	        String fileName = file.getOriginalFilename();
	        String saveFileName = System.currentTimeMillis()+ fileName.substring(fileName.indexOf("."),fileName.length());
	        File dir = new File(path, saveFileName);
	        if (!dir.getParentFile().exists()) {
	            dir.getParentFile().mkdirs();
	        }
	        //调用file.transferTo()直接保存文件
	        file.transferTo(dir);
	        String url = "/cbms/uploadfiles/" + saveFileName;
	        String result = "{\"errno\":0,\"data\":[\"" + url + "\"]}";
	        return result;
	    }
	
	/*
	 * @Description: 获取所有课程操作 ，增加keyword,增加title字段的模糊查询
	* @return:  返回json格式课程列表list
	 */
	@RequestMapping("/selectAll")
	@ResponseBody
	public FenbiResult selectCourse(String keyword,@RequestParam(defaultValue="1")int num) {
		List<Course> courses = courseService.findAll(keyword,num);
		return new FenbiResult(courses);
	}
	
	/*
	 * @Description: 分页器获取所有记录
	* @return:返回总共记录条数
	 */
	@RequestMapping("/countAll")
	@ResponseBody
	public FenbiResult countAll(String keyword) {
		int counts = courseService.countAll(keyword);
		return new FenbiResult(counts);
	}
	
	/*
	 * @Description: 更新课程的状态
	* @param courseId  获取课程id
	* @param status   修改的课程状态
	* @return: 返回json
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public FenbiResult updateStatus(int courseId,int status) {
		try {
			courseService.updateStatus(courseId,status);
		} catch (UpdateCourseStatusFailException e) {
			return new FenbiResult(1, e.getMessage(), null);
		}
		return new FenbiResult();
	}
	
	/*
	 * @Description: 根据课程id获取课程相信信息
	* @param id  课程id
	* @return: 返回json，包含课程信息
	 */
	@RequestMapping("/getCourseById")
	@ResponseBody
	public FenbiResult getCourseById(int id) {
		Course course = courseService.getCourseById(id);
		return new FenbiResult(course);
	}
	
	/*
	 * @Description: 更新课程信息
	* @param course   传过来的课程信息
	* @param startTimeString  额外需要的两个时间 需要string todate 处理一下
	* @param endTimeString
	* @return:  返回json
	 */
	@RequestMapping("/updateCourse")
	@ResponseBody
	public FenbiResult updatecourse(Course course,String startTimeString,String endTimeString) {
		course.setStartTime(new Timestamp(DateUtils.stringToDate(startTimeString).getTime()));
		course.setEndTime(new Timestamp(DateUtils.stringToDate(endTimeString).getTime()));
		courseService.update(course);
		return new FenbiResult();
	}

}
