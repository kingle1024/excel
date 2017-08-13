package com.mycompany.vo;

import java.util.Date;

public class workExcel {
	private Date workDate;
	private String week;
	private Date startTime;
	private Date endTime;
	private Date endSubStart;
	private String content;
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndSubStart() {
		return endSubStart;
	}
	public void setEndSubStart(Date endSubStart) {
		this.endSubStart = endSubStart;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "workExcel [workDate=" + workDate + ", week=" + week + ", startTime=" + startTime + ", endTime="
				+ endTime + ", endSubStart=" + endSubStart + ", content=" + content + "]";
	}
	
}
