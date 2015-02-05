package com.farmers.batch.kyn.quote;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmers.batch.kyn.AgentDetailsRepo;
import com.farmers.batch.kyn.Agentdetail;

@Component("jpaAgentDetailsWriter")
public class AgentDetailsWriter implements ItemWriter<Agentdetail> {

	@Autowired
	private AgentDetailsRepo agentdetailrepo;

	@Override
	public void write(List<? extends Agentdetail> agentdetails) throws Exception {
		System.out.println("Save the agent details count -- "+agentdetails.size());
		agentdetailrepo.save(agentdetails);
		agentdetailrepo.flush();
	}



}
