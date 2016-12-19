package com.peraglobal.datacrawlerapp.crawler.model;

import java.util.Date;

/**
 *  <code>Crawler.java</code>
 *  <p>功能:定义顶层爬虫对象（包括运行履历和爬虫规则）
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class CrawlerHistory {
	
	private String id;

	/**
	 * 爬虫的id
	 */
	private String crawlerId;
	
	/**
	 * 抓取数据的数量
	 */
	private int pageCrawledCount;
	
	/**
	 * 爬虫的启动时间
	 */
	private Date startDate;
	
	public boolean isHasException() {
		return hasException;
	}

	public void setHasException(boolean hasException) {
		this.hasException = hasException;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * 爬虫的停止时间
	 */
	private Date StopDate;
	
	/**
	 * 指示是否存在异常
	 */
	private boolean hasException;
	
	/**
	 * 异常对应的信息
	 */
	private String exceptionMessage;

	public String getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}

	public int getPageCrawledCount() {
		return pageCrawledCount;
	}

	public void setPageCrawledCount(int pageCrawledCount) {
		this.pageCrawledCount = pageCrawledCount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return StopDate;
	}

	public void setStopDate(Date stopDate) {
		StopDate = stopDate;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
