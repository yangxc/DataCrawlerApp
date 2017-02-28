package com.peraglobal.datacrawlerapp.crawler.model;

import java.io.Serializable;
import java.util.List;

/**
 *  <code>DbTable.java</code>
 *  <p>功能:数据库表
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class DbTable implements Serializable {
	
	private static final long serialVersionUID = -26282454940127085L;
	
	/**
	 * @category 表名称
	 */
	private String name;
	
	/**
	 * @category 查询条件
	 */
	private String query;
	
	/**
	 * @category 主键
	 */
	private String pk;
	
	private List<DbField> fields;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public List<DbField> getFields() {
		return fields;
	}
	public void setFields(List<DbField> fields) {
		this.fields = fields;
	}
	
}
