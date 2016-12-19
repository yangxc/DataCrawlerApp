package com.peraglobal.datacrawlerapp.task.model;

import java.util.Date;

/**
 *  <code>Task.java</code>
 *  <p>功能:任务对象,提供对web api数据结构的封装
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
public class Task {

	/**
	 * 任务Id
	 */
	private String taskId;
	
	/**
	 * 任务所属任务分组Id
	 */
	private String groupId;
	
	/**
	 * 任务所属任务分组名称
	 */
	private String groupName;

	/**
	 * 任务关联的爬虫id
	 */
	private String crawlerId;
	
	/**
	 * 任务名称
	 */
	private String taskName;
	
	/**
	 * 任务所处的状态（就绪、运行、停止、禁止）
	 */
	private String taskStatus;
	
	/**
	 * 任务创建时间
	 */
	private Date createdTime;
	
	/**
	 * 任务更新时间
	 */
	private Date updateTime;
	
	/**
	 * 任务调度规则
	 */
	private TaskSchedulerInfo schedulerInfo;
	
	public TaskSchedulerInfo getSchedulerInfo() {
		return schedulerInfo;
	}
	public void setSchedulerInfo(TaskSchedulerInfo schedulerInfo) {
		this.schedulerInfo = schedulerInfo;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCrawlerId() {
		return crawlerId;
	}
	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
