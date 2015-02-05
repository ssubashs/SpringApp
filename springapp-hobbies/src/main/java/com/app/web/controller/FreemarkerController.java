package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class FreemarkerController 
{
	@RequestMapping(value="/{viewName}.ftl")
	public String renderFreemarkerTemplates(@PathVariable(value="viewName") String viewName,Model model)
	{
		System.out.println("Free marker models");
		return viewName;
	}


}
