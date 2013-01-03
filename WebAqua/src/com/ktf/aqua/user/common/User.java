/*
 * Created on 2006. 2. 10.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.user.common;

/**
 * 사용자 관리를 위하여 필요한 도메인 클래스.
 * USERINFO 테이블의 각 칼럼에 해당하는 setter와 getter를 가진다. 
 */
public class User {
	
	private String sequence = "";
	private String userId = "";
	private String password = "";
	private String name = "";
	private String email1 = "";
	private String email2 = "";
	private String email3 = "";
	private String regRgstNum = "";
	
	private String userType;    
	private String userCode;    
	private String cpId;
	private String sabun;
	
	private String company;   
	private String companyDept; 
	private String companyGroup;
	private String partCd;    
	private String partName;
	private String innerTel;
	
	private String mobile;
	private String telnumber; 
	  
	private String authType;   
	private String progStat_Cd;
	private String applyStat_Cd;
	          
	private String applyDate;
	private String regId;
	private String regDate;
	private String updateDate;
	private String updateId;
	
	private long errorCode;
	private boolean status;	
	
	public User() {
		this.userId = "";
		this.name = "";
		this.regRgstNum = "";
		this.mobile = "";
		// TODO Auto-generated constructor stub
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
	 * @return Returns the sequence.
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
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
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail1() {
		return email1;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	/**
	 * @return Returns the email2.
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * @param email2 The email2 to set.
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	/**
	 * @return Returns the password.
	 */
	public String getEmail3() {
		return email3;
	}

	/**
	 * @param email2 The email2 to set.
	 */
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the applyDate.
	 */
	public String getApplyDate() {
		return applyDate;
	}
	/**
	 * @param applyDate The applyDate to set.
	 */
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	/**
	 * @return Returns the applyStat_Cd.
	 */
	public String getApplyStat_Cd() {
		return applyStat_Cd;
	}
	/**
	 * @param applyStat_Cd The applyStat_Cd to set.
	 */
	public void setApplyStat_Cd(String applyStat_Cd) {
		this.applyStat_Cd = applyStat_Cd;
	}
	/**
	 * @return Returns the authType.
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType The authType to set.
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return Returns the companyDept.
	 */
	public String getCompanyDept() {
		return companyDept;
	}
	/**
	 * @param companyDept The companyDept to set.
	 */
	public void setCompanyDept(String companyDept) {
		this.companyDept = companyDept;
	}
	/**
	 * @return Returns the companyGroup.
	 */
	public String getCompanyGroup() {
		return companyGroup;
	}
	/**
	 * @param companyGroup The companyGroup to set.
	 */
	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}
	/**
	 * @return Returns the cpId.
	 */
	public String getCpId() {
		return cpId;
	}
	/**
	 * @param cpId The cpId to set.
	 */
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	/**
	 * @return Returns the innerTel.
	 */
	public String getInnerTel() {
		return innerTel;
	}
	/**
	 * @param innerTel The innerTel to set.
	 */
	public void setInnerTel(String innerTel) {
		this.innerTel = innerTel;
	}
	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return Returns the partCd.
	 */
	public String getPartCd() {
		return partCd;
	}
	/**
	 * @param partCd The partCd to set.
	 */
	public void setPartCd(String partCd) {
		this.partCd = partCd;
	}
	/**
	 * @return Returns the partName.
	 */
	public String getPartName() {
		return partName;
	}
	/**
	 * @param partName The partName to set.
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * @return Returns the progStat_Cd.
	 */
	public String getProgStat_Cd() {
		return progStat_Cd;
	}
	/**
	 * @param progStat_Cd The progStat_Cd to set.
	 */
	public void setProgStat_Cd(String progStat_Cd) {
		this.progStat_Cd = progStat_Cd;
	}
	/**
	 * @return Returns the regDate.
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate The regDate to set.
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return Returns the regId.
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId The regId to set.
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return Returns the sabun.
	 */
	public String getSabun() {
		return sabun;
	}
	/**
	 * @param sabun The sabun to set.
	 */
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	/**
	 * @return Returns the telnumber.
	 */
	public String getTelnumber() {
		return telnumber;
	}
	/**
	 * @param telnumber The telnumber to set.
	 */
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	/**
	 * @return Returns the updateDate.
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return Returns the updateId.
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * @param updateId The updateId to set.
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * @return Returns the userType.
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return Returns the userCode.
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode The userCode to set.
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * 현재 user_info_tbl에 등록 된지 확인 메써드.
	 */
	public boolean isExistUser(String userid){
		if ( getUserId().equals(userid)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return Returns the regRgstNum.
	 */
	public String getRegRgstNum() {
		return regRgstNum;
	}
	/**
	 * @param regRgstNum The regRgstNum to set.
	 */
	public void setRegRgstNum(String regRgstNum) {
		this.regRgstNum = regRgstNum;
	}

}
