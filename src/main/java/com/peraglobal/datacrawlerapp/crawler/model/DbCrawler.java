package com.peraglobal.datacrawlerapp.crawler.model;

import java.io.Serializable;

/**
 *  <code>DbCrawler.java</code>
 *  <p>功能:数据库采集服务集成类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class DbCrawler extends Crawler implements Serializable {

	private static final long serialVersionUID = 5821503653276789562L;
	
	/**
	 * @category 数据库连接对象
	 */
	private DbConnection dbConnection;

	public DbConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}
