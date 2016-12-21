package com.peraglobal.datacrawlerapp.crawler.model;

/**
 *  <code>DBFieldInfo.java</code>
 *  <p>功能:要抓取的数据库字段封装类
 *  
 *  <p>Copyright 安世亚太 2016 All right reserved.
 *  @author xiaochen.yang	
 *  @version 1.0
 *  @see 2016-12-19
 *  </br>最后修改人 无
 */
public class DBFieldInfo {
	
	/**
	 * 列的别名，也就是要保存的采集数据对应的列名
	 */
	private String alia;
	
	/**
	 * 要采集的列名
	 */
	private String fieldName;
	
	/**
	 * 要采集的列的类型
	 */
	private String fieldType;
	
	/**
	 * 是否为保存附件的列
	 */
	private boolean isAttachmentField;
	
	/**
	 * 附件的类型，是URL的还是二进制的
	 */
	private String attachmentType;
	
	/**
	 * 保存附件的列的列名
	 */
	private String attatchmentField;
	
	/**
	 * 是否为要排序的列
	 */
	private boolean isPK;
	
	public String getAlia() {
		return alia;
	}
	public void setAlia(String alia) {
		this.alia = alia;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public boolean isAttachmentField() {
		return isAttachmentField;
	}
	public void setAttachmentField(boolean isAttachmentField) {
		this.isAttachmentField = isAttachmentField;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getAttatchmentField() {
		return attatchmentField;
	}
	public void setAttatchmentField(String attatchmentField) {
		this.attatchmentField = attatchmentField;
	}
	public boolean isPK() {
		return isPK;
	}
	public void setPK(boolean isPK) {
		this.isPK = isPK;
	}
	
}
