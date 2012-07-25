package com.xueshijun.getcssjsimg;


/**
 * 使用 JSOUP 网站抓取CSS,JS,IMG信息       一直想找点东西写进博客,可每天都掉进了while(true)语句,做着重复着工作,生活平淡无奇,

 

      昨天在淘福上买了欣博阅G10电子书,今天到货了,可我那时在公司,没收到,这厮从去年我就一直惦记着它,这个月一发工资就下手了,1280大洋，可怜的我这个月要勒紧腰带.之前说带女朋友去吃香辣蟹估计要搁浅了,哎,女朋友,你受苦了....

 

      之前自己从网上下载的模板,再从后台补充数据在前台进行展示的企业模板都N久没去更新了,写来写去都是CTRL + C TO CTRL+V,模块也就这么些,可电脑中还有一大堆模板没填充,弄完后再整几个旅游网站上去...这只是计划,亲，你懂的。

 

      网站的模板都是从国外的免费模板网站下载下来的，里面有很多图片啊,CSS啊,JS啊都没用到,我之前都没把它们过滤就直接上传到服务器上,今天有丝心情想整理一下,就用JSOUP从网站上抓取了所有的JS,CSS,IMG进行按它的目录路径打包,这样就可以去掉那些没用到的.

 

   好了,这周计划调整一下网站界面,

  1、在页面加入留言版块，偶尔有路过的达人,还可以指点我一下.

  2、在图片增加点击排行,显示创建模板网站的时间,这样以后自己明白.

  3、在后台写一个自动打包,导出数据库,改路由配置,希望达到点一下按钮,指定的模板就能打包且能工作。

   4、我再想想....

 

   好了,说了那么多废话,来吧,将JSOUP代码粘贴上来,度娘,你啥时给我们加个代码加亮显示啊，不然看起来黑吃力的...over




目录结构
<<<<<<<<OMG
	<<<<<<css
	<<<<<<import
		<<<img
	<<<<<<js
	<<<<<<WEB
		<<<web1
		<<<web2
 


 * **/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 读取指定页面的JS,CSS,IMG保存起来 2012-6-7
 * 
 * @author Administrator
 */
public class GanseJsoup {
 private static String BASE = "http://localhost/myweb/";

 public static void main(String[] args) throws IOException {
  final GanseJsoup gj = new GanseJsoup();
  // 取9个页面
  List<String> pagesUrl = gj.get_Pages(9);
  ExecutorService pool = Executors.newCachedThreadPool();
  for (final String url : pagesUrl) {
   pool.execute(new Runnable() {
    public void run() {
     try {
      gj.readUrlFromList(gj.get_urls(url));
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   });
  }
 }

 // 读取指定的页面
 private List<String> get_Pages(int num) {
  List<String> pagesLst = new ArrayList<String>();
  while (num > 0) {
   pagesLst.add(getPageUrl(num));
   num--;
  }
  return pagesLst;
 }

 // 页面完整地址
 private String getPageUrl(int indexPage) {
  return "http://localhost/myweb/template/web" + indexPage + "/index";
 }

 // 取指定标签下的内容,我这里的菜单都定义成了dir=menu
 public List<String> get_urls(String url) throws IOException {
  List<String> urlLst = new ArrayList<String>();
  Document doc = Jsoup.connect(url).timeout(50*1000).get();
  Elements links = doc.select(" div[dir=menu]>ul>li>a ");
  for (Element src : links) {
   urlLst.add(src.attr("href"));
  }
  return urlLst;
 }

 // 从页面中读取指定 的东东
 public void readUrlFromList(List<String> urls) throws IOException {
  // ExecutorService pool = Executors.newFixedThreadPool(15);
  for (final String url : urls) {
   final Set<String> set = readUrlPage(url);
   PrintUtils.print("______________-开始:" + url);
   // pool.execute(new Runnable() {
   // public void run() {
   try {
    writeImgToDriver(set);// 写入磁盘文件
   } catch (IOException e) {
    e.printStackTrace();
   }
   // }
   // });
   PrintUtils.print("______________-开始:" + url);
  }

 }

 // 读取页面中的图片(img),css(link),js(src)
 private Set<String> readUrlPage(final String url) throws IOException {
  Document doc = null;
  doc = Jsoup.connect(url).timeout(50*1000).get();
  Elements objs = doc.select("img");
  // 避免重复数据
  Set<String> st = new HashSet<String>();
  for (Element img : objs) {
   st.add(img.attr("src"));
  }
  objs = doc.select("link");
  for (Element img : objs) {
   st.add(img.attr("href"));
  }
  objs = doc.select("script");
  for (Element img : objs) {
   st.add(img.attr("src"));
  }
  return st;
 }

 // 将数据进行下载
 public void writeImgToDriver(Set<String> set) throws IOException {
  for (Iterator<String> ite = set.iterator(); ite.hasNext();) {
   beginDown(ite.next());
  }
 }

 public void beginDown(String imgurl) throws IOException {
  if (imgurl != null && imgurl != "") {
   URL u = new URL(imgurl);// 径
//   File file = makeFile(imgurl);
//   PrintUtils.print(makeFile(imgurl));
//   String fullName = file.getAbsolutePath();// + File.separatorChar+
              // getImgName(imgurl);
   String fullName=makeFile(imgurl);
   byte[] buffer = new byte[1024 * 8];
   int read;
   int ava = 0;
   long start = System.currentTimeMillis();
   BufferedInputStream bin = new BufferedInputStream(u.openStream());
   BufferedOutputStream bout = new BufferedOutputStream(
     new FileOutputStream(fullName));
   while ((read = bin.read(buffer)) > -1) {
    bout.write(buffer, 0, read);
    ava += read;
    long speed = ava / (System.currentTimeMillis() - start);
    PrintUtils.print("Download: " + ava + " byte(s)"
      + "    avg speed: " + speed + "  (kb/s)");
   }
   bout.flush();
   bout.close();
   System.out.println("Done. size:" + ava + " byte(s) and Path  = "
     + fullName);
  }
 }

 private String makeFile(String url) {
  String fs = url.replace(BASE, "").replace("//", "/");// 目录
  int last = fs.lastIndexOf("/");
  String name = fs.substring(last + 1);// 文件名
  fs = fs.substring(0, last);
  mkdir(fs);
  return new File("C:\\OMG\\" +fs+"\\" + name).getPath();
 }

 private void mkdir(String str) {
  File file = new File("C:\\OMG\\" + str);
  if (!file.exists()) {
   file.mkdirs();
  }
 }
}

