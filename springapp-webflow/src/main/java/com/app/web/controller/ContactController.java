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
import com.app.domain.Userrole;
import com.app.service.ContactRepo;
import com.app.service.CustomerRepo;

@Controller
@SessionAttributes({"personid","contactid"})
public class ContactController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@RequestMapping(value="/customer/addContact", method=RequestMethod.POST)
	public String addContact(@ModelAttribute(value="contact") Contact contact, final BindingResult bindingResult, Model model)
	{
		log.debug("addContact request handler");
		model.addAttribute("contactid", contactRepo.save(contact).getContactid());
	//	model.addAttribute("person", customerRepo.findOne((Integer) model.asMap().get("personid")));
		Person person = customerRepo.findOne(contact.getPersonid());
		model.addAttribute("person", person);

		model.addAttribute("contact", contact);		
		model.addAttribute("userrole", new Userrole());
		return "customer/addUserrole";
	}
	
	
}
