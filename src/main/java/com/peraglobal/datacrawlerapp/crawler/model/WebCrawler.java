package com.peraglobal.datacrawlerapp.crawler.model;

public class WebCrawler extends Crawler {

	private WebRule webRule;
	
	private Proxy proxy;


	public WebRule getWebRule() {
		return webRule;
	}

	public void setWebRule(WebRule webRule) {
		this.webRule = webRule;
	}
	
	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
	
}
