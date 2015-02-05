package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyPK;

@Service("DataProcessor")
public class AgentDataProcessor implements ItemProcessor<QuoteData,Agentmonthly[]>{

	@Override
	public Agentmonthly[] process(final QuoteData data) throws Exception {
		System.out.println("Processing..." + data.toString());
		ArrayList<Agentmonthly> monthlydata = new ArrayList<Agentmonthly>();
		if(data!=null)
		{	
			for(final MonthlyPerformance monthly:data.getMonthly()){
				Agentmonthly eachmonth = new Agentmonthly();
				
				eachmonth.setId(new AgentmonthlyPK(data.getAgentOfRecord(),monthly.getYear(),Integer.parseInt(monthly.getMonth()),monthly.getLob()));
				eachmonth.setDistrict(data.getDistrict());
				eachmonth.setState(data.getState());
				
				if(monthly.getQuote()!=null)
					eachmonth.setQuotes(monthly.getQuote().intValue());
				if(monthly.getPif()!=null)
					eachmonth.setPif(monthly.getPif().intValue());	
				if(monthly.getNewbusiness() != null)
					eachmonth.setNewbusiness(monthly.getNewbusiness().intValue());
				if(monthly.getRetention() != null)
					eachmonth.setRetention(new BigDecimal(monthly.getRetention()).setScale(2, RoundingMode.HALF_UP));
				
				
				eachmonth.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				monthlydata.add(eachmonth);
			}
		}
		return monthlydata.toArray(new Agentmonthly[monthlydata.size()]);
	}

//	@Override
//	public Quote process(Quote input) throws Exception {		
//		System.out.println("Processing..." + input.toString());		
//		return input;
//	}

}
