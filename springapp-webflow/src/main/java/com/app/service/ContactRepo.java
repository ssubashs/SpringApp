package com.app.service;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.app.domain.Contact;

public interface ContactRepo  extends CrudRepository<Contact,Integer>, Serializable{
	


}
