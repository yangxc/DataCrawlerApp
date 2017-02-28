package com.peraglobal.datacrawlerapp.crawler.model;

import java.io.Serializable;

/**
 *  <code>DbConnection.java</code>
 *  <p>功能:数据库连接类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class DbConnection implements Serializable {

	private static final long serialVersionUID = 3741021089502624197L;

	/**
	 * @category 名称
	 */
	private String name;
	
	/**
	 * @category 驱动
	 */
	private String driver;
	
	/**
	 * @category 地址
	 */
	private String url;
	
	/**
	 * @category 用户名
	 */
	private String user;
	
	/**
	 * @category 密码
	 */
	private String password;
	
	/**
	 * @category 类型
	 */
	private String type;
	
	/**
	 * @category 表信息
	 */
	private DbTable tables;
	
	public DbTable getTables() {
		return tables;
	}
	public void setTables(DbTable tables) {
		this.tables = tables;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
