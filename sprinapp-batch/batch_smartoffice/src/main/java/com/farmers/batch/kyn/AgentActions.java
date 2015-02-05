package com.farmers.batch.kyn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AgentActions {
	
	private final List<Agentaction> actionList = Collections.synchronizedList(new ArrayList<Agentaction>());
	
	public Collection<Agentaction> getActions() {
        return Collections.unmodifiableCollection(actionList);
    }

    public void addAction(Object target, String category, String description) {
    try{	
    	if(target instanceof Agentmonthly )
    	{ 
    		Agentmonthly monthlydata = (Agentmonthly)target;
    		Agentaction agentaction = new Agentaction();
    		AgentactionPK actionkey = new AgentactionPK();
    		actionkey.setAgentNum(monthlydata.getId().getAgentNum());
    		actionkey.setLob(monthlydata.getId().getLob());
    		actionkey.setYear(monthlydata.getId().getYear());
    		actionkey.setMonth(monthlydata.getId().getMonth());
    		actionkey.setCategory(category);
    		agentaction.setId(actionkey);
    		agentaction.setDescription(description);
    		this.actionList.add(agentaction);
    	}
    	else if(target instanceof Reportview)
    	   	{ 
    		    Reportview reportView = (Reportview)target;
        		Agentaction agentaction = new Agentaction();
        		AgentactionPK actionkey = new AgentactionPK();
        		actionkey.setAgentNum(reportView.getId().getAgentnum());
        		actionkey.setLob(reportView.getId().getLob());
        		actionkey.setYear(reportView.getId().getYear());        		
        		actionkey.setCategory(category);
        		agentaction.setId(actionkey);
        		agentaction.setDescription(description);
        		
        		agentaction.getId().setMonth(this.getmonth(reportView.getId().getQuarter(), 1));
        		this.actionList.add(agentaction);
        		
        		Agentaction agentaction2 = (Agentaction) agentaction.clone();
        		agentaction2.getId().setMonth(this.getmonth(reportView.getId().getQuarter(), 2));
        		this.actionList.add(agentaction2);
        		
        		Agentaction agentaction3 = (Agentaction) agentaction.clone();
        		agentaction3.getId().setMonth(this.getmonth(reportView.getId().getQuarter(), 3));
        		this.actionList.add(agentaction3);
        	}
    }
    catch(CloneNotSupportedException cloneException)
    {
    	cloneException.printStackTrace();
    }
    }
    
    public Integer getmonth(Integer quarter, Integer month)
    {
    	return (quarter-1)*3+month;
    }

    public boolean hasActions() {
        if (this.actionList.size() > 0) {
            return true;
        }
        return false;
    }

}
