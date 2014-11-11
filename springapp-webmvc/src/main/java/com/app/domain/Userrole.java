package com.app.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;


/**
 * The persistent class for the userroles database table.
 * 
 */
@Entity
@Table(name="userroles")
@NamedQuery(name="Userrole.findAll", query="SELECT role FROM Userrole role")
public class Userrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleid;

	private String rolecd;

	private String status;

	//bi-directional many-to-one association to person
	@ManyToOne
	@JoinColumn(name="personid")
	@JsonBackReference
	private Person person;

	public Userrole() {
	}
	
	public Userrole(String rolecd,String status) {
		this.rolecd = rolecd;
		this.status = status;
	}

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolecd() {
		return this.rolecd;
	}

	public void setRolecd(String rolecd) {
		this.rolecd = rolecd;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}