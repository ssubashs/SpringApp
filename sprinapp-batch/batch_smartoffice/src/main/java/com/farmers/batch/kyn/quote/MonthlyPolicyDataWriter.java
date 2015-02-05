package com.farmers.batch.kyn.quote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyRepo;

@Component("jpaMonthlyPolicyDataWriter")
public class MonthlyPolicyDataWriter implements ItemWriter<Agentmonthly[]> {

	@Autowired
	private AgentmonthlyRepo repo;

	@Override
	public void write(List<? extends Agentmonthly[]> list) throws Exception {
		
		List<Agentmonthly> monthlySave = new ArrayList<Agentmonthly>();
		for(Agentmonthly[] monthly:list)
		{
			monthlySave.addAll(Arrays.asList(monthly));
		}
		if(monthlySave.size()>0)
		{
			for(Agentmonthly monthlydata:monthlySave)
			{
//				manually merge data
				Agentmonthly fromdb = repo.findOne(monthlydata.getId());
				if(fromdb!=null)
				{
					fromdb.setNewbusiness(monthlydata.getNewbusiness()); 
					fromdb.setPif(monthlydata.getPif());
					fromdb.setUpdatetime(new Timestamp(System.currentTimeMillis()));
					fromdb.setRetention(monthlydata.getRetention());
					repo.save(fromdb);
				}
				else
				{
					monthlydata.setUpdatetime(new Timestamp(System.currentTimeMillis()));
					repo.save(monthlydata);
				}
			}
			
			
		}
	}

}
