/*
 * Created on 2005. 1. 13.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.iss.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.ktf.aqua.db.DBDataSource;

/**
 * @author hyunyun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Report_DAO {
	
	public ArrayList getReportTemplateList(Connection conn, String reptTmplName, int start, int end) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT * FROM ( ")
			 .append("	SELECT ROWNUM RN, REPT_TMPL_ID, REPT_TMPL_NM, REPT_TMPL_FILE_NM, REPT_PRD ")
			 .append("	FROM ( ")
			 .append("		SELECT REPT_TMPL_ID, REPT_TMPL_NM, REPT_TMPL_FILE_NM, REPT_PRD ")
			 .append("			FROM ISS_REPT_TMPL_INFO ");
		
		if(reptTmplName != null){
			query.append("			WHERE  REPT_TMPL_NM LIKE ? ");
		}
			
		query.append("			ORDER BY REPT_TMPL_ID DESC ")
			 .append("		) ")
			 .append("	) WHERE RN BETWEEN ? AND ? ");

		ArrayList result = new ArrayList(); 
		
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			if(reptTmplName != null){
				pstmt.setString(index++, reptTmplName);
			}
				
			pstmt.setInt(index++, start);
			pstmt.setInt(index++, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportTemplate_DTO dto = new ReportTemplate_DTO();
				dto.setRownum(rs.getString(1));
				dto.setRept_tmpl_id(rs.getString(2));
				dto.setRept_tmpl_nm(rs.getString(3));
				dto.setRept_tmpl_file_nm(rs.getString(4));
				dto.setRept_prd(rs.getString(5));
				result.add(dto);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				}
				
				if(rs != null){
					rs.close();
				} 
				
			} catch (SQLException sqle) {}
		}
		return result;
	}

	public ArrayList getReportTemplateList(Connection conn, int mode) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT REPT_TMPL_ID, REPT_TMPL_NM, REPT_TMPL_REAL_FILE ")
			.append("	FROM ISS_REPT_TMPL_INFO ")
			.append("	WHERE  REPT_PRD = 'D' ");
		if(mode > 1)
			query.append("		OR REPT_PRD = 'W' ");
		if(mode > 2)
			query.append("		OR REPT_PRD = 'M' ");

		ArrayList result = new ArrayList(); 
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReportTemplate_DTO dto = new ReportTemplate_DTO();
				dto.setRept_tmpl_id(rs.getString(1));
				dto.setRept_tmpl_nm(rs.getString(2));
				dto.setRept_tmpl_real_file(rs.getString(3));
				result.add(dto);
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

	public int getReportTemplateTotal(Connection conn, String reptTmplName) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT COUNT(*) ")
			 .append("	FROM ISS_REPT_TMPL_INFO ");
		
		if(reptTmplName != null){
			query.append("	WHERE  REPT_TMPL_NM LIKE ? ");
		}

		int result = -1; 
		
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			
			if(reptTmplName != null){
				pstmt.setString(index++, reptTmplName);
			}
				
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				} 
				
				if(rs != null){
					rs.close();
				}
			} catch (SQLException sqle) {}
		}
		return result;
	}

	public String getNextReportTemplateID(Connection conn) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT 'RPTP'||SUBSTR('0000'||ISS_REPT_TMPL_SEQ.NEXTVAL, -4, 4) FROM DUAL";
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

	public String getNextReportStatisticsInfoID(Connection conn) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT 'RPST'||SUBSTR('0000'||ISS_REPT_STAT_SEQ.NEXTVAL, -4, 4) FROM DUAL";
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

	public String getNextReportID(Connection conn) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT 'REPT'||SUBSTR('000000'||ISS_REPT_FILE_SEQ.NEXTVAL, -6, 6) FROM DUAL";
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

	public int insertReportTemplateInfo(Connection conn, ReportTemplate_DTO dto) throws Exception  {

		PreparedStatement pstmt = null;
		String query = "INSERT INTO ISS_REPT_TMPL_INFO(REPT_TMPL_ID, REPT_TMPL_NM, REPT_TMPL_FILE_NM, REPT_PRD, REPT_TMPL_REAL_FILE)	VALUES(?,?,?,?,?)";
		int result = -1;
		
		try {
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, dto.getRept_tmpl_id());
			pstmt.setString(index++, dto.getRept_tmpl_nm());
			pstmt.setString(index++, dto.getRept_tmpl_file_nm());
			pstmt.setString(index++, dto.getRept_prd());
			pstmt.setString(index++, dto.getRept_tmpl_real_file());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}	

	public int insertReportStatisticsInfo(Connection conn, ReportTemplateStatistics_DTO dto) throws Exception  {

		PreparedStatement pstmt = null;
		String query = "INSERT INTO ISS_REPT_STAT_INFO(REPT_STAT_ID, REPT_TMPL_ID, START_ROW, END_ROW, START_COL, END_COL, DATA_TYPES, SQL, SHEET_NO) "
			 		+ " VALUES(?,?,?,?,?,?,?,?,?)";
		int result = -1;
		
		try {
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, dto.getRept_stat_id());
			pstmt.setString(index++, dto.getRept_tmpl_id());
			pstmt.setString(index++, dto.getStart_row());
			pstmt.setString(index++, dto.getEnd_row());
			pstmt.setString(index++, dto.getStart_col());
			pstmt.setString(index++, dto.getEnd_col());
			pstmt.setString(index++, dto.getData_type());
			pstmt.setString(index++, dto.getSql());
			pstmt.setString(index++, dto.getSheet_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}
	
	public int deleteReportTemplateInfo(Connection conn, String rept_tmpl_id) throws Exception  {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM ISS_REPT_TMPL_INFO WHERE REPT_TMPL_ID = ?";
		int result = -1;
		
		try {
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, rept_tmpl_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public int deleteReportStatisticsInfo(Connection conn, String rept_tmpl_id) throws Exception  {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM ISS_REPT_STAT_INFO WHERE REPT_TMPL_ID = ?";
		int result = -1;
		
		try {
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, rept_tmpl_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}

	public int deleteReportFileInfo(Connection conn, String rept_id) throws Exception  {
		
		PreparedStatement pstmt = null;
		String query = "DELETE FROM ISS_REPT_FILE_INFO WHERE REPT_ID = ?";
		int result = -1;
		
		try {
			
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, rept_id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				}
			} catch (SQLException sqle) {}
		}
		return result;				
	}

	public ReportTemplate_DTO getReportTemplateInfo(Connection conn, String rept_tmpl_id) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT REPT_TMPL_ID, REPT_TMPL_NM, REPT_TMPL_FILE_NM, REPT_PRD, REPT_TMPL_REAL_FILE" +
				" FROM ISS_REPT_TMPL_INFO WHERE REPT_TMPL_ID = ?";
		ReportTemplate_DTO result = new ReportTemplate_DTO();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rept_tmpl_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result.setRept_tmpl_id(rs.getString(1));
				result.setRept_tmpl_nm(rs.getString(2));
				result.setRept_tmpl_file_nm(rs.getString(3));
				result.setRept_prd(rs.getString(4));
				result.setRept_tmpl_real_file(rs.getString(5));
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

	public ArrayList getReportStatisticsInfo(Connection conn, String rept_tmpl_id) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT REPT_STAT_ID, REPT_TMPL_ID, START_ROW, END_ROW, START_COL, END_COL, DATA_TYPES, SQL, SHEET_NO " +
				" FROM ISS_REPT_STAT_INFO WHERE REPT_TMPL_ID = ? ORDER BY REPT_STAT_ID";
		ArrayList result = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rept_tmpl_id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ReportTemplateStatistics_DTO dto = new ReportTemplateStatistics_DTO();
				dto.setRept_stat_id(rs.getString(1));
				dto.setRept_tmpl_id(rs.getString(2));
				dto.setStart_row(rs.getString(3));
				dto.setEnd_row(rs.getString(4));
				dto.setStart_col(rs.getString(5));
				dto.setEnd_col(rs.getString(6));
				dto.setData_type(rs.getString(7));
				dto.setSql(rs.getString(8));
				dto.setSheet_no(rs.getString(9));
				result.add(dto);
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

	public ReportFileInfo_DTO getReportFileInfo(Connection conn, String rept_id) throws Exception  {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT REPT_ID, REPT_TMPL_ID, REPT_NM, REG_DT, REPT_FILE_NM" +
				" FROM ISS_REPT_FILE_INFO WHERE REPT_ID = ?";
		ReportFileInfo_DTO result = new ReportFileInfo_DTO();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rept_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				result.setRept_id(rs.getString(1));
				result.setRept_tmpl_id(rs.getString(2));
				result.setRept_nm(rs.getString(3));
				result.setReg_dt(rs.getString(4));
				result.setRept_file_nm(rs.getString(5));
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

	public ArrayList getReportFileList(Connection conn, String rept_tmpl_name, String rept_nm, String eventTimeFrom, String eventTimeTo, int start, int end) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT * FROM ( ")
			 .append("	SELECT ROWNUM RN, REPT_ID, REPT_TMPL_ID, REPT_NM, REG_DT, REPT_FILE_NM, REPT_TMPL_NM ")
			 .append("		FROM ( ")
			 .append("			SELECT A.REPT_ID, A.REPT_TMPL_ID, A.REPT_NM, A.REG_DT, A.REPT_FILE_NM, B.REPT_TMPL_NM ") 
			 .append("				FROM ISS_REPT_FILE_INFO A, ISS_REPT_TMPL_INFO B ")
			 .append("				WHERE A.REPT_TMPL_ID = B.REPT_TMPL_ID ");
		
		if(eventTimeFrom != null){
			query.append("			AND SUBSTR(A.REG_DT,1,8) >= ? ");
		}
			
		if(eventTimeTo != null){
			query.append("			AND SUBSTR(A.REG_DT,1,8) <= ? ");
		}
			
		if(rept_nm != null){
			query.append("			AND A.REPT_NM LIKE ? ");
		}
			
		if(rept_tmpl_name != null){
			query.append("			AND B.REPT_TMPL_NM LIKE ? ");
		}
		
		query.append("				ORDER BY A.REPT_ID DESC ")
			 .append("			) ")
			 .append("	) WHERE RN BETWEEN ? AND ? ");
			
		ArrayList result = new ArrayList(); 
		
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			
			if(eventTimeFrom != null){
				pstmt.setString(index++, eventTimeFrom);
			}
				
			if(eventTimeTo != null){
				pstmt.setString(index++, eventTimeTo);
			}
				
			if(rept_nm != null){
				pstmt.setString(index++, rept_nm);
			}
				
			if(rept_tmpl_name != null){
				pstmt.setString(index++, rept_tmpl_name);
			}
				
			pstmt.setInt(index++, start);
			pstmt.setInt(index++, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ReportFileInfo_DTO dto = new ReportFileInfo_DTO();
				dto.setRownum(rs.getString(1));
				dto.setRept_id(rs.getString(2));
				dto.setRept_tmpl_id(rs.getString(3));
				dto.setRept_nm(rs.getString(4));
				dto.setReg_dt(rs.getString(5));
				dto.setRept_file_nm(rs.getString(6));
				dto.setRept_tmpl_nm(rs.getString(7));
				result.add(dto);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				} 
				
				if(rs != null){
					rs.close();
				} 
			} catch (SQLException sqle) {}
		}
		return result;
	}

	public int getReportFileTotal(Connection conn, String rept_tmpl_nm, String rept_nm, String eventTimeFrom, String eventTimeTo) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT COUNT(*) ") 
			 .append("	FROM ISS_REPT_FILE_INFO A, ISS_REPT_TMPL_INFO B ")
			 .append("	WHERE A.REPT_TMPL_ID = B.REPT_TMPL_ID ");
		
		if(eventTimeFrom != null){
			query.append("		AND SUBSTR(A.REG_DT,1,8) >= ? ");
		}
			
		if(eventTimeTo != null){
			query.append("		AND SUBSTR(A.REG_DT,1,8) <= ? ");
		}
			
		if(rept_nm != null){
			query.append("		AND A.REPT_NM LIKE ? ");
		}
			
		if(rept_tmpl_nm != null){
			query.append("		AND B.REPT_TMPL_NM LIKE ?");
		}

		int result = -1; 
		
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			
			if(eventTimeFrom != null){
				pstmt.setString(index++, eventTimeFrom);
			}
				
			if(eventTimeTo != null){
				pstmt.setString(index++, eventTimeTo);
			}
				
			if(rept_nm != null){
				pstmt.setString(index++, rept_nm);
			}
				
			if(rept_tmpl_nm != null){
				pstmt.setString(index++, rept_tmpl_nm);
			}
				
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(pstmt != null){
					pstmt.close();
				}
				
				if(rs != null){
					rs.close();
				}
			} catch (SQLException sqle) {}
		}
		return result;
	}

	public ArrayList getTableList(Connection conn, String table_org_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.TABLE_ID, B.TABLE_NAME, B.ALIAS, DECODE(B.AGGR_RESULT_TYPE,1,'시간',2,'일간',3,'주간',4,'월간') ")
			.append("	FROM ISS_SVC_TABLE_ORG A, ISS_STAT_TABLE_LIST B ")
			.append("	WHERE A.TABLE_ORG_ID = ? AND A.TABLE_ID = B.TABLE_ID ");
		ArrayList result = new ArrayList(); 
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			pstmt.setString(index++, table_org_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				TableInfo_DTO dto = new TableInfo_DTO();
				dto.setTable_id(rs.getString(1));
				dto.setTable_name(rs.getString(2));
				dto.setAlias(rs.getString(3));
				dto.setAggr_result_type(rs.getString(4));
				result.add(dto);
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

	public ArrayList getColumnList(Connection conn, String table_id) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.COL_NAME, B.ALIAS, B.LEN, B.DESCRIPTION, DECODE(B.DATA_TYPE,1,'코드',2,'숫자',3,'날짜',4,'문자') ")
			.append("	FROM ISS_STAT_TABLE_INFO A, ISS_STAT_COLUMN_INFO B ")
			.append("	WHERE A.TABLE_ID = ? AND A.VISIBLE = 1 AND A.COL_NAME = B.COL_NAME");
		ArrayList result = new ArrayList(); 
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			int index = 1;
			pstmt.setString(index++, table_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ColumnInfo_DTO dto = new ColumnInfo_DTO();
				dto.setCol_name(rs.getString(1));
				dto.setAlias(rs.getString(2));
				dto.setLen(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setData_type(rs.getString(5));
				result.add(dto);
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
	
  public void fillSheet(Connection conn, ReportTemplateStatistics_DTO dto, String[] in_param, HSSFSheet sheet, String date_fld)
  throws Exception
  {
	int startRow = Integer.parseInt(dto.getStart_row());
	int endRow = Integer.parseInt(dto.getEnd_row());
	int startCol = Integer.parseInt(dto.getStart_col());
	int endCol = Integer.parseInt(dto.getEnd_col());
	
	if( startRow == 2500) conn = DBDataSource.getCon(2);
	if( startRow == 2400) conn = DBDataSource.getCon(0);
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try
    {
      pstmt = conn.prepareStatement(dto.getSql());
      for (int i = 0; (in_param != null) && (i < in_param.length); ++i) {
        if (in_param[i].length() > 0) {
          pstmt.setString(i + 1, in_param[i]);
        }

      }

      int matchCount = 0;
      for (int sqlLength = 0; sqlLength < dto.getSql().toString().length(); ++sqlLength) {
        if (dto.getSql().toString().charAt(sqlLength) == '?') {
          ++matchCount;
        }

      }

      if (matchCount > 0) {
        for (int idx = 1; idx <= matchCount; ++idx) {
          pstmt.setString(idx, date_fld);
        }
      }

      rs = pstmt.executeQuery();
      int columnCount = rs.getMetaData().getColumnCount();
      
           int rowNum = startRow;
      while(rs.next()){
    	HSSFRow row = sheet.getRow(rowNum - 1);
    	
    	if( row != null){
	        int i = 1;
	        for (int colNum = startCol; (colNum <= endCol) && (i <= columnCount); ++colNum) {
	          HSSFCell cell = row.getCell((short)(colNum - 1));
	          if (cell == null)
	            cell = row.createCell((short)(colNum - 1));
	          char ch = dto.getData_type().charAt(i - 1);
	          if (ch == '1')
	          {
	            cell.setCellValue(rs.getDouble(i));
	          } else if (ch == '2')
	          {
	            cell.setCellValue(rs.getDate(i));
	          }
	          else {
	            String str = rs.getString(i);
	            cell.setEncoding((short) 1);
	            cell.setCellValue(str);
	          }
	          ++i;
	        }
	        ++rowNum;
	        if (rowNum >= endRow) break;
	      }
      }
    }
    catch (Exception e)
    {
      throw e;
    } finally {
      try {
        if (pstmt != null) pstmt.close();
        if (rs == null) rs.close();
      } catch (SQLException localSQLException) {
      }
    }
  }

	public int insertReportFileInfo(Connection conn, ReportFileInfo_DTO dto) throws Exception  {

		PreparedStatement pstmt = null;
		String query = "INSERT INTO ISS_REPT_FILE_INFO(REPT_ID, REPT_TMPL_ID, REPT_NM, REG_DT, REPT_FILE_NM) VALUES(?,?,?,TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),?)";
		int result = -1;
		
		try {
			int index = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(index++, dto.getRept_id());
			pstmt.setString(index++, dto.getRept_tmpl_id());
			pstmt.setString(index++, dto.getRept_nm());
			pstmt.setString(index++, dto.getRept_file_nm());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException sqle) {}
		}
		
		return result;				
	}	
}
