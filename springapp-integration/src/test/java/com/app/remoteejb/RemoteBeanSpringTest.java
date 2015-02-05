package com.app.remoteejb;

import javax.naming.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ejb.account.Account;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/springAppConfig/app-jee-ejb.xml"
			})
public class RemoteBeanSpringTest {
	private static Context ctx;
	
	@Autowired
	@Qualifier(value="accountService")
	Account accountService;
	
	@Test	
	public void testAccountBean() {
	System.out.println("Spring the test account bean");
	try{
		// provide the interface details 
		//ICMSAccountData data = accountService.getAccountName("500322", 159662);
		//System.out.println("data received "+data.getAccountName());
	 System.out.println("Successfully invoked the ");
	}
	catch (Exception exc)
	{
		exc.printStackTrace();
	}
	}
	


}
