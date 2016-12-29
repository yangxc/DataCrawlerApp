package com.peraglobal.datacrawlerapp.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peraglobal.datacrawlerapp.crawler.model.DbCrawler;
import com.peraglobal.datacrawlerapp.crawler.model.WebCrawler;
import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

/**
 * 用于进行采集任务相关操作的控制器；
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskGroupService taskGroupService;
	

	@RequestMapping("/status/{statusName}")
	public String getTaskListByStatus(Model model, @PathVariable String statusName) {
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
		model.addAttribute("statusName", statusName);
		model.addAttribute("statusAdnCount", statusAndCount);
		return "index";
	}
	
	/**
	 * 程序入口
	 * @param model
	 * @return
	 */
	@RequestMapping("/history")
	public String history(Model model,  @RequestParam(value="taskId") String taskId) {
		// 获取任务分组列表
		model.addAttribute("groups", taskGroupService.getGroups());
		// 获取任务列表
		model.addAttribute("tasks", taskService.getTasks(0, 50));
		// 获取任务状态和对应的任务数
		List<String> statuses = taskService.getTaskStatuses();
		Map<String, Integer> statusAndCount = new HashMap<String, Integer>();
		for (String status : statuses) {
			statusAndCount.put(status, taskService.getTasksByTaskStatus(status).size());
		}
		model.addAttribute("taskStatus", statuses);
		model.addAttribute("statusAndCount", statusAndCount);
		model.addAttribute("groupId", null);
		return "/task/history";
	}
	
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/createTaskPage", method=RequestMethod.GET)
	public String createTaskCrawler(Model model, @RequestParam(value="groupId") String groupId) {
		model.addAttribute("groupId", groupId);
		model.addAttribute("groups", taskGroupService.getGroups());
		return "/task/createTask";
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@RequestMapping(value="/createWebCrawler", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createWebCrawler(@RequestBody WebCrawler webCrawler) {
		try {
			taskService.createWebCrawler(webCrawler);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@RequestMapping(value="/createDbCrawler", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createDbCrawler(@RequestBody DbCrawler dbCrawler) {
		try {
			taskService.createDbCrawler(dbCrawler);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * 删除任务
	 * @return
	 */
	@RequestMapping(value="/removeTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String removeTask(@RequestBody String taskIds) {
		try {
			taskService.removeTask(taskIds);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * 开始任务
	 * @return
	 */
	@RequestMapping(value="/startTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String startTask(@RequestBody String taskIds) {
		try {
			taskService.startTask(taskIds);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * 停止任务
	 * @return
	 */
	@RequestMapping(value="/stopTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String stopTask(@RequestBody String taskIds) {
		try {
			taskService.stopTask(taskIds);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
}
