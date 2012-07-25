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
			Connection conn=mysql.getConnetction();
			if(!conn.isClosed()) {
				System.out.println("´ò¿ªÁË");
				ResultSet rs=mysql.getRes(conn, "select * from student");
				while(rs.next()){
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace(); 
		}  
	} 
}
