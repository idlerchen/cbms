package com.fenbi.mvctest.mapper;

import java.util.List;

import com.fenbi.mvctest.entity.Lesson;
import com.fenbi.mvctest.entity.Page;


public interface LessonMapper {
	/**根据查询关键字和页码查询当前页所有的课时列表
	 * @param key  查询关键字
	 * @param begin 从哪一行开始查询
	 * @return  课时列表对象
	 */
	List<Lesson> selectAll(Page page);
	
	/**根据关键字查询课时的总数量
	 * @param key查询关键字
	 * @return 课时的总数量
	 */
	int selectCount(String key);
	
	/**根据课时id查询当前课时信息
	 * @param id 课时id
	 * @return  课时信息
	 */
	Lesson selectById(int id);
	
	/**添加课时
	 * @param Lesson 封装了课时信息的对象
	 */
	void insert(Lesson lesson);
	
	/**修改课时
	 * @param Lesson 封装了课时信息的对象
	 */
	void update(Lesson lesson);
	
	/**根据课时id删除对应的记录
	 * @param id 课时id
	 */
	void delete(int id);
	
	/*
	 * @Description: 根据课程id获取该课程的所有课时
	* @param courseId 课程id
	* @return:  课时集合
	 */
	List<Lesson> selectByCourseId(int courseId);
	
	
}
