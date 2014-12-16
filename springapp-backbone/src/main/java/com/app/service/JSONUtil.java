package com.app.service;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;

public final class JSONUtil {

	
	    
	public static String toJson(Object value) throws JsonGenerationException, JsonMappingException, IOException {
		  JacksonObjectMapper mapper = new JacksonObjectMapper();
		  return mapper.writeValueAsString(value);
		 }

	 public static String model2Json(HttpServletRequest request)
	    	    throws JsonMappingException, JsonGenerationException, IOException {
		  	JacksonObjectMapper mapper = new JacksonObjectMapper();
		            JsonFactory jsonfactory = new JsonFactory();
	    	        StringWriter sw = new StringWriter();
	    	        JsonGenerator jg = jsonfactory.createJsonGenerator(sw);
	    	        
	    	        Enumeration<String> enumList = request.getAttributeNames();
	    	        while(enumList.hasMoreElements())
	    	        {
	    	        	String modelname = enumList.nextElement();
	    	        	System.out.println("model "+modelname);
	    	        	Object model = request.getAttribute(modelname);
	    	        	if(model instanceof Serializable)
	    	        		mapper.writeValue(jg, model);
	    	        }   
	    	        System.out.println("json returned :: "+sw.toString());
	    	        return sw.toString();
	    	    }
		 
}
