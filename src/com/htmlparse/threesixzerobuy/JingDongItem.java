package com.htmlparse.threesixzerobuy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;   
 
public class JingDongItem  {
 
	private String strMarketPrice="";//�г���
	private String strJingDongPrice="";//������
	private String strItemNumber="";//��Ʒ���
	private String strItemName="";//��Ʒ��
	private String strItemMadeArea="";//��Ʒ����
	private String strItemOnShelfDate="";//�ϼ�ʱ��
	private String strItemCompany="";//��������
	private String strItemWeight="";//��Ʒë��
	private ItemType strItemTopType=null;//��Ʒ�������� 
	
	private ArrayList<ItemType> listItemType=null; 
	private ArrayList<ItemImg> listItemImg=null;   
	
	public JingDongItem(Document doc) {  
		if(doc!=null){
			 initPrice(doc); 
			 initItemType(doc);
			 initItemDetails(doc);
		 }
	} 
	
	
	/**
	 * ��Ʒ����
	 * */
	public String getItemTitle(Document doc){
		if(doc!=null){   
			Element link=doc.select(".right-extra>#name>h1").first();
			return link.text();
		} 
		return "";
	}

	
	
	/*****************************
	 * ��ʼ���г��ۺ;�����
	 *****************************/
	public void initPrice(Document doc) {
		if(doc!=null){   
			Elements links=doc.select(".right-extra>#summary>li"); 
			int counter=0;
			for(Element link:links){ 
				switch(counter)
				{
					case 0://�г��� 
						strMarketPrice=link.getElementsByTag("del").text().replace("��", "");//�г��� 
						strItemNumber=link.getElementsByTag("span").text().replace("��Ʒ��ţ�","");//��Ʒ���span[style]
						break;
					case 1://������ 
						strJingDongPrice=link.select("img[src$=.png]").attr("src");  
						break;
					}  
					counter++;
					if(counter>1)break;
			}  
		}
	} 
	
	
	/**
	 * �г���
	 * @return
	 */
	public String getMarketPrice(){
		return this.strMarketPrice;
	}
	/**
	 * ������
	 * @return
	 */
	public String getJingDongPrice(){
		return this.strJingDongPrice;
	} 
 
	 
	/**
	 * ��ȡ��Ʒ���� 
	 */
	public int getItemScore(Document doc){
		if(doc!=null){   
			Element ItemId=doc.select(".right-extra>#summary>li.clearfix>div.fl>div").first();  
			return consumerScore(ItemId.attr("class").toString()); 
		} 
			return 0;
	}
	//��ʽ����Ʒ����
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
	 * ��ʼ����Ʒ����
	 ******************************/
	public void  initItemType(Document doc){
		if(doc!=null){ 
			listItemType = new ArrayList<ItemType>();
			Element link=doc.select("div.breadcrumb>strong").first();  
			strItemTopType=new ItemType(link.text(),link.attr("href"));
			
			Elements links=doc.select("div.breadcrumb>span>a");
 
			for(Element link1:links){ 
				listItemType.add(new ItemType(link1.text(),link1.attr("href")));
			}  
		} 
	}
	//��װ��Ʒ����
	public class ItemType{ 
		public String Url="";
		public String TypeName="";
		ItemType(String TypeName, String Url){
			this.Url=Url;
			this.TypeName=TypeName;
		}
	} 

	/***
	 * ��Ʒ����һ������
	 * @return
	 */
	public ItemType getTopItemType(){
		return this.strItemTopType;
	}
	/***
	 * ��Ʒ����ϸ��
	 * @return
	 */
	public  ArrayList<ItemType> getItemType(){
		return this.listItemType;
	}

	
	 
	/******************************
	 * ��ʼ����Ʒ��ϸ��Ϣ
	 ******************************/
	public void initItemDetails(Document doc){
		if(doc!=null){   
			Elements links=doc.select(".right-extra>#detail>div.mc.fore.tabcon>ul#i-detail>li");
			
	//		System.out.println(links.toString());
			int counter=0;
		    for(Element link:links){
		    	switch(counter){
		    	case 0://��Ʒ����
		    		this.strItemName=link.text().replace("��Ʒ���ƣ�", "");
		    		break;
		    	case 1://��������
		    		this.strItemMadeArea=link.text().replace("�������ң�", "");//
		    		break;
		    	case 2://��Ʒ����
		    		this.strItemCompany=link.text().replace("��Ʒ���أ�", "");//
		    		break;
		    	case 3://��Ʒë��
		    		this.strItemWeight=link.text().replace("��Ʒë�أ�", "");
		    		break;
		    	case 4://�ϼ�ʱ��
		    		this.strItemOnShelfDate=link.text().replace("�ϼ�ʱ�䣺", "");
		    		break; 
		    	} 
		    	if(counter++>4)
		    		break;
		    }
	    }
	}
	
	/**
	 * ��ȡ��Ʒ���
	 * @return
	 */
	public String getItemId(){ 
		return  this.strItemNumber;
	}	
	
	
	/***
	 * ��ȡ��Ʒ��
	 * **/
	public String getItemName(){ 
		return this.strItemName;
	}
	/**
	 * ��������
	 * @return
	 */
	public String getItemCompany(){
		return this.strItemMadeArea;}
	/**
	 * ��Ʒ����
	 * @return
	 */
	public String getItemMadeArea(){
		return this.strItemCompany;}
	/**
	 * �ϼ�ʱ��
	 * @return
	 */
	public String getItemOnShelfDate(){
		return this.strItemOnShelfDate;}
	/**
	 * ��Ʒë��
	 * @return
	 */
	public String getItemWeight(){return this.strItemWeight;}
	
	
	/**
	 * ��Ʒ��ͼ
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
	 
	//��ȡ��Ʒ��ͼ
	public ArrayList<ItemImg> getItemImg(Document doc){ 
		if(doc!=null){   
			listItemImg = new ArrayList<ItemImg>();
			Elements links=doc.select(".right-extra>div#preview>div#spec-n5>div#spec-list>ul.list-h>li>img[src]"); 
 
		    for(Element link:links){ 
		    	listItemImg.add( new ItemImg(  link.attr("src").trim(),	link.attr("src").replace("360buyimg.com/n5", "360buyimg.com/n0").trim())); 
		    } 
		    return listItemImg;
	    }  
		return listItemImg;
	}
//	/**
//	 * ����������*/
//	public void initComment(){
//		
//	}
	
	
	
	/**++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	
	/**
	 * ���첽���ݡ�
	 * ��ȡ��Ʒ�����еĴ�����Ϣ(����) 
	 * */
	public String getItemTitleAdvertiseWord(Document mobileDoc){
		try {
			if(mobileDoc!=null){   
				Element link=mobileDoc.select("body>.pro>font[color]").first(); 
				return link.text();
			} 
		}catch(Exception ex){
			return "";
		}
		return "";
	} 
	
	/**
	 * ���첽���ݡ�
	 * ��ȡ��Ʒ������Ϣ
	 * */
	public String  getItemSalesPromotionInfo(Document mobileDoc){
		try {
			if(mobileDoc!=null){   
				Element link=mobileDoc.select("body>.content.content2>div>font").first();    
				return  link.text(); 
			}  
		}catch(Exception ex){
			return "";
		}
		return "";
	}
	
	
	
	
	/**++++++++++++++++++++++++++++++++++++++++��ʱû�취��ȡ++++++++++++++++++++++++++++++++++++++++++++++++++++**/
	/**
	 * ���첽���ݡ�
	 * ��ȡ��Ʒ��Ϣ
	 * */
	public String  getItemLargessInfo(Document doc){
		try {
			if(doc!=null){   
				Element itemSalesPromotionInfo=doc.select(".right-extra>#summary>#liLargess").first();  
				return  itemSalesPromotionInfo.getElementsByTag("div").text(); 
			}  		
		}catch(Exception ex){
			return "";
		}
		return "";
	}


	public int getCommentNum(Document doc){
		if(doc!=null){   
			Element ItemId=doc.select(".right-extra>#summary>li.clearfix>div.fl>div").first();  
			return consumerScore(ItemId.attr("class").toString()); 
		} 
			return 0;
	}
	
} 

