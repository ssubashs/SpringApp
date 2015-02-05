package com.app.remoteejb;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class HttpTest {
	
	@Test
	public void calservice()
	{
		String url = "http://webmail.taf1.farmersagent.com/icalphp/minical.php?userid=icmsyy03@taf1.farmersagent.com&date=20150106";
		 HttpHost target = new HttpHost("webmail.taf1.farmersagent.com");
		 
	        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	                new AuthScope(target.getHostName(), target.getPort()),
	                new UsernamePasswordCredentials("admin.sso@taf1.farmersagent.com", "password"));
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider).build();
	        try {

	            // Create AuthCache instance
	            AuthCache authCache = new BasicAuthCache();
	            // Generate BASIC scheme object and add it to the local
	            // auth cache
	            BasicScheme basicAuth = new BasicScheme();
	            authCache.put(target, basicAuth);

	            // Add AuthCache to the execution context
	            HttpClientContext localContext = HttpClientContext.create();
	            localContext.setAuthCache(authCache);

	            HttpGet httpget = new HttpGet("/icalphp/minical.php?userid=icmsyy03@taf1.farmersagent.com&date=20150106");

	            System.out.println("Executing request " + httpget.getRequestLine() + " to target " + target);
	            for (int i = 0; i < 3; i++) {
	                CloseableHttpResponse response = httpclient.execute(target, httpget, localContext);
	                try {
	                    System.out.println("----------------------------------------");
	                    System.out.println(response.getStatusLine());
	                    EntityUtils.consume(response.getEntity());
	                } finally {
	                    response.close();
	                }
	            }
	        }catch(Exception exc)
	        {
	        	
	        }	        
	        finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}

}
