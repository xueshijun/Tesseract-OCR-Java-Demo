package com.htmlparse.threesixzerobuy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baseUrl.PageInterface;
import com.baseUrl.TSZPage;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemImg;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemType;

 

public class JingDong extends TSZPage{ 

	//ҳ�泬ʱ����
	private  int CONNECT_Time_OUT=3000;
	
	Document doc=null;
	Document mobileDoc=null;
	
	TSZPage page=null;
	JingDongItem jingDongItem;
 
	public JingDong(String url){
		super(url);
		if((page=new TSZPage(url))!=null){
			 
			if((doc=initPage())!=null){
				mobileDoc=initMobilePage();
				jingDongItem=new JingDongItem(doc);
			}
		}
	}

	/**
	 * ��ʼ��ҳ��
	 * @return
	 */
	public  Document initPage(){ 
			try {
				return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
			} catch (IOException e) {  
				System.out.println("��ʱ����̫��,"+CONNECT_Time_OUT+"��ǰ��ʱ����������[��һ���Զ�����]...");
				this.CONNECT_Time_OUT+=500;
				try {
					return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
				} catch (IOException e1) {
					System.out.println("��ʱ����̫��,"+CONNECT_Time_OUT+"��ǰ��ʱ����������[�ڶ����Զ�����]...");
					this.CONNECT_Time_OUT+=500;
					try {
						return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
					} catch (IOException ex) {
						System.out.println("��ʱ����̫��,"+CONNECT_Time_OUT+"��ǰ��ʱ�����Ѿ��������Σ������Զ����ӣ�");
						this.CONNECT_Time_OUT=500;
					}
				}
			}
			return null;
//			 return  Jsoup.connect(strUrl)
//			.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7") // ����User-Agent   
//          .timeout(3000) // �������ӳ�ʱʱ��   
//          .get();
			// "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)"
			//"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7";
//			Jsoup.connect("http://www.dajie.com/account/loginsubmit").data("d.email", "XXXXXXX").data("d.password", "XXXXXX").data("d.rememberMe", "1").method(Method.POST).execute();    
	} 
	 public final static String[] UserAgent = {
	        "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2",
	        "Mozilla/5.0 (iPad; U; CPU OS 3_2_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B500 Safari/531.21.11",
	        "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18121",
	        //http://blog.csdn.net/yjflinchong
	        "Nokia5700AP23.01/SymbianOS/9.1 Series60/3.0",
	        "UCWEB7.0.2.37/28/998",
	        "NOKIA5700/UCWEB7.0.2.37/28/977",
	        "Openwave/UCWEB7.0.2.37/28/978",
	        "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/989"
	    };
	
	/**
	 * ��ʼ���ƶ�ҳ��
	 * @return
	 */
	public Document initMobilePage(){ 
			try {
				return  Jsoup.connect(this.strMobileUrl).timeout(CONNECT_Time_OUT).get();
			} catch (IOException e) { 
//				e.printStackTrace();
				System.out.println("����̫������ʱ����̫��,"+CONNECT_Time_OUT+"�������ӳ�ʱ����...");
//				CONNECT_Time_OUT+=1000; 
				return null; 
			} 
	} 
	
	/**
	 * �������ӳ�ʱ����ʱ��
	 **/
	public int getConnectionTimeout() {
		return this.CONNECT_Time_OUT;
	}
	
	/**
	 * ��ȡ���ӳ�ʱ����ʱ��
	 * */
	public void setConnectionTimeout(int Timeout) {
		this.CONNECT_Time_OUT=Timeout;
	}
	
	/**��ȡҳ��URL*/
	public String getPageUrl() { 
		return page.getPageUrl();
	}  
	
	
	/**��ȡ�ƶ�ҳ��URL*/
	public String getMobilePageUrl() { 
		return page.getMobilePageUrl();
	} 	
	
	/**��ȡҳ��ͷ��*/
	public Element getPageHead(){ 
		return page.getPageHead(doc);
	}
	
	/**��ȡҳ��body*/
	public Element getPageBody(){ 
		return page.getPageBody(doc);
	}
	
	/**��ȡҳ�����*/
	public String getPageTitle() {  
		return page.getPageTitle(doc); 
	} 
	
	/**��ȡҳ��ؼ���*/
	public String getPageKeyWords() { 
		return page.getPageKeyWords(doc);  
	}	 
	
	/**��ȡҳ������*/
	public String getPageDescription() { 
		return page.getPageDescription(doc);
	} 
	
	
	
	/**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++**/
	
	
	//��ȡ��Ʒ���
	public String getItemId(){ 
		return jingDongItem.getItemId();
	}	
	
	
	/***
	 * ��Ʒ����(����) 
	 */
	public String getItemTitle() {
		return  jingDongItem.getItemTitle(doc);
	}
	
	
	//�г���
	public String getMarketPrice(){
		return jingDongItem.getMarketPrice();
	}
	//������
	public String getJingDongPrice(){
		return jingDongItem.getJingDongPrice();
	} 
	
//	//��Ʒ����
//	public int getItemCommentScore(){
//		return jdItem.getItemScore(doc);
//	}
	//��Ʒ����һ������
	public ItemType getTopItemType(){
		return  jingDongItem.getTopItemType();
	}
	//��Ʒ����ϸ��
	public ArrayList<ItemType> getItemType(){
		return  jingDongItem.getItemType();
	}
	
	//��Ʒ��
	public String getItemName( ){
		return jingDongItem.getItemName();
	}
	//��������
	public String getItemCompany(){
		return jingDongItem.getItemCompany();}
	//��Ʒ����
	public String getItemMadeArea(){
		return jingDongItem.getItemMadeArea();}
	//�ϼ�ʱ��
	public String getItemOnShelfDate(){
		return jingDongItem.getItemOnShelfDate();}
	//��Ʒë��
	public String getItemWeight(){
		return jingDongItem.getItemWeight();}
	
//	
	/**
	 * ����������������������������������������һ�����⣬���������
	 * ��ȡ��Ʒ��ͼ
	 * @return 
	 * @return
	 */
	public   ArrayList<ItemImg>  getItemImg(){
		return  jingDongItem.getItemImg(doc);
	}
//	
	
	 
	/**===========================*/ 
	
	/**
	 * ���첽���ݡ�
	 * ��ȡ��Ʒ�����еĴ�����Ϣ(����) 
	 * */
	public String getItemTitleAdvertiseWord(){
		return jingDongItem.getItemTitleAdvertiseWord(mobileDoc);
	}

	
	/**
	 * ���첽���ݡ�
	 * ��ȡ��Ʒ������Ϣ
	 * */
	public String  getItemSalesPromotionInfo(){
		return jingDongItem.getItemSalesPromotionInfo(mobileDoc);
	}

	
	/**
	 * ****************��BUG***************************
	 * 
	 * ���첽���ݡ�??????????????????????????
	 * ��ȡ��Ʒ��Ϣ
	 * */
	public String  getItemLargessInfo(){
		return jingDongItem.getItemLargessInfo(doc);
	}



}
