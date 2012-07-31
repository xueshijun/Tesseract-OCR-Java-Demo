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
	public static void  main(String[] args) { 

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
		 
		JingDong jingDong=new JingDong (url);

		jingDong.setConnectionTimeout(500);
		
		System.out.println("ҳ�泬ʱ����:\t "+jingDong.getConnectionTimeout()); 
		System.out.println("ҳ��URL:\t "+jingDong.getPageUrl());
		System.out.println("�ƶ�ҳ��URL:\t"+jingDong.getMobilePageUrl());
		System.out.println("ҳ�����:\t"+jingDong.getPageTitle());
		System.out.println("ҳ��ؼ���:\t"+jingDong.getPageKeyWords());
		System.out.println("ҳ������:\t"+jingDong.getPageDescription());
//		TSZPage.CONNECT_Time_OUT=5000;
//		System.out.println("ҳ�泬ʱ����:\t "+TSZPage.CONNECT_Time_OUT); 
//		System.out.println(jingDong.getPageHead());
//		System.out.println(jingDong.getPageBody());
	 
		System.out.println("------------------"); 
		System.out.println("��Ʒ�������⣺\t"+jingDong.getItemTitle());

		System.out.println("��Ʒ��ţ�"+jingDong.getItemId( ));
		System.out.println("�г��ۣ�"+jingDong.getMarketPrice());
		System.out.println("�����ۣ�"+jingDong.getJingDongPrice()); 
//		System.out.println("��Ʒ����:"+jingDong.getItemCommentScore());
//		
//	 
		System.out.println("��Ʒ��������:"+jingDong.getTopItemType().TypeName);
		System.out.print("��Ʒ��ϸ����");
		ArrayList<ItemType> itemType=jingDong.getItemType();
		System.out.print("["+itemType.size()+"]:");
		for(int i=0;i<itemType.size();i++)
			System.out.print(itemType.get(i).TypeName+" | ");
//		 
		System.out.println();
		System.out.println("��Ʒ���ƣ�"+jingDong.getItemName());
		System.out.println("��������:"+jingDong.getItemCompany());
		System.out.println("��Ʒ���أ�"+jingDong.getItemMadeArea());
		System.out.println("�ϼ�ʱ�䣺"+jingDong.getItemOnShelfDate());
		System.out.println("��Ʒë�أ�"+jingDong.getItemWeight()); 
		System.out.println("��Ʒ��ͼ:");
		ArrayList<ItemImg> strItemImg=jingDong.getItemImg();
		System.out.println(strItemImg.size());
		for(int i=0;i <strItemImg.size();i++) { 
				System.out.println("\tСͼ:"+strItemImg.get(i).getSmallImgSrc());
				System.out.println("\t��ͼ:"+strItemImg.get(i).getBigImgSrc()); 
		} 
		
		System.out.println("------------------");
		System.out.println("��ȡ��Ʒ������Ϣ��\t"+jingDong.getItemSalesPromotionInfo());
		System.out.println("��ȡ��Ʒ�����еĴ�����Ϣ(����)��\t"+jingDong.getItemTitleAdvertiseWord());
		System.out.println("��ȡ��Ʒ��Ϣ��\t"+jingDong.getItemLargessInfo());
        Date endTime=new Date();
        long timeOfSearch=endTime.getTime()-beginTime.getTime();
        
    	System.out.println(timeOfSearch+"ms\n=============END================");
//    	count[j]=timeOfSearch;
//		}
//		
//		long sum=0;
//		System.out.println("ͬһ��ҳ�����50��");
//		for(int j=0;j<50;j++){
//			System.out.print(count[j]+" ");
//			sum+=count[j];
//		}
//		System.out.println("�ܹ���ʱ:"+sum);
//		System.out.println("ƽ����ʱ:"+sum/50);
	}

}
