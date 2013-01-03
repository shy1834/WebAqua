package com.ktf.aqua.mgr.menu.form;

import org.apache.struts.action.*;

public class ParamForm extends ActionForm{
	
	private String menuType = null;
	private String menu1 = null;
	private String menu2 = null;
	
	private String userAuthType = null;
 	private String UserType = null;
 	private String cpId = null; 
 	private String cdma_dev = null;
 	private String dayTypeForm = null;
 	private String client_val = null;
 	private String server_val = null;
 	
 	public String getClient_val() {
		return client_val;
	}
	public void setClient_val(String clientVal) {
		client_val = clientVal;
	}
	public String getServer_val() {
		return server_val;
	}
	public void setServer_val(String serverVal) {
		server_val = serverVal;
	}
	public String getDayTypeForm() {
		return dayTypeForm;
	}
	public void setDayTypeForm(String dayTypeForm) {
		this.dayTypeForm = dayTypeForm;
	}
	public String getMenu1() {
		return menu1;
	}
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}
	public String getMenu2() {
		return menu2;
	}
	
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	public String getCdma_dev() {
		return cdma_dev;
	}
	public void setCdma_dev(String cdma_dev) {
		// 초기값 4 : 전체
		if( cdma_dev == null ) cdma_dev = "4";
		this.cdma_dev = cdma_dev;
	}

	private String menuId = null;	
	private String plf_dev = null;		

	private String menu_dev = null;
	private String svc_dev = null;
	private String svc_dev_1 = null;
	private String data_dev = null;
	private String data_dev_val = null;
	
	
	private String dayType = null;
	private String from_date = null;
	private String to_date = null;
	private String from_time = null;
	private String to_time = null;
	private String url_val = null;
	private String srh_cnt = null;
	private String svc_sort = null;
	private String sort_dev = null;
	private String p_name = null;
	private String svc_name = null;
	private String type = null;
	private String plf_name = null;
	private String log_type = null;
	private String path = null;
	private String select_type = null;
	private String error_code = null;
	private String tcs_num_val = null;
	private String min_type = null;
	private String min_val = null;
	private String input_val = null;
	private String select_list = null;
	private String input_val1 = null;
	private String select_list1 = null;
	private String from_low = null;
	private String to_high = null;
	private String sFlag = null;
	private String tt = null;
	
		
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}
	public String getUserAuthType() {
		return userAuthType;
	}
	public void setUserAuthType(String userAuthType) {
		this.userAuthType = userAuthType;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getPlf_dev() {
		if( plf_dev == null) this.plf_dev = "0";
		return plf_dev;
	}
	public void setPlf_dev(String plf_dev) {
		this.plf_dev = plf_dev;
	}
	public String getMenu_dev() {
		return menu_dev;
	}
	public void setMenu_dev(String menu_dev) {
		this.menu_dev = menu_dev;
	}
	public String getSvc_dev() {
		return svc_dev;
	}
	public void setSvc_dev(String svc_dev) {
		this.svc_dev = svc_dev;
	}
	public String getSvc_dev_1() {
		return svc_dev_1;
	}
	public void setSvc_dev_1(String svc_dev_1) {
		this.svc_dev_1 = svc_dev_1;
	}
	public String getData_dev() {
		return data_dev;
	}
	public void setData_dev(String data_dev) {
		this.data_dev = data_dev;
	}
	public String getDayType() {
		if(dayType == null )dayType = "2";
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getTo_time() {
		return to_time;
	}
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	public String getData_dev_val() {
		return data_dev_val;
	}
	public void setData_dev_val(String data_dev_val) {
		this.data_dev_val = data_dev_val;
	}
	public String getUrl_val() {
		return url_val;
	}
	public void setUrl_val(String url_val) {
		this.url_val = url_val;
	}
	public String getSrh_cnt() {
		if( srh_cnt == null ) return "100";
		return srh_cnt;
	}
	public void setSrh_cnt(String srh_cnt) {
		this.srh_cnt = srh_cnt;
	}
	public String getSvc_sort() {
		return svc_sort;
	}
	public void setSvc_sort(String svc_sort) {
		this.svc_sort = svc_sort;
	}
	public String getSort_dev() {
		if(sort_dev == null)sort_dev = "1";
		return sort_dev;
	}
	public void setSort_dev(String sort_dev) {
		this.sort_dev = sort_dev;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getSvc_name() {
		return svc_name;
	}
	public void setSvc_name(String svc_name) {
		this.svc_name = svc_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlf_name() {
		return plf_name;
	}
	public void setPlf_name(String plf_name) {
		this.plf_name = plf_name;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSelect_type() {
		return select_type;
	}
	public void setSelect_type(String select_type) {
		this.select_type = select_type;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getTcs_num_val() {
		return tcs_num_val;
	}
	public void setTcs_num_val(String tcs_num_val) {
		this.tcs_num_val = tcs_num_val;
	}
	public String getMin_type() {
		return min_type;
	}
	public void setMin_type(String min_type) {
		this.min_type = min_type;
	}
	public String getMin_val() {
		return min_val;
	}
	public void setMin_val(String min_val) {
		this.min_val = min_val;
	}
	public String getInput_val() {
		return input_val;
	}
	public void setInput_val(String input_val) {
		this.input_val = input_val;
	}
	public String getSelect_list() {
		return select_list;
	}
	public void setSelect_list(String select_list) {
		this.select_list = select_list;
	}
	public String getInput_val1() {
		return input_val1;
	}
	public void setInput_val1(String input_val1) {
		this.input_val1 = input_val1;
	}
	public String getSelect_list1() {
		return select_list1;
	}
	public void setSelect_list1(String select_list1) {
		this.select_list1 = select_list1;
	}
	public String getFrom_low() {
		return from_low;
	}
	public void setFrom_low(String from_low) {
		this.from_low = from_low;
	}
	public String getTo_high() {
		return to_high;
	}
	public void setTo_high(String to_high) {
		this.to_high = to_high;
	}
	public String getsFlag() {
		return sFlag;
	}
	public void setsFlag(String sFlag) {
		this.sFlag = sFlag;
	}
}
