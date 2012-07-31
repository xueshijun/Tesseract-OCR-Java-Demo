/*
 * Main.java
 *
 * Created on __DATE__, __TIME__
 */

package com.mainpackge;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.htmlparse.threesixzerobuy.JingDong;
import com.htmlparse.threesixzerobuy.JingDongItemPackage;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemType;
import com.htmlparse.threesixzerobuy.mysql.JingDongDB;

/**
 *
 * @author  __USER__
 */
public class Main extends java.awt.Frame {

	/** Creates new form Main */
	public Main() {
		initComponents();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		jTextFieldBegin = new javax.swing.JTextField();
		jTextFieldEnd = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabelTo = new javax.swing.JLabel();
		list1 = new java.awt.List();
		jButtonDataBase = new javax.swing.JButton();
		jButtonTable = new javax.swing.JButton();
		jButtonGetDBNum = new javax.swing.JButton();

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 800));
 
		jLayeredPane1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLayeredPane1MouseClicked(evt);
			}
		}); 
		jTextFieldBegin.setText("100001");
		jTextFieldBegin.setBounds(150, 130, 110, 21);
		jLayeredPane1.add(jTextFieldBegin,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTextFieldEnd.setText("700001");
		jTextFieldEnd.setBounds(520, 130, 90, 21);
		jLayeredPane1
				.add(jTextFieldEnd, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jButton1
				.setText("3.\u63d0\u4ea4(\u7ee7\u7eed\u4e0a\u6b21\u7684\u4f4d\u7f6e)");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jButton1.setBounds(670, 110, 170, 50);
		jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabel1.setText("\u8f93\u5165\u5546\u54c1\u7f16\u53f7\u8303\u56f4");
		jLabel1.setBounds(20, 130, 100, 20);
		jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLabelTo.setText("100001   TO    700001");
		jLabelTo.setBounds(320, 130, 150, 20);
		jLayeredPane1.add(jLabelTo, javax.swing.JLayeredPane.DEFAULT_LAYER);
		list1.setBounds(0, 190, 860, 140);
		jLayeredPane1.add(list1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jButtonDataBase.setText("1.\u521b\u5efa\u6570\u636e\u5e93");
		jButtonDataBase.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonDataBaseActionPerformed(evt);
			}
		});
		jButtonDataBase.setBounds(80, 60, 140, 40);
		jLayeredPane1.add(jButtonDataBase,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jButtonTable.setText("2.\u521b\u5efa\u8868");
		jButtonTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonTableActionPerformed(evt);
			}
		});
		jButtonTable.setBounds(340, 60, 140, 40);
		jLayeredPane1.add(jButtonTable, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jButtonGetDBNum.setText("\u83b7\u53d6DB\u4e2d\u8d77\u59cb\u6570");
		jButtonGetDBNum.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonGetDBNumMouseClicked(evt);
			}
		}); 
		jButtonGetDBNum.setBounds(130, 160, 150, 23);
		jLayeredPane1.add(jButtonGetDBNum,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTabbedPane1.addTab("tab1", jLayeredPane1);

		add(jTabbedPane1, java.awt.BorderLayout.NORTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	
	
	private void jButtonGetDBNumMouseClicked(java.awt.event.MouseEvent evt) {
		conn = JingDongDB.mysql.getConnetction("mystore");
		int now = 0;
		try {
			ResultSet rs = JingDongDB.mysql.getRes(conn,"select max(ItemNumber) from " + JingDongDB.STRTABLE_NAME);
			while (rs.next()) {
				now = Integer.valueOf(rs.getString(1)); 
			}
			this.jTextFieldBegin.setText(String.valueOf(now));
		} catch (Exception ex) {
			//begin = Integer.valueOf(this.jTextFieldBegin.getText());//100001-700001
		}
	}

	private void jLayeredPane1MouseClicked(java.awt.event.MouseEvent evt) {
		System.exit(0);
	}

	static Connection conn = JingDongDB.mysql.getConnetction("test");

	/**
	 * 创建数据库
	 * 
	 */
	private void jButtonDataBaseActionPerformed(java.awt.event.ActionEvent evt) {
		this.list1.add("创建数据库....");
		if (JingDongDB.CreateDataBase(conn, JingDongDB.STRCREATE_DATABASE) == true) {
			//			System.out.println("创建数据库mystore成功");
			this.list1.add("创建数据库mystore成功");

		} else {
			//			System.out.println("创建数据库mystore");
			this.list1.add("创建数据库mystore");
		}
	}

	/**
	 * 创建表
	 */
	private void jButtonTableActionPerformed(java.awt.event.ActionEvent evt) {
		conn = JingDongDB.mysql.getConnetction("mystore");
		this.list1.add("创建表");
		if (JingDongDB.CreateTable(conn, JingDongDB.STRCREATE_PRODUCT_TABLE) == true) {
			this.list1.add("创建product成功");
		} else {
			this.list1.add("创建product失败");
		}
		if (JingDongDB.CreateTable(conn, JingDongDB.STRCREATE_BAD_TABLE) == true) {
			this.list1.add("创建prourl成功");
		} else {
			this.list1.add("创建prourl失败");
		}
	}

	/**
	 * 主入口
	 */ 
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
		int count;
		conn = JingDongDB.mysql.getConnetction("mystore"); 
		try {
			if (!conn.isClosed()) {
				this.list1.add("建立数据库连接...."); 
//				int begin = Integer.valueOf(this.jTextFieldBegin.getText());
//				int end = Integer.valueOf(this.jTextFieldEnd.getText());
//				this.jTextFieldBegin.setText(String.valueOf(begin));
				count = 100001;
				
				while ((count+=504)< 700001) {
					String url = "http://www.360buy.com/product/" + count + ".html"; 
					try {
						JingDong jingDong = new JingDong(url);
						
						jingDong.setConnectionTimeout(500);
						
						Document doc = jingDong.initPage();
						//错误页面
						if (doc == null || doc.title() == "该页无法显示") {//连接不上
							JingDongDB.mysql.executeSql(conn, "insert into "+ JingDongDB.STRTABLE_BAD_NAME+ " values ('" + url + "')"); 
							continue;
						}
						
						String strItemType = jingDong.getTopItemType().TypeName	+ "|";
						ArrayList<ItemType> itemType = jingDong.getItemType();
						for (int i = 0; i < itemType.size(); i++) {
							if (i != itemType.size() - 1) {
								strItemType += itemType.get(i).TypeName + "|";
							} else {
								strItemType += itemType.get(i).TypeName;
							}
						}
						try{
							//插入数据
							if(JingDongDB.GeneralInsertItem(conn,
											"ItemNumber,ItemName,ItemType,JingDongPrice,JingDongPriceUrl,ItemSalesPromotionInfo","'"
											+jingDong.getItemId()+"','"
											+jingDong.getItemName()+"','"
											+strItemType+"','','"
											+jingDong.getJingDongPrice()+"','"
											+jingDong.getItemSalesPromotionInfo()+"'")){	
								this.list1.clear();
								System.out.println("成功插入数据！");								
								this.list1.add("成功插入数据！"+jingDong.getItemId()+"---"+jingDong.getItemName()+"--"+strItemType);
								
							} else {
								this.list1.clear();
								this.list1.add("插入数据失败！");
								System.out.println("插入数据失败！"+jingDong.getItemId()+"---"+jingDong.getItemName()); 
								continue;
							} 
						}catch(Exception ex){
							continue;
						}
//						查询插入
//						ResultSet rs = JingDongDB.mysql.getRes(conn,"select max(ItemNumber) from "+ JingDongDB.STRTABLE_NAME);
//						while (rs.next()) {
//							this.list1.clear();
//							this.list1.add(String.valueOf(count) + " +++ "+ rs.getString(1));
//						}
//						count++;  
					} catch (Exception ex) {//异常页面
						JingDongDB.mysql.executeSql(conn, "insert into "+ JingDongDB.STRTABLE_BAD_NAME + " values ('"+ url + "')");
						continue;
					}
				}
			}else {
				System.out.println("不好意思！数据库连接已关闭了！");
			}
		} catch (NumberFormatException e) { 
			e.printStackTrace(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		}
	}

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
		System.exit(0);
	}//GEN-LAST:event_exitForm



	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButtonDataBase;
	private javax.swing.JButton jButtonGetDBNum;
	private javax.swing.JButton jButtonTable;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelTo;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField jTextFieldBegin;
	private javax.swing.JTextField jTextFieldEnd;
	private java.awt.List list1;
	// End of variables declaration//GEN-END:variables

}