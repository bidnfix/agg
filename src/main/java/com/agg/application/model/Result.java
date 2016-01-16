package com.agg.application.model;

public class Result {

	private String status;

	private String errMessage;

	private Object data;

	public Result(String status, String errMessage, Object data) {
		this.status = status;
		this.errMessage = errMessage;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", errMessage=" + errMessage + ", data=" + data + "]";
	}

}
