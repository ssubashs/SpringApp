package com.farmers.batch.kyn.quote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class QuoteSummary2QuoteMapper implements ResultSetExtractor<List<QuoteData>>{
//RowMapper<QuoteData>{
//, ResultSetExtractor<List<QuoteData>>{

	
	public List<QuoteData> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		HashMap<String , QuoteData> localmap = new HashMap<String, QuoteData>();
		
		while(rs.next())
		{
			String agentNum = rs.getString("AGENT_OF_REC");
			
		    Integer year = rs.getInt("TRANS_YR");
			String month= rs.getString("TRANS_MTH");
					
			MonthlyPerformance autoperformance = new MonthlyPerformance("Auto",year,month);
			MonthlyPerformance fireperformance = new MonthlyPerformance("Fire",year,month);
			
			autoperformance.setQuote(rs.getDouble("AUTO_QUOTE_VALUE"));
			autoperformance.setYrtdQuote(rs.getDouble("AUTO_YTD_QUOTE_VAL"));
			fireperformance.setQuote(rs.getDouble("FIRE_QUOTE_VALUE"));
			fireperformance.setYrtdQuote(rs.getDouble("FIRE_YTD_QUOTE_VAL"));
			
			QuoteData currentdata = null;
			if(localmap.get(agentNum)!=null)
			{
				currentdata = localmap.get(agentNum);
			}
			else
			{
				currentdata = new QuoteData();
				currentdata.setAgentOfRecord(agentNum);
				currentdata.setDistrict(agentNum.trim().substring(2, 4));
				currentdata.setDivision( rs.getString("DIVISION_CD"));
				currentdata.setState(rs.getString("STATE_CD"));
				localmap.put(agentNum,currentdata);
			}
			
			List<MonthlyPerformance> monthlyPerformanceList;
			if(currentdata.getMonthly()==null)
			{
				monthlyPerformanceList = new ArrayList<MonthlyPerformance>();
				currentdata.setMonthly(monthlyPerformanceList);
			}
			else
			{
				monthlyPerformanceList = currentdata.getMonthly();
				
			}
			
			monthlyPerformanceList.add(autoperformance);
			monthlyPerformanceList.add(fireperformance);
			
			
		}
		
		return new ArrayList<QuoteData>(localmap.values());
	}

	
	public QuoteData mapRow(ResultSet rs, int rowNumber) throws SQLException {
		QuoteData currentdata = new QuoteData();
			String agentNum = rs.getString("AGENT_OF_REC");
			
		    Integer year = rs.getInt("TRANS_YR");
			String month= rs.getString("TRANS_MTH");
					
			MonthlyPerformance autoperformance = new MonthlyPerformance("Auto",year,month);
			MonthlyPerformance fireperformance = new MonthlyPerformance("Fire",year,month);
			
			autoperformance.setQuote(rs.getDouble("AUTO_QUOTE_VALUE"));
			autoperformance.setYrtdQuote(rs.getDouble("AUTO_YTD_QUOTE_VAL"));
			fireperformance.setQuote(rs.getDouble("FIRE_QUOTE_VALUE"));
			fireperformance.setYrtdQuote(rs.getDouble("FIRE_YTD_QUOTE_VAL"));
				
				currentdata.setAgentOfRecord(agentNum);
				currentdata.setDistrict(agentNum.trim().substring(2, 4));
				currentdata.setDivision( rs.getString("DIVISION_CD"));
				currentdata.setState(rs.getString("STATE_CD"));
			
			List<MonthlyPerformance> monthlyPerformanceList;
			if(currentdata.getMonthly()==null)
			{
				monthlyPerformanceList = new ArrayList<MonthlyPerformance>();
				currentdata.setMonthly(monthlyPerformanceList);
			}
			else
			{
				monthlyPerformanceList = currentdata.getMonthly();
				
			}
			
			monthlyPerformanceList.add(autoperformance);
			monthlyPerformanceList.add(fireperformance);
		
		
		return currentdata;
	}

	


}
