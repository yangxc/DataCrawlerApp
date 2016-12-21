package com.peraglobal.datacrawlerapp.crawler.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  <code>DBCrawlerRule.java</code>
 *  <p>功能:数据库抓取规则
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-19
 *  </br>最后修改人 无
 */
public class DBCrawlerRule implements CrawlerRule {
	
	/**
	 * 数据库连接信息
	 */
	private DBConnectionInfo connectionInfo;
	
	/**
	 * 要抓取的表名
	 */
	private String tableName;
	
	/**
	 * 要抓取的字段信息
	 */
	private List<DBFieldInfo> fields = new ArrayList<>();

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 爬虫id
	 */
	private String crawlerId;

	public DBConnectionInfo getConnectionInfo() {
		return connectionInfo;
	}

	public void setConnectionInfo(DBConnectionInfo connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<DBFieldInfo> getFields() {
		return fields;
	}

	public void setFields(List<DBFieldInfo> fields) {
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(String crawlerId) {
		this.crawlerId = crawlerId;
	}

}
/**
 * {
	"name":"gecko",
	"type":"JdbcDataSource",
	"driver":"oracle.jdbc.driver.OracleDriver",
	"url":"jdbc:oracle:thin:@192.168.50.96:1521:gecko",
	"user":"gecko",
	"password":"gecko",
	"entity":
		{
			"name":"DATATEST",
			"query":"SELECT * FROM DATATEST T",
			"pk":"ID",
			"field":
				[
					{
						"name":"id",
						"as":"唯一标识",
						"type":"-5"
					},
					{
						"name":"name",
						"as":"记录名",
						"type":"12"
					}
				]
		}
	
}
 */
