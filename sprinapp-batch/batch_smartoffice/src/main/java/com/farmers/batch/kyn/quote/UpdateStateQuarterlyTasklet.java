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

import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.ReportviewPK;

@Service("updateStateQuarterly")
public class UpdateStateQuarterlyTasklet implements Tasklet{
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private ReportViewRepo reportRepo;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("update state quarterly :: Starts ");
			Integer[] quarters = new Integer[]{1,2,3,4};
			
			for(Integer quarter :quarters )
			{	
				this.updateforQuarter(quarter);
			}
			
			System.out.println("update state quarterly  :: Ends ");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

	private void updateforQuarter(Integer quarter) {
		
		String queryStr = "select id.state,id.lob,id.year,sum(quotes) as quotes, sum(pif) as pif, sum(newbusiness) as newbusiness, avg(retention) as retention, avg(closerate) as closerate FROM Statemonthly where id.month <=:currentquarter *3 and month >=(:currentquarter-1)*3 group by id.state,id.lob,id.year";
		EntityManager manager = emf.createEntityManager();
		Query query = manager.createQuery(queryStr);
		query.setParameter("currentquarter", quarter);
		List<Object[]>  resultlist = query.getResultList();
		
		for(Object[] row:resultlist )
		{			
			System.out.println(row.toString());
			Reportview dummyview = new Reportview();
			dummyview.setId(new ReportviewPK());
			dummyview.getId().setQuarter(quarter);
			System.out.println("Current row "+ row.toString());
			
			for(int i=0;i<row.length;i++)
			{
				switch (i){
				case 0:	
					if(row[i]!=null)
					{
					dummyview.setState((String)row[i]);
					}
					break;				
				case 1:
					if(row[i]!=null)
					{
					dummyview.getId().setLob((String)row[i]);
					}
					break;
				case 2:
					if(row[i]!=null)
					{
					dummyview.getId().setYear((Integer)row[i]);
					}
					break;
				case 3:
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("Quotes");
					updatelikeviews(dummyview);
					break;
				case 4:
					{
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("Policies");
					updatelikeviews(dummyview);
					}
					break;
				case 5:
					if(row[i]!=null)
					{
					dummyview.setDistrictdata(new BigDecimal((Long)row[i]));
					dummyview.getId().setDatacategory("New Business");
					updatelikeviews(dummyview);
					}
					break;				
				case 6:
					if(row[i]!=null)
					{
					dummyview.setDistrictdata(new BigDecimal((Double)row[i]));
					dummyview.getId().setDatacategory("Retention");
					updatelikeviews(dummyview);
					}
					break;
				case 7:
					if(row[i]!=null)
					{
					dummyview.setDistrictdata(new BigDecimal((Double)row[i]));
					dummyview.getId().setDatacategory("Close Rate");
					updatelikeviews(dummyview);
					}
					break;
			 }					
		
			
			}
			//break;
		}
		manager.close();
	}

	
	public void updatelikeviews(Reportview dummyview) {
		EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	  try
	  {  
        tx.begin();
		String queryStr = "update Reportview SET statedata = :data where state = :state and id.datacategory = :datatype and id.lob = :lob and id.quarter = :quarter";
		Query query = em.createQuery(queryStr);
		query.setParameter("data", dummyview.getDistrictdata());
		query.setParameter("state", dummyview.getState());
		query.setParameter("datatype", dummyview.getId().getDatacategory());
		query.setParameter("lob", dummyview.getId().getLob());
		query.setParameter("quarter", dummyview.getId().getQuarter());
		System.out.println("rows update "+ query.executeUpdate());
		tx.commit();
	 }
    catch (RuntimeException ex) {
      System.out.println("Error trying to update all " + ex.getMessage());
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
