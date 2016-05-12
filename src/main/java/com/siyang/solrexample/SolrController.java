package com.siyang.solrexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class SolrController {
	
	private final String url="http://localhost:8983/solr";
	private SolrServer server;
	
	private List<SolrInputDocument> docs;
	
	public SolrController(){
		server=new HttpSolrServer(url);
		docs=new ArrayList<SolrInputDocument>();
	}
	
	public void addField(String field, Object value){
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField(field, value);
		docs.add(doc);
	}
	
	public void addDocuments() throws SolrServerException, IOException{
		server.add(docs);
		server.commit();
	}
}
