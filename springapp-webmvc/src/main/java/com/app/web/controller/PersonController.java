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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.domain.Contact;
import com.app.domain.Person;
import com.app.service.ContactRepo;
import com.app.service.CustomerRepo;

@Controller
@SessionAttributes("personid")
public class PersonController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@RequestMapping(value={"/customer/edit"}, method=RequestMethod.POST)
	public String addCustomer(@ModelAttribute(value="person") Person person, final BindingResult bindingResult, Model model)
	{
		log.debug("addCustomer submit request handler");
		System.out.println("\n\nAdded customer through MVC.\n\n");
		Person savedperson = customerRepo.save(person);
		model.addAttribute("personid", savedperson.getPersonid());
		model.addAttribute("person", person);
		Contact contact = null;
		List<Contact> contacts = contactRepo.getContactforPerson(savedperson.getPersonid());
		if(contacts==null || contacts.size()<=0)
			{
				contact =  new Contact();
				contact.setPersonid(person.getPersonid());
			}
		else
			{
				contact = contacts.get(0);
			}
		
		model.addAttribute("contact",contact);
		return "customer/addContact";
	}
	
	
	@RequestMapping(value="/customer/edit/{personid}", method=RequestMethod.GET)
	public String editCustomer(Model model,@PathVariable Integer personid)
	{
		log.debug("Edit submit request handler");
		model.addAttribute("personid", personid);
		Person person = customerRepo.findOne(personid);
		model.addAttribute("person",person );
//		if(person.getContacts()==null)
//		{
//			Contact contact = new Contact();
//			contact.setPersonid(personid);
//			model.addAttribute("contact", contact);
//			
//		}
//		else
//			model.addAttribute("contact", person.getContacts().get(0));	
		
		return "customer/addPerson";
	}
	
	
}
