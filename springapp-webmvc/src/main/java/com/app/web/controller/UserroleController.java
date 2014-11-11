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

import com.app.domain.Userrole;
import com.app.service.ContactRepo;
import com.app.service.CustomerRepo;
import com.app.service.RoleRepo;

@Controller
@SessionAttributes({"personid","contactid","roleid"})
public class UserroleController {
	Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@RequestMapping(value="/customer/edit/{personid}/role", method=RequestMethod.POST)
	public String addUserrole(@ModelAttribute(value="userrole") Userrole userrole, @PathVariable Integer personid,final BindingResult bindingResult, Model model)
	{
		log.debug("addUserrole request handler");
		userrole.setPerson(customerRepo.findOne(personid));
		model.addAttribute("roleid",roleRepo.save(userrole).getRoleid());
		return "redirect:/app/customer";
	}
	
	
}
