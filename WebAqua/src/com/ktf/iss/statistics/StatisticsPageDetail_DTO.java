/*
 * Created on 2003. 10. 15.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.util.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsPageDetail_DTO  implements Comparator {

	private String page_id;
	private String col_id;
	private String col_name;
	private String data_type;
	private String code_category;
	private String field_nm;
	private String table_vsb;
	private String graph_vsb;
	private String user_defined_info;
	private String user_defined_info_yn;
	private String group_field;
	private String group_func;
	private String reference_field;
	private String seq;
	private String format;
	private String alias;
	private String len;
	private String special_type;
	private String order_seq;
	private String orderby;
	private String is_compare;
	private String qm_value;
	
	/**
	 * 
	 */
	public StatisticsPageDetail_DTO() {
		super();
		page_id = "";
		col_id = "";
		col_name = "";
		data_type = "";
		code_category = "";
		field_nm = "";
		table_vsb = "";
		graph_vsb = "";
		user_defined_info = "";
		user_defined_info_yn = "";
		group_field = "";
		group_func = "";
		reference_field = "";
		seq = "";
		format = "";
		order_seq = "";
		orderby = "";
		is_compare = "N";
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
	public String getField_nm() {
		return field_nm;
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
	public String getGraph_vsb() {
		return graph_vsb;
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
	public String getReference_field() {
		return reference_field;
	}

	/**
	 * @return
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @return
	 */
	public String getTable_vsb() {
		return table_vsb;
	}

	/**
	 * @return
	 */
	public String getUser_defined_info() {
		return user_defined_info;
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
	public void setField_nm(String string) {
		field_nm = string;
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
	public void setGraph_vsb(String string) {
		graph_vsb = string;
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
	public void setReference_field(String string) {
		reference_field = string;
	}

	/**
	 * @param i
	 */
	public void setSeq(String i) {
		seq = i;
	}

	/**
	 * @param string
	 */
	public void setTable_vsb(String string) {
		table_vsb = string;
	}

	/**
	 * @param string
	 */
	public void setUser_defined_info(String string) {
		user_defined_info = string;
	}

	/**
	 * @return
	 */
	public String getUser_defined_info_yn() {
		return user_defined_info_yn;
	}

	/**
	 * @param string
	 */
	public void setUser_defined_info_yn(String string) {
		user_defined_info_yn = string;
	}

	public int compare(Object arg1,Object arg2) {
		StatisticsPageDetail_DTO spdDTO1 = (StatisticsPageDetail_DTO)arg1;
		StatisticsPageDetail_DTO spdDTO2 = (StatisticsPageDetail_DTO)arg2;
		try {
			int index1 = Integer.parseInt(spdDTO1.getSeq());
			int index2 = Integer.parseInt(spdDTO2.getSeq());
			if(index1 < index2) {
				return -1;
			} else if(index1 == index2) {
				return 0;
			} else if(index1 > index2) {
				return 1;
			}
		} catch(Exception e) {}
		
		return 0;
	}
	
	public boolean equals(Object obj) {
		StatisticsPageDetail_DTO spdDTO1 = (StatisticsPageDetail_DTO)obj;
		try {
			int index1 = Integer.parseInt(spdDTO1.getSeq());
			int index2 = Integer.parseInt(this.getSeq());
			if(index1 == index2) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {}
		
		return false;
	}

	/**
	 * @return
	 */
	public String getCol_name() {
		return col_name;
	}

	/**
	 * @param string
	 */
	public void setCol_name(String string) {
		col_name = string;
	}

	/**
	 * @return
	 */
	public String getGroup_field() {
		return group_field;
	}

	/**
	 * @return
	 */
	public String getGroup_func() {
		return group_func;
	}

	/**
	 * @param string
	 */
	public void setGroup_field(String string) {
		group_field = string;
	}

	/**
	 * @param string
	 */
	public void setGroup_func(String string) {
		group_func = string;
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
	public String getData_type() {
		return data_type;
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
	public void setData_type(String string) {
		data_type = string;
	}

	/**
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param string
	 */
	public void setAlias(String string) {
		alias = string;
	}

	/**
	 * @return
	 */
	public String getLen() {
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
	public void setLen(String string) {
		len = string;
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
	public String getOrder_seq() {
		return order_seq;
	}

	/**
	 * @param string
	 */
	public void setOrder_seq(String string) {
		order_seq = string;
	}

	/**
	 * @return
	 */
	public String getOrderby() {
		return orderby;
	}

	/**
	 * @param string
	 */
	public void setOrderby(String string) {
		orderby = string;
	}

	/**
	 * @return
	 */
	public String getIs_compare() {
		return is_compare;
	}

	/**
	 * @param string
	 */
	public void setIs_compare(String string) {
		is_compare = string;
	}

	/**
	 * @return
	 */
	public String getQm_value() {
		return qm_value;
	}

	/**
	 * @param string
	 */
	public void setQm_value(String string) {
		qm_value = string;
	}

}
