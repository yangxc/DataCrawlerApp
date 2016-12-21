package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
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
    private final String crawlerServiceURL;
    
    @Autowired
    public TaskGroupService(WebServiceProperties webServiceProperty) {
    	taskServiceURL = webServiceProperty.getTaskService();
    	crawlerServiceURL = webServiceProperty.getCrawlerService();
    }

	/**
	 * 得到任务分组列表
	 * @return
	 */
	public List<TaskGroup> getGroups() {
		return this.taskGroups();
	}
	
	/**
	 * 根据分组id得到一个具体的任务分组
	 * @return
	 */
	public TaskGroup getGroupById(String id) {
		//TaskGroup group = restTemplate.getForObject(null, TaskGroup.class);
		for (TaskGroup group : this.taskGroups()) {
			if (group.getChildren().size() != 0) {
				for (TaskGroup child: group.getChildren()) {
					if (child.getGroupId().equals(id)) {
						return child;
					}
				}
			} 
			if (group.getGroupId().equals(id)) {
				return group;
			}
		}
		return null;
	}
	
	/**
	 * 删除任务分组
	 * @param groupId
	 */
	public void deleteTaskGroup(String groupId) {
		
	}
	
	/**
	 * 对任务分组进行重命名
	 * @param taskGroup
	 */
	public void renameTaskGroup(TaskGroup taskGroup) {
		
	}
	
	/**
	 * 创建采集任务分组
	 */
	public void createGroup(TaskGroup taskGroup) {}
	
	private List<TaskGroup> taskGroups() {
		List<TaskGroup> groups = new ArrayList<>();
		TaskGroup taskGroup1 = new TaskGroup();
		taskGroup1.setGroupId("1");
		taskGroup1.setGroupName("期刊类");
		
		TaskGroup taskGroup2 = new TaskGroup();
		taskGroup2.setGroupId("2");
		taskGroup2.setGroupName("新闻类");
		
		TaskGroup taskGroup3 = new TaskGroup();
		taskGroup3.setGroupId("3");
		taskGroup3.setGroupName("石油钻探期刊");
		
		TaskGroup taskGroup4 = new TaskGroup();
		taskGroup4.setGroupId("4");
		taskGroup4.setGroupName("学术期刊");
		
		TaskGroup taskGroup5 = new TaskGroup();
		taskGroup5.setGroupId("5");
		taskGroup5.setGroupName("石油钻探期刊1-1");
		
		taskGroup1.addChild(taskGroup3);
		taskGroup1.addChild(taskGroup4);
		taskGroup3.addChild(taskGroup5);
		groups.add(taskGroup1);
		groups.add(taskGroup2);
		return groups;
	}
}
