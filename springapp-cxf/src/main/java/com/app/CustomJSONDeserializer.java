package com.app;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.app.domain.Person;

public class CustomJSONDeserializer extends JsonDeserializer<Person>{

	@Override
	public Person deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		// 
		 ObjectCodec oc = parser.getCodec();
	        JsonNode node = oc.readTree(parser);
		return null;
	}

}
