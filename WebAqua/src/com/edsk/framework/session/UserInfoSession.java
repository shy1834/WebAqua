package com.edsk.framework.session;

import java.io.Serializable;

/**
 * UserInfoSession ����� ���� ���� ���� ����
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
     * @return ���
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @return �̸�
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @return �����ڵ�
     */
    public String getTitleCode() {
        return titleCode;
    }
    /**
     * @return ���޸�
     */
    public String getTitleName() {
        return titleName;
    }
    /**
     * @return �μ��ڵ�
     */
    public String getDeptCode() {
        return deptCode;
    }
    /**
     * @return �μ���
     */
    public String getDeptName() {
        return deptName;
    }
    /**
     * @param empNo ���
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @param userName �̸�
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @param titleCode �����ڵ�
     */
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }
    /**
     * @param titleName ���޸�
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    /**
     * @param string �μ��ڵ�
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    /**
     * @param deptName �μ���
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
