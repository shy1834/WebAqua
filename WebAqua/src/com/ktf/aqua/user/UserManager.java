/*
 * Created on 2006. 2. 15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ktf.aqua.user;


import java.sql.SQLException;
import java.util.List;

import com.ktf.aqua.user.common.User;
import com.ktf.aqua.user.dao.UserDAO;

/**
 * @author redfox
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserManager {
	
	private UserManager() {
	}
	
	public static UserManager instance() {
		return (new UserManager());
	}
	
	private UserDAO getDAO() {
		return new UserDAO();
	}
	
	/**
	 * 해당 user가 존재 하는지 확인 메소드
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean existedUser(String userId)throws  SQLException, Exception   {
		return getDAO().existedUser(userId);
		
	}
	
	/**
	 * KTF사용자인지 확인 한다.
	 * @param mobileNum
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean existedKTFUser(String mobileNum )throws  SQLException, Exception   {
		return getDAO().existedKTFUser( mobileNum );
		
	}
	

	/**
	 * KTF사용자(인터넷운용팀)인지 확인 한다.
	 * @param mobileNum
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean existedTeamUser(String mobileNum )throws  SQLException, Exception   {
		return getDAO().existedTeamUser( mobileNum );
		
	}
	
	
	/**
	 * CASS에 등록된 사용자인지 확인 한다.
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean existedCassUser(String userId )throws  SQLException, Exception   {
		return getDAO().existedCassUser( userId );
		
	}
	
	
	/**
	 * USER_INFO_TBL에 사용자 정보(CASS, FREENET)를 저장한다.
	 * @param 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int createCass(String userId, String rgstNo)throws  SQLException, Exception   {
		return getDAO().createCass(userId,  rgstNo);
	}
	
	public int createKTF(String userId, String mobileNum, String authType)throws  SQLException, Exception   {
		return getDAO().createKTF(userId,  mobileNum, authType);
	}
	
	
	/**
	 * LOGIN_INFO_TBL에 사용자 정보(USER_INFO_TBL)를 저장한다.
	 * @param 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int loginCass(String userId)throws  SQLException, Exception   {
		return getDAO().loginCass(userId);
	}
	
	public int loginKTF(String mobileNum)throws  SQLException, Exception   {
		return getDAO().loginKTF(mobileNum);
	}
	
	
	/**
	 * 사용자 이름 가지고 온다.
	 * @param userId
	 * @return
	 * @throws Exception
	 */
//	public String getUserName(String userId)throws  SQLException, Exception   {
//		return getDAO().findUser(userId).getName();
//	}
	
	public String getUserAuthType(String userId)throws  SQLException, Exception   {
		return getDAO().getUserAuthType(userId);
	}
	
	public String getUserType(String userId)throws  SQLException, Exception   {
		return getDAO().getUserType(userId);
	}
	
	public String getCpId(String userId)throws  SQLException, Exception   {
		return getDAO().getCpId(userId);
	}
	
	/**
	 * 공지사항, 사용자 게시판 정보를 가지고 온다.
	 * @param 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List findGongjiList()
	throws SQLException, Exception  {
	  return getDAO().getGongjiList();
	}
	
	public List findUserBoardList()
	throws SQLException, Exception  {
	  return getDAO().getUserBoardList();
	}
	
	public List findBbsInfo(int seq, int gubun)
	throws SQLException, Exception  {
		  return getDAO().getBbsInfo(seq, gubun);
	}

	/*
	public int Create(user)throws  SQLException, Exception   {
		return getDAO().create(user);
	}
	*/
	public int Update(int seq, String auth_type, String User_Email1, String User_Email2, String User_Mobile, String Company_Dept, String update_id)
		throws  SQLException, Exception   {
		return getDAO().update(seq, auth_type, User_Email1,User_Email2, User_Mobile, Company_Dept, update_id);
	}

	public int Remove(int seq)throws  SQLException, Exception   {
		return getDAO().remove(seq);
	}

	public List findInUser(int seq)throws  SQLException, Exception   {
		return getDAO().findInUser(seq);
	}
	
	public List findInUserInfo(String phone)throws  SQLException, Exception   {
		return getDAO().findInUserInfo(phone);
	}
	
	public List findOutUser(int seq)throws  SQLException, Exception   {
		return getDAO().findOutUser(seq);
	}
	
	public List findOutUserInfo(String userId)throws  SQLException, Exception   {
		return getDAO().findOutUserInfo(userId);
	}
	
	public List findUserList(int currentPage, int countPerPage)throws  SQLException, Exception   {
		return getDAO().findUserList(currentPage, countPerPage);
	}
	
	public List findSearchList(String str, int currentPage, int countPerPage)throws  SQLException, Exception   {
		return getDAO().findSearchList(str, currentPage, countPerPage);
	}
	
	/**
	 * 총사용자수 정보를 가지고 온다.
	 * @param 
	 * @return str_total_cnt
	 * @throws SQLException
	 * @throws Exception
	 */
	public int getUserTotCnt()throws  SQLException, Exception   {
		return getDAO().getTotalNo();
	}
	
	public int getUserSrhCnt(String str)throws  SQLException, Exception   {
		return getDAO().getSearchNo(str);
	}
	
}
