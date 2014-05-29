package com.app;

import java.io.IOException;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomJSONSerializer extends JsonSerializer<Integer>{

	
	public void serialize(String value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {		 	
		
		Message msg = PhaseInterceptorChain.getCurrentMessage();
		System.out.println("Exchange messag in serializer ::: " +msg.getExchange().get("encoding"));
		
			jgen.writeObject("enc_"+value);
		
	}
	
	
	public void serialize(Integer value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {		 	
		
		Message msg = PhaseInterceptorChain.getCurrentMessage();
		System.out.println("Exchange messag in serializer ::: " +msg.getExchange().get("encoding"));
		
			jgen.writeObject(value);
		
	}


	

}
