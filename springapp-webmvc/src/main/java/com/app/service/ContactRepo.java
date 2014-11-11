package com.app.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domain.Contact;

public interface ContactRepo  extends CrudRepository<Contact,Integer>, Serializable{

public final static String getContactforPerson = "select contact from Contact contact where contact.personid = :personid";

	
@Query(getContactforPerson)	
public List<Contact> getContactforPerson(@Param("personid")Integer personid);

}
