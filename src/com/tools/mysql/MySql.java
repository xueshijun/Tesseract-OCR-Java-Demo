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
    	// URLָ��Ҫ���ʵ����ݿ���
//    	String url = "jdbc:mysql://localhost:3306/stu?characterEncoding=utf8";
		String url = "jdbc:mysql://localhost:3306/"+strDBName+"?useUnicode=true&characterEncoding=utf8";//(ע�⣺��Ҫ�����κοո񣬷������)
//		String url = "jdbc:mysql://localhost:3306/"+strDBName+"?user=root&password=xueshijun&unicode=true&charachterEncoding=utf8"; 
    	 


    	// MySQL����ʱ���û��� 
    	String user = "root";
//    	String user = "xueshijun"; 
    	// Java����MySQL����ʱ������ 
    	String password = "xueshijun"; 
		try
        { 
        	// ������������ 
        	Class.forName(driver); 
        	// �������ݿ� 
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
		//ִ��SQL���     
			Statement statement = conn.createStatement();   
			ResultSet resultSet = statement.executeQuery( query );   
		//�ڱ������ʾ��ѯ���   
			displayResultSet(resultSet);
		}   
	catch ( SQLException sqlex ) 
		{   sqlex.printStackTrace();   }   }    

	private void displayResultSet( ResultSet rs )   throws SQLException   
	{   
		//��λ�����һ����¼  
		boolean moreRecords = rs.next();  
		//���û�м�¼������ʾһ����Ϣ  
		if ( ! moreRecords ) {   
			System.out.println("��������޼�¼" );    
		}   
		Vector columnHeads = new Vector();   
		Vector rows = new Vector();   
		try {   
			//��ȡ�ֶε�����  
			ResultSetMetaData  rsmd = rs.getMetaData();  
			for ( int i = 1; i <= rsmd.getColumnCount(); ++i )  
				columnHeads.addElement( rsmd.getColumnName( i ) );
			//��ȡ��¼�� 
			do {   
				rows.addElement( getNextRow( rs, rsmd ) );   
			} while ( rs.next() );  
			//�ڱ������ʾ��ѯ���    
			
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
		//����һ����¼   
		return currentRow;  
	}   

	/**
	 * �ر����ݿ�
	 */
	public void shutDown(Connection conn)   {   
		try {    
			conn.close();   //�Ͽ����ݿ�����
		}   
		catch ( SQLException sqlex ) {  
			System.err.println( "Unable to disconnect" ); 
			sqlex.printStackTrace();  
		}  
	}  
	
    /**
     * ִ��SQL���
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
     * ���ؽ����
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
