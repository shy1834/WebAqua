/*
 * Created on 2006. 2. 15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.user.common;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SSOAuthority {
	
	private String SSOE_SiteInfo;
	private String KTFE_UserID;
	private String KTFE_UserName;
	private String KTFE_RegRgstNum;
	private String KTFE_PhoneNum;
	private String KTFE_NickName;
	private String KTFE_keyid;
	private String KTFE_enckey;
	private String KTFE_UserHash;
	//private String KTFE_Wcdma;
	
	private boolean status;	
	private long errorCode;	
	private String errorDesc;

	
	/**
	 * 
	 */
	public SSOAuthority() {
		this.KTFE_enckey = "";
		this.KTFE_keyid = "";
		this.KTFE_NickName = "";
		this.KTFE_PhoneNum = "";
		this.KTFE_RegRgstNum = "";
		this.KTFE_UserHash = "";
		this.KTFE_UserID = "";
		this.KTFE_UserName = "";
		this.SSOE_SiteInfo = "";
		//this.KTFE_Wcdma = "";
		
		this.status = false;
		this.errorCode = 0;
		this.errorDesc = "";
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @return Returns the kTFE_enckey.
	 */
	public String getKTFE_enckey() {
		return KTFE_enckey;
	}
	/**
	 * @param ktfe_enckey The kTFE_enckey to set.
	 */
	public void setKTFE_enckey(String ktfe_enckey) {
		KTFE_enckey = ktfe_enckey;
	}
	/**
	 * @return Returns the kTFE_keyid.
	 */
	public String getKTFE_keyid() {
		return KTFE_keyid;
	}
	/**
	 * @param ktfe_keyid The kTFE_keyid to set.
	 */
	public void setKTFE_keyid(String ktfe_keyid) {
		KTFE_keyid = ktfe_keyid;
	}
	/**
	 * @return Returns the kTFE_NickName.
	 */
	public String getKTFE_NickName() {
		return KTFE_NickName;
	}
	/**
	 * @param nickName The kTFE_NickName to set.
	 */
	public void setKTFE_NickName(String nickName) {
		KTFE_NickName = nickName;
	}
	/**
	 * @return Returns the kTFE_PhoneNum.
	 */
	public String getKTFE_PhoneNum() {
		return KTFE_PhoneNum;
	}
	/**
	 * @param phoneNum The kTFE_PhoneNum to set.
	 */
	public void setKTFE_PhoneNum(String phoneNum) {
		KTFE_PhoneNum = phoneNum;
	}
	/**
	 * @return Returns the kTFE_RegRgstNum.
	 */
	public String getKTFE_RegRgstNum() {
		return KTFE_RegRgstNum;
	}
	/**
	 * @param regRgstNum The kTFE_RegRgstNum to set.
	 */
	public void setKTFE_RegRgstNum(String regRgstNum) {
		KTFE_RegRgstNum = regRgstNum;
	}
	/**
	 * @return Returns the kTFE_UserID.
	 */
	public String getKTFE_UserID() {
		return KTFE_UserID;
	}
	/**
	 * @param userID The kTFE_UserID to set.
	 */
	public void setKTFE_UserID(String userID) {
		KTFE_UserID = userID;
	}
	/**
	 * @return Returns the kTFE_UserName.
	 */
	public String getKTFE_UserName() {
		return KTFE_UserName;
	}
	/**
	 * @param userName The kTFE_UserName to set.
	 */
	public void setKTFE_UserName(String userName) {
		KTFE_UserName = userName;
	}
	
	/**
	 * @return Returns the sSOE_SiteInfo.
	 */
	public String getSSOE_SiteInfo() {
		return SSOE_SiteInfo;
	}
	/**
	 * @param siteInfo The sSOE_SiteInfo to set.
	 */
	public void setSSOE_SiteInfo(String siteInfo) {
		SSOE_SiteInfo = siteInfo;
	}	
	
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the errDesc.
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc The errDesc to set.
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}	
	
	/**
	 * @return Returns the errorCode.
	 */
	public long getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return Returns the kTFE_UserHash.
	 */
	public String getKTFE_UserHash() {
		return KTFE_UserHash;
	}
	/**
	 * @param userHash The kTFE_UserHash to set.
	 */
	public void setKTFE_UserHash(String userHash) {
		KTFE_UserHash = userHash;
	}

/*
	public String getKTFE_Wcdma() {
		return KTFE_Wcdma;
	}


	public void setKTFE_Wcdma(String kTFE_Wcdma) {
		KTFE_Wcdma = kTFE_Wcdma;
	}
*/
	
}
