package com.app.domain;

import java.io.Serializable;

public class TestDomain implements Serializable
{
	private String firstname;
	private String lastname;
	private Integer age;
	
	public TestDomain(){};
	
//	public TestDomain(String fname,String lname,Integer age)
//	{
//		this.firstname=fname;
//		this.lastname=lname;
//		this.age=age;
//	}
	
	public String mystatus()
	{
		return "Always happy";
	}
	
	private String myagepref()
	{
		return "Don't show age";
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
