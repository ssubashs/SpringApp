package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentmonthly;

@Service("closeRateProcessor")
public class CloseRateProcessor implements ItemProcessor<Agentmonthly,Agentmonthly>{

	private static final Integer zero = new Integer(0);
	@Override
	public Agentmonthly process(final Agentmonthly data) throws Exception {
		System.out.println(" close rate Processing..." + data.toString());
			if(data!=null && data.getQuotes()!=null&& !zero.equals(data.getQuotes()) && data.getNewbusiness() !=null )
			{			
				double closerate = (double)data.getNewbusiness()/data.getQuotes();
				closerate= closerate*100;
				System.out.println("Close rate "+closerate);
				data.setCloserate(new BigDecimal(closerate,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
		return data;
	}


}
