/*
 * Created on 2006. 2. 8.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.mgr.menu.dao;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.sql.*;
import java.util.*;

import javax.naming.*;

import org.apache.log4j.*;

import com.edsk.framework.dao.*;
import com.ktf.aqua.mgr.menu.common.*;
import com.ktf.aqua.mgr.menu.form.*;

//public class MenuDAO extends BaseDAO{
public class MenuDAO extends BaseDAO{
	
	protected Logger logger = Logger.getLogger( this.getClass() );
	
	public List getMenuList(String level, String menuId) throws SQLException, DAOException {
		List menuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT  LEVEL_DEPTH   \n");
			query.append("	     ,LIMIT_LEVEL_DEPTH       \n");
			query.append("	     ,MENU_ID       \n"); 
			query.append("	     ,MENU_NAME     \n");
			query.append("	     ,PATH          \n");
			query.append("	     ,IMAGE_PATH    \n");
			query.append("	     ,IMAGE_SIZE    \n");
			query.append("	     ,USE_YN        \n");
			query.append("	     ,REMARK        \n");
			query.append("	     ,VIEW_SORT     \n");	
			query.append("	     ,REG_DATE      \n");
			query.append("	     ,REG_ID        \n");
			query.append("	     ,UPDATE_DATE   \n");
			query.append("	     ,UPDATE_ID     \n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     \n");
			query.append("WHERE LEVEL_DEPTH = ?       \n");
			query.append("  AND MENU_ID LIKE ?||'%'   \n");
			query.append("ORDER BY VIEW_SORT ASC      \n");	
			 Object[] param=new Object[2];
		     param[0]=level;
		     param[1]=menuId;			
			
			rs = getQuery(con, query.toString(), param);		
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setLevelDepth(rs.getString("LEVEL_DEPTH"));
				menuForm.setLimitLevelDepth(rs.getString("LIMIT_LEVEL_DEPTH"));
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setMenuName(rs.getString("MENU_NAME"));
				menuForm.setPath(rs.getString("PATH"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setUseyn(rs.getString("USE_YN"));
				menuForm.setRemark(rs.getString("REMARK"));
				menuForm.setViewSort(rs.getString("VIEW_SORT"));
				menuForm.setRegDate(rs.getString("REG_DATE"));
				menuForm.setRegId(rs.getString("REG_ID"));				
				
				menuList.add(menuForm);
			}			
			rs.close();	
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}		
		return menuList;
	}
	
	
	
	/**
	 * 메인메뉴 리스트 정보를 데이터베이스에서 찾아 Menu 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public List getMainaddtotList(String auth_type) throws SQLException, DAOException {
		List menuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  MENU_ID       \n"); 
			query.append("	     ,	SUBSTR(MENU_ID, 0, 2) SHORT_MENU     \n");
			query.append("	     , IMAGE_PATH    \n");
			query.append("	     , IMAGE_SIZE    \n");
			query.append("	     , REPLACE(IMAGE_PATH, '.gif', '-o.gif')  IMAGE_PATH1      \n");
			query.append("	     , REPLACE(IMAGE_PATH, '.gif', '') DEV_NAME       \n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     \n");
			query.append("WHERE LEVEL_DEPTH = '1'      \n");
			query.append("  AND USE_YN >= ?   \n");
			query.append("  AND USE_YN <> 'N'   \n");
			query.append("  AND SUBSTR(MENU_ID,1,1) = '5'   \n");
			query.append("ORDER BY VIEW_SORT ASC      \n");	
			 
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,auth_type);
		    rs = pstmt.executeQuery();	
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setShortMenu(rs.getString("SHORT_MENU"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setImagePath1(rs.getString("IMAGE_PATH1"));
				menuForm.setDevName(rs.getString("DEV_NAME"));
								
				menuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return menuList;
	}
	
	public List getMainMenuList(String auth_type) throws SQLException, DAOException {
		List menuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  MENU_ID       											\n"); 
			query.append("	     ,	SUBSTR(MENU_ID, 0, 2) SHORT_MENU     					\n");
			query.append("	     , IMAGE_PATH    											\n");
			query.append("	     , IMAGE_SIZE    											\n");
			query.append("	     , REPLACE(IMAGE_PATH, '.gif', '-o.gif')  IMAGE_PATH1      	\n");
			query.append("	     , REPLACE(IMAGE_PATH, '.gif', '') DEV_NAME       			\n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     										\n");
			query.append("WHERE LEVEL_DEPTH = '1'      										\n");
			query.append("  AND USE_YN >= ?   												\n");
			query.append("  AND USE_YN <> 'N'   											\n");
			query.append("  AND (SUBSTR(MENU_ID,1,1) = '0' or SUBSTR(MENU_ID,1,1) = '1')    \n");
			query.append("ORDER BY VIEW_SORT ASC      										\n");	
			 
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,auth_type);
		    rs = pstmt.executeQuery();	
			int k = 1;
			while ( rs.next() ){
				
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setShortMenu(rs.getString("SHORT_MENU"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setImagePath1(rs.getString("IMAGE_PATH1"));
				menuForm.setDevName(rs.getString("DEV_NAME"));
								
				menuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return menuList;
	}
	
	
	
	
	/**
	 * 서브메뉴 리스트 정보를 데이터베이스에서 찾아 Menu 도메인 클래스에 
	 * 저장하여 반환.
	*/
	public List getSubaddtotList(String auth_type) throws SQLException, DAOException {
		List subMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  MENU_ID       \n"); 
			query.append("	     ,	SUBSTR(MENU_ID, 0, 2) SHORT_MENU     \n");
			query.append("	     , IMAGE_PATH    \n");
			query.append("	     , IMAGE_SIZE    \n");
			query.append("	     , PATH    \n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     \n");
			query.append("WHERE LEVEL_DEPTH = '2'       \n");
			query.append("  AND USE_YN >= ?   \n");
			query.append("  AND USE_YN <> 'N'   \n");
			query.append("  AND SUBSTR(MENU_ID,1,1) = '5'   \n");
			query.append("ORDER BY SUBSTR(MENU_ID, 0, 2), VIEW_SORT ASC      \n");	
			 
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,auth_type);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setShortMenu(rs.getString("SHORT_MENU"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setPath(rs.getString("PATH"));
				
				subMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return subMenuList;
	} 
	
	public List getSubMenuList(String auth_type) throws SQLException, DAOException {
		List subMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  MENU_ID       \n"); 
			query.append("	     ,	SUBSTR(MENU_ID, 0, 2) SHORT_MENU     \n");
			query.append("	     , IMAGE_PATH    \n");
			query.append("	     , IMAGE_SIZE    \n");
			query.append("	     , PATH    \n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     \n");
			query.append("WHERE LEVEL_DEPTH = '2'       \n");
			query.append("  AND USE_YN >= ?   \n");
			query.append("  AND USE_YN <> 'N'   \n");
			query.append("  AND (SUBSTR(MENU_ID,1,1) = '0' OR SUBSTR(MENU_ID,1,1) = '1')   \n");
			query.append("ORDER BY SUBSTR(MENU_ID, 0, 2), VIEW_SORT ASC      \n");	
			 
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,auth_type);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setShortMenu(rs.getString("SHORT_MENU"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setPath(rs.getString("PATH"));
				
				subMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return subMenuList;
	}
	
	/**
	 * 관리자 서브메뉴 리스트 정보를 데이터베이스에서 찾아 Menu 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public List getSubAdMenuList() throws SQLException, DAOException {
		List subMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT	MENU_ID	\n");
			query.append("		, SUBSTR(MENU_ID, 0, 4) SHORT_MENU	\n");
			query.append("		, PATH	\n");
			query.append("		, ADMIN_IMAGE	 IMAGE_PATH \n");
			query.append("		, ADMIN_SIZE	IMAGE_SIZE \n");
			query.append("		, REPLACE(ADMIN_IMAGE, '.gif', '-o.gif')  IMAGE_PATH1	\n");
			query.append("FROM AQUA3_MENU_MNG_TBL	\n");
			query.append("WHERE LEVEL_DEPTH = '2'	\n");
			query.append("  AND USE_YN = '0'	\n");
			query.append("ORDER BY SUBSTR(MENU_ID, 0, 4), VIEW_SORT	\n");	
			 
			con = getConnection();
			pstmt = con.prepareStatement(query.toString(), 
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY );
			
			rs = pstmt.executeQuery();		
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setShortMenu(rs.getString("SHORT_MENU"));
				menuForm.setPath(rs.getString("PATH"));
				menuForm.setImagePath(rs.getString("IMAGE_PATH"));
				menuForm.setImageSize(rs.getString("IMAGE_SIZE"));
				menuForm.setImagePath1(rs.getString("IMAGE_PATH1"));
				
				subMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findAdMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findAdMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findAdMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findAdMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( pstmt != null ){
				pstmt.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return subMenuList;
	}
	
	
	
	/**
	 * 서브메뉴 코드를 인자로 받아 하위 메뉴리스트의 정보를 데이터베이스에서 찾아  
	 * Menu 도메인 클래스에 저장하여 반환.
	 */
	public List getDetailMenuList(String menuId, String auth_type) throws SQLException, DAOException {
		List detailMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  MENU_ID       \n"); 
			query.append("	     ,	MENU_NAME     \n");
			query.append(" FROM AQUA3_MENU_MNG_TBL     \n");
			query.append("WHERE LEVEL_DEPTH = '3'       \n");
			query.append("  AND MENU_ID LIKE SUBSTR(?, 0, 4)||'%'   \n");
			query.append("  AND USE_YN >= ?   \n");
			query.append("  AND USE_YN <> 'N'   \n");
			query.append("ORDER BY VIEW_SORT ASC      \n");	
			 
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,menuId);
		    pstmt.setString(2,auth_type);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setMenuId(rs.getString("MENU_ID"));
				menuForm.setMenuName(rs.getString("MENU_NAME"));
				
				detailMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return detailMenuList;
	}
	

	/**
	 * 하위메뉴의 코드를 인자로 받아 해당메뉴의 정보를 데이터베이스에서 찾아  
	 * Menu 도메인 클래스에 저장하여 반환.
	 */
	public List getMenuDetail(String menu_dev) throws SQLException, DAOException {
		List detailMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT	A.MENU_NAME AS FIRST_NAME  \n");
			query.append("		, A.LEFT_IMAGE AS FIRST_IMAGE  \n");
			query.append("		, A.LEFT_SIZE AS FIRST_SIZE  \n");
			query.append("		, B.MENU_NAME AS SECOND_NAME  \n");
			query.append("		, B.LEFT_IMAGE AS SECOND_IMAGE  \n");
			query.append("		, B.LEFT_SIZE AS SECOND_SIZE  \n");
			query.append("		, C.MENU_NAME AS THIRD_NAME  \n");
			query.append("		, C.IMAGE_PATH AS THIRD_IMAGE  \n");
			query.append("		, C.IMAGE_SIZE AS THIRD_SIZE  \n");
			query.append("FROM  \n");
			query.append("(	  \n");
			query.append("	SELECT	MENU_NAME  \n");
			query.append("			, LEFT_IMAGE  \n");
			query.append("			, LEFT_SIZE  \n");
			query.append("	FROM	AQUA3_MENU_MNG_TBL  \n");
			query.append("	WHERE	MENU_ID = SUBSTR(?, 0, 2)||'0000'  \n");
			query.append(") A,  \n");
			query.append("(  \n");
			query.append("	SELECT	MENU_NAME  \n");
			query.append("			, LEFT_IMAGE  \n");
			query.append("			, LEFT_SIZE  \n");
			query.append("	FROM	AQUA3_MENU_MNG_TBL  \n");
			query.append("	WHERE	MENU_ID = SUBSTR(?, 0, 4)||'00'  \n");
			query.append(") B,  \n");
			query.append("(  \n");
			query.append("	SELECT  MENU_NAME  \n");
			query.append("			, IMAGE_PATH  \n");
			query.append("			, IMAGE_SIZE  \n");
			query.append("	FROM AQUA3_MENU_MNG_TBL   \n");
			query.append("	WHERE MENU_ID = ?  \n");
			query.append(") C  \n");
			 
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,menu_dev);
		    pstmt.setString(2,menu_dev);
		    pstmt.setString(3,menu_dev);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setFirst_Name(rs.getString("FIRST_NAME"));
				menuForm.setFirst_Image(rs.getString("FIRST_IMAGE"));
				menuForm.setFirst_Size(rs.getString("FIRST_SIZE"));
				menuForm.setSecond_Name(rs.getString("SECOND_NAME"));
				menuForm.setSecond_Image(rs.getString("SECOND_IMAGE"));
				menuForm.setSecond_Size(rs.getString("SECOND_SIZE"));
				menuForm.setThird_Name(rs.getString("THIRD_NAME"));
				menuForm.setThird_Image(rs.getString("THIRD_IMAGE"));
				menuForm.setThird_Size(rs.getString("THIRD_SIZE"));
				
				detailMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return detailMenuList;
	}
	
	
	
	/**
	 * 하위메뉴의 코드를 인자로 받아 해당메뉴의 정보를 데이터베이스에서 찾아  
	 * Menu 도메인 클래스에 저장하여 반환.
	 */
	public List getAdMenuDetail(String menuId) throws SQLException, DAOException {
		List detailMenuList = new ArrayList();
		MenuForm menuForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {		
			StringBuffer query = new StringBuffer();
			query.append("SELECT  A.MENU_NAME FIRST_NAME	\n");
			query.append("		, A.LEFT_IMAGE FIRST_IMAGE	\n");
			query.append("		, A.LEFT_SIZE FIRST_SIZE	\n");
			query.append("		, B.MENU_NAME SECOND_NAME	\n");
			query.append("		, B.IMAGE_PATH SECOND_IMAGE	\n");
			query.append("		, B.IMAGE_SIZE SECOND_SIZE	\n");
			query.append("		, B.LEFT_IMAGE THIRD_IMAGE	\n");
			query.append("		, B.LEFT_SIZE THIRD_SIZE	\n");
			query.append("FROM	\n");
			query.append("(		\n");
			query.append("	SELECT	MENU_NAME	\n");
			query.append("			, LEFT_IMAGE	\n");
			query.append("			, LEFT_SIZE	\n");
			query.append("	FROM AQUA3_MENU_MNG_TBL	\n");
			query.append("	WHERE MENU_ID = SUBSTR(?, 0, 4)||'00'	\n");
			query.append(") A,	\n");
			query.append("(	\n");
			query.append("	SELECT	MENU_NAME	\n");
			query.append("			, IMAGE_PATH	\n");
			query.append("			, IMAGE_SIZE	\n");
			query.append("			, LEFT_IMAGE	\n");
			query.append("			, LEFT_SIZE	\n");
			query.append("	FROM AQUA3_MENU_MNG_TBL	\n");
			query.append("	WHERE MENU_ID = ?	\n");
			query.append(") B	\n");
			 
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,menuId);
		    pstmt.setString(2,menuId);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				menuForm = new MenuForm();
				menuForm.setFirst_Name(rs.getString("FIRST_NAME"));
				menuForm.setFirst_Image(rs.getString("FIRST_IMAGE"));
				menuForm.setFirst_Size(rs.getString("FIRST_SIZE"));
				menuForm.setSecond_Name(rs.getString("SECOND_NAME"));
				menuForm.setSecond_Image(rs.getString("SECOND_IMAGE"));
				menuForm.setSecond_Size(rs.getString("SECOND_SIZE"));
				menuForm.setThird_Image(rs.getString("THIRD_IMAGE"));
				menuForm.setThird_Size(rs.getString("THIRD_SIZE"));
				
				detailMenuList.add(menuForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findAdMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findAdMenu()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findAdMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findAdMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return detailMenuList;
	}
	
	
	/**
	 * 사용자 아이디 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public String findMenuName(String level, String menuId) throws SQLException, DAOException {
		MenuForm menuForm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String menuName = "";
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT  LEVEL_DEPTH  \n ");
			query.append("		,MENU_ID	   \n ");   
			query.append("		,PATH          \n ");
			query.append("		,MENU_NAME     \n ");
			query.append("		,USE_YN        \n ");
			query.append("		,REMARK        \n ");
			query.append("		,VIEW_SORT 	   \n ");	
			query.append("   FROM AQUA3_MENU_MNG_TBL   \n");
			query.append("WHERE LEVEL_DEPTH = ?       \n");
			query.append("  AND MENU_ID LIKE ?||'%'   \n");		

			con = getConnection();
			
			Object[] param=new Object[2];
		     param[0]=level;
		     param[1]=menuId;
		     
			rs = getQuery(con, query.toString(), param);		
			
			if ( rs.next() ){	
				menuName = rs.getString("MENU_NAME");
			}

			rs.close();	
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".findMenuName() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenuName()" + "=>" + se);
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".findMenuName() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenuName()" + "=>" + ne);			
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}
		return menuName;
	}
	
	/**
	 * 각 해당 메뉴에 args[1]사용자 아이디, args[2]메뉴 아이디를 인자를 받아 메뉴에 권한 코드를 return함.
	 * @param userId
	 * @param menuId
	 * @return
	 * @throws SQLException
	 * @throws DAOException
	 */
	public String getAuthCode(String userId, String menuId) throws SQLException, DAOException {		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String authMenuCode = "";
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT AUTH_CODE  				\n");			
			query.append("   FROM AQUA3_USER_AUTH_MNG_TBL 	\n");
			query.append("WHERE USER_ID = ?       			\n");
			query.append("  AND MENU_ID = ?   				\n");		

			con = getConnection();
			
			Object[] param=new Object[2];
		     param[0] = userId;
		     param[1] = menuId;
		     
			rs = getQuery(con, query.toString(), param);		
			
			if ( rs.next() ){	
				authMenuCode = rs.getString("AUTH_CODE");
			}

			rs.close();	
			con.close();		
			
		} catch(SQLException se){
			logger.error(this.getClass().getName()+".authMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "authMenu()" + "=>" + se);
			
		} catch(NamingException ne){
			logger.error(this.getClass().getName()+".authMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "authMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}
		
		return authMenuCode;
	}
	
	
	
	/**
	 * 마스터 테이블에서 CP를 제외한 플랫폼구분 리스트를 return함.
	 * @param 
	 * @return PlfNameList
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getPlfNameList() throws SQLException, DAOException {
		List PlfNameList = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT DEV_CODE, DEV_DESC     \n");
			query.append("FROM	AQUA3_DEV_MST_TBL    \n");
			query.append("WHERE	DEV_LEVEL = '1'    \n");
			query.append("  AND DEV_CODE NOT IN ('4', '5000')    \n");
			query.append("  AND SERVICE_DEV = '0'    \n");
			query.append("ORDER BY DEV_CODE    \n");
			
			con = getConnection();
			pstmt = con.prepareStatement(query.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY );
		
			rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDevCode(rs.getString("DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				PlfNameList.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findGongjiList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findGongjiList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".findGongjiList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findGongjiList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( pstmt != null ){
				pstmt.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return PlfNameList;
	}
	
	
	
	/**
	 * 각 해당 플랫폼에 args[1]플랫폼 구분자를 인자로 받아 플랫폼에 서비스 구분 종류를 return함.
	 * @param plf_dev
	 * @return SvcList
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getSvcList(int plf_code,String path) throws SQLException, DAOException {
		List SvcList = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT 	DEV_CODE, DEV_DESC    \n");
			query.append("FROM 		AQUA3_DEV_MST_TBL   \n");
			query.append("WHERE 	UPPER_DEV_CODE = ?   \n");
			query.append("     AND 	DEV_LEVEL = 2   \n");
			if (path.equals("page")||path.equals("page_left")){
				query.append("  AND SERVICE_DEV = '1'    \n");	
			}else if(path.equals("down")||path.equals("down_left")){
				query.append("  AND SERVICE_DEV = '2'    \n");
			}else{
			query.append("  AND SERVICE_DEV = '0'    \n");
			}
			query.append("ORDER BY DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setInt(1, plf_code);
//			System.out.println("query = "+query.toString());
//			System.out.println("plf_code = "+plf_code);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDevCode(rs.getString("DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				SvcList.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return SvcList;
	}
	
	
	/**
	 * 해당 서비스별 서비스 구분자를 인자로 받아 플랫폼에 서비스 구분1 종류를 return함.
	 * @param svc_dev
	 * @return SvcList1
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getSvcList1(int plf_code,String path) throws SQLException, DAOException {
		System.out.println(path);
		List SvcList1 = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT 	DEV_CODE, DEV_DESC    \n");
			query.append("FROM 		AQUA3_DEV_MST_TBL   \n");
			query.append("WHERE 	UPPER_DEV_CODE = ?   \n");
			query.append("     AND    DEV_LEVEL = 3  \n");
			if (path.equals("page")||path.equals("page_left")){
				query.append("  AND SERVICE_DEV = '1'    \n");	
			}else if(path.equals("down")||path.equals("down_left")){
				query.append("  AND SERVICE_DEV = '2'    \n");
			}else{
			query.append("  AND SERVICE_DEV = '0'    \n");
			}
			query.append("ORDER BY DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setInt(1, plf_code);
//			System.out.println("query = "+query.toString());
//			System.out.println("plf_code = "+plf_code);
		    rs = pstmt.executeQuery();	
			
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDevCode(rs.getString("DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				SvcList1.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return SvcList1;
	}
	
	public List getSvcList2(int plf_code,String dev_level,String service_dev) throws SQLException, DAOException {
		List SvcList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT 	DEV_CODE, DEV_DESC    \n");
			query.append("FROM 		AQUA3_DEV_MST_TBL   \n");
			query.append("WHERE 	UPPER_DEV_CODE = ?   \n");
			query.append("     AND 	DEV_LEVEL = ?   \n");
			query.append("  AND SERVICE_DEV = ?    \n");
			query.append("ORDER BY DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setInt(1, plf_code);
		    pstmt.setString(2, dev_level);
		    pstmt.setString(3, service_dev);
		    
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				selForm = new SelectForm();
				selForm.setId(rs.getString("DEV_CODE"));
				selForm.setText(rs.getString("DEV_DESC"));
				
				SvcList.add(selForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}		
		return SvcList;
	}
	
	
	
	
	/**
	 * 각 해당 서비스에 args[1]서비스 구분자를 인자로 받아 서비스에 데이터 구분 종류를 return함.
	 * @param dev_code
	 * @return DataList
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getDataList(String menu_dev) throws SQLException, DAOException {
		List DataList = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT	DATA_DEV_CODE, DEV_DESC    \n");
			query.append("FROM		AQUA3_DATA_DEV_MST_TBL  \n");
			query.append("WHERE		LOG_CODE = ?   \n");
			query.append("ORDER BY DATA_DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setString(1,menu_dev);
		    rs = pstmt.executeQuery();
			logger.info("query = "+query.toString());
			logger.info("menu_dev = "+menu_dev);
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDataDevCode(rs.getString("DATA_DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				DataList.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return DataList;
	}
	
	
	/**
	 * NTAS 오류비율, 건수 화면에서 레벨과 플랫폼 구분자를 인자로 받아 서비스구분 LIST를 return함.
	 * 플랫폼 구분자 upper_code의 값이 0 인 경우 조건에서 제외함.
	 * @param dev_level
	 * @param upper_code
	 * @return NtasSvcList
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getNtasSvcList(int dev_level, int upper_code) throws SQLException, DAOException {
		List NtasSvcList = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT  DEV_CODE, DEV_DESC    \n");
			query.append("FROM 	AQUA3_DEV_MST_TBL  \n");
			query.append("WHERE 	DEV_LEVEL = ?   \n");
			if (upper_code != 0) {
				query.append("  AND   UPPER_DEV_CODE = ?   \n");
			}
			query.append("  AND SERVICE_DEV = '0'    \n");
			query.append("ORDER BY DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setInt(1,dev_level);
		    if (upper_code != 0) {
		    	pstmt.setInt(2,upper_code);
		    }
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDevCode(rs.getString("DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				NtasSvcList.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			con.close();		
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return NtasSvcList;
	}
	
	
	/**
	 * NTAS 모든 화면에서 레벨을 인자로 받아 플랫폼 구분, 타입 LIST를 return함.
	 * @param dev_level
	 * @return NtasSvcList
	 * @throws SQLException
	 * @throws DAOException
	 */
	public List getNtasList(int dev_level) throws SQLException, DAOException {
		List NtasList = new ArrayList();
		MasterForm masterForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT  DEV_CODE, DEV_DESC    \n");
			query.append("FROM 	AQUA3_DEV_MST_TBL  \n");
			query.append("WHERE		DEV_LEVEL = ?   \n");
			query.append("  AND SERVICE_DEV = '0'    \n");
			query.append("ORDER BY DEV_CODE   \n");
			
			con = getConnection();
			
			PreparedStatement pstmt=con.prepareStatement(query.toString());
		    pstmt.setInt(1,dev_level);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				masterForm = new MasterForm();
				masterForm.setDevCode(rs.getString("DEV_CODE"));
				masterForm.setDevDesc(rs.getString("DEV_DESC"));
				
				NtasList.add(masterForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSvcList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return NtasList;
	}

		
	/*public List getsubLogMenuList(List subMenuList) throws SQLException, DAOException{
		String menuId;
		String menuName;
		String userId;
		String userName;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA3_SUBMENU_LOG_TBL ");  
			insertQuery.append("SELECT	TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') DATE_FLD ");
			insertQuery.append(" , a.LEVEL_DEPTH, a.MENU_ID, a.MENU_NAME, b.USER_ID, b.USER_NAME ");
			insertQuery.append("FROM AQUA3_MENU_MNG_TBL a, AQUA2_USER_INFO_TBL b ");
			insertQuery.append("WHERE LEVEL_DEPTH = '2' AND MENU_ID = ? AND MENU_NAME = ? ");
			insertQuery.append("AND USER_ID = ? AND USER_NAME = ?");
			
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, menuId);
			pstmt.setString(2, menuName);
			pstmt.setString(3, userId);
			pstmt.setString(4, userName);			
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			return sub;
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".createKTF() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createKTF() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}*/
	
	// 1차 메뉴
	public List getFirstMenuList(String type) throws SQLException, NamingException{
	
		List returnList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select MENU_ID,MENU_NAME from AQUA3_MENU_MNG_TBL ");
			sb.append("where level_depth = '1' and path = ? and use_yn >= '1' and use_yn <> 'N' ");
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, type);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				selForm = new SelectForm();
				selForm.setId(rs.getString("MENU_ID"));
				selForm.setText(rs.getString("MENU_NAME"));
				
				returnList.add(selForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
	
	// 2차 메뉴
	public List getSeMenuList(String menuId) throws SQLException, NamingException{
	
		List returnList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select MENU_ID,MENU_NAME from AQUA3_MENU_MNG_TBL ");
			sb.append("where level_depth = '2' and substr(menu_id ,1,2)=?  ");
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,menuId.substring(0,2));
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				selForm = new SelectForm();
				selForm.setId(rs.getString("MENU_ID"));
				selForm.setText(rs.getString("MENU_NAME"));
				
				returnList.add(selForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
	
	/*
	 * 세부상세검색 DB로 처리하기가 애매하여 LIST로 처리
	 */
	public List getDetailSearch(String item_type,String upper_key) throws SQLException, NamingException{
		List returnList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select item_code,item_desc from SEARCH_OPTION_TBL ");
			sb.append("where item_type = ? ");
			
			if( upper_key.equals("") == false){
				sb.append("and upper_key = ? ");
			}
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,item_type);
			
			if( upper_key.equals("") == false){
				pstmt.setString(2,upper_key);
			}
			
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				selForm = new SelectForm();
				selForm.setId(rs.getString("item_code"));
				selForm.setText(rs.getString("item_desc"));
				
				returnList.add(selForm);
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
	
	/*
	 * 세부상세검색 DB로 처리하기가 애매하여 LIST로 처리
	 */
	public String getMvfFileName(String menuDev) throws SQLException, NamingException{
		List returnList = new ArrayList();
		String returnStr = "";
		Connection con = null;
		ResultSet rs = null;
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select mvf_file from MENU_MNG_TBL ");
			sb.append("where menu_level = '4' and code_id = ?");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1,menuDev);
			
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				returnStr = rs.getString("mvf_file");
			}			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getMvfFileName() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getMvfFileName() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnStr;
	}
	
	
	/*
	 * 새로운 마스터 테이블
	 */
	// 검색메뉴
	public List<SelectForm> getSearchType(String menuLevel, 
										  String type,
										  String upperKey,
										  String detailType,
										  String plfDevCode,
										  String userLevel) throws SQLException, NamingException{
		
		List<SelectForm> returnList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;

//삭제
System.out.println("MenuDAO getSearchType : menuLevel = " + menuLevel);
System.out.println("MenuDAO getSearchType : type = " + type);
System.out.println("MenuDAO getSearchType : upperKey = " + upperKey);
System.out.println("MenuDAO getSearchType : detailType = " + detailType);
System.out.println("MenuDAO getSearchType : plfDevCode = " + plfDevCode);
System.out.println("MenuDAO getSearchType : userLevel = " + userLevel);

		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("select code_id,code_desc from MENU_MNG_TBL ");
			sb.append("where menu_level = ? ");
			
			if(type.equals("") == false){
				sb.append("and menu_type = ? ");
			}
			
			if(upperKey.equals("") == false){
				sb.append("and upper_code = ?");
			}
			
			if(detailType.equals("") == false){
				sb.append("and detail_type = ?");
			}
			
			if(plfDevCode.equals("") == false){
				sb.append("and plf_code = ?");
			}
			
			if(userLevel.equals("") == false){
				if(userLevel.equals("4")){
					sb.append("and user_level = ?");
				}else{
					sb.append("and user_level >= ?");
				}
			}
			
			sb.append(" order by view_sort ");
			
			con = getConnection();
			
			int nextNum = 2;
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, menuLevel);
			
			if(type.equals("") == false){
				pstmt.setString(2, type);
				nextNum += 1;
			}
			
			if(upperKey.equals("") == false){
				pstmt.setString(nextNum, upperKey);
				nextNum += 1;
			}
			
			if(detailType.equals("") == false){
				pstmt.setString(nextNum, detailType);
				nextNum += 1;
			}
			
			if(plfDevCode.equals("") == false){
				pstmt.setString(nextNum, plfDevCode);
			}
			
			if(userLevel.equals("") == false){
				pstmt.setInt(nextNum, Integer.parseInt(userLevel));
				nextNum += 1;
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next() ){
				
				selForm = new SelectForm();
				selForm.setId(rs.getString("code_id"));
				selForm.setText(rs.getString("code_desc"));
				
				returnList.add(selForm);
			}	

			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
	
	// plf_dev 가져오기
	public HashMap<String,String> getSearchOption(String menuLevel, String type, String codeId, String upperKey) throws SQLException, NamingException{
		
//삭제
System.out.println("MenuDAO getSearchOption : menuLevel = " + menuLevel);
System.out.println("MenuDAO getSearchOption : type = " + type);
System.out.println("MenuDAO getSearchOption : codeId = " + codeId);
System.out.println("MenuDAO getSearchOption : upperKey = " + upperKey);
		
		Connection con = null;
		ResultSet rs = null;
		HashMap<String, String> returnHash = new HashMap<String, String>();
		
		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("select plf_code,detail_type,data_dev_type,date_type,date_type_yn,detail_yn,sort_type,url_yn,svc_yn,scount_yn,mvf_file from MENU_MNG_TBL ");
			sb.append("where menu_level = ? ");
			
			if( type.equals("") == false){
				sb.append("and menu_type = ? ");
			}
			
			if( upperKey.equals("") == false){
				sb.append("and upper_code = ?");
			}
			if( codeId.equals("") == false){
				sb.append("and code_id = ?");
			}
			
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, menuLevel);
			int numCnt = 2;
			
			if( type.equals("") == false){
				pstmt.setString(2, type);
				numCnt += 1;
			}
			
			if( upperKey.equals("") == false){
				pstmt.setString(numCnt, upperKey);
			}
			if( codeId.equals("") == false){
				pstmt.setString(numCnt, codeId);
			}
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				
				returnHash.put("plf_code", rs.getString("plf_code"));
				returnHash.put("detail_type", rs.getString("detail_type"));
				returnHash.put("data_dev_type", rs.getString("data_dev_type"));
				returnHash.put("date_type", rs.getString("date_type"));
				returnHash.put("date_type_yn", rs.getString("date_type_yn"));
				returnHash.put("sort_type", rs.getString("sort_type"));
				returnHash.put("mvf_file", rs.getString("mvf_file"));
				returnHash.put("url_yn", rs.getString("url_yn"));
				returnHash.put("svc_yn", rs.getString("svc_yn"));
				returnHash.put("scount_yn", rs.getString("scount_yn"));
				returnHash.put("detail_yn", rs.getString("detail_yn"));
			}			
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnHash;
	}
	
	//1차 메뉴
	public List getMenu1List(String type) throws SQLException, NamingException{
		
		List returnList = new ArrayList();
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("select MENU_ID,MENU_NAME from AQUA3_MENU_MNG_TBL ");
			sb.append("where level_depth = '1' and path = ? and use_yn >= '1' and use_yn <> 'N' ");
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, type);
		    rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				selForm = new SelectForm();
				selForm.setId(rs.getString("MENU_ID"));
				selForm.setText(rs.getString("MENU_NAME"));
				
				returnList.add(selForm);
			}			
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
	
	//검색기록 남기기
	//1차 메뉴
	public String[] searchLog(String id,String type,String menu1Id, String menu2Id) throws SQLException, NamingException{
		
		SelectForm selForm = null;
		Connection con = null;
		ResultSet rs = null;
		String[] resultStr = new String[3];
		
		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT A.CODE_DESC,B.CODE_DESC,C.CODE_DESC FROM ");
		    sb.append("( SELECT '1' A,CODE_DESC FROM MENU_MNG_TBL WHERE MENU_LEVEL = '0' AND CODE_ID = '"+ type +"' ) A, ");
		    sb.append("( SELECT '1' A,CODE_DESC FROM MENU_MNG_TBL WHERE MENU_LEVEL = '2' AND MENU_TYPE = '"+ type +"' AND CODE_ID = '"+ menu1Id +"' ) B, ");        
		    sb.append("( SELECT '1' A,CODE_DESC FROM MENU_MNG_TBL WHERE MENU_LEVEL = '3' AND MENU_TYPE = '"+ type +"' AND UPPER_CODE = '"+ menu1Id +"' AND CODE_ID = '"+ menu2Id +"' ) C ");
		    sb.append("WHERE A.A = B.A(+) AND A.A = C.A(+) ");
		
			con = getConnection();
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
		    rs = pstmt.executeQuery();
			
		    String menu1 = ""; 
		    String menu2 = ""; 
		    String menu3 = ""; 
		    	
			while (rs.next()){
				
				menu1 = rs.getString(1);
				menu2 = rs.getString(2);
				menu3 = rs.getString(3);
			}
			
			resultStr[0] = menu1;
			resultStr[1] = menu2;
			resultStr[2] = menu3;
			
			/* 검색로그 기록남기기 일단 보류
			String query = "insert into MENU_SEARCH_LOG_TBL values (?, ?, ?, ?, sysdate)";
			
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, menu1);
			pstmt.setString(3, menu2);
			pstmt.setString(4, menu3);
			
			pstmt.executeUpdate();
			*/
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return resultStr;
	}
	
	//관리자메뉴 정보 가져오기
	public List getAdminMenuList() throws SQLException, NamingException{
		
		Connection con = null;
		ResultSet rs = null;
		List<HashMap> returnList = new ArrayList();
		
		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append("select code_desc,mvf_file from MENU_MNG_TBL where menu_level = '11'");
			sb.append("order by view_sort ");
			
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				HashMap<String, String> returnHash = new HashMap<String, String>();
				returnHash.put("code_desc", rs.getString("code_desc"));
				returnHash.put("mvf_file", rs.getString("mvf_file"));
				returnList.add(returnHash);
			}			
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSvcList() Exception:"+se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".getSvcList() NamingException:"+ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return returnList;
	}
}




