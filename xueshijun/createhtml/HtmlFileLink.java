package com.xueshijun.createhtml;

public class HtmlFileLink {
	private String href;
	private String filename;
	private String type;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public HtmlFileLink(String href, String filename,String type) {
		super();
		this.href = href;
		this.filename = filename;
		this.type = type;
	}
	public HtmlFileLink() {
		super();
	}
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
}