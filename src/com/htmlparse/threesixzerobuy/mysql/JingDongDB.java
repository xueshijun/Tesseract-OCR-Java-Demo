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
	public final static String STRTABLE_BAD_NAME="badurl";
	
	public final static String STRCREATE_DATABASE="CREATE DATABASE "+STRDATABASE_NAME +" default character set utf8";	
	public final static String STRALTER_DATABASE_UTF8="ALTER DATABASE "+STRDATABASE_NAME +" character set utf8";
	public final static String STRALTER_TABLE_UTF8="alter table "+STRTABLE_NAME+" character set utf8";
	
	 
	public final static String STRCREATE_PRODUCT_TABLE=
		"create table product(" 
			+"PageUrl varchar(200),"
			+"PageTitle varchar(200),"
			+"PageKeyWords varchar(200),"
			+"PageDescription LONGTEXT," 
			+"ItemTitle  varchar(200) ,"
			+"ItemNumber varchar(200),"
			+"ItemName varchar(200),"
			+"MarketPrice varchar(40),"
			+"JingDongPrice varchar(40),"
			+"JingDongPriceUrl varchar(200)," 
			+"ItemMadeArea varchar(100),"
			+"ItemOnShelfDate varchar(100),"
			+"ItemCompany varchar(100),"
			+"ItemWeight varchar(50),"
			+"ItemTitleAdvertiseWord varchar(200),"
			+"ItemSalesPromotionInfo varchar(200),"
			+"ItemLargessInfo varchar(200),"
			+"ItemType varchar(200),"
			+"primary key(ItemNumber)" 
		+")";

	//存储异常页面URL的表
	public final static String STRCREATE_BAD_TABLE="CREATE TABLE "+STRTABLE_BAD_NAME+"(" +
	"URL varchar(200)" +
	")";
	
	
	public static MySql mysql=new MySql();
	public   void mymain(String[] args) {
//		try{ 	
//			JingDongItemPackage jingDongItemPackage=null;
//			Connection conn=mysql.getConnetction("test"); 
//			JingDong.CONNECT_Time_OUT=1000;
//			
//			if(!conn.isClosed()) {
//				System.out.println("建立数据库连接...."); 
//				System.out.println("创建数据库...."); 
//				
//				CreateDataBase(conn,STRCREATE_DATABASE);
////				AlterDataBase(conn,STRALTER_DATABASE_UTF8);
//				
//				conn=mysql.getConnetction("mystore");				
//				CreateTable(conn, STRCREATE_PRODUCT_TABLE);
////				AlterTable(conn,STRALTER_TABLE_UTF8); 
//				
//				CreateTable(conn, STRCREATE_BAD_TABLE);
//				
//				int count=100516;//100001-700001
//				while(count<700001){ 
//					String url="http://www.360buy.com/product/"+count+".html"; 
//	 				JingDong jingDong=new JingDong(url); 
//	 				Document doc=jingDong.initPage();
//	 				if(doc==null||doc.title()=="该页无法显示"){//连接不上
//	 					mysql.executeSql(conn, "insert into "+STRTABLE_BAD_NAME+" values ('"+url+"')");
//	 					continue;
//	 				} 
//	 				String strItemType=jingDong.getTopItemType().TypeName+"|";
//	 				ArrayList<ItemType> itemType=jingDong.getItemType(); 
//	 				for(int i=0;i<itemType.size();i++){
//	 					if(i!=itemType.size()-1){
//	 						strItemType+=itemType.get(i).TypeName+"|"; 
//	 					}else{
//	 						strItemType+=itemType.get(i).TypeName;
//	 					} 
//	 				} 
//	 				jingDongItemPackage=new JingDongItemPackage(
//	 						jingDong.getPageUrl(),jingDong.getPageTitle(),jingDong.getPageKeyWords(),jingDong.getPageDescription(),
//	 						jingDong.getItemId(),jingDong.getItemTitle(),jingDong.getItemName(),jingDong.getMarketPrice(),
//	 						"",jingDong.getJingDongPrice(),jingDong.getItemMadeArea(),
//	 						jingDong.getItemOnShelfDate(),jingDong.getItemCompany(),jingDong.getItemWeight(),
//	 						jingDong.getItemTitleAdvertiseWord(),jingDong.getItemSalesPromotionInfo(),
//	 						jingDong.getItemLargessInfo(),strItemType);
//
//	
// 
//					if(InsertItem(conn, jingDongItemPackage)){
//						System.out.println("成功插入数据！");
//					}
//					else{ 
//						System.out.println("插入数据失败！");
//					}
//					ResultSet rs=mysql.getRes(conn, "select * from "+STRTABLE_NAME);
//					while(rs.next()){
//						System.out.println(rs.getString(1));
//							System.out.println(rs.getString(2)); 
//						System.out.println(rs.getString(3)); 
//						System.out.println(rs.getString(4));
//						System.out.println(rs.getString(5));
//							System.out.println(rs.getString(6));
//						System.out.println(rs.getString(7));
//						System.out.println(rs.getString(8));
//						System.out.println(rs.getString(9));
//						System.out.println(rs.getString(10));
//						System.out.println(rs.getString(11));
//						System.out.println(rs.getString(12));
//						System.out.println(rs.getString(13));
//						System.out.println(rs.getString(14));
//						System.out.println(rs.getString(15));
//						System.out.println(rs.getString(16));
//						System.out.println(rs.getString(17));
//						System.out.println(rs.getString(18)); 
//					} 
//					count++;
//				}
//			}
//			else{
//				System.out.println("不好意思！数据库连接已关闭了！");
//			}
//		}catch(Exception ex){
//			ex.printStackTrace(); 
//		} 
	} 

	
	/**
	 * 创建数据库
	 * @param conn
	 * @param str
	 * @return
	 */
	public static boolean CreateDataBase(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("创建数据库成功！"); 
				return true;
			} 
			else {
				System.out.println("已经存在该DB！"); 
				return false;
			} 
		}catch(Exception ex){
			System.out.println("已经存在该DB！2");
			return false;
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
	public static boolean CreateTable(Connection conn,String str){
		try{
			if(mysql.executeSql(conn, str)>0){
				System.out.println("创建表成功！"); 
				return true;
			}
			else {
				System.out.println("已经存在该表！"); 
				return false;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage()); 
			return false;
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
