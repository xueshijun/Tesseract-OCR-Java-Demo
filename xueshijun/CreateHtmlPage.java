package com.xueshijun;
 
import java.util.*;
import java.io.*;

public class CreateHtmlPage  {
 public static void main(String[] args) {
  try {
   String title = "Make Html";
   String content = "小样，还搞不定你?";
   String editer = "秋水";
   //模板路径
   String filePath =  "H:\\奥瑞思\\jsoup\\JsoupExample\\lib\\leon.html";
   System.out.print(filePath);
   String templateContent = "";
   FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
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
     .replaceAll("###author###", editer);// 替换掉模板中相应的地方
   System.out.print(templateContent);

   // 根据时间得文件名
   Calendar calendar = Calendar.getInstance();
   String filename = String.valueOf(calendar.getTimeInMillis())
     + ".html";
   filename = "H:\\奥瑞思\\jsoup\\JsoupExample\\lib\\" + filename;// 生成的html文件保存路径。
   FileOutputStream fileoutputstream = new FileOutputStream(filename);// 建立文件输出流
   System.out.print("文件输出路径:");
   System.out.print(filename);
   byte tag_bytes[] = templateContent.getBytes();
   fileoutputstream.write(tag_bytes);
   fileoutputstream.close();
  } catch (Exception e) {
   System.out.print(e.toString());
  }
 }
}


 

