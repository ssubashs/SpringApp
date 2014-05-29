package com.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@NamedQuery(name="contact.findAll", query="SELECT h FROM Contact h")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int contactid;

	@Column(insertable=false, updatable=false)
	private int personid;

	private String addline1;

	private String addline2;

	private String city;

	private String ctype;

	private String email;

	private String hphone;

	private String mphone;

	private String state;

	private String wphone;

	private String zip;

	//bi-directional many-to-one association to Hperson
	@ManyToOne
	@JoinColumn(name="personid",insertable = false, updatable = false)
	@JsonBackReference
	private Person person;

	public Contact() {
	}
	
	

//	public HcontactPK getId() {
//		return this.id;
//	}
//
//	public void setId(HcontactPK id) {
//		this.id = id;
//	}

	public int getContactid() {
		return contactid;
	}



	public void setContactid(int contactid) {
		this.contactid = contactid;
	}



	public int getPersonid() {
		return personid;
	}



	public void setPersonid(int personid) {
		this.personid = personid;
	}



	public String getAddline1() {
		return this.addline1;
	}

	public void setAddline1(String addline1) {
		this.addline1 = addline1;
	}

	public String getAddline2() {
		return this.addline2;
	}

	public void setAddline2(String addline2) {
		this.addline2 = addline2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHphone() {
		return this.hphone;
	}

	public void setHphone(String hphone) {
		this.hphone = hphone;
	}

	public String getMphone() {
		return this.mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWphone() {
		return this.wphone;
	}

	public void setWphone(String wphone) {
		this.wphone = wphone;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}