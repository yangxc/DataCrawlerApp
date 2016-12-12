package com.peraglobal.datacrawlerapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peraglobal.datacrawlerapp.task.service.TaskGroupService;

@Controller
public class IndexController {
	
	@Autowired
	TaskGroupService taskGroupService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("groups", taskGroupService.getGroups());
		return "index";
	}
	
	@RequestMapping("/createGroup")
	public String createGroup() {
		return "createGroup";
	}
}
