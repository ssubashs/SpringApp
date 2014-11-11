package com.app.web.controller;

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
	
	@RequestMapping(value="/customer/edit/{personid}/contact", method=RequestMethod.POST)
	public String addContact(@ModelAttribute(value="contact") Contact contact,@PathVariable Integer personid,final BindingResult bindingResult, Model model)
	{
		log.debug("addContact request handler");
		Person person =  customerRepo.findOne(personid);
		contact.setPerson(person);
		contact.setPersonid(personid);
		model.addAttribute("contactid", contactRepo.save(contact).getContactid());
	
		model.addAttribute("person",person);
		model.addAttribute("contact", contact);
		Userrole role = null;
		if(person.getUserroles()!=null && person.getUserroles().size()>0)
			role = person.getUserroles().get(0);
		else
		{
			role = new Userrole();
			role.setPerson(person);
			
		}
		model.addAttribute("userrole", role);
		return "customer/addUserrole";
	}
	
	
	// may not be used for now
	@RequestMapping(value="/customer/edit/{personid}/contact", method=RequestMethod.GET)
	public String editCustomerContact(Model model,@PathVariable Integer personid)
	{
		log.debug("Edit submit request handler");
		model.addAttribute("personid", personid);
		Person person = customerRepo.findOne(personid);
		model.addAttribute("person",person );
		if(person.getContacts()==null)
		{
			Contact contact = new Contact();
			contact.setPersonid(personid);
			model.addAttribute("contact", contact);
			
		}
		else
			model.addAttribute("contact", person.getContacts().get(0));	
		
		return "customer/addContact";
	}
	
	
}
