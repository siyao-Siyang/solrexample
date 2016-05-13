package com.siyang.solrexample;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import javax.ws.rs.GET;

@Path("solr")
public class SolrClient {
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "jersey works";
	}
	
}
