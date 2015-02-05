package com.farmers.batch.kyn.quote;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyRepo;

@Component("jpaMonthlyDataWriter")
public class MonthlyAgentDataWriter implements ItemWriter<Agentmonthly> {

	@Autowired
	private AgentmonthlyRepo repo;

	@Override
	public void write(List<? extends Agentmonthly> monthlydata) throws Exception {
		
		repo.save(monthlydata);
	}

	
}
