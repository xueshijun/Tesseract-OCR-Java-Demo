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
				System.out.println("����");
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
//					+"ItemName varchar(100),"//��Ʒ�� 
//					+"MarketPrice varchar(40),"
//					+"JingDongPrice varchar(40),"
//					+"JingDongPriceUrl varchar(100)," 
//					+"ItemMadeArea varchar(100),"//��Ʒ����
//					+"ItemOnShelfDate varchar(100),"//�ϼ�ʱ��
//					+"ItemCompany varchar(100),"//��������
//					+"ItemWeight varchar(50),"//��Ʒë��
//					+"ItemTitleAdvertiseWord varchar(200),"//�����еĹ����
//					+"ItemSalesPromotionInfo varchar(400),"//��ȡ������Ϣ
//					+"ItemLargessInfo varchar(200),"//��Ʒ��Ϣ(��ʱû�л�ȡ��)
//					+"ItemType varchar(200),"
//					+"primary key(ItemNumber)" 
//				+")";
				String str="insert into student(SNO,SNAME,SEX)values('2','xuexiao','Ů')";
				if(mysql.executeSql(conn,str)>0){
					System.out.println("����ɹ�");
				}
				else{
					System.out.println("����ʧ��");}
				
			}
		}catch(Exception ex){
			ex.printStackTrace(); 
		}  
	} 
}
