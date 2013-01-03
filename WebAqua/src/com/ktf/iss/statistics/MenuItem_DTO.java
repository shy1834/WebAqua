/*
 * Created on 2003-10-06
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
public class MenuItem_DTO implements Comparator {
	private String menu_item_id;
	private String menu_item_nm;
	private String menu_item_tp;
	private int    menu_item_level;
	private String menu_group_id;
	private String menu_subgroup_id;
	private String page_id;
	private String menu_item_img_url;
	private long   menu_item_perm;
	private int    menu_item_seq;
	private int    menu_subgroup_seq;
	private String menu_item_url;
	private String menu_item_vsb;
	private String page_nm;
	private String page_user;
	private String table_id;
	private String menu_group_nm;
	private String menu_subgroup_nm;
	private String menu_group_init_url;
	private String query1;
	private String query2;
	private String query3;

	/**
	 * @return
	 */
	public String getMenu_group_id() {
		return menu_group_id;
	}

	/**
	 * @return
	 */
	public String getMenu_item_id() {
		return menu_item_id;
	}

	/**
	 * @return
	 */
	public String getMenu_item_img_url() {
		return menu_item_img_url;
	}

	/**
	 * @return
	 */
	public int getMenu_item_level() {
		return menu_item_level;
	}

	/**
	 * @return
	 */
	public long getMenu_item_perm() {
		return menu_item_perm;
	}

	/**
	 * @return
	 */
	public int getMenu_item_seq() {
		return menu_item_seq;
	}

	/**
	 * @return
	 */
	public String getMenu_item_tp() {
		return menu_item_tp;
	}

	/**
	 * @return
	 */
	public String getMenu_item_url() {
		return menu_item_url;
	}

	/**
	 * @return
	 */
	public String getMenu_item_vsb() {
		return menu_item_vsb;
	}

	/**
	 * @return
	 */
	public String getMenu_item_nm() {
		return menu_item_nm;
	}

	/**
	 * @return
	 */
	public String getMenu_subgroup_id() {
		return menu_subgroup_id;
	}

	/**
	 * @return
	 */
	public String getPage_id() {
		return page_id;
	}

	/**
	 * @param string
	 */
	public void setMenu_group_id(String string) {
		menu_group_id = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_id(String string) {
		menu_item_id = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_img_url(String string) {
		menu_item_img_url = string;
	}

	/**
	 * @param i
	 */
	public void setMenu_item_level(int i) {
		menu_item_level = i;
	}

	/**
	 * @param l
	 */
	public void setMenu_item_perm(long l) {
		menu_item_perm = l;
	}

	/**
	 * @param i
	 */
	public void setMenu_item_seq(int i) {
		menu_item_seq = i;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_tp(String string) {
		menu_item_tp = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_url(String string) {
		menu_item_url = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_vsb(String string) {
		menu_item_vsb = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_item_nm(String string) {
		menu_item_nm = string;
	}

	/**
	 * @param string
	 */
	public void setMenu_subgroup_id(String string) {
		menu_subgroup_id = string;
	}

	/**
	 * @param string
	 */
	public void setPage_id(String string) {
		page_id = string;
	}

	public int compare(Object arg1,Object arg2) {
		MenuItem_DTO item1 = (MenuItem_DTO)arg1;
		MenuItem_DTO item2 = (MenuItem_DTO)arg2;
		try {
			item1.getMenu_subgroup_seq();
			
			if(item1.getMenu_item_seq() < item2.getMenu_item_seq()) {
				return -1;
			} else if(item1.getMenu_item_seq() == item2.getMenu_item_seq()) {
				if(item1.getMenu_subgroup_seq() < item2.getMenu_subgroup_seq()) {
					return -1;
				} else if(item1.getMenu_subgroup_seq() == item2.getMenu_subgroup_seq()) {
					return 0;
				} else if(item1.getMenu_subgroup_seq() > item2.getMenu_subgroup_seq()) {
					return 1;
				}
			} else if(item1.getMenu_item_seq() > item2.getMenu_item_seq()) {
				return 1;
			}
		} catch(Exception e) {}
		
		return 0;
	}
	
	public boolean equals(Object obj) {
		MenuItem_DTO item = (MenuItem_DTO)obj;
		try {
			if(item.getMenu_subgroup_seq() == menu_subgroup_seq 
				&& item.getMenu_item_seq() == menu_item_seq) {
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
	public int getMenu_subgroup_seq() {
		return menu_subgroup_seq;
	}

	/**
	 * @param i
	 */
	public void setMenu_subgroup_seq(int i) {
		menu_subgroup_seq = i;
	}

	/**
	 * @return
	 */
	public String getPage_nm() {
		return page_nm;
	}

	/**
	 * @param string
	 */
	public void setPage_nm(String string) {
		page_nm = string;
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
	public void setTable_id(String string) {
		table_id = string;
	}

	/**
	 * @return
	 */
	public String getMenu_group_nm() {
		return menu_group_nm;
	}

	/**
	 * @param string
	 */
	public void setMenu_group_nm(String string) {
		menu_group_nm = string;
	}

	/**
	 * @return
	 */
	public String getMenu_subgroup_nm() {
		return menu_subgroup_nm;
	}

	/**
	 * @param string
	 */
	public void setMenu_subgroup_nm(String string) {
		menu_subgroup_nm = string;
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
	public String getMenu_group_init_url() {
		return menu_group_init_url;
	}

	/**
	 * @param string
	 */
	public void setMenu_group_init_url(String string) {
		menu_group_init_url = string;
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
	public String getQuery3() {
		return query3;
	}

	/**
	 * @param string
	 */
	public void setQuery3(String string) {
		query3 = string;
	}

}
