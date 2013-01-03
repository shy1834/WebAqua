/*
 * Created on 2003. 10. 27.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.io.*;
import java.util.*;
import java.sql.*;

import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.*;
//import com.ktf.iss.Abstract_BO;
//import com.ktf.iss.common.config.*;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PageCreator_BO {
	
	private Connection conn = null;
	private String jdbc_url = "jdbc:oracle:thin:@172.31.34.50:1521:AQUAORA"; //데이터베이스 URL
	private String db_id = "aquauser02"; //데이터베이스 계정
	private String db_pwd = "aqua1234"; //데이터베이스 비밀 번호

	/**
	 * 
	 */
	public PageCreator_BO() {
		super();
	}

/*
	public int create(String url, 
						String serviceDiretory, 
						StatisticsPage_DTO spDTO, 
						StatisticsPageDetail_DTO[] spdDTO, 
						StatisticsPageCondition_DTO[] spcDTO,
						StatisticsPageCompare_DTO[] spcomDTO) throws Exception {

		try {
			Configuration config = Configuration.getInstance();
			String hostName = config.getProperty("ServerName");
			url = "http://" + hostName + url;
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);
			method.setRequestBody(makeNameValuePairs(spDTO, spdDTO, spcDTO, spcomDTO));
			//method.setRequestHeader("Content-Type", "charset=EUC-KR");

			client.executeMethod(method);
			BufferedReader in = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));

			StringBuffer statisticsPage = new StringBuffer();
			statisticsPage.append("<%@ page contentType=\"text/html;charset=euc-kr\" %>\n");
			statisticsPage.append("<%@ taglib uri=\"/WEB-INF/struts-bean.tld\" prefix=\"bean\" %>\n");
			statisticsPage.append("<%@ taglib uri=\"/WEB-INF/struts-html.tld\" prefix=\"html\" %>\n");
			statisticsPage.append("<%@ taglib uri=\"/WEB-INF/struts-logic.tld\" prefix=\"logic\" %>\n");
			statisticsPage.append("<%@ taglib uri=\"/WEB-INF/iss-sttdto.tld\" prefix=\"iss\" %>\n");

			String inputLine = null;
			while ((inputLine = in.readLine()) != null)
				statisticsPage.append(inputLine + "\n");
			in.close();
			String fileName = spDTO.getPage_id() + ".jsp";
			File file = new File(serviceDiretory + "/" + fileName);
			if(file.exists()) {
				file.delete();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(statisticsPage.toString());
			fw.close();
			//if(!System.getProperty("os.name").startsWith("Windows"))
			//	Runtime.getRuntime().exec("chmod 777 " + fileName);
			
			ArrayList al = getWebServerList();
			for(int i = 0; i < al.size(); i++) {
				WebServerInfo_DTO dto = (WebServerInfo_DTO)al.get(i);

				if(spDTO.getPage_user().equals("0") && dto.getSvc_type().equals("2")
					|| dto.getHostname().equals(config.getProperty("HostName")))
					continue;

				if(dto.getHostname().equals(config.getProperty("HostName")))
					continue;
				deploy(dto.getIp(),
						dto.getLogin_id(),
						dto.getLogin_pwd(),
						dto.getSvc_dir()+"/"+fileName,
						file.getAbsolutePath(),
						dto.getPort(),
						true,
						false);
			}
			return statisticsPage.length();
		} catch (Exception e) {
			throw e;
		}
	}
*/
	private NameValuePair[] makeNameValuePairs(StatisticsPage_DTO spDTO, 
												StatisticsPageDetail_DTO[] spdDTO, 
												StatisticsPageCondition_DTO[] spcDTO,
												StatisticsPageCompare_DTO[] spcomDTO) throws Exception {

		ArrayList al = new ArrayList();
		al.add(new NameValuePair("page_id", spDTO.getPage_id()));
		al.add(new NameValuePair("page_nm", new String(spDTO.getPage_nm().getBytes("EUC_KR"), "ISO_8859_1")));
		al.add(new NameValuePair("stat_tp", spDTO.getStat_tp()));
		al.add(new NameValuePair("dt_tp", spDTO.getDt_tp()));
		al.add(new NameValuePair("dt_tp_usr_input", spDTO.getDt_tp_usr_input()));
		al.add(new NameValuePair("dt_usr_input", spDTO.getDt_usr_input()));
		al.add(new NameValuePair("inqr_begin_dt", spDTO.getInqr_begin_dt()));
		al.add(new NameValuePair("inqr_end_dt", spDTO.getInqr_end_dt()));
		if(!spDTO.getStat_tp().equals("STTP0005")) {
			al.add(new NameValuePair("table_id", spDTO.getTable_id()));
			al.add(new NameValuePair("dt_col_id", spDTO.getDt_col_id()));
		}
		al.add(new NameValuePair("page_desc", new String(spDTO.getPage_desc().getBytes("EUC_KR"), "ISO_8859_1")));
		al.add(new NameValuePair("max_cnt", String.valueOf(spDTO.getMax_cnt())));
		al.add(new NameValuePair("stat_tp_nm", new String(spDTO.getStat_tp_nm().getBytes("EUC_KR"), "ISO_8859_1")));
		al.add(new NameValuePair("table_name", spDTO.getTable_name()));
		al.add(new NameValuePair("prev_comp", spDTO.getPrev_comp()));
		al.add(new NameValuePair("page_user", spDTO.getPage_user()));
		al.add(new NameValuePair("max_cnt", Integer.toString(spDTO.getMax_cnt())));
		al.add(new NameValuePair("max_cnt_usr_input", spDTO.getMax_cnt_usr_input()));
		al.add(new NameValuePair("svcorg_depth", spDTO.getSvcorg_depth()));
		al.add(new NameValuePair("svcorg_usr_input", spDTO.getSvcorg_usr_input()));
		al.add(new NameValuePair("detail_info", spDTO.getDetail_info()));
		al.add(new NameValuePair("svc_path", spDTO.getSvc_path()));
		al.add(new NameValuePair("refresh_flag", spDTO.getRefresh_flag()));
		al.add(new NameValuePair("refresh_time", spDTO.getRefresh_time()));
		al.add(new NameValuePair("sort_usr_input", spDTO.getSort_usr_input()));
		al.add(new NameValuePair("table_hide", spDTO.getTable_hide()));
		al.add(new NameValuePair("graph_height", spDTO.getGraph_height()));
		al.add(new NameValuePair("graph_max_cnt", spDTO.getGraph_max_cnt()));
		al.add(new NameValuePair("ref_flag", spDTO.getRef_flag()));
		al.add(new NameValuePair("ref_dt", spDTO.getRef_dt()));
		al.add(new NameValuePair("past_dt_tp", spDTO.getPast_dt_tp()));
		al.add(new NameValuePair("past_dt", spDTO.getPast_dt()));
	
		al.add(new NameValuePair("static_scale_y1", spDTO.getStatic_scale_y1()));
		al.add(new NameValuePair("static_scale_y2", spDTO.getStatic_scale_y2()));
		al.add(new NameValuePair("min_value_y1", spDTO.getMin_value_y1()));
		al.add(new NameValuePair("max_value_y1", spDTO.getMax_value_y1()));
		al.add(new NameValuePair("min_value_y2", spDTO.getMin_value_y2()));
		al.add(new NameValuePair("max_value_y2", spDTO.getMax_value_y2()));
		al.add(new NameValuePair("horizontal_grid", spDTO.getHorizontal_grid()));
		al.add(new NameValuePair("vertical_grid", spDTO.getVertical_grid()));
		al.add(new NameValuePair("view_3D", spDTO.getView_3D()));
		al.add(new NameValuePair("palette", spDTO.getPalette()));

		for(int i = 0; i < spdDTO.length; i++) { 
			al.add(new NameValuePair("code_category",spdDTO[i].getCode_category()));
			al.add(new NameValuePair("col_id",spdDTO[i].getCol_id()));
			al.add(new NameValuePair("col_name",spdDTO[i].getCol_name()));
			al.add(new NameValuePair("data_type",spdDTO[i].getData_type()));
			al.add(new NameValuePair("field_nm",new String(spdDTO[i].getField_nm().getBytes("EUC_KR"), "ISO_8859_1")));
			al.add(new NameValuePair("format",new String(spdDTO[i].getFormat().getBytes("EUC_KR"), "ISO_8859_1")));
			al.add(new NameValuePair("graph_vsb",spdDTO[i].getGraph_vsb()));
			al.add(new NameValuePair("group_field",spdDTO[i].getGroup_field()));
			al.add(new NameValuePair("group_func",spdDTO[i].getGroup_func()));
			al.add(new NameValuePair("seq",spdDTO[i].getSeq()));
			al.add(new NameValuePair("table_vsb",spdDTO[i].getTable_vsb()));
			al.add(new NameValuePair("user_defined_info",spdDTO[i].getUser_defined_info()));
			al.add(new NameValuePair("user_defined_info_yn",spdDTO[i].getUser_defined_info_yn()));
			al.add(new NameValuePair("order_seq",spdDTO[i].getOrder_seq()));
			al.add(new NameValuePair("orderby",spdDTO[i].getOrderby()));
			al.add(new NameValuePair("is_compare",spdDTO[i].getIs_compare()));
		}

		for(int i = 0; i < spcDTO.length; i++) {
			al.add(new NameValuePair("cndtn_code_category",spcDTO[i].getCndtn_code_category()));
			al.add(new NameValuePair("cndtn_col_id",spcDTO[i].getCndtn_col_id()));
			al.add(new NameValuePair("cndtn_col_name",spcDTO[i].getCndtn_col_name()));
			al.add(new NameValuePair("cndtn_condition",spcDTO[i].getCndtn_condition()));
			al.add(new NameValuePair("cndtn_index",spcDTO[i].getCndtn_index()));
			al.add(new NameValuePair("cndtn_usr_inpt",spcDTO[i].getCndtn_usr_inpt()));
			al.add(new NameValuePair("cndtn_value",new String(spcDTO[i].getCndtn_value().getBytes("EUC_KR"), "ISO_8859_1")));
			al.add(new NameValuePair("cndtn_is_compare",spcDTO[i].getCndtn_is_compare()));
		}

		for(int i = 0; i < spcomDTO.length; i++) {
			al.add(new NameValuePair("table_id",spcomDTO[i].getTable_id()));
			al.add(new NameValuePair("comp_usr_input",spcomDTO[i].getComp_usr_input()));
			al.add(new NameValuePair("dt_col_id",spcomDTO[i].getDt_col_id()));
			al.add(new NameValuePair("service_id",spcomDTO[i].getService_id()));
		}
		
		NameValuePair[] nvps = new NameValuePair[al.size()];
		for(int i = 0; i < nvps.length; i++) {
			nvps[i] = (NameValuePair)al.get(i);
		}
		return nvps;
	}
	
	private int deploy(String server, 
						String username, 
						String password, 
						String remoteFile, 
						String localFile,
						int port,
						boolean storeFile, 
						boolean binaryTransfer) throws Exception {
							
		boolean error = false;
		FTPClient ftp = new FTPClient();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(baos)));

		try
		{
			int reply;
			ftp.connect(server, port);

			// After connection attempt, you should check the reply code to verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				return 1;
			}
		}
		catch (IOException e)
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
			return 1;
		}

__main:
		try
		{
			if (!ftp.login(username, password))
			{
				ftp.logout();
				error = true;
				break __main;
			}


			if (binaryTransfer)
				ftp.setFileType(FTP.BINARY_FILE_TYPE);

		// Use passive mode as default because most of us are
		// behind firewalls these days.
		ftp.enterLocalPassiveMode();

			if (storeFile)
			{
				InputStream input;

				input = new FileInputStream(localFile);
				ftp.storeFile(remoteFile, input);
			}
			else
			{
				OutputStream output;

				output = new FileOutputStream(localFile);
				ftp.retrieveFile(remoteFile, output);
			}

			ftp.logout();
		}
		catch (FTPConnectionClosedException e)
		{
			error = true;
		}
		catch (IOException e)
		{
			error = true;
		}
		finally
		{
			if (ftp.isConnected())
			{
				try
				{
					ftp.disconnect();
				}
				catch (IOException f)
				{
					// do nothing
				}
			}
		}

		return error ? 1 : 0;
	}
	
	public class PrintCommandListener implements ProtocolCommandListener
	{
		private PrintWriter __writer;

		public PrintCommandListener(PrintWriter writer)
		{
			__writer = writer;
		}

		public void protocolCommandSent(ProtocolCommandEvent event)
		{
			__writer.print(event.getMessage());
			__writer.flush();
		}

		public void protocolReplyReceived(ProtocolCommandEvent event)
		{
			__writer.print(event.getMessage());
			__writer.flush();
		}
	}

	
	public ArrayList getWebServerList() throws Exception {
		
		ArrayList al = null;
		Connection conn = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
			StatisticsPage_DAO pageDAO = new StatisticsPage_DAO(); 
			al = pageDAO.getWebServerList(conn);
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
