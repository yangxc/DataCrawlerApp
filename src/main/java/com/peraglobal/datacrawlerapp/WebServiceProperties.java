package com.peraglobal.datacrawlerapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="webservice.url")
public class WebServiceProperties {
	
	private String dbService;
	private String webService;
	private String taskService;
	
	
	public String getDbService() {
		return dbService;
	}
	public void setDbService(String dbService) {
		this.dbService = dbService;
	}
	public String getWebService() {
		return webService;
	}
	public void setWebService(String webService) {
		this.webService = webService;
	}
	public String getTaskService() {
		return taskService;
	}
	public void setTaskService(String taskService) {
		this.taskService = taskService;
	}

}
