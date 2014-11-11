package com.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.domain.Person;
import com.app.domain.Userrole;
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
	
	@Autowired
	private EntityManagerFactory emf;
	
	@RequestMapping("/customer")
	public String customerHome(Model model)
	{
		log.debug("Customer request handler");
		Iterable<Person> customers = customerRepo.findAll();
		model.addAttribute("heading","View All Customers");
		model.addAttribute("customers",customers);
		return "customer/home";
	}
	
	@RequestMapping("/customer/role/{role}")
	public String customerByRole(Model model,@PathVariable String role)
	{
		log.debug("Customer by role request handler");
	
		
		String queryStr = "select role FROM Userrole role LEFT JOIN FETCH role.person where role.rolecd = :role";
		Query query = emf.createEntityManager().createQuery(queryStr);
		query.setParameter("role", role);
		List<Userrole>  users = query.getResultList();
		List<Person> customers = new ArrayList<Person>();
		for(Userrole user:users)
			customers.add(user.getPerson());
		if(customers!=null && customers.size()>0)
		{
			model.addAttribute("heading","View Users with role - "+role);
			
		}
		else
			model.addAttribute("heading","No customer with role  - "+role);
		
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
	
	
	
	
	
}
