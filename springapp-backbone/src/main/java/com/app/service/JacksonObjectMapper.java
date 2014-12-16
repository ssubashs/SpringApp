package com.app.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JacksonObjectMapper extends ObjectMapper{
//	private static final Logger log = Logger.getLogger(JacksonObjectMapper.class);
	 
	 public JacksonObjectMapper() {
	  configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	 }
}
