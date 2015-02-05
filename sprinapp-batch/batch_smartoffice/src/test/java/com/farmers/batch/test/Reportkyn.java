package com.farmers.batch.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import javax.persistence.Cache;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farmers.batch.kyn.AgentDetailsRepo;
import com.farmers.batch.kyn.Agentaction;
import com.farmers.batch.kyn.AgentactionRepo;
import com.farmers.batch.kyn.Agentdetail;
import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyPK;
import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.batch.kyn.Districtmonthly;
import com.farmers.batch.kyn.DistrictmonthlyRepo;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.ReportviewPK;
import com.farmers.batch.kyn.Statemonthly;
import com.farmers.batch.kyn.StatemonthlyRepo;
import com.farmers.batch.kyn.quote.QuoteData;
import com.farmers.batch.kyn.quote.QuoteTrackerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/batch/override/appcontext.xml",	     
	    "classpath:META-INF/spring/config/batchrepocontext.xml",
	    "classpath:META-INF/spring/config/testproperties.xml"
			})
public class Reportkyn 
{
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
	
	@Autowired
	AgentactionRepo agentActionRepo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	
	@Test
	public void testInsertAgentData()
	{
		System.out.println("Test initialized");
		Reportview dummy = new Reportview();
		ReportviewPK dummyid = new ReportviewPK();
		dummyid.setAgentnum("TestAA");
		dummyid.setDatacategory("Quotes");
		dummyid.setLob("Auto");
		dummyid.setYear(2014);
		dummy.setId(dummyid);
		dummy.setAgentname("Subash");
		dummy = reportrepo.save(dummy);
		System.out.println("row created for  "+ dummy.toString());
		reportrepo.flush();
//		yearlyrepo.delete(dummy);
		reportrepo.flush();
	}
	
	@Test
	public void testReadAgentData()
	{
		System.out.println("Test initialized");
		ReportviewPK dummyid = new ReportviewPK();
		dummyid.setAgentnum("TestAA");
		dummyid.setDatacategory("Quotes");
		dummyid.setLob("Auto");
		dummyid.setYear(2014);
		Reportview data = reportrepo.findOne(dummyid);
		Assert.assertEquals("Subash", data.getAgentname());
		
	}
	
	@Test
	public void testQuoteTrackerData()
	{
		System.out.println("Test initialized");
		List<QuoteData> data = dao.findbyAOR("040302");
		System.out.println("Test completed");
	}
	
	@Test
	public void testMonthlyData()
	{
		System.out.println("Test initialized");
		Agentmonthly monthly = new Agentmonthly();
		monthly.setId(new AgentmonthlyPK("040309",2014,11,"Auto"));
		monthly.setQuotes(129);
		monthly.setCloserate(new BigDecimal(88));
//		monthly.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		monthlyrepo.save(monthly);
		
//		monthly.setNewbusiness(34);
//		monthly.setPif(33);
//		monthly.setUpdatetime(new Timestamp(System.currentTimeMillis()));
//		monthlyrepo.save(monthly);
//		monthlyrepo.flush();
		System.out.println("Test completed");
	}
	
	@Test
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
	
	
	@Test
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
	
	
	@Test
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
	
	
	@Test
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
	
	
	@Test
	public void testCache()
	{
		String queryStr = "SELECT detail FROM Agentdetail as detail where detail.agentNum=:aor";
//		Map<String, Object> properties = new HashMap<String, Object>();
//		properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
//		emf.createEntityManager(properties);
		Query query = emf.createEntityManager().createQuery(queryStr);
		query.setParameter("aor", "040302");
		
		List<Agentdetail>  resultlist = query.getResultList();
		
		Agentdetail wren = hierarchyrepo.findOne("WREN919");
		
		System.out.println("resultlist size "+resultlist.size());
		
		Cache cache = emf.getCache();
		
		System.out.println("cache.contain should return true: "+cache.contains(Agentdetail.class, "WREN918"));
//        cache.evict(Account.class);
//        System.out.println("cache.contain should return false: "+cache.contains(Agentdetail.class, account.getAccountNumber()));
		
	}
	
	public void queryforcachetest(String aor)
	{
		String queryStr = "SELECT detail FROM Agentdetail as detail where detail.agentNum=:aor";
		Query query = emf.createEntityManager().createQuery(queryStr);
		query.setParameter("aor", aor);
		
		List<Agentdetail>  resultlist = query.getResultList();
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
	
	@Test
	public void testReportview()
	{
		System.out.println("Testing report view :: Starts");
		List<Reportview> viewlist = reportrepo.findAgentreportquarter("040302", 2013, 1);
		for(Reportview view:viewlist)
		{
			System.out.println(view.toString());
			
		}
		

		List<Agentaction> agentActions = agentActionRepo.findAgentreportquarter("040302", 2010, 1);
		for(Agentaction view:agentActions)
		{
			System.out.println(view.toString());
			
		}
		System.out.println("Testing report view :: Starts");
	}
	

}
