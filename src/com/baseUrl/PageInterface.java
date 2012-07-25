package com.baseUrl;

import org.jsoup.nodes.Document;

public interface  PageInterface {
	
	public String strUrl="";
	public String pageTitle="";
	public String pageDescription="";
	public String pageKeyWords="";
	
	
	public String getPageUrl();	
//	public void setPageUrl();
	public String getPageTitle(Document doc);
//	public void setPageTitle();
	public String getPageDescription(Document doc);	
//	public void setPageDescription();
	public String getPageKeyWords(Document doc);
//	public void setPageKeyWords();

	 
}
