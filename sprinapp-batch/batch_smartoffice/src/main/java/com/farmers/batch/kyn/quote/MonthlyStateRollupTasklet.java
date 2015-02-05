package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Statemonthly;
import com.farmers.batch.kyn.StatemonthlyPK;
import com.farmers.batch.kyn.StatemonthlyRepo;

@Service("monthlystateRollups")
public class MonthlyStateRollupTasklet implements Tasklet{

	@Autowired
	private StatemonthlyRepo repo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("Rolling up State data ");
			String queryStr = "SELECT monthly.state,monthly.id.lob,monthly.id.year,monthly.id.month, sum(monthly.quotes) as quotes, sum(monthly.pif) as pif, sum(monthly.newbusiness) as newbusiness, sum(monthly.ytdquotes) as ytdquotes, sum(monthly.ytdpif) as ytdpif, sum(monthly.ytdnewbusiness) as ytdnewbusiness,avg(retention) as retention, avg(closerate) as closerate FROM Agentmonthly as monthly  group by monthly.state,monthly.id.lob,monthly.id.year,monthly.id.month";
			EntityManager manager = emf.createEntityManager();
			Query query = manager.createQuery(queryStr);
			List<Object[]>  resultlist = query.getResultList();
			System.out.println("State monthly result size "+resultlist.size());
			ArrayList<Statemonthly> smonthlyList = new ArrayList<Statemonthly>();
			for(Object[] result:resultlist)
			{
				Statemonthly smonthly = new Statemonthly();
				StatemonthlyPK smonthlypk = new StatemonthlyPK();
				smonthly.setId(smonthlypk);
				boolean isvalid=false;
				for(int i=0;i<result.length;i++)
				{
					switch (i){
					case 0:							
						smonthlypk.setState((String) result[i]);
						break;
					case 1:
						smonthlypk.setLob((String) result[i]);
						break;
					case 2:
						smonthlypk.setYear((Integer) result[i]);
						break;
					case 3:
						smonthlypk.setMonth((Integer) result[i]);
						isvalid=true;
						break;	
					case 4:
						smonthly.setQuotes((Long)result[i]);
						break;	
					case 5:
						smonthly.setPif((Long)result[i]);
						break;	
					case 6:
						smonthly.setNewbusiness((Long)result[i]);
						break;
					case 7:
						smonthly.setYtdquotes((Long)result[i]);
						break;
					case 8:
						smonthly.setYtdpif((Long)result[i]);
						break;
					case 9:
						smonthly.setYtdnewbusiness((Long)result[i]);
						break;
					case 10:
						smonthly.setRetention(new BigDecimal((Double)result[i]));
						break;
					case 11:
						smonthly.setCloserate(new BigDecimal((Double)result[i]));
						break;
					}
				}
				if(isvalid)
				{
					smonthly.setUpdatetime(new Timestamp(System.currentTimeMillis()));
					smonthlyList.add(smonthly);
				}
			}
			manager.close();
			repo.save(smonthlyList);
			System.out.println("Rolled up State data saved");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

}
