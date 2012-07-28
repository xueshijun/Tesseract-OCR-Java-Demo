package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlparse.yihaodian.YiHaoDianItemPackage.ItemGift;
import com.htmlparse.yihaodian.YiHaoDianItemPackage.ItemGiftInfo;

public class YiHaoDianItem  {
//	//商品编号
//	private String ItemID="";
//	//商家
//	private String ItemShopName=""; 
	//抢购价
	private String ItemQiangPrice="";
	//市场价
	private String ItemMarketPrice="";
	//赠送积分
	private String ItemGiftPoints="";
	//一号店价
	private String ItemShopPrice="";
	//库存状况
	private String ItemStoreStatus="";
	
	//促销-折扣
	private String ItemDiscount="";
	//促销-立减
	private String ItemSubstrite="";
	//促销-赠品
	private ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>();;
	//促销-优惠
	private String ItemYouHui=""; 
	//促销—换购 
	private String ItemRedemption="";
	
//	//商品名称
//	private String ItemName="";//暂不用
//	//主标题
//	private String ItemMainName="";
//	//子标题
//	private String ItemSubtitles=""; 
	//品牌
	private String ItemBrand="";
	//上架时间
	private String ItemOnShelfDate="";
	//补充信息
	private String ItemDetails="";
	//包装信息
	private String ItemWrapper="";
	
	
	
	public YiHaoDianItem(){
		
	}  
	/**
	 * 商品标题 
	 */
	public String getItemMainName(Document doc){ 
			if(doc!=null){    
				Element link=doc.select(".wrap>.produce.clearfix>.p_title>h2>font#productMainName").first(); 
				return link.text();
			}
			return null;
	}
	/**
	 * 商品子标题 
	 */
	public String getItemSubtitles(Document doc){ 
		if(doc!=null){    
			Element link=doc.select(".wrap>.produce.clearfix>.p_title>p#productSubtitle").first();
			return link.text();
		}
		return null;
	}
	/**
	 * 商品编号 
	 */
	public String getItemID(Document doc){ 
			if(doc!=null){    
				Element link=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl.clearfix>dd#productCode").first(); 
				return link.text();
			}
			return null;
	}
	/**
	 * 商家
	 * @param doc
	 * @return
	 */
	private String ItemShopName(Document doc){ 
		if(doc!=null){ 
			Element link=null;
			//旗舰店：http://www.yihaodian.com/product/3696739_5201
			if((link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd").first().getElementsByTag("a").first())!=null){
				 return link.text(); 
			}else{
				//普通店：http://www.yihaodian.com/product/16082_1
				link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd").first();
				return link.text(); 
			} 
		}
		return null;
	}
	
	
	
	
	/**
	 * 抢购价、一号店价、市场价、库存
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
						case 0://抢?购 418元 抢完为止
							link1=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>.price>strong>span#productFacadePrice").first();							
							ItemQiangPrice=link1.text();
							break;
						case 1://1号店?价： 488元 降价通知 赠送积分：130
							link2=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>span#nonMemberPrice").first();
							ItemShopPrice=link2.text();
							link3=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>span.gift_points").first();
							ItemGiftPoints=link3.text();
							break; 
						case 2://市??场?价： 568元
							link4=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd>del.old_price").first();
							ItemMarketPrice=link4.text();
							break;
						case 3://库存状况：
							link5=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl>dd#stockDesc").first();				System.out.println(link.toString());
							ItemStoreStatus=link5.text();
							break; 
					}   
				} 
			} 
		}	
	}
	 
	/**
	 * 抢购价
	 * @return
	 */
	private String ItemQiangPrice(){
		return this.ItemQiangPrice;
	}
	
	/**
	 * 市场价
	 * @return
	 */
	private String ItemMarketPrice(){
		return this.ItemMarketPrice;
	}
	/**
	 * 赠送积分
	 * @return
	 */
	private String ItemGiftPoints(){
		return this.ItemGiftPoints;
	}
	/**
	 * 一号店价
	 * @return
	 */
	private String ItemShopPrice(){
		return this.ItemShopPrice;
	}
	/**
	 * 库存状况
	 * @return
	 */ 
	private String ItemStoreStatus(){
		return this.ItemStoreStatus;
	}
	
	
	/**
	 * 折扣
	 * 立减
	 * 赠品
	 * 优惠
	 * 换购
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
					case 0://折扣
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_zk>.promo_cen").first();
						Elements link1=link.select("div#promotion_zk>.promo_cen");
						System.out.println("折扣"); 
						System.out.println(link1.toString());
						System.out.println("=========================================================================================================================================================================="); 
						break;
						
					case 1://立减
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_lj>.promo_cen").first();
						Elements link2=link.select("div#promotion_lj>.promo_cen");
						System.out.println("立减");
						System.out.println(link2.toString());
						System.out.println("==============================================================================================================================================================================================================================================");
						break;
						
					case 2://赠品 
						System.out.println("赠品");
						//遍历.gift_box
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						Elements links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							//赠品主题h3>adoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>h3>a").first();
							Elements linkTitle=link_gift_box.select("h3>a");
							System.out.println("赠品主题");
							
							System.out.println(linkTitle.text()); 
							//赠品列表ul>lidoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li");
							Elements linkItemGift=link_gift_box.select("ul>li");
							System.out.println("赠品列表:"+ linkItemGift.size()+"件");  
							//遍历<li>单件赠品信息</li>
//							ArrayList<ItemGift> arrayListItemGift=new ArrayList<ItemGift>();
							ArrayList arrayListItemGift=new ArrayList(); 
							for(Element linkSub:linkItemGift){ 
										//赠品图片信息
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a");
										Elements linkItemGiftImg_A=linkSub.select("a"); 
										System.out.println("图片超链");
										System.out.println(linkItemGiftImg_A.attr("href")); 
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a>img");
										Elements linkItemGiftImg=linkSub.select("a>img");
										System.out.println("图片标题");		 
										System.out.println(linkItemGiftImg.attr("title"));
										System.out.println("图片路径");
										System.out.println(linkItemGiftImg.attr("original"));
										System.out.println("赠品图片中包含的赠品名");
										System.out.println(linkItemGiftImg.attr("title")); //  
									 
									//赠品名<h4></h4>
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>h4>a")
									Elements linkItemGiftName=linkSub.select("h4>a");
									System.out.println("赠品所在网址");
									System.out.println(linkItemGiftName.attr("href")); 
									System.out.println("赠品名 ");
									System.out.println(linkItemGiftName.attr("title") ); 
									//.info>.price>del
//									select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.price>del");

									System.out.println("赠品原价");
									System.out.println(linkSub.select(".info>.price>del").text()); 
//									Elements linkItemGiftFreePrice=linkSub.select(".info>.price>del");
									
									System.out.println("赠品现价");
									System.out.println(linkSub.select(".info>.price>strong").text());
									
									//doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.limit>strong");
									Elements linkItemGiftLimit=linkSub.select(".info>.limit>strong");
									System.out.println("赠品限量()");
									System.out.println(linkItemGiftLimit.text()); 
									
									System.out.println("赠品状态（已售完/领取）");
//									doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>a");
									Elements linkItemGiftStatus=linkSub.select(".info>a");
									
									boolean bol=false;
									if(linkItemGiftStatus.text()=="已售完"){ 
										bol=false;
									}
									else{
										bol=true;
									}
									System.out.println(bol);
									
									System.out.println("商品标题");
									//采用名称最长的那个作为商品标题
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
							//促销-赠品
//						ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>();
						ArrayList itemGiftInfo=new ArrayList();
							//增加一条商品赠品主题(系列)
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
					case 3://优惠
						System.out.println("优惠");
						Elements link3=link.select("div#promotion_yh>.promo_cen");
						
						System.out.println(link3.toString());
						System.out.println("==========================================================================================================================================================================");
						break;
					case 4://换购
						System.out.println("换购"); 
						//遍历.gift_box
//						doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						Elements links_gift_boxs=link.select("div#product_redemption>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_boxs){
							//换购品主题h3>adoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>h3>a").first();
							Elements linkTitle=link_gift_box.select("h3>a");
							System.out.println("换购主题");
							
							System.out.println(linkTitle.text()); 
							//换购品列表ul>lidoc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li");
							Elements linkItemGift=link_gift_box.select("ul>li");
							System.out.println("换购列表:"+ linkItemGift.size()+"件");  
							//遍历<li>单件换购品信息</li>
//							ArrayList<ItemGift> arrayListItemGift=new ArrayList<ItemGift>();
							ArrayList arrayListItemGift=new ArrayList(); 
							for(Element linkSub:linkItemGift){ 
										//换购品图片信息
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a");
										Elements linkItemGiftImg_A=linkSub.select("a.pic"); 
										System.out.println("图片超链");
										System.out.println(linkItemGiftImg_A.attr("href")); 
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>a>img");
										Elements linkItemGiftImg=linkSub.select("a>img");
										System.out.println("图片标题");		 
										System.out.println(linkItemGiftImg.attr("title"));
										System.out.println("图片路径");
										System.out.println(linkItemGiftImg.attr("original"));
										System.out.println("换购图片中包含的换购名");
										System.out.println(linkItemGiftImg.attr("title")); //  
									 
									//换购品名<h4></h4>
//										doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>h4>a")
									Elements linkItemGiftName=linkSub.select("div.info>h4>a");
									System.out.println("换购品所在网址");
									System.out.println(linkItemGiftName.attr("href")); 
									System.out.println("换购品名 ");
									System.out.println(linkItemGiftName.attr("title") ); 
									//.info>.price>del
//									select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.price>del");

									System.out.println("换购品原价");
									System.out.println(linkSub.select(".info>.price>del").text()); 
//									Elements linkItemGiftFreePrice=linkSub.select(".info>.price>del");
									
									System.out.println("换购品现价");
									System.out.println(linkSub.select(".info>.price>strong").text());
									
									
									System.out.println("换购品限量");									
									//doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>.limit>strong");
									Elements linkItemGiftLimit=linkSub.select("div.info>div.limit>strong");
									System.out.println(linkItemGiftLimit.text()); 
									
									System.out.println("换购品状态（??/换购）");
//									doc.select(".wrap>.produce.clearfix>.property.property_box>.promotion>dl#detail_promotion>dd>.promo_list>ul.promo_title.clearfix>li>div#promotion_gift>.promo_cen>.gift_list>.gift_box>ul>li>.info>a");
									Elements linkItemGiftStatus=linkSub.select(".info>a");
									
									boolean bol=false;
									if(linkItemGiftStatus.text()!="换购"){ 
										bol=false;
									}
									else{
										bol=true;
									}
									System.out.println(bol);
									
//									System.out.println("商品标题");
									//采用名称最长的那个作为商品标题
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
							//促销-赠品
						ArrayList<ItemGiftInfo> itemGiftInfo=new ArrayList<ItemGiftInfo>(); 
							//增加一条商品赠品主题(系列)
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
	
//	//促销-折扣
//	private String ItemDiscount="";
//	//促销-立减
//	private String ItemSubstrite="";  
//	//促销-优惠
//	private String ItemYouHui=""; 
//	//促销—换购 
//	private String ItemRedemption="";
//	
//	//商品名称
//	private String ItemName="";
//	//品牌
//	private String ItemBrand="";
//	//上架时间
//	private String ItemOnShelfDate="";
//	//补充信息
//	private String ItemDetails="";
//	//包装信息
//	private String ItemWrapper="";
	 
			
//	public  ArrayList getItemGift(Document doc){ 
//	}

 
	
	
	
	
	
}
