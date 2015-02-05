package com.farmers.batch.kyn.quote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class QuoteSummary2QuoteRowMapper implements RowMapper<QuoteData>{
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
				if(agentNum!=null && agentNum.trim().length()<6)
					agentNum="0"+agentNum;
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
