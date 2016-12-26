/**
 * 用于进行采集任务相关操作的控制器；
 */
package com.peraglobal.datacrawlerapp.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

@Controller
@RequestMapping("/task")
public class CrawlerTaskController {

	@Autowired
	TaskGroupService taskGroupService;
	@Autowired
	TaskService taskService;

	@RequestMapping("/status/{statusName}")
	public String getTaskListByStatus(@PathVariable String statusName, Model model) {
		// 获取任务分组列表
		model.addAttribute("groups", taskGroupService.getGroups());
		// 获取任务列表
		model.addAttribute("tasks", taskService.getTasksByTaskStatus(statusName));
		// 获取任务状态和对应的任务数
		List<String> statuses = taskService.getTaskStatuses();
		Map<String, Integer> statusAndCount = new HashMap<String, Integer>();
		for (String status : statuses) {
			statusAndCount.put(status, taskService.getTasksByTaskStatus(status).size());
		}
		model.addAttribute("taskStatus", statuses);
		model.addAttribute("statusAdnCount", statusAndCount);
		return "index";
	}
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/createCrawlerTask", method=RequestMethod.GET)
	public String createCrawlerTask(Model model, @RequestParam(value="groupId") String groupId) {
		return "/task/createCrawlerTask";
	}
}
