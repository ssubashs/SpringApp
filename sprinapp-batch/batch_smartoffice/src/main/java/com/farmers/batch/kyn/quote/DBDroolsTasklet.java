package com.farmers.batch.kyn.quote;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmers.batch.kyn.AgentActions;
import com.farmers.batch.kyn.AgentactionRepo;
import com.farmers.batch.kyn.Agentmonthly;
import com.farmers.batch.kyn.AgentmonthlyRepo;
import com.farmers.batch.kyn.FocusAreaComparator;
import com.farmers.batch.kyn.ReportViewRepo;
import com.farmers.batch.kyn.Reportview;
import com.farmers.batch.kyn.SmartRuleRepo;
import com.farmers.batch.kyn.Smartrule;

@Service("dbdroolsRules")
public class DBDroolsTasklet implements Tasklet{

	@Autowired
	SmartRuleRepo rulerepo;
	
	@Autowired 
	AgentmonthlyRepo monthlyrepo;
	
	@Autowired
	AgentactionRepo actionrepo;
	
	@Autowired 
	ReportViewRepo reportRepo;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public RepeatStatus execute(StepContribution stepArgs, ChunkContext chunkArgs)
			throws Exception {
		try
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
		        
		        AgentActions actions = new AgentActions();
		        
		       this.fireAllMonthlyrules(ksession, actions);
		       this.fireAllViewrules(ksession,actions);
			
			
			    
			    
			    
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
		
		return RepeatStatus.FINISHED;
	}

	
	
	private void fireAllViewrules(KieSession ksession, AgentActions actions) {
		String queryStr = "select view from Reportview view where view.focusarea='Y'";
		Query query = emf.createEntityManager().createQuery(queryStr);		
		List<Reportview>  resultlist = query.getResultList();		
		 System.out.println("fireAllViewrules intiated.... successfully");
	      ksession.setGlobal("agentactions", actions);
		    for (Reportview fact : resultlist)
		    {
		        ksession.insert( fact);
		    }
	    
	    ksession.fireAllRules();
		
	}



	private void fireAllMonthlyrules(KieSession ksession, AgentActions actions)
	{
		 List<Agentmonthly> monthlydata = monthlyrepo.findAll();
	     
	      System.out.println("Rules intiated.... successfully");
	      ksession.setGlobal("agentactions", actions);
		    for (Agentmonthly fact : monthlydata)
		    {
		        ksession.insert( fact);
		    }
	    
	    ksession.fireAllRules();
		
	}
}
