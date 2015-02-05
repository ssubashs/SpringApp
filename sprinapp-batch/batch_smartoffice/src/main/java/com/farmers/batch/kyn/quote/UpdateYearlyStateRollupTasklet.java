package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.ReportviewPK;

@Service("yearlyStateRollups")
public class UpdateYearlyStateRollupTasklet implements Tasklet{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("update agent yearly :: Starts ");
			Integer[] years = new Integer[]{2009,2010,2011,2012,2013,2014};
			
			for(Integer year :years )
			{	
//				this.updateAgentforYear(year);
			}
			
			this.updateAgentforYear();
			
			System.out.println("update district quarterly  :: Ends ");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

	private void updateAgentforYear() {
		
		String queryStr = "SELECT monthly.state,monthly.id.lob,monthly.id.year,sum(monthly.quotes) as quotes, sum(monthly.pif) as pif, sum(monthly.newbusiness)as newbusiness,avg(retention) as retention, avg(closerate) as closerate  FROM Agentmonthly monthly  group by monthly.state,monthly.id.lob,monthly.id.year";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);
//		query.setParameter("pastyear", year-1);
		List<Object[]>  resultlist = query.getResultList();
		
		for(Object[] row:resultlist )
		{			
			System.out.println(row.toString());
			Reportview dummyview = new Reportview();
			dummyview.setId(new ReportviewPK());
//			dummyview.getId().setYear(year);
			
			
			for(int i=0;i<row.length;i++)
			{
				switch (i){
				case 0:							
					dummyview.setState((String)row[i]);
					break;				
				case 1:
					dummyview.getId().setLob((String)row[i]);
					break;
				case 2:
					dummyview.getId().setYear((Integer)row[i]+1);
					break;
				case 3:
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("Quotes");
					updatelikeviewsagentdata(dummyview);
					break;
				case 4:
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("Policies");
					updatelikeviewsagentdata(dummyview);
					break;
				case 5:
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("New Business");
					updatelikeviewsagentdata(dummyview);
					break;				
				case 6:
					dummyview.setDistrictdata(new BigDecimal((Double)row[i]));
					dummyview.getId().setDatacategory("Retention");
					updatelikeviewsagentdata(dummyview);
					break;
				case 7:
					dummyview.setDistrictdata(new BigDecimal((Double)row[i]));
					dummyview.getId().setDatacategory("Close Rate");
					updatelikeviewsagentdata(dummyview);
					break;
			 }					
		
			
			}
			//break;
		}
		manager.close();
	}

	
	public void updatelikeviewsagentdata(Reportview dummyview) {
		EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	  try
	  {  
        tx.begin();
		String queryStr = "update Reportview SET paststatedata = :data where state = :state and id.datacategory = :datatype and id.lob = :lob and id.year = :year";
		Query query = em.createQuery(queryStr);
		query.setParameter("data", dummyview.getDistrictdata());
		query.setParameter("state", dummyview.getState());		
		query.setParameter("datatype", dummyview.getId().getDatacategory());
		query.setParameter("lob", dummyview.getId().getLob());
		query.setParameter("year", dummyview.getId().getYear());
		System.out.println("rows update "+ query.executeUpdate());
		tx.commit();
	 }
    catch (RuntimeException ex) {
      System.out.println("Error trying to updating all records" + ex.getMessage());
       try {
          tx.rollback();
       } catch (RuntimeException ex2) {
          System.out.println("Unable to rollback Tx"+ex2.getMessage());
       }
    } finally {
       em.close();
    }
		
	}

}
