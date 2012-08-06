package com.image;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveInternetImage { 
	public SaveInternetImage(){ }

  	public static boolean saveUrlAs(String fileUrl, String savePath)/*fileUrl������Դ��ַ*/
  	{ 
	  try
	  {
		  URL url = new URL(fileUrl);/*��������Դ��ַ����,����ֵ��url*/
		  /*��Ϊ��ϵ���������Դ�Ĺ̶���ʽ�÷����Ա�����in�������url��ȡ������Դ��������*/
		  HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		  DataInputStream in = new DataInputStream(connection.getInputStream());
		  /*�˴�Ҳ����BufferedInputStream��BufferedOutputStream*/
		  DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
		  /*������savePath��������ȡ��ͼƬ�Ĵ洢�ڱ��ص�ַ��ֵ��out�������ָ���ĵ�ַ*/
		  byte[] buffer = new byte[8192];
		  int count = 0;
		  while ((count = in.read(buffer)) > 0)/*�����������ֽڵ���ʽ��ȡ��д��buffer��*/
		  {
			  out.write(buffer, 0, count);
		  }
		  if(buffer.length==0){
			  return false;
		  }
		  out.close();/*��������Ϊ�ر�����������Լ�������Դ�Ĺ̶���ʽ*/
		  in.close();
		  connection.disconnect();
		  return true;/*������Դ��ȡ���洢���سɹ�����true*/ 
	  }
	  catch (Exception e)
	  {
		  System.out.println(e + fileUrl + savePath);
		  return false;
	  }
  }

  	public static void main(String[] args)
  	{
  		SaveInternetImage pic = new SaveInternetImage();/*����ʵ��*/
  		  String photoUrl ="http://price.360buyimg.com/gp280127,3.png";
  		  /*photoUrl.substring(photoUrl.lastIndexOf("/")�ķ������������һ������Ϊ
  		  * ��/����photoUrl�����е������ַ����������������*/
  		  String fileName = photoUrl.substring(photoUrl.lastIndexOf("/")+1);
  		  String filePath = "E:";
  		  /*���ú��������ҽ��д���*/ 
  		  if(pic.saveUrlAs(photoUrl, filePath + fileName)){
	  		  System.out.println("Run ok!\n Get URL file " );
	  		  System.out.println(filePath);
	  		  System.out.println(fileName);
  		  }
  	} 
}