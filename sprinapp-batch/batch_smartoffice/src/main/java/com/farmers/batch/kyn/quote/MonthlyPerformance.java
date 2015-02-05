package com.farmers.batch.kyn.quote;

public class MonthlyPerformance {
	
	
	private Integer year;
	private String month;
	private String lob;
	private Double quote;
	private Double newbusiness;
	private Double pif;
	private Double retention;
	private Double yrtdQuote;
	private Double yrtdNb;
	private Double yrtdPif;
	
	public MonthlyPerformance(String lob, Integer year, String month)
	{
		this.lob = lob;
		this.year = year;
		this.month = month;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public Double getQuote() {
		return quote;
	}
	public void setQuote(Double quote) {
		this.quote = quote;
	}
	public Double getNewbusiness() {
		return newbusiness;
	}
	public void setNewbusiness(Double newbusiness) {
		this.newbusiness = newbusiness;
	}
	public Double getPif() {
		return pif;
	}
	public void setPif(Double pif) {
		this.pif = pif;
	}

	public Double getRetention() {
		return retention;
	}

	public void setRetention(Double retention) {
		this.retention = retention;
	}

	public Double getYrtdQuote() {
		return yrtdQuote;
	}

	public void setYrtdQuote(Double yrtdQuote) {
		this.yrtdQuote = yrtdQuote;
	}

	public Double getYrtdNb() {
		return yrtdNb;
	}

	public void setYrtdNb(Double yrtdNb) {
		this.yrtdNb = yrtdNb;
	}

	public Double getYrtdPif() {
		return yrtdPif;
	}

	public void setYrtdPif(Double yrtdPif) {
		this.yrtdPif = yrtdPif;
	}
	
	@Override
	public String toString() {
		return "MonthlyPerformance [year=" + year + ", month=" + month
				+ ", lob=" + lob + ", quote=" + quote + ", newbusiness="
				+ newbusiness + ", pif=" + pif +", retention="+retention;
	}


}
