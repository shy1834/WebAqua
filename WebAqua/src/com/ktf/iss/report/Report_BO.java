/*
 * Created on 2005. 1. 13.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.iss.report;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.net.*;
import org.apache.commons.net.ftp.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

import com.ktf.aqua.db.DBDataSource;

/**
 * @author hyunyun
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Report_BO {

	private Connection conn = null;
	//private String jdbc_url = "jdbc:oracle:thin:@172.31.34.50:1521:AQUAORA"; // 데이터베이스
																				// URL
	//private String db_id = "aquauser02"; // 데이터베이스 계정
	//private String db_pwd = "aqua1234"; // 데이터베이스 비밀 번호

	private Report_DAO dao;
	private static Report_BO bo;

	private Report_BO() {
		super();
		dao = new Report_DAO();
	}

	public static Report_BO getInstance() {
		
		if(bo == null){
			bo = new Report_BO();
		}
		return bo;
	}

	public ArrayList getReportTemplateList(String reptTmplName, int page, int rows) throws Exception {
		
		Connection conn = null;
		ArrayList al = null;
		
		try {
			
			int start = (page - 1) * rows + 1;
			int end = page * rows;
			if(reptTmplName != null){
				reptTmplName = "%" + reptTmplName + "%";
			}
				
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			conn = DBDataSource.getCon(0);
			al = dao.getReportTemplateList(conn, reptTmplName, start, end);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return al;
	}

	public int getReportTemplateTotal(String reptTmplName) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			
			if(reptTmplName != null){
				reptTmplName = "%" + reptTmplName + "%";
			}
				
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getReportTemplateTotal(conn, reptTmplName);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ArrayList getReportFileList(String reptTmplName, String reptName, String eventTimeFrom, String eventTimeTo, int page, int rows) throws Exception {
		
		Connection conn = null;
		ArrayList al = null;
		
		try {
			
			int start = (page - 1) * rows + 1;
			int end = page * rows;
			if(reptTmplName != null){
				reptTmplName = "%" + reptTmplName + "%";
			}
				
			if(reptName != null){
				reptName = "%" + reptName + "%";
			}
				
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			al = dao.getReportFileList(conn, reptTmplName, reptName, eventTimeFrom, eventTimeTo, start, end);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return al;
	}

	public int getReportFileTotal(String reptTmplName, String reptName, String eventTimeFrom, String eventTimeTo) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			if(reptTmplName != null){
				reptTmplName = "%" + reptTmplName + "%";
			}
				
			if(reptName != null){
				reptName = "%" + reptName + "%";
			}
				
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getReportFileTotal(conn, reptTmplName, reptName, eventTimeFrom, eventTimeTo);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public int insertReportTemplate(ReportTemplate_DTO rtDTO, ReportTemplateStatistics_DTO[] rtsDTOs) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			conn.setAutoCommit(false);
			rtDTO.setRept_tmpl_id(dao.getNextReportTemplateID(conn));
			//Configuration config = Configuration.getInstance();
			String path = "E:/template"; // config.getProperty("ReportTemplateDirectory");

			File file = new File(path + File.separator + rtDTO.getRept_tmpl_file_nm());
			for(int i = 0; file.exists(); i++){
				file = new File(path + File.separator + rtDTO.getRept_tmpl_file_nm() + i);
			}
				
			rtDTO.setRept_tmpl_real_file(file.getAbsolutePath());

			BufferedInputStream bis = new BufferedInputStream(rtDTO.getRept_tmpl_file().getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			
			byte[] buffer = new byte[8192];
			int r = -1;
			while((r = bis.read(buffer)) > -1) {
				bos.write(buffer);
				buffer = new byte[8192];
			}

			bis.close();
			bos.close();
			rtDTO.getRept_tmpl_file().destroy();

			if (dao.insertReportTemplateInfo(conn, rtDTO) > 0) {
				for (int i = 0; i < rtsDTOs.length; i++) {
					rtsDTOs[i].setRept_stat_id(dao.getNextReportStatisticsInfoID(conn));
					rtsDTOs[i].setRept_tmpl_id(rtDTO.getRept_tmpl_id());
					dao.insertReportStatisticsInfo(conn, rtsDTOs[i]);
				}
			}
			conn.commit();
			
		} catch (Exception e) {
			if(conn != null){
				conn.rollback();
			}
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public int deleteReportTemplate(String rept_tmpl_id) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			conn.setAutoCommit(false);
			ReportTemplate_DTO dto = dao.getReportTemplateInfo(conn, rept_tmpl_id);
			File file = new File(dto.getRept_tmpl_real_file());
			if(file.exists()){
				file.delete();
			}
				
			dao.deleteReportTemplateInfo(conn, rept_tmpl_id);
			dao.deleteReportStatisticsInfo(conn, rept_tmpl_id);
			conn.commit();
			
		} catch (Exception e) {
			if(conn != null){
				conn.rollback();
			}
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public int deleteReport(String rept_id) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			conn.setAutoCommit(false);
			
			ReportFileInfo_DTO dto = dao.getReportFileInfo(conn, rept_id);
			File file = new File(dto.getRept_file_nm());
			
			if(file.exists()){
				file.delete();
			}
				
			dao.deleteReportFileInfo(conn, rept_id);
			conn.commit();
			
		} catch (Exception e) {
			if(conn != null){
				conn.rollback();
			}
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public int modifyReportTemplate(ReportTemplate_DTO rtDTO, ReportTemplateStatistics_DTO[] rtsDTOs) throws Exception {
		
		Connection conn = null;
		int result = -1;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			conn.setAutoCommit(false);
			String rept_tmpl_id = rtDTO.getRept_tmpl_id();
			ReportTemplate_DTO dto = dao.getReportTemplateInfo(conn, rept_tmpl_id);
			File file = new File(dto.getRept_tmpl_real_file());
			if(file.exists()){
				file.delete();
			}
				
			dao.deleteReportTemplateInfo(conn, rept_tmpl_id);
			dao.deleteReportStatisticsInfo(conn, rept_tmpl_id);

			//Configuration config = Configuration.getInstance();
			String path = "E:/template"; // config.getProperty("ReportTemplateDirectory");

			file = new File(path + File.separator + rtDTO.getRept_tmpl_file_nm());
			for(int i = 0; file.exists(); i++){
				file = new File(path + File.separator + rtDTO.getRept_tmpl_file_nm() + i);
			}
				
			rtDTO.setRept_tmpl_real_file(file.getAbsolutePath());
			BufferedInputStream bis = new BufferedInputStream(rtDTO.getRept_tmpl_file().getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			
			byte[] buffer = new byte[8192];
			int r = -1;
			while((r = bis.read(buffer)) > -1) {
				bos.write(buffer);
				buffer = new byte[8192];
			}
			
			bis.close();
			bos.close();
			rtDTO.getRept_tmpl_file().destroy();

			if (dao.insertReportTemplateInfo(conn, rtDTO) > 0) {
				for(int i = 0; i < rtsDTOs.length; i++) {
					rtsDTOs[i].setRept_stat_id(dao.getNextReportStatisticsInfoID(conn));
					rtsDTOs[i].setRept_tmpl_id(rtDTO.getRept_tmpl_id());
					dao.insertReportStatisticsInfo(conn, rtsDTOs[i]);
				}
			}
			
			conn.commit();
			
		} catch (Exception e) {
			if(conn != null){
				conn.rollback();
			}
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ReportTemplate_DTO getReportTemplateInfo(String rept_tmpl_id)throws Exception {
		
		Connection conn = null;
		ReportTemplate_DTO result = null;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getReportTemplateInfo(conn, rept_tmpl_id);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ArrayList getReportStatisticsInfo(String rept_tmpl_id) throws Exception {
		
		Connection conn = null;
		ArrayList result = null;
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getReportStatisticsInfo(conn, rept_tmpl_id);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ReportFileInfo_DTO getReportFileInfo(String rept_id) throws Exception {
		
		Connection conn = null;
		ReportFileInfo_DTO result = null;
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getReportFileInfo(conn, rept_id);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}					
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ArrayList getTableList(String table_org_id) throws Exception {
		
		Connection conn = null;
		ArrayList result = null;
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getTableList(conn, table_org_id);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public ArrayList getColumnList(String table_id) throws Exception {
		
		Connection conn = null;
		ArrayList result = null;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			result = dao.getColumnList(conn, table_id);
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn != null){
					conn.close();
				}					
			} catch (SQLException sqle) {
			}
		}
		return result;
	}

	public void createReport(String rept_tmpl_id, String rept_nm,String[] in_params, String date_fld) throws Exception {
		
		try{
		
			Connection conn = null;
		
			try {
			
				ReportTemplate_DTO dto = getReportTemplateInfo(rept_tmpl_id);
				ArrayList statInfos = getReportStatisticsInfo(rept_tmpl_id);
				String filePath = dto.getRept_tmpl_real_file();
				//filePath = "E:\\"+ filePath.substring(3);
				filePath = "E:\\"+ filePath.substring(3);
				POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
				HSSFWorkbook wb = new HSSFWorkbook(fs);

				//Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DBDataSource.getCon(0);
	
				for (int i = 0; i < statInfos.size(); ++i) {
					ReportTemplateStatistics_DTO statInfo = (ReportTemplateStatistics_DTO) statInfos.get(i);
					int sheetNo = Integer.parseInt(statInfo.getSheet_no());
					HSSFSheet sheet = wb.getSheetAt(sheetNo);
					this.dao.fillSheet(conn, statInfo, in_params[i].split(","),	sheet, date_fld);
					//System.out.println("==="+ i);
				}
	
				String rept_id = this.dao.getNextReportID(conn);
				String path = "E:/template";
				String rept_file_nm = path + File.separator + rept_id + ".xls";
				//System.out.println("rept_file_nm = " + rept_file_nm);
	
				FileOutputStream fileOut = new FileOutputStream(rept_file_nm);
				wb.write(fileOut);
				fileOut.close();
				ReportFileInfo_DTO rfDTO = new ReportFileInfo_DTO();
				rfDTO.setRept_id(rept_id);
				rfDTO.setRept_tmpl_id(rept_tmpl_id);
				rfDTO.setRept_nm(rept_nm);
				rfDTO.setRept_file_nm(rept_file_nm);
				this.dao.insertReportFileInfo(conn, rfDTO);
				
			} catch (Exception e) {
				throw e;
		} finally {
			try {
				if(conn == null){
					conn.close();
				}					
			} catch (SQLException localSQLException) {
			}
		}
		}catch(Exception e ){
			System.out.println(e);
		}
	}

	public void createReportAuto(int mode) throws Exception {
		
		Connection conn = null;
		
		try {
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DBDataSource.getCon(0);
			ArrayList reptList = this.dao.getReportTemplateList(conn, mode);
			
			for (int j = 0; j < reptList.size();) {
				try {
					ReportTemplate_DTO dto = (ReportTemplate_DTO) reptList.get(j);
					ArrayList statInfos = getReportStatisticsInfo(dto.getRept_tmpl_id());
					String filePath = dto.getRept_tmpl_real_file();
					POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
					HSSFWorkbook wb = new HSSFWorkbook(fs);

					for (int i = 0; i < statInfos.size(); ++i) {
						ReportTemplateStatistics_DTO statInfo = (ReportTemplateStatistics_DTO) statInfos.get(i);
						int sheetNo = Integer.parseInt(statInfo.getSheet_no());
						HSSFSheet localHSSFSheet = wb.getSheetAt(sheetNo);
					}

					String rept_id = this.dao.getNextReportID(conn);
					String path = "E:/template";
					String rept_file_nm = path + File.separator + rept_id + ".xls";
					System.out.println("rept_file_nm = " + rept_file_nm);
					FileOutputStream fileOut = new FileOutputStream(rept_file_nm);
					wb.write(fileOut);
					fileOut.close();
					
					ReportFileInfo_DTO rfDTO = new ReportFileInfo_DTO();
					rfDTO.setRept_id(rept_id);
					rfDTO.setRept_tmpl_id(dto.getRept_tmpl_id());
					rfDTO.setRept_nm(dto.getRept_tmpl_nm() + "_AUTO");
					rfDTO.setRept_file_nm(rept_file_nm);
					this.dao.insertReportFileInfo(conn, rfDTO);
					
				} catch (Exception dto) {
				}
				label364: ++j;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(conn == null){
					conn.close();
				}					
			} catch (SQLException localSQLException) {
			}
		}
	}

	/*
	 * public ArrayList getWebServerList() throws Exception {
	 * 
	 * ArrayList al = null; Connection conn = null; try { Class.forName(
	 * "oracle.jdbc.driver.OracleDriver"); conn =
	 * DriverManager.getConnection(jdbc_url, db_id, db_pwd); StatisticsPage_DAO
	 * pageDAO = new StatisticsPage_DAO(); al = pageDAO.getWebServerList(conn);
	 * } catch (Exception e) {
	 * 
	 * throw e; } finally { try { if(conn != null) conn.close(); } catch
	 * (Exception e) {} }
	 * 
	 * return al; }
	 */
	private int deploy(String server, String username, String password, String remoteFile, String localFile, int port, boolean storeFile,
			boolean binaryTransfer) throws Exception {

		boolean error = false;
		FTPClient ftp = new FTPClient();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(baos)));

		try {
			
			int reply;
			ftp.connect(server, port);

			//After connection attempt, you should check the reply code to
			//verify
			//success.
			reply = ftp.getReplyCode();

			if(!FTPReply.isPositiveCompletion(reply)){
				
				ftp.disconnect();

				return 1;
			}
		} catch (IOException e) {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
			return 1;
		}

		__main: try {
			if (!ftp.login(username, password)) {
				ftp.logout();
				error = true;
				break __main;
			}

			if (binaryTransfer){
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
			}

			//Use passive mode as default because most of us are
			//behind firewalls these days.
			ftp.enterLocalPassiveMode();

			if(storeFile){
				
				InputStream input;
				input = new FileInputStream(localFile);
				ftp.storeFile(remoteFile, input);
			}else{
				
				OutputStream output;
				output = new FileOutputStream(localFile);
				ftp.retrieveFile(remoteFile, output);
			}
			ftp.logout();
		} catch (FTPConnectionClosedException e) {
			error = true;
		} catch (IOException e) {
			error = true;
		} finally {
			if(ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
					// do nothing
				}
			}
		}
		return error ? 1 : 0;
	}

	public class PrintCommandListener implements ProtocolCommandListener {
		
		private PrintWriter __writer;

		public PrintCommandListener(PrintWriter writer) {
			__writer = writer;
		}

		public void protocolCommandSent(ProtocolCommandEvent event) {
			__writer.print(event.getMessage());
			__writer.flush();
		}

		public void protocolReplyReceived(ProtocolCommandEvent event) {
			__writer.print(event.getMessage());
			__writer.flush();
		}
	}
}
