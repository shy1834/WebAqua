/*
 * Created on 2005. 1. 20.
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
public class TableInfo_DTO {
	
	private String table_id;
	private String table_name;
	private String alias;
	private String aggr_result_type;
	
	/**
	 * @return Returns the aggr_result_type.
	 */
	public String getAggr_result_type() {
		return aggr_result_type;
	}
	/**
	 * @param aggr_result_type The aggr_result_type to set.
	 */
	public void setAggr_result_type(String aggr_result_type) {
		this.aggr_result_type = aggr_result_type;
	}
	/**
	 * @return Returns the alias.
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias The alias to set.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return Returns the table_id.
	 */
	public String getTable_id() {
		return table_id;
	}
	/**
	 * @param table_id The table_id to set.
	 */
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	/**
	 * @return Returns the table_name.
	 */
	public String getTable_name() {
		return table_name;
	}
	/**
	 * @param table_name The table_name to set.
	 */
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
}
