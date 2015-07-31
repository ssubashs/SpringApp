package com.farmers.test;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

public class TestCamelEAI {
	
	@Test
	public void testFileHandline()
	{
		CamelContext context = new DefaultCamelContext();		
		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("file:C://static//server//reports?noop=true").to("file:C://static//server//reports//archive");					
				}
				
			});
			context.start();
			Thread.sleep(10000);
			context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
