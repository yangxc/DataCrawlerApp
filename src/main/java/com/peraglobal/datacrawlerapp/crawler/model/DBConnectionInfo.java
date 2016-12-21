package com.peraglobal.datacrawlerapp.crawler.model;

/**
 *  <code>DBConnectionInfo.java</code>
 *  <p>功能:数据库连接对象
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-15
 *  </br>最后修改人 无
 */
public class DBConnectionInfo {

	/**
	 * 驱动
	 */
	private String driverName;

	/**
	 * 连接字符串
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
