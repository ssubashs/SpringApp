package com.farmers.batch.kyn.quote;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Agentmonthly;

@Service("agentYTDProcessor")
public class AgentYTDProcessor implements ItemProcessor<Agentmonthly,Agentmonthly>{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public Agentmonthly process(final Agentmonthly data) throws Exception {
		System.out.println("AgentYTDProcessor Processing..." + data.toString());
		
		String queryStr = "SELECT sum(monthly.quotes) as ytdquotes, sum(monthly.pif) as ytdpif, sum(monthly.newbusiness) as ytdnewbusiness"+ 
						  " FROM Agentmonthly as monthly  where monthly.id.agentNum= :agentnumber and monthly.id.lob= :lob and monthly.id.year= :year and monthly.id.month <= :month "+
						  " group by monthly.id.agentNum,monthly.id.lob,monthly.id.year";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);
		query.setParameter("agentnumber", data.getId().getAgentNum());
		query.setParameter("lob", data.getId().getLob());
		query.setParameter("year", data.getId().getYear());
		query.setParameter("month", data.getId().getMonth());
		List<Object[]>  resultlist = query.getResultList();
		System.out.println(" monthly ytd result size "+resultlist.size());
		
		for(Object[] result:resultlist)
		{
			
			for(int i=0;i<result.length;i++)
			{
				switch (i){
				case 0:							
					data.setYtdquotes((Long)result[i]);
					break;
				case 1:
					data.setYtdpif((Long)result[i]);
					break;
				case 2:
					data.setYtdnewbusiness((Long)result[i]);
					break;
				
				}
			}
			data.setUpdatetime(new Timestamp(System.currentTimeMillis()));
			break; // should return only one row:: assumption
		}
		
		manager.close();
//		manager.clear();
		return data;
	}


}
