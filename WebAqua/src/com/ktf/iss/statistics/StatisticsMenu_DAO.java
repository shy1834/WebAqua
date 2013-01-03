/*
 * Created on 2003. 10. 25.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.util.*;
import java.sql.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsMenu_DAO {

	/**
	 * 
	 */
	public StatisticsMenu_DAO() {
		super();
	}

	public ArrayList getMenuItemList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.MENU_ITEM_ID,A.MENU_ITEM_TP,A.MENU_GROUP_ID,A.MENU_SUBGROUP_ID, ")			.append(" A.PAGE_ID,A.MENU_ITEM_NM,A.MENU_ITEM_LEVEL ") 
			.append("	FROM ISS_MENU_ITEM A, ISS_MENU_GROUP B")
			.append("	WHERE  B.MENU_GROUP_TP = 'S' ")
			.append("		AND A.MENU_ITEM_VSB = 'Y' ")
			.append("		AND B.MENU_GROUP_ID = A.MENU_GROUP_ID ")
			.append("	ORDER BY A.MENU_GROUP_ID, A.MENU_ITEM_LEVEL, A.MENU_ITEM_SEQ ");
		 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while(rs.next()){
				MenuItem_DTO menuItem = new MenuItem_DTO();
				menuItem.setMenu_item_id(rs.getString(1));
				menuItem.setMenu_item_tp(rs.getString(2));
				menuItem.setMenu_group_id(rs.getString(3));
				menuItem.setMenu_subgroup_id(rs.getString(4));
				menuItem.setPage_id(rs.getString(5));
				menuItem.setMenu_item_nm(rs.getString(6));
				menuItem.setMenu_item_level(rs.getInt(7));
				result.add(menuItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public ArrayList getMenuGroupList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT MENU_GROUP_ID, MENU_GROUP_NM ") 
			.append("	FROM ISS_MENU_GROUP ")
			.append("	WHERE MENU_GROUP_TP = 'S' ")
			.append("	ORDER BY MENU_GROUP_ID ");
		 
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
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public int insertMenuItem(Connection conn, MenuItem_DTO miDTO) throws Exception {
		
		PreparedStatement pstmt = null;
		StringBuffer insertQuery = new StringBuffer();
		insertQuery.append("INSERT INTO ISS_MENU_ITEM(MENU_ITEM_ID, MENU_ITEM_NM, MENU_ITEM_TP, ")			.append(" MENU_ITEM_LEVEL, MENU_GROUP_ID, MENU_SUBGROUP_ID, PAGE_ID, MENU_ITEM_PERM, ")			.append(" MENU_ITEM_SEQ, MENU_ITEM_URL, MENU_ITEM_VSB, CRITICAL_JOB) ")			.append(" VALUES( ?,?,?,?,?,?,?,4294967295,?,?,'Y','N') ");
		int result = -1;
		
		try {
			result = shiftMenuItemSeq(conn, miDTO);
			pstmt = conn.prepareStatement(insertQuery.toString());
			pstmt.setString(1, miDTO.getMenu_item_id());
			pstmt.setString(2, miDTO.getMenu_item_nm());
			pstmt.setString(3, miDTO.getMenu_item_tp());
			pstmt.setInt(4, miDTO.getMenu_item_level());
			pstmt.setString(5, miDTO.getMenu_group_id());
			pstmt.setString(6, miDTO.getMenu_subgroup_id());
			pstmt.setString(7, miDTO.getPage_id());
			pstmt.setInt(8, miDTO.getMenu_item_seq());
			pstmt.setString(9, miDTO.getMenu_item_url());
			result = result + pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
				
		return result;
	}
	
	public int insertMenuFolder(Connection conn, MenuItem_DTO miDTO) throws Exception {
		
		PreparedStatement pstmt = null;
		StringBuffer insertQuery = new StringBuffer();
		insertQuery.append("INSERT INTO ISS_MENU_ITEM(MENU_ITEM_ID, MENU_ITEM_NM, MENU_ITEM_TP, MENU_ITEM_LEVEL, ")
			.append(" MENU_GROUP_ID, MENU_SUBGROUP_ID, MENU_ITEM_PERM, MENU_ITEM_SEQ, MENU_ITEM_URL, MENU_ITEM_VSB, CRITICAL_JOB) ")
			.append(" VALUES( 'MENU'||SUBSTR('0000'||ISS_MENU_SEQ.NEXTVAL, -4, 4),?,'1',2,?, ")			.append(" 'MENU'||SUBSTR('0000'||ISS_MENU_SEQ.CURRVAL, -4, 4),4294967295,?,'#','Y','N') ");
		int result = -1;
		
		try {
			result = shiftMenuItemSeq(conn, miDTO);
			pstmt = conn.prepareStatement(insertQuery.toString());
			pstmt.setString(1, miDTO.getMenu_item_nm());
			pstmt.setString(2, miDTO.getMenu_group_id());
			pstmt.setInt(3, miDTO.getMenu_item_seq());
			result = result + pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
				
		return result;
	}

	public int updateMenuItem(Connection conn, MenuItem_DTO miDTO) throws Exception {
		
		PreparedStatement pstmt = null;
		StringBuffer updateQuery = new StringBuffer();
		updateQuery.append("UPDATE ISS_MENU_ITEM SET MENU_ITEM_NM = ?, MENU_ITEM_SEQ = ? ")
			.append("	WHERE MENU_ITEM_ID = ?");
		int result = -1;
		
		try {
			result = shiftMenuItemSeq(conn, miDTO);
			pstmt = conn.prepareStatement(updateQuery.toString());
			pstmt.setString(1, miDTO.getMenu_item_nm());
			pstmt.setInt(2, miDTO.getMenu_item_seq());
			pstmt.setString(3, miDTO.getMenu_item_id());
			result = result + pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
				
		return result;
	}

	public int deleteMenuItem(Connection conn, String menu_item_id) throws Exception {
		
		PreparedStatement pstmt = null;
		StringBuffer deleteQuery = new StringBuffer();
		deleteQuery.append("DELETE FROM ISS_MENU_ITEM WHERE MENU_ITEM_ID = ?");
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(deleteQuery.toString());
			pstmt.setString(1, menu_item_id);
			result = result + pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
				
		return result;
	}

	public int deleteMenuFolder(Connection conn, String menu_item_id) throws Exception {
		
		PreparedStatement pstmt = null;
		StringBuffer deleteQuery = new StringBuffer();
		deleteQuery.append("DELETE FROM ISS_MENU_ITEM WHERE MENU_ITEM_ID = ? OR MENU_SUBGROUP_ID = ? ");
		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(deleteQuery.toString());
			pstmt.setString(1, menu_item_id);
			pstmt.setString(2, menu_item_id);
			result = result + pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
				
		return result;
	}

	private int shiftMenuItemSeq(Connection conn, MenuItem_DTO miDTO) throws Exception {

		PreparedStatement pstmt = null;
		StringBuffer updateQuery = new StringBuffer();
		updateQuery.append("UPDATE ISS_MENU_ITEM " )
			.append("	SET MENU_ITEM_SEQ = MENU_ITEM_SEQ + 1")
			.append("	WHERE  MENU_ITEM_SEQ >= ? ")
			.append("		AND MENU_GROUP_ID = ? ");
		if(miDTO.getMenu_subgroup_id() != null 
			&& miDTO.getMenu_subgroup_id().length() > 0) {
			updateQuery.append("		AND MENU_SUBGROUP_ID = ? ");
		} else {
			updateQuery.append("		AND MENU_ITEM_LEVEL = 2 ");
		}

		int result = -1;
		
		try {
			pstmt = conn.prepareStatement(updateQuery.toString());
			pstmt.setInt(1, miDTO.getMenu_item_seq());
			pstmt.setString(2, miDTO.getMenu_group_id());
			if(miDTO.getMenu_subgroup_id() != null 
				&& miDTO.getMenu_subgroup_id().length() > 0) {
				pstmt.setString(3, miDTO.getMenu_subgroup_id());
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}

		return result;
	}
	
	public String getNextMenuItemID(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT 'MENU'||SUBSTR('0000'||ISS_MENU_SEQ.NEXTVAL, -4, 4) FROM DUAL";

		String result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
	
	public MenuItem_DTO getMenuItem(Connection conn, String menu_item_id, boolean isFolder) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer(); 
		query.append("SELECT A.MENU_ITEM_TP, A.MENU_ITEM_LEVEL, A.MENU_GROUP_ID, A.MENU_SUBGROUP_ID, ")			.append("A.PAGE_ID, A.MENU_ITEM_IMG_URL, A.MENU_ITEM_PERM, A.MENU_ITEM_SEQ, A.MENU_ITEM_URL, ")			.append("A.MENU_ITEM_VSB, A.MENU_ITEM_NM ");
		if(!isFolder)
			query.append(", B.PAGE_NM ");		query.append("FROM ISS_MENU_ITEM A ");
		if(!isFolder)
			query.append(", ISS_STAT_PAGE_INFO B ");		query.append(" WHERE A.MENU_ITEM_ID = ? ");
		if(!isFolder)
			query.append(" AND A.PAGE_ID = B.PAGE_ID");

		MenuItem_DTO result = null;
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, menu_item_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = new MenuItem_DTO();
				result.setMenu_item_id(menu_item_id);
				result.setMenu_item_tp(rs.getString(1));
				result.setMenu_item_level(rs.getInt(2));
				result.setMenu_group_id(rs.getString(3));
				result.setMenu_subgroup_id(rs.getString(4));
				result.setPage_id(rs.getString(5));
				result.setMenu_item_img_url(rs.getString(6));
				result.setMenu_item_perm(rs.getLong(7));
				result.setMenu_item_seq(rs.getInt(8));
				result.setMenu_item_url(rs.getString(9));
				result.setMenu_item_vsb(rs.getString(10));
				result.setMenu_item_nm(rs.getString(11));
				if(!isFolder)
					result.setPage_nm(rs.getString(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch (SQLException sqle) {}
		}
		
		return result;
	}
}
