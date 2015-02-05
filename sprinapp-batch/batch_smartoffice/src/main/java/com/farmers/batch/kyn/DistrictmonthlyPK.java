package com.farmers.batch.kyn;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the districtmonthly database table.
 * 
 */
@Embeddable
public class DistrictmonthlyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String state;
	
	private String district;
	
	private String lob;

	private int year;

	private int month;

	public DistrictmonthlyPK() {
	}
	public String getDistrict() {
		return this.district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((lob == null) ? 0 : lob.hashCode());
		result = prime * result + month;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + year;
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
		DistrictmonthlyPK other = (DistrictmonthlyPK) obj;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (lob == null) {
			if (other.lob != null)
				return false;
		} else if (!lob.equals(other.lob))
			return false;
		if (month != other.month)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DistrictmonthlyPK [state=" + state + ", district=" + district
				+ ", lob=" + lob + ", year=" + year + ", month=" + month + "]";
	}
	
	

}