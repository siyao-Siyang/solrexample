package com.siyang.solrexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrController {
	
	private final String url="http://localhost:8983/solr/core1";
	private SolrClient client;
	
	private Collection<SolrInputDocument> docs;
	
	public SolrController(){
		client=new HttpSolrClient(url);
		docs=new ArrayList<SolrInputDocument>();
	}
	
	public void excute(String field, Object value){
		try {
			SolrInputDocument doc=new SolrInputDocument();
			doc.addField(field, value);
			client.add(doc);
			client.commit();
		} catch (SolrServerException e) {
			System.out.println("Add documents: solrserver exception"+e.getMessage());
		} catch (IOException e) {
			System.out.println("Add documents: IO exception"+e.getMessage());
		}
		System.out.println("add field and commit");
	}

}
