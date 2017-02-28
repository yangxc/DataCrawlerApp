package com.peraglobal.datacrawlerapp.crawler.model;

import java.io.Serializable;

/**
 *  <code>DbField.java</code>
 *  <p>功能:数据库表数学规则
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2016-12-14
 */
public class DbField implements Serializable {

	private static final long serialVersionUID = -7190035565886097969L;

	/**
	 * @category 名称
	 */
	private String name;
	
	/**
	 * @category 字段类型
	 */
	private String type;
	
	/**
	 * @category 别名
	 */
	private String as;
	
	/**
	 * @category 附件类型
	 */
	private String filetype;
	
	/**
	 * @category 附件名称
	 */
	private String filename;
	

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
	
	public String getAs() {
		return as;
	}
	public void setAs(String as) {
		this.as = as;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
