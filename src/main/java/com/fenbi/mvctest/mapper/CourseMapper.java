package com.fenbi.mvctest.mapper;

import java.util.List;

import com.fenbi.mvctest.entity.Course;
import com.fenbi.mvctest.entity.Page;


public interface CourseMapper {

	/*
	 * @Description: 插入课程
	* @param course:
	 */
	public void insert(Course course);
	
	/*
	 * @Description: 删除课程
	* @param id:
	 */
	public void delete(int id);
	
	/*
	 * @Description: 更新课程
	* @param course:
	 */
	public void update(Course course);
	
	/*
	 * @Description: 获取所有课程列表，page中带着显示那几条信息（begin,size)，还封装着模糊查询的title属性
	* @param page
	* @return:
	 */
	public List<Course> selectAll(Page page);
	
	/*
	 * @Description: 获取所有的记录数，带着模糊查询的字段，没有为空，查询所有
	* @param keyword
	* @return:
	 */
	public int selectCount(String keyword);
	
	/*
	 * @Description: 根据ID获取课程
	* @param id
	* @return:
	 */
	public Course selectById(int id);
	
	/*
	 * @Description: 单独更新课程的状态信息， 即上架下架
	* @param course:
	 */
	public void updateStatus(Course course);
	
	/*
	 * @Description: 获取所有课程的标题
	* @return: 返回课程标题的集合
	 */
	public List<Course> selectTitles();
	
}
