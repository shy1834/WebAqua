/*
 * Created on 2006. 2. 8.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu;

import java.sql.*;
import java.util.*;

import javax.naming.*;

import org.apache.log4j.*;

import com.ktf.aqua.mgr.menu.dao.*;
/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuManager {
	
	protected Logger logger = Logger.getLogger( this.getClass() );
	
	private MenuManager() {
	}

	public static MenuManager instance() {
		return (new MenuManager());
	}
	
	private MenuDAO getDAO() {
		return new MenuDAO();
	}
	
	public List findMenuList(String level, String menuId)
	throws SQLException, Exception  {
	  return getDAO().getMenuList(level, menuId);
	}
	
	public String findMenu(String level, String menuId)
	throws SQLException, Exception  {
	  return getDAO().findMenuName(level, menuId);
	}
	
	public String getAuthCode(String userId, String menuId)
	throws SQLException, Exception  {
	  return getDAO().getAuthCode(userId, menuId);
	}
	
	public List findPlfNameList()
	throws SQLException, Exception  {
	  return getDAO().getPlfNameList();
	}
	
	public List findSvcList(int plf_code,String path)
	throws SQLException, Exception  {
	  return getDAO().getSvcList(plf_code,path);
	}
	
	public List findSvcList1(int plf_code,String path)
	throws SQLException, Exception  {
	  return getDAO().getSvcList1(plf_code,path);
	}
	
	public List findSvcList2(int plf_code,String dev_level,String service_dev)
	throws SQLException, Exception  {
	  return getDAO().getSvcList2(plf_code,dev_level,service_dev);
	}
	
	public List findDataiList(String menu_dev)
	throws SQLException, Exception  {
	  return getDAO().getDataList(menu_dev);
	}

	public List findNtasSvcList(int dev_level, int upper_code)
	throws SQLException, Exception  {
	  return getDAO().getNtasSvcList(dev_level, upper_code);
	}
	
	public List findNtasList(int dev_level)
	throws SQLException, Exception  {
	  return getDAO().getNtasList(dev_level);
	}
	
	public List findMainaddtotList(String auth_type)
	throws SQLException, Exception  {
	  return getDAO().getMainaddtotList(auth_type);
	}
	
	public List findMainMenuList(String auth_type)
	throws SQLException, Exception  {
	  return getDAO().getMainMenuList(auth_type);
	}
	
	public List findSubaddtotList(String auth_type)
	throws SQLException, Exception  {
	  return getDAO().getSubaddtotList(auth_type);
	}

	public List findSubMenuList(String auth_type)
	throws SQLException, Exception  {
	  return getDAO().getSubMenuList(auth_type);
	}

	public List findSubAdMenuList()
	throws SQLException, Exception  {
	  return getDAO().getSubAdMenuList();
	}
	
	public List findDetailMenuList(String menuId, String auth_type)
	throws SQLException, Exception  {
	  return getDAO().getDetailMenuList(menuId, auth_type);
	}

	public List findMenuDetail(String menu_dev)
	throws SQLException, Exception  {
	  return getDAO().getMenuDetail(menu_dev);
	}
	
	public List findAdMenuDetail(String menuId)
	throws SQLException, Exception  {
	  return getDAO().getAdMenuDetail(menuId);
	}
	
	public List getFirstMenuList(String type) throws SQLException, NamingException{
		return getDAO().getFirstMenuList(type); 
	}
	
	public List getSeMenuList(String menuId) throws SQLException, NamingException{
		return getDAO().getSeMenuList(menuId); 
	}
	
	public List getDetailSearch(String item_type,String upper_key) throws SQLException, NamingException{
		return getDAO().getDetailSearch(item_type,upper_key);
	}
	
	public String getMvfName(String menuDev) throws SQLException, NamingException{
		return getDAO().getMvfFileName(menuDev);  
	}
	
	// 새로운 MASTER TABLE
	// 검색타입
	public List getSearchType(String menuLevel,String type,String upperKey,String detailType, String plfDevCode,String userLevel) throws SQLException, NamingException{
		return getDAO().getSearchType(menuLevel,type,upperKey,detailType,plfDevCode,userLevel); 
	}
	
	// 검색옵션
	public HashMap<String,String> getSearchOption(String menuLevel,String type,String codeId, String upperCode) throws SQLException, NamingException{
		return getDAO().getSearchOption(menuLevel,type,codeId,upperCode); 
	}
	
	// 1차메뉴
	public List getMenu1List(String type) throws SQLException, NamingException{
		return getDAO().getMenu1List(type); 
	}
	
	public String[] searchLog(String id,String type,String menu1Id, String menu2Id) throws SQLException, NamingException {
		return getDAO().searchLog(id, type, menu1Id, menu2Id);
	}
	
	// 관리자메뉴 
	public List getAdminMenuList() throws SQLException, NamingException{
		return getDAO().getAdminMenuList();
	}

	/*public List findSubLogMenuList(List subMenuList) 
	throws SQLException, Exception {
	  return getDAO().getsubLogMenuList(subMenuList);
	}*/
	
}
