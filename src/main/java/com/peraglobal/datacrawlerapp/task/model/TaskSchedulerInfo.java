package com.peraglobal.datacrawlerapp.task.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  <code>TaskScheduler.java</code>
 *  <p>功能:定义任务调度规则
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class TaskSchedulerInfo {

	/**
	 * 任务执行类型（1: 2: 3:）
	 */
	private String schedulerType;
	
	/**
	 * {
	 * 	任务类型：=====
	 *  执行时间：{
	 *  	开始时间：
	 *  	结束时间：
	 *  	周：第几天
	 *  	月：第几周 第几天
	 *  }
	 * }
	 * 
	 */
	
	private Date startTime;
	private Date stopTime;
	private int[] theDaysOfWeek;
	// private List<Integer, Integer[]> theDaysAndWeeksOfMonth = new ArrayList<>();
	
	
	
}
