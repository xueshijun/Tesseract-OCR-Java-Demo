package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlparse.yihaodian.*;

public class YiHaoDianItemPackage {
	//商品编号
	private String ItemID="";
	//商家
	private String ItemShopName=""; 
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
	private ArrayList<ItemGiftInfo> temGiftInfo=null;
	//促销-优惠
	private String ItemYouHui=""; 
	//促销—换购 
	private String ItemRedemption="";
//	//商品名称
//	private String ItemName="";
	//主标题
	private String ItemMainName="";
	//子标题
	private String ItemSubtitles="";
	//品牌
	private String ItemBrand="";
	//上架时间
	private String ItemOnShelfDate="";
	//补充信息
	private String ItemDetails="";
	//包装信息
	private String ItemWrapper="";
	
	
	
	
	
	

	
	/**
	 * 获取商品分类
	 * @param doc
	 * @return
	 */
	public ArrayList getItemType(Document doc){
		ArrayList listItemType=new ArrayList();
		if(doc!=null){    
			Elements links=doc.select(".wrap>.detailnav>");
			int count=0;
			for(Element link:links){ 
				listItemType.add(new ItemType(link.getElementsByTag("a").text(),link.getElementsByTag("a").attr("href")));
			}
		}  
		return listItemType;
	} 
	
	/**
	 * 封装商品类型
	 * */
	public class ItemType{
		private String ItemTypeName="";
		private String ItemTypeURL="";
		ItemType(String ItemTypeName,String ItemTypeURL){
			this.ItemTypeName=ItemTypeName;
			this.ItemTypeURL=ItemTypeURL;
		} 
	}
	
	
	
	/**
	 * 二次封装赠品信息
	 * @author Administrator
	 * @param <ItemGift>
	 *
	 */
	public class ItemGiftInfo{ 
		public String ItemGiftTitle;//赠品标题
		public String ItemGiftUrl;//享有相同赠品的商品
		ArrayList<ItemGift> itemGift=null;//赠品列表
		
		public ItemGiftInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemGift> itemGift){
			this.ItemGiftTitle=ItemGiftTitle;//赠品主题
			this.ItemGiftUrl=ItemGiftUrl;//享有相同赠品的商品
			this.itemGift=itemGift;//赠品列表
		} 
	}
	/**
	 * 封装赠品信息
	 */
	public class ItemGift{
		private String PageUrl="";//商品页面地址：/product/3780742_1
		private String ItemName="";//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		private String ItemImage="";//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//现价：免费 
		private String ItemPriceOld="";//原价：￥4
		private String ItemLimit="";//每日限量：每日限量10000个
		private boolean ItemStatu=true;//已售完/领取 
		public ItemGift(String PageUrl,String ItemName,String ItemImage,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
			this.PageUrl=PageUrl;//商品页面地址
			this.ItemName=ItemName;//商品名
			this.ItemImage=ItemImage;//商品图片
			this.ItemPriceNow=ItemPriceNow;//现价
			this.ItemPriceOld=ItemPriceOld;//现价
			this.ItemLimit=ItemLimit;//每日限量
			this.ItemStatu= boolItemStatu;/// 
		}
	} 

	
	
	
	/**
	 * 二次封装换购信息
	 * @author Administrator
	 * @param ItemRedemption  
	 *
	 */
	public class ItemRedemptionInfo{ 
		public String ItemGiftTitle;//换购标题
		public String ItemGiftUrl;//享有相同换购品的商品（换购主题页）
		ArrayList<ItemRedemption> itemRedemption=null;//换购品列表
		
		public ItemRedemptionInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemRedemption> itemRedemption){
			this.ItemGiftTitle=ItemGiftTitle;//赠品主题
			this.ItemGiftUrl=ItemGiftUrl;//享有相同赠品的商品
			this.itemRedemption=itemRedemption;//赠品列表
		} 
	}
	/**
	 * 封装换购信息
	 */
	public class ItemRedemption{
		private String PageUrl="";//商品页面地址：/product/3780742_1
		private String ItemName="";//商品名(赠品标题)：乐事 波谷脆椒盐坚果味 40g
		private String ItemImage="";//商品图片：http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//现价： ￥2 
		private String ItemPriceOld="";//原价：￥4
		
		private String ItemLimit="";//每日限量：每日限量10000个
		private boolean ItemStatu=true;//已售完/领取 
		public ItemRedemption(String PageUrl,String ItemName,String ItemImage,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
				this.PageUrl=PageUrl;//商品页面地址
				this.ItemName=ItemName;//商品名
				this.ItemImage=ItemImage;//商品图片
				this.ItemPriceNow=ItemPriceNow;//现价
				this.ItemPriceOld=ItemPriceOld;//现价
				this.ItemLimit=ItemLimit;//每日限量
				this.ItemStatu= boolItemStatu;/// 
		}
	}
	
}
