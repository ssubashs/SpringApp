package com.app.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.domain.Contact;
import com.app.domain.Person;
import com.app.service.CustomerRepo;

@Controller
@SessionAttributes("personid")
public class PersonController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@RequestMapping(value={"/customer/addPerson"}, method=RequestMethod.POST)
	public String addCustomer(@ModelAttribute(value="person") Person person, final BindingResult bindingResult, Model model)
	{
		log.debug("addCustomer submit request handler");
		System.out.println("\n\nAdded customer through MVC.\n\n");
		model.addAttribute("personid", customerRepo.save(person).getPersonid());
		model.addAttribute("person", person);
		model.addAttribute("contact", new Contact());
		return "customer/addContact";
	}
	
	
}
