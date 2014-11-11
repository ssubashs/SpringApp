package com.app.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.domain.Contact;
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
	
	@RequestMapping(value="/customer/add",method=RequestMethod.POST)
	public String customerBase(Model model)
	{
		log.debug("Customer add request handler");
		model.addAttribute("person", new Person());
		return "customer/addPerson";
	}
	
	@RequestMapping(value="/customer/editPerson/{personid}", method=RequestMethod.GET)
	public String editCustomer(Model model,@PathVariable Integer personid)
	{
		log.debug("Edit submit request handler");
		model.addAttribute("personid", personid);
		Person person = customerRepo.findOne(personid);
		model.addAttribute("person",person );
		if(person.getContacts()==null)
			model.addAttribute("contact", new Contact());
		else
			model.addAttribute("contact", person.getContacts().get(0));	
		return "customer/addContact";
	}
}
