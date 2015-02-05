package com.farmers.smartoffice;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.farmers.RulesRunner;
import com.farmers.smartoffice.kbase.Agentdata;

public class TestRules {
	
	static Agentdata[] agents = new Agentdata[5];
	static{
    agents[0] = new Agentdata(); 
    agents[0].setAgentNumber("040301"); agents[0].setState("CA");agents[0].setDistrict("03");
    agents[0].setQuotes(new Double(91));agents[0].setNewBusiness(new Double(121));
    
    agents[1] = new Agentdata(); 
    agents[1].setAgentNumber("070811"); agents[1].setState("TX");agents[1].setDistrict("08");
    agents[1].setQuotes(new Double(189));agents[1].setNewBusiness(new Double(21));
    
    agents[2] = new Agentdata(); 
    agents[2].setAgentNumber("040725"); agents[2].setState("CA");agents[2].setDistrict("07");
    agents[2].setQuotes(new Double(198));agents[2].setNewBusiness(new Double(32));
    
    agents[3] = new Agentdata(); 
    agents[3].setAgentNumber("532619"); agents[3].setState("AL");agents[3].setDistrict("26");
    agents[3].setQuotes(new Double(142));agents[3].setNewBusiness(new Double(221));
    
    agents[4] = new Agentdata(); 
    agents[4].setAgentNumber("532618"); agents[4].setState("AL");agents[4].setDistrict("26");
    agents[4].setQuotes(new Double(432));agents[4].setNewBusiness(new Double(543));
	};
	
	@Test
	public  void TestSampleRules()
    {
        RulesRunner runner = new RulesRunner();
 
        String[] rules = { "smartoffice.drl" };
        
       
 
        runner.runRules(rules,agents);
        System.out.println("Rules executed");
    }
	
	@Test
	public void testKSession()
	{
	  KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
      KieSession ksession = kc.newKieSession("smartofficeKS1");

//    KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "log/fibonacci.log");
    for (Object fact : agents)
    {
        ksession.insert(fact);
    }
    
    ksession.fireAllRules();

//    logger.close();
    ksession.dispose(); // Stateful 
		
	}

}
