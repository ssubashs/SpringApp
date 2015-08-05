package com.farmers.eai;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMqMsgRoutes extends RouteBuilder  {
	
	
    static Logger LOG = LoggerFactory.getLogger(ActiveMqMsgRoutes.class);
    public void configure() {
      LOG.debug("Active mq routes builder for incoming messages ");
   
      from("jms:jms/outputqueue")
      .process(new Processor() {
		
		@Override
		public void process(Exchange exc) throws Exception {
			Message inmessage = exc.getIn();
			LOG.debug("Processign message ------- > " + inmessage.getBody().toString());
		}
	})
      .to("jms:jms/inputqueue");
    }


}
