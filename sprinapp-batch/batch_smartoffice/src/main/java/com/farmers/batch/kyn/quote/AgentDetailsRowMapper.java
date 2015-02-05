package com.farmers.batch.kyn.quote;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.farmers.batch.kyn.Agentdetail;

public class AgentDetailsRowMapper implements RowMapper<Agentdetail>{
//RowMapper<QuoteData>{
//, ResultSetExtractor<List<QuoteData>>{

	
	public Agentdetail extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		Agentdetail detail = null;
		
		while(rs.next())
		{
			
			
			
		}
		
		return detail;
	}

@Override
public Agentdetail mapRow(ResultSet rs, int rowcount) throws SQLException {
	Agentdetail detail = null;
	
	if(rs!=null && rs.getString("agentnum")!=null && rs.getString("userid")!=null)
	{
		
		detail = new Agentdetail();
	
		if(rs.getString("userid")!=null)
			detail.setUserid(rs.getString("userid").trim());
		if(rs.getString("agentnum")!=null)
			detail.setAgentNum(rs.getString("agentnum").trim());
		if(rs.getString("firstname")!=null)
			detail.setFirstname(rs.getString("firstname").trim());
		if(rs.getString("lastname")!=null)
			detail.setLastname(rs.getString("lastname").trim());
		
		if(rs.getString("agencyname")!=null)
		detail.setAgencyname(rs.getString("agencyname").trim());
		if(rs.getString("agenttype")!=null)
		detail.setAgenttype(rs.getString("agenttype").trim());
		if(rs.getString("dmfirstname")!=null)
		detail.setDmfirstname(rs.getString("dmfirstname").trim());
		if(rs.getString("dmlastname")!=null)
		detail.setDmlastname(rs.getString("dmlastname").trim());
		if(rs.getString("email")!=null)
		detail.setEmail(rs.getString("email").trim());
		
		if(rs.getString("status")!=null)
		detail.setStatus(rs.getString("status").trim());
		
	}
	
	return detail;
}

		


}
