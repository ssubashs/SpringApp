package com.farmers.batch.kyn;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the agentmonthly database table.
 * 
 */
@Embeddable
public class AgentmonthlyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String agentNum;

	private Integer year;

	private Integer month;
	
	private String lob;

	public AgentmonthlyPK(String agentNum,Integer year, Integer month, String lob) {
		this.agentNum = agentNum;
		this.year = year;
		this.month = month;
		this.lob = lob;
	}
	
	public AgentmonthlyPK()
	{
		
	}
	public String getAgentNum() {
		return this.agentNum;
	}
	public void setAgentNum(String agentNum) {
		this.agentNum = agentNum;
	}
	public Integer getYear() {
		return this.year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return this.month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}	

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AgentmonthlyPK)) {
			return false;
		}
		AgentmonthlyPK castOther = (AgentmonthlyPK)other;
		return 
			this.agentNum.equals(castOther.agentNum)
			&& (this.year == castOther.year)
			&& (this.month == castOther.month)
			&& (this.lob.equalsIgnoreCase(castOther.lob));
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.agentNum.hashCode();
		hash = hash * prime + this.lob.hashCode();
		hash = hash * prime + this.year;
		hash = hash * prime + this.month;
		
		return hash;
	}

	@Override
	public String toString() {
		return "AgentmonthlyPK [agentNum=" + agentNum + ", year=" + year
				+ ", month=" + month + ", lob=" + lob + "]";
	}
	
	
}