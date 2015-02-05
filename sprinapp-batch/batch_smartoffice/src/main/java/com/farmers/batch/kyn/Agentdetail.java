package com.farmers.batch.kyn;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * The persistent class for the agentdetails database table.
 * 
 */
@Entity
@Table(name="agentdetails")
@NamedQuery(name="Agentdetail.findAll", query="SELECT a FROM Agentdetail a")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Agentdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userid;

	private String agencyname;

	private String agentNum;

	private String agenttype;

	private String dmfirstname;

	private String dmlastname;

	private String email;

	private String firstname;

	private String lastname;

	private String status;

	public Agentdetail() {
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAgencyname() {
		return this.agencyname;
	}

	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}

	public String getAgentNum() {
		return this.agentNum;
	}

	public void setAgentNum(String agentNum) {
		this.agentNum = agentNum;
	}

	public String getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(String agenttype) {
		this.agenttype = agenttype;
	}

	public String getDmfirstname() {
		return this.dmfirstname;
	}

	public void setDmfirstname(String dmfirstname) {
		this.dmfirstname = dmfirstname;
	}

	public String getDmlastname() {
		return this.dmlastname;
	}

	public void setDmlastname(String dmlastname) {
		this.dmlastname = dmlastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agencyname == null) ? 0 : agencyname.hashCode());
		result = prime * result
				+ ((agentNum == null) ? 0 : agentNum.hashCode());
		result = prime * result
				+ ((agenttype == null) ? 0 : agenttype.hashCode());
		result = prime * result
				+ ((dmfirstname == null) ? 0 : dmfirstname.hashCode());
		result = prime * result
				+ ((dmlastname == null) ? 0 : dmlastname.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agentdetail other = (Agentdetail) obj;
		if (agencyname == null) {
			if (other.agencyname != null)
				return false;
		} else if (!agencyname.equals(other.agencyname))
			return false;
		if (agentNum == null) {
			if (other.agentNum != null)
				return false;
		} else if (!agentNum.equals(other.agentNum))
			return false;
		if (agenttype == null) {
			if (other.agenttype != null)
				return false;
		} else if (!agenttype.equals(other.agenttype))
			return false;
		if (dmfirstname == null) {
			if (other.dmfirstname != null)
				return false;
		} else if (!dmfirstname.equals(other.dmfirstname))
			return false;
		if (dmlastname == null) {
			if (other.dmlastname != null)
				return false;
		} else if (!dmlastname.equals(other.dmlastname))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agentdetail [userid=" + userid + ", agencyname=" + agencyname
				+ ", agentNum=" + agentNum + ", agenttype=" + agenttype
				+ ", dmfirstname=" + dmfirstname + ", dmlastname=" + dmlastname
				+ ", email=" + email + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", status=" + status + "]";
	}
	
	

}