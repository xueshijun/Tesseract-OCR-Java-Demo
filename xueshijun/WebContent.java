package com.xueshijun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author ����2007.1.18 ץȡ�Ż�֪ʶ�õ����±��⼰���ݣ����ԣ� �ֶ�������ַץȡ���ɽ�һ���Զ�ץȡ����֪ʶ�õ�ȫ������
*
*/
public class WebContent
{
/**
* ��ȡһ����ҳȫ������
*/
public String getOneHtml(final String htmlurl) throws IOException
{
URL url;
String temp;
final StringBuffer sb = new StringBuffer();
try
{
   url = new URL(htmlurl);
   final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));// ��ȡ��ҳȫ������
   while ((temp = in.readLine()) != null)
   {
    sb.append(temp);
   }
   in.close();
}
catch (final MalformedURLException me)
{
   System.out.println("�������URL��ʽ�����⣡����ϸ����");
   me.getMessage();
   throw me;
}
catch (final IOException e)
{
   e.printStackTrace();
   throw e;
}
return sb.toString();
}

/**
*
* @param s
* @return �����ҳ����
*/
public String getTitle(final String s)
{
String regex;
String title = "";
final List<String> list = new ArrayList<String>();
regex = "<title>.*?</title>";
final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
final Matcher ma = pa.matcher(s);
while (ma.find())
{
   list.add(ma.group());
}
for (int i = 0; i < list.size(); i++)
{
   title = title + list.get(i);
}
return outTag(title);
}

/**
*
* @param s
* @return �������
*/
public List<String> getLink(final String s)
{
String regex;
final List<String> list = new ArrayList<String>();
regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
final Matcher ma = pa.matcher(s);
while (ma.find())
{
   list.add(ma.group());
}
return list;
}

/**
*
* @param s
* @return ��ýű�����
*/
public List<String> getScript(final String s)
{
String regex;
final List<String> list = new ArrayList<String>();
regex = "<script.*?</script>";
final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
final Matcher ma = pa.matcher(s);
while (ma.find())
{
   list.add(ma.group());
}
return list;
}

/**
*
* @param s
* @return ���CSS
*/
public List<String> getCSS(final String s)
{
String regex;
final List<String> list = new ArrayList<String>();
regex = "<style.*?</style>";
final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
final Matcher ma = pa.matcher(s);
while (ma.find())
{
   list.add(ma.group());
}
return list;
}

/**
*
* @param s
* @return ȥ�����
*/
public String outTag(final String s)
{
return s.replaceAll("<.*?>", "");
}

/**
*
* @param s
* @return ��ȡ�Ż�֪ʶ�����±��⼰����
*/
public HashMap<String, String> getFromYahoo(final String s)
{
final HashMap<String, String> hm = new HashMap<String, String>();
final StringBuffer sb = new StringBuffer();
String html = "";
System.out.println("\n------------------��ʼ��ȡ��ҳ(" + s + ")--------------------");
try
{
   html = getOneHtml(s);
}
catch (final Exception e)
{
   e.getMessage();
}
// System.out.println(html);
System.out.println("------------------��ȡ��ҳ(" + s + ")����--------------------\n");
System.out.println("------------------����(" + s + ")�������--------------------\n");
String title = outTag(getTitle(html));
title = title.replaceAll("_�Ż�֪ʶ��", "");
// Pattern pa=Pattern.compile("<div
// class=\"original\">(.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)</div>",Pattern.DOTALL);
final Pattern pa = Pattern.compile("<div class=\"original\">(.*?)</p></div>", Pattern.DOTALL);
final Matcher ma = pa.matcher(html);
while (ma.find())
{
   sb.append(ma.group());
}
String temp = sb.toString();
temp = temp.replaceAll("(<br>)+?", "\n");// ת������
temp = temp.replaceAll("<p><em>.*?</em></p>", "");// ȥͼƬע��
hm.put("title", title);
hm.put("original", outTag(temp));
return hm;

}

/**
*
* @param args
*            ����һ����ҳ������Ż�֪ʶ��
*/
public static void main(final String args[])
{
String url = "";
final List<String> list = new ArrayList<String>();
System.out.print("����URL��һ��һ����������������� go ����ʼ����:   \n");
/*
   * http://ks.cn.yahoo.com/question/1307121201133.html
   * http://ks.cn.yahoo.com/question/1307121101907.html
   * http://ks.cn.yahoo.com/question/1307121101907_2.html
   * http://ks.cn.yahoo.com/question/1307121101907_3.html
   * http://ks.cn.yahoo.com/question/1307121101907_4.html
   * http://ks.cn.yahoo.com/question/1307121101907_5.html
   * http://ks.cn.yahoo.com/question/1307121101907_6.html
   * http://ks.cn.yahoo.com/question/1307121101907_7.html
   * http://ks.cn.yahoo.com/question/1307121101907_8.html
   */
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
try
{
   while (!(url = br.readLine()).equals("go"))
   {
    list.add(url);
   }
}
catch (final Exception e)
{
   e.getMessage();
}
final WebContent wc = new WebContent();
HashMap<String, String> hm = new HashMap<String, String>();
for (int i = 0; i < list.size(); i++)
{
   hm = wc.getFromYahoo(list.get(i));
   System.out.println("���⣺ " + hm.get("title"));
   System.out.println("���ݣ� \n" + hm.get("original"));
}
/*
   * String htmlurl[] = {
   * "http://ks.cn.yahoo.com/question/1307121201133.html",
   * "http://ks.cn.yahoo.com/question/1307121101907.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_2.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_3.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_4.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_5.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_6.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_7.html",
   * "http://ks.cn.yahoo.com/question/1307121101907_8.html" }; WebContent
   * wc = new WebContent(); HashMap<String, String> hm = new HashMap<String,
   * String>(); for (int i = 0; i < htmlurl.length; i++) { hm =
   * wc.getFromYahoo(htmlurl[i]); System.out.println("���⣺ " +
   * hm.get("title")); System.out.println("���ݣ� \n" + hm.get("original")); }
   */
/*
   * String html=""; String link=""; String sscript=""; String content="";
   * System.out.println(htmlurl+" ��ʼ��ȡ��ҳ���ݣ�");
   * html=wc.getOneHtml(htmlurl); System.out.println(htmlurl+"
   * ��ȡ��Ͽ�ʼ��������"); html=html.replaceAll("(<script.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)(</script>)","
   * ");//ȥ���ű� html=html.replaceAll("(<style.*?)((\r\n)*)(.*?)((\r\n)*)(.*?)(</style>)","
   * ");//ȥ��CSS html=html.replaceAll("<title>.*?</title>"," ");//��ȥҳ�����
   * html=html.replaceAll("<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>","
   * ");//ȥ������ html=html.replaceAll("(\\s){2,}?"," ");//��ȥ����ո�
   * html=wc.outTag(html);//������ System.out.println(html);
   */

/*
   * String s[]=html.split(" +"); for(int i=0;i<s.length;i++){
   * content=(content.length()>s[i].length())?content:s[i]; }
   * System.out.println(content);
   */

// System.out.println(htmlurl+"��ҳ���ݽ���");
/*
   * System.out.println(htmlurl+"��ҳ�ű���ʼ��"); List
   * script=wc.getScript(html); for(int i=0;i<script.size();i++){
   * System.out.println(script.get(i)); }
   * System.out.println(htmlurl+"��ҳ�ű�������");
   *
   * System.out.println(htmlurl+"CSS��ʼ��"); List css=wc.getCSS(html);
   * for(int i=0;i<css.size();i++){ System.out.println(css.get(i)); }
   * System.out.println(htmlurl+"CSS������");
   *
   * System.out.println(htmlurl+"ȫ���������ݿ�ʼ��"); List list=wc.getLink(html);
   * for(int i=0;i<list.size();i++){ link=list.get(i).toString(); }
   * System.out.println(htmlurl+"ȫ���������ݽ�����");
   *
   * System.out.println("����"); System.out.println(wc.outTag(html));
   */
}
}

