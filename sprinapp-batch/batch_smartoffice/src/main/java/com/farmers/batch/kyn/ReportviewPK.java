package com.farmers.batch.kyn;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reportview database table.
 * 
 */
@Embeddable
public class ReportviewPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String agentnum;

	private Integer year;

	private Integer quarter;

	private String datacategory;

	private String lob;

	public ReportviewPK() {
	}
	
	public ReportviewPK(String agentnum,int year,int month,String datacategory,String lob) {
		this.agentnum = agentnum;
		this.year = year;
		this.quarter = this.getQuarter(month);
		this.datacategory = datacategory;
		this.lob=lob;
	}
	
	private Integer getQuarter(Integer month)
	{
		Integer tempquarter = 0; 
		switch(month)
		{
		case (1): 
			tempquarter= 1;
			break;
		case (2):
			tempquarter= 1;
		    break;
		case (3):
			tempquarter= 1;
		    break;
		case (4):
			tempquarter= 2;
		    break;
		case (5):
			tempquarter= 2;
		    break;
		case (6):
			tempquarter= 2;
		    break;
		case (7):
			tempquarter= 3;
		    break;
		case (8):
			tempquarter= 3;
		    break;
		case (9):
			tempquarter= 3;
		    break;
		case (10):
			tempquarter= 4;
		    break;
		case (11):
			tempquarter= 4;
		    break;
		case (12):
			tempquarter= 4;
		    break;
		
		    
		}
		
		return tempquarter;
	}
	
	public String getAgentnum() {
		return this.agentnum;
	}
	public void setAgentnum(String agentnum) {
		this.agentnum = agentnum;
	}
	public Integer getYear() {
		return this.year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getQuarter() {
		return this.quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public String getDatacategory() {
		return this.datacategory;
	}
	public void setDatacategory(String datacategory) {
		this.datacategory = datacategory;
	}
	public String getLob() {
		return this.lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReportviewPK)) {
			return false;
		}
		ReportviewPK castOther = (ReportviewPK)other;
		return 
			this.agentnum.equals(castOther.agentnum)
			&& (this.year == castOther.year)
			&& (this.quarter == castOther.quarter)
			&& this.datacategory.equals(castOther.datacategory)
			&& this.lob.equals(castOther.lob);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.agentnum.hashCode();
		hash = hash * prime + this.year;
		hash = hash * prime + this.quarter;
		hash = hash * prime + this.datacategory.hashCode();
		hash = hash * prime + this.lob.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "ReportviewPK [agentnum=" + agentnum + ", year=" + year
				+ ", quarter=" + quarter + ", datacategory=" + datacategory
				+ ", lob=" + lob + "]";
	}
	

	public String getkeystring() {
		return agentnum + year+ quarter + datacategory+ lob;
	}
	
}