package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.datacrawlerapp.WebServiceProperties;
import com.peraglobal.datacrawlerapp.crawler.model.DbCrawler;
import com.peraglobal.datacrawlerapp.crawler.model.History;
import com.peraglobal.datacrawlerapp.crawler.model.Metadata;
import com.peraglobal.datacrawlerapp.crawler.model.WebCrawler;
import com.peraglobal.datacrawlerapp.task.model.Status;
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
	public List<Status> getTaskStatuses() {
		List<Status> statuses = new ArrayList<Status>();
		
		Status ready = new Status();
		ready.setKey(TaskStatus.READY);
		ready.setValue(TaskStatus.READY_TO);
		ready.setCount(getTasksByTaskStatus(TaskStatus.READY).size());
		
		Status running = new Status();
		running.setKey(TaskStatus.RUNNING);
		running.setValue(TaskStatus.RUNNING_TO);
		running.setCount(getTasksByTaskStatus(TaskStatus.RUNNING).size());
		
		Status stop = new Status();
		stop.setKey(TaskStatus.STOP);
		stop.setValue(TaskStatus.STOP_TO.toString());
		stop.setCount(getTasksByTaskStatus(TaskStatus.STOP).size());
		
		Status forbidden = new Status();
		forbidden.setKey(TaskStatus.FORBIDDEN);
		forbidden.setValue(TaskStatus.FORBIDDEN_TO);
		forbidden.setCount(getTasksByTaskStatus(TaskStatus.FORBIDDEN).size());
		
		statuses.add(ready);
		statuses.add(running);
		statuses.add(stop);
		statuses.add(forbidden);
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
		List<Task> tasks = restTemplate.getForEntity(url, List.class).getBody();
		this.setTasksStatusAndCount(tasks);
		return tasks;
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
		List<Task> tasks = restTemplate.getForEntity(url, List.class).getBody();
		this.setTasksStatusAndCount(tasks);
		return tasks;
	}

	/**
	 * 根据组 Id 查询任务列表
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByGroupId(String groupId) {
		String url = taskServiceURL + "/task/getTaskList/" + groupId;
		List<Task> tasks = restTemplate.getForEntity(url, List.class).getBody();
		this.setTasksStatusAndCount(tasks);
		return tasks;
	}
	
	/**
	 * 创建 web 爬虫任务
	 * @param webCrawler
	 */
	public void createWebCrawler(WebCrawler webCrawler) {
		String url = webServiceURL + "/createWeb/";
		webCrawler.setCrawlerId(java.util.UUID.randomUUID().toString());
		// 保存web爬虫对象
		restTemplate.postForEntity(url, webCrawler, String.class);
		
		// 保存任务对象
		Task task = new Task();
		task.setTaskId(webCrawler.getCrawlerId());
		task.setTaskName(webCrawler.getCrawlerName());
		task.setGroupId(webCrawler.getGroupId());
		task.setGroupName(webCrawler.getGroupName());
		task.setStartCommand(webServiceURL + "/start");
		task.setStopCommand(webServiceURL + "/stop");
		url = taskServiceURL + "/task/createTask/";
		restTemplate.postForEntity(url, task, String.class);
	}

	/**
	 * 创建 db 爬虫项目
	 * @param dbCrawler
	 */
	public void createDbCrawler(DbCrawler dbCrawler) {
		String url = dbServiceURL + "/createCrawler/";
		dbCrawler.setCrawlerId(java.util.UUID.randomUUID().toString());
		// 保存web爬虫对象
		restTemplate.postForEntity(url, dbCrawler, String.class);
		
		// 保存任务对象
		Task task = new Task();
		task.setTaskId(dbCrawler.getCrawlerId());
		task.setTaskName(dbCrawler.getCrawlerName());
		task.setGroupId(dbCrawler.getGroupId());
		task.setGroupName(dbCrawler.getGroupName());
		task.setStartCommand(dbServiceURL + "/start");
		task.setStopCommand(dbServiceURL + "/stop");
		url = taskServiceURL + "/task/createTask/";
		restTemplate.postForEntity(url, task, String.class);
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


	public List<History> getHistoryByTaskId(String taskId) {
		/*
		String url = dbServiceURL + "/getHistoryByCrawlerId/" + taskId;
		List histroy = restTemplate.getForEntity(url, List.class).getBody();
		if(histroy == null) {
			url = webServiceURL + "/getHistoryByCrawlerId/" + taskId;
			histroy = restTemplate.getForEntity(url, List.class).getBody();
		}
		*/
		List<History> histroys = new ArrayList<History>();
		History h1 = new History();
		h1.setCrawlerId(taskId);
		h1.setStartDate(new Date());
		h1.setStopDate(new Date());
		h1.setVersion(1);
		h1.setPageCrawledCount(100);
		histroys.add(h1);
		History h11 = new History();
		h11.setCrawlerId(taskId);
		h11.setStartDate(new Date());
		h11.setStopDate(new Date());
		h11.setVersion(1);
		h11.setPageCrawledCount(105);
		histroys.add(h11);
		History h111 = new History();
		h111.setCrawlerId(taskId);
		h111.setStartDate(new Date());
		h111.setStopDate(new Date());
		h111.setVersion(1);
		h111.setPageCrawledCount(109);
		histroys.add(h111);
		
		return histroys;
	}

	/**
	 * 设置任务状态和采集数量
	 */
	private void setTasksStatusAndCount(List<Task> tasks) {
		for (Task task : tasks) {
			// 设置任务状态
			switch (task.getTaskState()) {
			case TaskStatus.READY:
				task.setTaskState(TaskStatus.READY_TO);
				break;
			case TaskStatus.RUNNING:
				task.setTaskState(TaskStatus.RUNNING_TO);
				break;
			case TaskStatus.STOP:
				task.setTaskState(TaskStatus.STOP_TO);
				break;
			default:
				task.setTaskState(TaskStatus.FORBIDDEN_TO);
				break;
			}
			
			// 设置任务采集数量
			String url = dbServiceURL + "/getCountByCrawlerId/" + task.getTaskId();
			int count = restTemplate.getForEntity(url, Integer.class).getBody();
			if(count == 0) {
				url = webServiceURL + "/getCountByCrawlerId/" + task.getTaskId();
				count = restTemplate.getForEntity(url, Integer.class).getBody();
			}
			task.setCount(count);
		}
	}

	public List<Metadata> getMetadataByTaskId(String taskId) {
		String url = dbServiceURL + "/getMetadataByCrawlerId/" + taskId;
		List metadatas = restTemplate.getForEntity(url, List.class).getBody();
		if(metadatas == null) {
			url = webServiceURL + "/getMetadataByCrawlerId/" + taskId;
			metadatas = restTemplate.getForEntity(url, List.class).getBody();
		}
		return metadatas;
	}
}
