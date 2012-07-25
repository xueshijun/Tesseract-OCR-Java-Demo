package test;


import java.io.IOException;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

 

public class Jsoup01 {

 

public static void main(String[] args) {

 

String getUrl = "http://www.epzw.com/files/article/topmonthvisit/0/1.htm"; //指定网页地址

String g1 = ".grid > tbody > tr > td > strong > a"; //获取<ahref="***">***</a>规则

String g2 = "abs:href"; //获取URL的规则

try {

Document doc = Jsoup.connect(getUrl).timeout(60000).get();

Elements links = doc.select(g1); //获取<a href="***">***</a>列表

for (Element link : links) {

String bookURL = link.attr(g2); //获取完整URL地址

String bookTitle=link.text(); //获取URL标题

System.out.println(bookURL+"   "+bookTitle);

}

} catch (IOException e) {

e.printStackTrace();

}

}

 

}


