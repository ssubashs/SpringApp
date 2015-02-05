package com.farmers.batch.kyn;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the reportview database table.
 * 
 */
@Entity
@NamedQuery(name="Reportview.findAll", query="SELECT r FROM Reportview r")
public class Reportview implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReportviewPK id;

	private BigDecimal agentdata;

	private String agentname;

	private String district;

	private BigDecimal districtdata;

	private String dmname;

	private String focusarea;

	private BigDecimal pastagentdata;

	private BigDecimal pastdistrictdata;

	private BigDecimal paststatedata;

	private String state;

	private BigDecimal statedata;

	public Reportview() {
	}

	public ReportviewPK getId() {
		return this.id;
	}

	public void setId(ReportviewPK id) {
		this.id = id;
	}

	public BigDecimal getAgentdata() {
		if(this.agentdata == null)
			this.agentdata = new BigDecimal(0);
		return this.agentdata;
	}

	public void setAgentdata(BigDecimal agentdata) {
		this.agentdata = agentdata;
	}

	public String getAgentname() {
		return this.agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public BigDecimal getDistrictdata() {
		if(this.districtdata == null)
			this.districtdata = new BigDecimal(0);
		return this.districtdata;
	}

	public void setDistrictdata(BigDecimal districtdata) {
		this.districtdata = districtdata;
	}

	public String getDmname() {
		return this.dmname;
	}

	public void setDmname(String dmname) {
		this.dmname = dmname;
	}

	public String getFocusarea() {
		return this.focusarea;
	}

	public void setFocusarea(String focusarea) {
		this.focusarea = focusarea;
	}

	public BigDecimal getPastagentdata() {
		if(this.pastagentdata == null)
			this.pastagentdata = new BigDecimal(0);
		return this.pastagentdata;
	}

	public void setPastagentdata(BigDecimal pastagentdata) {
		this.pastagentdata = pastagentdata;
	}

	public BigDecimal getPastdistrictdata() {
		if(this.pastdistrictdata == null)
			this.pastdistrictdata = new BigDecimal(0);
		return this.pastdistrictdata;
	}

	public void setPastdistrictdata(BigDecimal pastdistrictdata) {
		this.pastdistrictdata = pastdistrictdata;
	}

	public BigDecimal getPaststatedata() {
		if(this.paststatedata == null)
			this.paststatedata = new BigDecimal(0);
		return this.paststatedata;
	}

	public void setPaststatedata(BigDecimal paststatedata) {
		this.paststatedata = paststatedata;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getStatedata() {
		if(this.statedata == null)
			this.statedata = new BigDecimal(0);
		return this.statedata;
	}

	public void setStatedata(BigDecimal statedata) {
		this.statedata = statedata;
	}

}