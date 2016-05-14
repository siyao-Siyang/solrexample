package com.siyang.solrexample;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Path("solr")
public class SolrClient {
	
	private String response;
	private Gson gson;
	private JSONObject json;
	private Client client;
	private WebTarget wt;
	
	public SolrClient(){
		gson=new GsonBuilder().setPrettyPrinting().create();
		client=ClientBuilder.newClient();
	}
	
	/*
	 * get request: test
	 * */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "jersey works";
	}
	
	/*
	 * get request: retrieve all fields
	 * */
	@GET
	@Path("/{core}/schema/fields")
	public String getFields(@PathParam("core") String core){
		System.out.println("get all fields from "+core);
		wt=client.target("http://localhost:8983/solr/"+core+"/schema/fields");
		System.out.println(wt.getUri());
		response=wt.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}
	
	/*
	 * get request: search and query
	 * */
	@GET
	@Path("/{core}/search")
	@Produces(MediaType.APPLICATION_JSON)
	public String search(@QueryParam("q") String q, @PathParam("core") String core){
		System.out.println("search: "+"q="+q);
		wt=client.target("http://localhost:8983/solr/"+core+"/select").queryParam("indent", "on").queryParam("q", q).queryParam("wt", "json");
		System.out.println(wt.getUri());
		response=wt.request(MediaType.APPLICATION_JSON).get(String.class);
		return response;
	}
	
	/*
	 * post request: add new fields
	 * */
	@POST
	@Path("/{core}/schema/field")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addField(@PathParam("core") String core, String input){
		System.out.println("add field");
		WebTarget wt=client.target("http://localhost:8983/solr/"+core+"/schema");
		System.out.println("add filed: "+ wt.getUri());
		Response response=wt.request(MediaType.APPLICATION_JSON).post(Entity.json(input));
		return response.readEntity(String.class);
	}
}
