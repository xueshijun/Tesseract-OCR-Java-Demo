package com.tools.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.DriverManager;
public class MySql {
 
 
	/**
	 * 1.String url = "jdbc:mysql://localhost:3306/"+strDBName+"?useUnicode=true&characterEncoding=utf8";
	 * DriverManager.getConnection(url, user, password);
//        	
	 * 2.String url = "jdbc:mysql://localhost:3306/"+strDBName+"?user=root&password=xueshijun&unicode=true&charachterEncoding=utf8";
	 * DriverManager.getConnection(url);

	 * 
	 * 
	 * @param strDBName
	 * @return
	 */
	public   Connection getConnetction(String strDBName) {
		String driver = "com.mysql.jdbc.Driver"; 
    	// URL指向要访问的数据库名
//    	String url = "jdbc:mysql://localhost:3306/stu?characterEncoding=utf8";
//		jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf-8(注意：不要出现任何空格，否则出错)
		String url = "jdbc:mysql://localhost:3306/"+strDBName+"?useUnicode=true&characterEncoding=utf8";
//		String url = "jdbc:mysql://localhost:3306/"+strDBName+"?user=root&password=xueshijun&unicode=true&charachterEncoding=utf8";
    	 


    	// MySQL配置时的用户名 
    	String user = "root"; 
    	// Java连接MySQL配置时的密码 
    	String password = "xueshijun"; 
		try
        { 
        	// 加载驱动程序 
        	Class.forName(driver); 
        	// 连接数据库 
        	return DriverManager.getConnection(url, user, password);
//        	return DriverManager.getConnection(url);
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        }
		return null;  
	}
	
	private void getTable(Connection conn,String query)   {   
		try {   
		//执行SQL语句     
			Statement statement = conn.createStatement();   
			ResultSet resultSet = statement.executeQuery( query );   
		//在表格中显示查询结果   
			displayResultSet(resultSet);
		}   
	catch ( SQLException sqlex ) 
		{   sqlex.printStackTrace();   }   }    

	private void displayResultSet( ResultSet rs )   throws SQLException   
	{   
		//定位到达第一条记录  
		boolean moreRecords = rs.next();  
		//如果没有记录，则提示一条消息  
		if ( ! moreRecords ) {   
			System.out.println("结果集中无记录" );    
		}   
		Vector columnHeads = new Vector();   
		Vector rows = new Vector();   
		try {   
			//获取字段的名称  
			ResultSetMetaData  rsmd = rs.getMetaData();  
			for ( int i = 1; i <= rsmd.getColumnCount(); ++i )  
				columnHeads.addElement( rsmd.getColumnName( i ) );
			//获取记录集 
			do {   
				rows.addElement( getNextRow( rs, rsmd ) );   
			} while ( rs.next() );  
			//在表格中显示查询结果    
			
		}   
		catch ( SQLException sqlex ){   
				sqlex.printStackTrace();   
			}   
	}     


	private Vector getNextRow( ResultSet rs,ResultSetMetaData rsmd ) 
	throws SQLException   {   
		Vector currentRow = new Vector();   
		for ( int i = 1; i <= rsmd.getColumnCount(); ++i )  
		currentRow.addElement( rs.getString( i ) );  
		//返回一条记录   
		return currentRow;  
	}   

	/**
	 * 关闭数据库
	 */
	public void shutDown(Connection conn)   {   
		try {    
			conn.close();   //断开数据库连接
		}   
		catch ( SQLException sqlex ) {  
			System.err.println( "Unable to disconnect" ); 
			sqlex.printStackTrace();  
		}  
	}  
	
    /**
     * 执行SQL语句
     * */
    public int executeSql(Connection conn,String sql) 
    { 
    	Statement statement=null;
    	int result=0;
    	try {
    		if(!conn.isClosed()) { 
    			statement=conn.createStatement(
    					ResultSet.TYPE_SCROLL_SENSITIVE,
    					ResultSet.CONCUR_UPDATABLE); 
    			 result=statement.executeUpdate(sql);
    			return result;
			}
		} catch (SQLException e) {  
	        e.printStackTrace(); 
	        return result; 
		} 
		finally {
			if(statement!=null) {
				try {
					statement.close();
				}catch (SQLException ex) {
		            // ignore
					ex.printStackTrace();
					return result; 
		        } 
			}
		}
		return  result;
    } 

    
    /**
     * 返回结果集
     * */ 
	public ResultSet getRes(Connection conn,String sql){ 
    	Statement statement=null;
    	ResultSet result=null;
    	try
        { 
        	statement=conn.createStatement(
        			ResultSet.TYPE_SCROLL_SENSITIVE,
        			ResultSet.CONCUR_READ_ONLY); 
            result=statement.executeQuery(sql); 
            return result; 
        } 
        catch(Exception e){ 
            e.printStackTrace(); 
            return result; 
        }
//        finally { 
//        	if (result != null) {
//                try {
//                	result.close();
//                } catch (SQLException ex) {
//                    // ignore
//                }
//            } 
//            if (statement != null) {
//                try {
//                	statement.close();
//                } catch (SQLException ex) {
//                    // ignore
//                }
//            }
//            return result;
//
//        }  
    } 
}
