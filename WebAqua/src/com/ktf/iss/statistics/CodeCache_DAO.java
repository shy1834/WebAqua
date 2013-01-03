/*
 * Created on 2003. 11. 11.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.sql.*;
import java.util.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CodeCache_DAO {

	/**
	 * 
	 */
	public CodeCache_DAO() {
		super();
	}

	public ArrayList getCodeCategoryList(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT CODE_CATEGORY FROM ISS_CODE_INFO";
		 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next())
				result.add(rs.getString(1));
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

	public ArrayList getCodeList(Connection conn, String category) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT CODE, NAME FROM ISS_CODE_INFO WHERE CODE_CATEGORY = ? ";
		 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String[] rows = new String[2];
				rows[0] = rs.getString(1);
				rows[1] = rs.getString(2);
				result.add(rows);
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
	
	public ArrayList getCPCodeList(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT CP_CODE, CP_NAME FROM ISS_CP_CODE_INFO";
		 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String[] rows = new String[2];
				rows[0] = rs.getString(1);
				rows[1] = rs.getString(2);
				result.add(rows);
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

	public ArrayList getServiceCodeList(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT SUBSTR('00000000'||SVC_ID, -8, 8), NAME FROM ISS_SVC_DESC";
		 
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String[] rows = new String[2];
				rows[0] = rs.getString(1);
				rows[1] = rs.getString(2);
				result.add(rows);
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
