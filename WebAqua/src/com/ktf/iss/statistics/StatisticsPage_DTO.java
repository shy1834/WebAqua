/*
 * Created on 2003. 10. 2.
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
public class StatisticsPage_DTO {

	private int rownum;
	private String page_id;
	private String page_nm;
	private String stat_tp;
	private String dt_tp;
	private String dt_tp_usr_input;
	private String dt_col_id;
	private String dt_col_name;
	private String dt_usr_input;
	private String inqr_begin_dt;
	private String inqr_end_dt;
	private String table_id;
	private String page_loc;
	private String page_desc;
	private String svcorg_depth;
	private int max_cnt;
	private String stat_tp_nm;
	private String table_name;
	private String prev_comp;
	private String page_user;
	private String[] menu_group;
	private boolean isRegistered;
	private String query1;
	private String query2;
	private String query3;
	private String query4;
	private String query5;
	private String query6;
	private String query7;
	private String query8;
	private String query9;
	private String query10;
	private String max_cnt_usr_input;
	private String platform;
	private String svcorg_usr_input;
	private String detail_info;
	private String svc_path;
	private String graph_max_cnt;
	private String sort_usr_input;
	private String table_hide;
	private String graph_height;
	private String refresh_flag;
	private String refresh_time;
	private String ref_flag;
	private String ref_dt;
	private String past_dt_tp;
	private String past_dt;
	
	private String static_scale_y1;
	private String min_value_y1;
	private String max_value_y1;
	private String static_scale_y2;
	private String min_value_y2;
	private String max_value_y2;
	private String horizontal_grid;
	private String vertical_grid;
	private String view_3D;
	private String palette;

	/**
	 * 
	 */
	public StatisticsPage_DTO() {
		super();
		page_id = "";
		page_nm = "";
		stat_tp = "";
		dt_tp = "";
		dt_tp_usr_input = "";
		dt_col_id = "";
		dt_col_name = "";
		dt_usr_input = "";
		inqr_begin_dt = "";
		inqr_end_dt = "";
		table_id = "";
		page_loc = "";
		page_desc = "";
		svcorg_depth = "";
		stat_tp_nm = "";
		table_name = "";
		prev_comp = "";
		isRegistered = false;
		query1 = "";
		query2 = "";
		query3 = "";
		svcorg_usr_input = "";
		
		static_scale_y1 = "";
		min_value_y1 = "";
		max_value_y1 = "";
		static_scale_y2 = "";
		min_value_y2 = "";
		max_value_y2 = "";
		horizontal_grid = "";
		vertical_grid = "";
		view_3D = "";
		palette = "Default";
	}

	/**
	 * @return
	 */
	public String getDt_tp() {
		return dt_tp;
	}

	/**
	 * @return
	 */
	public String getDt_usr_input() {
		return dt_usr_input;
	}

	/**
	 * @return
	 */
	public String getInqr_begin_dt() {
		return inqr_begin_dt;
	}

	/**
	 * @return
	 */
	public String getInqr_end_dt() {
		return inqr_end_dt;
	}

	/**
	 * @return
	 */
	public int getMax_cnt() {
		return max_cnt;
	}

	/**
	 * @return
	 */
	public String getPage_desc() {
		return page_desc;
	}

	/**
	 * @return
	 */
	public String getPage_id() {
		return page_id;
	}

	/**
	 * @return
	 */
	public String getPage_nm() {
		return page_nm;
	}

	/**
	 * @return
	 */
	public String getPage_loc() {
		return page_loc;
	}

	/**
	 * @return
	 */
	public String getSvcorg_depth() {
		return svcorg_depth;
	}

	/**
	 * @return
	 */
	public String getStat_tp() {
		return stat_tp;
	}

	/**
	 * @return
	 */
	public String getTable_id() {
		return table_id;
	}

	/**
	 * @param string
	 */
	public void setDt_tp(String string) {
		dt_tp = string;
	}

	/**
	 * @param string
	 */
	public void setDt_usr_input(String string) {
		dt_usr_input = string;
	}

	/**
	 * @param string
	 */
	public void setInqr_begin_dt(String string) {
		inqr_begin_dt = string;
	}

	/**
	 * @param string
	 */
	public void setInqr_end_dt(String string) {
		inqr_end_dt = string;
	}

	/**
	 * @param i
	 */
	public void setMax_cnt(int i) {
		max_cnt = i;
	}

	/**
	 * @param string
	 */
	public void setPage_desc(String string) {
		page_desc = string;
	}

	/**
	 * @param string
	 */
	public void setPage_id(String string) {
		page_id = string;
	}

	/**
	 * @param string
	 */
	public void setPage_nm(String string) {
		page_nm = string;
	}

	/**
	 * @param string
	 */
	public void setPage_loc(String string) {
		page_loc = string;
	}

	/**
	 * @param string
	 */
	public void setSvcorg_depth(String string) {
		svcorg_depth = string;
	}

	/**
	 * @param string
	 */
	public void setStat_tp(String string) {
		stat_tp = string;
	}

	/**
	 * @param string
	 */
	public void setTable_id(String string) {
		table_id = string;
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
	public String getTable_name() {
		return table_name;
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
	public void setTable_name(String string) {
		table_name = string;
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

	/**
	 * @return
	 */
	public String getDt_col_id() {
		return dt_col_id;
	}

	/**
	 * @param string
	 */
	public void setDt_col_id(String string) {
		dt_col_id = string;
	}

	/**
	 * @return
	 */
	public String getDt_tp_usr_input() {
		return dt_tp_usr_input;
	}

	/**
	 * @param string
	 */
	public void setDt_tp_usr_input(String string) {
		dt_tp_usr_input = string;
	}

	/**
	 * @return
	 */
	public String getPrev_comp() {
		return prev_comp;
	}

	/**
	 * @param string
	 */
	public void setPrev_comp(String string) {
		prev_comp = string;
	}

	/**
	 * @return
	 */
	public String[] getMenu_group() {
		return menu_group;
	}

	/**
	 * @param strings
	 */
	public void setMenu_group(String[] strings) {
		menu_group = strings;
	}

	/**
	 * @return
	 */
	public String getPage_user() {
		return page_user;
	}

	/**
	 * @param string
	 */
	public void setPage_user(String string) {
		page_user = string;
	}

	/**
	 * @return
	 */
	public boolean isRegistered() {
		return isRegistered;
	}

	/**
	 * @param b
	 */
	public void setRegistered(boolean b) {
		isRegistered = b;
	}

	/**
	 * @return
	 */
	public String getQuery1() {
		return query1;
	}

	/**
	 * @return
	 */
	public String getQuery2() {
		return query2;
	}

	/**
	 * @param string
	 */
	public void setQuery1(String string) {
		query1 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery2(String string) {
		query2 = string;
	}

	/**
	 * @return
	 */
	public String getDt_col_name() {
		return dt_col_name;
	}

	/**
	 * @param string
	 */
	public void setDt_col_name(String string) {
		dt_col_name = string;
	}

	/**
	 * @return
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param string
	 */
	public void setPlatform(String string) {
		platform = string;
	}

	/**
	 * @return
	 */
	public String getMax_cnt_usr_input() {
		return max_cnt_usr_input;
	}

	/**
	 * @param string
	 */
	public void setMax_cnt_usr_input(String string) {
		max_cnt_usr_input = string;
	}

	/**
	 * @return
	 */
	public String getQuery3() {
		return query3;
	}

	/**
	 * @param string
	 */
	public void setQuery3(String string) {
		query3 = string;
	}

	/**
	 * @return
	 */
	public String getSvcorg_usr_input() {
		return svcorg_usr_input;
	}

	/**
	 * @param string
	 */
	public void setSvcorg_usr_input(String string) {
		svcorg_usr_input = string;
	}

	/**
	 * @return
	 */
	public String getDetail_info() {
		return detail_info;
	}

	/**
	 * @param string
	 */
	public void setDetail_info(String string) {
		detail_info = string;
	}

	/**
	 * @return
	 */
	public String getSvc_path() {
		return svc_path;
	}

	/**
	 * @param string
	 */
	public void setSvc_path(String string) {
		svc_path = string;
	}

	/**
	 * @return
	 */
	public String getGraph_height() {
		return graph_height;
	}

	/**
	 * @return
	 */
	public String getSort_usr_input() {
		return sort_usr_input;
	}

	/**
	 * @param string
	 */
	public void setGraph_height(String string) {
		graph_height = string;
	}

	/**
	 * @param string
	 */
	public void setSort_usr_input(String string) {
		sort_usr_input = string;
	}

	/**
	 * @return
	 */
	public String getGraph_max_cnt() {
		return graph_max_cnt;
	}

	/**
	 * @return
	 */
	public String getPast_dt() {
		return past_dt;
	}

	/**
	 * @return
	 */
	public String getPast_dt_tp() {
		return past_dt_tp;
	}

	/**
	 * @return
	 */
	public String getRef_dt() {
		return ref_dt;
	}

	/**
	 * @return
	 */
	public String getRef_flag() {
		return ref_flag;
	}

	/**
	 * @return
	 */
	public String getRefresh_flag() {
		return refresh_flag;
	}

	/**
	 * @return
	 */
	public String getRefresh_time() {
		return refresh_time;
	}

	/**
	 * @return
	 */
	public String getTable_hide() {
		return table_hide;
	}

	/**
	 * @param string
	 */
	public void setGraph_max_cnt(String string) {
		graph_max_cnt = string;
	}

	/**
	 * @param string
	 */
	public void setPast_dt(String string) {
		past_dt = string;
	}

	/**
	 * @param string
	 */
	public void setPast_dt_tp(String string) {
		past_dt_tp = string;
	}

	/**
	 * @param string
	 */
	public void setRef_dt(String string) {
		ref_dt = string;
	}

	/**
	 * @param string
	 */
	public void setRef_flag(String string) {
		ref_flag = string;
	}

	/**
	 * @param string
	 */
	public void setRefresh_flag(String string) {
		refresh_flag = string;
	}

	/**
	 * @param string
	 */
	public void setRefresh_time(String string) {
		refresh_time = string;
	}

	/**
	 * @param string
	 */
	public void setTable_hide(String string) {
		table_hide = string;
	}

	/**
	 * @return
	 */
	public String getQuery10() {
		return query10;
	}

	/**
	 * @return
	 */
	public String getQuery4() {
		return query4;
	}

	/**
	 * @return
	 */
	public String getQuery5() {
		return query5;
	}

	/**
	 * @return
	 */
	public String getQuery6() {
		return query6;
	}

	/**
	 * @return
	 */
	public String getQuery7() {
		return query7;
	}

	/**
	 * @return
	 */
	public String getQuery8() {
		return query8;
	}

	/**
	 * @return
	 */
	public String getQuery9() {
		return query9;
	}

	/**
	 * @param string
	 */
	public void setQuery10(String string) {
		query10 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery4(String string) {
		query4 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery5(String string) {
		query5 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery6(String string) {
		query6 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery7(String string) {
		query7 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery8(String string) {
		query8 = string;
	}

	/**
	 * @param string
	 */
	public void setQuery9(String string) {
		query9 = string;
	}

	/**
	 * @return
	 */
	public String getHorizontal_grid() {
		return horizontal_grid;
	}


	/**
	 * @return
	 */
	public String getVertical_grid() {
		return vertical_grid;
	}

	/**
	 * @return
	 */
	public String getView_3D() {
		return view_3D;
	}

	/**
	 * @param string
	 */
	public void setHorizontal_grid(String string) {
		horizontal_grid = string;
	}


	/**
	 * @param string
	 */
	public void setVertical_grid(String string) {
		vertical_grid = string;
	}

	/**
	 * @param string
	 */
	public void setView_3D(String string) {
		view_3D = string;
	}

	/**
	 * @return
	 */
	public String getMax_value_y1() {
		return max_value_y1;
	}

	/**
	 * @return
	 */
	public String getMax_value_y2() {
		return max_value_y2;
	}

	/**
	 * @return
	 */
	public String getMin_value_y1() {
		return min_value_y1;
	}

	/**
	 * @return
	 */
	public String getMin_value_y2() {
		return min_value_y2;
	}

	/**
	 * @return
	 */
	public String getStatic_scale_y1() {
		return static_scale_y1;
	}

	/**
	 * @return
	 */
	public String getStatic_scale_y2() {
		return static_scale_y2;
	}

	/**
	 * @param string
	 */
	public void setMax_value_y1(String string) {
		max_value_y1 = string;
	}

	/**
	 * @param string
	 */
	public void setMax_value_y2(String string) {
		max_value_y2 = string;
	}

	/**
	 * @param string
	 */
	public void setMin_value_y1(String string) {
		min_value_y1 = string;
	}

	/**
	 * @param string
	 */
	public void setMin_value_y2(String string) {
		min_value_y2 = string;
	}

	/**
	 * @param string
	 */
	public void setStatic_scale_y1(String string) {
		static_scale_y1 = string;
	}

	/**
	 * @param string
	 */
	public void setStatic_scale_y2(String string) {
		static_scale_y2 = string;
	}

	/**
	 * @return
	 */
	public String getPalette() {
		return palette;
	}

	/**
	 * @param string
	 */
	public void setPalette(String string) {
		palette = string;
	}

}
