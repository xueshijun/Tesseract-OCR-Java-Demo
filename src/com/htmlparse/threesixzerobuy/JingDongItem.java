package com.htmlparse.threesixzerobuy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 

public class JingDongItem  {
 
	private String strMarketPrice=null;//市场价
	private String strJingDongPrice=null;//京东价
	private String strItemNumber=null;//商品编号
	private String strItemName=null;//商品名
	private String strItemMadeArea=null;//商品产地
	private String strItemOnShelfDate=null;//上架时间
	private String strItemCompany=null;//生产厂家
	private String strItemWeight=null;//商品毛重
	private ItemType strItemTopType=null;//商品顶级分类
	public int  intItemTypeCount=4;
	private ItemType []strItemType=new ItemType[intItemTypeCount];//商品分类
	public  int  intItemImgCount=5;
	private ItemImg [] strItemImg=new ItemImg[intItemImgCount];//商品配图 
	
	 
//	public JingDongItem(String url) { 
//		super(url);
//	} 
 
	public JingDongItem(Document doc) { 
//		this.strUrl=super.strUrl;
		 initPrice(doc); 
		 initItemType(doc);
		 initItemDetails(doc);
	} 
	
	
	/**
	 * 商品标题
	 * */
	public String getItemTitle(Document doc){
		if(doc!=null){   
			Element link=doc.select(".right-extra>#name>h1").first();
			return link.text();
		} 
		return null;
	}

	
	
	/*****************************
	 * 初始化市场价和京东价
	 *****************************/
	public void initPrice(Document doc) {
		if(doc!=null){   
			Elements links=doc.select(".right-extra>#summary>li"); 
			int counter=0;
			for(Element link:links){ 
				switch(counter)
				{
					case 0://市场价
//						Elements docMarketPrice=doc.select(".right-extra>#summary>li"+">del");
						String str=link.getElementsByTag("del").text();//市场价
						strMarketPrice=str.replace("￥", "");
						strItemNumber=link.getElementsByTag("span").text().replace("商品编号：","");//商品编号span[style]
						break;
					case 1://京东价
						Elements docJDPriceSelector=doc.select(".right-extra>#summary>li");
						Elements pngJDPrice =  docJDPriceSelector.select("img[src$=.png]");
						strJingDongPrice=pngJDPrice.attr("src");  
						break;
					}  
					counter++;
					if(counter>1)break;
			}  
		}
	} 
	
	
	/**
	 * 市场价
	 * @return
	 */
	public String getMarketPrice(){
		return this.strMarketPrice;
	}
	/**
	 * 京东价
	 * @return
	 */
	public String getJingDongPrice(){
		return this.strJingDongPrice;
	} 
 
	 
	/**
	 * 获取商品评分 
	 */
	public int getItemScore(Document doc){
		if(doc!=null){   
			Element ItemId=doc.select(".right-extra>#summary>li.clearfix>div.fl>div").first();  
			return consumerScore(ItemId.attr("class").toString()); 
		} 
			return 0;
	}
	//格式化商品评分
	public int consumerScore(String strScore){ 
		if(strScore.equals("star sa1")){
			return 1;}
		else if(strScore.equals("star sa2")){
			return 2;}
		else if(strScore.equals("star sa3")){
			return 3;}
		else if(strScore.equals("star sa4")){
			return 4;}
		else if(strScore.equals("star sa5")){
			return 5;} 
		else{
			return 0;}
	}
	
	
	
	
	
	/******************************
	 * 初始化商品分类
	 ******************************/
	public void  initItemType(Document doc){
		if(doc!=null){ 
			Element link=doc.select("div.breadcrumb>strong").first();  
			strItemTopType=new ItemType(link.text(),link.attr("href"));
			
			Elements links=doc.select("div.breadcrumb>span>a");
			int counter=0;
			for(Element link1:links){ 
				strItemType[counter++]=new ItemType(link1.text(),link1.attr("href"));  
				if(counter>=intItemTypeCount)
					break;
			} 	
		} 
	}
	//封装商品类型
	public class ItemType{ 
		public String Url="";
		public String TypeName="";
		ItemType(String TypeName, String Url){
			this.Url=Url;
			this.TypeName=TypeName;
		}
	} 

	/***
	 * 商品类型一级分类
	 * @return
	 */
	public ItemType getTopItemType(){
		return this.strItemTopType;
	}
	/***
	 * 商品类型细分
	 * @return
	 */
	public ItemType[] getItemType(){
		return this.strItemType;
	}

	
	 
	/******************************
	 * 初始化商品详细信息
	 ******************************/
	public void initItemDetails(Document doc){
		if(doc!=null){   
			Elements links=doc.select(".right-extra>#detail>div.mc.fore.tabcon>ul#i-detail>li");
			
	//		System.out.println(links.toString());
			int counter=0;
		    for(Element link:links){
		    	switch(counter){
		    	case 0://商品名称
		    		this.strItemName=link.text().replace("商品名称：", "");
		    		break;
		    	case 1://生产厂家
		    		this.strItemMadeArea=link.text().replace("生产厂家：", "");//
		    		break;
		    	case 2://商品产地
		    		this.strItemCompany=link.text().replace("商品产地：", "");//
		    		break;
		    	case 3://商品毛重
		    		this.strItemWeight=link.text().replace("商品毛重：", "");
		    		break;
		    	case 4://上架时间
		    		this.strItemOnShelfDate=link.text().replace("上架时间：", "");
		    		break; 
		    	} 
		    	if(counter++>4)
		    		break;
		    }
	    }
	}
	
	/**
	 * 获取商品编号
	 * @return
	 */
	public String getItemId(){ 
		return  this.strItemNumber;
	}	
	
	
	/***
	 * 获取商品名
	 * **/
	public String getItemName(){ 
		return this.strItemName;
	}
	/**
	 * 生产厂家
	 * @return
	 */
	public String getItemCompany(){return this.strItemMadeArea;}
	/**
	 * 商品产地
	 * @return
	 */
	public String getItemMadeArea(){return this.strItemCompany;}
	/**
	 * 上架时间
	 * @return
	 */
	public String getItemOnShelfDate(){return this.strItemOnShelfDate;}
	/**
	 * 商品毛重
	 * @return
	 */
	public String getItemWeight(){return this.strItemWeight;}
	
	
	/**
	 * 商品配图
	 * BIG//http://img13.360buyimg.com/n0/5506/eabb8265-67d5-4744-a72a-52e168629f97.jpg
	 * SMALL//http://img13.360buyimg.com/n5/5506/eabb8265-67d5-4744-a72a-52e168629f97.jpg
	 **/
	public class ItemImg{ 
		protected String strBigImgSrc;
		protected String strSmallImgSrc; 
		public ItemImg(String strSmallImgSrc,String strBigImgSrc){
			this.strBigImgSrc=strBigImgSrc;
			this.strSmallImgSrc=strSmallImgSrc;
	}
		public String getBigImgSrc(){
			return this.strBigImgSrc;
		}
		public String getSmallImgSrc(){
			return this.strSmallImgSrc;
		}
	}
	 
	//获取商品配图
	public ItemImg[] getItemImg(Document doc){ 
		if(doc!=null){   
			Elements links=doc.select(".right-extra>div#preview>div#spec-n5>div#spec-list>ul.list-h>li>img[src]"); 
			int counter=0;
		    for(Element link:links){  
		    	strItemImg[counter++]=new ItemImg(
		    			 link.attr("src").trim(),
		    			 link.attr("src").replace("360buyimg.com/n5", "360buyimg.com/n0").trim());
		    	 if(counter>(--intItemImgCount))//只存intItemImgCount个，多余的不处理。
		    		 break;
		    }
		    return strItemImg;
	    }  
		return null;
	}
//	/**
//	 * 消费者评论*/
//	public void initComment(){
//		
//	}
	
	
	
	/**++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	
	/**
	 * 【异步数据】
	 * 获取商品标题中的促销信息(广告词) 
	 * */
	public String getItemTitleAdvertiseWord(Document mobileDoc){
		try {
			if(mobileDoc!=null){   
				Element link=mobileDoc.select("body>.pro>font[color]").first(); 
				return link.text();
			} 
		}catch(Exception ex){
			return null;
		}
		return null;
	}
	
	

	
	/**
	 * 【异步数据】
	 * 获取商品促销信息
	 * */
	public String  getItemSalesPromotionInfo(Document mobileDoc){
		try {
			if(mobileDoc!=null){   
				Element link=mobileDoc.select("body>.content.content2>div>font").first();    
				return  link.text(); 
			}  
		}catch(Exception ex){
			return null;
		}
		return null;
	}
	
	
	
	
	/**++++++++++++++++++++++++++++++++++++++++暂时没办法获取++++++++++++++++++++++++++++++++++++++++++++++++++++**/
	/**
	 * 【异步数据】
	 * 获取赠品信息
	 * */
	public String  getItemLargessInfo(Document doc){
		try {
			if(doc!=null){   
				Element itemSalesPromotionInfo=doc.select(".right-extra>#summary>#liLargess").first();  
				return  itemSalesPromotionInfo.getElementsByTag("div").text(); 
			}  		
		}catch(Exception ex){
			return null;
		}
		return null;
	}


	public int getCommentNum(Document doc){
		if(doc!=null){   
			Element ItemId=doc.select(".right-extra>#summary>li.clearfix>div.fl>div").first();  
			return consumerScore(ItemId.attr("class").toString()); 
		} 
			return 0;
	}
	
} 

