package com.farmers.batch.kyn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the agentmonthly database table.
 * 
 */
@Entity
@Table(name = "agentmonthly")
@NamedQuery(name="Agentmonthly.findAll", query="SELECT a FROM Agentmonthly a")
public class Agentmonthly implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AgentmonthlyPK id;
	
	private String state;
	
	private String district;
	
	private Integer newbusiness;

	private Integer pif;

	private Integer quotes;
	
	private Long ytdnewbusiness;

	private Long ytdpif;

	private Long ytdquotes;

	private BigDecimal retention;
	
	private BigDecimal closerate;
	
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "updatetime", 
//	        updatable = false,
//	        columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//	@Column(name = "updatetime", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp updatetime;

	public Agentmonthly() {
	}

	public AgentmonthlyPK getId() {
		return this.id;
	}

	public void setId(AgentmonthlyPK id) {
		this.id = id;
	}
	
	public BigDecimal getCloserate() {
		return this.closerate;
	}

	public void setCloserate(BigDecimal closerate) {
		this.closerate = closerate;
	}

	public Integer getNewbusiness() {
		return this.newbusiness;
	}

	public void setNewbusiness(Integer newbusiness) {
		this.newbusiness = newbusiness;
	}

	public Integer getPif() {
		return this.pif;
	}

	public void setPif(Integer pif) {
		this.pif = pif;
	}

	public Integer getQuotes() {
		return this.quotes;
	}

	public void setQuotes(Integer quotes) {
		this.quotes = quotes;
	}

	public BigDecimal getRetention() {
		return this.retention;
	}

	public void setRetention(BigDecimal retention) {
		this.retention = retention;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
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
	
	

	public Long getYtdnewbusiness() {
		return ytdnewbusiness;
	}

	public void setYtdnewbusiness(Long ytdnewbusiness) {
		this.ytdnewbusiness = ytdnewbusiness;
	}

	public Long getYtdpif() {
		return ytdpif;
	}

	public void setYtdpif(Long ytdpif) {
		this.ytdpif = ytdpif;
	}

	public Long getYtdquotes() {
		return ytdquotes;
	}

	public void setYtdquotes(Long ytdquotes) {
		this.ytdquotes = ytdquotes;
	}

	@Override
	public String toString() {
		return "Agentmonthly [id=" + id.toString() + ", state=" + state + ", district="
				+ district + ", closerate=" + closerate + ", newbusiness="
				+ newbusiness + ", pif=" + pif + ", quotes=" + quotes
				+ ", ytdnewbusiness=" + ytdnewbusiness + ", ytdpif=" + ytdpif
				+ ", ytdquotes=" + ytdquotes + ", retention=" + retention
				+ ", updatetime=" + updatetime + "]";
	}

	

}