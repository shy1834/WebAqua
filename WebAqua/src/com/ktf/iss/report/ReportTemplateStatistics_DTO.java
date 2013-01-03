/*
 * Created on 2005. 1. 14.
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
public class ReportTemplateStatistics_DTO {
	private String rept_stat_id;
	private String rept_tmpl_id;
	private String start_row;
	private String end_row;
	private String start_col;
	private String end_col;
	private String data_type;
	private String sql;
	private String sheet_no;
	
	/**
	 * @return Returns the sheet_no.
	 */
	public String getSheet_no() {
		return sheet_no;
	}
	/**
	 * @param sheet_no The sheet_no to set.
	 */
	public void setSheet_no(String sheet_no) {
		this.sheet_no = sheet_no;
	}
	/**
	 * @return Returns the data_types.
	 */
	public String getData_type() {
		return data_type;
	}
	/**
	 * @param data_types The data_types to set.
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	/**
	 * @return Returns the end_col.
	 */
	public String getEnd_col() {
		return end_col;
	}
	/**
	 * @param end_col The end_col to set.
	 */
	public void setEnd_col(String end_col) {
		this.end_col = end_col;
	}
	/**
	 * @return Returns the end_row.
	 */
	public String getEnd_row() {
		return end_row;
	}
	/**
	 * @param end_row The end_row to set.
	 */
	public void setEnd_row(String end_row) {
		this.end_row = end_row;
	}
	/**
	 * @return Returns the rept_stat_id.
	 */
	public String getRept_stat_id() {
		return rept_stat_id;
	}
	/**
	 * @param rept_stat_id The rept_stat_id to set.
	 */
	public void setRept_stat_id(String rept_stat_id) {
		this.rept_stat_id = rept_stat_id;
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
	 * @return Returns the sql.
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * @param sql The sql to set.
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * @return Returns the start_col.
	 */
	public String getStart_col() {
		return start_col;
	}
	/**
	 * @param start_col The start_col to set.
	 */
	public void setStart_col(String start_col) {
		this.start_col = start_col;
	}
	/**
	 * @return Returns the start_row.
	 */
	public String getStart_row() {
		return start_row;
	}
	/**
	 * @param start_row The start_row to set.
	 */
	public void setStart_row(String start_row) {
		this.start_row = start_row;
	}
}
