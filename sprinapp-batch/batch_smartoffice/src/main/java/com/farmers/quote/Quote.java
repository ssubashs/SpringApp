package com.farmers.quote;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "quote")
public class Quote {
	
	private String agentNumber;
	private Date quoteDate;
	private String lineOfBusiness;
	private String source;
	private Integer quoteCount;
	private Integer quoteConversion;
	
	public Quote(String agentNumber,Date quoteDate,String lineOfBusiness)
	{
		this.agentNumber = agentNumber;
		this.quoteDate = quoteDate;
		this.lineOfBusiness =lineOfBusiness;
	}
	
	public Quote()
	{
		super();
	}
	
	@XmlElement(name = "agentNumber")
	public String getAgentNumber() {
		return agentNumber;
	}
	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}
	
	@XmlElement(name = "date")
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	
	@XmlAttribute(name = "type")
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	
	@XmlElement(name = "source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@XmlElement(name = "count")
	public Integer getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(Integer quoteCount) {
		this.quoteCount = quoteCount;
	}
	
	@XmlElement(name = "conversionCount")
	public Integer getQuoteConversion() {
		return quoteConversion;
	}
	public void setQuoteConversion(Integer quoteConversion) {
		this.quoteConversion = quoteConversion;
	}
	
	@Override
	public String toString() {
		return "Quote [agentNumber=" + agentNumber + ", quoteDate=" + quoteDate
				+ ", lineOfBusiness=" + lineOfBusiness + ", source=" + source
				+ ", quoteCount=" + quoteCount + ", quoteConversion="
				+ quoteConversion + "]";
	}
	
	

}
