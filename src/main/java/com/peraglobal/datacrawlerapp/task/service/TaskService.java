package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.datacrawlerapp.WebServiceProperties;
import com.peraglobal.datacrawlerapp.crawler.model.DbCrawler;
import com.peraglobal.datacrawlerapp.crawler.model.WebCrawler;
import com.peraglobal.datacrawlerapp.task.model.Task;
import com.peraglobal.datacrawlerapp.task.model.TaskStatus;

@Service
public class TaskService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private final String dbServiceURL;
	private final String webServiceURL;
	private final String taskServiceURL;
    
    @Autowired
    public TaskService(WebServiceProperties webServiceProperty) {
    	dbServiceURL = webServiceProperty.getDbService();
    	webServiceURL = webServiceProperty.getWebService();
    	taskServiceURL = webServiceProperty.getTaskService();
    }
    
    
	/**
	 * 获取支持的任务状态
	 * @return
	 */
	public List<String> getTaskStatuses() {
		List<String> statuses = new ArrayList<>();
		statuses.add(TaskStatus.FORBIDDEN.toString());
		statuses.add(TaskStatus.READY.toString());
		statuses.add(TaskStatus.RUNNING.toString());
		statuses.add(TaskStatus.STOP.toString());
		return statuses;
	}
	
	/**
	 * 根据任务状态，获取对应的任务列表
	 * @param status 任务状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByTaskStatus(String status) {
		String url = taskServiceURL + "/task/getTasksByState/" + status;
		return restTemplate.getForEntity(url, List.class).getBody();
	}
	
	
	/**
	 * 返回任务列表
	 * 
	 * @param pageNo 要获取的列表页数
	 * @param pageNum 每个列表页要显示的任务数
	 * 
	 * @return 任务列表
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasks(int pageNo, int pageNum) {
		String url = taskServiceURL + "/task/getTasks/" + pageNo + 1;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

	/**
	 * 根据组 Id 查询任务列表
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByGroupId(String groupId) {
		String url = taskServiceURL + "/task/getTaskList/" + groupId;
		return restTemplate.getForEntity(url, List.class).getBody();
	}
	
	/**
	 * 创建 web 爬虫任务
	 * @param webCrawler
	 */
	public void createWebCrawler(WebCrawler webCrawler) {
		String url = webServiceURL + "/createWeb/";
		restTemplate.postForEntity(url, webCrawler, String.class);
	}

	/**
	 * 创建 db 爬虫项目
	 * @param dbCrawler
	 */
	public void createDbCrawler(DbCrawler dbCrawler) {
		String url = dbServiceURL + "/createCrawler/";
		restTemplate.postForEntity(url, dbCrawler, String.class);
	}
	
	/**
	 * 删除任务
	 * @param taskIds
	 * @return
	 */
	public void removeTask(String taskIds) {
		String[] ids = taskIds.split(",");
		for (String taskId : ids) {
			if (taskId != null && !"".equals(taskId)) {
				String url = taskServiceURL + "/task/removeTask/" + taskId;
				restTemplate.delete(url);
			}
		}
	}
	
	/**
	 * 开始任务
	 * @param taskIds
	 * @return
	 */
	public boolean startTask(String taskIds) {
		String url = taskServiceURL + "/task/start";
		String[] ids = taskIds.split(",");
		for (String taskId : ids) {
			if (taskId != null && !"".equals(taskId)) {
				Task task = new Task();
				task.setTaskId(taskId);
				restTemplate.put(url, task);
			}
		}
		return true;
	}

	/**
	 * 停止任务
	 * @param taskIds
	 * @return
	 */
	public boolean stopTask(String taskIds) {
		String url = taskServiceURL + "/task/stop";
		String[] ids = taskIds.split(",");
		for (String taskId : ids) {
			if (taskId != null && !"".equals(taskId)) {
				Task task = new Task();
				task.setTaskId(taskId);
				restTemplate.put(url, task);
			}
		}
		return true;
	}


	

}
