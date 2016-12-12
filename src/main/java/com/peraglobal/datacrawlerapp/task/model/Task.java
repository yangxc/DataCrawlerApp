package com.peraglobal.datacrawlerapp.task.model;

public class Task {

	private Long taskId;
	private Long groupId;
	private Long crawlerId;
	private String taskName;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getCrawlerId() {
		return crawlerId;
	}
	public void setCrawlerId(Long crawlerId) {
		this.crawlerId = crawlerId;
	}
	
}
