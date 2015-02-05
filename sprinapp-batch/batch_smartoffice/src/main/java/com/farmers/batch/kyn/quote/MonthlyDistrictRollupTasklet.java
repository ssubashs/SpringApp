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

import com.farmers.batch.kyn.Districtmonthly;
import com.farmers.batch.kyn.DistrictmonthlyPK;
import com.farmers.batch.kyn.DistrictmonthlyRepo;

@Service("monthlydistrictRollups")
public class MonthlyDistrictRollupTasklet implements Tasklet{

	@Autowired
	private DistrictmonthlyRepo repo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("Rolling up district data ");
			String queryStr = "SELECT monthly.state,monthly.district,monthly.id.lob,monthly.id.year,monthly.id.month, sum(monthly.quotes) as quotes, sum(monthly.pif) as pif, sum(monthly.newbusiness) as newbusiness, sum(monthly.ytdquotes) as ytdquotes, sum(monthly.ytdpif) as ytdpif, sum(monthly.ytdnewbusiness) as ytdnewbusiness,avg(retention) as retention, avg(closerate) as closerate FROM Agentmonthly as monthly  group by monthly.state,monthly.district,monthly.id.lob,monthly.id.year,monthly.id.month";
			EntityManager manager = emf.createEntityManager();
			Query query = manager.createQuery(queryStr);
			List<Object[]>  resultlist = query.getResultList();
			System.out.println("district monthly result size "+resultlist.size());
			ArrayList<Districtmonthly> dmonthlyList = new ArrayList<Districtmonthly>();
			for(Object[] result:resultlist)
			{
				Districtmonthly dmonthly = new Districtmonthly();
				DistrictmonthlyPK dmonthlypk = new DistrictmonthlyPK();
				dmonthly.setId(dmonthlypk);
				boolean isvalid=false;
				for(int i=0;i<result.length;i++)
				{
					switch (i){
					case 0:							
						dmonthlypk.setState((String) result[i]);
						break;
					case 1:							
						dmonthlypk.setDistrict((String) result[i]);
						break;
					case 2:
						dmonthlypk.setLob((String) result[i]);
						break;
					case 3:
						dmonthlypk.setYear((Integer) result[i]);
						break;
					case 4:
						dmonthlypk.setMonth((Integer) result[i]);
						isvalid=true;
						break;	
					case 5:
						dmonthly.setQuotes((Long)result[i]);
						break;	
					case 6:
						dmonthly.setPif((Long)result[i]);
						break;	
					case 7:
						dmonthly.setNewbusiness((Long)result[i]);
						break;
					case 8:
						dmonthly.setYtdquotes((Long)result[i]);
						break;
					case 9:
						dmonthly.setYtdpif((Long)result[i]);
						break;
					case 10:
						dmonthly.setYtdnewbusiness((Long)result[i]);
						break;
					case 11:
						dmonthly.setRetention(new BigDecimal((Double)result[i]));
						break;
					case 12:
						dmonthly.setCloserate(new BigDecimal((Double)result[i]));
						break;
					}
				}
				if(isvalid)
				{
					dmonthly.setUpdatetime(new Timestamp(System.currentTimeMillis()));
					dmonthlyList.add(dmonthly);
				}
			}
			manager.close();
			repo.save(dmonthlyList);
			System.out.println("Rolled up district data");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

}
