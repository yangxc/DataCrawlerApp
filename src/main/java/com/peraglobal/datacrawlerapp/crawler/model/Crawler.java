package com.peraglobal.datacrawlerapp.crawler.model;

import java.util.List;

/**
 *  <code>Crawler.java</code>
 *  <p>功能:定义顶层爬虫对象（包括运行履历和爬虫规则）
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class Crawler {

	/**
	 * 爬虫的id
	 */
	private String crawlerId;
	
	/**
	 * 爬虫的名称
	 */
	private String crawlerName;
	
	/**
	 * 抓取数据的总体数量
	 */
	private int pageCrawlerCount;
	
	/**
	 * 爬虫历史
	 */
	private List<CrawlerHistory> crawlerHistory;

	
	public String getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}

	public String getCrawlerName() {
		return crawlerName;
	}

	public void setCrawlerName(String crawlerName) {
		this.crawlerName = crawlerName;
	}

	public int getPageCrawlerCount() {
		return pageCrawlerCount;
	}

	public void setPageCrawlerCount(int pageCrawlerCount) {
		this.pageCrawlerCount = pageCrawlerCount;
	}

	public List<CrawlerHistory> getCrawlerHistory() {
		return crawlerHistory;
	}

	public void setCrawlerHistory(List<CrawlerHistory> crawlerHistory) {
		this.crawlerHistory = crawlerHistory;
	}

}
