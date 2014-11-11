package com.app.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.domain.Person;
import com.app.domain.Userrole;
import com.app.service.CustomerRepo;
import com.app.service.RoleRepo;



@Controller
@RequestMapping(value = "/customer")
public class CustomerRestController 
{
	@Autowired
	private CustomerRepo repository;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Person newperson(@RequestBody Person customer) 
	{
		return repository.save(customer);
	}
	
	/**
	 * Select an existing Hperson entity
	 * 
	 */
	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public Person loadperson(@PathVariable Integer customerId) {
		
		return repository.findOne(customerId);
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getAllRoles() {
		
		return roleRepo.allRoles();
	}
	
	/**
	 * Select an existing Hperson entity
	 * 
	 */
	@RequestMapping(value = "/{username}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Person authUser(@PathVariable String username,@PathVariable String password) {
		return repository.authUser(username, password);
	}
	
	/**
	 * Select an existing Hperson entity
	 * 
	 */
	@RequestMapping(value = "/loginname/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Person loggedUser(@PathVariable String username) {		
		return repository.findLoggedUser(username);
	}
	
	

	/**
	 * Delete an existing Hperson entity
	 * 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteperson(@PathVariable Integer id) {
	
		repository.delete(id);
	}


	
//	 @RequestMapping(method = RequestMethod.GET)
//	  public @ResponseBody CustomPage<Hperson> listpersons(
//	      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
//	      @RequestParam(value = "max", required = false, defaultValue = "10") int max) {
//		
//	    final ArrayList<Hperson> customerList = new java.util.ArrayList<Hperson>(repository.findAll());
//	    final int startIdx = (page - 1) * max;
//	    final int endIdx = Math.min(startIdx + max, customerList.size());
//	    
//	    return new CustomPage<Hperson>(customerList.subList(startIdx, endIdx), page, max, customerList.size());
//	  }
	 
	 /**
		 * Save an existing Hperson entity
		 * 
		 */
		@RequestMapping(method = RequestMethod.PUT)
		@ResponseBody
		public Person saveperson(@RequestBody Person customer) {
			return repository.save(customer);
		}
	
	

}
