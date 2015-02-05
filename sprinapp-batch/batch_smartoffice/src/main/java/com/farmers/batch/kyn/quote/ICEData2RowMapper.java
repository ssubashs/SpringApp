package com.farmers.batch.kyn.quote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class ICEData2RowMapper implements RowMapper<QuoteData>{
	public QuoteData mapRow(ResultSet rs, int rowNumber) throws SQLException {
		QuoteData currentdata = new QuoteData();
			String agentNum = rs.getString("AGENT_OF_REC");
			
		    Integer year = rs.getInt("TRANS_YR");
			String month= rs.getString("TRANS_MTH");
					
			MonthlyPerformance autoperformance = new MonthlyPerformance("Auto",year,month);
			MonthlyPerformance fireperformance = new MonthlyPerformance("Fire",year,month);
			autoperformance.setNewbusiness(rs.getDouble("AUTONB"));
			autoperformance.setPif(rs.getDouble("AUTOPIF"));
			autoperformance.setRetention(rs.getDouble("AUTORETENTION"));
			fireperformance.setNewbusiness(rs.getDouble("FIRENB"));
			fireperformance.setPif(rs.getDouble("FIREPIF"));
			fireperformance.setRetention(rs.getDouble("FIRERETENTION"));
			
				if(agentNum!=null && agentNum.trim().length()<6)
					agentNum="0"+agentNum.trim();
				currentdata.setAgentOfRecord(agentNum);
				currentdata.setDistrict(agentNum.trim().substring(2, 4));
				currentdata.setDivision( rs.getString("DIVISION_CD"));
				currentdata.setState(agentNum.trim().substring(0, 2));
			
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
