package com.htmlparse.threesixzerobuy.mysql;

import java.sql.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.htmlparse.threesixzerobuy.JingDong;
import com.tools.mysql.MySql;

public class JingDongProductURL {
	
	static String STRCREATE_GOOD_TABLE="CREATE TABLE goodurl(" +
			"URL varchar(200)" +
			")";
	static String STRCREATE_BAD_TABLE="CREATE TABLE goodurl(" +
	"URL varchar(200)" +
	")";
	static MySql mysql=new MySql();
	public static void main(String[] args) {
		try{ 	
			Connection conn=mysql.getConnetction("mystore"); 
			JingDong.CONNECT_Time_OUT=1000; 
			if(!conn.isClosed()) {
				if(mysql.executeSql(conn, STRCREATE_GOOD_TABLE)>0){
					System.out.println("创建表成功.......");
				}
				if(mysql.executeSql(conn, STRCREATE_BAD_TABLE)>0){
					System.out.println("创建表成功.......");
				}
				
				int count=100001;//100001-700001
				while(count<700001){
					try{
//						JingDong jingDong=new JingDong("http://www.360buy.com/product/"+count+".html");
						String url="http://www.360buy.com/product/"+count+".html";
						Document doc=Jsoup.connect(url).timeout(1000).get();
						 if(doc!=null){
							 	if(doc.title()!="该页无法显示"){ 
									if(mysql.executeSql(conn, "insert into Prourl values ('"+url+"')")>0){
										System.out.println("成功插入 "+count+doc.title());
									}else{ 
										 System.out.println("无此"+count+"页面");
									}
							 }else{
								 System.out.println("无此"+count+"页面");
							 }
						}else{
							 System.out.println("无此"+count+"页面");
						}
					}catch(Exception ex){
						System.out.println("此"+count+"异常页面");
					}
					count++; 
				} 
			}
		}catch(Exception ex){
			
		}
	}
}
