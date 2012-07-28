package com.htmlparse.threesixzerobuy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.baseUrl.TSZPage;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemImg;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemType;


public class Test{
 

	/**
	 * @param 
	 * @param
	 * http://www.360buy.com/product/521421.html
	 * 
	 * http://www.360buy.com/product/280126.html
	 * 
	 * http://www.360buy.com/product/280127.html
	 */
	public static void main(String[] args) { 

//		long [] count=new long[50];
		String url=null;
		url="http://www.360buy.com/product/280126.html";
//		for(int j=0;j<10;j++){
//			switch(j%4){
//			case 0:
//				url="http://www.360buy.com/product/280127.html";
//				break;
//			case 1:url="http://www.360buy.com/product/280126.html";
//				break;
//			case 2:url="http://www.360buy.com/product/521421.html";
//				break;
//			case 3:url="http://www.360buy.com/product/521422.html";
//				break;
//			}
		Date beginTime=new Date();
		JingDong.CONNECT_Time_OUT=500;

		JingDong jingDong=new JingDong (url);

		
		System.out.println("页面超时限制:\t "+JingDong.CONNECT_Time_OUT); 
		System.out.println("页面URL:\t "+jingDong.getPageUrl());
		System.out.println("移动页面URL:\t"+jingDong.getMobilePageUrl());
		System.out.println("页面标题:\t"+jingDong.getPageTitle());
		System.out.println("页面关键词:\t"+jingDong.getPageKeyWords());
		System.out.println("页面描述:\t"+jingDong.getPageDescription());
//		TSZPage.CONNECT_Time_OUT=5000;
//		System.out.println("页面超时限制:\t "+TSZPage.CONNECT_Time_OUT); 
//		System.out.println(jingDong.getPageHead());
//		System.out.println(jingDong.getPageBody());
	 
		System.out.println("------------------"); 
		System.out.println("商品顶部标题：\t"+jingDong.getItemTitle());

		System.out.println("商品编号："+jingDong.getItemId( ));
		System.out.println("市场价："+jingDong.getMarketPrice());
		System.out.println("京东价："+jingDong.getJingDongPrice()); 
//		System.out.println("商品评分:"+jingDong.getItemCommentScore());
//		
//	 
		System.out.println("商品顶级分类:"+jingDong.getTopItemType().TypeName);
		System.out.print("商品详细分类");
		ArrayList<ItemType> itemType=jingDong.getItemType();
		System.out.print("["+itemType.size()+"]:");
		for(int i=0;i<itemType.size();i++)
			System.out.print(itemType.get(i).TypeName+" | ");
//		 
		System.out.println();
		System.out.println("商品名称："+jingDong.getItemName());
		System.out.println("生产厂家:"+jingDong.getItemCompany());
		System.out.println("商品产地："+jingDong.getItemMadeArea());
		System.out.println("上架时间："+jingDong.getItemOnShelfDate());
		System.out.println("商品毛重："+jingDong.getItemWeight()); 
		System.out.println("商品配图:");
		ArrayList<ItemImg> strItemImg=jingDong.getItemImg();
		System.out.println(strItemImg.size());
		for(int i=0;i <strItemImg.size();i++) { 
				System.out.println("\t小图:"+strItemImg.get(i).getSmallImgSrc());
				System.out.println("\t大图:"+strItemImg.get(i).getBigImgSrc()); 
		} 
		
		System.out.println("------------------");
		System.out.println("获取商品促销信息：\t"+jingDong.getItemSalesPromotionInfo());
		System.out.println("获取商品标题中的促销信息(广告词)：\t"+jingDong.getItemTitleAdvertiseWord());
		System.out.println("获取赠品信息：\t"+jingDong.getItemLargessInfo());
        Date endTime=new Date();
        long timeOfSearch=endTime.getTime()-beginTime.getTime();
        
    	System.out.println(timeOfSearch+"ms\n=============END================");
//    	count[j]=timeOfSearch;
//		}
//		
//		long sum=0;
//		System.out.println("同一个页面测试50次");
//		for(int j=0;j<50;j++){
//			System.out.print(count[j]+" ");
//			sum+=count[j];
//		}
//		System.out.println("总共耗时:"+sum);
//		System.out.println("平均耗时:"+sum/50);
	}

}
