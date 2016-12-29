package com.peraglobal.datacrawlerapp.task.model;

/**
 *  <code>CrawlerRule.java</code>
 *  <p>功能:定义任务状态类型
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class TaskStatus {

	public final static TaskStatus READY = new TaskStatus("READY");
	public final static TaskStatus RUNNING = new TaskStatus("RUNNING");
	public final static TaskStatus STOP = new TaskStatus("STOP");
	public final static TaskStatus FORBIDDEN = new TaskStatus("FORBIDDEN");
	
	private String status;
	
	private TaskStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.status;
	}
}
