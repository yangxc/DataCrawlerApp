package com.peraglobal.datacrawlerapp.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.peraglobal.datacrawlerapp.WebServiceProperties;
import com.peraglobal.datacrawlerapp.task.model.TaskGroup;

@Service
public class TaskGroupService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private final String taskServiceURL;
    
    @Autowired
    public TaskGroupService(WebServiceProperties webServiceProperty) {
    	taskServiceURL = webServiceProperty.getTaskService();
    }

	/**
	 * 得到任务分组列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskGroup> getGroups() {
		// 获取根节点数据
		String url = taskServiceURL + "/group/getTaskGroupsByParentId/" + "0";
		return restTemplate.getForEntity(url, List.class).getBody();
	}
	
	/**
	 * 根据分组id得到一个具体的任务分组
	 * @return
	 */
	public TaskGroup getGroup(String groupId) {
		// 获取根节点数据
		String url = taskServiceURL + "/group/getTaskGroup/" + groupId;
		return restTemplate.getForEntity(url, TaskGroup.class).getBody();
	}
	
	/**
	 * 得到任务分组列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskGroup> getTaskGroupsByParentId(String groupId) {
		// 获取根节点数据
		String url = taskServiceURL + "/group/getTaskGroupsByParentId/" + groupId;
		return restTemplate.getForEntity(url, List.class).getBody();
	}
	
	/**
	 * 对任务分组进行重命名
	 * @param taskGroup
	 */
	public void modifyTaskGroup(TaskGroup taskGroup) {
		String url = taskServiceURL + "/group/editTaskGroup/";
		restTemplate.put(url, taskGroup);
		
	}
	
	/**
	 * 创建采集任务分组
	 */
	public void createTaskGroup(TaskGroup taskGroup) {
		String url = taskServiceURL + "/group/createTaskGroup/";
		restTemplate.postForEntity(url, taskGroup, String.class);
	}

	/**
	 * 删除任务分组
	 * @param groupId 组 ID
	 */
	public void removeTaskGroup(String groupId) {
		
		String[] groupIds = groupId.split(",");
		for (int i = 0; i < groupIds.length; i++) {
			if (groupIds[i] != null) {
				String url = taskServiceURL + "/group/removeTaskGroup/" + groupIds[i];
				restTemplate.delete(url);
			}
		}
		
		
	}
	
	
}
