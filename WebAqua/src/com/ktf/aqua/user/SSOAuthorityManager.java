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
	* SSO 인증관련 서버 환경 설정 메소드
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
	 *ckErrCode = -1 (ID 없음)
	 *ckErrCode = -2 (Password 틀림)
	 *ckErrCode = -3 (System 장애)
	 *ckErrCode = -11 (로그온 불가 : n-drama 여성전용)
	 *정상      =  0
	*/
	public String errorCode (HttpServletRequest request){
		
		String chkSSOType = request.getParameter("SSO");
		String ckErrCode = "";
		//S.S.O. 인증 성공 or 페이지를 처음 요청한 경우
		if ("f".equals(chkSSOType)) {
			ckErrCode =  request.getParameter("ErrCode");
		} else {
			ckErrCode = "0";
		}
		return ckErrCode;
	}
	
	/**
	 * 에러 메세지
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
					errMsg = "존재하지 않는 ID입니다. 다시 시도하십시오.";
					break;
				case -2:
					errMsg = "Password가 틀렸습니다. 다시 시도하십시오.";
					break;
				case -3:
					errMsg = "일시적인 시스템 오류입니다. 다시 시도하십시오.";
					break;
				case -11:
					errMsg = "가입되어 있지 않은 Site입니다.";
					break;						
		}
		return errMsg;	
	}
	/**
	 * 쿠키에서 사용자정보를 읽어온다.
	 * 
	 *@param	request
	 *@return 	Stirng[0] - 사용자ID, Stirng[1] - 사용자명
	 */	
	public Map getUserInfoInCookie(HttpServletRequest request) 
			throws Exception {
		String name = "";
		String value = "";
		
		Map userInfo = new Hashtable();
		
		// 쿠키에서 사용자정보 확인
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
			  name = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
			  value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
			  userInfo.put(name, value);
			}			
			log.info("쿠키-	 :  " + userInfo);							
		}		
		return userInfo;
		
	}	
	
	/**
	 * SSO인증 후 쿠키에 값을 사용자정보를 읽어온다.(암호 풀다)
	 * 
	 *@param	request
	 *@return 	Stirng[0] - 사용자ID, Stirng[1] - 사용자명
	 */	
	public SSOAuthority getCookiesBySSO(HttpServletRequest request) 
			throws Exception {
		
		List info = new ArrayList();
		
		String name = "";
		String value = "";		
	
		SSOAuthority authorityBySSOBean= new SSOAuthority();
		// 쿠키에서 사용자정보 확인
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
			  //사용자 아이디 
			  if ("KTFE_UserID".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_UserID(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }	
			  //사용자 이름 
			  if ("KTFE_UserName".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
		  		authorityBySSOBean.setKTFE_UserName(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }				  
			  //주민번호
			  if ("KTFE_resRgstNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  	authorityBySSOBean.setKTFE_RegRgstNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }
			  //모바일폰 
			  if ("KTFE_PhoneNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  		authorityBySSOBean.setKTFE_PhoneNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
			  }			  
				
			/*PSSO
				if ("PSSO_SiteInfo".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setSSOE_SiteInfo(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				//사용자 아이디 
				if ("PSSO_UserID".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_UserID(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}	
				//사용자 이름 
				if ("PSSO_UserName".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
					authorityBySSOBean.setKTFE_UserName(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}	
				//모바일폰 
				if ("PSSO_PhoneNum".equals(URLDecoder.decode(cookies[i].getName(), "EUC-KR"))) {
			  		authorityBySSOBean.setKTFE_PhoneNum(URLDecoder.decode(cookies[i].getValue(), "EUC-KR"));
				}
				//WCDMA지원여부 
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
			  //log.debug("SSO쿠키-1111 :  " + info.toString());
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
	 * SSO인증 후 쿠키에 값을 사용자정보를 읽어온다.
	 * 
	 *@param	request
	 *@return 	Stirng[0] - 사용자ID, Stirng[1] - 사용자명
	 */	
	public Map getCookies(HttpServletRequest request) 
			throws Exception {
		
		List info = new ArrayList();
		
		String name = "";
		String value = "";		
		
		SSOAuthority SSOAuthority= new SSOAuthority();
		// 쿠키에서 사용자정보 확인
		Cookie[] cookies = request.getCookies();
		
		Map userInfo = new Hashtable();
		
		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
			  name = URLDecoder.decode(cookies[i].getName(), "EUC-KR");
			  value = URLDecoder.decode(cookies[i].getValue(), "EUC-KR");
			  userInfo.put(name, value);
			}			
			log.info("쿠키-	5555555555555 :  " + userInfo);
							
		}		
		return userInfo;
		
	}
	
	/**
	 * 쿠키 삭제
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		// 쿠키삭제 
		Cookie[] cookies = request.getCookies();	

		if (cookies != null) {
			for (int i=0; i < cookies.length; i++) {
				log.info("쿠키 삭제  시작  "+cookies[i].getName());
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);				
			}	
			log.info("쿠키 삭제  완료 ");										
		}
	}
	
	/*
	"KTFE_keyid" 사용되는 공개키/개인키 ID 추가
	"KTFE_enckey" 공개키로 암호화된 대칭키 추가
	"KTFE_phonenum" 사용자 폰 번호 "KTF_phonenum" 대체
	"SSOE_siteinfo" 사용자 주소등 "SSO_siteinfo" 대체
	"KTFE_resrgstnum" 사용자 주민번호 "KTF_resrgstnum" 대체
	"KTFE_userhash"  사용자 비밀번호 "KTF_userhash" 대체
    */
	public SSOAuthority getCookiesUserInfoBySSO(HttpServletRequest request) throws Exception {
		
		log.info("SSO쿠키-	암호 decode ==>  start");
		SSOAuthority bean = getCookiesBySSO(request);
		
		SSOAuthority authorityBySSOBean = new SSOAuthority();
		
		JKTFCrypto crypto = new JKTFCrypto();
		crypto.setKeyId(bean.getKTFE_keyid());
		crypto.DecryptSessionKey(bean.getKTFE_enckey());
		
		byte[] decmsg;		
		 
		//전화번호
		decmsg = crypto.DecryptData(bean.getKTFE_PhoneNum());
		if (decmsg == null) {
		 // 복호화 실패
			authorityBySSOBean.setErrorCode(crypto.getErrorCode());
			authorityBySSOBean.setStatus(false);
		} else {
			 // 복호화 성공					    	
			authorityBySSOBean.setKTFE_PhoneNum(new String(decmsg));
		}
		//주민번호			
		decmsg = crypto.DecryptData(bean.getKTFE_RegRgstNum());
		if (decmsg == null) {
			  // 복호화 실패
			authorityBySSOBean.setErrorCode(crypto.getErrorCode());
			authorityBySSOBean.setStatus(false);
		} else {
			 // 복호화 성공			   	
			authorityBySSOBean.setKTFE_RegRgstNum(new String(decmsg));
		}
		
	   log.info("전화번호==>"+authorityBySSOBean.getKTFE_PhoneNum());
	   log.info("주민번호==>"+authorityBySSOBean.getKTFE_RegRgstNum());
	   log.info("SSO쿠키-	암호 decode ==>  end");
					
	   return authorityBySSOBean;
	}
	
	public User getCookiesUserInfo(HttpServletRequest request) throws Exception {
		/*
		User userInfo = new User();
		userInfo.setUserId("ahmax");
		userInfo.setMobile("01042851322");
		userInfo.setName("안호민");
				
		return userInfo;
		*/

		log.info("SSO쿠키-	암호 decode ==>  start");
		SSOAuthority bean = getCookiesBySSO(request);	
		User user = new User();		
		JKTFCrypto crypto = new JKTFCrypto(); 
		crypto.setKeyId(bean.getKTFE_keyid());
		crypto.DecryptSessionKey(bean.getKTFE_enckey());
		
		byte[] decmsg;	
		//사용자 아이디
		decmsg = crypto.DecryptData(bean.getKTFE_UserID());
		if (decmsg == null) {
		 // 복호화 실패
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // 복호화 성공					    	
			user.setUserId(new String(decmsg));
		}
		//사용자 이름 
		decmsg = crypto.DecryptData(bean.getKTFE_UserName());
		if (decmsg == null) {
		 // 복호화 실패
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // 복호화 성공					    	
			user.setName(new String(decmsg));
		} 
		//전화번호
		decmsg = crypto.DecryptData(bean.getKTFE_PhoneNum());
		if (decmsg == null) {
		 // 복호화 실패
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // 복호화 성공					    	
			user.setMobile(new String(decmsg));
		}
		//주민번호			
		decmsg = crypto.DecryptData(bean.getKTFE_RegRgstNum());
		if (decmsg == null) {
			  // 복호화 실패
			user.setErrorCode(crypto.getErrorCode());
			user.setStatus(false);
		} else {
			 // 복호화 성공			   	
			user.setRegRgstNum(new String(decmsg));
		}
	   return user;
	}
}
