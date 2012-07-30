package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 

public class YiHaoDianItem extends YiHaoDianItemPackage {
//	//��Ʒ���
//	private String ItemID="";
	//�̼�
//	private String ItemShopName=""; 
	
	//��Ʒ����
	private ArrayList<ItemType>  listItemType=null; 
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
	//�˷���Ϣ
	private String ItemInfoDelivery="";
	//����֪ͨ
	private String ItemDeliveryInfo="";
	
	//����-�ۿ�
	private String ItemDiscount="";
	//����-����
	private String ItemSubstrite="";
	//����-��Ʒ
	private ArrayList<ItemGiftInfo> listItemGiftInfo=null; 
	//����-�Ż�
	private String ItemYouHui="";  
	//����������  
	private ArrayList<ItemRedemptionInfo> listItemRedemptionInfo=null;
	
	
	
//	//��Ʒ����
//	private String ItemName="";//�ݲ���
//	//������
//	private String ItemMainName="";
//	//�ӱ���
//	private String ItemSubtitles="";

 
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
	 * ��ȡ��Ʒ����
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
	private String getItemShopName(Document doc){ 
		if(doc!=null){ 
			Element link=null;
			//�콢�꣺http://www.yihaodian.com/product/3696739_5201
			if((link=doc.select(".wrap>.produce.clearfix>.property.property_box>#merchantCompanyName>dl>dd>a").first())!=null){
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
	 * �����ۡ�һ�ŵ�ۡ��г��ۡ���桢�˷���Ϣ������֪ͨ
	 * @param doc
	 */
	public void initPriceAndStore(Document doc){
		if(doc!=null){    
			if(doc!=null){ 
				int count=0; 
				Elements links=doc.select(".wrap>.produce.clearfix>.property.property_box>.specific_info1>dl");
				for(Element link:links){
					switch(count){  
						case 0://��?�� 418Ԫ ����Ϊֹ 
							ItemQiangPrice=link.select("dl>dd>.price>strong>span#productFacadePrice").first().text();
							break;
						case 1://1�ŵ�?�ۣ� 488Ԫ ����֪ͨ ���ͻ��֣�130 
							ItemShopPrice=link.select("dl>dd>span#nonMemberPrice").first().text(); 
							ItemGiftPoints=link.select("dl>dd>span.gift_points").first().text();
							break; 
						case 2://��??��?�ۣ� 568Ԫ 
							ItemMarketPrice=link.select("dl>dd>del.old_price").first().text();
							break;
						case 3://���״���� 
							ItemStoreStatus=link.select("dl>dd#stockDesc").first().text();
							break;
						case 4://������ϢproductInfoDelivery
							Elements eLink=link.select("dl#productInfoDelivery>dd>ul#sfi>li");
							int i=0;
							for(Element myLink:eLink){
								switch(i){
								case 0:
									//�˷���Ϣ
									ItemInfoDelivery=myLink.select("span").text();
									break;
								case 1:
									//����֪ͨ
									ItemDeliveryInfo=myLink.select("span").text();
									break;
								} 
							}  
							break; 
						case 5://������ϢnewDeliveryInfo
							break;
					}   
					count++;
				} 
			} 
		}	
	}
	 
	/**
	 * ������
	 * @return
	 */
	public String getItemQiangPrice(){
		return this.ItemQiangPrice;
	}
	
	/**
	 * �г���
	 * @return
	 */
	public String getItemMarketPrice(){
		return this.ItemMarketPrice;
	}
	/**
	 * ���ͻ���
	 * @return
	 */
	public String getItemGiftPoints(){
		return this.ItemGiftPoints;
	}
	/**
	 * һ�ŵ��
	 * @return
	 */
	public String getItemShopPrice(){
		return this.ItemShopPrice;
	}
	/**
	 * ���״��
	 * @return
	 */ 
	public String getItemStoreStatus(){
		return this.ItemStoreStatus;
	}
	
	//�˷���Ϣ
	public String getItemInfoDelivery(){
		return this.ItemInfoDelivery;
	}
	//����֪ͨ
	public String ItemDeliveryInfo(){
		return this.ItemDeliveryInfo;
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
			for(Element link:links){
				switch(count){
					case 0://�ۿ� 
						ItemDiscount=link.select("div#promotion_zk>.promo_cen").text(); 
						break;
						
					case 1://����
						ItemSubstrite=link.select("div#promotion_lj>.promo_cen").text();
						break;
						
					case 2://��Ʒ
						listItemGiftInfo=new ArrayList<ItemGiftInfo>();//��Ʒ������Ϣ�б�
						ArrayList<ItemGift> listItemGift=new ArrayList<ItemGift>();//��Ʒ�б�
						
						ItemGiftInfo itemGiftInfo=new  ItemGiftInfo();//��Ʒ������Ϣ
						ItemGift itemGift=new ItemGift(); //��Ʒ
						
						Elements links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							/*******************������Ʒ����Begin------------------*/
							//��Ʒ����  
							itemGiftInfo.setItemGiftTitle(link_gift_box.select("h3>a").text());
							//��Ʒ����ҳ
							itemGiftInfo.setItemGiftUrl(link_gift_box.select("h3>a").attr("href"));
							//��Ʒ�б�
							Elements linkItemGift=link_gift_box.select("ul>li");  
							//����<li>������Ʒ��Ϣ</li> 
							for(Element linkSub:linkItemGift){ 
								/*******************������Ʒ��ϢBegin------------------*/
								//��ƷͼƬ��Ϣ 
//									//ͼƬ�ϵĳ�����Ʒ-����ҳ��/product/3780742_1
									itemGift.setPageUrl(linkSub.select("a").attr("href")); 
									//ͼƬ·��
									itemGift.setItemImageURL(linkSub.select("a>img").attr("original"));
									//ͼƬ����(��ƷͼƬ�а�������Ʒ��)
									itemGift.setItemName(linkSub.select("a>img").attr("title"));   
										
//									��Ʒ������ַ /product/3780742_1
									itemGift.setPageUrl(linkSub.select("h4>a").attr("href"));//��������ظ��� 
//									
									//��Ʒ��(��ͼƬ��ȡ������Ʒ�����ȱȽϣ�ȡ�ϳ���һ��)  
									itemGift.setItemName(itemGift.getItemName().length()>linkSub.select("h4>a").attr("title").length()?itemGift.getItemName():linkSub.select("h4>a").attr("title") );
									
									//��Ʒԭ��
									itemGift.setItemPriceOld(linkSub.select(".info>.price>del").text()); 
//								
									//��Ʒ�ּ� 
									itemGift.setItemPriceNow(linkSub.select(".info>.price>strong").text());

									//��Ʒ����
									itemGift.setItemLimit(linkSub.select(".info>.limit>strong").text());
									
									//��Ʒ״̬��������/��ȡ��
									itemGift.setItemStatu(linkSub.select(".info>a").text()=="������"?false:true); 
									
									/*******************������Ʒ��ϢEnd------------------*/
									
									/*******************������Ʒ�б���¼������Ʒ��Ϣ��*************/
									listItemGift.add(itemGift);
									
							} 
							/*++++++++������Ʒ������ ����Ʒ����¼һ����++++++++*/
							itemGiftInfo.setItemGift(listItemGift);// 
					 
							//����һ����Ʒ��Ʒ����(ϵ��)
							/*******************������Ʒ����Begin------------------*/
							listItemGiftInfo.add(itemGiftInfo);//��ӵ������б�	
						}
						
						break;
						
					case 3://�Ż�
						ItemYouHui=link.select("div#promotion_yh>.promo_cen").text();
						break;
					case 4://����
						listItemRedemptionInfo=new ArrayList<ItemRedemptionInfo>();//����������Ϣ�б�
						ArrayList<ItemRedemption> listItemRedemption=new ArrayList<ItemRedemption>();//����Ʒ�б�
						
						ItemRedemptionInfo itemRedemptionInfo=new  ItemRedemptionInfo();//��Ʒ������Ϣ
						ItemRedemption itemRedemption=new ItemRedemption(); //��Ʒ
						
						links_gift_box=link.select("div#promotion_gift>.promo_cen>.gift_list>.gift_box");
						for(Element link_gift_box:links_gift_box){
							/*******************������Ʒ����Begin------------------*/
							//��Ʒ����  
							itemRedemptionInfo.setItemGiftTitle(link_gift_box.select("h3>a").text());
							//��Ʒ����ҳ
							itemRedemptionInfo.setItemGiftUrl(link_gift_box.select("h3>a").attr("href"));
							//��Ʒ�б�
							Elements linkItemGift=link_gift_box.select("ul>li");  
							//����<li>������Ʒ��Ϣ</li> 
							for(Element linkSub:linkItemGift){ 
								/*******************������Ʒ��ϢBegin------------------*/
								//��ƷͼƬ��Ϣ 
//									//ͼƬ�ϵĳ�����Ʒ-����ҳ��/product/3780742_1
								itemRedemption.setPageUrl(linkSub.select("a").attr("href")); 
									//ͼƬ·��
								itemRedemption.setItemImageURL(linkSub.select("a>img").attr("original"));
									//ͼƬ����(��ƷͼƬ�а�������Ʒ��)
								itemRedemption.setItemName(linkSub.select("a>img").attr("title"));   
										
//									��Ʒ������ַ /product/3780742_1
									itemRedemption.setPageUrl(linkSub.select("h4>a").attr("href"));//��������ظ��� 
//									
									//��Ʒ��(��ͼƬ��ȡ������Ʒ�����ȱȽϣ�ȡ�ϳ���һ��)  
									itemRedemption.setItemName(
											itemRedemption.getItemName().length()>linkSub.select("h4>a").attr("title").length() ? itemRedemption.getItemName() : linkSub.select("h4>a").attr("title") );
									
									//��Ʒԭ��
									itemRedemption.setItemPriceOld(linkSub.select(".info>.price>del").text()); 
//								
									//��Ʒ�ּ� 
									itemRedemption.setItemPriceNow(linkSub.select(".info>.price>strong").text());

									//��Ʒ����
									itemRedemption.setItemLimit(linkSub.select(".info>.limit>strong").text());
									
									//��Ʒ״̬��������/��ȡ��
									itemRedemption.setItemStatu(linkSub.select(".info>a").text()=="������" ? false : true); 
									
									/*******************������Ʒ��ϢEnd------------------*/
									
									
									/*******************������Ʒ�б���¼������Ʒ��Ϣ��*************/
									listItemRedemption.add(itemRedemption);
									
							} 
							/*++++++++������Ʒ������ ����Ʒ����¼һ����++++++++*/ 
							itemRedemptionInfo.setItemRedemption(listItemRedemption);
							//����һ����Ʒ��Ʒ����(ϵ��)
							/*******************������Ʒ����Begin------------------*/
							listItemRedemptionInfo.add(itemRedemptionInfo);//��ӵ������б�	
						}  
						break;
					}  
					count++;
				}  
		}
	} 
	//����-�ۿ�
	public String getItemDiscount(){
		return this.ItemDiscount;
	}
	//����-����
	public String getItemSubstrite(){
		return this.ItemSubstrite;
	} 
	//����-��Ʒ
	public ArrayList<ItemGiftInfo> getListItemGiftInfo(){
		return this.listItemGiftInfo;
	} 
	//����-�Ż�
	public String getItemYouHui(){
		return this.ItemYouHui;
	}  
	//����������  
	public ArrayList<ItemRedemptionInfo> getListItemRedemptionInfo(){
		return this.listItemRedemptionInfo;
	};

	
	
	
	
//Ʒ�� 
//	//��˾��
//	private String ItemCompanyName="";
//	//��˾���ڵ�
//	private String ItemCompanyArea="";
//	
//	
//	private String ItemBrand="";
//	//�ϼ�ʱ��
//	private String ItemOnShelfDate="";
//	//������Ϣ
//	private String ItemDetails="";
//	//��װ��Ϣ
//	private String ItemWrapper="";
 
	
	

	
	/**
	 * ��װ��Ʒ����
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
	 * ���η�װ��Ʒ��Ϣ
	 * @author Administrator
	 * @param <ItemGift>
	 *
	 */
	public class ItemGiftInfo{ 
		private String ItemGiftTitle;//��Ʒ����
		private String ItemGiftUrl;//������ͬ��Ʒ����Ʒ
		private ArrayList<ItemGift> itemGift=null;//��Ʒ�б�

		public ItemGiftInfo(){}
		public ItemGiftInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemGift> itemGift){
			this.ItemGiftTitle=ItemGiftTitle;//��Ʒ����
			this.ItemGiftUrl=ItemGiftUrl;//������ͬ��Ʒ����Ʒ
			this.itemGift=itemGift;//��Ʒ�б�
		} 
		//��Ʒ����
		public  String getItemGiftTitle(){
			return this.ItemGiftTitle;
		}
		//������ͬ��Ʒ����Ʒ
		public String getItemGiftUrl(){
			return this.ItemGiftUrl;
		}
		//��Ʒ�б�
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
	 * ��װ��Ʒ��Ϣ
	 */
	public class ItemGift{
		private String PageUrl="";//��Ʒҳ���ַ��/product/3780742_1
		private String ItemName="";//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		private String ItemImageURL="";//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//�ּۣ���� 
		private String ItemPriceOld="";//ԭ�ۣ���4
		private String ItemLimit="";//ÿ��������ÿ������10000��
		private boolean ItemStatu=true;//������/��ȡ
		
		public ItemGift(){}
		public ItemGift(String PageUrl,String ItemName,String ItemImageURL,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
			this.PageUrl=PageUrl;//��Ʒҳ���ַ
			this.ItemName=ItemName;//��Ʒ��
			this.ItemImageURL=ItemImageURL;//��ƷͼƬ
			this.ItemPriceNow=ItemPriceNow;//�ּ�
			this.ItemPriceOld=ItemPriceOld;//�ּ�
			this.ItemLimit=ItemLimit;//ÿ������
			this.ItemStatu= boolItemStatu;/// 
		}
		//��Ʒҳ���ַ��/product/3780742_1
		public  String getPageUrl(){
			return this.PageUrl;
		}//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		public  String getItemName(){
			return this.ItemName;
		}
		//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		public  String getItemImage(){
			return this.ItemImageURL;
		}
		//�ּۣ����
		public  String getItemPriceNow(){
			return this.ItemPriceNow;
		} 
		//ԭ�ۣ���4
		public  String getItemPriceOld(){
			return this.ItemPriceOld;
		}
		//ÿ��������ÿ������10000��
		public  String getItemLimit(){
			return this.ItemLimit;
		}
		//������/��ȡ 
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
	 * ���η�װ������Ϣ
	 * @author Administrator
	 * @param ItemRedemption  
	 *
	 */
	public class ItemRedemptionInfo{ 
		private String ItemGiftTitle;//��������
		private String ItemGiftUrl;//������ͬ����Ʒ����Ʒ����������ҳ��
		private ArrayList<ItemRedemption> itemRedemption=null;//����Ʒ�б�
		
		public ItemRedemptionInfo(){}
		public ItemRedemptionInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemRedemption> itemRedemption){
			this.ItemGiftTitle=ItemGiftTitle;//��Ʒ����
			this.ItemGiftUrl=ItemGiftUrl;//������ͬ��Ʒ����Ʒ
			this.itemRedemption=itemRedemption;//��Ʒ�б�
		} 
	
		
		
		
		//��������
		public String getItemGiftTitle(){
			return this.ItemGiftTitle;
		}
		//������ͬ����Ʒ����Ʒ����������ҳ��
		public String getItemGiftUrl(){
			return this.ItemGiftUrl;
		}
		//����Ʒ�б�
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
	 * ��װ������Ϣ
	 */
	public class ItemRedemption{
		private String PageUrl="";//��Ʒҳ���ַ��/product/3780742_1
		private String ItemName="";//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		private String ItemImageURL="";//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//�ּۣ� ��2 
		private String ItemPriceOld="";//ԭ�ۣ���4 
		private String ItemLimit="";//ÿ��������ÿ������10000��
		private boolean ItemStatu=true;//������/��ȡ 
		public ItemRedemption(){}
		public ItemRedemption(String PageUrl,String ItemName,String ItemImageURL,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
				this.PageUrl=PageUrl;//��Ʒҳ���ַ
				this.ItemName=ItemName;//��Ʒ��
				this.ItemImageURL=ItemImageURL;//��ƷͼƬ
				this.ItemPriceNow=ItemPriceNow;//�ּ�
				this.ItemPriceOld=ItemPriceOld;//�ּ�
				this.ItemLimit=ItemLimit;//ÿ������
				this.ItemStatu= boolItemStatu;/// 
		}
		//��Ʒҳ���ַ��/product/3780742_1
		public  String getPageUrl(){
			return this.PageUrl;
		}//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		public  String getItemName(){
			return this.ItemName;
		}
		//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		public  String getItemImage(){
			return this.ItemImageURL;
		}
		//�ּۣ����
		public  String getItemPriceNow(){
			return this.ItemPriceNow;
		} 
		//ԭ�ۣ���4
		public  String getItemPriceOld(){
			return this.ItemPriceOld;
		}
		//ÿ��������ÿ������10000��
		public  String getItemLimit(){
			return this.ItemLimit;
		}
		//������/��ȡ 
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
