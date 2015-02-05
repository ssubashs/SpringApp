package com.app.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.document.Person;





@Controller
@RequestMapping(value = "/document/person")
public class MongoCustomerRestController 
{
	@Autowired
	private MongoOperations mongoRepo;
	
	@RequestMapping(method = RequestMethod.POST)	
	public void newperson(@RequestBody Person customer) 
	{
		mongoRepo.save(customer);
	}
	
	/**
	 * Select an existing Hperson entity
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getAllPerson() {
		
		return mongoRepo.findAll(Person.class);
	}
	
//	/**
//	 * Select an existing Hperson entity
//	 * 
//	 */
//	@RequestMapping(value = "/{username}/{password}", method = RequestMethod.GET)
//	@ResponseBody
//	public Person authUser(@PathVariable String username,@PathVariable String password) {
//		return repository.authUser(username, password);
//	}
//	
//	/**
//	 * Select an existing Hperson entity
//	 * 
//	 */
//	@RequestMapping(value = "/loginname/{username}", method = RequestMethod.GET)
//	@ResponseBody
//	public Person loggedUser(@PathVariable String username) {		
//		return repository.findLoggedUser(username);
//	}
//	
//	
//
//	/**
//	 * Delete an existing Hperson entity
//	 * 
//	 */
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public void deleteperson(@PathVariable Integer id) {
//	
//		repository.delete(id);
//	}
//
//
//	
////	 @RequestMapping(method = RequestMethod.GET)
////	  public @ResponseBody CustomPage<Hperson> listpersons(
////	      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
////	      @RequestParam(value = "max", required = false, defaultValue = "10") int max) {
////		
////	    final ArrayList<Hperson> customerList = new java.util.ArrayList<Hperson>(repository.findAll());
////	    final int startIdx = (page - 1) * max;
////	    final int endIdx = Math.min(startIdx + max, customerList.size());
////	    
////	    return new CustomPage<Hperson>(customerList.subList(startIdx, endIdx), page, max, customerList.size());
////	  }
//	 
//	 /**
//		 * Save an existing Hperson entity
//		 * 
//		 */
//		@RequestMapping(method = RequestMethod.PUT)
//		@ResponseBody
//		public Person saveperson(@RequestBody Person customer) {
//			return repository.save(customer);
//		}
//	
	

}
