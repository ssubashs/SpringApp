package com.app.service;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.domain.Contact;

@RepositoryRestResource(collectionResourceRel = "mysql_contact", path = "address")
public interface ContactRepo  extends CrudRepository<Contact,Integer>, Serializable{
	


}
