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

	public final static TaskStatus READY = new TaskStatus("就绪");
	public final static TaskStatus RUNNING = new TaskStatus("运行");
	public final static TaskStatus STOP = new TaskStatus("停止");
	public final static TaskStatus FORBIDDEN = new TaskStatus("禁用");
	
	private String status;
	
	private TaskStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.status;
	}
}
