package com.peraglobal.datacrawlerapp.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peraglobal.datacrawlerapp.crawler.service.CrawlerService;
import com.peraglobal.datacrawlerapp.task.model.TaskCrawler;

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
public class TaskCrawlerService {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	CrawlerService crawlerService;
	
	public List<TaskCrawler> getTaskCrawlers() {
		/*List<Task> tasks = taskService.getTasks(0, 0);
		List<TaskCrawler> results = new ArrayList<>();
		for (Task task : tasks) {
			String crawlerId = task.getTaskId();
			Crawler crawler = new Crawler();
			//crawler.setHistories(crawlerService.getCrawlerHistroy(crawlerId));
			TaskCrawler composer = new TaskCrawler();
			composer.setCrawler(crawler);
			composer.setTask(task);
			results.add(composer);
		}
		return results;*/
		return null;
	}

}
