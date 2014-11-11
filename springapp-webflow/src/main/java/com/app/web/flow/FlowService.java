package com.app.web.flow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.Contact;
import com.app.domain.Person;
import com.app.domain.Userrole;
import com.app.service.ContactRepo;
import com.app.service.CustomerRepo;
import com.app.service.RoleRepo;

@Service("flowService")
public class FlowService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public Iterable<Person> getCustomers() {
		return customerRepo.findAll();
	}
	
	public List<String> getRoles() {
		return roleRepo.allRoles();
	}
	
	public void savePerson(Person person) {
		customerRepo.save(person);
	}
	
	public void saveContact(Contact contact) {
		contactRepo.save(contact);
	}
	
	public void saveUserrole(Userrole userrole) {
		roleRepo.save(userrole);
	}
	
}
