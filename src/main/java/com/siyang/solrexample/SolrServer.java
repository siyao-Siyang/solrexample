package com.siyang.solrexample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.sun.net.httpserver.*;

@SuppressWarnings("restriction")
public class SolrServer {
	
	public void startServer() throws IllegalArgumentException, IOException{
		HttpServer server=createHttpServer();
		//server.start();
		System.out.println(String.format("server starts: %s", getURL()));
	}
	
	private HttpServer createHttpServer() throws IllegalArgumentException, IOException{
		ResourceConfig rc=new ResourceConfig(SolrClient.class);
		return JdkHttpServerFactory.createHttpServer(getURL(), rc);
	}
	
	private URI getURL(){
		return UriBuilder.fromUri("http://"+getHostName()+"/").port(8085).build();
	}
	
	private String getHostName(){
		String hostname="localhost";
		try {
			hostname=InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("get host name wrong: "+e.getMessage());
		}
		System.out.println("host name: "+hostname);
		return hostname;
	}
}
