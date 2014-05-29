package com.app.cxfrs.impls;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.cxfrs.defs.ICustomerServ;
import com.app.domain.Person;
import com.app.service.CustomerRepo;

public class CustomerServiceImpl implements ICustomerServ{

	
	@Autowired
	private CustomerRepo repository;
	
	@Override
	public Response getAllCustomer() {
		System.out.println("Get all customer jax-rs service endpoint");
		return Response.ok(repository.findAll()).build();
	}

	@Override
	public Person fetchbyId(Integer id) {
		System.out.println("Get customer by id jax-rs service endpoint");
		return repository.findOne(id);
	}

}
