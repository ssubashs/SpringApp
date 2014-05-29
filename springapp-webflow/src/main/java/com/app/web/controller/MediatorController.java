package com.app.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MediatorController {
	
	Logger log = LoggerFactory.getLogger(MediatorController.class);	
//	@RequestMapping(value = "/")
//	public String defaultRequest(Model model)
//	{
//		System.out.println("default mediator controller ");
//		return "redirect:/test";
//	}
	
	@RequestMapping("/test")
	public String testRequest(Model model)
	{
		log.debug("Test controller invoked");
		model.addAttribute("buildmessage","first build springmvc/thymeleaf");
		return "build";
	}

}
