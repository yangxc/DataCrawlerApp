package com.peraglobal.datacrawlerapp.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	TaskService taskService;
	
	@Autowired
	TaskGroupService taskGroupService;
	
	
	/**
	 * 返回任务分组列表页面
	 * @return
	 */
	@RequestMapping(value="/taskgroups", method=RequestMethod.GET)
	public String getGroups(Model model, @RequestParam(value="groupId") String groupId) {
		
		List<TaskGroup> taskGroups = taskGroupService.getTaskGroupsByParentId(groupId);
		List<Task> tasks = taskService.getTasksByGroupId(groupId);
		// 当前组信息
		model.addAttribute("group", taskGroupService.getGroup(groupId));
		model.addAttribute("groups", taskGroupService.getGroups());
		model.addAttribute("groupId", groupId);
		model.addAttribute("taskGroups", taskGroups);
		model.addAttribute("groupNum", taskGroups == null ? 0 : taskGroups.size());
		model.addAttribute("taskNum", tasks == null ? 0 : tasks.size());
		// 获取任务状态和对应的任务数
		List<String> statuses = taskService.getTaskStatuses();
		Map<String, Integer> statusAndCount = new HashMap<String, Integer>();
		for (String status : statuses) {
			statusAndCount.put(status, taskService.getTasksByTaskStatus(status).size());
		}
		model.addAttribute("taskStatus", statuses);
		model.addAttribute("statusAdnCount", statusAndCount);
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
	 * @param childId 对应要修改的任务分组的id
	 * @return
	 */
	@RequestMapping(value = "/renameGroupPage", method = RequestMethod.GET)
	public String renameGroupPage(Model model, @RequestParam(value="groupId") String groupId) {
		return "/task/renameTaskGroupName";
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
