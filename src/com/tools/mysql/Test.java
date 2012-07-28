package com.tools.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		MySql mysql=new MySql();
		try{ 
			Connection conn=mysql.getConnetction("stu");
			if(!conn.isClosed()) {
				System.out.println("打开了");
				ResultSet rs=mysql.getRes(conn, "select * from student");
				while(rs.next()){
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
				} 
//				String str="create table product(" 
//					+"PageUrl varchar(100),"
//					+"PageTitle varchar(300),"
//					+"PageKeyWords varchar(300),"
//					+"PageDescription varchar(600)," 
//					+"ItemTitle  varchar(100) ,"
//					+"ItemNumber varchar(100),"
//					+"ItemName varchar(100),"//商品名 
//					+"MarketPrice varchar(40),"
//					+"JingDongPrice varchar(40),"
//					+"JingDongPriceUrl varchar(100)," 
//					+"ItemMadeArea varchar(100),"//商品产地
//					+"ItemOnShelfDate varchar(100),"//上架时间
//					+"ItemCompany varchar(100),"//生产厂家
//					+"ItemWeight varchar(50),"//商品毛重
//					+"ItemTitleAdvertiseWord varchar(200),"//标题中的广告语
//					+"ItemSalesPromotionInfo varchar(400),"//获取促销信息
//					+"ItemLargessInfo varchar(200),"//赠品信息(暂时没有获取到)
//					+"ItemType varchar(200),"
//					+"primary key(ItemNumber)" 
//				+")";
				String str="insert into student(SNO,SNAME,SEX)values('2','xuexiao','女')";
				if(mysql.executeSql(conn,str)>0){
					System.out.println("插入成功");
				}
				else{
					System.out.println("插入失败");}
				
			}
		}catch(Exception ex){
			ex.printStackTrace(); 
		}  
	} 
}
