package com.agg.application.model;

public class Result {

	private String status;

	private String errMessage;

	private Object data;
	
	private int draw;
	
	private long recordsTotal;
	
	private long recordsFiltered;
	
	

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
	
	

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", errMessage=" + errMessage + ", data=" + data + "]";
	}

}
