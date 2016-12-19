/**
 * 用于进行采集任务分组操作的控制器；
 */
package com.peraglobal.datacrawlerapp.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.peraglobal.datacrawlerapp.task.model.TaskGroup;
import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;

@Controller
@RequestMapping("/taskgroup")
public class TaskGroupController {
	
	@Autowired
	TaskGroupService taskGroupService;
	
	/**
	 * 返回任务分组列表页面
	 * @return
	 */
	@RequestMapping(value="/taskgroups", method=RequestMethod.GET)
	public String getGroups(Model model, @RequestParam(value="groupId") String groupId) {
		model.addAttribute("groups", taskGroupService.getGroups());
		model.addAttribute("group", taskGroupService.getGroupById(groupId));
		model.addAttribute("groupId", groupId);
		return "/task/taskGroups";
	}
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/creategroup", method=RequestMethod.GET)
	public String createGroup() {
		return "/task/createTaskGroup";
	}
	
	/**
	 * 保存任务分组
	 * @return
	 */
	@RequestMapping(value="/savegroup", method=RequestMethod.POST)
	public String saveGroup() {
		taskGroupService.createGroup(null);
		return "redirect:/";
	}
	
	/**
	 * 删除对应的任务分组
	 * @return
	 */
	@RequestMapping(value="/modifyTaskGroup", method=RequestMethod.POST, params={"deleteGroup"})
	public String deleteGroup(HttpServletRequest request, Model model, @RequestParam(value="groupId") String groupId) {
		String[] groupIds = request.getParameterValues("childId");
		for (String id : groupIds) {
			taskGroupService.deleteTaskGroup(id);
		}
		model.addAttribute("childIds", groupIds);
		model.addAttribute("groups", taskGroupService.getGroups());
		model.addAttribute("group", taskGroupService.getGroupById(groupId));
		model.addAttribute("groupId", groupId);
		return "/task/taskGroups";
	}
	
	/**
	 * 显示任务分组重命名页面
	 * @param groupId 对应要修改的任务分组的父任务分组id
	 * @param childId 对应要修改的任务分组的id
	 * @return
	 */
	@RequestMapping("renameGroupPage")
	public String renameGroupPage(HttpServletRequest request, Model model, 
			@RequestParam(value="groupId") String groupId, @RequestParam(value="childId") String childId) {
		return "renameTaskGroupName";
	}
	
	/**
	 * 重命名对应的任务分组
	 * @return
	 */
	@RequestMapping(value="/modifyTaskGroup", method=RequestMethod.POST, params={"renameGroup"})
	public String renameGroup() {
		taskGroupService.renameTaskGroup(null);
		return "/task/taskGroups";
	}
}
