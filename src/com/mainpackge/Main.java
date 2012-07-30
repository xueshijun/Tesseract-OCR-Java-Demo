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

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 800));

		jTextFieldBegin.setText("100001");
		jTextFieldBegin.setBounds(150, 130, 110, 21);
		jLayeredPane1.add(jTextFieldBegin,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTextFieldEnd.setText("700001");
		jTextFieldEnd.setBounds(550, 130, 90, 21);
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

		jTabbedPane1.addTab("tab1", jLayeredPane1);

		add(jTabbedPane1, java.awt.BorderLayout.NORTH);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	Connection conn = JingDongDB.mysql.getConnetction("test");

	/**
	 * �������ݿ�
	 * 
	 */
	private void jButtonDataBaseActionPerformed(java.awt.event.ActionEvent evt) {
		this.list1.add("�������ݿ�....");
		if (JingDongDB.CreateDataBase(conn, JingDongDB.STRCREATE_DATABASE) == true) {
			//			System.out.println("�������ݿ�mystore�ɹ�");
			this.list1.add("�������ݿ�mystore�ɹ�");

		} else {
			//			System.out.println("�������ݿ�mystore");
			this.list1.add("�������ݿ�mystore");
		}
	}

	/**
	 * ������
	 */
	private void jButtonTableActionPerformed(java.awt.event.ActionEvent evt) {
		conn = JingDongDB.mysql.getConnetction("mystore");
		this.list1.add("������");
		if (JingDongDB.CreateTable(conn, JingDongDB.STRCREATE_PRODUCT_TABLE) == true) {
			this.list1.add("����product�ɹ�");
		} else {
			this.list1.add("����productʧ��");
		}
		if (JingDongDB.CreateTable(conn, JingDongDB.STRCREATE_BAD_TABLE) == true) {
			this.list1.add("����prourl�ɹ�");
		} else {
			this.list1.add("����prourlʧ��");
		}
	}

	/**
	 * �����
	 */
	@SuppressWarnings("deprecation")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		JingDongItemPackage jingDongItemPackage = null;
		conn = JingDongDB.mysql.getConnetction("mystore");
		JingDong.CONNECT_Time_OUT = 500;

		try {
			if (!conn.isClosed()) {
				this.list1.add("�������ݿ�����....");

				int begin = Integer.valueOf(this.jTextFieldBegin.getText());
				int end = Integer.valueOf(this.jTextFieldEnd.getText());
			
				try{
					ResultSet rs = JingDongDB.mysql.getRes(conn,"select * from " + JingDongDB.STRTABLE_NAME);
					while (rs.next()) {
						begin=Integer.valueOf(rs.getString(6));
						
					}
				}catch(Exception ex){
					begin = Integer.valueOf(this.jTextFieldBegin.getText());//100001-700001
				}
				this.jTextFieldBegin.setText(String.valueOf(begin));
				int count = begin;
				while (count < end) {
					String url = "http://www.360buy.com/product/" + count
							+ ".html";
					JingDong jingDong = new JingDong(url);
					Document doc = jingDong.initPage();
					if (doc == null || doc.title() == "��ҳ�޷���ʾ") {//���Ӳ���
						JingDongDB.mysql.executeSql(conn, "insert into "
								+ JingDongDB.STRTABLE_BAD_NAME + " values ('"
								+ url + "')");
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
					jingDongItemPackage = new JingDongItemPackage(jingDong
							.getPageUrl(), jingDong.getPageTitle(), jingDong
							.getPageKeyWords(), jingDong.getPageDescription(),
							jingDong.getItemId(), jingDong.getItemTitle(),
							jingDong.getItemName(), jingDong.getMarketPrice(),
							"", jingDong.getJingDongPrice(), jingDong
									.getItemMadeArea(), jingDong
									.getItemOnShelfDate(), jingDong
									.getItemCompany(),
							jingDong.getItemWeight(), jingDong
									.getItemTitleAdvertiseWord(), jingDong
									.getItemSalesPromotionInfo(), jingDong
									.getItemLargessInfo(), strItemType);
					try {
						if (JingDongDB.InsertItem(conn, jingDongItemPackage)) {
							System.out.println("�ɹ��������ݣ�");
							this.list1.add("�ɹ��������ݣ�");
						} else {
							this.list1.add("��������ʧ�ܣ�");
							System.out.println("��������ʧ�ܣ�");
						}
					} catch (Exception ex) {
						continue;
					}
					ResultSet rs= JingDongDB.mysql.getRes(conn,
							"select * from " + JingDongDB.STRTABLE_NAME);
					while (rs.next()) {
						this.list1.clear();
						this.list1.add(String.valueOf(count) + " +++ "
								+ rs.getString(6) + " +++ " + rs.getString(2));
					}

					count++;
				}
			} else {
				System.out.println("������˼�����ݿ������ѹر��ˣ�");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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