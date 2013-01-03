/*
 * Created on 2006. 2. 10.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.user.dao;

import java.sql.*;
import java.util.*;

import javax.naming.*;

import org.apache.log4j.*;

import com.edsk.framework.dao.*;
import com.ktf.aqua.mgr.menu.MenuManager;
import com.ktf.aqua.user.UserManager;
import com.ktf.aqua.user.common.*;

/**
 * 사용자 관리에서 데이터베이스와의 작업을 전담하는 클래스.
 * UserInfo 테이블에 사용자를 추가, 수정, 삭제, 검색등의 작업을 한다. 
 */
public class UserDAO extends BaseDAO{
	
	protected Logger log = Logger.getLogger( this.getClass() );
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(User user) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_INFO_TBL  ");
			insertQuery.append(" (SEQUENCE, USER_ID, USER_NAME, COMPANY, MOBILE_PHONE_NUM, REG_RGST_NUM, USER_TYPE ");
			insertQuery.append(", APPLY_STAT_CD, PROG_STAT_CD, AUTH_TYPE, SSO_USE_CHANGE, REG_DATE) ");
			insertQuery.append("VALUES ");
			insertQuery.append("(SEQ_USER_INFO.NEXTVAL, ?, ?, ?, ?, '0000000000000', ?, ?, ?, ?, '01', sysdate)");		
		
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getCompany());
			pstmt.setString(4, user.getMobile());
			//pstmt.setString(5, user.getRegRgstNum());
			pstmt.setString(5, user.getUserType());
			pstmt.setString(6, user.getApplyStat_Cd());
			pstmt.setString(7, user.getProgStat_Cd());
			pstmt.setString(8, user.getAuthType());
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}

	/**
	 * 기존의 사용자 사용자 정보를 수정.
	 */
	public int update(int seq, String auth_type, String User_Email2, String User_Email3, String User_Mobile, String Company_Dept, String update_id) 
		throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer updateQuery = new StringBuffer();
			updateQuery.append("UPDATE AQUA2_USER_INFO_TBL SET ");
			//updateQuery.append("AUTH_TYPE = ?,  EMAIL1 = ?,  EMAIL2 = ?,");
			updateQuery.append("AUTH_TYPE = ?,  EMAIL2 = ?,  EMAIL3 = ?,");
			updateQuery.append("MOBILE_PHONE_NUM = ?, COMPANY = ?, UPDATE_ID = ?,  APPLY_DATE = SYSDATE ");
			updateQuery.append("WHERE SEQUENCE = ? ");		
			
			con = getConnection();
			
			pstmt = con.prepareStatement(updateQuery.toString());
			pstmt.setString(1, auth_type);
			pstmt.setString(2, User_Email2);
			pstmt.setString(3, User_Email3);
			pstmt.setString(4, User_Mobile);
			pstmt.setString(5, Company_Dept);			
			pstmt.setString(6, update_id);
			pstmt.setInt(7, seq);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".update() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "update()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".update() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "update()" + "=>" + ne);
		}  finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}

	/**
	 * 사용자 아이디에 해당하는 사용자를 삭제.
	 */
	public int remove(int seq) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer removeQuery = new StringBuffer();
			removeQuery.append("DELETE FROM AQUA2_USER_INFO_TBL ");
			removeQuery.append("WHERE sequence = ? ");		
		
			con = getConnection();
			pstmt = con.prepareStatement(removeQuery.toString());
			pstmt.setInt(1, seq);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findMenu() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findMenu() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		}  finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}

	/**
	 * 사용자 아이디 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public List findInUser(int seq) throws SQLException, DAOException {
		
		List userEditList = new ArrayList();
		User user = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT  A.SEQUENCE      SEQUENCE  ");
			findQuery.append("		, A.USER_ID					USER_ID  ");
			findQuery.append("		, B.NAME						USER_NAME  ");
			findQuery.append("		, A.USER_TYPE 	 		USER_TYPE  ");
			findQuery.append("		, DECODE(A.USER_TYPE, '01', '내부', '02', '외부')	 USER_CODE  ");
			findQuery.append("		, B.POP_REG_NM			REG_RGST_NUM  ");	
			findQuery.append("		, B.PART_NAME			COMPANY_DEPT  ");	
			findQuery.append("		, NVL(B.CLASS, ' ')		COMPANY_GROUP  ");
			findQuery.append("		, A.MOBILE_PHONE_NUM			USER_MOBILE   ");
			findQuery.append("		, NVL(B.COMPANY_PHN, ' ')		USER_TELNUMBER   ");
			findQuery.append("		, DECODE(A.EMAIL2, NULL, ' ', A.EMAIL2)		USER_EMAIL2   ");
			findQuery.append("		, DECODE(A.EMAIL3, NULL, ' ', A.EMAIL3)		USER_EMAIL3   ");
			findQuery.append("		, A.APPLY_STAT_CD		APPLY_STAT_CD  ");
			findQuery.append("	    , A.PROG_STAT_CD		PROG_STAT_CD  ");
			findQuery.append("	    , A.AUTH_TYPE				AUTH_TYPE	  ");   
			findQuery.append("      , TO_CHAR(A.REG_DATE, 'YYYY/MM/DD')  REG_DATE    "); 
			findQuery.append("	    , NVL(TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD'), ' ')	APPLY_DATE  ");
			findQuery.append("	    , NVL(A.UPDATE_ID, ' ') UPDATE_ID    ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL A, AQUA_USER_FREENET_TBL B  ");
			findQuery.append("WHERE A.MOBILE_PHONE_NUM = B.MOBILE_PHN    ");      
			findQuery.append("AND A.SEQUENCE = ?  ");
			
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(findQuery.toString());
		    pstmt.setInt(1,seq);
		    rs = pstmt.executeQuery();
				
			while (rs.next()){
				
				user = new User();
				user.setSequence(rs.getString("SEQUENCE"));
				user.setUserId(rs.getString("USER_ID"));				
				user.setName(rs.getString("USER_NAME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setUserCode(rs.getString("USER_CODE"));
				user.setRegRgstNum(rs.getString("REG_RGST_NUM"));
				user.setCompanyDept(rs.getString("COMPANY_DEPT"));
				user.setCompanyGroup(rs.getString("COMPANY_GROUP"));
				user.setMobile(rs.getString("USER_MOBILE"));
				user.setInnerTel(rs.getString("USER_TELNUMBER"));
				user.setEmail2(rs.getString("USER_EMAIL2"));
				user.setEmail3(rs.getString("USER_EMAIL3"));	
				user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));
				user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
				user.setAuthType(rs.getString("AUTH_TYPE"));
				user.setRegDate(rs.getString("REG_DATE"));	
				user.setApplyDate(rs.getString("APPLY_DATE"));
				user.setUpdateId(rs.getString("UPDATE_ID"));
				
				userEditList.add(user);	
			}

			rs.close();			
			pstmt.close();
			con.close();
		
			return userEditList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findInUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findInUser()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findInUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findInUser()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	public List findInUserInfo(String phone) throws SQLException, DAOException {
		
		List userInfoList = new ArrayList();
		User user = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT  A.SEQUENCE      SEQUENCE  ");
			findQuery.append("		, A.USER_ID					USER_ID  ");
			findQuery.append("		, B.NAME						USER_NAME  ");
			findQuery.append("		, DECODE(A.USER_TYPE, '01', '내부사용자', '02', '외부사용자')		USER_TYPE ");
			findQuery.append("		, B.POP_REG_NM			REG_RGST_NUM  ");	
			findQuery.append("		, B.PART_NAME			COMPANY_DEPT  ");	
			findQuery.append("		, NVL(B.CLASS, ' ')		COMPANY_GROUP  ");
			findQuery.append("		, DECODE(B.MOBILE_PHN, NULL, DECODE(A.MOBILE_PHONE_NUM, NULL, ' ', A.MOBILE_PHONE_NUM),B.MOBILE_PHN)  USER_MOBILE");
			findQuery.append("		, NVL(B.COMPANY_PHN, ' ')		USER_TELNUMBER   ");
			findQuery.append("		, DECODE(A.EMAIL2, NULL, ' ', A.EMAIL2)		USER_EMAIL2   ");
			findQuery.append("		, DECODE(A.EMAIL3, NULL, ' ', A.EMAIL3)		USER_EMAIL3   ");
			findQuery.append("		, DECODE(A.APPLY_STAT_CD, '01', '가입', '02', '탈퇴', '03', '재가입')		APPLY_STAT_CD ");
			findQuery.append("		, DECODE(A.PROG_STAT_CD, '01', '신청', '02', '승인', '03', '거부')		PROG_STAT_CD ");
			findQuery.append("		, DECODE(A.AUTH_TYPE, '00', '관리자', '01', '분석운용자', '02', '운용자', '03', '사용자', '04', '외부사용자')		AUTH_TYPE ");  
			findQuery.append("      , TO_CHAR(A.REG_DATE, 'YYYY/MM/DD')  REG_DATE    "); 
			findQuery.append("	    , NVL(TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD'), ' ')	APPLY_DATE  ");
			findQuery.append("	    , NVL(A.UPDATE_ID, ' ') UPDATE_ID    ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL A, AQUA_USER_FREENET_TBL B  ");
			findQuery.append("WHERE A.MOBILE_PHONE_NUM = B.MOBILE_PHN    ");      
			findQuery.append("AND B.MOBILE_PHN = ?  ");
			
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(findQuery.toString());
		    pstmt.setString(1,phone);
		    rs = pstmt.executeQuery();
			
			
			while ( rs.next() ){
				
				user = new User();
				user.setSequence(rs.getString("SEQUENCE"));
				user.setUserId(rs.getString("USER_ID"));				
				user.setName(rs.getString("USER_NAME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setRegRgstNum(rs.getString("REG_RGST_NUM"));
				user.setCompanyDept(rs.getString("COMPANY_DEPT"));
				user.setCompanyGroup(rs.getString("COMPANY_GROUP"));
				user.setMobile(rs.getString("USER_MOBILE"));
				user.setInnerTel(rs.getString("USER_TELNUMBER"));
				user.setEmail2(rs.getString("USER_EMAIL2"));
				user.setEmail3(rs.getString("USER_EMAIL3"));
				user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));
				user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
				user.setAuthType(rs.getString("AUTH_TYPE"));
				user.setRegDate(rs.getString("REG_DATE"));	
				user.setApplyDate(rs.getString("APPLY_DATE"));
				user.setUpdateId(rs.getString("UPDATE_ID"));
				
				userInfoList.add(user);	
			}

			rs.close();			
			pstmt.close();
		
			return userInfoList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findInUserInfo() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findInUserInfo()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findInUserInfo() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findInUserInfo()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	public List findOutUser(int seq) throws SQLException, DAOException {
		
		List userEditList = new ArrayList();
		User user = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT  A.SEQUENCE		SEQUENCE ");
			findQuery.append("		, A.USER_ID					USER_ID ");
			findQuery.append("		, A.USER_NAME			USER_NAME ");
			findQuery.append("		, A.USER_TYPE				USER_TYPE ");
			findQuery.append("		, DECODE(A.USER_TYPE, '01', '내부', '02', '외부')  USER_CODE  ");
			findQuery.append("		, A.REG_RGST_NUM		REG_RGST_NUM ");
			findQuery.append("		, A.COMPANY				COMPANY_DEPT ");
			findQuery.append("		, NVL(B.COMPANY_GROUP, ' ')	COMPANY_GROUP ");
			findQuery.append("		, A.MOBILE_PHONE_NUM			USER_MOBILE ");
			findQuery.append("		, NVL(B.USER_TELNUMBER, ' ')	USER_TELNUMBER ");
			findQuery.append("		, DECODE(A.EMAIL2, NULL, ' ', A.EMAIL2)			USER_EMAIL2 ");
			findQuery.append("		, DECODE(A.EMAIL3, NULL, ' ', A.EMAIL3)			USER_EMAIL3 ");
			findQuery.append("		, A.APPLY_STAT_CD		APPLY_STAT_CD ");
			findQuery.append("		, A.PROG_STAT_CD		PROG_STAT_CD ");
			findQuery.append("		, A.AUTH_TYPE		AUTH_TYPE ");
			findQuery.append("		, TO_CHAR(A.REG_DATE, 'YYYY/MM/DD') REG_DATE ");
			findQuery.append("		, NVL(TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD'), ' ')	APPLY_DATE ");
			findQuery.append("		, NVL(A.UPDATE_ID, ' ') UPDATE_ID ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL A, AQUA2_CASS_USER_INFO_TBL B ");
			findQuery.append("WHERE A.USER_ID = B.MAGICN_ID(+) ");
			findQuery.append("AND A.SEQUENCE = ?  ");
			
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(findQuery.toString());
		    pstmt.setInt(1,seq);
		    rs = pstmt.executeQuery();
			
			
			while (rs.next()){
				
				user = new User();
				user.setSequence(rs.getString("SEQUENCE"));
				user.setUserId(rs.getString("USER_ID"));				
				user.setName(rs.getString("USER_NAME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setUserCode(rs.getString("USER_CODE"));
				user.setRegRgstNum(rs.getString("REG_RGST_NUM"));
				user.setCompanyDept(rs.getString("COMPANY_DEPT"));
				user.setCompanyGroup(rs.getString("COMPANY_GROUP"));
				user.setMobile(rs.getString("USER_MOBILE"));
				user.setInnerTel(rs.getString("USER_TELNUMBER"));
				user.setEmail2(rs.getString("USER_EMAIL2"));
				user.setEmail3(rs.getString("USER_EMAIL3"));
				user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));
				user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
				user.setAuthType(rs.getString("AUTH_TYPE"));
				user.setRegDate(rs.getString("REG_DATE"));	
				user.setApplyDate(rs.getString("APPLY_DATE"));
				user.setUpdateId(rs.getString("UPDATE_ID"));
				
				userEditList.add(user);	
			}

			rs.close();			
			pstmt.close();
		
			return userEditList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findOutUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findOutUser()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findOutUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findOutUser()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	
	/**
	 * 사용자 아이디 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public List findOutUserInfo(String userId) throws SQLException, DAOException {
		
		List userInfoList = new ArrayList();
		User user = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT  A.SEQUENCE		SEQUENCE ");
			findQuery.append("		, A.USER_ID					USER_ID ");
			findQuery.append("		, A.USER_NAME			USER_NAME ");
			findQuery.append("		, DECODE(A.USER_TYPE, '01', '내부사용자', '02', '외부사용자')		USER_TYPE ");
			findQuery.append("		, A.REG_RGST_NUM		REG_RGST_NUM ");
			findQuery.append("		, A.COMPANY				COMPANY_DEPT ");
			findQuery.append("		, NVL(B.COMPANY_GROUP, ' ')	COMPANY_GROUP ");
			findQuery.append("		, DECODE(B.USER_MOBILE, NULL, DECODE(A.MOBILE_PHONE_NUM, NULL,' ', A.MOBILE_PHONE_NUM),	B.USER_MOBILE)	USER_MOBILE ");
			findQuery.append("		, NVL(B.USER_TELNUMBER, ' ')	USER_TELNUMBER ");
			findQuery.append("		, DECODE(A.EMAIL2, NULL, ' ', A.EMAIL2)	USER_EMAIL2 ");
			findQuery.append("		, DECODE(A.EMAIL3, NULL, ' ', A.EMAIL3) USER_EMAIL3 ");
			findQuery.append("		, DECODE(A.APPLY_STAT_CD, '01', '가입', '02', '탈퇴', '03', '재가입')		APPLY_STAT_CD ");
			findQuery.append("		, DECODE(A.PROG_STAT_CD, '01', '신청', '02', '승인', '03', '거부')		PROG_STAT_CD ");
			findQuery.append("		, DECODE(A.AUTH_TYPE, '00', '관리자', '01', '분석운용자', '02', '운용자', '03', '사용자', '04', '외부사용자')		AUTH_TYPE ");
			findQuery.append("		, TO_CHAR(A.REG_DATE, 'YYYY/MM/DD') REG_DATE ");
			findQuery.append("		, NVL(TO_CHAR(A.APPLY_DATE, 'YYYY/MM/DD'), ' ')	APPLY_DATE ");
			findQuery.append("		, NVL(A.UPDATE_ID, ' ') UPDATE_ID ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL A, AQUA2_CASS_USER_INFO_TBL B ");
			findQuery.append("WHERE A.USER_ID = B.MAGICN_ID(+) ");
			findQuery.append("AND A.USER_ID = ? ");
			
			con = getConnection();
 			
		    PreparedStatement pstmt=con.prepareStatement(findQuery.toString());
		    pstmt.setString(1,userId);
		    rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				user = new User();
				user.setSequence(rs.getString("SEQUENCE"));
				user.setUserId(rs.getString("USER_ID"));				
				user.setName(rs.getString("USER_NAME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setRegRgstNum(rs.getString("REG_RGST_NUM"));
				user.setCompanyDept(rs.getString("COMPANY_DEPT"));
				user.setCompanyGroup(rs.getString("COMPANY_GROUP"));
				user.setMobile(rs.getString("USER_MOBILE"));
				user.setInnerTel(rs.getString("USER_TELNUMBER"));
				user.setEmail2(rs.getString("USER_EMAIL2"));	
				user.setEmail3(rs.getString("USER_EMAIL3"));
				user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));
				user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
				user.setAuthType(rs.getString("AUTH_TYPE"));
				user.setRegDate(rs.getString("REG_DATE"));	
				user.setApplyDate(rs.getString("APPLY_DATE"));
				user.setUpdateId(rs.getString("UPDATE_ID"));
				
				userInfoList.add(user);	
			}

			rs.close();			
			pstmt.close();
		
			return userInfoList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findOutUserInfo() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findOutUserInfo()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findOutUserInfo() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findOutUserInfo()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	
	/**
	 * 사용자 리스트를 만들기 위한 부분으로 현재 페이지와 
	 * 페이지당 카운트수를 이용하여 해당부분의 사용자만을 List콜렉션에
	 * 저장하여 반환.
	 */
	public List findUserList(int currentPage, int countPerPage)
		throws SQLException , DAOException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("	SEQUENCE ");
			findQuery.append("	, USER_TYPE ");
			findQuery.append("	, DECODE(USER_TYPE, '01', '내부', '02', '외부')  USER_CODE ");
			findQuery.append("	, USER_ID, USER_NAME ");
			findQuery.append("	, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
			findQuery.append("	, DECODE(APPLY_STAT_CD, '01', '가입', '02', '탈퇴', '03', '재가입') APPLY_STAT_CD ");
			findQuery.append("	, DECODE(AUTH_TYPE, '00', '관리자', '01', '분석운용자', '02', '운용자', '03', '사용자', '04', '외부사용자') AUTH_TYPE ");
			findQuery.append("	, DECODE(PROG_STAT_CD, '01', '신청', '02', '승인', '03', '거부') PROG_STAT_CD ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL ");
			findQuery.append("ORDER BY SEQUENCE DESC ");
	
			con = getConnection();
			pstmt = con.prepareStatement(findQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = pstmt.executeQuery();
			
			int start = ((currentPage-1) * countPerPage) + 1;
			
			List userList = null;
			if ((start >= 0) && rs.absolute(start)) {
				boolean hasNext = false;
				userList = new ArrayList();
				
				do {
					
					User user = new User();
					user.setSequence(rs.getString("SEQUENCE"));
					user.setUserType(rs.getString("USER_TYPE"));
					user.setUserCode(rs.getString("USER_CODE"));
					user.setUserId(rs.getString("USER_ID"));					
					user.setName(rs.getString("USER_NAME"));
					user.setRegDate(rs.getString("REG_DATE"));
					user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));					
					user.setAuthType(rs.getString("AUTH_TYPE"));
					user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
					
					userList.add(user);					
				} while ( (hasNext = rs.next()) && (--countPerPage > 0));				
			}
		
			rs.close();			
			pstmt.close();
	
			return userList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findUserList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findUserList()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findUserList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findUserList()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	/**
	 * 사용자 리스트를 만들기 위한 부분으로 현재 페이지와 
	 * 페이지당 카운트수를 이용하여 해당부분의 사용자만을 List콜렉션에
	 * 저장하여 반환.
	 */
	public List findSearchList(String str, int currentPage, int countPerPage)
		throws SQLException , DAOException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("	SEQUENCE ");
			findQuery.append("	, USER_TYPE ");
			findQuery.append("	, DECODE(USER_TYPE, '01', '내부', '02', '외부')  USER_CODE ");
			findQuery.append("	, USER_ID, USER_NAME ");
			findQuery.append("	, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
			findQuery.append("	, DECODE(APPLY_STAT_CD, '01', '가입', '02', '탈퇴', '03', '재가입') APPLY_STAT_CD ");
			findQuery.append("	, DECODE(AUTH_TYPE, '00', '관리자', '01', '분석운용자', '02', '운용자', '03', '사용자', '04', '외부사용자') AUTH_TYPE ");
			findQuery.append("	, DECODE(PROG_STAT_CD, '01', '신청', '02', '승인', '03', '거부') PROG_STAT_CD ");
			findQuery.append("FROM AQUA2_USER_INFO_TBL " + str );
			findQuery.append("ORDER BY SEQUENCE DESC ");
			con = getConnection();
 			
			pstmt = con.prepareStatement(findQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = pstmt.executeQuery();
		
		    int start = ((currentPage-1) * countPerPage) + 1;
			
			List userList = null;
			
			if ((start >= 0) && rs.absolute(start)) {
				boolean hasNext = false;
				userList = new ArrayList();
				
				do {
					
					User user = new User();
					user.setSequence(rs.getString("SEQUENCE"));
					user.setUserType(rs.getString("USER_TYPE"));
					user.setUserCode(rs.getString("USER_CODE"));
					user.setUserId(rs.getString("USER_ID"));					
					user.setName(rs.getString("USER_NAME"));
					user.setRegDate(rs.getString("REG_DATE"));
					user.setApplyStat_Cd(rs.getString("APPLY_STAT_CD"));					
					user.setAuthType(rs.getString("AUTH_TYPE"));
					user.setProgStat_Cd(rs.getString("PROG_STAT_CD"));
					
					userList.add(user);					
				} while ( (hasNext = rs.next()) && (--countPerPage > 0));				
			}
	
			rs.close();
			pstmt.close();
	
			return userList;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findSearchList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findSearchList()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".findSearchList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findSearchList()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( pstmt != null ){
				pstmt.close();
			}
			if ( con != null ){
				con.close();
			}
		}
	}
	

	public int getTotalNo() throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer maxQuery = new StringBuffer();
			maxQuery.append("SELECT count(*) FROM AQUA2_USER_INFO_TBL");

			con = getConnection();
			pstmt = con.prepareStatement(maxQuery.toString());
			rs = pstmt.executeQuery();

			rs.next();
			int totalNo = rs.getInt(1);

			rs.close();
			pstmt.close();

			return totalNo;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getTotalNo() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getTotalNo()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getTotalNo() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getTotalNo()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	public int getSearchNo(String str) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer maxQuery = new StringBuffer();
			maxQuery.append("SELECT count(*) FROM AQUA2_USER_INFO_TBL  " + str );
			
			con = getConnection();
			pstmt = con.prepareStatement(maxQuery.toString());
			rs = pstmt.executeQuery();

			rs.next();
			int totalNo = rs.getInt(1);

			rs.close();
			pstmt.close();
			

			return totalNo;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getSearchNo() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getSearchNo()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getSearchNo() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getSearchNo()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}
			if ( pstmt != null ){
				pstmt.close();
			}	
			if ( con != null ){
				con.close();
			}
		}
	}
	
	/**
	 * 인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 
	 * 유무를 판별. 
	 */
	public boolean existedUser(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer existedQuery = new StringBuffer();
			existedQuery.append("SELECT count(*) FROM AQUA2_USER_INFO_TBL WHERE USER_ID = ? ");		
		
			con = getConnection();
			pstmt=con.prepareStatement(existedQuery.toString());			
			pstmt.setString(1,userId);
			
			rs = pstmt.executeQuery();			
			
			int count = 0;
			if (rs.next()){
				count = rs.getInt(1);
			}					
			
			rs.close();
			pstmt.close();
			
			if(count > 0) {
				return true;
			} else {
				return false;
			}			
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".existedUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".existedUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}		
	}
	
	/**
	 * 인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 
	 * 유무를 판별. 
	 */
	public boolean existedKTFUser(String mobileNum) throws SQLException, DAOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer existedKTFQuery = new StringBuffer();
			existedKTFQuery.append("SELECT count(*) \n");
			existedKTFQuery.append("FROM AQUA_USER_FREENET_TBL \n");
			existedKTFQuery.append("WHERE MOBILE_PHN = ? ");		
			
			con = getConnection();
			pstmt=con.prepareStatement(existedKTFQuery.toString());			
			pstmt.setString(1,mobileNum);
			
			rs = pstmt.executeQuery();		
			
			int count = 0;
			if (rs.next()){
				count = rs.getInt(1);
			}					
			
			rs.close();
			pstmt.close();
			
			if (count > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".existedKTFUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".existedKTFUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}		
	}
	
	/**
	 * 인자로 전달되는 아이디를 가지는 사용자가 인터넷운용팀에 존재하는지의 
	 * 유무를 판별. 
	 */
	public boolean existedTeamUser(String mobileNum) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer existedKTFQuery = new StringBuffer();
			existedKTFQuery.append("SELECT count(*) \n");
			existedKTFQuery.append("FROM AQUA_USER_FREENET_TBL \n");
			existedKTFQuery.append("WHERE PART_CD = '60001326' ");	
			existedKTFQuery.append("  AND   MOBILE_PHN = ? ");	
			
			con = getConnection();
			
			Object[] param = new Object[1];
		    param[0] = mobileNum;
			
			rs = getQuery(con, existedKTFQuery.toString(), param);			
			
			int count = 0;
			if (rs.next()){
				count = rs.getInt(1);
			}					
			
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException se){
			log.error(this.getClass().getName()+".existedTeamUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".existedTeamUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}		
	}
	
	/**
	 * 인자로 전달되는 아이디를 가지는 사용자가 CASS 정보에 존재하는지의 
	 * 유무를 판별. 
	 */
	public boolean existedCassUser(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer existedCASSQuery = new StringBuffer();
			existedCASSQuery.append("SELECT count(*) \n");
			existedCASSQuery.append("FROM AQUA2_CASS_USER_INFO_TBL \n");
			existedCASSQuery.append("WHERE MAGICN_ID = ? ");	
			
			con = getConnection();
			pstmt = con.prepareStatement(existedCASSQuery.toString());			
			pstmt.setString(1,userId);
			
			rs = pstmt.executeQuery();		
			
			int count = 0;
			if (rs.next()){
				count = rs.getInt(1);
			}					
			
			rs.close();
			pstmt.close();
			
			if (count > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".existedTeamUser() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".existedTeamUser() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}		
	}
	
	/**
	 * 아이디와 주민번호를 인자로 전달받아 CASS 정보와 함께 사용자 테이블에 입력 
	 */
	public int createCass(String userId, String rgstNo) throws SQLException, DAOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_INFO_TBL  ");
			insertQuery.append("SELECT  SEQ_USER_INFO.NEXTVAL, A.MAGICN_ID ");
			insertQuery.append(" 		, A.USER_NAME, '0000000000000', REPLACE(USER_MOBILE, '-', '')  ");
			insertQuery.append(" 		, '02', '01', '02', '04', '', ?, SYSDATE, '', '', '01', B.COMPANY_NAME, B.CP_ID, '', '', A.USER_EMAIL " );
			insertQuery.append("FROM  	AQUA2_CASS_USER_INFO_TBL A, AQUA2_CASS_CP_CODE_TBL B  ");
			insertQuery.append("WHERE	A.CP_ID = B.CP_ID  ");
			insertQuery.append("  AND	A.MAGICN_ID = ?  ");	
			
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			//pstmt.setString(1, rgstNo);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".createCass() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createCass() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	/*	
 	public int createCass(String userId, String rgstNo) throws SQLException, DAOException {
 		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_INFO_TBL  ");
			insertQuery.append("SELECT  SEQ_USER_INFO.NEXTVAL, A.MAGICN_ID ");
			insertQuery.append(" 		, A.USER_NAME, ?, REPLACE(USER_MOBILE, '-', '')  ");
			insertQuery.append(" 		, '02', '01', '02', '04', '', ?, SYSDATE, '', '', '01', B.COMPANY_NAME, B.CP_ID  ");
			insertQuery.append("FROM  	AQUA2_CASS_USER_INFO_TBL A, AQUA2_CASS_CP_CODE_TBL B  ");
			insertQuery.append("WHERE	A.CP_ID = B.CP_ID  ");
			insertQuery.append("  AND	A.MAGICN_ID = ?  ");	
		
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, rgstNo);
			pstmt.setString(2, userId);
			pstmt.setString(3, userId);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		
			return result;

		} catch(SQLException se){
			log.error(this.getClass().getName()+".createCass() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createCass() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}
	 */	
	
	/**
	 * 아이디를 인자로 전달받아 사용자 정보를 로그인정보 테이블에 입력 
	 */
	public int loginCass(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_CON_LOG_TBL  ");
			insertQuery.append("SELECT  TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') DATE_FLD, USER_ID ");
			insertQuery.append("      , AUTH_TYPE, CP_ID, '', USER_NAME, COMPANY ");
			insertQuery.append("FROM  	AQUA2_USER_INFO_TBL  ");
			insertQuery.append("WHERE	USER_ID = ?  ");	
		
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, userId);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".createCass() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createCass() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createCass()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}
		
	/**
	 * 아이디와 전화번호, 권한등급을 인자로 전달받아 FREENET 정보와 함께 사용자 테이블에 입력 
	 */
	public int createKTF(String userId, String mobileNum, String authType) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			/*
			System.out.println("아이디정보:"+userId);
			System.out.println("사용권한 :"+authType);
			System.out.println("전화번호:"+mobileNum);
		 	*/
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_INFO_TBL ");  
			insertQuery.append("SELECT SEQ_USER_INFO.NEXTVAL, ?, NAME, POP_REG_NM, MOBILE_PHN ");
			insertQuery.append("	   , '01', '01', '02', ?, '', ?, SYSDATE, '', '', '01', PART_NAME, '', 'fingers10@kt.com', '', E_MAIL ");
			insertQuery.append("FROM AQUA_USER_FREENET_TBL ");
			insertQuery.append("WHERE MOBILE_PHN = ? ");
		
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, authType);
			pstmt.setString(3, userId);
			pstmt.setString(4, mobileNum);
	
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".createKTF() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createKTF() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	/**
	 * 전화번호를 인자로 전달받아 사용자정보를 로그인정보 테이블에 입력 
	 */
	public int loginKTF(String mobileNum) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("INSERT INTO AQUA2_USER_CON_LOG_TBL ");  
			insertQuery.append("SELECT  TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') DATE_FLD, A.USER_ID ");
			insertQuery.append(" , A.AUTH_TYPE, '', B.EMP_NM, A.USER_NAME, A.COMPANY  ");
			insertQuery.append("FROM AQUA2_USER_INFO_TBL A, AQUA_USER_FREENET_TBL B ");
			insertQuery.append("WHERE A.MOBILE_PHONE_NUM = B.MOBILE_PHN ");
			insertQuery.append("  AND A.MOBILE_PHONE_NUM = ? ");
		
			con = getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, mobileNum);
			
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			
			return result;
		} catch(SQLException se){
			log.error(this.getClass().getName()+".createKTF() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + se);
			
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".createKTF() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "createKTF()" + "=>" + ne);
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}
			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public String getUserAuthType(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strAuthType = "";
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT AUTH_TYPE           \n");
			query.append("   FROM AQUA2_USER_INFO_TBL  \n");
			query.append("  WHERE USER_ID = ?         \n");		
			
			con = getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				strAuthType = rs.getString("AUTH_TYPE");
			}

			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getUserAuthType() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getUserAuthType() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findMenu()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}	
		return strAuthType;
	}
	
	public String getUserType(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strUserType = "";
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT USER_TYPE           \n");
			query.append("   FROM AQUA2_USER_INFO_TBL  \n");
			query.append("  WHERE USER_ID = ?         \n");		
			
			con = getConnection();
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				strUserType = rs.getString("USER_TYPE");
			}
		
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getUserType() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getUserType()()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getUserType() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getUserType()()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}	
		return strUserType;
	}
	
	public String getCpId(String userId) throws SQLException, DAOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strCpID = "";
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT CP_ID           \n");
			query.append("   FROM AQUA2_USER_INFO_TBL  \n");
			query.append("  WHERE USER_ID = ?         \n");		
			
			con = getConnection();
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()){
				strCpID = rs.getString("CP_ID");
			}
		
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getUserType() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getUserType()()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getUserType() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getUserType()()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}					
			if ( con != null ){
				con.close();
			}
		}	
		return strCpID;
	}
	
	public List getGongjiList() throws SQLException, DAOException {
		
		List gongjiList = new ArrayList();
		BoardForm gongjiForm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT	ROWNUM, SEQUENCE, BBS_TITLE, REG_DATE   \n");
			query.append("FROM   \n");
			query.append("(   \n");
			query.append("	SELECT	SEQUENCE   \n");
			query.append("			, BBS_TITLE   \n");
			query.append("			, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE   \n");
			query.append("	FROM AQUA2_NOTICE_BOARD_TBL   \n");
			query.append("	ORDER BY SEQUENCE DESC   \n");
			query.append(")   \n");
			query.append("WHERE ROWNUM <= 4   \n");
			
			con = getConnection();
			pstmt = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				
				gongjiForm = new BoardForm();
				gongjiForm.setSequence(rs.getString("SEQUENCE"));
				gongjiForm.setBbsTitle(rs.getString("BBS_TITLE"));
				gongjiForm.setRegDate(rs.getString("REG_DATE"));	
				
				gongjiList.add(gongjiForm);
			}			
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findGongjiList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findGongjiList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".findGongjiList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findGongjiList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( pstmt != null ){
				pstmt.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return gongjiList;
	}
	
	public List getUserBoardList() throws SQLException, DAOException {
		
		List userBoardList = new ArrayList();
		BoardForm userBoardForm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT	ROWNUM, SEQUENCE, BBS_TITLE, REG_DATE   \n");
			query.append("FROM   \n");
			query.append("(   \n");
			query.append("	SELECT	SEQUENCE   \n");
			query.append("			, BBS_TITLE   \n");
			query.append("			, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE   \n");
			query.append("	FROM AQUA2_PUBLIC_BOARD_TBL   \n");
			query.append("	ORDER BY SEQUENCE DESC   \n");
			query.append(")   \n");
			query.append("WHERE ROWNUM <= 4   \n");
			
			con = getConnection();
			pstmt = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			rs = pstmt.executeQuery();	
			
			while (rs.next()){
				
				userBoardForm = new BoardForm();
				userBoardForm.setSequence(rs.getString("SEQUENCE"));
				userBoardForm.setBbsTitle(rs.getString("BBS_TITLE"));
				userBoardForm.setRegDate(rs.getString("REG_DATE"));	
				
				userBoardList.add(userBoardForm);
			}	
			
			rs.close();
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".findUserBoardList() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "findUserBoardList()" + "=>" + se);
		} catch(NamingException ne){
			log.error(this.getClass().getName()+".findUserBoardList() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "findUserBoardList()" + "=>" + ne);
		} finally {
			if ( rs != null ){
				rs.close();
			}	
			if ( pstmt != null ){
				pstmt.close();
			}	
			if ( con != null ){
				con.close();
			}
		}		
		return userBoardList;
	}
	
	public List getBbsInfo(int seq, int gubun) throws SQLException, DAOException {
		
		List userBoardInfo = new ArrayList();
		BoardForm BoardInfo = null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			
			StringBuffer findQuery = new StringBuffer();
			
			if (gubun == 1) {
				findQuery.append("SELECT	SEQUENCE");
				findQuery.append("				, BBS_TITLE ");
				findQuery.append("				, CONTENTS ");
				findQuery.append("				, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
				findQuery.append("				, REG_ID ");
				findQuery.append("				, TO_CHAR(UPDATE_DATE, 'YYYY/MM/DD') UPDATE_DATE ");
				findQuery.append("				, UPDATE_ID ");
				findQuery.append("FROM AQUA2_NOTICE_BOARD_TBL ");
				findQuery.append("WHERE SEQUENCE = ? ");
			} else {
				findQuery.append("SELECT	SEQUENCE");
				findQuery.append("				, BBS_TITLE ");
				findQuery.append("				, CONTENTS ");
				findQuery.append("				, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
				findQuery.append("				, REG_ID ");
				findQuery.append("				, TO_CHAR(UPDATE_DATE, 'YYYY/MM/DD') UPDATE_DATE ");
				findQuery.append("				, UPDATE_ID ");
				findQuery.append("FROM AQUA2_PUBLIC_BOARD_TBL ");
				findQuery.append("WHERE SEQUENCE = ? ");
			}
					
			con = getConnection();
	     			
		    PreparedStatement pstmt=con.prepareStatement(findQuery.toString());
		    pstmt.setInt(1,seq);
		    rs = pstmt.executeQuery();

			while (rs.next()){
				
				BoardInfo = new BoardForm();
				BoardInfo.setSequence(rs.getString("SEQUENCE"));
				BoardInfo.setBbsTitle(rs.getString("BBS_TITLE"));
				BoardInfo.setContents(rs.getString("CONTENTS"));
				BoardInfo.setRegDate(rs.getString("REG_DATE"));
				BoardInfo.setRegId(rs.getString("REG_ID"));
				BoardInfo.setUpdateDate(rs.getString("UPDATE_DATE"));
				BoardInfo.setUpdateId(rs.getString("UPDATE_ID"));
				
				userBoardInfo.add(BoardInfo);
			}

			rs.close();			
			pstmt.close();
			
		} catch(SQLException se){
			log.error(this.getClass().getName()+".getBoardInfo() Exception:"+se);
			throw new DAOException(this.getClass().getName() + "." + "getBoardInfo()" + "=>" + se);
		} catch(NamingException ne){
			log.debug(this.getClass().getName()+".getBoardInfo() NamingException:"+ne);
			throw new DAOException(this.getClass().getName() + "." + "getBoardInfo()" + "=>" + ne);
		}  finally {
			if ( rs != null ){
				rs.close();
			}		
			if ( con != null ){
				con.close();
			}
		}
		return userBoardInfo;
	}
	
	public String[] loginProcess(String id,String mobileNum,String regRgstNum) throws SQLException, Exception{
		
		String resultStr[]	= new String[4];
		
		UserDAO manager = new UserDAO();
		String authType   = "";
		String resultPage = "success";
		String userLevel = "";
		String cpId		 = "";
		String userType	 = "";
		
		//1.aqua_user_info_tbl에 등록여부 확인			
		boolean existedUser = manager.existedUser(id);
	
		if(existedUser == false) {
			//2.내부사용자인지 확인 
			boolean existedKTFUse = manager.existedKTFUser(mobileNum);
			
			if(existedKTFUse == true){ 	//내부 사용자
				//3. KTF직원인 경우 부서(인터넷운용팀)에 따라 다른 권한을 부여하기 위해서 부서에 존재하는지 확인
				boolean existedTeamUse = manager.existedTeamUser(mobileNum);
				
				if(existedTeamUse == true){
					authType = "02"; 	//운용자
				} else {
					authType = "03"; 	//사용자
				}
				
				//KTF직원인 경우 운용자 또는 사용자 권한으로 사용자 테이블에 등록시킨다.
				int result = manager.createKTF(id, mobileNum, authType);
			} else { 					//외부사용자	
				
				//aqua2_cass_user_info_tbl에 등록여부 확인
				boolean existedCassUse = manager.existedCassUser(id);
				
				if(existedCassUse == true) {
					//aqua2_cass_user_info_tbl에 존재 할 경우 외부사용자로 사용자 테이블에 등록시킨다.
					int result = manager.createCass(id, regRgstNum);
				} else {
					//존재하지 않는 경우 로그인페이지로 이동 시킨다.(cass 정보가 없는 경우 회원등록 불가)
					resultPage = "notAllowed";
				}
			}	  
		}
		
		if(resultPage.equals("notAllowed") == false){
			
			//사용자 권한 타입 코드를 가지고 온다.
			String strUserAuthType = manager.getUserAuthType(id);
			
			//외부사용자인 경우 해당 cp_id를 가져온다.
			if(strUserAuthType.equals("04")) {
				cpId = manager.getCpId(id);
			}
			
			userLevel = strUserAuthType.substring(1,2);
			
			//사용자 타입(USER_TYPE)을 가져온다.
			userType = manager.getUserType(id);
			
			//사용자 타입(USER_TYPE)에 따른 로그인정보 저장
			if(userType.equals("01")) { 				//내부사용자 로그인
				manager.loginKTF(mobileNum);
			} else if(userType.equals("02")) { 			//외부사용자 로그인
				manager.loginCass(id);
			}
		}
		
		resultStr[0] = userLevel;
		resultStr[1] = resultPage;
		resultStr[2] = cpId;
		resultStr[3] = userType;
		
		return resultStr;
		
	}
}
