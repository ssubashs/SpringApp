package com.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="person.findAll", query="SELECT person FROM Person person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer personid;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	private String firstname;

	private String lastname;

	private String middlename;
	
	private String username;
	
	private String password;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="person",fetch=FetchType.EAGER)
	@JsonManagedReference
	@Cascade(value = CascadeType.ALL)
	private List<Contact> contacts;
	
	
	//bi-directional many-to-one association to userrole
	@OneToMany(mappedBy="person",fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonManagedReference
	@Cascade(value = CascadeType.ALL)
	private List<Userrole> userroles;
	

	public Person() {
	}

	public Integer getPersonid() {
		return this.personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> hcontacts) {
		this.contacts = hcontacts;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Contact addContact(Contact hcontact) {
		getContacts().add(hcontact);
		hcontact.setPerson(this);

		return hcontact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setPerson(null);

		return contact;
	}
	
	public List<Userrole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(List<Userrole> userroles) {
		this.userroles = userroles;
	}

	public Userrole addUserrole(Userrole userrole) {
		if(getUserroles()==null)
			setUserroles(new ArrayList<Userrole>());
		
		getUserroles().add(userrole);
		userrole.setPerson(this);

		return userrole;
	}

	public Userrole removeUserrole(Userrole userrole) {
		if(getUserroles()!=null)
		getUserroles().remove(userrole);
		userrole.setPerson(null);

		return userrole;
	}
	

	
}