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
public class StatisticsPageCondition_DTO implements Comparator {

	private String page_id;
	private String cndtn_col_id;
	private String cndtn_col_name;
	private String cndtn_condition;
	private String cndtn_value;
	private String cndtn_usr_inpt;
	private String cndtn_index;
	private String cndtn_code_category;
	private ArrayList codeList;
	private String cndtn_is_compare;
	private String cndtn_field_nm;
	
	/**
	 * 
	 */
	public StatisticsPageCondition_DTO() {
		super();
		page_id = "";
		cndtn_col_id = "";
		cndtn_col_name = "";
		cndtn_condition = "";
		cndtn_value = "";
		cndtn_usr_inpt = "";
		cndtn_index = "";
		cndtn_code_category = "";
		cndtn_is_compare = "N";
		cndtn_field_nm = "";
	}

	/**
	 * @return
	 */
	public String getCndtn_col_id() {
		return cndtn_col_id;
	}

	/**
	 * @return
	 */
	public String getCndtn_condition() {
		return cndtn_condition;
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
	public String getCndtn_index() {
		return cndtn_index;
	}

	/**
	 * @return
	 */
	public String getCndtn_usr_inpt() {
		return cndtn_usr_inpt;
	}

	/**
	 * @return
	 */
	public String getCndtn_value() {
		return cndtn_value;
	}

	/**
	 * @param string
	 */
	public void setCndtn_col_id(String string) {
		cndtn_col_id = string;
	}

	/**
	 * @param string
	 */
	public void setCndtn_condition(String string) {
		cndtn_condition = string;
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
	public void setCndtn_index(String string) {
		cndtn_index = string;
	}

	/**
	 * @param string
	 */
	public void setCndtn_usr_inpt(String string) {
		cndtn_usr_inpt = string;
	}

	/**
	 * @param string
	 */
	public void setCndtn_value(String string) {
		cndtn_value = string;
	}

	public int compare(Object arg1,Object arg2) {
		StatisticsPageCondition_DTO spcDTO1 = (StatisticsPageCondition_DTO)arg1;
		StatisticsPageCondition_DTO spcDTO2 = (StatisticsPageCondition_DTO)arg2;
		try {
			int index1 = Integer.parseInt(spcDTO1.getCndtn_index());
			int index2 = Integer.parseInt(spcDTO2.getCndtn_index());
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
		StatisticsPageCondition_DTO spcDTO1 = (StatisticsPageCondition_DTO)obj;
		try {
			int index1 = Integer.parseInt(spcDTO1.getCndtn_index());
			int index2 = Integer.parseInt(this.getCndtn_index());
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
	public String getCndtn_code_category() {
		return cndtn_code_category;
	}

	/**
	 * @return
	 */
	public ArrayList getCodeList() {
		return codeList;
	}

	/**
	 * @param string
	 */
	public void setCndtn_code_category(String string) {
		cndtn_code_category = string;
	}

	/**
	 * @param list
	 */
	public void setCodeList(ArrayList list) {
		codeList = list;
	}
	/**
	 * @return
	 */
	public String getCndtn_col_name() {
		return cndtn_col_name;
	}

	/**
	 * @param string
	 */
	public void setCndtn_col_name(String string) {
		cndtn_col_name = string;
	}

	/**
	 * @return
	 */
	public String getCndtn_is_compare() {
		return cndtn_is_compare;
	}

	/**
	 * @param string
	 */
	public void setCndtn_is_compare(String string) {
		cndtn_is_compare = string;
	}

	/**
	 * @return
	 */
	public String getCndtn_field_nm() {
		return cndtn_field_nm;
	}

	/**
	 * @param string
	 */
	public void setCndtn_field_nm(String string) {
		cndtn_field_nm = string;
	}

}
