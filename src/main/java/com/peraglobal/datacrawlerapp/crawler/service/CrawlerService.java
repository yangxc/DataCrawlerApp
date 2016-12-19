package com.peraglobal.datacrawlerapp.crawler.service;

import java.util.List;

import com.peraglobal.datacrawlerapp.crawler.model.Crawler;
import com.peraglobal.datacrawlerapp.crawler.model.CrawlerHistory;

public class CrawlerService {

	/**
	 * 启动爬虫
	 * @param crawlerId 爬虫的ID
	 * @return
	 */
	public boolean start(String crawlerId) {
		//Crawler crawler = new Crawler();
		//crawler
		return false;
	}
	
	/**
	 * 停止爬虫
	 * @param crawlerId 爬虫的ID
	 * @return
	 */
	public boolean stop(String crawlerId) {
		//Crawler crawler = new Crawler();
		//crawler
		return false;
	}
	
	//提供web service
	public int getCrawlerDataCount(String crawlerId) {
		return 0;
	}
	
	public List<CrawlerHistory> getCrawlerHistroy(String crawlerId) {
		return null;
	}
	
	public Crawler getCrawlerById(String crawlerId) {
		return null;
	}
	
}
