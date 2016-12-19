package com.peraglobal.datacrawlerapp.task.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  <code>TaskGroup.java</code>
 *  <p>功能:任务分组对象,提供对web api数据结构的封装
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
public class TaskGroup {

	private String groupId;
	private String parentId;
	private String groupName;
	private List<TaskGroup> children = new ArrayList<>();
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
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
