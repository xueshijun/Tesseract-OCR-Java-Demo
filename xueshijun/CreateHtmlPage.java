package com.xueshijun;
 
import java.util.*;
import java.io.*;

public class CreateHtmlPage  {
 public static void main(String[] args) {
  try {
   String title = "Make Html";
   String content = "С�������㲻����?";
   String editer = "��ˮ";
   //ģ��·��
   String filePath =  "H:\\����˼\\jsoup\\JsoupExample\\lib\\leon.html";
   System.out.print(filePath);
   String templateContent = "";
   FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�
   int lenght = fileinputstream.available();
   byte bytes[] = new byte[lenght];
   fileinputstream.read(bytes);
   fileinputstream.close();
   templateContent = new String(bytes);
   System.out.print(templateContent);
   templateContent = templateContent.replaceAll("###title###", title);
   templateContent = templateContent.replaceAll("###content###",
     content);
   templateContent = templateContent
     .replaceAll("###author###", editer);// �滻��ģ������Ӧ�ĵط�
   System.out.print(templateContent);

   // ����ʱ����ļ���
   Calendar calendar = Calendar.getInstance();
   String filename = String.valueOf(calendar.getTimeInMillis())
     + ".html";
   filename = "H:\\����˼\\jsoup\\JsoupExample\\lib\\" + filename;// ���ɵ�html�ļ�����·����
   FileOutputStream fileoutputstream = new FileOutputStream(filename);// �����ļ������
   System.out.print("�ļ����·��:");
   System.out.print(filename);
   byte tag_bytes[] = templateContent.getBytes();
   fileoutputstream.write(tag_bytes);
   fileoutputstream.close();
  } catch (Exception e) {
   System.out.print(e.toString());
  }
 }
}


 

