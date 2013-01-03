/*
 * Created on 2006. 2. 15.
 */
package com.ktf.aqua.user;

import java.net.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.log4j.*;
import JKTFCrypto.*;
import com.edsk.framework.*;
import com.ktf.aqua.user.common.*;

public class SSOAuthorityManager {
	
	protected Logger log = Logger.getLogger(this.getClass());

	public static SSOAuthorityManager instance() {
		return (new SSOAuthorityManager());
	}
	
	/**
	* SSO �������� ���� ȯ�� ���� �޼ҵ�
	* @return
	*/
	public Map serverInfo() throws Exception {

		Map config = new Hashtable();
		try {
			String className = getClass().getName();	
			
			config.put("THIS_URL", 		Config.getInstance().getProperty(className,ConfigKey.THIS_URL));
			config.put("PORT",		 	Config.getInstance().getProperty(className,ConfigKey.PORT));
			config.put("THIS_SERVER_IP",Config.getInstance().getProperty(className,ConfigKey.THIS_SERVER_IP));
			config.put("LOGON_SERVER", 	Config.getInstance().getProperty(className,ConfigKey.LOGON_SERVER));
			config.put("LOGON_PAGE", 	Config.getInstance().getProperty(className,ConfigKey.LOGON_PAGE));
			config.put("SSO_PAGE", 		Config.getInstance().getProperty(className,ConfigKey.SSO_PAGE));
			config.put("LOGOFF_PAGE", 	Config.getInstance().getProperty(className,ConfigKey.LOGOFF_PAGE));
			config.put("JOIN_PAGE", 	Config.getInstance().getProperty(className,ConfigKey.JOIN_PAGE));
			config.put("UPDATE_PAGE", 	Config.getInstance().getProperty(className,ConfigKey.UPDATE_PAGE));	
			
		} catch(Exception e){			
			throw new Exception(this.getClass().getName() + "." + "serverInfo()" + "=>" + e);			
		}
		return config;		
	}
	
	
	/**
	 *ckErrCode = -1 (ID ����)
	 *ckErrCode = -2 (Password Ʋ��)
	 *ckErrCode = -3 (System ���)
	 *ckErrCode = -11 (�α׿� �Ұ� : n-drama ��������)
	 *����      =  0
	*/
	public String errorCode (HttpServletRequest request){
		
		String chkSSOType = request.getParameter("SSO");
		String ckErrCode = "";
		//S.S.O. ���� ���� or �������� ó�� ��û�� ���
		if ("f".equals(chkSSOType)) {
			ckErrCode =  request.getParameter("ErrCode");
		} else {
			ckErrCode = "0";
		}
		return ckErrCode;
	}
	
	/**
	 * ���� �޼���
	 * @param msg
	 * @return
	 */
	public String getSSOErrorMessage(String msg){
		String errMsg = "";
		int ichkErrCode = Integer.parseInt(msg);
		switch (ichkErrCode) {
				case 0:
					errMsg = "";
					break;
				case -1:
					errMsg = "�������� �ʴ� ID�Դϴ�. �ٽ� �õ��Ͻʽÿ�.";
					break;
				case -2:
					errMsg = "Password�� Ʋ�Ƚ��ϴ�. �ٽ� �õ��Ͻʽÿ�.";
					break;
				case -3:
					errMsg = "�Ͻ����� �ý��� �����Դϴ�. �ٽ� �õ��Ͻʽÿ�.";
					break;
				case -11:
					errMsg = "���ԵǾ� ���� ���� Site�Դϴ�.";
					break;						
		}
		return errMsg;	
	}
	/**
	 * ��Ű���� ����������� �о�´�.
	 * 
	 *@param	request
	 *@return 	Stirng[0] - �����ID, Stirng[1] - ����ڸ�
	 */	
	public Map getUserInfoInCookie(HttpServletRequest request) 
			throws Exception {
		String name = "";
		String value = "";
		
		Map userInfo = new Hashtable();
		
		// ��Ű���� ��������� Ȯ��
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
			  name = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
			  value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
			  userInfo.put(name, value);
			}			
			log.info("��Ű-	 :  " + userInfo);							
		}		
		return userInfo;
		
	}	
	
	/**
	 * SSO���� �� ��Ű�� ���� ����������� �о�´�.(��ȣ Ǯ��)
	 * 
	 *@param	request
	 *@return 	Stirng[0] - �����ID, Stirng[1] - ����ڸ�
	 */	
	public SSOAuthority getCookiesBySSO(HttpServletRequest request) 
			throws Exception {
		
		List info = new ArrayList();
		
		String name = "";
		String value = "";		
	
		SSOAuthority authorityBySSOBean= new SSOAuthority();
		// ��Ű���� ��������� Ȯ��
		Cookie[] cookies = request.getCookies();
	
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
			//SSO
			
			  if ("KTFE_keyid".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_keyid(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }
			  if ("KTFE_enckey".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_enckey(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }	
			  //����� ���̵� 
			  if ("KTFE_UserID".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_UserID(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }	
			  //����� �̸� 
			  if ("KTFE_UserName".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_UserName(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }				  
			  //�ֹι�ȣ
			  if ("KTFE_resRgstNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  	authorityBySSOBean.setKTFE_RegRgstNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }
			  //������� 
			  if ("KTFE_PhoneNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  		authorityBySSOBean.setKTFE_PhoneNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }			  
				
			/*PSSO
				if ("PSSO_SiteInfo".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setSSOE_SiteInfo(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				//����� ���̵� 
				if ("PSSO_UserID".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_UserID(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}	
				//����� �̸� 
				if ("PSSO_UserName".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_UserName(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}	
				//������� 
				if ("PSSO_PhoneNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  		authorityBySSOBean.setKTFE_PhoneNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				//WCDMA�������� 
				if ("PSSO_WCDMA".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  		authorityBySSOBean.setKTFE_Wcdma(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				if ("PSSO_keyid".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_keyid(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				if ("PSSO_enckey".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_enckey(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}	
				*/			  
			  //info.add(authorityBySSOBean);
			  //log.debug("SSO��Ű-1111 :  " + info.toString());
			}
		}
		/*
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getErrorCode());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getErrorDesc());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getSSOE_SiteInfo());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_UserID());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_UserName());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_PhoneNum());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_Wcdma());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_keyid());
		System.out.println("authorityBySSOBean=" + authorityBySSOBean.getKTFE_enckey());
		 */
		return authorityBySSOBean;
		
	}
	
	/**
	 * SSO���� �� ��Ű�� ���� ����������� �о�´�.
	 * 
	 *@param	request
	 *@return 	Stirng[0] - �����ID, Stirng[1] - ����ڸ�
	 */	
	public Map getCookies(HttpServletRequest request) 
			throws Exception {
		
		List info = new ArrayList();
		
		String name = "";
		String value = "";		
		
		SSOAuthority SSOAuthority= new SSOAuthority();
		// ��Ű���� ��������� Ȯ��
		Cookie[] cookies = request.getCookies();
		
		Map userInfo = new Hashtable();
		
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
			  name = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
			  value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
			  userInfo.put(name, value);
			}			
			log.info("��Ű-	5555555555555 :  " + userInfo);
							
		}		
		return userInfo;
		
	}
	
	/**
	 * ��Ű ����
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		// ��Ű���� 
		Cookie[] cookies = request.getCookies();	

		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
				log.info("��Ű ����  ����  "+cookies[i].getName());
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);				
			}	
			log.info("��Ű ����  �Ϸ� ");										
		}
	}
	
	/*
	"KTFE_keyid" ���Ǵ� ����Ű/����Ű ID �߰�
	"KTFE_enckey" ����Ű�� ��ȣȭ�� ��ĪŰ �߰�
	"KTFE_phonenum" ����� �� ��ȣ "KTF_phonenum" ��ü
	"SSOE_siteinfo" ����� �ּҵ� "SSO_siteinfo" ��ü
	"KTFE_resrgstnum" ����� �ֹι�ȣ "KTF_resrgstnum" ��ü
	"KTFE_userhash"  ����� ��й�ȣ "KTF_userhash" ��ü
    */
	public SSOAuthority getCookiesUserInfoBySSO(HttpServletRequest request) throws Exception {
		
		log.info("SSO��Ű-	��ȣ decode ==>  start");
		SSOAuthority bean = getCookiesBySSO(request);
		
		SSOAuthority authorityBySSOBean = new SSOAuthority();
		
		JKTFCrypto crypto = new JKTFCrypto();
		crypto.setKeyId(bean.getKTFE_keyid());
		crypto.DecryptSessionKey(bean.getKTFE_enckey());
		
		byte[] decmsg;		
		 
		//��ȭ��ȣ
		decmsg = crypto.DecryptData(bean.getKTFE_PhoneNum());
		if (decmsg == null) {
		 // ��ȣȭ ����
			authorityBySSOBean.setErrorCode(crypto.getErrorCode());
			authorityBySSOBean.setStatus(false);
		} else {
			 // ��ȣȭ ����					    	
			authorityBySSOBean.setKTFE_PhoneNum(new String(decmsg));
		}
		//�ֹι�ȣ			
		decmsg = crypto.DecryptData(bean.getKTFE_RegRgstNum());
		if (decmsg == null) {
			  // ��ȣȭ ����
			authorityBySSOBean.setErrorCode(crypto.getErrorCode());
			authorityBySSOBean.setStatus(false);
		} else {
			 // ��ȣȭ ����			   	
			authorityBySSOBean.setKTFE_RegRgstNum(new String(decmsg));
		}
		
	   log.info("��ȭ��ȣ==>"+authorityBySSOBean.getKTFE_PhoneNum());
	   log.info("�ֹι�ȣ==>"+authorityBySSOBean.getKTFE_RegRgstNum());
	   log.info("SSO��Ű-	��ȣ decode ==>  end");
					
	   return authorityBySSOBean;
	}
	
	public User getCookiesUserInfo(HttpServletRequest request) throws Exception {
		/*
		User userInfo = new User();
		userInfo.setUserId("ahmax");
		userInfo.setMobile("01042851322");
		userInfo.setName("��ȣ��");
				
		return userInfo;
		*/

		log.info("SSO��Ű-	��ȣ decode ==>  start");
		SSOAuthority bean = getCookiesBySSO(request);	
		User user = new User();		
		JKTFCrypto crypto = new JKTFCrypto(); 
		crypto.setKeyId(bean.getKTFE_keyid());
		crypto.DecryptSessionKey(bean.getKTFE_enckey());
		
		byte[] decmsg;	
		//����� ���̵�
		decmsg = crypto.DecryptData(bean.getKTFE_UserID());
		if (decmsg == null) {
		 // ��ȣȭ ����
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // ��ȣȭ ����					    	
			user.setUserId(new String(decmsg));
		}
		//����� �̸� 
		decmsg = crypto.DecryptData(bean.getKTFE_UserName());
		if (decmsg == null) {
		 // ��ȣȭ ����
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // ��ȣȭ ����					    	
			user.setName(new String(decmsg));
		} 
		//��ȭ��ȣ
		decmsg = crypto.DecryptData(bean.getKTFE_PhoneNum());
		if (decmsg == null) {
		 // ��ȣȭ ����
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // ��ȣȭ ����					    	
			user.setMobile(new String(decmsg));
		}
		//�ֹι�ȣ			
		decmsg = crypto.DecryptData(bean.getKTFE_RegRgstNum());
		if (decmsg == null) {
			  // ��ȣȭ ����
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // ��ȣȭ ����			   	
			user.setRegRgstNum(new String(decmsg));
		}
	   return user;
	}
}
