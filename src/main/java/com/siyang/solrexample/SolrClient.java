package com.siyang.solrexample;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("solr")
public class SolrClient {
	
	private String response;
	private Gson gson;
	private Client client;
	private WebTarget wt;
	
	public SolrClient(){
		gson=new GsonBuilder().setPrettyPrinting().create();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "jersey works";
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchById(@QueryParam("id") String id){
		System.out.println("search by id: "+id);
		client=ClientBuilder.newClient();
		wt=client.target("http://localhost:8983/solr/core2/select").queryParam("indent", "on").queryParam("q", "id:"+id).queryParam("wt", "json");
		System.out.println(wt.getUri());
		response=wt.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}
	
	
	@POST
	@Path("/field")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addField(String json){
		Field field=gson.fromJson(json, Field.class);
		displayField(field);
		
		//call schema api(add field)
		/*
		Client client=ClientBuilder.newClient();
		WebTarget wt=client.target("http://localhost:8983/solr/core1/schema");
		response = wt.request(MediaType.APPLICATION_JSON).post(Entity.json(json));*/
		return gson.toJson(response);
	}
	
	private void displayField(Field field){
		System.out.println(field.getName());
		System.out.println(field.getType());
		System.out.println(field.isIndexed());
		System.out.println(field.isStored());
		System.out.println(field.isMultiValued());
	}
}
