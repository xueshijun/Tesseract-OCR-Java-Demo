package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlparse.yihaodian.YiHaoDianItemPackage.ItemGift;
import com.htmlparse.yihaodian.YiHaoDianItemPackage.ItemGiftInfo;

public class YiHaoDianItem  {
//	//��Ʒ���
//	private String ItemID="";
//	//�̼�
//	private String ItemShopName=""; 
	//������
	private String ItemQiangPrice="";
	//�г���
	private String ItemMarketPrice="";
	//���ͻ���
	private String ItemGiftPoints="";
	//һ�ŵ��
	private String ItemShopPrice="";
	//���״��
	private String ItemStoreStatus="";
	
	//����-�ۿ�
	private String ItemDiscount="";
	//����-����
	private String ItemSubstrite="";
	//����-��Ʒ
	private ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>();;
	//����-�Ż�
	private String ItemYouHui=""; 
	//���������� 
	private String ItemRedemption="";
	
//	//��Ʒ����
//	private String ItemName="";//�ݲ���
//	//������
//	private String ItemMainName="";
//	//�ӱ���
//	private String ItemSubtitles=""; 
	//Ʒ��
	private String ItemBrand="";
	//�ϼ�ʱ��
	private String ItemOnShelfDate="";
	//������Ϣ
	private String ItemDetails="";
	//��װ��Ϣ
	private String ItemWrapper="";
	
	
	
	public YiHaoDianItem(){
		
	}  
	/**
	 * ��Ʒ���� 
	 */
	public String getItemMainName(Document doc){ 
			if(doc!=null){    
				Element link=doc.select(".wrap>.produce.clearfix>.p_title>h2>font#productMainName").first(); 
				return link.text();
			}
			return null;
	}
	/**
	 * ��Ʒ�ӱ��� 
	 */
	public String getItemSubtitles(Document doc){ 
		if(doc!=null){    
			Element link=doc.select(".wrap>.produce.clearfix>.p_title>p#productSubtitle").first();
			return link.text();
		}
		return null;
	}
	/**
	 * ��Ʒ��� 
	 */
	public String getItemID(Document doc){ 
			if(doc!=null){    
				Element link=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl.clearfix>dd#productCode").first(); 
				return link.text();
			}
			return null;
	}
	/**
	 * �̼�
	 * @param doc
	 * @return
	 */
	private String ItemShopName(Document doc){ 
		if(doc!=null){ 
			Element link=null;
			//�콢�꣺http://www.yihaodian.com/product/3696739_5201
			if((link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd").first().getElementsByTag("a").first())!=null){
				 return link.text(); 
			}else{
				//��ͨ�꣺http://www.yihaodian.com/product/16082_1
				link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd").first();
				return link.text(); 
			} 
		}
		return null;
	}
	
	
	
	
	/**
	 * �����ۡ�һ�ŵ�ۡ��г��ۡ����
	 * @param doc
	 */
	public void initPriceAndStore(Document doc){
		if(doc!=null){    
			if(doc!=null){ 
				int count=0;
				Element link1,link2,link3,link4,link5;
					Elements links=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1").first().getElementsByTag("dl");
				for(Element link:links){
					switch(count){  
						case 0://��?�� 418Ԫ ����Ϊֹ
							link1=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>.price>strong>span#productFacadePrice").first();							
							ItemQiangPrice=link1.text();
							break;
						case 1://1�ŵ�?�ۣ� 488Ԫ ����֪ͨ ���ͻ��֣�130
							link2=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>span#nonMemberPrice").first();
							ItemShopPrice=link2.text();
							link3=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>span.gift_points").first();
							ItemGiftPoints=link3.text();
							break; 
						case 2://��??��?�ۣ� 568Ԫ
							link4=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>del.old_price").first();
							ItemMarketPrice=link4.text();
							break;
						case 3://���״����
							link5=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd#stockDesc").first();				System.out.println(link.toString());
							ItemStoreStatus=link5.text();
							break; 
					}   
				} 
			} 
		}	
	}
	 
	/**
	 * ������
	 * @return
	 */
	private String ItemQiangPrice(){
		return this.ItemQiangPrice;
	}
	
	/**
	 * �г���
	 * @return
	 */
	private String ItemMarketPrice(){
		return this.ItemMarketPrice;
	}
	/**
	 * ���ͻ���
	 * @return
	 */
	private String ItemGiftPoints(){
		return this.ItemGiftPoints;
	}
	/**
	 * һ�ŵ��
	 * @return
	 */
	private String ItemShopPrice(){
		return this.ItemShopPrice;
	}
	/**
	 * ���״��
	 * @return
	 */ 
	private String ItemStoreStatus(){
		return this.ItemStoreStatus;
	}
	
	
	/**
	 * �ۿ�
	 * ����
	 * ��Ʒ
	 * �Ż�
	 * ����
	 * @param doc
	 */
	private void initPromotion(Document doc){
		if(doc!=null){ 
			Elements links=doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li");				
			int count=0;
//			Element link1,link2;
			System.out.println(links.size());
			for(Element link:links){
				switch(count){
					case 0://�ۿ�
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_zk>.promo_cen").first();
						Elements link1=link.select("div#promotion_zk>.promo_cen");
						System.out.println("�ۿ�"); 
						System.out.println(link1.toString());
						System.out.println("=========================================================================================================================================================================="); 
						break;
						
					case 1://����
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_lj>.promo_cen").first();
						Elements link2=link.select("div#promotion_lj>.promo_cen");
						System.out.println("����");
						System.out.println(link2.toString());
						System.out.println("==============================================================================================================================================================================================================================================");
						break;
						
					case 2://��Ʒ 
						System.out.println("��Ʒ");
						//����.gift_box
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						Elements links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							//��Ʒ����h3>adoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>h3>a").first();
							Elements linkTitle=link_gift_box.select("h3>a");
							System.out.println("��Ʒ����");
							
							System.out.println(linkTitle.text()); 
							//��Ʒ�б�ul>lidoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li");
							Elements linkItemGift=link_gift_box.select("ul>li");
							System.out.println("��Ʒ�б�:"+ linkItemGift.size()+"��");  
							//����<li>������Ʒ��Ϣ</li>
//							ArrayList<ItemGift> arrayListItemGift=new ArrayList<ItemGift>();
							ArrayList arrayListItemGift=new ArrayList(); 
							for(Element linkSub:linkItemGift){ 
										//��ƷͼƬ��Ϣ
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a");
										Elements linkItemGiftImg_A=linkSub.select("a"); 
										System.out.println("ͼƬ����");
										System.out.println(linkItemGiftImg_A.attr("href")); 
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a>img");
										Elements linkItemGiftImg=linkSub.select("a>img");
										System.out.println("ͼƬ����");		 
										System.out.println(linkItemGiftImg.attr("title"));
										System.out.println("ͼƬ·��");
										System.out.println(linkItemGiftImg.attr("original"));
										System.out.println("��ƷͼƬ�а�������Ʒ��");
										System.out.println(linkItemGiftImg.attr("title")); //  
									 
									//��Ʒ��<h4></h4>
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>h4>a")
									Elements linkItemGiftName=linkSub.select("h4>a");
									System.out.println("��Ʒ������ַ");
									System.out.println(linkItemGiftName.attr("href")); 
									System.out.println("��Ʒ�� ");
									System.out.println(linkItemGiftName.attr("title") ); 
									//.info>.price>del
//									select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.price>del");

									System.out.println("��Ʒԭ��");
									System.out.println(linkSub.select(".info>.price>del").text()); 
//									Elements linkItemGiftFreePrice=linkSub.select(".info>.price>del");
									
									System.out.println("��Ʒ�ּ�");
									System.out.println(linkSub.select(".info>.price>strong").text());
									
									//doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.limit>strong");
									Elements linkItemGiftLimit=linkSub.select(".info>.limit>strong");
									System.out.println("��Ʒ����()");
									System.out.println(linkItemGiftLimit.text()); 
									
									System.out.println("��Ʒ״̬��������/��ȡ��");
//									doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>a");
									Elements linkItemGiftStatus=linkSub.select(".info>a");
									
									boolean bol=false;
									if(linkItemGiftStatus.text()=="������"){ 
										bol=false;
									}
									else{
										bol=true;
									}
									System.out.println(bol);
									
									System.out.println("��Ʒ����");
									//������������Ǹ���Ϊ��Ʒ����
//									String ItemName=linkItemGiftImg.attr("title").length()>linkItemGiftName.attr("title").length()?linkItemGiftImg.attr("title"):linkItemGiftName.attr("title");
//									System.out.println(ItemName);
									
//									arrayListItemGift.add(
//											new ItemGift(
//													linkItemGiftName.attr("href") ,
//													ItemName ,linkItemGiftImg.attr("src") , 
//													linkItemGiftFreePrice.text(),
//													linkItemGiftLimit.text(), 
//													bol)
//											);
									System.out.println("---------------------------------------------------");
										
							} 
							//����-��Ʒ
//						ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>();
						ArrayList itemGiftInfo=new ArrayList();
							//����һ����Ʒ��Ʒ����(ϵ��)
//							itemGiftInfo.add(new ItemGiftInfo(
//									linkTitle.text(),
//									linkTitle.attr("href"),
//									arrayListItemGift
//									)
//							);
							System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}		
						System.out.println("==========================================================================================================================================================================");
						break;
					case 3://�Ż�
						System.out.println("�Ż�");
						Elements link3=link.select("div#promotion_yh>.promo_cen");
						
						System.out.println(link3.toString());
						System.out.println("==========================================================================================================================================================================");
						break;
					case 4://����
						System.out.println("����"); 
						//����.gift_box
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						Elements links_gift_boxs=link.select("div#product_redemption>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_boxs){
							//����Ʒ����h3>adoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>h3>a").first();
							Elements linkTitle=link_gift_box.select("h3>a");
							System.out.println("��������");
							
							System.out.println(linkTitle.text()); 
							//����Ʒ�б�ul>lidoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li");
							Elements linkItemGift=link_gift_box.select("ul>li");
							System.out.println("�����б�:"+ linkItemGift.size()+"��");  
							//����<li>��������Ʒ��Ϣ</li>
//							ArrayList<ItemGift> arrayListItemGift=new ArrayList<ItemGift>();
							ArrayList arrayListItemGift=new ArrayList(); 
							for(Element linkSub:linkItemGift){ 
										//����ƷͼƬ��Ϣ
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a");
										Elements linkItemGiftImg_A=linkSub.select("a.pic"); 
										System.out.println("ͼƬ����");
										System.out.println(linkItemGiftImg_A.attr("href")); 
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a>img");
										Elements linkItemGiftImg=linkSub.select("a>img");
										System.out.println("ͼƬ����");		 
										System.out.println(linkItemGiftImg.attr("title"));
										System.out.println("ͼƬ·��");
										System.out.println(linkItemGiftImg.attr("original"));
										System.out.println("����ͼƬ�а����Ļ�����");
										System.out.println(linkItemGiftImg.attr("title")); //  
									 
									//����Ʒ��<h4></h4>
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>h4>a")
									Elements linkItemGiftName=linkSub.select("div.info>h4>a");
									System.out.println("����Ʒ������ַ");
									System.out.println(linkItemGiftName.attr("href")); 
									System.out.println("����Ʒ�� ");
									System.out.println(linkItemGiftName.attr("title") ); 
									//.info>.price>del
//									select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.price>del");

									System.out.println("����Ʒԭ��");
									System.out.println(linkSub.select(".info>.price>del").text()); 
//									Elements linkItemGiftFreePrice=linkSub.select(".info>.price>del");
									
									System.out.println("����Ʒ�ּ�");
									System.out.println(linkSub.select(".info>.price>strong").text());
									
									
									System.out.println("����Ʒ����");									
									//doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.limit>strong");
									Elements linkItemGiftLimit=linkSub.select("div.info>div.limit>strong");
									System.out.println(linkItemGiftLimit.text()); 
									
									System.out.println("����Ʒ״̬��??/������");
//									doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>a");
									Elements linkItemGiftStatus=linkSub.select(".info>a");
									
									boolean bol=false;
									if(linkItemGiftStatus.text()!="����"){ 
										bol=false;
									}
									else{
										bol=true;
									}
									System.out.println(bol);
									
//									System.out.println("��Ʒ����");
									//������������Ǹ���Ϊ��Ʒ����
//									String ItemName=linkItemGiftImg.attr("title").length()>linkItemGiftName.attr("title").length()?linkItemGiftImg.attr("title"):linkItemGiftName.attr("title");
//									System.out.println(ItemName);
									
//									arrayListItemGift.add(
//											new ItemGift(
//													linkItemGiftName.attr("href") ,
//													ItemName ,linkItemGiftImg.attr("src") , 
//													linkItemGiftFreePrice.text(),
//													linkItemGiftLimit.text(), 
//													bol)
//											);
									System.out.println("---------------------------------------------------");
										
							} 
							//����-��Ʒ
						ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>(); 
							//����һ����Ʒ��Ʒ����(ϵ��)
//							itemGiftInfo.add(new ItemGiftInfo(
//									linkTitle.text(),
//									linkTitle.attr("href"),
//									arrayListItemGift
//									)
//							);
							System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}		
						System.out.println("============================================================================================================================================================================================================");
						break;
					}  
					count++;
				}  
		}
	} 
	
//	//����-�ۿ�
//	private String ItemDiscount="";
//	//����-����
//	private String ItemSubstrite="";  
//	//����-�Ż�
//	private String ItemYouHui=""; 
//	//���������� 
//	private String ItemRedemption="";
//	
//	//��Ʒ����
//	private String ItemName="";
//	//Ʒ��
//	private String ItemBrand="";
//	//�ϼ�ʱ��
//	private String ItemOnShelfDate="";
//	//������Ϣ
//	private String ItemDetails="";
//	//��װ��Ϣ
//	private String ItemWrapper="";
	 
			
//	public  ArrayList getItemGift(Document doc){ 
//	}

 
	
	
	
	
	
}
