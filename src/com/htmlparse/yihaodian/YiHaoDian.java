package com.htmlparse.yihaodian;

import com.baseUrl.TSZPage;

public class YiHaoDian extends TSZPage {

	public YiHaoDian(String url) {
		super(url);
		this.strMobileUrl=url.replace("http://www.yihaodian.com", "http://m.yihaodian.com");
	}
}
