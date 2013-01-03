/*
 * Created on 2005. 1. 13.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.iss.report;

import org.apache.struts.upload.FormFile;
/**
 * @author hyunyun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportTemplate_DTO {
	
	private String rownum;
	private String rept_tmpl_id;
	private String rept_tmpl_nm;
	private String rept_tmpl_file_nm;
	private String rept_tmpl_real_file;
	private FormFile rept_tmpl_file;
	/**
	 * @return Returns the rept_tmpl_real_file.
	 */
	public String getRept_tmpl_real_file() {
		return rept_tmpl_real_file;
	}
	/**
	 * @param rept_tmpl_real_file The rept_tmpl_real_file to set.
	 */
	public void setRept_tmpl_real_file(String rept_tmpl_real_file) {
		this.rept_tmpl_real_file = rept_tmpl_real_file;
	}
	private String rept_prd;
	
	/**
	 * @return Returns the rept_prd.
	 */
	public String getRept_prd() {
		return rept_prd;
	}
	/**
	 * @param rept_prd The rept_prd to set.
	 */
	public void setRept_prd(String rept_prd) {
		this.rept_prd = rept_prd;
	}
	/**
	 * @return Returns the rept_tmpl_file_nm.
	 */
	public String getRept_tmpl_file_nm() {
		return rept_tmpl_file_nm;
	}
	/**
	 * @param rept_tmpl_file_nm The rept_tmpl_file_nm to set.
	 */
	public void setRept_tmpl_file_nm(String rept_tmpl_file_nm) {
		this.rept_tmpl_file_nm = rept_tmpl_file_nm;
	}
	/**
	 * @return Returns the rept_tmpl_id.
	 */
	public String getRept_tmpl_id() {
		return rept_tmpl_id;
	}
	/**
	 * @param rept_tmpl_id The rept_tmpl_id to set.
	 */
	public void setRept_tmpl_id(String rept_tmpl_id) {
		this.rept_tmpl_id = rept_tmpl_id;
	}
	/**
	 * @return Returns the rept_tmpl_nm.
	 */
	public String getRept_tmpl_nm() {
		return rept_tmpl_nm;
	}
	/**
	 * @param rept_tmpl_nm The rept_tmpl_nm to set.
	 */
	public void setRept_tmpl_nm(String rept_tmpl_nm) {
		this.rept_tmpl_nm = rept_tmpl_nm;
	}
	/**
	 * @return Returns the rownum.
	 */
	public String getRownum() {
		return rownum;
	}
	/**
	 * @param rownum The rownum to set.
	 */
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return Returns the rept_tmpl_file.
	 */
	public FormFile getRept_tmpl_file() {
		return rept_tmpl_file;
	}
	/**
	 * @param rept_tmpl_file The rept_tmpl_file to set.
	 */
	public void setRept_tmpl_file(FormFile rept_tmpl_file) {
		this.rept_tmpl_file = rept_tmpl_file;
	}
	
	public String getRept_prd_str() {
		if(rept_prd != null && rept_prd.equals("D")) {
			return "일간";
		} else if(rept_prd != null && rept_prd.equals("W")) {
			return "주간";
		} else if(rept_prd != null && rept_prd.equals("M")) {
			return "월간";
		} else if(rept_prd != null && rept_prd.equals("R")) {
			return "임의";
		}
		return "";
	}
}
