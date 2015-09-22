/**
 * Title : ChnlEmailBean.java
 * Description : This Class is to 
 * Copyright: Ford Motor Company
 */
package com.hackthon.samurai;

import java.util.Date;

/**
 * @project	:DataLoader
 * @author	:Vijayakumar Anbu (avijaya8)
 * @date	:Nov 28, 2013
 * @since 	:JDK 1.5
 */
public class ChnlEmailBean {
	
	String chnlName;
	Date emailDate;
	boolean emailSent;
	/**
	 * Get the chnlName
	 * @return the chnlName
	 */
	public String getChnlName() {
		return chnlName;
	}
	/**
	 * Set the chnlName
	 * @param chnlName, to set chnlName
	 */
	public void setChnlName(String chnlName) {
		this.chnlName = chnlName;
	}
	/**
	 * Get the emailDate
	 * @return the emailDate
	 */
	public Date getEmailDate() {
		return emailDate;
	}
	/**
	 * Set the emailDate
	 * @param emailDate, to set emailDate
	 */
	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}
	/**
	 * Get the emailSent
	 * @return the emailSent
	 */
	public boolean isEmailSent() {
		return emailSent;
	}
	/**
	 * Set the emailSent
	 * @param emailSent, to set emailSent
	 */
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
	
	

}
