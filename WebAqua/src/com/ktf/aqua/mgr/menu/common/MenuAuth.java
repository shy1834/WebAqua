/*
 * Created on 2006. 2. 23.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.common;

import org.apache.struts.action.ActionForm;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuAuth extends ActionForm {
	
	private String userId;
	private String menuId;
	private String authCode;
	private String levelDepth;

	/**
	 * 
	 */
	public MenuAuth() {
		this.userId = "";
		this.menuId = "";
		this.authCode = "";
		this.levelDepth = "";
	}

	/**
	 * @return Returns the authCode.
	 */
	public String getAuthCode() {
		return authCode;
	}
	/**
	 * @param authCode The authCode to set.
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	/**
	 * @return Returns the levelDepth.
	 */
	public String getLevelDepth() {
		return levelDepth;
	}
	/**
	 * @param levelDepth The levelDepth to set.
	 */
	public void setLevelDepth(String levelDepth) {
		this.levelDepth = levelDepth;
	}
	/**
	 * @return Returns the menuId.
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId The menuId to set.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
