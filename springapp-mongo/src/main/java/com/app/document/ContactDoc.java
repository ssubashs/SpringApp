package com.app.document;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contact")
public class ContactDoc implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

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

	public ContactDoc() {
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

	@Override
	public String toString() {
		return "ContactDoc [addline1=" + addline1 + ", addline2=" + addline2
				+ ", city=" + city + ", ctype=" + ctype + ", email=" + email
				+ ", hphone=" + hphone + ", mphone=" + mphone + ", state="
				+ state + ", wphone=" + wphone + ", zip=" + zip + "]";
	}	
	
	

}