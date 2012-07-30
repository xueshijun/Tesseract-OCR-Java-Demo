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
ʵ�ֹ��ܣ� 
1.����URLץȡ��ҳ 
2.���(�޸�)��ҳ���� 
3.��������ҳ��ص�js,css,img,flash,iframe�� 
4.�޸�js,css,img,flash,iframe��·�� 
5.���ע��<!-- saved from url=("+new DecimalFormat("0000").format(url.length())+")"+url+" -->����ҳ���ᵯ���������нű���ʾ 
6.���ձ��뷽ʽ���ɱ���ҳ�� 

�����ࣺ 


 * **/
	
	
	
	
	
	
	
	
	/**
	 * ����HTML�����ص���ʱĿ¼��
	 */
	public static void makeHeatMapHtml() {
		System.out.println("����ҳ�濪ʼ......");
		long start = System.currentTimeMillis();
		String url = "";//
		String filepath = "";// 
		String filename = "";// 
		String ecoding = "";// 
		
		url = "http://finance.people.com.cn/GB/index.html";
		filepath = "F:\\people.temp\\";
		filename = "index.html";// �ļ�����
		ecoding = "gbk";// �����ʽ
		if (copyURLToHtml(url, filepath, filename, ecoding)) {// ���ҳ����ʳɹ�������URL��ҳ��������ʱĿ¼
			copyDirToDir(filepath,StringUtils.replace(filepath, ".temp",""));// ����ʱĿ¼�е��ļ����Ƶ�����
			delTempFile(filepath);// ɾ����ʱĿ¼
		} 

		System.out.println("����ҳ����� ,��ʱ"
				+ (System.currentTimeMillis() - start) / 1000 / 60 + "����");
	}

	/**
	 * �ļ���copy
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
				System.out.println("������ʱĿ¼ʧ��");
			}
		}
	}

	/**
	 * ɾ����ʱĿ¼
	 * 
	 * @param filepath
	 */
	protected static void delTempFile(String filepath) {
		File tempFile = new File(filepath);
		try {
				FileUtils.deleteDirectory(tempFile);
		} catch (IOException e) {
			System.out.println("ɾ����ʱĿ¼ʧ��");
		}
	}

	/**
	 * ���ɱ���HTML
	 * 
	 * @param url
	 *            ���ӵ�ַ
	 * @param filepath
	 *            �����ļ�·��
	 * @param filename
	 *            �����ļ�����
	 * @param ecoding
	 *            ���뷽ʽ
	 * @return
	 */
	private static boolean copyURLToHtml(String url, String filepath, String filename,
			String ecoding) {
		boolean flag = false;
		Document doc;
		doc = copyURLToHtmlDoc(url, filename, filepath, ecoding, 5 * 60000);// ����Urlץȡҳ�����document����
		if (null == doc) {
			System.out.println("����ҳ��" + url + "ʧ��......");
			return flag;
		}
		String content = "<script type='text/javascript' src='../../render.js' charset='utf-8' ></script>"
			+ "<script type='text/javascript'>"
			+ "_RunTerrenStyle();"
			+ "</script>";
		doc = appendLink(doc, "body", content);// ��head��ǩ�м���js
		flag = makeHtmlByDoc(filepath, filename, doc, ecoding);// ����html�ļ�
		System.out.println("����ҳ��" + url + "�ɹ�......���룺 " + ecoding);
		return flag;
	}

	/**
	 * ����urlץȡ��ҳ���ҵ�css��ʽ,js,img���ӵ�ַ���ļ���
	 * 
	 * @param doc
	 *            jsoup.nodes.Document
	 * @return List<StyleSheet>
	 */
	public static List<HtmlFileLink> getHtmlFileLink(Document doc) {
		List<HtmlFileLink> HtmlFileLinks = new ArrayList<HtmlFileLink>();
		String postfix = "";// �ļ���׺��
		int index = 0; // �����ļ���
		Elements importcss = doc.select("link[href]");// �ҵ�document�д���link��ǩ��Ԫ��
		for (Element link : importcss) {
			postfix = "css";
			if (link.attr("rel").equals("stylesheet")) {// ���rel����ΪHtmlFileLink
				String href = link.attr("abs:href");// �õ�css��ʽ��href�ľ���·��
				// ��http://news.baidu.com/resource/css/search.css
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
	 * ȥ��HTML�е�A��ǩ���ӵ�ַ
	 * 
	 * @param doc
	 */
	public static void removeHref(Document doc) {
		doc.select("a").removeAttr("href");// ȥ��a������
		doc.select("img").removeAttr("onclick"); // ȥ��ͼƬonclick�¼�
		doc.select("input[type=submit]").attr("type", "button");// submit��ť��Ϊ��ͨ��ť
		doc.select("input[type=button]").removeAttr("onclick"); // ȥ����ť��onclick�¼�
		doc.select("area").removeAttr("href");// ȥ��area������
	}

	/**
	 * �޸�ץȡ��ҳ�е�css��ʽ�����ӵ�ַ,��Ϊ���·��
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
	 * ����ҳ��Ԫ������Ӷ���
	 * 
	 * @param doc
	 * @param tag
	 *            ��ǩ
	 * @param content
	 *            ����
	 * @return
	 */
	public static Document appendLink(Document doc, String tag, String content) {
		Element element = doc.select(tag).first();
		element.append(content);
		return doc;
	}

	/**
	 * ����u��ҳ��css��js��img���ӵ�ַ���ļ������ɱ����ļ�
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
				System.out.println("����ļ��� :" + filepath + " ����");
			}
		}
		List<HtmlFileLink> list = getHtmlFileLink(doc);// ����urlץȡ��ҳ���ҵ�css��ʽ���ӵ�ַ���ļ���
		for (HtmlFileLink ss : list) {
			/**
			 * jsoup��֧�ַ���js�ļ����Ը���apache commons��fileUtil����js��css�ļ�
			 */
			try {
				FileUtils.copyURLToFile(new URL(ss.getHref()), new File(
						filepath + ss.getFilename()), timeout, timeout);
			} catch (MalformedURLException e) {
				System.out.println("��ַ :" + ss.getHref() + " ���Ӵ���");
				continue;
			} catch (IOException e) {
				System.out.println("��ַ :" + ss.getHref() + " ���ӳ�ʱ");
				continue;
			}
		}
	}

	/**
	 * ����Urlץȡҳ�淵��document����
	 * 
	 * @param url
	 *            ���ӵ�ַ
	 * @param filename
	 *            �ļ�����
	 * @param filepath
	 *            �ļ�����·��
	 * @param ecoding
	 *            �����ʽ
	 * @param timeout
	 *            ��ʱ
	 * @return boolean
	 */
	public static Document copyURLToHtmlDoc(String url, String filename,
			String filepath, String ecoding, int timeout) {
		Document doc = null;
		try {

			doc = Jsoup.connect(url).timeout(timeout).get();// ����Urlץȡҳ�����document����
			makeHtmlLinkFile(doc, filepath, timeout);// ����u��ҳ��css��js��img���ӵ�ַ���ļ������ɱ����ļ�
			renameHref(doc);// ��ҳ����link��ǩ�е�href���Ը�Ϊ���ص����·��,��ȷ����css��ʽ
		    removeHref(doc);// ȥ��A����
		    doc.select("html").before("<!-- saved from url=("+new DecimalFormat("0000").format(url.length())+")"+url+" -->");
		} catch (IOException e) {
			System.out.println("����ҳ���ַ : " + url + "���ӳ�ʱ");
			System.out.println("����ҳ���ַ : " + url + "ʧ��");
			return doc;
		} catch (IllegalArgumentException e) {
			System.out.println("����ҳ���ַ : " + url + "�������");
			System.out.println("����ҳ���ַ : " + url + "ʧ��");
			return doc;
		}
		System.out.println("����ҳ���ַ : " + url + "�ɹ�");
		return doc;
	}

	/**
	 * ����urlץȡ��ҳ,�����ɱ��ص�html�ļ� �����css��ʽ����
	 * 
	 * @param url
	 *            ���ӵ�ַ
	 * @param filename
	 *            �ļ�����
	 * @param filepath
	 *            �ļ�����·��
	 * @param ecoding
	 *            �����ʽ
	 * @return boolean
	 */
	public static Document copyURLToHtmlDoc(String url, String filename,
			String filepath, String ecoding) {
		return copyURLToHtmlDoc(url, filename, filepath, ecoding, 60000);
	}

	/**
	 * �����ļ���,�õ��ļ���׺
	 * 
	 * @param filename
	 *            �ļ���
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
	 * ����ץȡ��ҳ��document��������html�ļ�
	 * @version 0.1 �����window ���з�����
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
			System.out.println("����ҳ����� �ļ��� " + filepath + filename);
		}// ���ɱ��ص�html�ļ�
		System.out.println("����htmlҳ�� �ļ� : " + filepath + filename + "���");
		return flag;
	}
}