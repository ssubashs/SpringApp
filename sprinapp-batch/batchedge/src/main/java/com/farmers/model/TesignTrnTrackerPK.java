package com.farmers.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tesign_trn_tracker database table.
 * 
 */
@Embeddable
public class TesignTrnTrackerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="HOUSEHOLD_NUM")
	private String householdNum;

	@Column(name="PLCY_CONTRACT_NUM")
	private String plcyContractNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INSERT_TIMESTAMP")
	private java.util.Date insertTimestamp;

	public TesignTrnTrackerPK() {
	}
	public String getHouseholdNum() {
		return this.householdNum;
	}
	public void setHouseholdNum(String householdNum) {
		this.householdNum = householdNum;
	}
	public String getPlcyContractNum() {
		return this.plcyContractNum;
	}
	public void setPlcyContractNum(String plcyContractNum) {
		this.plcyContractNum = plcyContractNum;
	}
	public java.util.Date getInsertTimestamp() {
		return this.insertTimestamp;
	}
	public void setInsertTimestamp(java.util.Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TesignTrnTrackerPK)) {
			return false;
		}
		TesignTrnTrackerPK castOther = (TesignTrnTrackerPK)other;
		return 
			this.householdNum.equals(castOther.householdNum)
			&& this.plcyContractNum.equals(castOther.plcyContractNum)
			&& this.insertTimestamp.equals(castOther.insertTimestamp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.householdNum.hashCode();
		hash = hash * prime + this.plcyContractNum.hashCode();
		hash = hash * prime + this.insertTimestamp.hashCode();
		
		return hash;
	}
}