package com.farmers.batch.kyn;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the agentactions database table.
 * 
 */
@Embeddable
public class AgentactionPK implements Serializable,Cloneable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String agentNum;

	private int year;

	private int month;

	private String lob;

	private String category;

	public AgentactionPK() {
	}
	public String getAgentNum() {
		return this.agentNum;
	}
	public void setAgentNum(String agentNum) {
		this.agentNum = agentNum;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return this.month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getLob() {
		return this.lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AgentactionPK)) {
			return false;
		}
		AgentactionPK castOther = (AgentactionPK)other;
		return 
			this.agentNum.equals(castOther.agentNum)
			&& (this.year == castOther.year)
			&& (this.month == castOther.month)
			&& this.lob.equals(castOther.lob)
			&& this.category.equals(castOther.category);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.agentNum.hashCode();
		hash = hash * prime + this.year;
		hash = hash * prime + this.month;
		hash = hash * prime + this.lob.hashCode();
		hash = hash * prime + this.category.hashCode();
		
		return hash;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}