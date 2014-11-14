package com.app.web.flow;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.RequestContext;

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
	
	@Autowired
	private EntityManagerFactory emf;
	
	public Iterable<Person> getCustomers() {
		return customerRepo.findAll();
	}
	
	public List<String> getRoles() {
		return roleRepo.allRoles();
	}
	
	public List<Person> getCustomers(RequestContext requestContext)
	{
		String role = requestContext.getRequestParameters().get("role");
		String queryStr = "select role FROM Userrole role LEFT JOIN FETCH role.person where role.rolecd = :role";
		Query query = emf.createEntityManager().createQuery(queryStr);
		query.setParameter("role", role);
		List<Userrole>  users = query.getResultList();
		List<Person> customers = new ArrayList<Person>();
		for(Userrole user:users)
			customers.add(user.getPerson());
		
		return customers;
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
