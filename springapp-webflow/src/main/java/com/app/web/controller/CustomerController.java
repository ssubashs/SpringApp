package com.app.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.domain.Person;
import com.app.service.CustomerRepo;
import com.app.service.RoleRepo;

@Controller
public class CustomerController 
{
	Logger log = LoggerFactory.getLogger(CustomerController.class);	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private RoleRepo rolesRepo;
	
	@RequestMapping("/customer")
	public String customerHome(Model model)
	{
		log.debug("Customer request handler");
		Iterable<Person> customers = customerRepo.findAll();
		List<String> roles =  rolesRepo.allRoles();
		model.addAttribute("roles",roles);
		model.addAttribute("customers",customers);
		return "customer/home";
	}
	
	@RequestMapping("/customer/add")
	public String addCustomer(Model model)
	{
		log.debug("Customer request handler");		
		List<String> roles =  rolesRepo.allRoles();
		model.addAttribute("roles",roles);		
		return "customer/add";
	}


}
