package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.datacrawlerapp.WebServiceProperties;
import com.peraglobal.datacrawlerapp.crawler.model.Crawler;
import com.peraglobal.datacrawlerapp.crawler.model.DbConnection;
import com.peraglobal.datacrawlerapp.crawler.model.DbCrawler;
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
	private final String TaskServiceName = "task-manager";
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient balancerClient;

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
		// String url = taskServiceURL;
		String url = getTaskManagerUri();
		if ("all".equals(status)) { // 全部分类
			url += "/task/getTasks/" + 0 + 1;
		}else {
			url += "/task/getTasksByState/" + status;
		}
		System.out.println("uri=" + url);
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
		// String url = taskServiceURL + "/task/getTasks/" + pageNo + 1;
        String url = getTaskManagerUri() + "/task/getTasks/" + pageNo + 1;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

	/**
	 * 根据组 Id 查询任务列表
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getTasksByGroupId(String groupId) {
//		String url = taskServiceURL + "/task/getTaskList/" + groupId;
        String url = getTaskManagerUri() + "/task/getTaskList/" + groupId;
		return restTemplate.getForEntity(url, List.class).getBody();
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
		// url = taskServiceURL + "/task/createTask/";
        url = getTaskManagerUri() + "/task/createTask/";
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
//		url = taskServiceURL + "/task/createTask/";
        url = getTaskManagerUri() + "/task/createTask/";
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
				// 删除任务
				String url = getTaskManagerUri() + "/task/removeTask/" + taskId;
				restTemplate.delete(url);
				
				// 删除 db
				url = dbServiceURL + "/db/removeCrawler/" + taskId;
				restTemplate.delete(url);
				
				// 删除 web
				url = webServiceURL + "/web/removeWeb/" + taskId;
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
		String url = getTaskManagerUri() + "/task/start";
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
		String url = getTaskManagerUri() + "/task/stop";
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

	@SuppressWarnings("rawtypes")
	public List getHistoryByTaskId(String taskId) {
		
		String url = dbServiceURL + "/getCrawler/" + taskId;
		Crawler crawler = (Crawler)restTemplate.getForEntity(url, Crawler.class).getBody();
		
		if (crawler != null) {
			url = dbServiceURL + "/getHistoryByCrawlerId/" + taskId;
			return restTemplate.getForEntity(url, List.class).getBody();
		} else {
			url = webServiceURL + "/getHistoryByCrawlerId/" + taskId;
			return restTemplate.getForEntity(url, List.class).getBody();
		}
	}

	@SuppressWarnings("rawtypes")
	public List getMetadataByTaskId(String taskId) {
		String url = dbServiceURL + "/getCrawler/" + taskId;
		Crawler crawler = (Crawler)restTemplate.getForEntity(url, Crawler.class).getBody();
		if (crawler != null) {
			url = dbServiceURL + "/getMetadataByCrawlerId/" + taskId;
			return restTemplate.getForEntity(url, List.class).getBody();
		} else {
			url = webServiceURL + "/getMetadataByCrawlerId/" + taskId;
			return restTemplate.getForEntity(url, List.class).getBody();
		}
	}

	@SuppressWarnings("rawtypes")
	public List getTables(DbConnection dbConnection) {
		String url = dbServiceURL + "/getTables/";
		return (List)restTemplate.postForObject(url, dbConnection, List.class);
	}


	@SuppressWarnings("rawtypes")
	public List getFields(DbConnection dbConnection) {
		String url = dbServiceURL + "/getFields/";
		return (List)restTemplate.postForObject(url, dbConnection, List.class);
	}

	private String getTaskManagerUri() {
	    String uri = balancerClient.choose("crawler-app").getUri().toString();
	    return uri + "/taskmanager";
	    //return discoveryClient.getInstances(TaskServiceName).get(0).getUri().toString() + "/taskmanager";
        // return "http://task-manager" + "/taskmanager";
    }
}
