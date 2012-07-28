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
	
	//��ʼ��
	public TSZPage(String url){
		this.strUrl=url;  
		this.strMobileUrl=url.replace("http://www.360buy.com/", "http://m.360buy.com/");
	}
	 
	//��ʼ��
	public TSZPage(String url,String Old,String New){
		this.strUrl=url;  
		this.strMobileUrl=url.replace(Old, New);
	}
	
	/**��ȡҳ��URL*/
	public String getPageUrl() { 
		return this.strUrl;
	}  
	
	
	/**��ȡ�ƶ�ҳ��URL*/
	public String getMobilePageUrl() { 
		return this.strMobileUrl;
	} 	
	
	
	
	/**��ȡҳ��ͷ��*/
	public Element getPageHead(Document doc){
		if(doc!=null){ 
			return doc.head(); 
		}
		return null;
	}
	/**��ȡҳ��body*/
	public Element getPageBody(Document doc){
		if(doc!=null){ 
			return doc.body(); 
		}
		return null;
	}
	
	/**��ȡҳ�����*/
	public String getPageTitle(Document doc) { 
		if(doc!=null){ 
			return doc.title(); 
		}else{
			return "ҳ�治���ڻ�����̫����";
		}
	} 
	
	/**��ȡҳ��ؼ���*/
	public String getPageKeyWords(Document doc) {
		if(doc!=null){ 
			Element  link= doc.head().getElementsByAttributeValue("name", "Keywords").first();
			return link.attr("content");
		} 
		return null;  
	}	 
	
	/**��ȡҳ������*/
	public String getPageDescription(Document doc) { 
		if(doc!=null){ 
			Element  link= doc.head().getElementsByAttributeValue("name", "description").first();
			return link.attr("content");
		} 
		return null;  
	} 
	
}
