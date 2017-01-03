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

	public final static String READY = "0";
	public final static String RUNNING = "1";
	public final static String STOP = "2";
	public final static String FORBIDDEN = "3";
	
	public final static String READY_TO = "就绪";
	public final static String RUNNING_TO = "运行";
	public final static String STOP_TO = "停止";
	public final static String FORBIDDEN_TO = "禁用";
	
}
