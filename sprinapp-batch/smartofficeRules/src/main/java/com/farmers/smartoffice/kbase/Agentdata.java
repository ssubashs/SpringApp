package com.farmers.smartoffice.kbase;

public class Agentdata {
	private String agentNumber;
	private String state;
	private String district;
	private Double quotes;
	private Double retention;
	private Double newBusiness;
	private Double closerate;
	public String getAgentNumber() {
		return agentNumber;
	}
	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Double getQuotes() {
		return quotes;
	}
	public void setQuotes(Double quotes) {
		this.quotes = quotes;
	}
	public Double getRetention() {
		return retention;
	}
	public void setRetention(Double retention) {
		this.retention = retention;
	}
	public Double getNewBusiness() {
		return newBusiness;
	}
	public void setNewBusiness(Double newBusiness) {
		this.newBusiness = newBusiness;
	}
	public Double getCloserate() {
		return closerate;
	}
	public void setCloserate(Double closerate) {
		this.closerate = closerate;
	}
	
	@Override
	public String toString() {
		return "Agentdata [agentNumber=" + agentNumber + ", state=" + state
				+ ", district=" + district + ", quotes=" + quotes
				+ ", retention=" + retention + ", newBusiness=" + newBusiness
				+ ", closerate=" + closerate + "]";
	}
	
	
}
