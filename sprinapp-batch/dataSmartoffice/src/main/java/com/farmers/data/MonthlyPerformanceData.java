package com.farmers.data;

public class MonthlyPerformanceData {
	
	private Integer month;
	private Integer year;
	private PerformanceData performanceData;	
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public PerformanceData getPerformanceData() {
		return performanceData;
	}

	public void setPerformanceData(PerformanceData performanceData) {
		this.performanceData = performanceData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result
				+ ((performanceData == null) ? 0 : performanceData.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		MonthlyPerformanceData other = (MonthlyPerformanceData) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (performanceData == null) {
			if (other.performanceData != null)
				return false;
		} else if (!performanceData.equals(other.performanceData))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MonthlyPerformanceData [month=" + month + ", year=" + year
				+ ", performanceData=" + performanceData + "]";
	}
	
	

}
