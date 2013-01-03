/*
 * Created on 2003. 11. 10.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.util.*;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ktf.iss.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CodeCacheManager implements Runnable {
	
	private Connection conn = null;
	private String jdbc_url = "jdbc:oracle:thin:@172.31.34.50:1521:AQUAORA"; //데이터베이스 URL
	private String db_id = "aquauser02"; //데이터베이스 계정
	private String db_pwd = "aqua1234"; //데이터베이스 비밀 번호

	private HashMap categories;
	private CodeCache_DAO dao;
	private Log log = LogFactory.getLog(this.getClass());
	private int state = 0;
	private final static int RUNNING = 1;
	private final static int TERMINATED = 0;	 
	private static CodeCacheManager codeCacheManager;

	/**
	 * 
	 */
	private CodeCacheManager() {
		super();
		categories = new HashMap();
		dao = new CodeCache_DAO();
		state = RUNNING; 
		new Thread(this).start();
	}

	public static CodeCacheManager getInstance() {
		if(codeCacheManager == null)
			codeCacheManager = new CodeCacheManager();
		return codeCacheManager;
	}

	public String getCodeName(String category, String code) {
		CodeCategory codeCcategory = (CodeCategory)categories.get(category);
		return codeCcategory.getCodeName(code);
	}
	
	public synchronized int refresh() {
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			ArrayList al = dao.getCodeCategoryList(conn);
			for(int i = 0; i < al.size(); i++) {
				String categoryName = (String)al.get(i);
				refresh(conn, categoryName);
			}
			
			refresh(conn, "CP");
			refresh(conn, "SERVICE");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if(conn != null) conn.close();
			} catch(SQLException sqle) {}
		}
		
		return categories.size();
	}
	
	public synchronized int refresh(Connection conn, String categoryName) throws Exception {

		CodeCategory category = new CodeCategory(categoryName);
		ArrayList codeList = null;
		int result = -1;
		if(categoryName.equals("CP")) {
			codeList = dao.getCPCodeList(conn);
		} else if(categoryName.equals("SERVICE")) {
			codeList = dao.getServiceCodeList(conn);
		} else {
			codeList = dao.getCodeList(conn, categoryName);
		}
		for(int j = 0; codeList != null && j < codeList.size(); j++) {
			String[] codeRow = (String[])codeList.get(j);
			category.put(codeRow[0], codeRow[1]);
		}

		if(codeList != null) {
			categories.put(categoryName, category);
			result = codeList.size();
		}
		
		return result;
	}
	
	public synchronized int refresh(String categoryName) {
		Connection conn = null;
		int result = -1;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			result = refresh(conn, categoryName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if(conn != null) conn.close();
			} catch(SQLException sqle) {}
		}
		
		return result;
	}

	public void run() {
		try {
			while(state == RUNNING) {
				synchronized(this) {
					refresh();
					wait(1000*60*60);				
				}
			}
		} catch (Exception e) {}
	}
	
	public void stop() {
		synchronized(this) {
			state = TERMINATED;
			notify();
		}
	}
	
	class CodeCategory {
		private String category;
		private HashMap codes;
		
		public CodeCategory(String category) {
			this.category = category;
			this.codes = new HashMap();
		}
		
		public void put(String code, String name) {
			codes.put(code, name);
		}
		
		public String getCodeName(String code) {
			return (String)codes.get(code);
		}
		
		public String getName() {
			return category;
		}
	}
}
