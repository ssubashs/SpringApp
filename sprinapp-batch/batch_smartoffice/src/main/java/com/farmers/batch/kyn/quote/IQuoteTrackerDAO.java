package com.farmers.batch.kyn.quote;

import java.util.List;

public interface IQuoteTrackerDAO {
	
	public List<QuoteData> findbyAOR(String agentOfRecord);
	public List<QuoteData> findbyDistrict(String district);
	
	

}
