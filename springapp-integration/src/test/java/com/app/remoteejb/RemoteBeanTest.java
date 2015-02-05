package com.app.remoteejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ejb.account.Account;
import com.ejb.account.AccountHome;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/springAppConfig/app-jee-ejb.xml"
			})
public class RemoteBeanTest {
	private static Context ctx;
	
	@BeforeClass	
	public static void setUp() throws NamingException {
	Properties env = new Properties();	
	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
	env.put(Context.PROVIDER_URL, "iiop://localhost:9102");
	ctx = new InitialContext(env);
	}
	
	@AfterClass
	public static void tearDown() throws NamingException {	
		if(ctx !=null)
		ctx.close();	
	}
	
	@Test
	
	public void testAccountBean() {
	System.out.println("Starting the test of account bean");
	try{	
		Object obj = ctx.lookup("ejb/com/farmers/eagent/icms/ejb/account/AccountHome");
		AccountHome accounthome = (AccountHome) PortableRemoteObject.narrow(obj,AccountHome.class); 
		Account accountService = accounthome.create();
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
