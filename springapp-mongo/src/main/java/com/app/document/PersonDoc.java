package com.app.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class PersonDoc 
{
	@Id
	private String id;
	
	private String firstname;
	private String lastname;
	private String username;
	private List<String> role;
	
    @DBRef
	private ContactDoc contact;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
		
	public ContactDoc getContact() {
		return contact;
	}
	public void setContact(ContactDoc contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "PersonDoc [id=" + id + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", username=" + username
				+ ", role=" + role + ", contact=" + contact + "]";
	}
	
	
	
	
	

}
