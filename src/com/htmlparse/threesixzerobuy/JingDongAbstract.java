package com.htmlparse.threesixzerobuy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.baseUrl.PageInterface;

public class JingDongAbstract implements PageInterface{
//	 
//	protected String strUrl=null; 
//	protected String strMobileUrl=null; 
	
//	protected String strHead=null;
//	protected String strBody=null;	
//	
//	protected String pageTitle=null;
//	protected String pageDescription=null;
//	protected String pageKeyWords=null;
	
	public JingDongAbstract(){ 
	}
	//初始化页面
	public Document initPage(String strUrl){  		
		return null; 
	} 
	/**获取页面URL*/
	public String getPageUrl() { 
		return null;
	}  
	
	
	/**获取移动页面URL*/
	public String getMobilePageUrl() { 
		return null;
	} 	
	/**获取页面头部*/
	public Element getPageHead(Document doc){ 
		return null;
	}
	/**获取页面body*/
	public Element getPageBody(Document doc){ 
		return null;
	}
	
	/**获取页面标题*/
	public String getPageTitle(Document doc) { 
		return null;
	} 
	
	/**获取页面关键词*/
	public String getPageKeyWords(Document doc) { 
		return null;  
	}	 
	
	/**获取页面描述*/
	public String getPageDescription(Document doc) { 
		return null;
	} 
	
}
