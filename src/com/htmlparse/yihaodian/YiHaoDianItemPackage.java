package com.htmlparse.yihaodian;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlparse.yihaodian.*;

public class YiHaoDianItemPackage {
	//��Ʒ���
	private String ItemID="";
	//�̼�
	private String ItemShopName=""; 
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
	private ArrayList<ItemGiftInfo> temGiftInfo=null;
	//����-�Ż�
	private String ItemYouHui=""; 
	//���������� 
	private String ItemRedemption="";
//	//��Ʒ����
//	private String ItemName="";
	//������
	private String ItemMainName="";
	//�ӱ���
	private String ItemSubtitles="";
	//Ʒ��
	private String ItemBrand="";
	//�ϼ�ʱ��
	private String ItemOnShelfDate="";
	//������Ϣ
	private String ItemDetails="";
	//��װ��Ϣ
	private String ItemWrapper="";
	
	
	
	
	
	

	
	/**
	 * ��ȡ��Ʒ����
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
	 * ��װ��Ʒ����
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
	 * ���η�װ��Ʒ��Ϣ
	 * @author Administrator
	 * @param <ItemGift>
	 *
	 */
	public class ItemGiftInfo{ 
		public String ItemGiftTitle;//��Ʒ����
		public String ItemGiftUrl;//������ͬ��Ʒ����Ʒ
		ArrayList<ItemGift> itemGift=null;//��Ʒ�б�
		
		public ItemGiftInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemGift> itemGift){
			this.ItemGiftTitle=ItemGiftTitle;//��Ʒ����
			this.ItemGiftUrl=ItemGiftUrl;//������ͬ��Ʒ����Ʒ
			this.itemGift=itemGift;//��Ʒ�б�
		} 
	}
	/**
	 * ��װ��Ʒ��Ϣ
	 */
	public class ItemGift{
		private String PageUrl="";//��Ʒҳ���ַ��/product/3780742_1
		private String ItemName="";//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		private String ItemImage="";//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//�ּۣ���� 
		private String ItemPriceOld="";//ԭ�ۣ���4
		private String ItemLimit="";//ÿ��������ÿ������10000��
		private boolean ItemStatu=true;//������/��ȡ 
		public ItemGift(String PageUrl,String ItemName,String ItemImage,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
			this.PageUrl=PageUrl;//��Ʒҳ���ַ
			this.ItemName=ItemName;//��Ʒ��
			this.ItemImage=ItemImage;//��ƷͼƬ
			this.ItemPriceNow=ItemPriceNow;//�ּ�
			this.ItemPriceOld=ItemPriceOld;//�ּ�
			this.ItemLimit=ItemLimit;//ÿ������
			this.ItemStatu= boolItemStatu;/// 
		}
	} 

	
	
	
	/**
	 * ���η�װ������Ϣ
	 * @author Administrator
	 * @param ItemRedemption  
	 *
	 */
	public class ItemRedemptionInfo{ 
		public String ItemGiftTitle;//��������
		public String ItemGiftUrl;//������ͬ����Ʒ����Ʒ����������ҳ��
		ArrayList<ItemRedemption> itemRedemption=null;//����Ʒ�б�
		
		public ItemRedemptionInfo(String ItemGiftTitle,String ItemGiftUrl,ArrayList<ItemRedemption> itemRedemption){
			this.ItemGiftTitle=ItemGiftTitle;//��Ʒ����
			this.ItemGiftUrl=ItemGiftUrl;//������ͬ��Ʒ����Ʒ
			this.itemRedemption=itemRedemption;//��Ʒ�б�
		} 
	}
	/**
	 * ��װ������Ϣ
	 */
	public class ItemRedemption{
		private String PageUrl="";//��Ʒҳ���ַ��/product/3780742_1
		private String ItemName="";//��Ʒ��(��Ʒ����)������ ���ȴཷ�μ��ζ 40g
		private String ItemImage="";//��ƷͼƬ��http://d12.yihaodianimg.com/t1/2012/0703/136/403/8aee43be80678016YY_115x115.jpg
		private String ItemPriceNow="";//�ּۣ� ��2 
		private String ItemPriceOld="";//ԭ�ۣ���4
		
		private String ItemLimit="";//ÿ��������ÿ������10000��
		private boolean ItemStatu=true;//������/��ȡ 
		public ItemRedemption(String PageUrl,String ItemName,String ItemImage,String ItemPriceNow,String ItemPriceOld,String ItemLimit,boolean boolItemStatu){
				this.PageUrl=PageUrl;//��Ʒҳ���ַ
				this.ItemName=ItemName;//��Ʒ��
				this.ItemImage=ItemImage;//��ƷͼƬ
				this.ItemPriceNow=ItemPriceNow;//�ּ�
				this.ItemPriceOld=ItemPriceOld;//�ּ�
				this.ItemLimit=ItemLimit;//ÿ������
				this.ItemStatu= boolItemStatu;/// 
		}
	}
	
}
