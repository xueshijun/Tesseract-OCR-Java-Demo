package com.htmlparse.threesixzerobuy.mysql;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.baseUrl.TSZPage;
import com.htmlparse.threesixzerobuy.JingDong;
import com.htmlparse.threesixzerobuy.JingDongItem;
import com.htmlparse.threesixzerobuy.JingDongItemPackage;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemImg;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemType;
import com.tools.mysql.MySql;


public class JingDongDB{
	public final static String STRDATABASE_NAME="mystore";
	public final static String STRTABLE_NAME="product";
	
//	public final static String STRCREATEDATABASE="CREATE DATABASE "+STRDATABASE_NAME +"default character set utf8";
	public final static String STRCREATEDATABASE="CREATE DATABASE "+STRDATABASE_NAME;
	
	public final static String STRALTERDATABASE_UTF8="ALTER DATABASE "+STRDATABASE_NAME +" character set utf8";
	public final static String STRALTERTABLE_UTF8="alter table "+STRTABLE_NAME+" character set utf8";
	
	public final static String STRCREATETABLE=
			"create table "+STRTABLE_NAME+"(" 
				+"PageUrl varchar(1000),"
				+"PageTitle varchar(1000),"
				+"PageKeyWords varchar(1000),"
				+"PageDescription varchar(1000)," 
				+"ItemTitle  varchar(400) ,"
				+"ItemNumber varchar(400),"
				+"ItemName varchar(1000),"//商品名 
				+"MarketPrice varchar(40),"
				+"JingDongPrice varchar(40),"
				+"JingDongPriceUrl varchar(200)," 
				+"ItemMadeArea varchar(100),"//商品产地
				+"ItemOnShelfDate varchar(100),"//上架时间
				+"ItemCompany varchar(100),"//生产厂家
				+"ItemWeight varchar(50),"//商品毛重
				+"ItemTitleAdvertiseWord varchar(1000),"//标题中的广告语
				+"ItemSalesPromotionInfo varchar(1000),"//获取促销信息
				+"ItemLargessInfo varchar(1000),"//赠品信息(暂时没有获取到)
				+"ItemType varchar(1000),"
				+"primary key(ItemNumber)" 
			+")";

	
	static MySql mysql=new MySql();
	public static void main(String[] args) {
		try{ 	
			JingDongItemPackage jingDongItemPackage=null;
			Connection conn=mysql.getConnetction("test"); 
			JingDong.CONNECT_Time_OUT=1000;
			
			if(!conn.isClosed()) {
				System.out.println("建立数据库连接...."); 
				System.out.println("创建数据库...."); 
				
				CreateDataBase(conn,STRCREATEDATABASE);
//				AlterDataBase(conn,STRALTERDATABASE_UTF8);
				
				conn=mysql.getConnetction(STRDATABASE_NAME);				
				CreateTable(conn,STRCREATETABLE);
//				AlterTable(conn,STRALTERTABLE_UTF8);
				
				JingDong jingDong=new JingDong("http://www.360buy.com/product/280127.html");   
				String strItemType=jingDong.getTopItemType().TypeName+"|";
				ArrayList<ItemType> itemType=jingDong.getItemType(); 
				for(int i=0;i<itemType.size();i++){
					if(i!=itemType.size()-1){
						strItemType+=itemType.get(i).TypeName+"|"; 
					}else{
						strItemType+=itemType.get(i).TypeName;
					} 
				} 
//							String PageUrl,String PageTitle,String PageKeyWords,String PageDescription,
//							String ItemNumber,String ItemTitle,String ItemName,String MarketPrice,
//							String JingDongPrice,String JingDongPriceUrl,String ItemMadeArea,
//							String ItemOnShelfDate,String ItemCompany,String ItemWeight,
//							String ItemTitleAdvertiseWord,String ItemSalesPromotionInfo,
//							String ItemLargessInfo,String ItemType) 
				
				
				jingDongItemPackage=new JingDongItemPackage(
						jingDong.getPageUrl(),jingDong.getPageTitle(),jingDong.getPageKeyWords(),jingDong.getPageDescription(),
						jingDong.getItemId(),jingDong.getItemTitle(),jingDong.getItemName(),jingDong.getMarketPrice(),
						"",jingDong.getJingDongPrice(),jingDong.getItemMadeArea(),
						jingDong.getItemOnShelfDate(),jingDong.getItemCompany(),jingDong.getItemWeight(),
						jingDong.getItemTitleAdvertiseWord(),jingDong.getItemSalesPromotionInfo(),
						jingDong.getItemLargessInfo(),strItemType);
//				System.out.println(jingDongItemPackage.ItemCompany);
//				System.out.println(jingDongItemPackage.ItemLargessInfo);
//				System.out.println(jingDongItemPackage.ItemMadeArea);
//				System.out.println(jingDongItemPackage.ItemName);
//				System.out.println(jingDongItemPackage.ItemNumber);
//				System.out.println(jingDongItemPackage.ItemOnShelfDate);
//				System.out.println(jingDongItemPackage.ItemSalesPromotionInfo);
//				System.out.println(jingDongItemPackage.ItemTitle);
//				System.out.println(jingDongItemPackage.ItemTitleAdvertiseWord);
//				System.out.println(jingDongItemPackage.ItemType);
//				System.out.println(jingDongItemPackage.ItemWeight);
//				System.out.println(jingDongItemPackage.JingDongPrice);
//				System.out.println(jingDongItemPackage.JingDongPriceUrl);
//				System.out.println(jingDongItemPackage.MarketPrice);
//				System.out.println(jingDongItemPackage.PageDescription);
//				System.out.println(jingDongItemPackage.PageKeyWords);
//				System.out.println(jingDongItemPackage.PageTitle);
//				System.out.println(jingDongItemPackage.PageUrl);
 
				if(InsertItem(conn, jingDongItemPackage)){
					System.out.println("成功插入数据！");
				}
				else{ 
					System.out.println("插入数据失败！");
				}
				ResultSet rs=mysql.getRes(conn, "select * from "+STRTABLE_NAME);
				while(rs.next()){
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2)); 
					System.out.println(rs.getString(3)); 
					System.out.println(rs.getString(4));
					System.out.println(rs.getString(5));
					System.out.println(rs.getString(6));
					System.out.println(rs.getString(7));
					System.out.println(rs.getString(8));
					System.out.println(rs.getString(9));
					System.out.println(rs.getString(10));
					System.out.println(rs.getString(11));
					System.out.println(rs.getString(12));
					System.out.println(rs.getString(13));
					System.out.println(rs.getString(14));
					System.out.println(rs.getString(15));
					System.out.println(rs.getString(16));
					System.out.println(rs.getString(17));
					System.out.println(rs.getString(18)); 
				}
			}
			else{
				System.out.println("不好意思！数据库连接已关闭了！");
			}
		}catch(Exception ex){
			ex.printStackTrace(); 
		} 
	} 

	
	/**
	 * 创建数据库
	 * @param conn
	 * @param str
	 * @return
	 */
	public static void CreateDataBase(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("创建数据库成功！"); 
			} 
			else {
				System.out.println("已经存在该DB！"); 
			} 
		}catch(Exception ex){
			System.out.println("已经存在该DB！2");
		}
	} 
	/**
	 * 修改数据库字符
	 * @param conn
	 * @param str
	 */
	public static void AlterDataBase(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("修改字符成功！"); 
			} 
			else {			
				System.out.println("修改字符失败！"); 
			} 
		}catch(Exception ex){
			System.out.println("修改字符失败！2");
		}
	} 
	/**
	 * 创建表
	 * @param conn
	 * @param str
	 * @return
	 */
	public static void CreateTable(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("创建表成功！"); 
			}
			else {
				System.out.println("已经存在该表！"); 
			}
		}catch(Exception ex){
			System.out.println("已经存在该表！2"); 
		}
	}
	
	/**
	 * 修改
	 * @param conn
	 * @param str
	 * @return
	 */
	public static void AlterTable(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("修改表成功！"); 
			}
			else {
				System.out.println("修改表失败！"); 
			}
		}catch(Exception ex){
			System.out.println("修改表异常！2"); 
		}
	}
	/**
	 *  插入数据
	 * */
	public static boolean InsertItem(Connection conn,JingDongItemPackage jingDongItemPackage){ 
	String strInsert="insert into product(" 
		+"PageUrl,PageTitle,PageKeyWords,PageDescription,ItemTitle,"
		+"ItemNumber,ItemName,MarketPrice,JingDongPrice,JingDongPriceUrl,"
		+"ItemMadeArea,ItemOnShelfDate,ItemCompany,ItemWeight,ItemTitleAdvertiseWord,"
		+"ItemSalesPromotionInfo,ItemLargessInfo,ItemType) values ('"+
		jingDongItemPackage.PageUrl+"','"+
		jingDongItemPackage.PageTitle+"','"+
		jingDongItemPackage.PageKeyWords+"','"+
		jingDongItemPackage.PageDescription+"','"+
		jingDongItemPackage.ItemTitle+"','"+
		jingDongItemPackage.ItemNumber+"','"+
		jingDongItemPackage.ItemName+"','"+
		jingDongItemPackage.MarketPrice+"','"+
		jingDongItemPackage.JingDongPrice+"','"+
		jingDongItemPackage.JingDongPriceUrl+"','"+
		jingDongItemPackage.ItemMadeArea+"','"+
		jingDongItemPackage.ItemOnShelfDate+"','"+
		jingDongItemPackage.ItemCompany+"','"+
		jingDongItemPackage.ItemWeight+"','"+
		jingDongItemPackage.ItemTitleAdvertiseWord+"','"+
		jingDongItemPackage.ItemSalesPromotionInfo+"','"+
		jingDongItemPackage.ItemLargessInfo+"','"+
		jingDongItemPackage.ItemType+"')";
		if(mysql.executeSql(conn, strInsert)>0) 
			return true;
		else
			return false;
		
	}

}
