package com.app.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class SessionController 
{
	
	@RequestMapping(value = "/session",method = RequestMethod.GET)
	public ResponseEntity<String>  getSessionTime() 
	{
		return new ResponseEntity<String>("{\"sessiontimeout\":3600,\"alerttimeout\":300}",HttpStatus.OK);
	}
	
}
