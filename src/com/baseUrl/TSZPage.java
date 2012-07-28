package com.baseUrl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class TSZPage extends PageAbstract {
	
	

	protected String strUrl=null; 
	protected String strMobileUrl=null; 
	
//	protected String strHead=null;
//	protected String strBody=null;	
//	
//	protected String pageTitle=null;
//	protected String pageDescription=null;
//	protected String pageKeyWords=null;
	
	//初始化
	public TSZPage(String url){
		this.strUrl=url;  
		this.strMobileUrl=url.replace("http://www.360buy.com/", "http://m.360buy.com/");
	}
	 
	//初始化
	public TSZPage(String url,String Old,String New){
		this.strUrl=url;  
		this.strMobileUrl=url.replace(Old, New);
	}
	
	/**获取页面URL*/
	public String getPageUrl() { 
		return this.strUrl;
	}  
	
	
	/**获取移动页面URL*/
	public String getMobilePageUrl() { 
		return this.strMobileUrl;
	} 	
	
	
	
	/**获取页面头部*/
	public Element getPageHead(Document doc){
		if(doc!=null){ 
			return doc.head(); 
		}
		return null;
	}
	/**获取页面body*/
	public Element getPageBody(Document doc){
		if(doc!=null){ 
			return doc.body(); 
		}
		return null;
	}
	
	/**获取页面标题*/
	public String getPageTitle(Document doc) { 
		if(doc!=null){ 
			return doc.title(); 
		}else{
			return "页面不存在或网速太慢！";
		}
	} 
	
	/**获取页面关键词*/
	public String getPageKeyWords(Document doc) {
		if(doc!=null){ 
			Element  link= doc.head().getElementsByAttributeValue("name", "Keywords").first();
			return link.attr("content");
		} 
		return null;  
	}	 
	
	/**获取页面描述*/
	public String getPageDescription(Document doc) { 
		if(doc!=null){ 
			Element  link= doc.head().getElementsByAttributeValue("name", "description").first();
			return link.attr("content");
		} 
		return null;  
	} 
	
}
