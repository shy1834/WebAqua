/*
 * Created on 2003. 10. 9.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsColumn_DTO {

	private String col_id;
	private String col_name;
	private String alias;
	private int len;
	private int data_type;
	private String format;
	private String special_type;
	private String code_category;
	private String formatString;
	
	private int rnum; //030105 Ãß°¡
	/**
	 * 
	 */
	public StatisticsColumn_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @return
	 */
	public String getCode_category() {
		return code_category;
	}

	/**
	 * @return
	 */
	public String getCol_id() {
		return col_id;
	}

	/**
	 * @return
	 */
	public String getCol_name() {
		return col_name;
	}

	/**
	 * @return
	 */
	public int getData_type() {
		return data_type;
	}

	/**
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return
	 */
	public int getLen() {
		return len;
	}

	/**
	 * @return
	 */
	public String getSpecial_type() {
		return special_type;
	}

	/**
	 * @param string
	 */
	public void setAlias(String string) {
		alias = string;
	}

	/**
	 * @param string
	 */
	public void setCode_category(String string) {
		code_category = string;
	}

	/**
	 * @param string
	 */
	public void setCol_id(String string) {
		col_id = string;
	}

	/**
	 * @param string
	 */
	public void setCol_name(String string) {
		col_name = string;
	}

	/**
	 * @param string
	 */
	public void setData_type(int i) {
		data_type = i;
	}

	/**
	 * @param string
	 */
	public void setFormat(String string) {
		format = string;
	}

	/**
	 * @param string
	 */
	public void setLen(int i) {
		len = i;
	}

	/**
	 * @param string
	 */
	public void setSpecial_type(String string) {
		special_type = string;
	}

	/**
	 * @return
	 */
	public String getFormatString() {
		return formatString;
	}

	/**
	 * @param string
	 */
	public void setFormatString(String string) {
		formatString = string;
	}

	/**
	 * @return
	 */
	public int getRnum() {
		return rnum;
	}

	/**
	 * @param i
	 */
	public void setRnum(int i) {
		rnum = i;
	}

}
