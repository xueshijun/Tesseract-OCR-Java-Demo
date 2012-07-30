package test;


import java.io.IOException;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

 

public class Jsoup01 {

 

public  void mymain(String[] args) {

 

String getUrl = "http://www.epzw.com/files/article/topmonthvisit/0/1.htm"; //ָ����ҳ��ַ

String g1 = ".grid > tbody > tr > td > strong > a"; //��ȡ<ahref="***">***</a>����

String g2 = "abs:href"; //��ȡURL�Ĺ���

try {

Document doc = Jsoup.connect(getUrl).timeout(60000).get();

Elements links = doc.select(g1); //��ȡ<a href="***">***</a>�б�

for (Element link : links) {

String bookURL = link.attr(g2); //��ȡ����URL��ַ

String bookTitle=link.text(); //��ȡURL����

System.out.println(bookURL+"   "+bookTitle);

}

} catch (IOException e) {

e.printStackTrace();

}

}

 

}


