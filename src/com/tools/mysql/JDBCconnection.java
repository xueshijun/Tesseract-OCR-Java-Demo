package com.tools.mysql;
import java.sql.*;
public class JDBCconnection {
    private final String dbDriver = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; //����sql���ݿ�ķ���
	String url = "jdbc:mysql://localhost:3306/mystore?useUnicode=true&characterEncoding=utf8";//(ע�⣺��Ҫ�����κοո񣬷������)

   private final String userName = "root";
   private final String password = "xueshijun";
   private Connection con = null;

   public JDBCconnection() {
       try {
            System.out.println(dbDriver);
           Class.forName(dbDriver).newInstance(); //�������ݿ�����
       } catch (Exception ex) {
           ex.printStackTrace();
           System.out.println("���ݿ����ʧ��");
       }
   }

//�������ݿ�����
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



//�����ݿ�Ĳ�ѯ����
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

//�ر����ݿ�Ĳ���
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
