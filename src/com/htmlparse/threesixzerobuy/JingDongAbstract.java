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
	//��ʼ��ҳ��
	public Document initPage(String strUrl){  		
		return null; 
	} 
	/**��ȡҳ��URL*/
	public String getPageUrl() { 
		return null;
	}  
	
	
	/**��ȡ�ƶ�ҳ��URL*/
	public String getMobilePageUrl() { 
		return null;
	} 	
	/**��ȡҳ��ͷ��*/
	public Element getPageHead(Document doc){ 
		return null;
	}
	/**��ȡҳ��body*/
	public Element getPageBody(Document doc){ 
		return null;
	}
	
	/**��ȡҳ�����*/
	public String getPageTitle(Document doc) { 
		return null;
	} 
	
	/**��ȡҳ��ؼ���*/
	public String getPageKeyWords(Document doc) { 
		return null;  
	}	 
	
	/**��ȡҳ������*/
	public String getPageDescription(Document doc) { 
		return null;
	} 
	
}
