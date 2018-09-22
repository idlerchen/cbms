package com.fenbi.mvctest.entity;

import java.io.Serializable;

public class FenbiResult implements Serializable {
	private static final long serialVersionUID = -4969534715966733889L;
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_FAIL = 1;
	public static final int STATUS_NOUSER = 2;
	public static final int STATUS_NOACCESS = 3;

	private int status = STATUS_SUCCESS;

	private String msg;

	private Object data;

	public FenbiResult() {
		super(); 
	}

	public FenbiResult(Object data) {
		super();
		this.data = data;
	}

	public FenbiResult(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
