package com.local.test;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.After;
import org.junit.Test;

import com.farmers.eai.ActiveMqMsgRoutes;
import com.farmers.eai.EAIRouteBuilder;

public class Testapachemq {

	private ConnectionFactory factory = null;
	 private Connection connection = null;
	 private Session session = null;
	 private Destination destination = null;
	  private MessageProducer producer = null;
	
	
	@Test
	public void sender()
	{
		 try {
	             factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
	             connection = factory.createConnection();
	             connection.start();
	             session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	             destination = session.createQueue("jms/inputqueue");
	             producer = session.createProducer(destination);
	             TextMessage message = session.createTextMessage();
	             message.setText("Java test client sending message "+new Date());
	             producer.send(message);
	            
	             System.out.println("Sent: " + message.getText());
	         } catch (JMSException e) {
	             e.printStackTrace();
	         }
		 
			
	}
	
	@Test
	public void camelMQRoutes()
	{
		CamelContext context = new DefaultCamelContext();		  
		   context.addComponent("jms",ActiveMQComponent.activeMQComponent(ActiveMQConnection.DEFAULT_BROKER_URL));
		   try {
				context.addRoutes(new ActiveMqMsgRoutes()				
				);
				context.start();
				Thread.sleep(20000);			
				context.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@After
	public void clearresources()
	{
		try {
			if(producer!=null)
			producer.close();
			if(session != null)
			session.close();
			if(connection!=null)
			connection.close();			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
