package com.farmers.batch.kyn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the districtmonthly database table.
 * 
 */
@Entity
@Table(name ="districtmonthly")
@NamedQuery(name="Districtmonthly.findAll", query="SELECT d FROM Districtmonthly d")
public class Districtmonthly implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DistrictmonthlyPK id;

	private Long newbusiness;

	private Long pif;

	private Long quotes;
	
	private Long ytdnewbusiness;

	private Long ytdpif;

	private Long ytdquotes;
	
	private BigDecimal retention;
	
	private BigDecimal closerate;


	private Timestamp updatetime;

	public Districtmonthly() {
	}

	public DistrictmonthlyPK getId() {
		return this.id;
	}

	public void setId(DistrictmonthlyPK id) {
		this.id = id;
	}

	public Long getNewbusiness() {
		return this.newbusiness;
	}

	public void setNewbusiness(Long newbusiness) {
		this.newbusiness = newbusiness;
	}

	public Long getPif() {
		return this.pif;
	}

	public void setPif(Long pif) {
		this.pif = pif;
	}

	public Long getQuotes() {
		return this.quotes;
	}

	public void setQuotes(Long quotes) {
		this.quotes = quotes;
	}

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
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

	public BigDecimal getRetention() {
		return retention;
	}

	public void setRetention(BigDecimal retention) {
		this.retention = retention;
	}

	public BigDecimal getCloserate() {
		return closerate;
	}

	public void setCloserate(BigDecimal closerate) {
		this.closerate = closerate;
	}

	@Override
	public String toString() {
		return "Districtmonthly [id=" + id + ", newbusiness=" + newbusiness
				+ ", pif=" + pif + ", quotes=" + quotes + ", ytdnewbusiness="
				+ ytdnewbusiness + ", ytdpif=" + ytdpif + ", ytdquotes="
				+ ytdquotes + ", retention=" + retention + ", closerate="
				+ closerate + "]";
	}

	
	
	

	
}