package com.fenbi.mvctest.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Course implements Serializable {

	private static final long serialVersionUID = -5224036032495876575L;

	private Integer id;
	private Integer typeId;
	private String title;
	private String description;
	private Double price;
	private Integer total;
	private Integer status;// 表示 0未上架／1上架／2下架
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private Integer saleNum=10;
	private Integer lessonCount=10;
	// 课程类型名
	private String courseTypeName;
	private List<Lesson> lessons;

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(Integer id, Integer typeId, String title, String description, Double price, Integer total,
			Integer status, Timestamp startTime, Timestamp endTime, Timestamp createTime, Integer saleNum,
			Integer lessonCount) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.title = title;
		this.description = description;
		this.price = price;
		this.total = total;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.saleNum = saleNum;
		this.lessonCount = lessonCount;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getLessonCount() {
		return lessonCount;
	}

	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", typeId=" + typeId + ", title=" + title + ", description=" + description
				+ ", price=" + price + ", total=" + total + ", status=" + status + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + ", saleNum=" + saleNum + ", lessonCount="
				+ lessonCount + "]\n";
	}

}
