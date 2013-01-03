/*
 * Created on 2003. 10. 29.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.sql.*;
import java.util.*;
//import com.ktf.iss.Abstract_BO;
import com.ktf.iss.etc.util.StringUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsService_BO {

	private Connection conn = null;
	private String jdbc_url = "jdbc:oracle:thin:@172.31.34.50:1521:AQUAORA"; //데이터베이스 URL
	private String db_id = "aquauser02"; //데이터베이스 계정
	private String db_pwd = "aqua1234"; //데이터베이스 비밀 번호
	
	private static StatisticsService_BO bo;
	private StatisticsPage_DAO pageDAO;

	private StatisticsService_BO() {
		super();
		pageDAO = new StatisticsPage_DAO(); 
	}
	
	public static StatisticsService_BO getInstance() {
		if(bo == null)
			bo = new StatisticsService_BO();
		return bo;
	}
	
	public String getTableName(String page_id, String dt_tp, String inqr_dt, boolean isDataQuery) throws Exception {

		Connection conn = null;
		String tableName = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			String table_id = pageDAO.getPageTableID(conn, page_id);
			int dateType = Integer.parseInt(dt_tp);

			if(!isDataQuery)
				dateType += 1;
			else {
				if(dateType == 3 || dateType == 5)
					dateType -= 1;
			}
				
			tableName = pageDAO.getTableName(conn, table_id, dateType);
//			if(dateType == 1)
//				tableName = tableName + "_" + inqr_dt;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}

		return tableName;
	}

	public String getTableNameWithTableID(String table_id, String dt_tp, String inqr_dt, boolean isDataQuery) throws Exception {

		Connection conn = null;
		String tableName = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			int dateType = Integer.parseInt(dt_tp);
			if(!isDataQuery)
				dateType += 1;
			else {
				if(dateType == 3 || dateType == 5)
					dateType -= 1;
			}
			tableName = pageDAO.getTableName(conn, table_id, dateType);
//			if(dateType == 1)
//				tableName = tableName + "_" + inqr_dt;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}

		return tableName;
	}

	public ArrayList getStatisticsData(String query, 
										String[] args, 
										String[] columnNames, 
										String[] dataTypes, 
										String[] formats,
										String dt_col_name,
										String dt_tp,
										boolean isDataQuery,
										int maxCount)throws Exception {
		Connection conn = null;
		ArrayList result = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			
			HashMap formatMap = new HashMap();
			HashMap dataTypeMap = new HashMap();

			String eventTimeFormat = "yyyy-MM-dd";
			if(dt_tp.equals("01")) {
				if(isDataQuery) {
					eventTimeFormat = "HH시";
				} else {
					eventTimeFormat = "yyyy-MM-dd";
				}
			} else if(dt_tp.equals("02")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM-dd";
				} else {
					eventTimeFormat = "yyyy-MM W주차";
				}
			} else if(dt_tp.equals("03")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM-dd";
				} else {
					eventTimeFormat = "yyyy-MM";
				}
			} else if(dt_tp.equals("04")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM";
				} else {
					eventTimeFormat = "yyyy년 {MM}분기";
				}
			} else if(dt_tp.equals("05")) {
				if(isDataQuery) {
					eventTimeFormat = "yyyy-MM";
				} else {
					eventTimeFormat = "yyyy";
				}
			}

			for(int j = 0; j < columnNames.length; j++) {
				dataTypeMap.put(columnNames[j], dataTypes[j]);
				if(dataTypes[j].equals("2")) {
					java.text.DecimalFormat df = new java.text.DecimalFormat(formats[j]);
					formatMap.put(columnNames[j],df);
				} else if(dataTypes[j].equals("3")) {
					if(!columnNames[j].equals(dt_col_name)) {
						String format = StatisticsPage_BO.convertDateFormat(formats[j]);
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
						formatMap.put(columnNames[j],sdf);
					} else {
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(eventTimeFormat);
						formatMap.put(columnNames[j],sdf);
					}
				} else {
					formatMap.put(columnNames[j],formats[j]);
				}
			}

			result = pageDAO.getStatisticsData(conn, query, args, dataTypeMap, formatMap, maxCount);
		} catch (Exception e) {
			
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}
		return result; 
	}

	public ArrayList getStatisticsData(String query, 
										String[] args, 
										String[] columnNames, 
										String[] dataTypes, 
										String[] formats,
										String dt_col_name,
										String dt_tp,
										boolean isDataQuery)throws Exception {
		return getStatisticsData(query, args, columnNames, dataTypes, formats, dt_col_name, dt_tp, isDataQuery, -1);
	}

	public ArrayList getStatisticsData(String query1, 
										String[] arg1s, 
										String[] columnName1s, 
										String[] dataType1s, 
										String[] format1s,
										String query2, 
										String[] arg2s, 
										String[] columnName2s, 
										String[] dataType2s, 
										String[] format2s,
										String[] dt_col_names,
										String dt_tp,
										boolean isDataQuery) throws Exception {
											
		ArrayList result1 = getStatisticsData(query1, arg1s, columnName1s, dataType1s, format1s, dt_col_names[0], dt_tp, isDataQuery, -1);
		ArrayList result2 = getStatisticsData(query2, arg2s, columnName2s, dataType2s, format2s, dt_col_names[1], dt_tp, isDataQuery, -1);
		
		ArrayList result = new ArrayList();
		try {
			String logDateFieldName1 = dt_col_names[0];
			String logDateFieldName2 = dt_col_names[1];
			
			int j = 0, k = 0;
			StatisticsService_DTO dto1 = null;
			Object[] keys1 = null;
			HashMap defaultValues1 = makeDefaultValues(columnName1s, dataType1s, format1s);
			if(result1.size() > j) {
				dto1 = (StatisticsService_DTO)result1.get(j);
				keys1 = dto1.getColumnNameArray();
			}
			StatisticsService_DTO dto2 = null;
			Object[] keys2 = null;
			HashMap defaultValues2 = makeDefaultValues(columnName2s, dataType2s, format2s);
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
					for(int l = 0; l < columnName1s.length; l++) {
						dto.setValue(columnName1s[l] + "_1", (String)defaultValues1.get(columnName1s[l]));
						dto.setValue(columnName1s[l] + "_1", 0);
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
					for(int l = 0; l < columnName2s.length; l++) {
						dto.setValue(columnName2s[l] + "_2", (String)defaultValues2.get(columnName2s[l]));
						dto.setValue(columnName2s[l] + "_2", 0);
					}
					j++;
				} else if(eventTime1 == null && eventTime2 != null) {
					dto.setValue(logDateFieldName1, eventTime2);
					for(int l = 0; l < columnName1s.length; l++) {
						dto.setValue(columnName1s[l] + "_1", (String)defaultValues1.get(columnName1s[l]));
						dto.setValue(columnName1s[l] + "_1", 0);
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
					for(int l = 0; l < columnName2s.length; l++) {
						dto.setValue(columnName2s[l] + "_2", (String)defaultValues2.get(columnName2s[l]));
						dto.setValue(columnName2s[l] + "_2", 0);
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
		}
		
		return result;
	}

	public String getQueryDateFormat(String dt_tp) throws Exception {
		
		String result = "YYYYMMDD";
		if(dt_tp.equals("03") || dt_tp.equals("04")){
			result = "YYYYMM";	
		} else if(dt_tp.equals("05")){
			result = "YYYY";	
		}
		
		return result;
	}
	
	public String convertPreviousCompare(String query, String dt_tp) throws Exception {
		
		int start = query.indexOf("#{");
		int end = query.indexOf("}", start);
		
		if(start > -1 && end > -1) {
			String fromString = query.substring(start, end+1);
			String toString = fromString.substring(2, fromString.length()-1);
			if(dt_tp.equals("01")){//일간
				toString = toString + "+1";	
			} else if(dt_tp.equals("02")){//주간
				toString = toString + "+7";	
			} else if(dt_tp.equals("03")){//월간
				toString = "ADD_MONTHS("+toString + ",1)";	
			} else if(dt_tp.equals("04")){//분기
				toString = "ADD_MONTHS("+toString + ",3)";	
			} else if(dt_tp.equals("05")){//년간
				toString = "ADD_MONTHS("+toString + ",12)";	
			}
			
			query = StringUtil.replace(query, fromString, toString);		
		}
		
		return query;
	}
	
	public ArrayList getPageCodeInfo(String page_id) throws Exception {

		Connection conn = null;
		ArrayList result = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageCodeInfo(conn, page_id);
		} catch (Exception e) {
			
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}
		return result; 
	}

	public MenuItem_DTO getPageMenuInfo(String menu_item_id, String userID) throws Exception {

		Connection conn = null;
		MenuItem_DTO result = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getPageMenuInfo(conn, menu_item_id, userID);
		} catch (Exception e) {
			
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}
		return result; 
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

	public ArrayList getCPList(String table_id, String cp_code) throws Exception {

		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getCPList(conn, table_id, cp_code);
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

	public ArrayList getCPServiceList(String table_id, String cp_code) throws Exception {
	
		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getCPServiceList(conn, table_id, cp_code);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}
	
		return al;
	}

	public ArrayList getServiceList(String table_id) throws Exception  {
	
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

	public ArrayList getServiceList(String table_id, String cp_code) throws Exception  {
	
		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			al = pageDAO.getServiceList(conn, table_id, cp_code);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {}
		}
	
		return al;
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

	public String makeGraphInitData(int rows) throws Exception {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < rows; i++) {
			sb.append(',').append(0);
		}
		return sb.toString();
	}
	
	public boolean hasCPUserInput(StatisticsPageDetail_DTO[] spdDTO, StatisticsPageCondition_DTO[] spcDTO) throws Exception {
		
		boolean hasCPInput = false;
		
		for(int j = 0; j < spcDTO.length; j++) {
			for(int i = 0; i < spdDTO.length; i++) {
				if(spdDTO[i].getCol_id().equals(spcDTO[j].getCndtn_col_id()) 
					&& spcDTO[j].getCndtn_usr_inpt().equals("Y")
					&& spdDTO[i].getData_type().equals("1") 
					&& spdDTO[i].getCode_category().equals("CP")) {
					hasCPInput = true;
				}
			}
		}
		
		return hasCPInput;
	}
	
	public void convertDailyDateAliasToDateTime(String dt_tp, String[] args) throws Exception {
		try {
			if(args != null && args.length > 1) {
				String dateFormat = "yyyyMMdd";
				if(dt_tp.equals("03") || dt_tp.equals("04"))
					dateFormat = "yyyyMM";
				else if(dt_tp.equals("05"))
					dateFormat = "yyyy";
			
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
				Calendar cal = Calendar.getInstance();

				if(args[0].equals("TODAY")) {
					if(dt_tp.equals("02")) {
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						args[1] = sdf.format(cal.getTime());
					} else if(dt_tp.equals("04")) {
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						args[1] = sdf.format(cal.getTime());
					} else {
						args[0] = sdf.format(cal.getTime());
						args[1] = args[0];
					}
				} else if(args[0].equals("YESTERDAY")) {
					if(dt_tp.equals("01")) {
						cal.add(Calendar.DATE, -1);
						args[0] = sdf.format(cal.getTime());
						args[1] = args[0];
					}else if(dt_tp.equals("02")) {
						cal.add(Calendar.DATE, -7);
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						args[1] = sdf.format(cal.getTime());
					}else if(dt_tp.equals("03")) {
						cal.add(Calendar.MONTH, -1);
						args[0] = sdf.format(cal.getTime());
						args[1] = args[0];
					} else if(dt_tp.equals("04")) {
						cal.add(Calendar.MONTH, -3);
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						args[1] = sdf.format(cal.getTime());
					} else if(dt_tp.equals("05")) {
						cal.add(Calendar.YEAR, -1);
						args[0] = sdf.format(cal.getTime());
						args[1] = args[0];
					}
				} else {
					if(dt_tp.equals("02")) {
						cal.setTime(sdf.parse(args[0]));
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.DATE, 6);
						args[1] = sdf.format(cal.getTime());
					} else if(dt_tp.equals("03")) {
						args[0] = args[0].substring(0, 6);
						args[1] = args[0];
					} else if(dt_tp.equals("04")) {
						cal.setTime(sdf.parse(args[0]));
						args[0] = sdf.format(cal.getTime());
						cal.add(Calendar.MONTH, 2);
						args[1] = sdf.format(cal.getTime());
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
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

	public String getServiceName(String service_id) throws Exception {

		Connection conn = null;
		String result = null;
		try {
			try {
				Integer.parseInt(service_id);
			} catch(NumberFormatException nfe) {
				return "전체";
			}
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = pageDAO.getServiceName(conn, service_id);
		} catch (Exception e) {
			
			throw e;
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException sqle) {}
		}

		return result;
	}

	public String parseExpression(StatisticsService_DTO dto, String expr) {
		int start = expr.indexOf("${");
		while(start > -1) {
			int end = expr.indexOf('}', start);
			if(end > start) {
				String fieldName = expr.substring(start+2, end).trim();
				Double value = dto.getNumberValue(fieldName);
				String expTmp1 = "";
				String expTmp2 = "";
				if(start>0)
					expTmp1 = expr.substring(0, start);
				if(end < expr.length())
					expTmp2 = expr.substring(end+1);
				expr = expTmp1 + value + expTmp2;
			}
			start = expr.indexOf("${", start+2);
		}

		return expr;
	}

	private HashMap makeDefaultValues(String[] columnNames, String[] dataTypes, String[] formats) throws Exception {
		
		HashMap defaultValues = new HashMap(); 
		for(int j = 0; j < columnNames.length; j++) {
			if(dataTypes[j].equals("2")) {
				java.text.DecimalFormat df = new java.text.DecimalFormat(formats[j]);
				defaultValues.put(columnNames[j],df.format(0));
			} else {
				defaultValues.put(columnNames[j],"");
			}
		}
		
		return defaultValues;
	}
}
