package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.datacrawlerapp.WebServiceProperties;
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
	public List<Task> getTasksByTaskStatus(String status) {
		String url = taskServiceURL + "task/getTasksByState/" + status;
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
	public List<Task> getTasks(int pageNo, int pageNum) {
		String url = taskServiceURL + "/task/getTasks/" + pageNo + 1;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

	public List<Task> getTasksByGroupId(String groupId) {
		String url = taskServiceURL + "/task/getTaskList/" + groupId;
		return restTemplate.getForEntity(url, List.class).getBody();
	}
	
	public boolean startTask(String taskIds) {
		String url = taskServiceURL + "task/start";
		String[] ids = taskIds.split(",");
		for (String string : ids) {
			if (string != null && !"".equals(string)) {
				Task task = new Task();
				task.setTaskId(string);
				restTemplate.put(url, String.class, task);
			}
		}
		return true;
	}


	
	
	/*public boolean createTask(Task task) {
		return this.tasks().add(task);
	}*/
	
	/**
	 * 根据任务分组id，得到当前任务分组下任务总数
	 * 
	 * @param groupId
	 
	public int getTaskCountByGroupId(String groupId) {
		List<Task> tasks = getTasksByGroupId(groupId);
		return tasks.isEmpty() ? 0 : tasks.size();
	}*/

	/**
	 * 根据任务分组得到当前任务分组下的任务
	 * 
	 * @param groupId
	 * @return
	 
	public List<Task> getTasksByGroupId(String groupId) {
		List<Task> results = new ArrayList<>();
		for (Task task : this.tasks()) {
			if (task.getGroupId().equals(groupId)) {
				results.add(task);
			}
		}
		return results;
	}*/
	
	/**
	 * 根据任务状态，获取对应的任务列表
	 * @param status 任务状态
	 * @return
	 
	public List<Task> getTasksByTaskStatus(String status) {
		List<Task> results = new ArrayList<>();
		for (Task task : this.tasks()) {
			if (task.getTaskState().toString().equals(status)) {
				results.add(task);
			}
		}
		return results;
	}*/
	
	/**
	 * 根据任务id，获取具体的任务
	 * @param taskId 任务id
	 * @return
	
	public Task getTaskById(String taskId) {
		for (Task task : this.tasks()) {
			if (task.getTaskId().equals(taskId)) {
				return task;
			}
		}
		return null;
	} */
	
	/**
	 * 根据任务id，删除对应的任务
	 * @param taskId 任务id
	 * @return 如果成功删除任务，返回true，否则返回false
	 
	public boolean removeTask(String taskId) {
		Task task = this.getTaskById(taskId);
		if (task == null) {
			return false;
		} else {
			return this.tasks().remove(task);
		}
	}*/
	
	/**
	 * 修改任务
	 * @param task
	 * @return
	 
	public boolean modifyTask(Task modifiedTask) {
		int index = -1;
		for (int i = 0; i < this.tasks().size(); i++) {
			if (this.tasks().get(i).getTaskId().equals(modifiedTask.getTaskId())) {
				index = i;
				break;
			}
		}
		if (index < 0) {
			return false;
		} else {
			this.tasks().set(index, modifiedTask);
			return true;
		}
	}*/

	/*private List<Task> tasks() {
		List<Task> tasks = new ArrayList<>();
		Task task = new Task();
		task.setGroupId("1");
		task.setTaskId("1");
		task.setTaskName("Task Name 1");
		task.setTaskState(TaskStatus.READY.toString());

		Task task2 = new Task();
		task2.setGroupId("1");
		task2.setTaskId("2");
		task2.setTaskName("Task Name 2");
		task2.setTaskState(TaskStatus.READY.toString());

		Task task3 = new Task();
		task3.setGroupId("1");
		task3.setTaskId("3");
		task3.setTaskName("Task Name 3");
		task3.setTaskState(TaskStatus.READY.toString());

		Task task4 = new Task();
		task4.setGroupId("1");
		task4.setTaskId("4");
		task4.setTaskName("Task Name 4");
		task4.setTaskState(TaskStatus.READY.toString());

		Task task5 = new Task();
		task5.setGroupId("1");
		task5.setTaskId("5");
		task5.setTaskName("Task Name 5");
		task5.setTaskState(TaskStatus.READY.toString());

		tasks.add(task);
		tasks.add(task2);
		tasks.add(task3);
		tasks.add(task4);
		tasks.add(task5);
		return tasks;
	}*/
	
	
}
