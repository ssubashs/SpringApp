package com.farmers.batch.kyn;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the smartrules database table.
 * 
 */
@Entity
@Table(name="smartrules")
@NamedQuery(name="Smartrule.findAll", query="SELECT s FROM Smartrule s")
public class Smartrule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ruleid;

	private String category;

	@Column(name="package")
	private String rulepackage;

	@Lob
	private byte[] rule;

	private String rulename;

	private String type;

	public Smartrule() {
	}

	public int getRuleid() {
		return this.ruleid;
	}

	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String getRulepackage() {
		return rulepackage;
	}

	public void setRulepackage(String rulepackage) {
		this.rulepackage = rulepackage;
	}

	public byte[] getRule() {
		return this.rule;
	}

	public void setRule(byte[] rule) {
		this.rule = rule;
	}

	public String getRulename() {
		return this.rulename;
	}

	public void setRulename(String rulename) {
		this.rulename = rulename;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}