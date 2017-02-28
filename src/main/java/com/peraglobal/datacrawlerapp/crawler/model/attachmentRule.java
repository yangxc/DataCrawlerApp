package com.peraglobal.datacrawlerapp.crawler.model;

import java.io.Serializable;

/**
 *  <code>Attachment.java</code>
 *  <p>功能:附件下载规则
 *  
 *  <p>Copyright 安世亚太 2017 All right reserved.
 *  @author yongqian.liu	
 *  @version 1.0
 *  @see 2017-2-27
 */
public class attachmentRule implements Serializable {

	private static final long serialVersionUID = 5822479188090279457L;

	/**
	 * @category 附件地址
	 */
	private String attachmentKey;
	
	/**
	 * @category 附件类型
	 */
	private String attachmentType;
	
	
	public String getAttachmentKey() {
		return attachmentKey;
	}
	
	public void setAttachmentKey(String attachmentKey) {
		this.attachmentKey = attachmentKey;
	}
	
	public String getAttachmentType() {
		return attachmentType;
	}
	
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	
}
