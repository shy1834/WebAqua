/*
 * Created on 2003. 10. 2.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.sql.*;
import java.util.*;
//import com.ktf.iss.Abstract_BO;
import com.ktf.iss.etc.util.StringUtil;
import java.text.*;
 
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsPage_BO {

	private Connection conn = null;
	private String jdbc_url = "jdbc:oracle:thin:@172.31.34.50:1521:AQUAORA"; //데이터베이스 URL
	private String db_id = "aquauser02"; //데이터베이스 계정
	private String db_pwd = "aqua1234"; //데이터베이스 비밀 번호
	
	private StatisticsPage_DAO pageDAO;
	
	public StatisticsPage_BO() {
		super();
		pageDAO = new StatisticsPage_DAO();
	}
	
	public ArrayList getPageList(int page, int rows) throws Exception {
		return getPageList(null, null, null, null, page, rows);
	}
	
	public ArrayList getPageList(String page_nm, 
		String stat_tp_id, 
		String table_name, 
		String reg_yn, 
		int page, 
		int rows) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			int start = (page - 1) * rows + 1;
			int end = page * rows;
			if(page_nm != null)
				page_nm = "%" + page_nm + "%";
			if(table_name != null)
				table_name = "%" + table_name + "%";

			al = pageDAO.getPageList(conn, page_nm, stat_tp_id, table_name, reg_yn, start, end);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public int getTotalPage() throws Exception {
		return getTotalPage(null, null, null, null); 
	}

	public int getTotalPage(String page_nm, 
		String stat_tp_id,  
		String table_name,
		String reg_yn) throws Exception {

		Connection conn = null;
		int result = -1;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			if(page_nm != null)
				page_nm = "%" + page_nm + "%";
			if(table_name != null)
				table_name = "%" + table_name + "%";

			result = pageDAO.getTotalPage(conn, page_nm, stat_tp_id, table_name, reg_yn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;		
	}
	
	public ArrayList getStatisticsTypeList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getStatisticsTypeList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getPlatformList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getPlatformList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getDataList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getDataList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getStatisticsGroupList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getStatisticsGroupList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getCPList(String table_id) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getCPList(conn, table_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getCPServiceList(String table_id) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getCPServiceList(conn, table_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public ArrayList getServiceList(String table_id)  throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getServiceList(conn, table_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public String getStatisticsTypeAdminURL(String stat_tp_id) throws Exception {

		String url = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			url = pageDAO.getStatisticsTypeAdminURL(conn, stat_tp_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return url;
	}

	public String getStatisticsTypeModifyURL(String stat_tp_id) throws Exception {

		String url = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			url = pageDAO.getStatisticsTypeModifyURL(conn, stat_tp_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return url;
	}
/*
	public ArrayList getColumnList(String table_id) throws Exception {
		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getColumnList(conn, table_id);
			for(int i = 0; i < al.size(); i++) {
				StatisticsColumn_DTO column = (StatisticsColumn_DTO)al.get(i);
				if(column.getData_type() == 3) {
					column.setFormatString("YYYY-MM-DD HH24시");
				} else if(column.getData_type() == 2) {
					column.setFormatString("#,##0");
				} else if(column.getData_type() != 1) {
					column.setFormatString("");
				}
			}
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;		
	}
*/
    public ArrayList getColumnList(String table_id) throws Exception {
		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getColumnList(conn, table_id);
			for(int i = 0; i < al.size(); i++) {
				StatisticsColumn_DTO column = (StatisticsColumn_DTO)al.get(i);
				if(column.getData_type() == 3) {
					column.setFormatString("YYYY-MM-DD HH24시");
				} else if(column.getData_type() == 2) {
					column.setFormatString("#,##0");
				} else if(column.getData_type() != 1) {
					column.setFormatString("");
				}
			}
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;		
	}
	public String getTableAlias(String table_id) throws Exception {
		
		String alias = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			alias = pageDAO.getTableAlias(conn, table_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return alias;		
	}

	public ArrayList getCodeList(String code_category) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getCodeList(conn, code_category);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}
	
	public String getStatisticsTypeName(String stat_tp_id) throws Exception {
		String alias = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			alias = pageDAO.getStatisticsTypeName(conn, stat_tp_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return alias;		
	}

	public String getTermName(String dt_tp, String dt_tp_usr_input) throws Exception {

		String dt_tp_nm = "";
		
		if(dt_tp_usr_input.equals("Y"))
			return "사용자 입력";
			
		if(dt_tp.equals("01")) {
			dt_tp_nm = "일간";
		} else if(dt_tp.equals("02")) {
			dt_tp_nm = "주간";
		} else if(dt_tp.equals("03")) {
			dt_tp_nm = "월간";
		} else if(dt_tp.equals("04")) {
			dt_tp_nm = "분기";
		} else if(dt_tp.equals("05")) {
			dt_tp_nm = "년간";
		}
		
		return dt_tp_nm;		
	}
	
	public ArrayList getStatisticsData(String query, String[] args,StatisticsPage_DTO spDTO, StatisticsPageDetail_DTO[] spdDTO, boolean isDataQuery, int maxCount) throws Exception {
		Connection conn = null;
		ArrayList result = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			
			HashMap formats = new HashMap();
			HashMap dataTypes = new HashMap();
			
			String eventTimeFormat = "yyyy-MM-dd";
			if(spDTO.getDt_tp().equals("00")) {
				eventTimeFormat = "yyyy-MM-dd HH시";
			} else if(spDTO.getDt_tp().equals("01")) {
				if(isDataQuery) {
					eventTimeFormat = "HH시";
				} else {
					eventTimeFormat = "yyyy-MM-dd";
				}
			} else if(spDTO.getDt_tp().equals("02")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM-dd";
				} else {
					eventTimeFormat = "yyyy-MM W주차";
				}
			} else if(spDTO.getDt_tp().equals("03")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM-dd";
				} else {
					eventTimeFormat = "yyyy-MM";
				}
			} else if(spDTO.getDt_tp().equals("04")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM";
				} else {
					eventTimeFormat = "yyyy년 {MM}분기";
				}
			} else if(spDTO.getDt_tp().equals("05")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM";
				} else {
					eventTimeFormat = "yyyy";
				}
			}

			for(int j = 0; j < spdDTO.length; j++) {
				dataTypes.put(spdDTO[j].getCol_name(), spdDTO[j].getData_type());
				if(spdDTO[j].getData_type().equals("2")) {
					java.text.DecimalFormat df = new java.text.DecimalFormat(spdDTO[j].getFormat());
					formats.put(spdDTO[j].getCol_name(),df);
				} else if(spdDTO[j].getData_type().equals("3")) {
					if(!spdDTO[j].getCol_id().equals(spDTO.getDt_col_id())) {
						String format = convertDateFormat(spdDTO[j].getFormat());
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
						formats.put(spdDTO[j].getCol_name(),sdf);
					} else {
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(eventTimeFormat);
						formats.put(spdDTO[j].getCol_name(),sdf);						
					}
				} else {
					formats.put(spdDTO[j].getCol_name(),spdDTO[j].getFormat());
				}
			}

			result = pageDAO.getStatisticsData(conn, query, args, dataTypes, formats, maxCount);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}
		return result; 
	}

	public ArrayList getStatisticsData(String query, String[] args,StatisticsPage_DTO spDTO, StatisticsPageDetail_DTO[] spdDTO, boolean isDataQuery) throws Exception {
		return getStatisticsData(query, args, spDTO, spdDTO, isDataQuery, -1);
	}

	public ArrayList getStatisticsData(String dataQuery, 
										String[] dataArgs, 
										String compareQuery, 
										String[] compareArgs,
										StatisticsPage_DTO spDTO, 
										StatisticsPageDetail_DTO[] dataSpdDTO, 
										StatisticsPageDetail_DTO[] compareSpdDTO,
										StatisticsPageCompare_DTO[] spcomDTO,
										boolean isDataQuery) throws Exception {
											
		ArrayList result1 = getStatisticsData(dataQuery, dataArgs, spDTO, dataSpdDTO, isDataQuery, -1);
		spDTO.setDt_col_id(spcomDTO[1].getDt_col_id());
		ArrayList result2 = getStatisticsData(compareQuery, compareArgs, spDTO, compareSpdDTO, isDataQuery, -1);
		spDTO.setDt_col_id(spcomDTO[0].getDt_col_id());
		
		Connection conn = null;
		ArrayList result = new ArrayList();
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);

			String logDateFieldName1 = pageDAO.getColumnName(conn, spcomDTO[0].getDt_col_id());
			String logDateFieldName2 = pageDAO.getColumnName(conn, spcomDTO[1].getDt_col_id());
			
			int j = 0, k = 0;
			StatisticsService_DTO dto1 = null;
			Object[] keys1 = null;
			HashMap defaultValues1 = makeDefaultValues(dataSpdDTO);
			if(result1.size() > j) {
				dto1 = (StatisticsService_DTO)result1.get(j);
				keys1 = dto1.getColumnNameArray();
			}

			StatisticsService_DTO dto2 = null;
			Object[] keys2 = null;
			HashMap defaultValues2 = makeDefaultValues(compareSpdDTO);
			if(result2.size() > k) {
				dto2 = (StatisticsService_DTO)result2.get(k);
				keys2 = dto2.getColumnNameArray();
			}
			for(int i = 0; result1.size() > j || result2.size() > k; i++) {
				if(j < result1.size())
					dto1 = (StatisticsService_DTO)result1.get(j);
				else
					dto1 = null;

				String eventTime1 = null;
				if(dto1 != null)
					eventTime1 = (String)dto1.getValue(logDateFieldName1);

				if(k < result2.size())
					dto2 = (StatisticsService_DTO)result2.get(k);
				else
					dto2 = null;
				String eventTime2 = null;
				if(dto2 != null)
					eventTime2 = (String)dto2.getValue(logDateFieldName2);
				
				StatisticsService_DTO dto = new StatisticsService_DTO();
				if(eventTime1 != null && eventTime2 != null && eventTime1.equals(eventTime2)) {
					dto.setValue(logDateFieldName1, eventTime1);
					for(int l = 0; l < keys1.length; l++) {
						dto.setValue(keys1[l] + "_1", dto1.getValue((String)keys1[l]));
						dto.setValue(keys1[l] + "_1", dto1.getNumberValue((String)keys1[l]).doubleValue());
					}
					for(int l = 0; l < keys2.length; l++) {
						dto.setValue(keys2[l] + "_2", dto2.getValue((String)keys2[l]));
						dto.setValue(keys2[l] + "_2", dto2.getNumberValue((String)keys2[l]).doubleValue());
					}
					j++;
					k++;
				} else if(eventTime1 != null && eventTime2 != null
						&& StringUtil.compareString(eventTime1, eventTime2) > 0) {
					dto.setValue(logDateFieldName1, eventTime2);
					for(int l = 0; l < dataSpdDTO.length; l++) {
						dto.setValue(dataSpdDTO[l].getCol_name() + "_1", (String)defaultValues1.get(dataSpdDTO[l].getCol_name()));
						dto.setValue(dataSpdDTO[l].getCol_name() + "_1", 0);
					}
					for(int l = 0; l < keys2.length; l++) {
						dto.setValue(keys2[l] + "_2", dto2.getValue((String)keys2[l]));
						dto.setValue(keys2[l] + "_2", dto2.getNumberValue((String)keys2[l]).doubleValue());
					}
					k++;
				} else if(eventTime1 != null && eventTime2 != null
						&& StringUtil.compareString(eventTime1, eventTime2) < 0) {
					dto.setValue(logDateFieldName1, eventTime1);
					for(int l = 0; l < keys1.length; l++) {
						dto.setValue(keys1[l] + "_1", dto1.getValue((String)keys1[l]));
						dto.setValue(keys1[l] + "_1", dto1.getNumberValue((String)keys1[l]).doubleValue());
					}
					for(int l = 0; l < compareSpdDTO.length; l++) {
						dto.setValue(compareSpdDTO[l].getCol_name() + "_2", (String)defaultValues2.get(compareSpdDTO[l].getCol_name()));
						dto.setValue(compareSpdDTO[l].getCol_name() + "_2", 0);
					}
					j++;
				} else if(eventTime1 == null && eventTime2 != null) {
					dto.setValue(logDateFieldName1, eventTime2);
					for(int l = 0; l < dataSpdDTO.length; l++) {
						dto.setValue(dataSpdDTO[l].getCol_name() + "_1", (String)defaultValues1.get(dataSpdDTO[l].getCol_name()));
						dto.setValue(dataSpdDTO[l].getCol_name() + "_1", 0);
					}
					for(int l = 0; l < keys2.length; l++) {
						dto.setValue(keys2[l] + "_2", dto2.getValue((String)keys2[l]));
						dto.setValue(keys2[l] + "_2", dto2.getNumberValue((String)keys2[l]).doubleValue());
					}
					k++;
				} else if(eventTime1 != null && eventTime2 == null) {
					dto.setValue(logDateFieldName1, eventTime1);
					for(int l = 0; l < keys1.length; l++) {
						dto.setValue(keys1[l] + "_1", dto1.getValue((String)keys1[l]));
						dto.setValue(keys1[l] + "_1", dto1.getNumberValue((String)keys1[l]).doubleValue());
					}
					for(int l = 0; l < compareSpdDTO.length; l++) {
						dto.setValue(compareSpdDTO[l].getCol_name() + "_2", (String)defaultValues2.get(compareSpdDTO[l].getCol_name()));
						dto.setValue(compareSpdDTO[l].getCol_name() + "_2", 0);
					}
					j++;
				} else {
					break;
				}
				result.add(dto);
				//log.info("j="+j+",k="+k);
			}
		
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return result;
	}
		
	public static String convertDateFormat(String orgFormat) throws Exception {
		
		/*
		 * yyyy	Year  Year  1996; 96  				: YYYY
		 * MM	Month in year  Month  July; Jul; 07	: MM
		 * ww	Week in year  Number  27  			: IW,WW
		 * DDD	Day in year  Number  189  			: DDD
		 * dd	Day in month  Number  10  			: DD 
		 * F	Day of week in month  Number  2		: D
		 * HH	Hour in day (0-23)  Number  0 		: HH24 
		 * hh	Hour in am/pm (1-12)  Number  12  	: HH,HH12
		 * mm	Minute in hour  Number  30  		: MI
		 * ss	Second in minute  Number  55  		: SS
		 * Q	Quater in year						: Q
		 */
		String[] oracleFormatElements = {"YYYY", "YY", "IW", "WW", "DD", "D",
										"HH24", "HH12", "HH", "MI", "SS", "Q"};
		String[] javaFormatElements = {"yyyy", "yy", "ww", "ww", "dd", "F",
										"HK", "hh", "hh", "mm", "ss", "{MM}"};
		String tmpFormat = orgFormat;
		for(int i = 0; i < oracleFormatElements.length; i++) {
			int start = tmpFormat.indexOf(oracleFormatElements[i]);
			while(start > -1) {
				int end = start + oracleFormatElements[i].length();
				String tmp1Format = "";
				String tmp3Format = "";
				if(start > 0 )
					tmp1Format = tmpFormat.substring(0, start);
				if(end < tmpFormat.length())
					tmp3Format = tmpFormat.substring(end);
				
				tmpFormat = tmp1Format + javaFormatElements[i] + tmp3Format;

				start = tmpFormat.indexOf(oracleFormatElements[i]);
			}
		}
		
		int start = tmpFormat.indexOf("HK");
		if(start > -1) {
			int end = start + 2;
			String tmp1Format = "";
			String tmp3Format = "";
			if(start > 0 )
				tmp1Format = tmpFormat.substring(0, start);
			if(end < tmpFormat.length())
				tmp3Format = tmpFormat.substring(end);
				
			tmpFormat = tmp1Format + "HH" + tmp3Format;
		}
		
		return tmpFormat;
	}
	
	public String makeTermString(String inqr_begin_dt, String dt_tp, String dt_usr_input) throws Exception {
	
		String result = "";
		
		if(dt_usr_input.equals("Y"))
			return "사용자 입력";
	
		if(dt_tp.equals("01")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4, 6) + "/"
								+ inqr_begin_dt.substring(6); 
		} else if(dt_tp.equals("02")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4, 6) + "/"
								+ inqr_begin_dt.substring(6); 
		} else if(dt_tp.equals("03")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4); 
		} else if(dt_tp.equals("04")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4); 
		} else if(dt_tp.equals("05")) {
			result = inqr_begin_dt; 
		}
		
		return result;
	}

	public String makeTermString(String inqr_begin_dt,String inqr_end_dt, String dt_tp, String dt_usr_input) throws Exception {
	
		String result = "";
	
		if(dt_usr_input.equals("Y"))
			return "사용자 입력";

		if(dt_tp.equals("01")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4, 6) + "/"
								+ inqr_begin_dt.substring(6)
					+ " ~ "
					+ inqr_end_dt.substring(0, 4) + "/" 
											+ inqr_end_dt.substring(4, 6) + "/"
											+ inqr_end_dt.substring(6);			 
		} else if(dt_tp.equals("02")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4, 6) + "/"
								+ inqr_begin_dt.substring(6)
				   + " ~ "
				   + inqr_end_dt.substring(0, 4) + "/" 
								+ inqr_end_dt.substring(4, 6) + "/"
								+ inqr_end_dt.substring(6); 
		} else if(dt_tp.equals("03")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4)
				   + inqr_end_dt.substring(0, 4) + "/" 
								+ inqr_end_dt.substring(4); 
		} else if(dt_tp.equals("04")) {
			result = inqr_begin_dt.substring(0, 4) + "/" 
								+ inqr_begin_dt.substring(4)
				   + " ~ "
				   + inqr_end_dt.substring(0, 4) + "/" 
								+ inqr_end_dt.substring(4); 
		} else if(dt_tp.equals("05")) {
			result = inqr_begin_dt; 
		}
	
		return result;
	}

	public void convertFieldNameToColumnName(StatisticsPageDetail_DTO[] spdDTO) throws Exception {
		convertFieldNameToColumnName(spdDTO, null);
	}

	public void convertFieldNameToColumnName(StatisticsPageDetail_DTO[] spdDTO, String tableAlias) throws Exception {
		for(int i = 0; i < spdDTO.length; i++) {
			if(spdDTO[i].getUser_defined_info() != null 
				&& spdDTO[i].getUser_defined_info().length() > 0 ) {
					for(int j = 0; j < spdDTO.length; j++) {
						String alias = "";
						if(tableAlias != null)
							alias = tableAlias+ "."; 
						String expression = StringUtil.replace(spdDTO[i].getUser_defined_info(), 
																"${"+spdDTO[j].getField_nm()+"}", 
																"${"+alias + spdDTO[j].getCol_name()+"}");
						spdDTO[i].setUser_defined_info(expression);
						//System.out.println("$$$ convertFieldNameToColumnName -> field_name ["+spdDTO[j].getField_nm()+"]");
						//System.out.println("$$$ convertFieldNameToColumnName -> col_name["+alias + spdDTO[j].getCol_name()+"]");
						//System.out.println("$$$ convertFieldNameToColumnName -> expression["+expression+"]");
					}
			}
		}
	}

	public ArrayList getMenuItemList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			al = dao.getMenuItemList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}	

	public ArrayList getMenuGroupList() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			al = dao.getMenuGroupList(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}

	public int insertMenuItem(MenuItem_DTO miDTO) throws Exception {

		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			String menu_item_id = dao.getNextMenuItemID(conn);
			String actionURL = pageDAO.getStatisticsActionURL(conn, miDTO.getPage_id())
								+ "?first=Y&menu_item_id="+ menu_item_id;
			miDTO.setMenu_item_url(actionURL);
			miDTO.setMenu_item_id(menu_item_id);
			result = dao.insertMenuItem(conn, miDTO);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {}

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public int insertMenuFolder(MenuItem_DTO miDTO) throws Exception {

		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			result = dao.insertMenuFolder(conn, miDTO);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {}

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public int updateMenuItem(MenuItem_DTO miDTO) throws Exception {

		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			result = dao.updateMenuItem(conn, miDTO);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {}

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public int deleteMenuItem(String menu_item_id) throws Exception {

		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			result = dao.deleteMenuItem(conn, menu_item_id);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {}

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public int deleteMenuFolder(String menu_item_id) throws Exception {

		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			result = dao.deleteMenuFolder(conn, menu_item_id);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {}

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public MenuItem_DTO getMenuItem(String menu_item_id, boolean isFolder) throws Exception {

		MenuItem_DTO result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			StatisticsMenu_DAO dao = new StatisticsMenu_DAO();
			result = dao.getMenuItem(conn, menu_item_id, isFolder);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}
	
/*
	public void saveStatisticsPage(StatisticsPage_DTO spDTO, 
									StatisticsPageDetail_DTO[] spdDTO, 
									StatisticsPageCondition_DTO[] spcDTO,
									StatisticsPageCompare_DTO[] spcomDTO,
									String serviceDirectory) throws Exception {
		
		Connection conn = null;
	
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn.setAutoCommit(false);
			
			if(spDTO.getPage_id() != null && spDTO.getPage_id().length() > 0) {
				pageDAO.deleteStatisticsPage(conn, spDTO.getPage_id(), false);
			} else {
				String page_id = pageDAO.getNextPageID(conn);
				spDTO.setPage_id(page_id);
			}
			spDTO.setPage_loc(serviceDirectory);
			pageDAO.insertStatisticsPage(conn, spDTO);
			for(int i = 0; i < spdDTO.length; i++) {
				spdDTO[i].setPage_id(spDTO.getPage_id());
				pageDAO.insertStatisticsPageDetail(conn, spdDTO[i], i);
			}
			for(int i = 0; i < spcDTO.length; i++) {
				spcDTO[i].setPage_id(spDTO.getPage_id());
				pageDAO.insertStatisticsPageCondition(conn, spcDTO[i]);
			}
			for(int i = 0; i < spcomDTO.length; i++) {
				spcomDTO[i].setPage_id(spDTO.getPage_id());
				pageDAO.insertStatisticsPageCompare(conn, spcomDTO[i], i);
			}

			PageCreator_BO dpcBO = new PageCreator_BO();
			String templateURL = pageDAO.getTemplateURL(conn, spDTO.getStat_tp());
			dpcBO.create(templateURL, spDTO.getPage_loc(), spDTO, spdDTO, spcDTO, spcomDTO);	
			
			conn.commit();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch(SQLException sqle) {}
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}		
	}
*/

	public String makeGraphInitData(int rows) throws Exception {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < rows; i++) {
			sb.append(',').append(0);
		}
		return sb.toString();
	}

	public int deleteStatisticsPage(String page_id, boolean needMenuItemDelete) throws Exception {


		int result = -1;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.deleteStatisticsPage(conn, page_id, needMenuItemDelete);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public StatisticsPage_DTO getPageInfo(String page_id) throws Exception {

		StatisticsPage_DTO result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageInfo(conn, page_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}
/*
	public ArrayList getPageDetailQm(String page_id, boolean isModify, String table_id) throws Exception {

		ArrayList result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageDetailQm(conn, page_id, isModify, table_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}
*/
	public ArrayList getPageDetail(String page_id) throws Exception {

		ArrayList result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageDetail(conn, page_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}
	
	public ArrayList getPageDetail(String page_id, boolean isModify) throws Exception {

		ArrayList result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageDetail(conn, page_id, isModify);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public ArrayList getPageDetail(String page_id, boolean isModify, boolean isCompare) throws Exception {

		ArrayList result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageDetail(conn, page_id, isModify, isCompare);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public ArrayList getPageCondition(String page_id) throws Exception {

		ArrayList result = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageCondition(conn, page_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}
	
	public String convertDailyDateAliasToDateTime(String dt_tp, String dateAlias) throws Exception {
		
		String result = null;
		try {
			String dateFormat = "yyyyMMdd";
			if(dt_tp.equals("03") || dt_tp.equals("04"))
				dateFormat = "yyyyMM";
			else if(dt_tp.equals("05"))
				dateFormat = "yyyy";
			
			if(dateAlias.equals("TODAY")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				result = sdf.format(new java.util.Date());
			} else if(dateAlias.equals("YESTERDAY")) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				java.util.Date date = new java.util.Date();
				date.setTime(date.getTime() - (1000*60*60*24));			
				result = sdf.format(date);
			} else {
				result = dateAlias;
			}
		} catch (Exception e) {

			throw e;
		}
		
		return result;
	}

	public void convertTermDateTime(StatisticsPage_DTO spDTO) throws Exception {
		
		try {
			if(spDTO.getInqr_begin_dt().charAt(0) == 'F' 
				&& spDTO.getInqr_begin_dt().charAt(1) == 'F'){

				String dateFormat = "yyyyMMdd";
				if(spDTO.getDt_tp().equals("00")) {
					dateFormat = "yyyyMMddHH";
				} else if(spDTO.getDt_tp().equals("03")) {
					dateFormat = "yyyyMM";
				}
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				//java.util.Date today = new java.util.Date();
				
				Calendar cal = Calendar.getInstance();
				if(!spDTO.getDt_tp().equals("00")) {
					cal.set(Calendar.HOUR_OF_DAY, 0);
				}
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				
				if(spDTO.getRef_flag() != null && spDTO.getRef_flag().equals("Y")) {
					String ref_dt = spDTO.getRef_dt();
					if(spDTO.getDt_tp().equals("00")) {
						ref_dt = ref_dt + spDTO.getInqr_end_dt().substring(spDTO.getInqr_end_dt().length()-2);
					}
					java.util.Date date = sdf.parse(ref_dt.substring(2));
					spDTO.setRef_dt("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));					
				}
				java.util.Date date = sdf.parse(spDTO.getInqr_begin_dt().substring(2));
				spDTO.setInqr_begin_dt("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));
				date = sdf.parse(spDTO.getInqr_end_dt().substring(2));
				spDTO.setInqr_end_dt("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));
			} else if(spDTO.getInqr_begin_dt().charAt(0) != 'F'){
				if(spDTO.getRef_flag() != null && spDTO.getRef_flag().equals("Y")) {
					String ref_dt = spDTO.getRef_dt();
					if(spDTO.getDt_tp().equals("00")) {
						spDTO.setRef_dt(ref_dt + spDTO.getInqr_end_dt().substring(spDTO.getInqr_end_dt().length()-2));
					}
				}
			}
		} catch (Exception e) {

			throw e;
		}
	}

/*
	public void converTermDateTimeCompare(StatisticsPageCompare_DTO[] spcomDTO, String dt_tp) throws Exception {
		
		try {
			String dateFormat = "yyyyMMdd";
			if(dt_tp.equals("03")) {
				dateFormat = "yyyyMM";
			}
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
			//java.util.Date today = new java.util.Date();
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			for(int i = 0 ; i < spcomDTO.length ; i ++){
				if(spcomDTO[i].getPast_dt5().charAt(0) == 'F' 
					&& spcomDTO[i].getPast_dt5().charAt(1) == 'F'){
					java.util.Date date = sdf.parse(spcomDTO[i].getPast_dt5().substring(2));
					spcomDTO[i].setPast_dt5("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));

				}
				if(spcomDTO[i].getPast_dt6().charAt(0) == 'F' 
					&& spcomDTO[i].getPast_dt6().charAt(1) == 'F'){
					java.util.Date date = sdf.parse(spcomDTO[i].getPast_dt6().substring(2));
					spcomDTO[i].setPast_dt6("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));
				}
				if(spcomDTO[i].getPast_dt7().charAt(0) == 'F' 
					&& spcomDTO[i].getPast_dt7().charAt(1) == 'F'){
					java.util.Date date = sdf.parse(spcomDTO[i].getPast_dt7().substring(2));
					spcomDTO[i].setPast_dt7("F" + String.valueOf(cal.getTimeInMillis() - date.getTime()));
				}
			}
		} catch (Exception e) {

			throw e;
		}
	}

	public void convertTermDateTime(StatisticsPageCompare_DTO spcomDTO, String dt_tp) throws Exception {
		
		try {
			spcomDTO.setPast_dt5(convertTermDateTime(spcomDTO.getPast_dt5(), dt_tp));
			spcomDTO.setPast_dt6(convertTermDateTime(spcomDTO.getPast_dt6(), dt_tp));
			spcomDTO.setPast_dt7(convertTermDateTime(spcomDTO.getPast_dt7(), dt_tp));
		} catch (Exception e) {

			throw e;
		}
	}
*/
	public String convertTermDateTime(String dt, String dt_tp) throws Exception {
		
		try {
			if(dt.charAt(0) == 'F' 
				&& dt.charAt(1) == 'F'){

				String dateFormat = "yyyyMMdd";
				if(dt_tp.equals("03"))
					dateFormat = "yyyyMM";
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				java.util.Date today = new java.util.Date();
				java.util.Date date = sdf.parse(dt.substring(2));
				dt = "F" + String.valueOf(today.getTime() - date.getTime());
			}
		} catch (Exception e) {

			throw e;
		}
		return dt;
	}

	public String convertTermRealDate(String dt_tp, String termDate) throws Exception {
		
		String result = null;
		try {
			String dateFormat = "yyyyMMdd";
			if(dt_tp.equals("00"))
				dateFormat = "yyyyMMddHH";
			else if(dt_tp.equals("03") || dt_tp.equals("04"))
				dateFormat = "yyyyMM";
			else if(dt_tp.equals("05"))
				dateFormat = "yyyy";

			if(termDate.charAt(0) == 'F') {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				java.util.Date today = new java.util.Date();
				long term = Long.parseLong(termDate.substring(1));
				today.setTime(today.getTime() - term);
				result = sdf.format(today);
			} else {
				result = termDate;
			}
		} catch (Exception e) {

			throw e;
		}
		
		return result;
	}
	
	public String convertTermRealDate(String dt_tp, String termDate, int refType) throws Exception {
		
		String result = null;
		try {
			String dateFormat = "yyyyMMdd";
			if(dt_tp.equals("00"))
				dateFormat = "yyyyMMddHH";
			else if(dt_tp.equals("03") || dt_tp.equals("04"))
				dateFormat = "yyyyMM";
			else if(dt_tp.equals("05"))
				dateFormat = "yyyy";

			if(termDate.charAt(0) == 'F') {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				java.util.Date today = new java.util.Date();
				long term = Long.parseLong(termDate.substring(1));
				today.setTime(today.getTime() - term);
				result = sdf.format(today);
			} else {
				result = termDate;
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(result));
			switch(refType) {
				case 0: //전일
					cal.add(Calendar.DATE, -1);
					break;
				case 1: //전주
					cal.add(Calendar.DATE, -7);
					break;
				case 2: //전월
					if(!dt_tp.equals("02")) { 
						cal.add(Calendar.MONTH, -1);
					} else {
						int week = cal.get(Calendar.WEEK_OF_MONTH);
						cal.add(Calendar.DATE, -28);
						if(week > cal.get(Calendar.WEEK_OF_MONTH))
							cal.add(Calendar.DATE, -7);
					}
					break;
				case 3: //전년
					cal.add(Calendar.YEAR, -1);
					break;
			}
			result = sdf.format(cal.getTime());
		} catch (Exception e) {

			throw e;
		}
		
		return result;
	}

	public String getServiceName(String svc_id) throws Exception {

		String result = "전체";
		Connection conn = null;
		try {
			try {
				Integer.parseInt(svc_id);
			} catch(NumberFormatException nfe) {
				return result;
			}
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getServiceName(conn, svc_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;
	}

	public ArrayList getPageCompare(String page_id) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getPageCompare(conn, page_id);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}
	
	private HashMap makeDefaultValues(StatisticsPageDetail_DTO[] spdDTO) throws Exception {
		
		HashMap defaultValues = new HashMap(); 
		for(int j = 0; j < spdDTO.length; j++) {
			if(spdDTO[j].getData_type().equals("2") || spdDTO[j].getGroup_func().equals("COUNT")) {
				java.text.DecimalFormat df = new java.text.DecimalFormat(spdDTO[j].getFormat());
				defaultValues.put(spdDTO[j].getCol_name(),df.format(0));
			} else {
				defaultValues.put(spdDTO[j].getCol_name(),"");
			}
		}
		
		return defaultValues;
	}
	
	public boolean hasStatisticsTable(String table_org_id, String dt_tp) throws Exception {

		Connection conn = null;
		boolean result = false;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.hasStatisticsTable(conn, table_org_id, dt_tp);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return result;		
	}

	public ArrayList getRootService() throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getRootService(conn);
		} catch (Exception e) {

			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}

		return al;
	}
}
