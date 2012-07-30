package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 

public class YiHaoDianItem extends YiHaoDianItemPackage {
//	//商品编号
//	private String ItemID="";
	//商家
//	private String ItemShopName=""; 
	
	//商品分类
	private ArrayList<ItemType>  listItemType=null; 
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
	//运费信息
	private String ItemInfoDelivery="";
	//发货通知
	private String ItemDeliveryInfo="";
	
	//促销-折扣
	private String ItemDiscount="";
	//促销-立减
	private String ItemSubstrite="";
	//促销-赠品
	private ArrayList<ItemGiftInfo> listItemGiftInfo=null; 
	//促销-优惠
	private String ItemYouHui="";  
	//促销—换购  
	private ArrayList<ItemRedemptionInfo> listItemRedemptionInfo=null;
	
	
	
//	//商品名称
//	private String ItemName="";//暂不用
//	//主标题
//	private String ItemMainName="";
//	//子标题
//	private String ItemSubtitles="";

 
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
	 * 获取商品分类
	 * @param doc
	 * @return
	 */
	public ArrayList<ItemType> getItemType(Document doc){
		listItemType=new  ArrayList<ItemType> ();
		if(doc!=null){    
			Elements links=doc.select(".wrap>.detailnav>a"); 
			for(Element link:links){ 
				listItemType.add(new ItemType(link.text(),link.attr("href"))); 
			}
		}  
		return listItemType;
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
	private String getItemShopName(Document doc){ 
		if(doc!=null){ 
			Element link=null;
			//旗舰店：http://www.yihaodian.com/product/3696739_5201
			if((link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd>a").first())!=null){
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
	 * 抢购价、一号店价、市场价、库存、运费信息、发货通知
	 * @param doc
	 */
	public void initPriceAndStore(Document doc){
		if(doc!=null){    
			if(doc!=null){ 
				int count=0; 
				Elements links=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl");
				for(Element link:links){
					switch(count){  
						case 0://抢?购 418元 抢完为止 
							ItemQiangPrice=link.select("dl>dd>.price>strong>span#productFacadePrice").first().text();
							break;
						case 1://1号店?价： 488元 降价通知 赠送积分：130 
							ItemShopPrice=link.select("dl>dd>span#nonMemberPrice").first().text(); 
							ItemGiftPoints=link.select("dl>dd>span.gift_points").first().text();
							break; 
						case 2://市??场?价： 568元 
							ItemMarketPrice=link.select("dl>dd>del.old_price").first().text();
							break;
						case 3://库存状况： 
							ItemStoreStatus=link.select("dl>dd#stockDesc").first().text();
							break;
						case 4://配送信息productInfoDelivery
							Elements eLink=link.select("dl#productInfoDelivery>dd>ul#sfi>li");
							int i=0;
							for(Element myLink:eLink){
								switch(i){
								case 0:
									//运费信息
									ItemInfoDelivery=myLink.select("span").text();
									break;
								case 1:
									//发货通知
									ItemDeliveryInfo=myLink.select("span").text();
									break;
								} 
							}  
							break; 
						case 5://配送信息newDeliveryInfo
							break;
					}   
					count++;
				} 
			} 
		}	
	}
	 
	/**
	 * 抢购价
	 * @return
	 */
	public String getItemQiangPrice(){
		return this.ItemQiangPrice;
	}
	
	/**
	 * 市场价
	 * @return
	 */
	public String getItemMarketPrice(){
		return this.ItemMarketPrice;
	}
	/**
	 * 赠送积分
	 * @return
	 */
	public String getItemGiftPoints(){
		return this.ItemGiftPoints;
	}
	/**
	 * 一号店价
	 * @return
	 */
	public String getItemShopPrice(){
		return this.ItemShopPrice;
	}
	/**
	 * 库存状况
	 * @return
	 */ 
	public String getItemStoreStatus(){
		return this.ItemStoreStatus;
	}
	
	//运费信息
	public String getItemInfoDelivery(){
		return this.ItemInfoDelivery;
	}
	//发货通知
	public String ItemDeliveryInfo(){
		return this.ItemDeliveryInfo;
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
			for(Element link:links){
				switch(count){
					case 0://折扣 
						ItemDiscount=link.select("div#promotion_zk>.promo_cen").text(); 
						break;
						
					case 1://立减
						ItemSubstrite=link.select("div#promotion_lj>.promo_cen").text();
						break;
						
					case 2://赠品
						listItemGiftInfo=new ArrayList<ItemGiftInfo>();//赠品主题信息列表
						ArrayList<ItemGift> listItemGift=new ArrayList<ItemGift>();//赠品列表
						
						ItemGiftInfo itemGiftInfo=new  ItemGiftInfo();//赠品主题信息
						ItemGift itemGift=new ItemGift(); //赠品
						
						Elements links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							/*******************单个赠品主题Begin------------------*/
							//赠品主题  
							itemGiftInfo.setItemGiftTitle(link_gift_box.select("h3>a").text());
							//赠品主题页
							itemGiftInfo.setItemGiftUrl(link_gift_box.select("h3>a").attr("href"));
							//赠品列表
							Elements linkItemGift=link_gift_box.select("ul>li");  
							//遍历<li>单件赠品信息</li> 
							for(Element linkSub:linkItemGift){ 
								/*******************单个赠品信息Begin------------------*/
								//赠品图片信息 
//									//图片上的超链商品-所在页面/product/3780742_1
									itemGift.setPageUrl(linkSub.select("a").attr("href")); 
									//图片路径
									itemGift.setItemImageURL(linkSub.select("a>img").attr("original"));
									//图片标题(赠品图片中包含的赠品名)
									itemGift.setItemName(linkSub.select("a>img").attr("title"));   
										
//									赠品所在网址 /product/3780742_1
									itemGift.setPageUrl(linkSub.select("h4>a").attr("href"));//与上面的重复了 
//									
									//赠品名(与图片中取出的赠品名长度比较，取较长的一个)  
									itemGift.setItemName(itemGift.getItemName().length()>linkSub.select("h4>a").attr("title").length()?itemGift.getItemName():linkSub.select("h4>a").attr("title") );
									
									//赠品原价
									itemGift.setItemPriceOld(linkSub.select(".info>.price>del").text()); 
//								
									//赠品现价 
									itemGift.setItemPriceNow(linkSub.select(".info>.price>strong").text());

									//赠品限量
									itemGift.setItemLimit(linkSub.select(".info>.limit>strong").text());
									
									//赠品状态（已售完/领取）
									itemGift.setItemStatu(linkSub.select(".info>a").text()=="已售完"?false:true); 
									
									/*******************单个赠品信息End------------------*/
									
									/*******************加入赠品列表（记录单个赠品信息）*************/
									listItemGift.add(itemGift);
									
							} 
							/*++++++++设置赠品主题中 的赠品（记录一个）++++++++*/
							itemGiftInfo.setItemGift(listItemGift);// 
					 
							//增加一条商品赠品主题(系列)
							/*******************单个赠品主题Begin------------------*/
							listItemGiftInfo.add(itemGiftInfo);//添加到主题列表	
						}
						
						break;
						
					case 3://优惠
						ItemYouHui=link.select("div#promotion_yh>.promo_cen").text();
						break;
					case 4://换购
						listItemRedemptionInfo=new ArrayList<ItemRedemptionInfo>();//换购主题信息列表
						ArrayList<ItemRedemption> listItemRedemption=new ArrayList<ItemRedemption>();//换购品列表
						
						ItemRedemptionInfo itemRedemptionInfo=new  ItemRedemptionInfo();//赠品主题信息
						ItemRedemption itemRedemption=new ItemRedemption(); //赠品
						
						links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							/*******************单个赠品主题Begin------------------*/
							//赠品主题  
							itemRedemptionInfo.setItemGiftTitle(link_gift_box.select("h3>a").text());
							//赠品主题页
							itemRedemptionInfo.setItemGiftUrl(link_gift_box.select("h3>a").attr("href"));
							//赠品列表
							Elements linkItemGift=link_gift_box.select("ul>li");  
							//遍历<li>单件赠品信息</li> 
							for(Element linkSub:linkItemGift){ 
								/*******************单个赠品信息Begin------------------*/
								//赠品图片信息 
//									//图片上的超链商品-所在页面/product/3780742_1
								itemRedemption.setPageUrl(linkSub.select("a").attr("href")); 
									//图片路径
								itemRedemption.setItemImageURL(linkSub.select("a>img").attr("original"));
									//图片标题(赠品图片中包含的赠品名)
								itemRedemption.setItemName(linkSub.select("a>img").attr("title"));   
										
//									赠品所在网址 /product/3780742_1
									itemRedemption.setPageUrl(linkSub.select("h4>a").attr("href"));//与上面的重复了 
//									
									//赠品名(与图片中取出的赠品名长度比较，取较长的一个)  
									itemRedemption.setItemName(
											itemRedemption.getItemName().length()>linkSub.select("h4>a").attr("title").length() ? itemRedemption.getItemName() : linkSub.select("h4>a").attr("title") );
									
									//赠品原价
									itemRedemption.setItemPriceOld(linkSub.select(".info>.price>del").text()); 
//								
									//赠品现价 
									itemRedemption.setItemPriceNow(linkSub.select(".info>.price>strong").text());

									//赠品限量
									itemRedemption.setItemLimit(linkSub.select(".info>.limit>strong").text());
									
									//赠品状态（已售完/领取）
									itemRedemption.setItemStatu(linkSub.select(".info>a").text()=="已售完" ? false : true); 
									
									/*******************单个赠品信息End------------------*/
									
									
									/*******************加入赠品列表（记录单个赠品信息）*************/
									listItemRedemption.add(itemRedemption);
									
							} 
							/*++++++++设置赠品主题中 的赠品（记录一个）++++++++*/ 
							itemRedemptionInfo.setItemRedemption(listItemRedemption);
							//增加一条商品赠品主题(系列)
							/*******************单个赠品主题Begin------------------*/
							listItemRedemptionInfo.add(itemRedemptionInfo);//添加到主题列表	
						}  
						break;
					}  
					count++;
				}  
		}
	} 
	//促销-折扣
	public String getItemDiscount(){
		return this.ItemDiscount;
	}
	//促销-立减
	public String getItemSubstrite(){
		return this.ItemSubstrite;
	} 
	//促销-赠品
	public ArrayList<ItemGiftInfo> getListItemGiftInfo(){
		return this.listItemGiftInfo;
	} 
	//促销-优惠
	public String getItemYouHui(){
		return this.ItemYouHui;
	}  
	//促销—换购  
	public ArrayList<ItemRedemptionInfo> getListItemRedemptionInfo(){
		return this.listItemRedemptionInfo;
	};

	
	
	
	
//品牌 
//	//公司名
//	private String ItemCompanyName="";
//	//公司所在地
//	private String ItemCompanyArea="";
//	
//	
//	private String ItemBrand="";
//	//上架时间
//	private String ItemOnShelfDate="";
//	//补充信息
//	private String ItemDetails="";
//	//包装信息
//	private String ItemWrapper="";
 
	
	

	
	/**
	 * 封装商品类型
	 * */
	public class ItemType{
		private  String strItemTypeName="";
		private  String strItemTypeURL=""; 
		
		public ItemType(String ItemTypeName,String ItemTypeURL){
			this.strItemTypeName=ItemTypeName;
			this.strItemTypeURL=ItemTypeURL;
		} 
		
		public String getItemTypeName(){
			return this.strItemTypeName;
		} 
		public String getItemTypeURL(){
			return this.strItemTypeURL;
		} 
	}
	
	
	
	/**
	 * 二次封装赠品信息
	 * @author Administrator
	 * @param <ItemGift>
	 *
	 */
	public class ItemGiftInfo{ 
		private String ItemGiftTitle;//赠品标题
		private String ItemGiftUrl;//享有相同赠品的商品
		private ArrayList<ItemGift> itemGift=null;//赠品列表

		public ItemGiftInfo(){}
		public ItemGiftInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemGift> itemGift){
			this.ItemGiftTitle=ItemGiftTitle;//赠品主题
			this.ItemGiftUrl=ItemGiftUrl;//享有相同赠品的商品
			this.itemGift=itemGift;//赠品列表
		} 
		//赠品标题
		public  String getItemGiftTitle(){
			return this.ItemGiftTitle;
		}
		//享有相同赠品的商品
		public String getItemGiftUrl(){
			return this.ItemGiftUrl;
		}
		//赠品列表
		public ArrayList<ItemGift> getItemGift(){
			return this.itemGift;
		}
		
		
		
		public void setItemGiftTitle(String ItemGiftTitle){
			this.ItemGiftTitle=ItemGiftTitle;
		} 
		public void setItemGiftUrl(String ItemGiftUrl){
			this.ItemGiftUrl=ItemGiftUrl;
		} 
		public void setItemGift(ArrayList<ItemGift> itemGift){
			this.itemGift=itemGift;
		}
	}
	/**
	 * 封装赠品信息
	 */
	public class ItemGift{
		private String PageUrl="";//商品页面地址：/product/3780742_1
		private String ItemName="";//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		private String ItemImageURL="";//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//现价：免费 
		private String ItemPriceOld="";//原价：￥4
		private String ItemLimit="";//每日限量：每日限量10000个
		private boolean ItemStatu=true;//已售完/领取
		
		public ItemGift(){}
		public ItemGift(String PageUrl,String ItemName,String ItemImageURL,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
			this.PageUrl=PageUrl;//商品页面地址
			this.ItemName=ItemName;//商品名
			this.ItemImageURL=ItemImageURL;//商品图片
			this.ItemPriceNow=ItemPriceNow;//现价
			this.ItemPriceOld=ItemPriceOld;//现价
			this.ItemLimit=ItemLimit;//每日限量
			this.ItemStatu= boolItemStatu;/// 
		}
		//商品页面地址：/product/3780742_1
		public  String getPageUrl(){
			return this.PageUrl;
		}//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		public  String getItemName(){
			return this.ItemName;
		}
		//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		public  String getItemImage(){
			return this.ItemImageURL;
		}
		//现价：免费
		public  String getItemPriceNow(){
			return this.ItemPriceNow;
		} 
		//原价：￥4
		public  String getItemPriceOld(){
			return this.ItemPriceOld;
		}
		//每日限量：每日限量10000个
		public  String getItemLimit(){
			return this.ItemLimit;
		}
		//已售完/领取 
		public  boolean getItemStatu(){
			return this.ItemStatu;
		}
		
		
		public void setPageUrl(String PageUrl){
			this.PageUrl=PageUrl;
		} 
		public  void setItemName(String ItemName){
			this.ItemName=ItemName;
		}
		public  void setItemImageURL(String ItemImageURL){
			this.ItemImageURL=ItemImageURL;
		} 
		public void setItemPriceNow(String ItemPriceNow){
			this.ItemPriceNow=ItemPriceNow;
		}  
		public  void setItemPriceOld(String ItemPriceOld){
			this.ItemPriceOld=ItemPriceOld;
		} 
		public  void setItemLimit(String ItemLimit){
			this.ItemLimit=ItemLimit;
		} 
		public void setItemStatu(boolean ItemStatu){
			this.ItemStatu=ItemStatu;
		} 
	} 
	
	 
	/**
	 * 二次封装换购信息
	 * @author Administrator
	 * @param ItemRedemption  
	 *
	 */
	public class ItemRedemptionInfo{ 
		private String ItemGiftTitle;//换购标题
		private String ItemGiftUrl;//享有相同换购品的商品（换购主题页）
		private ArrayList<ItemRedemption> itemRedemption=null;//换购品列表
		
		public ItemRedemptionInfo(){}
		public ItemRedemptionInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemRedemption> itemRedemption){
			this.ItemGiftTitle=ItemGiftTitle;//赠品主题
			this.ItemGiftUrl=ItemGiftUrl;//享有相同赠品的商品
			this.itemRedemption=itemRedemption;//赠品列表
		} 
	
		
		
		
		//换购标题
		public String getItemGiftTitle(){
			return this.ItemGiftTitle;
		}
		//享有相同换购品的商品（换购主题页）
		public String getItemGiftUrl(){
			return this.ItemGiftUrl;
		}
		//换购品列表
		public ArrayList<ItemRedemption> getItemRedemption(){
			return this.itemRedemption;
		}
		
		
		 
		public void setItemGiftTitle(String ItemGiftTitle){
			this.ItemGiftTitle=ItemGiftTitle;
		} 
		public void setItemGiftUrl(String ItemGiftUrl){
			this.ItemGiftUrl=ItemGiftUrl;
		} 
		public void setItemRedemption(ArrayList<ItemRedemption> itemRedemption){
			this.itemRedemption=itemRedemption;
		}
	}
	/**
	 * 封装换购信息
	 */
	public class ItemRedemption{
		private String PageUrl="";//商品页面地址：/product/3780742_1
		private String ItemName="";//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		private String ItemImageURL="";//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//现价： ￥2 
		private String ItemPriceOld="";//原价：￥4 
		private String ItemLimit="";//每日限量：每日限量10000个
		private boolean ItemStatu=true;//已售完/领取 
		public ItemRedemption(){}
		public ItemRedemption(String PageUrl,String ItemName,String ItemImageURL,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
				this.PageUrl=PageUrl;//商品页面地址
				this.ItemName=ItemName;//商品名
				this.ItemImageURL=ItemImageURL;//商品图片
				this.ItemPriceNow=ItemPriceNow;//现价
				this.ItemPriceOld=ItemPriceOld;//现价
				this.ItemLimit=ItemLimit;//每日限量
				this.ItemStatu= boolItemStatu;/// 
		}
		//商品页面地址：/product/3780742_1
		public  String getPageUrl(){
			return this.PageUrl;
		}//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		public  String getItemName(){
			return this.ItemName;
		}
		//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		public  String getItemImage(){
			return this.ItemImageURL;
		}
		//现价：免费
		public  String getItemPriceNow(){
			return this.ItemPriceNow;
		} 
		//原价：￥4
		public  String getItemPriceOld(){
			return this.ItemPriceOld;
		}
		//每日限量：每日限量10000个
		public  String getItemLimit(){
			return this.ItemLimit;
		}
		//已售完/领取 
		public  boolean getItemStatu(){
			return this.ItemStatu;
		} 
		


		
		public void setPageUrl(String PageUrl){
			this.PageUrl=PageUrl;
		} 
		public  void setItemName(String ItemName){
			this.ItemName=ItemName;
		}
		public  void setItemImageURL(String ItemImageURL){
			this.ItemImageURL=ItemImageURL;
		} 
		public void setItemPriceNow(String ItemPriceNow){
			this.ItemPriceNow=ItemPriceNow;
		}  
		public  void setItemPriceOld(String ItemPriceOld){
			this.ItemPriceOld=ItemPriceOld;
		} 
		public  void setItemLimit(String ItemLimit){
			this.ItemLimit=ItemLimit;
		} 
		public void setItemStatu(boolean ItemStatu){
			this.ItemStatu=ItemStatu;
		} 
	} 
	
}
