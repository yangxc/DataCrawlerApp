package com.peraglobal.datacrawlerapp;


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
	TaskGroupService taskGroupService;
	@Autowired
	TaskService taskService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("groups", taskGroupService.getGroups());
		model.addAttribute("tasks", taskService.getTasks(0, 50));
		return "index";
	}
	
	@RequestMapping("/createGroup")
	public String createGroup() {
		return "createGroup";
	}
}
