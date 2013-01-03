/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.io.StringReader;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ktf.iss.config.*;
/** 
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsPage_DAO {

	protected Log log = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	public StatisticsPage_DAO() {
		super();
	} 
	
	public ArrayList getPageList(Connection conn,
		String page_nm, 
		String stat_tp_id, 
		String table_name, 
		String reg_yn, 
		int start,
		int end) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM ( ")
			.append("	SELECT ROWNUM RN, PAGE_ID, PAGE_NM, STAT_TP_NM, TABLE_NAME FROM ( ")
			.append("		SELECT DISTINCT A.PAGE_ID PAGE_ID, A.PAGE_NM PAGE_NM, ")
			.append("			B.STAT_TP_NM STAT_TP_NM, C.ALIAS TABLE_NAME ") 
			.append("			FROM ISS_STAT_PAGE_INFO A, ISS_STAT_TYPE B, ")
			.append("				ISS_STAT_TABLE_LIST C, ISS_SVC_TABLE_ORG D ");
		if(reg_yn != null)
			query.append("				, ISS_MENU_ITEM E ");
		query.append("			WHERE A.STAT_TP = B.STAT_TP_ID ")
			.append("				AND A.TABLE_ID = D.TABLE_ORG_ID ")
			.append("				AND D.TABLE_ID = C.TABLE_ID ");
		if(page_nm != null)
			query.append("				AND A.PAGE_NM LIKE ? ");
		if(stat_tp_id != null)
			query.append("				AND B.STAT_TP_ID = ?");
		if(table_name != null)
			query.append("				AND C.ALIAS LIKE ? ");
		if(reg_yn != null && reg_yn.equals("Y"))
			query.append("				AND A.PAGE_ID = E.PAGE_ID");
		else if (reg_yn != null && reg_yn.equals("N"))
			query.append("				AND A.PAGE_ID = E.PAGE_ID(+) AND E.PAGE_ID IS NULL ");

		query.append("			ORDER BY A.PAGE_NM ")
			.append("		) ")
			.append("	) ")
			.append("	WHERE RN >= ? AND RN <= ? ");

		ArrayList al = new ArrayList();

		try {
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			if(page_nm != null)
				pstmt.setString(index++, page_nm);
			if(stat_tp_id != null)
				pstmt.setString(index++, stat_tp_id);
			if(table_name != null)
				pstmt.setString(index++, table_name);
			pstmt.setInt(index++, start);
			pstmt.setInt(index++, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				StatisticsPage_DTO page = new StatisticsPage_DTO();
				page.setRownum(rs.getInt(1));
				page.setPage_id(rs.getString(2));
				page.setPage_nm(rs.getString(3));
				page.setStat_tp_nm(rs.getString(4));
				page.setTable_name(rs.getString(5));
				page.setRegistered(isRegisteredPage(conn, page.getPage_id()));
				al.add(page);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return al;
	}
	
	public int getTotalPage(Connection conn,
		String page_nm, 
		String stat_tp_id, 
		String table_name,
		String reg_yn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(*) FROM ( ") 
			.append("	SELECT DISTINCT A.PAGE_ID ") 
			.append("		FROM ISS_STAT_PAGE_INFO A, ISS_STAT_TYPE B, ")
			.append("			ISS_STAT_TABLE_LIST C, ISS_SVC_TABLE_ORG D ");
		if(reg_yn != null)
			query.append("			, ISS_MENU_ITEM E ");
		query.append("		WHERE A.STAT_TP = B.STAT_TP_ID AND A.TABLE_ID = D.TABLE_ORG_ID ")
			.append("			AND D.TABLE_ID = C.TABLE_ID ");
		if(page_nm != null)
			query.append("				AND A.PAGE_NM LIKE ? ");
		if(stat_tp_id != null)
			query.append("				AND B.STAT_TP_ID = ?");
		if(table_name != null)
			query.append("				AND C.ALIAS = ? ");
		if(reg_yn != null && reg_yn.equals("Y"))
			query.append("				AND A.PAGE_ID = E.PAGE_ID ");
		else if (reg_yn != null && reg_yn.equals("N"))
			query.append("				AND A.PAGE_ID = E.PAGE_ID(+) AND E.PAGE_ID IS NULL ");
		query.append(")");
			
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			if(page_nm != null)
				pstmt.setString(index++, page_nm);
			if(stat_tp_id != null)
				pstmt.setString(index++, stat_tp_id);
			if(table_name != null)
				pstmt.setString(index++, table_name);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public ArrayList getStatisticsTypeList(Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT STAT_TP_ID, STAT_TP_NM FROM ISS_STAT_TYPE ORDER BY STAT_TP_ID "; 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsType_DTO typeDTO = new StatisticsType_DTO();
				typeDTO.setStat_tp_id(rs.getString(1));
				typeDTO.setStat_tp_nm(rs.getString(2));
				result.add(typeDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}
	
	public ArrayList getPlatformList(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT A.PLATFORM, B.NAME, A.SVC_ID ")
			.append("	FROM ISS_SVC_DESC A, ISS_CODE_INFO B ")
			.append("	WHERE B.CODE_CATEGORY = 'PLATFORM' AND A.PLATFORM = B.CODE ")
			.append("	ORDER BY PLATFORM"); 
*/
		query.append("SELECT DISTINCT A.PLATFORM, B.NAME ")
			.append("	FROM ISS_SVC_DESC A, ISS_CODE_INFO B ")
			.append("	WHERE B.CODE_CATEGORY = 'PLATFORM' AND A.PLATFORM = B.CODE ")
			.append("	ORDER BY B.NAME"); 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while(rs.next()){
				Platform_DTO platformDTO = new Platform_DTO();
				platformDTO.setPlatform_code(rs.getString(1));
				platformDTO.setPlatform_name(rs.getString(2));
				result.add(platformDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getDataList(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT A.SVC_ID, A.TABLE_ORG_ID, B.ALIAS ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B")
			.append("	WHERE A.TABLE_ID = B.TABLE_ID")
			.append("		AND B.TABLE_TYPE = 1")
			.append("	ORDER BY A.SVC_ID, A.TABLE_ORG_ID"); 
*/
		query.append("SELECT DISTINCT C.PLATFORM, A.TABLE_ORG_ID, B.ALIAS, ")
			.append("		(SELECT COUNT(*) FROM ISS_STAT_TABLE_INFO D ")
			.append("					WHERE B.TABLE_ID = D.TABLE_ID AND D.COL_NAME = 'SERVICE_ID') ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B, ISS_SVC_DESC C ") 
			.append("	WHERE C.SVC_ID = A.SVC_ID  ")
			.append("		AND A.TABLE_ID = B.TABLE_ID ")
			.append("		AND B.TABLE_TYPE = 1 ")
			.append("	ORDER BY C.PLATFORM, A.TABLE_ORG_ID ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while(rs.next()){
				ServiceTable_DTO serviceTable = new ServiceTable_DTO();
				serviceTable.setPlatform_code(rs.getString(1));
				serviceTable.setTable_id(rs.getString(2));
				serviceTable.setTable_name(rs.getString(3));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getStatisticsGroupList(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT MENU_GROUP_ID, MENU_GROUP_NM ") 
			.append("	FROM ISS_MENU_GROUP")
			.append("	WHERE MENU_GROUP_TP = 'S'")
			.append("	ORDER BY MENU_GROUP_ID"); 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while(rs.next()){
				MenuGroup_DTO menuGroup = new MenuGroup_DTO();
				menuGroup.setMenu_group_id(rs.getString(1));
				menuGroup.setMenu_group_nm(rs.getString(2));
				result.add(menuGroup);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getCPList(Connection conn, String table_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT C.CP_CODE, C.CP_NAME ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_CP_CODE_INFO C ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.SVC_ID = B.SVC_ID ")
			.append("		AND B.CP_CODE = C.CP_CODE ")
			.append("	ORDER BY C.CP_NAME");
*/
		query.append("SELECT DISTINCT D.CP_CODE, D.CP_NAME ")
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C, ISS_CP_CODE_INFO D ") 
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.SVC_ID = B.SVC_ID ")
			.append("		AND B.PLATFORM = C.PLATFORM AND C.CP_CODE = D.CP_CODE ")
			.append("	ORDER BY D.CP_NAME");
 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Code_DTO cpCode = new Code_DTO();
				cpCode.setCode(rs.getString(1));
				cpCode.setName(rs.getString(2));
				result.add(cpCode);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getCPList(Connection conn, String table_id, String cp_code) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT C.CP_CODE, C.CP_NAME ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_CP_CODE_INFO C ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND B.CP_CODE = ? AND A.SVC_ID = B.SVC_ID ")
			.append("		AND B.CP_CODE = C.CP_CODE ")
			.append("	ORDER BY C.CP_NAME");
*/
		query.append("		SELECT DISTINCT D.CP_CODE, D.CP_NAME ") 
			.append("			FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C, ISS_CP_CODE_INFO D ")
			.append("			WHERE A.TABLE_ORG_ID = ? AND D.CP_CODE = ? AND A.SVC_ID = B.SVC_ID ")
			.append("				AND B.PLATFORM = C.PLATFORM AND C.CP_CODE = D.CP_CODE ")
			.append("			ORDER BY D.CP_NAME ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			pstmt.setString(2, cp_code);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Code_DTO cpCode = new Code_DTO();
				cpCode.setCode(rs.getString(1));
				cpCode.setName(rs.getString(2));
				result.add(cpCode);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getCPServiceList(Connection conn, String table_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT A.CP_CODE, A.SVC_ID, A.NAME ")
			.append(" FROM ISS_SVC_DESC A, ISS_SVC_TABLE_ORG B ")
			.append(" WHERE B.TABLE_ORG_ID = ? AND B.SVC_ID = A.SVC_ID ")
			.append(" ORDER BY A.CP_CODE, A.SVC_ID");
*/
		query.append("SELECT DISTINCT C.CP_CODE, SUBSTR('00000000'||C.SVC_ID, -8, 8), C.NAME ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.SVC_ID = B.SVC_ID ")
			.append("		AND B.PLATFORM = C.PLATFORM  ")
			.append("		AND C.TYPE = 'S' ")
			.append("	ORDER BY C.CP_CODE, SUBSTR('00000000'||C.SVC_ID, -8, 8) ");

		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Service_DTO serivce = new Service_DTO();
				serivce.setCp_code(rs.getString(1));
				serivce.setSvc_id(rs.getString(2));
				serivce.setName(rs.getString(3));
				result.add(serivce);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getCPServiceList(Connection conn, String table_id, String cp_code) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*
		query.append("SELECT DISTINCT A.CP_CODE, A.SVC_ID, A.NAME ")
			.append(" FROM ISS_SVC_DESC A, ISS_SVC_TABLE_ORG B ")
			.append(" WHERE B.TABLE_ORG_ID = ? AND A.CP_CODE = ? AND B.SVC_ID = A.SVC_ID ")
			.append(" ORDER BY A.CP_CODE, A.SVC_ID");
*/
		query.append("SELECT DISTINCT C.CP_CODE, SUBSTR('00000000'||C.SVC_ID, -8, 8), C.NAME ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND C.CP_CODE = ?  ")
			.append("		AND A.SVC_ID = B.SVC_ID	AND B.PLATFORM = C.PLATFORM  ")
			.append("		AND C.TYPE = 'S' ")
			.append("	ORDER BY C.CP_CODE, SUBSTR('00000000'||C.SVC_ID, -8, 8) ");

		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			pstmt.setString(2, cp_code);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Service_DTO serivce = new Service_DTO();
				serivce.setCp_code(rs.getString(1));
				serivce.setSvc_id(rs.getString(2));
				serivce.setName(rs.getString(3));
				result.add(serivce);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}

	public ArrayList getServiceList(Connection conn, String table_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT SUBSTR('00000000'||C.SVC_ID, -8, 8), C.NAME ")
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C ")  
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.SVC_ID = B.SVC_ID ")
			.append("		AND B.PLATFORM = C.PLATFORM ")
			.append("		AND C.TYPE = 'S' ")
			.append("	ORDER BY C.NAME ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Code_DTO service = new Code_DTO();
				service.setCode(rs.getString(1));
				service.setName(rs.getString(2));
				result.add(service);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public ArrayList getServiceList(Connection conn, String table_id, String cp_code) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DISTINCT SUBSTR('00000000'||C.SVC_ID, -8, 8), C.NAME ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_SVC_DESC B, ISS_SVC_DESC C ") 
			.append("	WHERE A.TABLE_ORG_ID = ? AND C.CP_CODE = ? ")
			.append("		AND A.SVC_ID = B.SVC_ID AND B.PLATFORM = C.PLATFORM ") 
			.append("		AND C.TYPE = 'S' ")
			.append("	ORDER BY C.NAME ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			pstmt.setString(2, cp_code);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Code_DTO service = new Code_DTO();
				service.setCode(rs.getString(1));
				service.setName(rs.getString(2));
				result.add(service);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public String getStatisticsTypeAdminURL(Connection conn, String stat_tp_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT ADMIN_URL FROM ISS_STAT_TYPE WHERE STAT_TP_ID = ? "; 
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, stat_tp_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}

	public String getStatisticsTypeModifyURL(Connection conn, String stat_tp_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT MODIFY_URL FROM ISS_STAT_TYPE WHERE STAT_TP_ID = ? "; 
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, stat_tp_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}
/*
	private String getTableID(Connection conn, String table_org_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT TABLE_ID FROM ISS_SVC_TABLE_ORG WHERE TABLE_ORG_ID = ?");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_org_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
*/

    private String getTableID(Connection conn, String table_org_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.TABLE_ID ") 
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B, ISS_SVC_DESC C ") 
			.append("	WHERE C.SVC_ID = A.SVC_ID  ")
			.append("       AND A.TABLE_ORG_ID = ? ")
			.append("		AND A.TABLE_ID = B.TABLE_ID ")
			.append("		AND B.TABLE_TYPE = 1 ")
			.append("	    AND B.AGGR_RESULT_TYPE=2")
			.append("	ORDER BY C.PLATFORM, A.TABLE_ORG_ID ");
		String result = null;
	
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_org_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
	
		return result;				
	}

	private String getTableIDWithPageID(Connection conn, String table_org_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.TABLE_ID ")
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B, ISS_SVC_DESC C, ISS_STAT_PAGE_INFO D ") 
			.append("	WHERE C.SVC_ID = A.SVC_ID  ")
			.append("       AND D.PAGE_ID = ? ")
			.append("       AND D.TABLE_ID = A.TABLE_ORG_ID ")
			.append("		AND A.TABLE_ID = B.TABLE_ID ")
			.append("		AND B.TABLE_TYPE = 1 ")
			.append("	    AND B.AGGR_RESULT_TYPE=2")
			.append("	ORDER BY C.PLATFORM, A.TABLE_ORG_ID ");
		String result = null;
	
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_org_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
	
		return result;				
	}

	private String getTableID(Connection conn, String table_org_id, int dt_tp) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.TABLE_ID FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.TABLE_ID = B.TABLE_ID ")
			.append("		AND B.AGGR_RESULT_TYPE = ? ");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_org_id);
			pstmt.setInt(2, dt_tp);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
/*
	public ArrayList getColumnList(Connection conn, String table_org_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.COL_ID, B.COL_NAME, B.ALIAS, B.LEN, B.DATA_TYPE, ")
			.append(" B.FORMAT, B.SPECIAL_TYPE, B.CODE_CATEGORY ")
			.append("	FROM ISS_STAT_TABLE_INFO A, ISS_STAT_COLUMN_INFO B ")
			.append("	WHERE A.TABLE_ID = ? AND A.VISIBLE = 1  AND A.COL_NAME = B.COL_NAME ")
			.append("	ORDER BY A.IDX ");
		ArrayList result = new ArrayList();
		
		try {
			String table_id = getTableID(conn, table_org_id);
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsColumn_DTO statColumn = new StatisticsColumn_DTO();
				statColumn.setCol_id(rs.getString(1));
				statColumn.setCol_name(rs.getString(2));
				statColumn.setAlias(rs.getString(3));
				statColumn.setLen(rs.getInt(4));
				statColumn.setData_type(rs.getInt(5));
				statColumn.setFormat(rs.getString(6));
				statColumn.setSpecial_type(rs.getString(7));
				statColumn.setCode_category(rs.getString(8));
				result.add(statColumn);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}
*/
	public ArrayList getColumnList(Connection conn, String table_org_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.COL_ID, B.COL_NAME, B.ALIAS, B.LEN, B.DATA_TYPE,")
			.append(" B.FORMAT, B.SPECIAL_TYPE, B.CODE_CATEGORY,  C.QM_VALUE ")
			.append("	FROM ISS_STAT_TABLE_INFO A, ISS_STAT_COLUMN_INFO B, ISS_STAT_QM C ")
			.append("	WHERE A.TABLE_ID = ? AND A.VISIBLE = 1  AND A.COL_NAME = B.COL_NAME AND C.COL_ID(+)=A.COL_ID")
			.append("	ORDER BY A.IDX ");
		ArrayList result = new ArrayList(); 
	
		try { 
			String table_id = getTableID(conn, table_org_id);
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsColumn_DTO statColumn = new StatisticsColumn_DTO();
				statColumn.setCol_id(rs.getString(1));
				statColumn.setCol_name(rs.getString(2));
				statColumn.setAlias(rs.getString(3));
				statColumn.setLen(rs.getInt(4));
				statColumn.setData_type(rs.getInt(5));
				statColumn.setFormat(rs.getString(6));
				statColumn.setSpecial_type(rs.getString(7));
				statColumn.setCode_category(rs.getString(8));
				result.add(statColumn);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
	
		return result;		
	}

	public String getColumnName(Connection conn, String col_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT COL_NAME ")
			.append("	FROM ISS_STAT_TABLE_INFO ")
			.append("	WHERE COL_ID = ? ");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, col_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}
	
	public String getColumnType(Connection conn, String col_name) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT DATA_TYPE ")
			.append("	FROM ISS_STAT_COLUMN_INFO ")
			.append("	WHERE COL_NAME = ? ");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, col_name);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}

	public String getTableAlias(Connection conn, String table_org_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT ALIAS FROM ISS_STAT_TABLE_LIST WHERE TABLE_ID = ? "; 
		String result = null;
		
		try {
			String table_id = getTableID(conn, table_org_id);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, table_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
	
	public String getTableName(Connection conn, String table_org_id, int dt_tp) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT TABLE_NAME FROM ISS_STAT_TABLE_LIST WHERE TABLE_ID = ? " +
			"	AND AGGR_RESULT_TYPE = ? "; 
		String result = null;
		
		try {
			
			String table_id = getTableID(conn, table_org_id, dt_tp);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, table_id);
			pstmt.setInt(2, dt_tp);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public ArrayList getCodeList(Connection conn, String code_category) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT CODE, NAME ")
			.append("	FROM ISS_CODE_INFO ")
			.append("	WHERE CODE_CATEGORY = ? AND CODE_TYPE = 'ISS' ")
			.append("	ORDER BY CODE ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, code_category);
			rs = pstmt.executeQuery();

			while(rs.next()){
				Code_DTO code = new Code_DTO();
				code.setCode(rs.getString(1));
				code.setName(rs.getString(2));
				result.add(code);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}

	public ArrayList getPageCodeInfo(Connection conn, String page_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT B.COL_NAME, A.VALUE, C.CODE_CATEGORY ")
			.append("	FROM ISS_STAT_PAGE_CONDITION A, ISS_STAT_TABLE_INFO B, ")
			.append("		ISS_STAT_COLUMN_INFO C ")
			.append("	WHERE A.PAGE_ID = ? ")
			.append("		AND A.USER_INPUT = 'Y' ")
			.append("		AND A.COL_ID = B.COL_ID ")
			.append("		AND B.COL_NAME = C.COL_NAME ") 
			.append("		AND C.DATA_TYPE = '1' ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsPageCondition_DTO spcDTO = new StatisticsPageCondition_DTO();
				spcDTO.setCndtn_col_name(rs.getString(1));
				spcDTO.setCndtn_value(rs.getString(2));
				spcDTO.setCndtn_code_category(rs.getString(3));
				result.add(spcDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}

	public MenuItem_DTO getPageMenuInfo(Connection conn, String menu_item_id, String userID) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		if(menu_item_id.startsWith("MENU")) {
			query.append("SELECT A.MENU_GROUP_NM, B.MENU_ITEM_NM, B.MENU_ITEM_LEVEL, C.PAGE_ID, C.TABLE_ID, C.PAGE_USER, ")
				.append(" (SELECT B.MENU_ITEM_NM ")
				.append("		FROM ISS_MENU_ITEM A, ISS_MENU_ITEM B ")
				.append("		WHERE A.MENU_ITEM_ID = ? AND A.MENU_SUBGROUP_ID = B.MENU_ITEM_ID ), A.MENU_GROUP_INIT_URL, ")
				.append(" C.QUERY ")
				.append("	FROM ISS_MENU_GROUP A, ISS_MENU_ITEM B, ISS_STAT_PAGE_INFO C ")
				.append("	WHERE B.MENU_ITEM_ID = ? ")
				.append("		AND B.MENU_GROUP_ID = A.MENU_GROUP_ID ")
				.append("		AND B.PAGE_ID = C.PAGE_ID ");
		} else {
			query.append("SELECT '나만의메뉴', B.MENU_ITEM_NM, B.MENU_ITEM_LEVEL, C.PAGE_ID, C.TABLE_ID, C.PAGE_USER, ")
				.append("	(SELECT B.MENU_ITEM_NM  ")
				.append("		FROM ISS_MY_MENU_ITEM A, ISS_MY_MENU_ITEM B ") 
				.append("		WHERE A.MENU_ITEM_ID = ? AND A.USER_ID = ? AND A.USER_ID = B.USER_ID ")				.append("			AND A.MENU_SUBGROUP_ID = B.MENU_ITEM_ID ), ")  
				.append("	'#', C.QUERY  ")
				.append("	FROM ISS_MY_MENU_ITEM B, ISS_STAT_PAGE_INFO C ")
				.append("	WHERE B.MENU_ITEM_ID = ?  AND B.USER_ID = ? ")
				.append("		AND B.PAGE_ID = C.PAGE_ID ");
		}

		MenuItem_DTO result = new MenuItem_DTO();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			
			if(menu_item_id.startsWith("MENU")) {
				pstmt.setString(1, menu_item_id);
				pstmt.setString(2, menu_item_id);
			} else {
				pstmt.setString(1, menu_item_id);
				pstmt.setString(2, userID);
				pstmt.setString(3, menu_item_id);
				pstmt.setString(4, userID);
			}
			rs = pstmt.executeQuery();

			if(rs.next()){
				result.setMenu_item_id(menu_item_id);
				result.setMenu_group_nm(rs.getString(1));
				result.setMenu_item_nm(rs.getString(2));
				result.setMenu_item_level(rs.getInt(3));
				result.setPage_id(rs.getString(4));
				result.setTable_id(rs.getString(5));
				result.setPage_user(rs.getString(6));
				result.setMenu_subgroup_nm(rs.getString(7));
				result.setMenu_group_init_url(rs.getString(8));
				
				String tmp = rs.getString(9);
				if(tmp != null) {
					int start = 0;
					int end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery1(tmp.substring(start, end));
				
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery2(tmp.substring(start, end));
						
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery3(tmp.substring(start, end));

					start = end + 4;
					end = tmp.indexOf("||||", start);
				}
/*
				StringTokenizer st = new StringTokenizer(tmp, "||||");
				if(st.hasMoreTokens())
					result.setQuery1(st.nextToken());
				if(st.hasMoreTokens())
					result.setQuery2(st.nextToken());
				if(st.hasMoreTokens())
					result.setQuery3(st.nextToken());
*/
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;		
	}

	public String getStatisticsTypeName(Connection conn, String stat_tp_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT STAT_TP_NM FROM ISS_STAT_TYPE WHERE STAT_TP_ID = ? "; 
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, stat_tp_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public ArrayList getStatisticsData(Connection conn, 
										String sql, 
										String[] args, 
										HashMap dataTypes,
										HashMap formats,
										int maxCount) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList result = new ArrayList();
			
		try {

			ArrayList al = new ArrayList();
			for(int i = 0; i < args.length; i++) {
				if(args[i] != null && (args[i].equals("${ALL}") || args[i].equals(""))) {
					al.add(new Integer(i));
				}
			}

			if(sql.indexOf("${ALL_IN_WHERE_CLAUSE}") > -1 || al.size() > 0) {				
				StringTokenizer st = new StringTokenizer(sql, " ");
				ArrayList tokens = new ArrayList();				
				while(st.hasMoreTokens()) {
					tokens.add(st.nextToken());
				}
				int firstSize = tokens.size();
			
				if(sql.indexOf("${ALL_IN_WHERE_CLAUSE}") > -1) {
					for(int j = 0; j < tokens.size(); j++) {
						if(tokens.get(j).equals("${ALL_IN_WHERE_CLAUSE}")) {
							tokens.remove(j + 1);
							tokens.remove(j);
							tokens.remove(j - 1);
							tokens.remove(j - 2);
							tokens.remove(j - 3);
							tokens.remove(j - 4);
							j = j - 6;
						}
					}
				}

				if(al.size() > 0) {
					int findCount = 0;
					for(int i = 0; i < al.size(); i++) {
						Integer index = (Integer)al.get(i);
						int counter = 0;
						for(int j = 0; j < tokens.size(); j++) {
							String token = (String)tokens.get(j);
							if(token.equals("?")) {
								counter++;
							}
							if((index.intValue()+1) == (counter+findCount)) {
								if(j+1 < tokens.size()) {
									String nextToken = (String)tokens.get(j+1);
									if(nextToken.equals("AND"))
										tokens.remove(j+1);
								}
								tokens.remove(j-2);
								tokens.remove(j-2);
								tokens.remove(j-2);
								if(j-2 < tokens.size()) {
									String prevToken = (String)tokens.get(j-3);
									String nextToken = (String)tokens.get(j-2);
									if(prevToken.equals("AND") && nextToken.equals("GROUP")
										|| prevToken.equals("AND") && nextToken.equals("ORDER")
										|| prevToken.equals("AND") && nextToken.equals(")")) {
										tokens.remove(j-3);
									} else if(prevToken.equals("NOT") && nextToken.equals(")") ) {
										tokens.remove(j-5);
										tokens.remove(j-5);
										tokens.remove(j-5);
										tokens.remove(j-5);
									}
								} else {
									String prevToken = (String)tokens.get(j-3);
									if(prevToken.equals("AND"))
										tokens.remove(j-3);								
								}
								findCount++;
								break;
							}						
						}
					}
				
					String[] newArgs = new String[args.length - al.size()];
					int j = 0;
					for(int i = 0; i < args.length; i++) {
						if(!args[i].equals("${ALL}") && !args[i].equals("")) {
							newArgs[j++] = args[i];
						}
					}
					args = newArgs;
				}

				if(firstSize > tokens.size()) {
					StringBuffer sb = new StringBuffer();
					for(int i = 0; i < tokens.size(); i++) {
						sb.append((String)tokens.get(i)).append(" ");
					}
					sql = sb.toString();
				}

			}

			log.info(">>Before Query : " + sql);
			pstmt = conn.prepareStatement(sql);
			if(args != null) {
				for(int i = 0; i < args.length; i++) {
					log.info(">> args["+i+"] : " + args[i]);
					pstmt.setString(i+1, args[i]);
				}
			}
			rs = pstmt.executeQuery();
			log.info(">>End Query : " + sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();

			int count = 0;
			StatisticsService_DTO totalDTO = new StatisticsService_DTO();
			while(rs.next()) {
				if((maxCount > 0 && count < maxCount) || (maxCount < 0)) {
					StatisticsService_DTO sDTO = new StatisticsService_DTO();
					for(int i = 1; i <= cols; i++){
						String columnName = rsmd.getColumnName(i);
						String orgColumnName = columnName;
						String dataType = (String)dataTypes.get(columnName);
						if(dataType == null && columnName.startsWith("PC_")) {
							orgColumnName = columnName.substring(3);
							dataType = (String)dataTypes.get(columnName.substring(3));
						}
						//PBJ_A_20040801_B
						//과거 일자 비교시 비교 데이타의 표시 형식이 맞지 않는 문제점 수정을 위해 추가 
						if(dataType == null && columnName.endsWith("__D")) {
							orgColumnName = columnName.substring(0, columnName.length() - 3);
							dataType = (String)dataTypes.get(columnName.substring(0, columnName.length() - 3));
						}
						if(dataType == null && columnName.endsWith("__P")) {
							orgColumnName = columnName.substring(0, columnName.length() - 3);
							dataType = (String)dataTypes.get(columnName.substring(0, columnName.length() - 3));
						}
						//PBJ_A_20040801_E


						String value = "";

						if(dataType!=null && dataType.equals("2")) {
							try {
								double doubleValue = rs.getDouble(columnName);
								DecimalFormat df = (DecimalFormat)formats.get(orgColumnName);
								value = df.format(doubleValue);
								sDTO.setValue(columnName, doubleValue);
							
								double tdValue = totalDTO.getNumberValue(columnName).doubleValue(); 
								totalDTO.setValue(columnName, doubleValue + tdValue);
								totalDTO.setValue(columnName, df.format(doubleValue+tdValue));
							} catch (Exception e) {
								value = rs.getString(columnName);
							}
						} else if(dataType!=null && dataType.equals("3")) {
							try {
								Timestamp date = rs.getTimestamp(columnName);
								SimpleDateFormat sdf = (SimpleDateFormat)formats.get(orgColumnName);
								value = sdf.format(date);
								if(value.indexOf("{") > -1) {
									int start = value.indexOf("{");
									int end = value.indexOf('}', start);
									if(end > start) {
										String tmpValue = value.substring(0, start);
										int month = Integer.parseInt(value.substring(start+1, end));
										if(month > 0 && month < 4) {
											value = tmpValue + "1" + value.substring(end+1);
										} else if(month > 3 && month < 7) {
											value = tmpValue + "2" + value.substring(end+1);
										} else if(month > 6 && month < 10) {
											value = tmpValue + "3" + value.substring(end+1);
										} else if(month > 9 && month < 13) {
											value = tmpValue + "4" + value.substring(end+1);
										}
									}
								}
							} catch (Exception e) {
								value = rs.getString(columnName);
							}
							totalDTO.setValue(columnName, "-");
						} else {
							int type = rsmd.getColumnType(i);
							if(type==Types.BIGINT||type==Types.DECIMAL||type==Types.DOUBLE
								|| type==Types.FLOAT || type==Types.INTEGER || type==Types.NUMERIC 
								|| type==Types.REAL || type==Types.SMALLINT || type==Types.TINYINT) {
								double doubleValue = 0 ;
								try{
									doubleValue = rs.getDouble(columnName);
									DecimalFormat df = new DecimalFormat("#,##0");
									value = df.format(doubleValue);
									sDTO.setValue(columnName, doubleValue);

									double tdValue = totalDTO.getNumberValue(columnName).doubleValue(); 
									totalDTO.setValue(columnName, doubleValue + tdValue);
									totalDTO.setValue(columnName, df.format(doubleValue+tdValue));
								}catch(Exception e){
									value = rs.getString(columnName);
									totalDTO.setValue(columnName, "-");
								}
								
							} else {
								value = rs.getString(columnName);
								totalDTO.setValue(columnName, "-");
							}
						}
						sDTO.setValue(columnName, value);
					}
					result.add(sDTO);
					count++;
				} else {
					break;
				}
			}
			if(result.size() > 0)
				result.add(totalDTO);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException sqle) {}
		}
		return result;
	}

	public ArrayList getStatisticsData(Connection conn, 
										String sql, 
										String[] args, 
										HashMap dataTypes,
										HashMap formats) throws Exception {
		return getStatisticsData(conn, sql, args, dataTypes, formats, -1);
	}
	
	public void insertStatisticsPage(Connection conn, 
										StatisticsPage_DTO spDTO) throws Exception  {

		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer(); 
		query.append("INSERT INTO ISS_STAT_PAGE_INFO(PAGE_ID, PAGE_NM, STAT_TP, DT_TP, ")
			.append(" DT_TP_USR_INPUT, DT_USR_INPUT, INQR_BEGIN_DT, INQR_END_DT, TABLE_ID, ")
			.append(" PAGE_LOC, PAGE_DESC, MAX_CNT, PREV_COMP, PAGE_USER, QUERY, EVENT_DT_COL_ID, ")
			.append(" MAX_CNT_USR_INPUT, SVC_ORG_DEPTH, SVC_ORG_USR_INPUT, DETAIL_INFO, SVC_PATH, ")			.append(" REFRESH_FLAG, REFRESH_TIME, SORT_USR_INPUT, TABLE_HIDE, GRAPH_HEIGHT, ")
			.append(" GRAPH_MAX_CNT, REF_FLAG, REF_DT, PAST_DT_TP, PAST_DT, ")
			.append(" STATIC_SCALE_Y1, STATIC_SCALE_Y2, MIN_VALUE_Y1, MAX_VALUE_Y1, MIN_VALUE_Y2, MAX_VALUE_Y2, ")
			.append(" HORIZONTAL_GRID, VERTICAL_GRID, VIEW_3D, PALETTE ) ")
			.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
/*
		query.append("INSERT INTO ISS_STAT_PAGE_INFO(PAGE_ID, PAGE_NM, STAT_TP, DT_TP, ")
			.append(" DT_TP_USR_INPUT, DT_USR_INPUT, INQR_BEGIN_DT, INQR_END_DT, TABLE_ID, ")
			.append(" PAGE_LOC, PAGE_DESC, MAX_CNT, PREV_COMP, PAGE_USER, QUERY1, QUERY2, ")
			.append(" QUERY3, EVENT_DT_COL_ID, MAX_CNT_USR_INPUT, SVC_ORG_DEPTH, SVC_ORG_USR_INPUT, DETAIL_INFO) ") //030113 detail_info 추가
			.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
*/

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, spDTO.getPage_id());
			pstmt.setString(2, spDTO.getPage_nm());
			pstmt.setString(3, spDTO.getStat_tp());
			pstmt.setString(4, spDTO.getDt_tp());
			pstmt.setString(5, spDTO.getDt_tp_usr_input());
			pstmt.setString(6, spDTO.getDt_usr_input());
			pstmt.setString(7, spDTO.getInqr_begin_dt());
			pstmt.setString(8, spDTO.getInqr_end_dt());
			pstmt.setString(9, spDTO.getTable_id());
			pstmt.setString(10, spDTO.getPage_loc());
			pstmt.setString(11, spDTO.getPage_desc());
			pstmt.setInt(12, spDTO.getMax_cnt());
			pstmt.setString(13, spDTO.getPrev_comp());
			pstmt.setString(14, spDTO.getPage_user());
			String tmp = spDTO.getQuery1() + "||||" + spDTO.getQuery2() + "||||" + spDTO.getQuery3()
						+ "||||" + spDTO.getQuery4() + "||||" + spDTO.getQuery5() + "||||" + spDTO.getQuery6()
						+ "||||" + spDTO.getQuery5() + "||||" + spDTO.getQuery6() + "||||" + spDTO.getQuery7()
						+ "||||" + spDTO.getQuery8() + "||||" + spDTO.getQuery9() + "||||" + spDTO.getQuery10();
			pstmt.setCharacterStream(15, new StringReader(tmp), tmp.length());
			pstmt.setString(16, spDTO.getDt_col_id());
			pstmt.setString(17, spDTO.getMax_cnt_usr_input());
			pstmt.setString(18, spDTO.getSvcorg_depth());
			pstmt.setString(19, spDTO.getSvcorg_usr_input());
			pstmt.setString(20, spDTO.getDetail_info());
			pstmt.setString(21, spDTO.getSvc_path());
			pstmt.setString(22, spDTO.getRefresh_flag());
			pstmt.setString(23, spDTO.getRefresh_time());
			pstmt.setString(24, spDTO.getSort_usr_input());
			pstmt.setString(25, spDTO.getTable_hide());
			pstmt.setString(26, spDTO.getGraph_height());
			pstmt.setString(27, spDTO.getGraph_max_cnt());
			pstmt.setString(28, spDTO.getRef_flag());
			pstmt.setString(29, spDTO.getRef_dt());
			pstmt.setString(30, spDTO.getPast_dt_tp());
			pstmt.setString(31, spDTO.getPast_dt());
			pstmt.setString(32, spDTO.getStatic_scale_y1());
			pstmt.setString(33, spDTO.getStatic_scale_y2());
			pstmt.setString(34, spDTO.getMin_value_y1());
			pstmt.setString(35, spDTO.getMax_value_y1());
			pstmt.setString(36, spDTO.getMin_value_y2());
			pstmt.setString(37, spDTO.getMax_value_y2());
			pstmt.setString(38, spDTO.getHorizontal_grid());
			pstmt.setString(39, spDTO.getVertical_grid());
			pstmt.setString(40, spDTO.getView_3D());
			pstmt.setString(41, spDTO.getPalette());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
	}
	
	public String getNextPageID(Connection conn) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT 'PAGE'||SUBSTR('0000'||ISS_PAGE_SEQ.NEXTVAL, -4, 4) FROM DUAL";
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public void insertStatisticsPageDetail(Connection conn, 
											StatisticsPageDetail_DTO spdDTO,
											int i) throws Exception {

		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer(); 
		query.append("INSERT INTO ISS_STAT_PAGE_DETAIL(PAGE_ID, COL_ID, FIELD_NM, ")
			.append(" TABLE_VSB, GRAPH_VSB, USER_DEFINED_INFO, REFERENCE_FIELD, SEQ, FORMAT, ")
			.append(" GROUP_FIELD, GROUP_FUNC, ORDER_SEQ, ORDERBY, IS_COMPARE, GRAPH_VSB_INIT, ")			.append(" GRAPH_AXIS, GRAPH_SCALE, GRAPH_TYPE, GRAPH_COLOR, IS_ANALYSIS) ")
			.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, spdDTO.getPage_id());
			if(spdDTO.getUser_defined_info_yn().equals("Y"))
				pstmt.setString(2, "UDF" + i);
			else
				pstmt.setString(2, spdDTO.getCol_id());
			pstmt.setString(3, spdDTO.getField_nm());
			pstmt.setString(4, spdDTO.getTable_vsb());
			pstmt.setString(5, spdDTO.getGraph_vsb());
			pstmt.setString(6, spdDTO.getUser_defined_info());
			pstmt.setString(7, spdDTO.getReference_field());
			pstmt.setString(8, spdDTO.getSeq());
			pstmt.setString(9, spdDTO.getFormat());
			pstmt.setString(10, spdDTO.getGroup_field());
			pstmt.setString(11, spdDTO.getGroup_func());
			pstmt.setString(12, spdDTO.getOrder_seq());
			pstmt.setString(13, spdDTO.getOrderby());
			pstmt.setString(14, spdDTO.getIs_compare());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
	}

	public void insertStatisticsPageCondition(Connection conn, 
												StatisticsPageCondition_DTO spcDTO)
												throws Exception {

		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer(); 
		query.append("INSERT INTO ISS_STAT_PAGE_CONDITION(PAGE_ID, COL_ID, CONDITION,")
			.append(" VALUE, USER_INPUT, SEQ, IS_COMPARE, VALUE_TEXT) ")
			.append(" VALUES (?,?,?,?,?,?,?,?) ");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, spcDTO.getPage_id());
			pstmt.setString(2, spcDTO.getCndtn_col_id());
			pstmt.setString(3, spcDTO.getCndtn_condition());
			pstmt.setString(4, spcDTO.getCndtn_value());
			pstmt.setString(5, spcDTO.getCndtn_usr_inpt());
			pstmt.setString(6, spcDTO.getCndtn_index());
			pstmt.setString(7, spcDTO.getCndtn_is_compare());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
	}

	public void insertStatisticsPageCompare(Connection conn, 
											StatisticsPageCompare_DTO spcomDTO,
											int seq)
											throws Exception {

		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer(); 
		query.append("INSERT INTO ISS_STAT_PAGE_COMPARE(PAGE_ID, CP_ID, SERVICE_ID, TABLE_ID, ")
			.append(" USER_INPUT, EVENT_DT_COL_ID, SEQ ) ")
			.append(" VALUES (?,?,?,?,?,?,?) ");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, spcomDTO.getPage_id());
			pstmt.setString(2, spcomDTO.getCp_id());
			pstmt.setString(3, spcomDTO.getService_id());
			pstmt.setString(4, spcomDTO.getTable_id());
			pstmt.setString(5, spcomDTO.getComp_usr_input());
			pstmt.setString(6, spcomDTO.getDt_col_id());
			pstmt.setInt(7, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
	}

	public String getTemplateURL(Connection conn, String stat_tp) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT TEMPLATE FROM ISS_STAT_TYPE WHERE STAT_TP_ID = ?";
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, stat_tp);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
	
	public String getPageTableID(Connection conn, String page_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT TABLE_ID FROM ISS_STAT_PAGE_INFO ")
			.append("	WHERE PAGE_ID = ? ");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
	
	public String getStatisticsActionURL(Connection conn, String page_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT B.ACTION_URL ") 
			.append("	FROM ISS_STAT_PAGE_INFO A, ISS_STAT_TYPE B ") 
			.append("	WHERE A.PAGE_ID = ? AND A.STAT_TP = B.STAT_TP_ID");
		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result= rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public int deleteStatisticsPage(Connection conn, String page_id, boolean needMenuItemDelete) throws Exception {

		String pageInfoDeleteQuery = "DELETE FROM ISS_STAT_PAGE_INFO WHERE PAGE_ID = '"+page_id+"'";
		String pageDetailDeleteQuery = "DELETE FROM ISS_STAT_PAGE_DETAIL WHERE PAGE_ID = '"+page_id+"'";
		String pageConditionDeleteQuery = "DELETE FROM ISS_STAT_PAGE_CONDITION WHERE PAGE_ID = '"+page_id+"'";
		String pageCompareDeleteQuery = "DELETE FROM ISS_STAT_PAGE_COMPARE WHERE PAGE_ID = '"+page_id+"'";
		String menuItemQuery = "DELETE FROM ISS_MENU_ITEM WHERE PAGE_ID = '"+page_id+"'";
		String myMenuItemQuery = "DELETE FROM ISS_MY_MENU_ITEM WHERE PAGE_ID = '"+page_id+"'";
		
		Statement stmt = null;
		int result = -1;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(pageInfoDeleteQuery);
			result = result + stmt.executeUpdate(pageDetailDeleteQuery);
			result = result + stmt.executeUpdate(pageConditionDeleteQuery);
			result = result + stmt.executeUpdate(pageCompareDeleteQuery);
			if(needMenuItemDelete) {
				result = result + stmt.executeUpdate(menuItemQuery);
				result = result + stmt.executeUpdate(myMenuItemQuery);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( stmt != null ) stmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public boolean isRegisteredPage(Connection conn, String page_id) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM ISS_MENU_ITEM WHERE PAGE_ID = ?";
		boolean result = false;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				if(rs.getInt(1)>0)
					result = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
	
	public StatisticsPage_DTO getPageInfo(Connection conn, String page_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 

		query.append("SELECT A.PAGE_ID, A.PAGE_NM, A.STAT_TP, A.DT_TP, A.DT_USR_INPUT, A.INQR_BEGIN_DT, ")
			.append("		A.INQR_END_DT, A.TABLE_ID, A.PAGE_LOC, A.PAGE_DESC, A.MAX_CNT, A.DT_TP_USR_INPUT, ")
			.append("		A.PREV_COMP, A.PAGE_USER, A.QUERY, B.COL_NAME, A.EVENT_DT_COL_ID, A.MAX_CNT_USR_INPUT, ")
			.append("		A.SVC_ORG_DEPTH, A.SVC_ORG_USR_INPUT, A.DETAIL_INFO, A.SVC_PATH, A.REFRESH_FLAG, ")
			.append("		A.REFRESH_TIME, A.SORT_USR_INPUT, A.TABLE_HIDE, A.GRAPH_HEIGHT, A.GRAPH_MAX_CNT, ")
			.append("		A.REF_FLAG, A.REF_DT, A.PAST_DT_TP, A.PAST_DT, ") 
			.append("		A.STATIC_SCALE_Y1, A.STATIC_SCALE_Y2, A.MIN_VALUE_Y1, A.MAX_VALUE_Y1, A.MIN_VALUE_Y2, A.MAX_VALUE_Y2, ") 
			.append("		A.HORIZONTAL_GRID, A.VERTICAL_GRID, A.VIEW_3D, A.PALETTE ") 
			.append("	FROM ISS_STAT_PAGE_INFO A, ISS_STAT_TABLE_INFO B ")
			.append("	WHERE A.EVENT_DT_COL_ID = B.COL_ID (+) AND A.PAGE_ID = ? ");
		StatisticsPage_DTO result = new StatisticsPage_DTO();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result.setPage_id(rs.getString(1));
				result.setPage_nm(rs.getString(2));
				result.setStat_tp(rs.getString(3));
				result.setDt_tp(rs.getString(4));
				result.setDt_usr_input(rs.getString(5));
				if(rs.getString(6) != null)
					result.setInqr_begin_dt(rs.getString(6).trim());
				if(rs.getString(7) != null)
					result.setInqr_end_dt(rs.getString(7).trim());
				result.setTable_id(rs.getString(8));
				result.setPage_loc(rs.getString(9));
				result.setPage_desc(rs.getString(10));
				result.setMax_cnt(rs.getInt(11));
				result.setDt_tp_usr_input(rs.getString(12));
				result.setPrev_comp(rs.getString(13));
				result.setPage_user(rs.getString(14));

				String tmp = rs.getString(15);
				if(tmp != null) {
					int start = 0;
					int end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery1(tmp.substring(start, end));
				
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery2(tmp.substring(start, end));

					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery3(tmp.substring(start, end));

					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery4(tmp.substring(start, end));

					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery5(tmp.substring(start, end));

					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery6(tmp.substring(start, end));
					
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery7(tmp.substring(start, end));
					
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery8(tmp.substring(start, end));
					
					start = end + 4;
					end = tmp.indexOf("||||", start);
					if(start <= end)
						result.setQuery9(tmp.substring(start, end));
					
					start = end + 4;
					if(start < tmp.length())
						result.setQuery10(tmp.substring(start));
				}

				result.setDt_col_name(rs.getString(16));
				result.setDt_col_id(rs.getString(17));
				result.setMax_cnt_usr_input(rs.getString(18));
				result.setSvcorg_depth(rs.getString(19));
				result.setSvcorg_usr_input(rs.getString(20));
				result.setDetail_info(rs.getString(21)); //040113 추가 toplist popup 
				result.setSvc_path(rs.getString(22));
				result.setRefresh_flag(rs.getString(23));
				result.setRefresh_time(rs.getString(24));
				result.setSort_usr_input(rs.getString(25));
				result.setTable_hide(rs.getString(26));
				result.setGraph_height(rs.getString(27));
				result.setGraph_max_cnt(rs.getString(28));
				result.setRef_flag(rs.getString(29));
				if(rs.getString(30) != null)
					result.setRef_dt(rs.getString(30).trim());
				result.setPast_dt_tp(rs.getString(31));
				
				if(rs.getString(32) != null)
					result.setPast_dt(rs.getString(32).trim());
				result.setStatic_scale_y1(rs.getString(33));
				result.setStatic_scale_y2(rs.getString(34));
				result.setMin_value_y1(rs.getString(35));
				result.setMax_value_y1(rs.getString(36));
				result.setMin_value_y2(rs.getString(37));
				result.setMax_value_y2(rs.getString(38));
				result.setHorizontal_grid(rs.getString(39));
				result.setVertical_grid(rs.getString(40));
				result.setView_3D(rs.getString(41));
				result.setPalette(rs.getString(42));
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}
/*
	public ArrayList getPageDetail(Connection conn, String page_id, boolean isModify, boolean isCompare) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 

		query.append(" SELECT A.PAGE_ID, A.COL_ID, A.FIELD_NM, A.TABLE_VSB, A.GRAPH_VSB, A.USER_DEFINED_INFO, ")
			.append("		A.REFERENCE_FIELD, A.SEQ, A.FORMAT, A.GROUP_FIELD, A.GROUP_FUNC, B.COL_NAME, ")
			.append("		B.DATA_TYPE, B.CODE_CATEGORY, B.ALIAS, B.LEN, B.SPECIAL_TYPE, A.ORDER_SEQ, ")
			.append("		A.ORDERBY, A.IS_COMPARE  ")
			.append("	FROM ISS_STAT_PAGE_DETAIL A, ISS_STAT_COLUMN_INFO B, ISS_STAT_TABLE_INFO C ") 
			.append("	WHERE A.PAGE_ID = ? ")
			.append("		AND SUBSTR(A.COL_ID, 1, 3) <> 'UDF' ")
			.append("		AND A.IS_COMPARE = ? ")
			.append("		AND A.COL_ID = C.COL_ID  ")
			.append("		AND C.COL_NAME = B.COL_NAME ")
			.append(" UNION ")
			.append(" SELECT A.PAGE_ID, A.COL_ID, A.FIELD_NM, A.TABLE_VSB, A.GRAPH_VSB, A.USER_DEFINED_INFO, ")
			.append("		A.REFERENCE_FIELD, A.SEQ, A.FORMAT, A.GROUP_FIELD, A.GROUP_FUNC, A.COL_ID, 2,  ")
			.append("		NULL, NULL, NULL, NULL , A.ORDER_SEQ, A.ORDERBY, A.IS_COMPARE ")
			.append("	FROM ISS_STAT_PAGE_DETAIL A  ")
			.append("	WHERE A.PAGE_ID = ? AND A.COL_ID LIKE 'UDF%' ")
			.append(" AND A.IS_COMPARE = ? ");

		ArrayList result = new ArrayList();
		
		try {
			String compare = "N"; 
			if(isCompare)
				compare = "Y";
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			pstmt.setString(2, compare);
			pstmt.setString(3, page_id);
			pstmt.setString(4, compare);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsPageDetail_DTO spdDTO = new StatisticsPageDetail_DTO();
				spdDTO.setPage_id(rs.getString(1));
				spdDTO.setCol_id(rs.getString(2));
				spdDTO.setField_nm(rs.getString(3));
				spdDTO.setTable_vsb(rs.getString(4));
				spdDTO.setGraph_vsb(rs.getString(5));
				spdDTO.setUser_defined_info(rs.getString(6));
				spdDTO.setReference_field(rs.getString(7));
				spdDTO.setSeq(rs.getString(8));
				spdDTO.setFormat(rs.getString(9));
				spdDTO.setGroup_field(rs.getString(10));
				spdDTO.setGroup_func(rs.getString(11));
				spdDTO.setCol_name(rs.getString(12));
				spdDTO.setData_type(rs.getString(13));
				if(rs.getString(14) != null	&& rs.getString(14).length() > 0) {
					spdDTO.setCode_category(rs.getString(14));
				}else {
					spdDTO.setCode_category("");
				}
				spdDTO.setAlias(rs.getString(15));
				if(spdDTO.getUser_defined_info() != null 
					&& spdDTO.getUser_defined_info().length() > 0
					&& (!spdDTO.getUser_defined_info().startsWith("$[") || isModify)) {
					spdDTO.setUser_defined_info_yn("Y");
				} else {
					spdDTO.setUser_defined_info_yn("N");
				}
				spdDTO.setOrder_seq(rs.getString("ORDER_SEQ"));
				spdDTO.setOrderby(rs.getString("ORDERBY"));
				spdDTO.setIs_compare(rs.getString("IS_COMPARE"));

				result.add(spdDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}
*/
	public ArrayList getPageDetail(Connection conn, String page_id, boolean isModify, boolean isCompare) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 

		query.append(" SELECT A.PAGE_ID, A.COL_ID, A.FIELD_NM, A.TABLE_VSB, A.GRAPH_VSB, A.USER_DEFINED_INFO, ")
			.append("		A.REFERENCE_FIELD, A.SEQ, A.FORMAT, A.GROUP_FIELD, A.GROUP_FUNC, B.COL_NAME, ")
			.append("		B.DATA_TYPE, B.CODE_CATEGORY, B.ALIAS, B.LEN, B.SPECIAL_TYPE, A.ORDER_SEQ, ")
			.append("		A.ORDERBY, A.IS_COMPARE, D.QM_VALUE, A.GRAPH_VSB_INIT, A.GRAPH_AXIS, A.GRAPH_SCALE, ")			.append("		A.GRAPH_TYPE, A.GRAPH_COLOR, A.IS_ANALYSIS ")
			.append("	FROM ISS_STAT_PAGE_DETAIL A, ISS_STAT_COLUMN_INFO B, ISS_STAT_TABLE_INFO C, ISS_STAT_QM D")// 040129 D 추가 
			.append("	WHERE A.PAGE_ID = ? AND SUBSTR(A.COL_ID, 1, 3) <> 'UDF' ")
			.append("		AND A.IS_COMPARE = ? AND A.COL_ID = C.COL_ID  ")
			.append("		AND C.COL_NAME = B.COL_NAME AND C.COL_ID = D.COL_ID(+) ")	//040129 추가		
			.append(" UNION ")
			.append(" SELECT A.PAGE_ID, A.COL_ID, A.FIELD_NM, A.TABLE_VSB, A.GRAPH_VSB, A.USER_DEFINED_INFO, ")
			.append("		A.REFERENCE_FIELD, A.SEQ, A.FORMAT, A.GROUP_FIELD, A.GROUP_FUNC, A.COL_ID, 2,  ")
			.append("		NULL, NULL, NULL, NULL , A.ORDER_SEQ, A.ORDERBY, A.IS_COMPARE, 0, A.GRAPH_VSB_INIT, ")			.append("		A.GRAPH_AXIS, A.GRAPH_SCALE, A.GRAPH_TYPE, A.GRAPH_COLOR, A.IS_ANALYSIS ")
			.append("	FROM ISS_STAT_PAGE_DETAIL A  ")
			.append("	WHERE A.PAGE_ID = ? AND A.COL_ID LIKE 'UDF%' ")
			.append(" AND A.IS_COMPARE = ? ")
			.append(" UNION ")
			.append(" SELECT ?,TO_CHAR(A.COL_ID), B.ALIAS, 'N', 'N', '', ")
			.append("	   '', 0, DECODE(B.DATA_TYPE, 2, '#,##0', ''), '', '', B.COL_NAME, ")  
			.append("	   B.DATA_TYPE, B.CODE_CATEGORY, B.ALIAS, B.LEN, B.SPECIAL_TYPE, NULL, ")
			.append("	   '', 'N',   C.QM_VALUE, 'N', '', '', 1, 255, 'N' ")
			.append("	FROM ISS_STAT_TABLE_INFO A, ISS_STAT_COLUMN_INFO B, ISS_STAT_QM C ")
			.append("	WHERE A.TABLE_ID = ?  ")
			.append("		  AND A.VISIBLE = 1   ")
			.append("		  AND A.COL_NAME = B.COL_NAME ") 
			.append("		  AND C.COL_ID(+)=A.COL_ID ")
			.append("		  AND TO_CHAR(A.COL_ID) NOT IN(SELECT COL_ID FROM ISS_STAT_PAGE_DETAIL WHERE PAGE_ID = ?) ");

		ArrayList result = new ArrayList();
	
		try {
			String compare = "N"; 
			if(isCompare)
				compare = "Y";
			String table_id = getTableIDWithPageID(conn, page_id);
			pstmt = conn.prepareStatement(query.toString());
			
			pstmt.setString(1, page_id);
			pstmt.setString(2, compare);
			pstmt.setString(3, page_id);
			pstmt.setString(4, compare);
			pstmt.setString(5, page_id);
			pstmt.setString(6, table_id);
			pstmt.setString(7, page_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsPageDetail_DTO spdDTO = new StatisticsPageDetail_DTO();
				spdDTO.setPage_id(rs.getString(1));
				spdDTO.setCol_id(rs.getString(2));
				spdDTO.setField_nm(rs.getString(3));
				spdDTO.setTable_vsb(rs.getString(4));
				spdDTO.setGraph_vsb(rs.getString(5));
				spdDTO.setUser_defined_info(rs.getString(6));
				spdDTO.setReference_field(rs.getString(7));
				spdDTO.setSeq(rs.getString(8));
				spdDTO.setFormat(rs.getString(9));
				spdDTO.setGroup_field(rs.getString(10));
				spdDTO.setGroup_func(rs.getString(11));
				spdDTO.setCol_name(rs.getString(12));
				spdDTO.setData_type(rs.getString(13));
				if(rs.getString(14) != null	&& rs.getString(14).length() > 0) {
					spdDTO.setCode_category(rs.getString(14));
				}else {
					spdDTO.setCode_category("");
				}
				spdDTO.setAlias(rs.getString(15));
/*
				if(spdDTO.getUser_defined_info() != null 
					&& spdDTO.getUser_defined_info().length() > 0
					&& (!spdDTO.getUser_defined_info().startsWith("$[") || isModify)) {
*/
				if(spdDTO.getCol_id() != null && spdDTO.getCol_id().startsWith("UDF")) {
					spdDTO.setUser_defined_info_yn("Y");
				} else {
					spdDTO.setUser_defined_info_yn("N");
				}
				spdDTO.setOrder_seq(rs.getString(18));
				spdDTO.setOrderby(rs.getString(19));
				spdDTO.setIs_compare(rs.getString(20));
				spdDTO.setQm_value(rs.getString(21));
				result.add(spdDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
	
		return result;						
	}
	public ArrayList getPageDetail(Connection conn, String page_id) throws Exception {
		return getPageDetail(conn, page_id, false, false);
	}
	
	public ArrayList getPageDetail(Connection conn, String page_id, boolean isModify) throws Exception {
		return getPageDetail(conn, page_id, isModify, false);
	}
/*
	public ArrayList getPageDetailQm(Connection conn, String page_id, boolean isModify, String table_id) throws Exception {
		return getPageDetailQm(conn, page_id, isModify, false, table_id);
	}
*/
	public ArrayList getPageCondition(Connection conn, String page_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append(" SELECT PAGE_ID, COL_ID, CONDITION, VALUE, USER_INPUT, SEQ, IS_COMPARE, VALUE_TEXT ")
			.append("	FROM ISS_STAT_PAGE_CONDITION ")
			.append("	WHERE PAGE_ID = ?");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsPageCondition_DTO spcDTO = new StatisticsPageCondition_DTO();
				spcDTO.setPage_id(rs.getString(1));
				spcDTO.setCndtn_col_id(rs.getString(2));
				spcDTO.setCndtn_condition(rs.getString(3).trim());
				spcDTO.setCndtn_value(rs.getString(4));
				spcDTO.setCndtn_usr_inpt(rs.getString(5));
				spcDTO.setCndtn_index(rs.getString(6));
				spcDTO.setCndtn_is_compare(rs.getString(7));
				result.add(spcDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}

	public String getServiceName(Connection conn, String svc_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append(" SELECT NAME FROM ISS_SVC_DESC WHERE SVC_ID = ? ");
		String result = "UNKNOWN";
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, svc_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}

	public ArrayList getPageCompare(Connection conn, String page_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
/*  
		query.append(" SELECT A.PAGE_ID, A.CP_ID, A.SERVICE_ID, A.TABLE_ID, A.USER_INPUT, ")
			.append(" A.EVENT_DT_COL_ID, B.COL_NAME ") 
			.append("	FROM ISS_STAT_PAGE_COMPARE A, ISS_STAT_TABLE_INFO B")
			.append("	WHERE A.PAGE_ID = ? AND A.EVENT_DT_COL_ID = B.COL_ID ")
			.append("	ORDER BY A.SEQ ");
*/
		query.append(" SELECT DISTINCT A.PAGE_ID, A.CP_ID, A.SERVICE_ID, A.TABLE_ID, ")
			.append("		A.USER_INPUT, A.EVENT_DT_COL_ID, B.COL_NAME, D.PLATFORM, A.SEQ, ")
			.append("		A.PAST_DT_TP1, A.PAST_DT_TP2, A.PAST_DT_TP3, A.PAST_DT_TP4, ")
			.append("		A.PAST_DT_TP5, A.PAST_DT_TP6, A.PAST_DT_TP7,  ")
			.append("		A.PAST_DT5, A.PAST_DT6, A.PAST_DT7 ")
			.append("	FROM ISS_STAT_PAGE_COMPARE A, ISS_STAT_TABLE_INFO B, ISS_SVC_TABLE_ORG C, ISS_SVC_DESC D  ")
			.append("	WHERE A.PAGE_ID = ? AND A.EVENT_DT_COL_ID = B.COL_ID ")
			.append("		AND A.TABLE_ID = C.TABLE_ORG_ID AND C.SVC_ID = D.SVC_ID ")
			.append("	ORDER BY A.SEQ ");
 		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, page_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				StatisticsPageCompare_DTO spcomDTO = new StatisticsPageCompare_DTO();
				spcomDTO.setPage_id(rs.getString(1));
				spcomDTO.setCp_id(rs.getString(2));
				spcomDTO.setService_id(rs.getString(3).trim());
				spcomDTO.setTable_id(rs.getString(4));
				spcomDTO.setComp_usr_input(rs.getString(5));
				spcomDTO.setDt_col_id(rs.getString(6));
				spcomDTO.setDt_col_name(rs.getString(7));
				spcomDTO.setPlatform(rs.getString(8));
				result.add(spcomDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}
	
	public ArrayList getWebServerList(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append(" SELECT HOSTNAME, IP, PORT, LOGIN_ID, LOGIN_PWD, SVC_DIR, SVC_TYPE ") 
			.append("	FROM ISS_WEB_SERVER_INFO ") 
			.append("	WHERE IS_ENABLE = 'Y' ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while(rs.next()){
				WebServerInfo_DTO wsiDTO = new WebServerInfo_DTO();
				wsiDTO.setHostname(rs.getString(1));
				wsiDTO.setIp(rs.getString(2));
				wsiDTO.setPort(rs.getInt(3));
				wsiDTO.setLogin_id(rs.getString(4));
				wsiDTO.setLogin_pwd(rs.getString(5));
				wsiDTO.setSvc_dir(rs.getString(6));
				wsiDTO.setSvc_type(rs.getString(7));
				result.add(wsiDTO);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}

	public boolean hasStatisticsTable(Connection conn, String table_org_id, String dt_tp) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append("SELECT COUNT(*) FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B ")
			.append("	WHERE A.TABLE_ORG_ID = ? ")			.append("		AND A.TABLE_ID = B.TABLE_ID ")			.append("		AND B.AGGR_RESULT_TYPE = ? ");
		boolean result = false;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, table_org_id);
			pstmt.setString(2, dt_tp);
			rs = pstmt.executeQuery();

			if(rs.next()){
				if(rs.getInt(1) > 0)
					result = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;								
	}
	
	public ArrayList getRootService(Connection conn) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append(" SELECT A.PATH, B.NAME ")
			.append("	FROM ISS_SVC_ORG A, ISS_SVC_DESC B ") 
			.append("	WHERE A.DEPTH = 1 AND A.SVC_ID = B.SVC_ID ");
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;						
	}
}
