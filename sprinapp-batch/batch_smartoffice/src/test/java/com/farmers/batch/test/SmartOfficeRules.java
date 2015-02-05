package com.farmers.batch.test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farmers.batch.kyn.AgentActions;
import com.farmers.batch.kyn.AgentactionRepo;
import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.batch.kyn.SmartRuleRepo;
import com.farmers.batch.kyn.Smartrule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/batch/override/appcontext.xml",
		"classpath:META-INF/spring/config/testproperties.xml"
		})
public class SmartOfficeRules {
	
	@Autowired 
	AgentmonthlyRepo monthlyrepo;
	
	@Autowired
	AgentactionRepo actionrepo;
	
	@Autowired
	SmartRuleRepo rulerepo;
	
	@Test
	public void testSmartOfficeRules()
	{
		try
		{
			  KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
		      KieSession ksession = kc.newKieSession("smartofficeKS");
		      List<Agentmonthly> monthlydata = monthlyrepo.findAll();
		      AgentActions actions = new AgentActions();
		      System.out.println("Rules intiated.... successfully");
		      ksession.setGlobal("agentactions", actions);
			    for (Agentmonthly fact : monthlydata)
			    {
			        ksession.insert( fact);
			    }
		    
		    ksession.fireAllRules();
		
		
		    ksession.dispose(); 
		    
		    System.out.println("Rules fired.... successfully");
		    
		    if(actions.hasActions())
		    {
		    	System.out.println("Agent recommendations tobe inserted .... ");
		    	actionrepo.save(actions.getActions());
		    }
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
		
	}
	
	@Test
	public void testDBRule()
	{
		
		List<Smartrule> dbrules = rulerepo.findAll();
		
		KieServices ks = KieServices.Factory.get();
		KieRepository ksrepo = ks.getRepository();
		KieFileSystem kfs = ks.newKieFileSystem();
		
		for(Smartrule dbrule : dbrules)
		{
			Resource myResource = ResourceFactory.newReaderResource((Reader) new StringReader(new String(dbrule.getRule())));
			kfs.write("src/main/resources/rules/"+dbrule.getRulename().trim()+".drl", myResource);
		}
		
		
		 KieBuilder kb = ks.newKieBuilder(kfs);
		  
	     kb.buildAll();
	  
	        if (kb.getResults().hasMessages(Level.ERROR))
	        {
	            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
	        }
	  
	        KieContainer kContainer = ks.newKieContainer(ksrepo.getDefaultReleaseId());
	 
	        KieSession ksession = kContainer.newKieSession();
	         
	        List<Agentmonthly> monthlydata = monthlyrepo.findAll();
		      AgentActions actions = new AgentActions();
		      System.out.println("Rules intiated.... successfully");
		      ksession.setGlobal("agentactions", actions);
			    for (Agentmonthly fact : monthlydata)
			    {
			        ksession.insert( fact);
			    }
		    
		    ksession.fireAllRules();
		
		
		    ksession.dispose(); 
		    
		    System.out.println("Rules fired.... successfully");
		    
		    if(actions.hasActions())
		    {
		    	System.out.println("Agent recommendations tobe inserted .... ");
		    	actionrepo.save(actions.getActions());
		    }
	}

}
