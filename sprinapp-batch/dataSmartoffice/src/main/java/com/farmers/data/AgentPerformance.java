package com.farmers.data;

import java.util.List;

public class AgentPerformance {
	
	private String agentNmber;
	private String district;
	private String state;
	List<MonthlyPerformanceData> monthlyperformance;
	public String getAgentNmber() {
		return agentNmber;
	}
	public void setAgentNmber(String agentNmber) {
		this.agentNmber = agentNmber;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<MonthlyPerformanceData> getMonthlyperformance() {
		return monthlyperformance;
	}
	public void setMonthlyperformance(
			List<MonthlyPerformanceData> monthlyperformance) {
		this.monthlyperformance = monthlyperformance;
	}
	
	

}
