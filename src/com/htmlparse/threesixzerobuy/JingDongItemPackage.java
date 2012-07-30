package com.htmlparse.threesixzerobuy;

import java.util.HashMap;

public class JingDongItemPackage {
	public String PageUrl;
	public String PageTitle;
	public String PageKeyWords;
	public String PageDescription;
	
	public String ItemNumber;
	public String ItemTitle;
	public String ItemName;
	public String MarketPrice;
	public String JingDongPrice ;
	public String JingDongPriceUrl ;

	public String ItemMadeArea ;
	public String ItemOnShelfDate;
	public String ItemCompany;
	public String ItemWeight;
	public String ItemTitleAdvertiseWord ;//标题中的广告语
	public String ItemSalesPromotionInfo;//获取促销信息
	public String ItemLargessInfo;
	public String ItemType ;
	/**
	 * @param String PageUrl;
	 * @param String PageTitle;	
	 * @param String PageKeyWords;		
	 * @param String PageDescription;	
	 * @param String ItemNumber;	
	 * @param String ItemTitle;	
	 * @param String ItemName;	
	 * @param String MarketPrice;	
	 * @param String JingDongPrice;	
	 * @param String JingDongPriceUrl ;	
	 * @param String ItemMadeArea ;	
	 * @param String ItemOnShelfDate;	
	 * @param String ItemCompany;	
	 * @param String ItemWeight;	
	 * @param String ItemTitleAdvertiseWord ;//标题中的广告语	
	 * @param String ItemSalesPromotionInfo;//获取促销信息	
	 * @param String ItemLargessInfo;	
	 * @param String ItemType ;
	 */
	public JingDongItemPackage(
			String PageUrl,String PageTitle,String PageKeyWords,String PageDescription,
			String ItemNumber,String ItemTitle,String ItemName,String MarketPrice,
			String JingDongPrice,String JingDongPriceUrl,String ItemMadeArea,
			String ItemOnShelfDate,String ItemCompany,String ItemWeight,
			String ItemTitleAdvertiseWord,String ItemSalesPromotionInfo,
			String ItemLargessInfo,String ItemType){
		
		this.PageUrl= replaceString(PageUrl); 
		this.PageTitle=replaceString(PageTitle);
		this.PageKeyWords=replaceString(PageKeyWords);
		this.PageDescription=replaceString(PageDescription);
		this.ItemNumber=replaceString(ItemNumber);
		this.ItemTitle=replaceString(ItemTitle);
		this.ItemName=replaceString(ItemName);
		this.MarketPrice=replaceString(MarketPrice);
		this.JingDongPrice=replaceString(JingDongPrice);
		this.JingDongPriceUrl=replaceString(JingDongPriceUrl);
		this.ItemMadeArea=replaceString(ItemMadeArea);
		this.ItemOnShelfDate=replaceString(ItemOnShelfDate);
		this.ItemCompany=replaceString(ItemCompany);
		this.ItemWeight=replaceString(ItemWeight);
		this.ItemTitleAdvertiseWord=replaceString(ItemTitleAdvertiseWord);
		this.ItemSalesPromotionInfo=replaceString(ItemSalesPromotionInfo);
		this.ItemLargessInfo=replaceString(ItemLargessInfo);
		this.ItemType=replaceString(ItemType);
	}
	
	public String replaceString(String str){
		HashMap<String, String> map=new HashMap<String, String>();
		if(str==""||str==null){
			return "";
		}
		else{ 
//			map.put("'","\'");
			map.put("'","‘");
			for(int i=0;i<str.length();i++){
				String charat=str.substring(i, i+1);
				if(map.get(charat)!=null){
					str=str.replace(charat, (String)map.get(charat)); 
				}
			}
			return str; 
			}
	} 
}
