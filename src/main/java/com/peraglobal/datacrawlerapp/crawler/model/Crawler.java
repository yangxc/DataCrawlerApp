package com.peraglobal.datacrawlerapp.crawler.model;

import java.util.ArrayList;
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
	private String id;
	
	/**
	 * 爬虫的名称
	 */
	private String name;

	/**
	 * 爬虫历史
	 */
	private List<CrawlerHistory> histories = new ArrayList<>();
	
	/**
	 * 爬虫采集规则
	 */
	private CrawlerRule crawlerRule;
	
	/**
	 * 抓取数据的总体数量
	 */
	private int pageCrawledCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CrawlerHistory> getHistories() {
		return histories;
	}

	public void setHistories(List<CrawlerHistory> histories) {
		this.histories = histories;
	}

	public CrawlerRule getCrawlerRule() {
		return crawlerRule;
	}

	public void setCrawlerRule(CrawlerRule crawlerRule) {
		this.crawlerRule = crawlerRule;
	}

	public int getPageCrawledCount() {
		return pageCrawledCount;
	}

	public void setPageCrawledCount(int pageCrawledCount) {
		this.pageCrawledCount = pageCrawledCount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
