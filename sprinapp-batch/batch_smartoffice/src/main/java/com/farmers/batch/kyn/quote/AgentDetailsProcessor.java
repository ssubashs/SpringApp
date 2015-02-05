package com.farmers.batch.kyn.quote;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentdetail;

@Service("agentDetailsProcessor")
public class AgentDetailsProcessor implements ItemProcessor<Agentdetail,Agentdetail>{

	
	@Override
	public Agentdetail process(final Agentdetail agentdetail) throws Exception {
		System.out.println(" Agentdetail  Processing..." + agentdetail.toString());
			
		return agentdetail;
	}


}
