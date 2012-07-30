package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlparse.threesixzerobuy.JingDongItem.ItemImg;  
import com.htmlparse.yihaodian.YiHaoDianItem.ItemType;

import com.sun.jndi.toolkit.url.Uri;

public class Interesting {

	public void mymain(String[] args) throws IOException  {
		System.out.println("=======BEGIN======");  

		String getUrl = "http://www.yihaodian.com/product/16082_1"; //指定网页地址
		Document doc =  Jsoup.connect(getUrl).userAgent("") // 设置User-Agent   
		                .timeout(3000) // 设置连接超时时间   
		                .get();
		// "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)"
		//"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7";
	
			Element link=doc.select(".wrap>.produce.clearfix").first(); 
			System.out.println(link.toString()); 
//			}
	}
}
 
