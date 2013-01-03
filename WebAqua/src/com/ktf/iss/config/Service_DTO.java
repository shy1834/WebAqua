/*
 * Created on 2003. 10. 8.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.config;

import java.sql.Date;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Service_DTO {
	
	private String svc_id;
	private String name;
	private String platform;
	private String platform_name;
	private long svc_code;
	private String cp_code;
	private Date open_date;
	private String in_out_svc;
	private String svc_url;
	private String bill_status;
	private boolean is_editable;
	private String svc_desc;
	
	/**
	 * 
	 */
	public Service_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getBill_status() {
		return bill_status;
	}

	/**
	 * @return
	 */
	public String getCp_code() {
		return cp_code;
	}

	/**
	 * @return
	 */
	public String getIn_out_svc() {
		return in_out_svc;
	}

	/**
	 * @return
	 */
	public boolean isIs_editable() {
		return is_editable;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Date getOpen_date() {
		return open_date;
	}

	/**
	 * @return
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @return
	 */
	public long getSvc_code() {
		return svc_code;
	}

	/**
	 * @return
	 */
	public String getSvc_desc() {
		return svc_desc;
	}

	/**
	 * @return
	 */
	public String getSvc_id() {
		return svc_id;
	}

	/**
	 * @return
	 */
	public String getSvc_url() {
		return svc_url;
	}

	/**
	 * @param string
	 */
	public void setBill_status(String string) {
		bill_status = string;
	}

	/**
	 * @param string
	 */
	public void setCp_code(String string) {
		cp_code = string;
	}

	/**
	 * @param string
	 */
	public void setIn_out_svc(String string) {
		in_out_svc = string;
	}

	/**
	 * @param b
	 */
	public void setIs_editable(boolean b) {
		is_editable = b;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param date
	 */
	public void setOpen_date(Date date) {
		open_date = date;
	}

	/**
	 * @param string
	 */
	public void setPlatform(String string) {
		platform = string;
	}

	/**
	 * @param l
	 */
	public void setSvc_code(long l) {
		svc_code = l;
	}

	/**
	 * @param string
	 */
	public void setSvc_desc(String string) {
		svc_desc = string;
	}

	/**
	 * @param l
	 */
	public void setSvc_id(String l) {
		svc_id = l;
	}

	/**
	 * @param string
	 */
	public void setSvc_url(String string) {
		svc_url = string;
	}


	public String getLabel() {
		return name;
	}
	
	public String getValue() {
		return String.valueOf(svc_id);
	}

	/**
	 * @return
	 */
	public String getPlatform_name() {
		return platform_name;
	}

	/**
	 * @param string
	 */
	public void setPlatform_name(String string) {
		platform_name = string;
	}

}
