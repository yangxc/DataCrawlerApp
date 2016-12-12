package com.peraglobal.datacrawlerapp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.peraglobal.datacrawlerapp.task.model.TaskGroup;

@Service
public class TaskGroupService {

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
	public TaskGroup getGroupById(Long id) {
		for (TaskGroup group : this.taskGroups()) {
			if (group.getGroupId()==id) {
				return group;
			}
		}
		return null;
	}
	
	/**
	 * 创建采集任务分组
	 */
	public void createGroup(TaskGroup taskGroup) {}
	
	/**
	 * 根据分组标识得到当前分组下任务的总数
	 * @param groupId
	 */
	public void getTaskCountByGroup(Long groupId) {
		
	}
	
	private List<TaskGroup> taskGroups() {
		List<TaskGroup> groups = new ArrayList<>();
		TaskGroup taskGroup1 = new TaskGroup();
		taskGroup1.setGroupId(1L);
		taskGroup1.setGroupName("期刊类");
		TaskGroup taskGroup2 = new TaskGroup();
		taskGroup2.setGroupId(2L);
		taskGroup2.setGroupName("新闻类");
		TaskGroup taskGroup3 = new TaskGroup();
		taskGroup3.setGroupId(3L);
		taskGroup3.setGroupName("石油钻探期刊");
		TaskGroup taskGroup4 = new TaskGroup();
		taskGroup4.setGroupId(4L);
		taskGroup4.setGroupName("学术期刊");
		TaskGroup taskGroup5 = new TaskGroup();
		taskGroup4.setGroupId(5L);
		taskGroup4.setGroupName("石油钻探期刊1-1");
		taskGroup1.addChild(taskGroup3);
		taskGroup1.addChild(taskGroup4);
		taskGroup3.addChild(taskGroup5);
		groups.add(taskGroup1);
		groups.add(taskGroup2);
		return groups;
	}
}
