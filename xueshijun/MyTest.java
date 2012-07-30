package com.xueshijun;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MyTest { 
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		System.out.println("========BEGIN!==========");
		
		 
		Document doc = Jsoup.connect("http://www.360buy.com/product/1004448812.html").get();
		Element body = doc.head();
		body.getElementsByAttribute("description");
//		
//		Elements links = doc.select("a[href]"); // a with href
//		Elements pngs = doc.select("img[src$=.png]");
//		  // img with src ending .png
//
//		Element masthead = doc.select("div.masthead").first();
//		  // div with class=masthead
//
//		Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
		
		System.out.println(body.toString());
		System.out.println("========END!==========");
	}

	public static class Input{

	  public static void Parse_a_document_from_a_String() {
			//
			//Parse a document from a String
			//Problem
			//
			//You have HTML in a Java String, and you want to parse that HTML to get at its contents, or to make sure it's well formed, or to modify it. The String may have come from user input, a file, or from the web.
			//Solution
			//
			//Use the static Jsoup.parse(String html) method, or Jsoup.parse(String html, String baseUri) if the page came from the web, and you want to get at absolute URLs (see [working-with-urls]).
			//
			//String html = "<html><head><title>First parse</title></head>"
			//  + "<body><p>Parsed HTML into a doc.</p></body></html>";
			//Document doc = Jsoup.parse(html);
			//
			//Description
			//
			//The parse(String html, String baseUri) method parses the input HTML into a new Document. The base URI argument is used to resolve relative URLs into absolute URLs, and should be set to the URL where the document was fetched from. If that's not applicable, or if you know the HTML has a base element, you can use the parse(String html) method.
			//
			//As long as you pass in a non-null string, you're guaranteed to have a successful, sensible parse, with a Document containing (at least) a head and a body element. (BETA: if you do get an exception raised, or a bad parse-tree, please file a bug.)
			//
			//Once you have a Document, you can get get at the data using the appropriate methods in Document and its supers Element and Node.

		  String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
				Document doc = Jsoup.parse(html);
				
				System.out.println(doc.html());
				
	  }
	  public static void Parsing_a_body_fragment() {

			//Parsing a body fragment
			//Problem
			//
			//You have a fragment of body HTML (e.g. a div containing a couple of p tags; as opposed to a full HTML document) that you want to parse. Perhaps it was provided by a user submitting a comment, or editing the body of a page in a CMS.
			//Solution
			//
			//Use the Jsoup.parseBodyFragment(String html) method.
			//
			//String html = "<div><p>Lorem ipsum.</p>";
			//Document doc = Jsoup.parseBodyFragment(html);
			//Element body = doc.body();
			//
			//Description
			//
			//The parseBodyFragment method creates an empty shell document, and inserts the parsed HTML into the body element. If you used the normal Jsoup.parse(String html) method, you would generally get the same result, but explicitly treating the input as a body fragment ensures that any bozo HTML provided by the user is parsed into the body element.
			//
			//The Document.body() method retrieves the element children of the document's body element; it is equivalent to doc.getElementsByTag("body").
			//Stay safe
			//
			//If you are going to accept HTML input from a user, you need to be careful to avoid cross-site scripting attacks. See the documentation for the Whitelist based cleaner, and clean the input with clean(String bodyHtml, Whitelist whitelist).
		  String html = "<div><p>Lorem ipsum.</p>";
		  Document doc = Jsoup.parseBodyFragment(html);
		  Element body = doc.body();
	  }
	  public static void Load_a_Document_from_a_URL() throws IOException {
			//
			//Load a Document from a URL
			//Problem
			//
			//You need to fetch and parse a HTML document from the web, and find data within it (screen scraping).
			//Solution
			//
			//Use the Jsoup.connect(String url) method:
			//
			//Document doc = Jsoup.connect("http://example.com/").get();
			//String title = doc.title();
			//
			//Description
			//
			//The connect(String url) method creates a new Connection, and get() fetches and parses a HTML file. If an error occurs whilst fetching the URL, it will throw an IOException, which you should handle appropriately.
			//
			//The Connection interface is designed for method chaining to build specific requests:
			//
			Document doc = Jsoup.connect("http://www.360buy.com/")
					.data("query", "Java")
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000).post();
			
			//This method only suports web URLs (http and https protocols); if you need to load from a file, use the parse(File in, String charsetName) method instead.
		 
		 
	  }
	  public static void Load_a_Document_from_a_File() { 
			//
			//Load a Document from a File
			//Problem
			//
			//You have a file on disk that contains HTML, that you'd like to load and parse, and then maybe manipulate or extract data from.
			//Solution
			//
			//Use the static Jsoup.parse(File in, String charsetName, String baseUri) method:
			//
			//File input = new File("/tmp/input.html");
			//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			//
			//Description
			//
			//The parse(File in, String charsetName, String baseUri) method loads and parses a HTML file. If an error occurs whilst loading the file, it will throw an IOException, which you should handle appropriately.
			//
			//The baseUri parameter is used by the parser to resolve relative URLs in the document before a <base href> element is found. If that's not a concern for you, you can pass an empty string instead.
			//
			//There is a sister method parse(File in, String charsetName) which uses the file's location as the baseUri. This is useful if you are working on a filesystem-local site and the relative links it points to are also on the filesystem.

	}
	}  
	 public static class Extractingdata{ 
		 public static void Use_DOM_methods_to_navigate_a_document() throws IOException {
		 
			//Element content = doc.getElementById("content");
			//Elements links = content.getElementsByTag("a");
			//for (Element link : links) {
			//  String linkHref = link.attr("href");
			//  String linkText = link.text();
			//}
			//
			//Description
			//
			//Elements provide a range of DOM-like methods to find elements, and extract and manipulate their data. The DOM getters are contextual: called on a parent Document they find matching elements under the document; called on a child element they find elements under that child. In this way you can winnow in on the data you want.
			//Finding elements
			//
			//    getElementById(String id)
			//    getElementsByTag(String tag)
			//    getElementsByClass(String className)
			//    getElementsByAttribute(String key) (and related methods)
			//    Element siblings: siblingElements(), firstElementSibling(), lastElementSibling(); nextElementSibling(), previousElementSibling()
			//    Graph: parent(), children(), child(int index)
			//
			//Element data
			//
			//    attr(String key) to get and attr(String key, String value) to set attributes
			//    attributes() to get all attributes
			//    id(), className() and classNames()
			//    text() to get and text(String value) to set the text content
			//    html() to get and html(String value) to set the inner HTML content
			//    outerHtml() to get the outer HTML value
			//    data() to get data content (e.g. of script and style tags)
			//    tag() and tagName()
			//
			//Manipulating HTML and text
			//
			//    append(String html), prepend(String html)
			//    appendText(String text), prependText(String text)
			//    appendElement(String tagName), prependElement(String tagName)
			//    html(String value)
			//
			 File input = new File("/tmp/input.html");
			 Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

			 Element content = doc.getElementById("content");
			 Elements links = content.getElementsByTag("a");
			 for (Element link : links) {
			   String linkHref = link.attr("href");
			   String linkText = link.text();
			   System.out.println(linkText);
			 } 
		 }
		 public static void Use_selector_syntax_to_find_elements() throws IOException {

			//Use selector-syntax to find elements
			//Problem
			//
			//You want to find or manipulate elements using a CSS or jquery-like selector syntax.
			//Solution
			//
			//Use the Element.select(String selector) and Elements.select(String selector) methods:
			//
			//File input = new File("/tmp/input.html");
			//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			//
			//Elements links = doc.select("a[href]"); // a with href
			//Elements pngs = doc.select("img[src$=.png]");
			//  // img with src ending .png
			//
			//Element masthead = doc.select("div.masthead").first();
			//  // div with class=masthead
			//
			//Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
			//
			//Description
			//
			//jsoup elements support a CSS (or jquery) like selector syntax to find matching elements, that allows very powerful and robust queries.
			//
			//The select method is available in a Document, Element, or in Elements. It is contextual, so you can filter by selecting from a specific element, or by chaining select calls.
			//
			//Select returns a list of Elements (as Elements), which provides a range of methods to extract and manipulate the results.
			//Selector overview
			//
			//    tagname: find elements by tag, e.g. a
			//    ns|tag: find elements by tag in a namespace, e.g. fb|name finds <fb:name> elements
			//    #id: find elements by ID, e.g. #logo
			//    .class: find elements by class name, e.g. .masthead
			//    [attribute]: elements with attribute, e.g. [href]
			//    [^attr]: elements with an attribute name prefix, e.g. [^data-] finds elements with HTML5 dataset attributes
			//    [attr=value]: elements with attribute value, e.g. [width=500]
			//    [attr^=value], [attr$=value], [attr*=value]: elements with attributes that start with, end with, or contain the value, e.g. [href*=/path/]
			//    [attr~=regex]: elements with attribute values that match the regular expression; e.g. img[src~=(?i)\.(png|jpe?g)]
			//    *: all elements, e.g. *
			//
			//Selector combinations
			//
			//    el#id: elements with ID, e.g. div#logo
			//    el.class: elements with class, e.g. div.masthead
			//    el[attr]: elements with attribute, e.g. a[href]
			//    Any combination, e.g. a[href].highlight
			//    ancestor child: child elements that descend from ancestor, e.g. .body p finds p elements anywhere under a block with class "body"
			//    parent > child: child elements that descend directly from parent, e.g. div.content > p finds p elements; and body > * finds the direct children of the body tag
			//    siblingA + siblingB: finds sibling B element immediately preceded by sibling A, e.g. div.head + div
			//    siblingA ~ siblingX: finds sibling X element preceded by sibling A, e.g. h1 ~ p
			//    el, el, el: group multiple selectors, find unique elements that match any of the selectors; e.g. div.masthead, div.logo
			//
			//Pseudo selectors
			//
			//    :lt(n): find elements whose sibling index (i.e. its position in the DOM tree relative to its parent) is less than n; e.g. td:lt(3)
			//    :gt(n): find elements whose sibling index is greater than n; e.g. div p:gt(2)
			//    :eq(n): find elements whose sibling index is equal to n; e.g. form input:eq(1)
			//    :has(seletor): find elements that contain elements matching the selector; e.g. div:has(p)
			//    :not(selector): find elements that do not match the selector; e.g. div:not(.logo)
			//    :contains(text): find elements that contain the given text. The search is case-insensitive; e.g. p:contains(jsoup)
			//    :containsOwn(text): find elements that directly contain the given text
			//    :matches(regex): find elements whose text matches the specified regular expression; e.g. div:matches((?i)login)
			//    :matchesOwn(regex): find elements whose own text matches the specified regular expression
			//    Note that the above indexed pseudo-selectors are 0-based, that is, the first element is at index 0, the second at 1, etc
			//
			//See the Selector API reference for the full supported list and details.
//			 File input = new File("/tmp/input.html");
//			 Document doc = Jsoup.parse(input, "UTF-8", "http://www.360buy.com/product/1004448812.html");
				Document doc = Jsoup.connect("http://www.360buy.com/product/1004448812.html").get();
				//Document doc = Jsoup.connect("http://example.com/").get();
				//String title = doc.title();

			 Elements links = doc.select("a[href]"); // a with href
			 Elements pngs = doc.select("img[src$=.png]");
			   // img with src ending .png

			 Element masthead = doc.select("div.breadcrumb").first();  
			   // div with class=masthead

			 Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
//			 System.out.println(links.toString());
			 System.out.println("=======================");
			 System.out.println(masthead.toString());
			 
			 
		 }
		 public static void Extract_attributes_text_an_HTML_from_elements() {
			//
			//Extract attributes, text, and HTML from elements
			//Problem
			//
			//After parsing a document, and finding some elements, you'll want to get at the data inside those elements.
			//Solution
			//
			//    To get the value of an attribute, use the Node.attr(String key) method
			//    For the text on an element (and its combined children), use Element.text()
			//    For HTML, use Element.html(), or Node.outerHtml() as appropriate
			//
			//For example:
			//
			//String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
			//Document doc = Jsoup.parse(html);
			//Element link = doc.select("a").first();
			//
			//String text = doc.body().text(); // "An example link"
			//String linkHref = link.attr("href"); // "http://example.com/"
			//String linkText = link.text(); // "example""
			//
			//String linkOuterH = link.outerHtml(); 
			//    // "<a href="http://example.com"><b>example</b></a>"
			//String linkInnerH = link.html(); // "<b>example</b>"
			//
			//Description
			//
			//The methods above are the core of the element data access methods. There are additional others:
			//
			//    Element.id()
			//    Element.tagName()
			//    Element.className() and Element.hasClass(String className)
			//
			//All of these accessor methods have corresponding setter methods to change the data.
			//See also
			//
			//    The reference documentation for Element and the collection Elements class
			//    Working with URLs
			//    finding elements with the CSS selector syntax
			//

		 }
		 public static void Working_with_URLs() throws IOException {
//			 jsoup » Cookbook » Extracting data » Working with URLs
//			 Working with URLs
//			 Problem
//
//			 You have a HTML document that contains relative URLs, which you need to resolve to absolute URLs.
//			 Solution
//
//			     Make sure you specify a base URI when parsing the document (which is implicit when loading from a URL), and
//			     Use the abs: attribute prefix to resolve an absolute URL from an attribute:
//
//			 Document doc = Jsoup.connect("http://jsoup.org").get();
//
//			 Element link = doc.select("a").first();
//			 String relHref = link.attr("href"); // == "/"
//			 String absHref = link.attr("abs:href"); // "http://jsoup.org/"
//
//			 Description
//
//			 In HTML elements, URLs are often written relative to the document's location: <a href="/download">...</a>. When you use the Node.attr(String key) method to get a href attribute, it will be returned as it is specified in the source HTML.
//
//			 If you want to get an absolute URL, there is a attribute key prefix abs: that will cause the attribute value to be resolved against the document's base URI (original location): attr("abs:href")
//
//			 For this use case, it is important to specify the base URI when parsing the document.
//
//			 If you don't want to use the abs: prefix, there is also a method Node.absUrl(String key) which does the same thing, but accesses via the natural attribute key.

			 
			 Document doc = Jsoup.connect("http://jsoup.org").get();

			 Element link = doc.select("a").first();
			 String relHref = link.attr("href"); // == "/"
			 String absHref = link.attr("abs:href"); // "http://jsoup.org/"
			 
			 System.out.println(relHref.toString());
		 }
		 public static void Example_program_listlinks() {
			 
		 }

	}
}
