package com.peraglobal.datacrawlerapp.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;
import com.peraglobal.datacrawlerapp.task.service.TaskService;

/**
 *  <code>Task.java</code>
 *  <p>功能:数据传输控制器
 *  
 *  <p>Copyright 安世亚太 2017 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2017-3-2
 *  </br>最后修改人 无
 */
@Controller
@RequestMapping("/dataprocess")
public class DataProcessController {

	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskGroupService taskGroupService;
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/createDataProcessPage", method=RequestMethod.GET)
	public String createDataProcessPage(Model model, @RequestParam(value="groupId") String groupId) {
		model.addAttribute("groupId", groupId);
		model.addAttribute("groups", taskGroupService.getTaskGroupList());
		
		return "/dataprocess/createDataProcess";
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
}
