package com.xueshijun.createhtml;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.ws.util.StringUtils;

public class CreateHtmlJsoup {
	public static void main(String[] args) {
		makeHeatMapHtml();
	}
/**
 * 
 * .
实现功能： 
1.根据URL抓取网页 
2.添加(修改)网页内容 
3.下载与网页相关的js,css,img,flash,iframe等 
4.修改js,css,img,flash,iframe的路径 
5.添加注释<!-- saved from url=("+new DecimalFormat("0000").format(url.length())+")"+url+" -->，网页不会弹出限制运行脚本提示 
6.按照编码方式生成本地页面 

工具类： 


 * **/
	
	
	
	
	
	
	
	
	/**
	 * 生成HTML到本地的临时目录中
	 */
	public static void makeHeatMapHtml() {
		System.out.println("下载页面开始......");
		long start = System.currentTimeMillis();
		String url = "";//
		String filepath = "";// 
		String filename = "";// 
		String ecoding = "";// 
		
		url = "http://finance.people.com.cn/GB/index.html";
		filepath = "F:\\people.temp\\";
		filename = "index.html";// 文件名称
		ecoding = "gbk";// 编码格式
		if (copyURLToHtml(url, filepath, filename, ecoding)) {// 如果页面访问成功，下载URL网页到本地临时目录
			copyDirToDir(filepath,StringUtils.replace(filepath, ".temp",""));// 把临时目录中的文件复制到本地
			delTempFile(filepath);// 删除临时目录
		} 

		System.out.println("下载页面完成 ,用时"
				+ (System.currentTimeMillis() - start) / 1000 / 60 + "分钟");
	}

	/**
	 * 文件夹copy
	 * 
	 * @param filepath
	 */
	protected static void copyDirToDir(String orgPath, String destPath) {
		File srcDir = new File(orgPath);
		File destDir = new File(destPath);
		if (srcDir.exists()) {
			try {
				FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
				System.out.println("复制临时目录失败");
			}
		}
	}

	/**
	 * 删除临时目录
	 * 
	 * @param filepath
	 */
	protected static void delTempFile(String filepath) {
		File tempFile = new File(filepath);
		try {
				FileUtils.deleteDirectory(tempFile);
		} catch (IOException e) {
			System.out.println("删除临时目录失败");
		}
	}

	/**
	 * 生成本地HTML
	 * 
	 * @param url
	 *            链接地址
	 * @param filepath
	 *            生成文件路径
	 * @param filename
	 *            生成文件名称
	 * @param ecoding
	 *            编码方式
	 * @return
	 */
	private static boolean copyURLToHtml(String url, String filepath, String filename,
			String ecoding) {
		boolean flag = false;
		Document doc;
		doc = copyURLToHtmlDoc(url, filename, filepath, ecoding, 5 * 60000);// 根据Url抓取页面放入document对象
		if (null == doc) {
			System.out.println("下载页面" + url + "失败......");
			return flag;
		}
		String content = "<script type='text/javascript' src='../../render.js' charset='utf-8' ></script>"
			+ "<script type='text/javascript'>"
			+ "_RunTerrenStyle();"
			+ "</script>";
		doc = appendLink(doc, "body", content);// 在head标签中加载js
		flag = makeHtmlByDoc(filepath, filename, doc, ecoding);// 生成html文件
		System.out.println("下载页面" + url + "成功......编码： " + ecoding);
		return flag;
	}

	/**
	 * 根据url抓取网页中找到css样式,js,img链接地址及文件名
	 * 
	 * @param doc
	 *            jsoup.nodes.Document
	 * @return List<StyleSheet>
	 */
	public static List<HtmlFileLink> getHtmlFileLink(Document doc) {
		List<HtmlFileLink> HtmlFileLinks = new ArrayList<HtmlFileLink>();
		String postfix = "";// 文件后缀名
		int index = 0; // 用于文件名
		Elements importcss = doc.select("link[href]");// 找到document中带有link标签的元素
		for (Element link : importcss) {
			postfix = "css";
			if (link.attr("rel").equals("stylesheet")) {// 如果rel属性为HtmlFileLink
				String href = link.attr("abs:href");// 得到css样式的href的绝对路径
				// 如http://news.baidu.com/resource/css/search.css
				String filename = postfix + index + "." + postfix;//
				HtmlFileLinks.add(new HtmlFileLink(href, filename, postfix));
				index++;
			}
		}
		Elements media = doc.select("[src]");
		for (Element link : media) {
			if (link.tagName().equals("img")) {
				String src = link.attr("abs:src");
				postfix = getPostfix(src);
				String filename = postfix + index + "." + postfix;
				HtmlFileLinks.add(new HtmlFileLink(src, filename, postfix));
				index++;
			}
			if (link.tagName().equals("input")) {
				if (link.attr("type").equals("Image")) {
					String src = link.attr("abs:src");
					postfix = getPostfix(src);
					String filename = postfix + index + "." + postfix;
					HtmlFileLinks.add(new HtmlFileLink(src, filename, postfix));
					index++;
				}
			}
			if (link.tagName().equals("javascript")
					|| link.tagName().equals("script")) {
				String src = link.attr("abs:src");
				postfix = getPostfix(src);
				String filename = postfix + index + "." + postfix;
				HtmlFileLinks.add(new HtmlFileLink(src, filename, postfix));
				index++;
			}
			if (link.tagName().equals("iframe")) {
				String src = link.attr("abs:src");
				postfix = getPostfix(src);
				String filename = postfix + index + ".html";
				HtmlFileLinks.add(new HtmlFileLink(src, filename, postfix));
				index++;
			}
			if (link.tagName().equals("embed")) {
				String src = link.attr("abs:src");
				postfix = getPostfix(src);
				String filename = postfix + index + "." + postfix;
				HtmlFileLinks.add(new HtmlFileLink(src, filename, postfix));
				index++;
			}
		}
		return HtmlFileLinks;
	}

	/**
	 * 去掉HTML中的A标签链接地址
	 * 
	 * @param doc
	 */
	public static void removeHref(Document doc) {
		doc.select("a").removeAttr("href");// 去掉a的链接
		doc.select("img").removeAttr("onclick"); // 去掉图片onclick事件
		doc.select("input[type=submit]").attr("type", "button");// submit按钮改为普通按钮
		doc.select("input[type=button]").removeAttr("onclick"); // 去掉按钮的onclick事件
		doc.select("area").removeAttr("href");// 去掉area的链接
	}

	/**
	 * 修改抓取网页中的css样式表链接地址,改为相对路径
	 * 
	 * @param doc
	 *            jsoup.nodes.Document
	 * @param list
	 *            List<HtmlFileLink>
	 */
	public static void renameHref(Document doc) {
		List<HtmlFileLink> list = getHtmlFileLink(doc);
		if (list.size() == 0) {
			return;
		}
		Elements imports = doc.select("link[href]");
		for (Element link : imports) {
			for (HtmlFileLink ss : list) {
				if (link.attr("abs:href").equals(ss.getHref())) {
					link.attr("href", ss.getFilename());
				}
			}
		}
		Elements media = doc.select("[src]");
		for (Element link : media) {
			for (HtmlFileLink ss : list) {
				if (link.attr("abs:src").equals(ss.getHref())) {
					link.attr("src", ss.getFilename());
				}
			}
		}
	}

	/**
	 * 在网页的元素中添加对象
	 * 
	 * @param doc
	 * @param tag
	 *            标签
	 * @param content
	 *            内容
	 * @return
	 */
	public static Document appendLink(Document doc, String tag, String content) {
		Element element = doc.select(tag).first();
		element.append(content);
		return doc;
	}

	/**
	 * 根据u网页中css、js、img链接地址及文件名生成本地文件
	 * 
	 * @param doc
	 * @param filepath
	 * @param timeout
	 * @throws IOException
	 */
	public static void makeHtmlLinkFile(Document doc, String filepath,
			int timeout) {
		File file = new File(filepath);
		if (file.exists()) {
			try {
				FileUtils.cleanDirectory(new File(filepath));
			} catch (IOException e) {
				System.out.println("清空文件夹 :" + filepath + " 错误");
			}
		}
		List<HtmlFileLink> list = getHtmlFileLink(doc);// 根据url抓取网页中找到css样式链接地址及文件名
		for (HtmlFileLink ss : list) {
			/**
			 * jsoup不支持访问js文件所以改用apache commons的fileUtil生成js和css文件
			 */
			try {
				FileUtils.copyURLToFile(new URL(ss.getHref()), new File(
						filepath + ss.getFilename()), timeout, timeout);
			} catch (MalformedURLException e) {
				System.out.println("地址 :" + ss.getHref() + " 链接错误");
				continue;
			} catch (IOException e) {
				System.out.println("地址 :" + ss.getHref() + " 链接超时");
				continue;
			}
		}
	}

	/**
	 * 根据Url抓取页面返回document对象
	 * 
	 * @param url
	 *            链接地址
	 * @param filename
	 *            文件名称
	 * @param filepath
	 *            文件生成路径
	 * @param ecoding
	 *            编码格式
	 * @param timeout
	 *            延时
	 * @return boolean
	 */
	public static Document copyURLToHtmlDoc(String url, String filename,
			String filepath, String ecoding, int timeout) {
		Document doc = null;
		try {

			doc = Jsoup.connect(url).timeout(timeout).get();// 根据Url抓取页面放入document对象
			makeHtmlLinkFile(doc, filepath, timeout);// 根据u网页中css、js、img链接地址及文件名生成本地文件
			renameHref(doc);// 把页面中link标签中的href属性改为本地的相对路径,正确加载css样式
		    removeHref(doc);// 去掉A链接
		    doc.select("html").before("<!-- saved from url=("+new DecimalFormat("0000").format(url.length())+")"+url+" -->");
		} catch (IOException e) {
			System.out.println("访问页面地址 : " + url + "链接超时");
			System.out.println("访问页面地址 : " + url + "失败");
			return doc;
		} catch (IllegalArgumentException e) {
			System.out.println("访问页面地址 : " + url + "输入错误");
			System.out.println("访问页面地址 : " + url + "失败");
			return doc;
		}
		System.out.println("访问页面地址 : " + url + "成功");
		return doc;
	}

	/**
	 * 根据url抓取网页,并生成本地的html文件 解决了css样式问题
	 * 
	 * @param url
	 *            链接地址
	 * @param filename
	 *            文件名称
	 * @param filepath
	 *            文件生成路径
	 * @param ecoding
	 *            编码格式
	 * @return boolean
	 */
	public static Document copyURLToHtmlDoc(String url, String filename,
			String filepath, String ecoding) {
		return copyURLToHtmlDoc(url, filename, filepath, ecoding, 60000);
	}

	/**
	 * 过滤文件名,得到文件后缀
	 * 
	 * @param filename
	 *            文件名
	 * @return
	 */
	public static String getPostfix(String filename) {
		filename = StringUtils.substringAfterLast(filename, ".");
		filename = StringUtils.substringBefore(filename, "?");
		filename = StringUtils.substringBefore(filename, "/");
		filename = StringUtils.substringBefore(filename, "\\");
		filename = StringUtils.substringBefore(filename, "&");
		filename = StringUtils.substringBefore(filename, "$");
		filename = StringUtils.substringBefore(filename, "%");
		filename = StringUtils.substringBefore(filename, "#");
		filename = StringUtils.substringBefore(filename, "@");
		return filename;
	}

	/**
	 * 根据抓取网页的document对象生成html文件
	 * @version 0.1 解决了window 换行符问题
	 * @param filepath
	 * @param filename
	 * @param doc
	 * @param ecoding
	 * @return
	 */
	public static boolean makeHtmlByDoc(String filepath, String filename,
			Document doc, String ecoding) {
		boolean flag = false;
		try {
			String html = doc.html();
			html = html.replaceAll("\n", "\r\n");
			FileUtils.writeStringToFile(new File(filepath + filename), html, ecoding);
			flag = true;
		} catch (IOException e) {
			System.out.println("生成页面错误 文件： " + filepath + filename);
		}// 生成本地的html文件
		System.out.println("生成html页面 文件 : " + filepath + filename + "完成");
		return flag;
	}
}