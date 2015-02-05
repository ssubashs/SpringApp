package com.farmers.dozer;

import junit.framework.Assert;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyPK;
import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.data.MonthlyPerformanceData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:META-INF/spring/batch/override/appcontext.xml",   
    "classpath:META-INF/spring/config/dozerconfig.xml",
    "classpath:META-INF/spring/config/testproperties.xml"
		})
public class MapperTest {
	
	@Autowired
	private DozerBeanMapper dozerMapper;
	
	@Autowired 
	AgentmonthlyRepo monthlyrepo;
	
	@Test
	public void testdb2DTO()
	{
		Assert.assertEquals(false, dozerMapper==null);		
		MonthlyPerformanceData monthlydata = dozerMapper.map(getAgentmonthly(), MonthlyPerformanceData.class);
		System.out.println("Transformed data " + monthlydata.toString());
	}
	
	public void testDTO2db()
	{
		Assert.assertEquals(false, dozerMapper==null);	
		
	}
	
	private Agentmonthly getAgentmonthly()
	{
		AgentmonthlyPK dummyid = new AgentmonthlyPK();
		dummyid.setAgentNum("040302");
		dummyid.setLob("Auto");
		dummyid.setYear(2010);
		dummyid.setMonth(1);
		
		Agentmonthly monthly = monthlyrepo.findOne(dummyid);
		return monthly;
	}

}
