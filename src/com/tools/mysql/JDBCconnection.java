package com.tools.mysql;
import java.sql.*;
public class JDBCconnection {
    private final String dbDriver = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; //连接sql数据库的方法
	String url = "jdbc:mysql://localhost:3306/mystore?useUnicode=true&characterEncoding=utf8";//(注意：不要出现任何空格，否则出错)

   private final String userName = "root";
   private final String password = "xueshijun";
   private Connection con = null;

   public JDBCconnection() {
       try {
            System.out.println(dbDriver);
           Class.forName(dbDriver).newInstance(); //加载数据库驱动
       } catch (Exception ex) {
           ex.printStackTrace();
           System.out.println("数据库加载失败");
       }
   }

//创建数据库连接
   public boolean creatConnection() {
       try {
           con = DriverManager.getConnection(url, userName, password);
           con.setAutoCommit(true);

       } catch (SQLException e) {
           System.out.println(e.getMessage());
           System.out.println("creatConnectionError!");
       }
       return true;
   }



//对数据库的查询操作
   public ResultSet executeQuery(String sql) {
       ResultSet rs;
       try {
           if (con == null) {
               creatConnection();
           }
           Statement stmt = con.createStatement();
           try {
               rs = stmt.executeQuery(sql);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
               return null;
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
           System.out.println("executeQueryError!");
           return null;
       }
       return rs;
   }

//关闭数据库的操作
   public void closeConnection() {
       if (con != null) {
           try {
               con.close();
           } catch (SQLException e) {
               e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
               System.out.println("Failed to close connection!");
           } finally {
               con = null;
           }
       }
   }

}
