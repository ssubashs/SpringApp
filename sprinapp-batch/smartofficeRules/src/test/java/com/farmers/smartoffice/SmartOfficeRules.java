package com.farmers.smartoffice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farmers.batch.kyn.AgentmonthlyRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/batch/override/appcontext.xml",
		"classpath*:META-INF/spring/config/testproperties.xml"
		})
public class SmartOfficeRules {
	
	@Autowired 
	AgentmonthlyRepo monthlyrepo;
	
	@Test
	public void testSmartOfficeRules()
	{
	  KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
      KieSession ksession = kc.newKieSession("smartofficeKS");


	    for (Object fact : monthlyrepo.findAll())
	    {
	        ksession.insert(fact);
	    }
    
    ksession.fireAllRules();


    ksession.dispose(); 
		
	}

}
