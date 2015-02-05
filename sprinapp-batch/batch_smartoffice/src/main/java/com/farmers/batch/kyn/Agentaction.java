package com.farmers.batch.kyn;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the agentactions database table.
 * 
 */
@Entity
@Table(name="agentactions")
@NamedQuery(name="Agentaction.findAll", query="SELECT a FROM Agentaction a")
public class Agentaction implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AgentactionPK id;

	private String description;

	public Agentaction() {
	}

	public AgentactionPK getId() {
		return this.id;
	}

	public void setId(AgentactionPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		Agentaction action = (Agentaction) super.clone();
		action.setId((AgentactionPK) action.getId().clone());
		return action;
		
	}

}