package com.app.cxfrs.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.cxfrs.defs.ICustomerServ;
import com.app.cxfrs.impls.CustomerServiceImpl;
import com.app.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/springAppConfig/appcxf-rs-client.xml" })
public class CustomWebClient {

	Logger logger = LoggerFactory.getLogger(CustomWebClient.class);	
	
	@Resource(name="jsonProvider")
	private JacksonJsonProvider jsonprovider;
	
	@Resource(name="customerService")
	private ICustomerServ customerRepo;
	
	
	@Test
	public void restWebClient()
	{
		
		 List<Object> providers = new ArrayList<Object>();
	     providers.add(jsonprovider);
	   
	     WebClient client = WebClient.create("http://localhost:8080/springapp-cxf/api", providers);
	     client = client.accept("application/json").type("application/json").path("/customer/1/person");
	     Person person = client.get(Person.class);
	     logger.debug("response person :: "+person.getFirstname() );
	    
		
	}
	
	@Test
	public void jaxrsProxyClient()
	{
		
		 List<Object> providers = new ArrayList<Object>();
	     providers.add(jsonprovider);
	   
	     ICustomerServ customerRepoLocal = JAXRSClientFactory.create("http://localhost:8080/springapp-cxf/api/", CustomerServiceImpl.class,providers,true);
	     
	     logger.debug("response person :: "+customerRepoLocal.fetchbyId(2).getFirstname());
	    
		
	}
	
	@Test
	public void jaxrsProxyClientXML()
	{
		
		logger.debug("get person id 1 " + customerRepo.fetchbyId(1).getFirstname());
		
	}
	
	
}
