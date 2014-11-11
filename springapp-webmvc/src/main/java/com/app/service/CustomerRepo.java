package com.app.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.domain.Person;

public interface CustomerRepo  extends CrudRepository<Person,Integer>, Serializable{
	
	public final static String authUser = "select loggedinPerson from Person loggedinPerson where loggedinPerson.username = :username and loggedinPerson.password = :password";
	public final static String loginUser = "select loggedinPerson from Person loggedinPerson where loggedinPerson.username = :username";
	
		@Query(authUser)
		public Person authUser(@Param("username") String username,@Param("password") String password);
		
		@Query(loginUser)
		public Person findLoggedUser(@Param("username") String username);
		

}
