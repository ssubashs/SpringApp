package com.farmers.batch.kyn;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the statemonthly database table.
 * 
 */
@Embeddable
public class StatemonthlyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="statecd")
	private String state;

	private String lob;
	
	private Integer year;

	private Integer month;

	public StatemonthlyPK() {
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
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
		if (!(other instanceof StatemonthlyPK)) {
			return false;
		}
		StatemonthlyPK castOther = (StatemonthlyPK)other;
		return 
			this.state.equals(castOther.state)
			&& (this.year == castOther.year)
			&& (this.month == castOther.month)
			&& (this.lob == castOther.lob);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.state.hashCode();
		hash = hash * prime + this.year;
		hash = hash * prime + this.month;
		hash = hash * prime + this.lob.hashCode();
		
		return hash;
	}
}