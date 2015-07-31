package com.farmers.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tesign_trn_tracker database table.
 * 
 */
@Entity
@Table(name="tesign_trn_tracker")
public class TesignTrnTracker implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TesignTrnTrackerPK id;

	@Column(name="AGT_TRANS_TIMESTMP")
	private Timestamp agtTransTimestmp;

	@Column(name="BUSINESS_UNIT")
	private String businessUnit;

	@Lob
	@Column(name="ESIGN_TRANS_DATA")
	private byte[] esignTransData;

	@Column(name="ESIGN_TRANS_DATAID")
	private String esignTransDataid;

	@Temporal(TemporalType.DATE)
	@Column(name="EXP_DT")
	private Date expDt;

	@Column(name="LINE_OF_BUS_CODE")
	private String lineOfBusCode;

	@Column(name="TRANS_SRC_CD")
	private String transSrcCd;

	@Column(name="TRANS_STATUS")
	private String transStatus;

	@Column(name="UPDATE_TIMESTAMP")
	private Timestamp updateTimestamp;

	public TesignTrnTracker() {
	}

	public TesignTrnTrackerPK getId() {
		return this.id;
	}

	public void setId(TesignTrnTrackerPK id) {
		this.id = id;
	}

	public Timestamp getAgtTransTimestmp() {
		return this.agtTransTimestmp;
	}

	public void setAgtTransTimestmp(Timestamp agtTransTimestmp) {
		this.agtTransTimestmp = agtTransTimestmp;
	}

	public String getBusinessUnit() {
		return this.businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public byte[] getEsignTransData() {
		return this.esignTransData;
	}

	public void setEsignTransData(byte[] esignTransData) {
		this.esignTransData = esignTransData;
	}

	public String getEsignTransDataid() {
		return this.esignTransDataid;
	}

	public void setEsignTransDataid(String esignTransDataid) {
		this.esignTransDataid = esignTransDataid;
	}

	public Date getExpDt() {
		return this.expDt;
	}

	public void setExpDt(Date expDt) {
		this.expDt = expDt;
	}

	public String getLineOfBusCode() {
		return this.lineOfBusCode;
	}

	public void setLineOfBusCode(String lineOfBusCode) {
		this.lineOfBusCode = lineOfBusCode;
	}

	public String getTransSrcCd() {
		return this.transSrcCd;
	}

	public void setTransSrcCd(String transSrcCd) {
		this.transSrcCd = transSrcCd;
	}

	public String getTransStatus() {
		return this.transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public Timestamp getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}