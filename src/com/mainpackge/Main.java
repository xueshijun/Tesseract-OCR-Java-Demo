/*
 * Main.java
 *
 * Created on __DATE__, __TIME__
 */

package com.mainpackge;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.htmlparse.threesixzerobuy.JingDong;
import com.htmlparse.threesixzerobuy.JingDongItemPackage;
import com.htmlparse.threesixzerobuy.JingDongItem.ItemType;
import com.htmlparse.threesixzerobuy.mysql.JingDongDB;
import com.image.SaveInternetImage;
import com.ocr.OCR;
import com.tools.mysql.MySql;

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
		jLayeredPane2 = new javax.swing.JLayeredPane();
		jButtonStart = new javax.swing.JButton();
		list2 = new java.awt.List();

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
		jTextFieldBegin.setBounds(150, 130, 110, -1);
		jLayeredPane1.add(jTextFieldBegin,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTextFieldEnd.setText("700001");
		jTextFieldEnd.setBounds(520, 130, 90, -1);
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
 
		jButtonGetDBNum.setBounds(130, 160, 150, -1);
		jLayeredPane1.add(jButtonGetDBNum,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTabbedPane1.addTab("tab1", jLayeredPane1);

		jButtonStart.setText("\u5f00\u59cb\u89e3\u6790\u56fe\u7247");
		jButtonStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonStartActionPerformed(evt);
			}
		});
		jButtonStart.setBounds(240, 70, 210, 23);
		jLayeredPane2.add(jButtonStart, javax.swing.JLayeredPane.DEFAULT_LAYER);
		list2.setBounds(90, 110, 660, 70);
		jLayeredPane2.add(list2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTabbedPane1.addTab("tab2", jLayeredPane2);

		add(jTabbedPane1, java.awt.BorderLayout.NORTH);

		pack();
	}

	/**
	 * 解析图片*/
	private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {
		runAnalylizeImg();
	}
	/**
	 * 解析图片*/
	private void runAnalylizeImg(){
		new Thread(){
			public void run(){

				Connection conn = JingDongDB.mysql.getConnetction("mystore");

				MySql mysql = new MySql();
				ResultSet rs = mysql
						.getRes(
								conn,"select ItemNumber,ItemName,ItemType,JingDongPrice,JingDongPriceUrl,ItemSalesPromotionInfo from product");
				try {
					while (rs.next()) {
						//			System.out.println("ItemNumber:" + rs.getString(1));
						//			System.out.println("ItemName:" + rs.getString(2));
						//			System.out.println("ItemType:" + rs.getString(3));
						//			System.out.println("JingDongPrice:" + rs.getString(4));
						//			System.out.println("JingDongPriceUrl:" + rs.getString(5));
						//			System.out.println("ItemSalesPromotionInfo:" + rs.getString(6));

						//下载图片
						SaveInternetImage pic = new SaveInternetImage();/*创建实例*/
						//	  		String photoUrl ="http://price.360buyimg.com/gp280127,3.png";
						String photoUrl = rs.getString(5);
						/*photoUrl.substring(photoUrl.lastIndexOf("/")的方法将返回最后一个符号为
						 * ‘/’后photoUrl变量中的所有字符，包裹此自身符号*/
						String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
						String filePath = "E:\\";
						/*调用函数，并且进行传参*/
						if (pic.saveUrlAs(photoUrl, filePath + fileName)) {
//							System.out.println("Run ok!" + fileName);

							//解析图片
							//	String path = "E:\\gp280127,3.png";
							String valCode="";
							String path = "E:\\"+fileName;
							try {
								valCode = new OCR().recognizeText(new File(path), "png");
								System.out.println("解析：" + valCode.trim());
								
								if(mysql.executeSql(conn, "update product set JingDongPrice='"+valCode+"' where ItemNumber="+ rs.getString(1)+"")>0){ 	
									list2.add(valCode); 
								}
								
							} catch (IOException e) {
								e.printStackTrace(); 
							} catch (Exception e) {
								e.printStackTrace(); 
							}
							 
						}  
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.start();
	}  
	
	
	
	private void jButtonGetDBNumMouseClicked(java.awt.event.MouseEvent evt) {
		conn = JingDongDB.mysql.getConnetction("mystore");
		int now = 0;
		try {
			ResultSet rs = JingDongDB.mysql.getRes(conn,
					"select max(ItemNumber) from " + JingDongDB.STRTABLE_NAME);
			while (rs.next()) {
				now = Integer.valueOf(rs.getString(1));
			}
			this.jTextFieldBegin.setText(String.valueOf(now));
		} catch (Exception ex) {
			//begin = Integer.valueOf(this.jTextFieldBegin.getText());//100001-700001
		}
	}

	private void jLayeredPane1MouseClicked(java.awt.event.MouseEvent evt) {
//		System.exit(0);
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
		runAddDataThread();
	}

	/**
	 * */
	public void runAddDataThread(){
		new Thread(){
			public void run(){
				int count;
				conn = JingDongDB.mysql.getConnetction("mystore");
				try {
					if (!conn.isClosed()) {
						list1.add("建立数据库连接....");
						//				int begin = Integer.valueOf(this.jTextFieldBegin.getText());
						//				int end = Integer.valueOf(this.jTextFieldEnd.getText());
						//				this.jTextFieldBegin.setText(String.valueOf(begin));
						count = 100001;

						while ((count += 104) < 700001) {
							String url = "http://www.360buy.com/product/" + count
									+ ".html";
							try {
								JingDong jingDong = new JingDong(url);

								jingDong.setConnectionTimeout(500);

								Document doc = jingDong.initPage();
								//错误页面
								if (doc == null || doc.title() == "该页无法显示") {//连接不上
									JingDongDB.mysql.executeSql(conn, "insert into "
											+ JingDongDB.STRTABLE_BAD_NAME
											+ " values ('" + url + "')");
									continue;
								}

								String strItemType = jingDong.getTopItemType().TypeName
										+ "|";
								ArrayList<ItemType> itemType = jingDong.getItemType();
								for (int i = 0; i < itemType.size(); i++) {
									if (i != itemType.size() - 1) {
										strItemType += itemType.get(i).TypeName + "|";
									} else {
										strItemType += itemType.get(i).TypeName;
									}
								}
								try {
									//插入数据
									if (JingDongDB
											.GeneralInsertItem(
													conn,
													"ItemNumber,ItemName,ItemType,JingDongPrice,JingDongPriceUrl,ItemSalesPromotionInfo,PageUrl",
													"'"
															+ jingDong.getItemId()
															+ "','"
															+ jingDong.getItemName()
															+ "','"
															+ strItemType
															+ "','','"
															+ jingDong
																	.getJingDongPrice()
															+ "','"
															+ jingDong.getItemSalesPromotionInfo()+ "','"
															+ jingDong.getPageUrl()+"'")) {
										list1.clear();
										System.out.println("成功插入数据！");
										list1.add("成功插入数据！" + jingDong.getItemId()
												+ "---" + jingDong.getItemName() + "--"
												+ strItemType);

									} else {
										list1.clear();
										list1.add("插入数据失败！"
												+ jingDong.getItemId() + "---"
												+ jingDong.getItemName());
										System.out.println("插入数据失败！"
												+ jingDong.getItemId() + "---"
												+ jingDong.getItemName());
										continue;
									}
								} catch (Exception ex) {
									continue;
								}
								//	查询插入
								//	ResultSet rs = JingDongDB.mysql.getRes(conn,"select max(ItemNumber) from "+ JingDongDB.STRTABLE_NAME);
								//	while (rs.next()) {
								//		this.list1.clear();
								//		this.list1.add(String.valueOf(count) + " +++ "+ rs.getString(1));
								//	}
								//	count++;  
							} catch (Exception ex) {//异常页面
								JingDongDB.mysql.executeSql(conn, "insert into "
										+ JingDongDB.STRTABLE_BAD_NAME + " values ('"
										+ url + "')");
								continue;
							}
						}
					} else {
						System.out.println("不好意思！数据库连接已关闭了！");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} 
		}.start();
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
	private javax.swing.JButton jButtonStart;
	private javax.swing.JButton jButtonTable;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelTo;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JLayeredPane jLayeredPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField jTextFieldBegin;
	private javax.swing.JTextField jTextFieldEnd;
	private java.awt.List list1;
	private java.awt.List list2;
	// End of variables declaration//GEN-END:variables

}