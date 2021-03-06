package com.peraglobal.datacrawlerapp.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peraglobal.datacrawlerapp.crawler.model.DbConnection;
import com.peraglobal.datacrawlerapp.crawler.model.DbCrawler;
import com.peraglobal.datacrawlerapp.crawler.model.WebCrawler;
import com.peraglobal.datacrawlerapp.task.model.Status;
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
	

	@RequestMapping(value="/getTaskByStatus", method=RequestMethod.GET)
	public String getTaskByStatus(Model model, @RequestParam(value="status") String status) {
		
		// 左侧任务部分
		List<Status> statuses = taskService.getTaskStatuses();
		model.addAttribute("statuses", statuses);
		
		// 左侧任务分组部分
		model.addAttribute("groups", taskGroupService.getGroups());
		
		// 右侧任务列表
		model.addAttribute("tasks", taskService.getTasksByTaskStatus(status));
		return "crawler-layout";
	}
	
	/**
	 * 查看历史记录
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/history")
	public String history(Model model,  @RequestParam(value="taskId") String taskId) {
		// 左侧任务部分
		List<Status> statuses = taskService.getTaskStatuses();
		model.addAttribute("statuses", statuses);
		
		// 左侧任务分组部分
		model.addAttribute("groups", taskGroupService.getGroups());
		
		// 右侧历史记录列表
		model.addAttribute("historys", taskService.getHistoryByTaskId(taskId));
		return "history-layout";
	}
	
	/**
	 * 查看元数据
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/metadata")
	public String metadata(Model model,  @RequestParam(value="taskId") String taskId) {
		// 左侧任务部分
		List<Status> statuses = taskService.getTaskStatuses();
		model.addAttribute("statuses", statuses);
		
		// 左侧任务分组部分
		model.addAttribute("groups", taskGroupService.getGroups());
		
		// 右侧元数据列表
		model.addAttribute("metadatas", taskService.getMetadataByTaskId(taskId));
		return "metadata-layout";
	}
	
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/createTaskPage", method=RequestMethod.GET)
	public String createTaskCrawler(Model model, @RequestParam(value="groupId") String groupId) {
		model.addAttribute("groupId", groupId);
		model.addAttribute("groups", taskGroupService.getTaskGroupList());
		return "/task/createTask";
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@RequestMapping(value="/createWebCrawler", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> createWebCrawler(@RequestBody WebCrawler webCrawler) {
		Map<String, Object> reulst = new HashMap<String, Object>();
		try {
			taskService.createWebCrawler(webCrawler);
			reulst.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			reulst.put("success", "false");
		}
		return reulst;
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@RequestMapping(value="/createDbCrawler", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> createDbCrawler(@RequestBody DbCrawler dbCrawler) {
		Map<String, Object> reulst = new HashMap<String, Object>();
		try {
			taskService.createDbCrawler(dbCrawler);
			reulst.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			reulst.put("success", "false");
		}
		return reulst;
	}
	
	/**
	 * 删除任务
	 * @return
	 */
	@RequestMapping(value="/removeTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> removeTask(@RequestBody String taskIds) {
		Map<String, Object> reulst = new HashMap<String, Object>();
		try {
			taskService.removeTask(taskIds);
			reulst.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			reulst.put("success", "false");
		}
		return reulst;
	}
	
	/**
	 * 开始任务
	 * @return
	 */
	@RequestMapping(value="/startTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> startTask(@RequestBody String taskIds) {
		Map<String, Object> reulst = new HashMap<String, Object>();
		try {
			taskService.startTask(taskIds);
			reulst.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			reulst.put("success", "false");
		}
		return reulst;
	}
	
	/**
	 * 停止任务
	 * @return
	 */
	@RequestMapping(value="/stopTask", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> stopTask(@RequestBody String taskIds) {
		Map<String, Object> reulst = new HashMap<String, Object>();
		try {
			taskService.stopTask(taskIds);
			reulst.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			reulst.put("success", "false");
		}
		return reulst;
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getTables", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getTables(@RequestBody DbConnection dbConnection) {
		try {
			return taskService.getTables(dbConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建web采集任务
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getFields", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getFields(@RequestBody DbConnection dbConnection) {
		try {
			return taskService.getFields(dbConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据 groupId 查询任务集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getTasksByGroupId", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getTasksByGroupId(String groupId) {
		try {
			return taskService.getTasksByGroupId(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
