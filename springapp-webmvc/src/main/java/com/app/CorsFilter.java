package com.app;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This filter enables Cross-Origin Resource Sharing (CORS) by automatically responding
 * to all OPTIONS requests.
 */
public class CorsFilter extends OncePerRequestFilter {

	private String[] allowedServers = {"http://localhost:18180","http://localhost:8080","http://localhost:3000","http://localhost:8000"};
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String host = request.getHeader("Host");
		host = "http://"+host;
		String origin = request.getHeader("Origin");
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Accept, Content-Type");
			response.addHeader("Access-Control-Max-Age", "1800"); //30 min
		}
		// to be removed after testing
//		else if(origin!=null && isFromAllowedServers(origin))
		else if(origin!=null)
		{
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		filterChain.doFilter(request, response);
	}
	private boolean isFromAllowedServers(String origin) {
		
		for(String server:allowedServers)
		{
			if(origin.equalsIgnoreCase(server))
			{
				return true;
			}
		}
		
		return false;
	}

}
