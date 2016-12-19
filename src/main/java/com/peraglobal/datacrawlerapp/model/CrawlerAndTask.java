package com.peraglobal.datacrawlerapp.model;

import com.peraglobal.datacrawlerapp.crawler.model.Crawler;
import com.peraglobal.datacrawlerapp.task.model.Task;

/**
 *  <code>CrawlerAndTask.java</code>
 *  <p>功能:通过组合Task和Crawler形成组合辅助类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-15
 *  </br>最后修改人 无
 */
public class CrawlerAndTask {
	
	private Crawler crawler;
	private Task task;
	
	public Crawler getCrawler() {
		return crawler;
	}
	public void setCrawler(Crawler crawler) {
		this.crawler = crawler;
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
}
