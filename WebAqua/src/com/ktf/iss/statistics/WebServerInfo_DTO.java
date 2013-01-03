/*
 * Created on 2004. 2. 21.
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
public class WebServerInfo_DTO {


	private String hostname;
	private String ip;
	private int port;
	private String login_id;
	private String login_pwd;
	private String svc_dir;
	private String svc_type;
	private String is_enable;
	
	/**
	 * 
	 */
	public WebServerInfo_DTO() {
		super();
	}

	/**
	 * @return
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return
	 */
	public String getIs_enable() {
		return is_enable;
	}

	/**
	 * @return
	 */
	public String getLogin_id() {
		return login_id;
	}

	/**
	 * @return
	 */
	public String getLogin_pwd() {
		return login_pwd;
	}

	/**
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return
	 */
	public String getSvc_dir() {
		return svc_dir;
	}

	/**
	 * @return
	 */
	public String getSvc_type() {
		return svc_type;
	}

	/**
	 * @param string
	 */
	public void setHostname(String string) {
		hostname = string;
	}

	/**
	 * @param string
	 */
	public void setIp(String string) {
		ip = string;
	}

	/**
	 * @param string
	 */
	public void setIs_enable(String string) {
		is_enable = string;
	}

	/**
	 * @param string
	 */
	public void setLogin_id(String string) {
		login_id = string;
	}

	/**
	 * @param string
	 */
	public void setLogin_pwd(String string) {
		login_pwd = string;
	}

	/**
	 * @param i
	 */
	public void setPort(int i) {
		port = i;
	}

	/**
	 * @param string
	 */
	public void setSvc_dir(String string) {
		svc_dir = string;
	}

	/**
	 * @param string
	 */
	public void setSvc_type(String string) {
		svc_type = string;
	}

}
