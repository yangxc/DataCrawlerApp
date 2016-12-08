package com.peraglobal.datacrawlerapp.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/taskgroup")
public class TaskGroupController {
	
	
	public void getGroups() {}
	
	/**
	 * 返回任务分组创建页面
	 * @return
	 */
	@RequestMapping(value="/creategroup", method=RequestMethod.GET)
	public String createGroup() {
		return "/task/createGroup";
	}
}
