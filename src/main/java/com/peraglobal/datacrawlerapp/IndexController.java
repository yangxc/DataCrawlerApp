package com.peraglobal.datacrawlerapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

/**
 *  <code>IndexController.java</code>
 *  <p>功能:任务对象
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-2
 *  </br>最后修改人 无
 */
@Controller
public class IndexController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskGroupService taskGroupService;
	

	/**
	 * 程序入口
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
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
		return "index";
	}
	
}
