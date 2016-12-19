package com.peraglobal.datacrawlerapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.datacrawlerapp.crawler.model.Crawler;
import com.peraglobal.datacrawlerapp.crawler.service.CrawlerService;
import com.peraglobal.datacrawlerapp.model.CrawlerAndTask;
import com.peraglobal.datacrawlerapp.task.model.Task;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

/**
 *  <code>CrawlerTaskService.java</code>
 *  <p>功能:通过组合TaskService和TaskService完成组合操作
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-15
 *  </br>最后修改人 无
 */
@Service
public class CrawlerTaskService {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	CrawlerService crawlerService;
	
	public List<CrawlerAndTask> getCrawlerAndTask() {
		List<Task> tasks = taskService.getTasks(0, 0);
		List<CrawlerAndTask> results = new ArrayList<>();
		for (Task task : tasks) {
			String crawlerId = task.getCrawlerId();
			Crawler crawler = new Crawler();
			crawler.setHistories(crawlerService.getCrawlerHistroy(crawlerId));
			CrawlerAndTask composer = new CrawlerAndTask();
			composer.setCrawler(crawler);
			composer.setTask(task);
			results.add(composer);
		}
		return results;
	}

}
