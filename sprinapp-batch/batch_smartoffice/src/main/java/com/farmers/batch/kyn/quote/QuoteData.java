package com.farmers.batch.kyn.quote;

import java.util.List;

public class QuoteData {
	
	private String agentOfRecord;
	private String district;
	private String division;
	private String state;
	private List<MonthlyPerformance> monthly;
	public String getAgentOfRecord() {
		return agentOfRecord;
	}
	public void setAgentOfRecord(String agentOfRecord) {
		this.agentOfRecord = agentOfRecord;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<MonthlyPerformance> getMonthly() {
		return monthly;
	}
	public void setMonthly(List<MonthlyPerformance> monthly) {
		this.monthly = monthly;
	}
	@Override
	public String toString() {
		return "QuoteData [agentOfRecord=" + agentOfRecord + ", district="
				+ district + ", division=" + division + ", state=" + state
				+ ", monthly=" + monthly + "]";
	}
	
	

}
