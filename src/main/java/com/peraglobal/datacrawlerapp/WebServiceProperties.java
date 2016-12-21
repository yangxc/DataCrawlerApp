package com.peraglobal.datacrawlerapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="webservice.url")
public class WebServiceProperties {
	
	private String taskService;
	private String crawlerService;
	
	public String getTaskService() {
		return taskService;
	}
	public void setTaskService(String taskService) {
		this.taskService = taskService;
	}
	
	public String getCrawlerService() {
		return crawlerService;
	}
	public void setCrawlerService(String crawlerService) {
		this.crawlerService = crawlerService;
	}
	
	
}
