package com.peraglobal.datacrawlerapp.task.model;

import java.util.ArrayList;
import java.util.List;

public class TaskGroup {

	private Long groupId;
	private Long parentId;
	private String groupName;
	private List<TaskGroup> children = new ArrayList<>();
	
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<TaskGroup> getChildren() {
		return children;
	}
	public void setChildren(List<TaskGroup> children) {
		this.children = children;
	}
	
	public void addChild(TaskGroup taskGroup) {
		this.children.add(taskGroup);
	}
	
}
