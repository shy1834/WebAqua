/*
 * Created on 2003. 10. 6.
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
public class ServiceTable_DTO {

	private String platform_code;
	private String table_id;
	private String table_name;
	/**
	 * 
	 */
	public ServiceTable_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getTable_id() {
		return table_id;
	}

	/**
	 * @return
	 */
	public String getTable_name() {
		return table_name;
	}

	/**
	 * @param string
	 */
	public void setTable_id(String string) {
		table_id = string;
	}

	/**
	 * @param string
	 */
	public void setTable_name(String string) {
		table_name = string;
	}

	/**
	 * @return
	 */
	public String getPlatform_code() {
		return platform_code;
	}

	/**
	 * @param string
	 */
	public void setPlatform_code(String string) {
		platform_code = string;
	}

}
