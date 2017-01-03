package com.peraglobal.datacrawlerapp;

import java.util.ArrayList;
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
		// 左侧任务部分
		List<String> taskStatus = new ArrayList<String>();
		Map<String, String> statuses = taskService.getTaskStatuses();
		Map<String, Integer> statusAndCount = new HashMap<String, Integer>();
		for (String status : statuses.keySet()) {  
			taskStatus.add(status);
			statusAndCount.put(status, taskService.getTasksByTaskStatus(status).size());
		}
		model.addAttribute("taskStatus", taskStatus);
		model.addAttribute("statusAndCount", statusAndCount);
		
		// 左侧任务分组部分
		model.addAttribute("groups", taskGroupService.getGroups());
		
		// 右侧任务列表
		model.addAttribute("tasks", taskService.getTasks(0, 50));
		
		model.addAttribute("groupId", null);
		return "crawler-layout";
	}
	
}
