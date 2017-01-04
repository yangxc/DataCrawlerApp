package com.peraglobal.datacrawlerapp.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peraglobal.datacrawlerapp.task.model.Status;
import com.peraglobal.datacrawlerapp.task.model.Task;
import com.peraglobal.datacrawlerapp.task.model.TaskGroup;
import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

/**
 * 用于进行采集任务分组操作的控制器；
 */
@Controller
@RequestMapping("/taskgroup")
public class TaskGroupController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskGroupService taskGroupService;
	
	
	/**
	 * 返回任务分组列表页面
	 * @return
	 */
	@RequestMapping(value="/taskgroups", method=RequestMethod.GET)
	public String getGroups(Model model, @RequestParam(value="groupId") String groupId) {
		
		// 左侧任务部分
		List<Status> statuses = taskService.getTaskStatuses();
		model.addAttribute("statuses", statuses);
		
		// 左侧任务分组部分
		model.addAttribute("groups", taskGroupService.getGroups());
		
		// 右侧任务分组信息
		model.addAttribute("group", taskGroupService.getGroup(groupId));
		model.addAttribute("groupId", groupId);
		List<TaskGroup> taskGroups = taskGroupService.getTaskGroupsByParentId(groupId);
		model.addAttribute("taskGroups", taskGroups);
		model.addAttribute("groupNum", taskGroups == null ? 0 : taskGroups.size());
		
		// 右侧任务信息
		List<Task> tasks = taskService.getTasksByGroupId(groupId);
		model.addAttribute("tasks", tasks);
		model.addAttribute("taskNum", tasks == null ? 0 : tasks.size());
		return "group-layout";
	}
	
	/**
	 * 返回任务分组创建页面
	 * @param groupId 对应要修改的任务分组的父任务分组id
	 * @return
	 */
	@RequestMapping(value = "/createTaskGroupPage", method = RequestMethod.GET)
	public String createTaskGroupPage(Model model, @RequestParam(value="groupId") String groupId) {
		if (!"0".equals(groupId)) {
			model.addAttribute("group", taskGroupService.getGroup(groupId));
		} else {
			TaskGroup group = new TaskGroup();
			group.setGroupId("0");
			model.addAttribute("group", group);
		}
		return "/task/createTaskGroup";
	}
	
	/**
	 * 保存任务分组
	 * @return
	 */
	@RequestMapping(value="/createTaskGroup", method=RequestMethod.POST)
	public @ResponseBody String createTaskGroup(@RequestBody TaskGroup taskGroup) {
		try {
			taskGroupService.createTaskGroup(taskGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * 移除组
	 * @param groupId 组 ID
	 * @return 
	 * @return 状态码
	 * @since 1.0
	 */
	@RequestMapping(value = "/removeTaskGroup", method = RequestMethod.POST)
	public @ResponseBody String removeTaskGroup(@RequestBody String groupId) {
		try {
			taskGroupService.removeTaskGroup(groupId);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	/**
	 * 显示任务分组重命名页面
	 * @param groupId 对应要修改的任务分组的父任务分组id
	 * @return
	 */
	@RequestMapping(value = "/renameGroupPage", method = RequestMethod.GET)
	public String renameGroupPage(Model model, @RequestParam(value="groupId") String groupId) {
		model.addAttribute("group", taskGroupService.getGroup(groupId));
		return "/task/renameTaskGroupName";
	}
	
	/**
	 * 重命名对应的任务分组
	 * @return
	 */
	@RequestMapping(value = "/modifyTaskGroup", method = RequestMethod.POST)
	public @ResponseBody String modifyTaskGroup(@RequestBody TaskGroup taskGroup) {
		try {
			taskGroupService.modifyTaskGroup(taskGroup);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
}
