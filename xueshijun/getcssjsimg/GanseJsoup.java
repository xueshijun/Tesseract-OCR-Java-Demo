package com.xueshijun.getcssjsimg;


/**
 * ʹ�� JSOUP ��վץȡCSS,JS,IMG��Ϣ       һֱ���ҵ㶫��д������,��ÿ�춼������while(true)���,�����ظ��Ź���,����ƽ������,

 

      �������Ը�������������G10������,���쵽����,������ʱ�ڹ�˾,û�յ�,���˴�ȥ���Ҿ�һֱ�������,�����һ�����ʾ�������,1280���󣬿������������Ҫ�ս�����.֮ǰ˵��Ů����ȥ������з����Ҫ��ǳ��,��,Ů����,���ܿ���....

 

      ֮ǰ�Լ����������ص�ģ��,�ٴӺ�̨����������ǰ̨����չʾ����ҵģ�嶼N��ûȥ������,д��дȥ����CTRL + C TO CTRL+V,ģ��Ҳ����ôЩ,�ɵ����л���һ���ģ��û���,Ū�����������������վ��ȥ...��ֻ�Ǽƻ�,�ף��㶮�ġ�

 

      ��վ��ģ�嶼�Ǵӹ�������ģ����վ���������ģ������кܶ�ͼƬ��,CSS��,JS����û�õ�,��֮ǰ��û�����ǹ��˾�ֱ���ϴ�����������,������˿����������һ��,����JSOUP����վ��ץȡ�����е�JS,CSS,IMG���а�����Ŀ¼·�����,�����Ϳ���ȥ����Щû�õ���.

 

   ����,���ܼƻ�����һ����վ����,

  1����ҳ��������԰�飬ż����·���Ĵ���,������ָ����һ��.

  2����ͼƬ���ӵ������,��ʾ����ģ����վ��ʱ��,�����Ժ��Լ�����.

  3���ں�̨дһ���Զ����,�������ݿ�,��·������,ϣ���ﵽ��һ�°�ť,ָ����ģ����ܴ�����ܹ�����

   4����������....

 

   ����,˵����ô��ϻ�,����,��JSOUP����ճ������,����,��ɶʱ�����ǼӸ����������ʾ������Ȼ�������ڳ�����...over




Ŀ¼�ṹ
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
 * ��ȡָ��ҳ���JS,CSS,IMG�������� 2012-6-7
 * 
 * @author Administrator
 */
public class GanseJsoup {
 private static String BASE = "http://localhost/myweb/";

 public static void main(String[] args) throws IOException {
  final GanseJsoup gj = new GanseJsoup();
  // ȡ9��ҳ��
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

 // ��ȡָ����ҳ��
 private List<String> get_Pages(int num) {
  List<String> pagesLst = new ArrayList<String>();
  while (num > 0) {
   pagesLst.add(getPageUrl(num));
   num--;
  }
  return pagesLst;
 }

 // ҳ��������ַ
 private String getPageUrl(int indexPage) {
  return "http://localhost/myweb/template/web" + indexPage + "/index";
 }

 // ȡָ����ǩ�µ�����,������Ĳ˵����������dir=menu
 public List<String> get_urls(String url) throws IOException {
  List<String> urlLst = new ArrayList<String>();
  Document doc = Jsoup.connect(url).timeout(50*1000).get();
  Elements links = doc.select(" div[dir=menu]>ul>li>a ");
  for (Element src : links) {
   urlLst.add(src.attr("href"));
  }
  return urlLst;
 }

 // ��ҳ���ж�ȡָ�� �Ķ���
 public void readUrlFromList(List<String> urls) throws IOException {
  // ExecutorService pool = Executors.newFixedThreadPool(15);
  for (final String url : urls) {
   final Set<String> set = readUrlPage(url);
   PrintUtils.print("______________-��ʼ:" + url);
   // pool.execute(new Runnable() {
   // public void run() {
   try {
    writeImgToDriver(set);// д������ļ�
   } catch (IOException e) {
    e.printStackTrace();
   }
   // }
   // });
   PrintUtils.print("______________-��ʼ:" + url);
  }

 }

 // ��ȡҳ���е�ͼƬ(img),css(link),js(src)
 private Set<String> readUrlPage(final String url) throws IOException {
  Document doc = null;
  doc = Jsoup.connect(url).timeout(50*1000).get();
  Elements objs = doc.select("img");
  // �����ظ�����
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

 // �����ݽ�������
 public void writeImgToDriver(Set<String> set) throws IOException {
  for (Iterator<String> ite = set.iterator(); ite.hasNext();) {
   beginDown(ite.next());
  }
 }

 public void beginDown(String imgurl) throws IOException {
  if (imgurl != null && imgurl != "") {
   URL u = new URL(imgurl);// ��
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
  String fs = url.replace(BASE, "").replace("//", "/");// Ŀ¼
  int last = fs.lastIndexOf("/");
  String name = fs.substring(last + 1);// �ļ���
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

