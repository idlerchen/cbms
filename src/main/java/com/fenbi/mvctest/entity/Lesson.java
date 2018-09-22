package com.fenbi.mvctest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Lesson implements Serializable {

	private static final long serialVersionUID = -7257271902015073177L;

	private Integer id;
	private Integer courseId;
	private Integer teacherId;
	private String title;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private String courseTitle;
	private String teacherName;
	private Teacher teacher;

	public Lesson() {
		// TODO Auto-generated constructor stub
	}

	public Lesson(Integer id, Integer courseId, Integer teacherId, String title, Timestamp startTime, Timestamp endTime,
			Timestamp createTime) {
		this.id = id;
		this.courseId = courseId;
		this.teacherId = teacherId;
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + ", courseId=" + courseId + ", teacherId=" + teacherId + ", title=" + title
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", courseTitle="
				+ courseTitle + ", teacherName=" + teacherName + "] \n";
	}

}
