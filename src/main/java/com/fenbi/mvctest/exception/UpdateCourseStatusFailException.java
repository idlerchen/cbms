package com.fenbi.mvctest.exception;
/**
 * 当修改课程状态时将会出现的业务异常
 */
public class UpdateCourseStatusFailException extends Exception {

	public UpdateCourseStatusFailException(String message) {		
		super(message);
	}
	
}
