package com.fenbi.mvctest.mapper;

import java.util.List;

import com.fenbi.mvctest.entity.Page;
import com.fenbi.mvctest.entity.Teacher;


public interface TeacherMapper {
	/**根据查询关键字和页码查询当前页所有的老师列表
	 * @param key  查询关键字
	 * @param begin 从哪一行开始查询
	 * @return  老师列表对象
	 */
	public List<Teacher> selectAll(Page page);
	
	/**根据关键字查询老师的总数量
	 * @param key查询关键字
	 * @return 老师的总数量
	 */
	public int selectCount(String key);
	
	/**根据老师id查询当前老师信息
	 * @param id 老师id
	 * @return  老师信息
	 */
	public Teacher selectById(int id);
	
	/**添加老师
	 * @param Teacher 封装了老师信息的对象
	 */
	public void insert(Teacher teacher);
	
	/**修改老师
	 * @param Teacher 封装了老师信息的对象
	 */
	public void update(Teacher teacher);
	
	/**根据老师id删除对应的记录
	 * @param id 老师id
	 */
	//void deleteById(int id);
	/**修改教师的职位状态
	 * @param teahcerId 教师id
	 * @param status 修改后的状态
	 */
	public void updateStatus(Teacher teacher);
	
	/**查询所有的老师
	 * @return 返回所有的老师名字
	 */
	public List<Teacher> selectTeacherNames();
	
	/*
	 * @Description: 获取所有的讲师名字
	* @return: 返回集合
	 */
	public List<Teacher> selectNames();
	
}
