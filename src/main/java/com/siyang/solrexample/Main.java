package com.siyang.solrexample;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		//non-finished
		SolrController sc=new SolrController();
		List<String> cat=new ArrayList<String>();
		cat.add("book");
		cat.add("hardcover");
		
		sc.excute("id", "1");
		sc.excute("cat", cat);
	}
}
