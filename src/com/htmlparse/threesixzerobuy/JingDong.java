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

	//页面超时限制
	public static int CONNECT_Time_OUT=3000;
	
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
	 * 初始化页面
	 * @return
	 */
	public  Document initPage(){ 
			try {
				return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
			} catch (IOException e) {  
				System.out.println("超时限制太短,"+CONNECT_Time_OUT+"当前超时限制增加中[第一次自动增加]...");
				CONNECT_Time_OUT+=500;
				try {
					return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
				} catch (IOException e1) {
					System.out.println("超时限制太短,"+CONNECT_Time_OUT+"当前超时限制增加中[第二次自动增加]...");
					CONNECT_Time_OUT+=500;
					try {
						return  Jsoup.connect(this.strUrl).timeout(CONNECT_Time_OUT).get();
					} catch (IOException ex) {
						System.out.println("超时限制太短,"+CONNECT_Time_OUT+"当前超时限制已经增加两次，不再自动增加！");
					}
				}
			}
			return null;
//			 return  Jsoup.connect(strUrl)
//			.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7") // 设置User-Agent   
//          .timeout(3000) // 设置连接超时时间   
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
	 * 初始化移动页面
	 * @return
	 */
	public Document initMobilePage(){ 
			try {
				return  Jsoup.connect(this.strMobileUrl).timeout(CONNECT_Time_OUT).get();
			} catch (IOException e) { 
//				e.printStackTrace();
				System.out.println("网速太慢，超时限制太短,"+CONNECT_Time_OUT+"，请增加超时限制...");
//				CONNECT_Time_OUT+=1000; 
			}
			return null; 
	} 
	
	/**
	 * 设置连接超时限制时间
	 **/
	public int getConnectionTimeout() {
		return CONNECT_Time_OUT;
	}
	
	/**
	 * 获取连接超时限制时间
	 * */
	public void setConnectionTimeout(int Timeout) {
		CONNECT_Time_OUT=Timeout;
	}
	
	/**获取页面URL*/
	public String getPageUrl() { 
		return page.getPageUrl();
	}  
	
	
	/**获取移动页面URL*/
	public String getMobilePageUrl() { 
		return page.getMobilePageUrl();
	} 	
	
	/**获取页面头部*/
	public Element getPageHead(){ 
		return page.getPageHead(doc);
	}
	
	/**获取页面body*/
	public Element getPageBody(){ 
		return page.getPageBody(doc);
	}
	
	/**获取页面标题*/
	public String getPageTitle() {  
		return page.getPageTitle(doc); 
	} 
	
	/**获取页面关键词*/
	public String getPageKeyWords() { 
		return page.getPageKeyWords(doc);  
	}	 
	
	/**获取页面描述*/
	public String getPageDescription() { 
		return page.getPageDescription(doc);
	} 
	
	
	
	/**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++**/
	
	
	//获取商品编号
	public String getItemId(){ 
		return jingDongItem.getItemId();
	}	
	
	
	/***
	 * 商品标题(顶部) 
	 */
	public String getItemTitle() {
		return  jingDongItem.getItemTitle(doc);
	}
	
	
	//市场价
	public String getMarketPrice(){
		return jingDongItem.getMarketPrice();
	}
	//京东价
	public String getJingDongPrice(){
		return jingDongItem.getJingDongPrice();
	} 
	
//	//商品评分
//	public int getItemCommentScore(){
//		return jdItem.getItemScore(doc);
//	}
	//商品类型一级分类
	public ItemType getTopItemType(){
		return  jingDongItem.getTopItemType();
	}
	//商品类型细分
	public ArrayList<ItemType> getItemType(){
		return  jingDongItem.getItemType();
	}
	
	//商品名
	public String getItemName( ){
		return jingDongItem.getItemName();
	}
	//生产厂家
	public String getItemCompany(){
		return jingDongItem.getItemCompany();}
	//商品产地
	public String getItemMadeArea(){
		return jingDongItem.getItemMadeArea();}
	//上架时间
	public String getItemOnShelfDate(){
		return jingDongItem.getItemOnShelfDate();}
	//商品毛重
	public String getItemWeight(){
		return jingDongItem.getItemWeight();}
	
//	
	/**
	 * ？？？？？？？？？？？？？？？？？（存在一点问题，数组溢出）
	 * 获取商品配图
	 * @return 
	 * @return
	 */
	public   ArrayList<ItemImg>  getItemImg(){
		return  jingDongItem.getItemImg(doc);
	}
//	
	
	 
	/**===========================*/ 
	
	/**
	 * 【异步数据】
	 * 获取商品标题中的促销信息(广告词) 
	 * */
	public String getItemTitleAdvertiseWord(){
		return jingDongItem.getItemTitleAdvertiseWord(mobileDoc);
	}

	
	/**
	 * 【异步数据】
	 * 获取商品促销信息
	 * */
	public String  getItemSalesPromotionInfo(){
		return jingDongItem.getItemSalesPromotionInfo(mobileDoc);
	}

	
	/**
	 * ****************有BUG***************************
	 * 
	 * 【异步数据】??????????????????????????
	 * 获取赠品信息
	 * */
	public String  getItemLargessInfo(){
		return jingDongItem.getItemLargessInfo(doc);
	}



}
