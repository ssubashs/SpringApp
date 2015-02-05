package com.farmers.batch.kyn.quote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuoteTrackerDao implements IQuoteTrackerDAO{
	
	@Autowired
	@Qualifier("mfjdbcTemplate")
	private JdbcTemplate jdbctempate;
	
	private String selectbyaor = 
			"select * from DFBP32M.TWEB_QT2CONV_SUMRY quote ,"
			+ "(select AGENT_OF_REC,TRANS_YR,TRANS_MTH,RECORD_LEVEL_CD, max(INS_DTSTMP) as INS_DTSTMP from DFBP32M.TWEB_QT2CONV_SUMRY group by AGENT_OF_REC,TRANS_YR,TRANS_MTH,RECORD_LEVEL_CD) as sublist"
			+ " where quote.AGENT_OF_REC = sublist.AGENT_OF_REC and quote.TRANS_YR = sublist.TRANS_YR and quote.TRANS_MTH = sublist.TRANS_MTH and quote.RECORD_LEVEL_CD = sublist.RECORD_LEVEL_CD and quote.INS_DTSTMP = sublist.INS_DTSTMP "
			+ " and quote.RECORD_LEVEL_CD = 'A' and quote.AGENT_OF_REC=? with ur;";

	//	private String selectbydistrict= "select * from TWEB_QT2CONV_SUMRY where RECORD_LEVEL_CD = 'D' fetch first 50 rows only with ur;";

	@Override
	public List<QuoteData> findbyAOR(String agentOfRecord) {
		List<QuoteData> data = jdbctempate.query(selectbyaor,new Object[]{agentOfRecord},new QuoteSummary2QuoteMapper());
		return data;
	}

	@Override
	public List<QuoteData> findbyDistrict(String district) {
		// TODO Auto-generated method stub
		return null;
	}

}
