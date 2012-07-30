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
					System.out.println("������ɹ�.......");
				}
				if(mysql.executeSql(conn, STRCREATE_BAD_TABLE)>0){
					System.out.println("������ɹ�.......");
				}
				
				int count=100001;//100001-700001
				while(count<700001){
					try{
//						JingDong jingDong=new JingDong("http://www.360buy.com/product/"+count+".html");
						String url="http://www.360buy.com/product/"+count+".html";
						Document doc=Jsoup.connect(url).timeout(1000).get();
						 if(doc!=null){
							 	if(doc.title()!="��ҳ�޷���ʾ"){ 
									if(mysql.executeSql(conn, "insert into Prourl values ('"+url+"')")>0){
										System.out.println("�ɹ����� "+count+doc.title());
									}else{ 
										 System.out.println("�޴�"+count+"ҳ��");
									}
							 }else{
								 System.out.println("�޴�"+count+"ҳ��");
							 }
						}else{
							 System.out.println("�޴�"+count+"ҳ��");
						}
					}catch(Exception ex){
						System.out.println("��"+count+"�쳣ҳ��");
					}
					count++; 
				} 
			}
		}catch(Exception ex){
			
		}
	}
}
