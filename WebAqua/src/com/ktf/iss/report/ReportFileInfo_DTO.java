/*
 * Created on 2005. 1. 18.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.iss.report;

/**
 * @author hyunyun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportFileInfo_DTO {
	private String rept_id;
	private String rept_tmpl_id;
	private String rept_tmpl_nm;
	private String rept_nm;
	private String reg_dt;
	private String rept_file_nm;
	private String rownum;
	
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
	 * @return Returns the reg_dt.
	 */
	public String getReg_dt() {
		return reg_dt;
	}
	/**
	 * @param reg_dt The reg_dt to set.
	 */
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt.substring(0, 4) + "/" + reg_dt.substring(4, 6) + "/" + reg_dt.substring(6, 8)
					+ " " + reg_dt.substring(8, 10) + ":" + reg_dt.substring(10, 12) + ":" + reg_dt.substring(12);   
		//this.reg_dt = reg_dt;
	}
	/**
	 * @return Returns the rept_file_nm.
	 */
	public String getRept_file_nm() {
		return rept_file_nm;
	}
	/**
	 * @param rept_file_nm The rept_file_nm to set.
	 */
	public void setRept_file_nm(String rept_file_nm) {
		this.rept_file_nm = rept_file_nm;
	}
	/**
	 * @return Returns the rept_id.
	 */
	public String getRept_id() {
		return rept_id;
	}
	/**
	 * @param rept_id The rept_id to set.
	 */
	public void setRept_id(String rept_id) {
		this.rept_id = rept_id;
	}
	/**
	 * @return Returns the rept_nm.
	 */
	public String getRept_nm() {
		return rept_nm;
	}
	/**
	 * @param rept_nm The rept_nm to set.
	 */
	public void setRept_nm(String rept_nm) {
		this.rept_nm = rept_nm;
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
}
