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
public class StatisticsType_DTO {

	private String stat_tp_id;
	private String stat_tp_nm;
	private String admin_url;
	private String template;
	private String stat_tp_desc;
	
	/**
	 * 
	 */
	public StatisticsType_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getAdmin_url() {
		return admin_url;
	}

	/**
	 * @return
	 */
	public String getStat_tp_desc() {
		return stat_tp_desc;
	}

	/**
	 * @return
	 */
	public String getStat_tp_id() {
		return stat_tp_id;
	}

	/**
	 * @return
	 */
	public String getStat_tp_nm() {
		return stat_tp_nm;
	}

	/**
	 * @return
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param string
	 */
	public void setAdmin_url(String string) {
		admin_url = string;
	}

	/**
	 * @param string
	 */
	public void setStat_tp_desc(String string) {
		stat_tp_desc = string;
	}

	/**
	 * @param string
	 */
	public void setStat_tp_id(String string) {
		stat_tp_id = string;
	}

	/**
	 * @param string
	 */
	public void setStat_tp_nm(String string) {
		stat_tp_nm = string;
	}

	/**
	 * @param string
	 */
	public void setTemplate(String string) {
		template = string;
	}

}
