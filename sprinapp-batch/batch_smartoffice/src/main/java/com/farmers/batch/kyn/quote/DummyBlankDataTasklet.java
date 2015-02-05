package com.farmers.batch.kyn.quote;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.AgentDetailsRepo;
import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.batch.kyn.Districtmonthly;
import com.farmers.batch.kyn.DistrictmonthlyRepo;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.Statemonthly;
import com.farmers.batch.kyn.StatemonthlyRepo;

@Service("dummyReportData")
public class DummyBlankDataTasklet implements Tasklet{

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired 
	ReportViewRepo reportrepo;
	
	@Autowired
	QuoteTrackerDao dao;
	
	@Autowired 
	AgentmonthlyRepo monthlyrepo;
	
	@Autowired
	DistrictmonthlyRepo districtrepo;
	
	@Autowired
	StatemonthlyRepo staterepo;
	
	@Autowired
	AgentDetailsRepo hierarchyrepo;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
		{
			System.out.println("Dummy up data for reports:: Starts");
			this.dummyAgentMonthly();
			this.dummyDistrictMonthly();
			this.dummyStateMonthly();
			this.dummyReportView();
			
			System.out.println("Dummy up data for reports:: Ends");
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
		}
		
		return RepeatStatus.FINISHED;
	}

	

	public void dummyAgentMonthly()
	{
		List<Agentmonthly> monthlydata = monthlyrepo.findAll();
		boolean always = false;
		for(Agentmonthly monthly: monthlydata)
		{
			Integer quotes = monthly.getQuotes();
			if(quotes==null || quotes == 0)
			{
				quotes=this.getRandom(50, 500);
				monthly.setQuotes(quotes);
			}
			if(monthly.getNewbusiness()==null || always)
				monthly.setNewbusiness(quotes*getRandom(40,130)/100);
			if(monthly.getPif()==null || always)
				monthly.setPif(monthly.getNewbusiness()*getRandom(40,130)/100);
			if(monthly.getRetention()==null || always)
			{
				Integer priorpif=null;
				if(monthly.getPif()==0)
					priorpif = getRandom(1, 50);
				else
				    priorpif = (monthly.getPif()*getRandom(80, 140))/100;
				
				System.out.println("new pif "+ monthly.getPif() + ":: prior pif "+ priorpif);
				double retention = (double)monthly.getPif()/(priorpif+monthly.getNewbusiness());
				System.out.print(":: Retention rate "+retention);
				monthly.setRetention(new BigDecimal(retention*100,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
			if(monthly.getCloserate()==null || always)
			{
				double closerate = (double)monthly.getNewbusiness()/monthly.getQuotes();
				closerate= closerate*100;
//				System.out.println("Close rate "+closerate);
				monthly.setCloserate(new BigDecimal(closerate,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
		}
		monthlyrepo.save(monthlydata);
		
	}
	
	

	public void dummyDistrictMonthly()
	{
		List<Districtmonthly> monthlydata = districtrepo.findAll();
	
		boolean always = false;
		for(Districtmonthly monthly: monthlydata)
		{
			
			if(monthly.getRetention()==null || always)
			{
				Long priorpif=null;
				if(monthly.getPif()==0)
					priorpif = getRandomLong(1, 50);
				else
				    priorpif = (monthly.getPif()*getRandomLong(50, 140))/100;
				
				System.out.println("new pif "+ monthly.getPif() + ":: prior pif "+ priorpif);
				double retention = (double)monthly.getPif()/(priorpif+monthly.getNewbusiness());
				System.out.print(":: Retention rate "+retention);
				monthly.setRetention(new BigDecimal(retention*100,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
			if(monthly.getCloserate()==null || always)
			{
				double closerate = (double)monthly.getNewbusiness()/monthly.getQuotes();
				closerate= closerate*100;
//				System.out.println("Close rate "+closerate);
				monthly.setCloserate(new BigDecimal(closerate,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
		}
		districtrepo.save(monthlydata);
		
	}
	
	

	public void dummyStateMonthly()
	{
		List<Statemonthly> monthlydata = staterepo.findAll();
	
		boolean always = false;
		for(Statemonthly monthly: monthlydata)
		{
			
			if(monthly.getRetention()==null || always)
			{
				Long priorpif=null;
				if(monthly.getPif()==0)
					priorpif = getRandomLong(1, 50);
				else
				    priorpif = (monthly.getPif()*getRandomLong(50, 140))/100;
				
				System.out.println("new pif "+ monthly.getPif() + ":: prior pif "+ priorpif);
				double retention = (double)monthly.getPif()/(priorpif+monthly.getNewbusiness());
				System.out.print(":: Retention rate "+retention);
				monthly.setRetention(new BigDecimal(retention*100,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
			if(monthly.getCloserate()==null || always)
			{
				double closerate = (double)monthly.getNewbusiness()/monthly.getQuotes();
				closerate= closerate*100;
//				System.out.println("Close rate "+closerate);
				monthly.setCloserate(new BigDecimal(closerate,new MathContext(2, RoundingMode.HALF_EVEN)));
			}
		}
		staterepo.save(monthlydata);
		
	}
	
	

	public void dummyReportView()
	{
		List<Reportview> monthlydata = reportrepo.findAll();
		
		String queryStr = "select view FROM Reportview view where view.pastagentdata is null or view.pastdistrictdata is null or view.paststatedata is null";
		Query query = emf.createEntityManager().createQuery(queryStr);
//		query.setParameter("pastyear", year-1);
		List<Reportview>  resultlist = query.getResultList();
	
		boolean always = false;
		for(Reportview view: resultlist)
		{
			
			if(view.getPastagentdata()==null || view.getPastagentdata().equals("0.00")   || always)
			{
				double temp = view.getAgentdata().longValue()* (double)getRandomLong(50, 140)/100;
				if((int)temp == 0)
					System.out.println("agent data for 0"+view.getAgentdata().toString());
				view.setPastagentdata(new BigDecimal(temp));
//				if("Quotes".equalsIgnoreCase(view.getId().getDatacategory()))
//				{					
//					view.setPastagentdata(view.getAgentdata().multiply(new BigDecimal(getRandomLong(50, 140)/100)));
//				}
//				else if("Policies".equalsIgnoreCase(view.getId().getDatacategory())){
//					view.setPastagentdata(view.getAgentdata().multiply(new BigDecimal(getRandomLong(50, 140)/100)));
//				}
//				else if("New Business".equalsIgnoreCase(view.getId().getDatacategory())){
//					view.setPastagentdata(view.getAgentdata().multiply(new BigDecimal(getRandomLong(50, 140)/100)));
//				}
//				else if("Retention".equalsIgnoreCase(view.getId().getDatacategory())){
//					view.setPastagentdata(view.getAgentdata().multiply(new BigDecimal(getRandomLong(50, 140)/100)));
//					
//				}
			
			}
			if(view.getPastdistrictdata()==null || view.getPastdistrictdata().equals("0.00")  || always)
			{
				double temp = view.getAgentdata().longValue()* (double)getRandomLong(50, 140)/100;
				if((int)temp == 0)
					System.out.println("district data for 0"+view.getDistrictdata().toString());
				view.setPastdistrictdata(new BigDecimal(temp));
			}
			if(view.getPaststatedata()==null || view.getPaststatedata().equals("0.00")  || always)
			{
				double temp = view.getAgentdata().longValue()* (double)getRandomLong(50, 140)/100;
				if((int)temp == 0)
					System.out.println("state data for 0"+view.getDistrictdata().toString());
				view.setPaststatedata(new BigDecimal(temp));
			}
			
		}
		reportrepo.save(resultlist);
	
		
	}
	
	public Long getRandomLong(int minimum, int maximum)
	{
	Random rn = new Random();
	int range = maximum - minimum + 1;
	int randomNum =  rn.nextInt(range) + minimum;
	return new Long(randomNum);
	}
	
	public Integer getRandom(int minimum, int maximum)
	{
	Random rn = new Random();
	int range = maximum - minimum + 1;
	int randomNum =  rn.nextInt(range) + minimum;
	return randomNum;
	}
	
	
}
