package com.edsk.framework.session;

import java.io.Serializable;

/**
 * UserInfoSession 사용자 정보 관련 세션 정보
 * 
 * @author mksong
 */
public class UserInfoSession implements Serializable {
    String empNo;
    String userName;
    String titleCode;
    String titleName;
    String deptCode;
    String deptName;
    
    public UserInfoSession() {
    }
    
    public UserInfoSession(String empNo,String userName,
            String titleCode,String titleName,String deptCode,String deptName) {
        this.empNo=empNo;
        this.userName=userName;
        this.titleCode=titleCode;
        this.titleName=titleName;
        this.deptCode=deptCode;
        this.deptName=deptName;
    }

    /**
     * @return 사번
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @return 이름
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @return 직급코드
     */
    public String getTitleCode() {
        return titleCode;
    }
    /**
     * @return 직급명
     */
    public String getTitleName() {
        return titleName;
    }
    /**
     * @return 부서코드
     */
    public String getDeptCode() {
        return deptCode;
    }
    /**
     * @return 부서명
     */
    public String getDeptName() {
        return deptName;
    }
    /**
     * @param empNo 사번
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @param userName 이름
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @param titleCode 직급코드
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }
    /**
     * @param titleName 직급명
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    /**
     * @param string 부서코드
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    /**
     * @param deptName 부서명
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
