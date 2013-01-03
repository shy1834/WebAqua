/*
 * Created on 2003. 10. 14.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.config;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Code_DTO {

	private String code_category;
	private String code;
	private String name;
	private String code_type;
	private String code_source;
	private String desc;
	private int    rownum;
	/**
	 * 
	 */
	public Code_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
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
	public String getCode_source() {
		return code_source;
	}

	/**
	 * @return
	 */
	public String getCode_type() {
		return code_type;
	}

	/**
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setCode(String string) {
		code = string;
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
	public void setCode_source(String string) {
		code_source = string;
	}

	/**
	 * @param string
	 */
	public void setCode_type(String string) {
		code_type = string;
	}

	/**
	 * @param string
	 */
	public void setDesc(String string) {
		desc = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	public String getLabel() {
		return name;
	}
	
	public String getValue() {
		return code;
	}
	/**
	 * @return
	 */
	public int getRownum() {
		return rownum;
	}

	/**
	 * @param i
	 */
	public void setRownum(int i) {
		rownum = i;
	}

}
