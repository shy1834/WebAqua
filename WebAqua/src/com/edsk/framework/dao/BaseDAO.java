package com.edsk.framework.dao;
import java.sql.*;

import javax.naming.*;

import org.apache.commons.logging.*;

import com.ktf.aqua.db.*;

/**
 * DAO를 위한 기본 객체.<br>
 * Database에 접근하기 위한 connection get/release,
 * 간단한 DT, String, int, long, double, Timestamp 조회 서비스를 제공한다.<br>
 * DT를 정의하여 사용할 때는 반드시 getDataTransferObject 메쏘드를 재정의 해야 한다.<br> 
 * 
 * @author mksong
 */
abstract public class BaseDAO {
    protected static Log log = LogFactory.getLog(BaseDAO.class);
    private static FactoryDAO daoFactory=FactoryDAO.getInstance();
    
    /**
     * key 에 해당하는 DAO 인스턴스를 생성한다.
     * 
     * @param key DAO의 key
     * @return key에 해당하는 DAO 인스턴스
     */
    public static BaseDAO lookupInstance(String key) {
        return daoFactory.getDAO(key);
    }
    
    /**
     * DAOBase 생성자<br>
     * DataSource의 이름을 초기화 한다.
     */
	protected BaseDAO() {
	}

    /**
     * DataSource로부터 Connection을 획득.
     * @return JDBC Connection
     */
    protected Connection getConnection() throws  NamingException,SQLException {
    	
    	//return SimpleDataSource.getInstance().getDataSource().getConnection(); 
    	return DBDataSource.getCon(0);
    }
    
    protected Connection getConnection(int type) throws  NamingException,SQLException {
    	
    	//return SimpleDataSource.getInstance().getDataSource().getConnection(); 
    	return DBDataSource.getCon(type);
    }

    /**
     * Connection close
     * @param conn JDBC Connection
     */ 
    protected void releaseConnection(Connection conn) throws SQLException {
        log.debug("close the connection");
        conn.close();
    }    

    /**
     * Query문에 따라 ResultSet 를 return한다.<br>
     * PreparedStatement로 처리되며 binding될 parameter는 변수 param을 통해
     * 전달된다.
     * @param query   SQL Query
     * @param param   조회시 인수 리스트
     * @return 조회결과
     */
    protected ResultSet getQuery(Connection conn,String query, Object[] param)
            throws SQLException { 
       
        ResultSet rs=null;
        log.info("query = "+query);
        PreparedStatement pstmt=conn.prepareStatement(query);
        try {
            
            if (param != null) {
                for (int i=0;i<param.length;i++) {
                	log.debug("param = "+param[i].toString());
                	pstmt.setObject(i+1,param[i]);
                }
            }
            rs = pstmt.executeQuery();
            
         }
         finally {
         	pstmt.close();
         }
         return rs;
    }
    
    /**
     * insert, update, delete query 수행
     * @param query insert/update/delete query
     * @return query로 인해 변경된 row의 수
     * @throws NamingException
     * @throws SQLException
     */
    protected int executeUpdate(String query) throws NamingException,SQLException {
        Connection conn=getConnection();
        log.info("query = "+query);
        try {
            return executeUpdate(conn,query);
        } finally {
            releaseConnection(conn);
        }
    }

    /**
     * insert, update, delete query 수행
     * @param query insert/update/delete query
     * @param param Parameter
     * @return query로 인해 변경된 row의 수
     * @throws NamingException
     * @throws SQLException
     */
    protected int executeUpdate(String query,Object param) throws NamingException,SQLException {
        Connection conn=getConnection();        
        try {
            return executeUpdate(conn,query,param);
        } finally {
            releaseConnection(conn);
        }
    }

    /**
     * insert, update, delete query 수행
     * @param query insert/update/delete query
     * @param param Parameters
     * @return query로 인해 변경된 row의 수
     * @throws NamingException
     * @throws SQLException
     */
    protected int executeUpdate(String query,Object[] param) throws NamingException,SQLException {
        Connection conn=getConnection();
        log.info("query = "+query);
        try {
            return executeUpdate(conn,query,param);
        } finally {
            releaseConnection(conn);
        }
    }

    /**
     * N개의 값를 insert, update, delete 한다.
     * @param query insert
     * @param param update하거나 insert할 값들
     * @return insert, update, delete된 값의 수 
     * @throws NamingException
     * @throws SQLException
     */
    protected int executeUpdateValues(String query,Object[] param) throws NamingException,SQLException {
        int count=0;
        if (param!=null) {
            Connection conn=getConnection();
            try {
                count=executeUpdateValues(conn,query,param);
            } finally {
                releaseConnection(conn);
            }
        }
        return count;
    }

    protected int executeUpdateValues(Connection conn,String query,Object[] param) throws SQLException {
        int count=0;
        if (param!=null) {
            log.debug("execute update for the query = "+query);
            PreparedStatement stmt=conn.prepareStatement(query);
            try {
                for (int i=0;i<param.length;i++) {
                    log.debug("param["+i+"]="+param[i]);
                    stmt.setObject(1,param[i]);
                    count+=stmt.executeUpdate();
                }
            } finally {
                stmt.close();
            }
        }
        return count;
    }

    /**
     * N개의 값를 insert, update, delete 한다.
     * @param query insert
     * @param param update하거나 insert할 값들
     * @return insert, update, delete된 값의 수 
     * @throws NamingException
     * @throws SQLException
     */
    protected int executeUpdateValues(String query,Object[][] param) throws NamingException,SQLException {
        int count=0;
        if (param!=null) {
            Connection conn=getConnection();
            try {
                count=executeUpdateValues(conn,query,param);
            } finally {
                releaseConnection(conn);
            }
        }
        return count;
    }

    protected int executeUpdateValues(Connection conn,String query,Object[][] param) throws SQLException {
        int count=0;
        if (param!=null) {
            log.debug("execute update for the query = "+query);
            PreparedStatement stmt=conn.prepareStatement(query);
            try {
                for (int i=0;i<param.length;i++) {
                    for (int j=0;j<param[i].length;j++) {
                        log.debug("param["+i+","+j+"]="+param[i][j]);
                        stmt.setObject(j+1,param[i][j]);
                    }
                    count+=stmt.executeUpdate();
                }
            } finally {
                stmt.close();
            }
        }
        return count;
    } 

    /**
     * insert,update,delete 쿼리 수행
     * @param conn Database Connection
     * @param query insert,update,delete 쿼리 
     * @return 변경된 row의 수
     */
    protected int executeUpdate(Connection conn, String query) throws SQLException {
        log.debug("execute update for the query = "+query);
    	Statement stmt=conn.createStatement();
        try {
            return stmt.executeUpdate(query);
        } finally {
        	stmt.close();
        }
    }

	/**
	 * insert,update,delete 쿼리 수행
	 * @param conn Database Connection
	 * @param query insert,update,delete 쿼리 
     * @param param Parameter
	 * @return 변경된 row의 수
	 */
    protected int executeUpdate(Connection conn, String query,Object param) throws SQLException {        
        log.debug("execute update for the query = "+query);
        PreparedStatement stmt=conn.prepareStatement(query);
        try {
            return executeUpdate(stmt,param);
        } finally {
            stmt.close();
        }
    }

	/**
	 * insert,update,delete 쿼리 수행
	 * @param conn Database Connection
	 * @param query insert,update,delete 쿼리 
	 * @param param Parameters
	 * @return 변경된 row의 수
	 */
    protected int executeUpdate(Connection conn, String query,Object[] param) throws SQLException {        
        log.debug("execute update for the query = "+query);
        PreparedStatement stmt=conn.prepareStatement(query);
        try {
            return executeUpdate(stmt,param);
        } finally {
            stmt.close();
        }
    }


	/**
	 * insert,update,delete 쿼리 수행
	 * @param stmt Database Statement
	 * @param param Parameter
	 * @return 변경된 row의 수
	 */
    protected int executeUpdate(PreparedStatement stmt, Object param) throws SQLException {
        if (param!=null) {
            log.debug("param="+param);
            stmt.setObject(1,param);
        }
        return stmt.executeUpdate();
    }

	/**
	 * insert,update,delete 쿼리 수행
	 * @param stmt Database Statement
	 * @param param Parameters
	 * @return 변경된 row의 수
	 */
    protected int executeUpdate(PreparedStatement stmt, Object[] param) throws SQLException {        
        if (param!=null) {
            for (int i=0;i<param.length;i++) {
                log.debug("param["+i+"]="+param[i]);
                stmt.setObject(i+1,param[i]);
            }
        }
        return stmt.executeUpdate();
    }
    
    
    /**
     * 문자열을 like 검색에 적용될 형태로 변환한다.<br>
     *@return 전달된 문자열이 null이면 "%", null이 아니면 "%"+문자열.toLowerCase()+"%".
     */
    protected String getLikeKeyword(String str) {
        return (str==null)?"%":"%"+str.toLowerCase()+"%";
    }
    
    protected boolean isNull(String str) {
    	return (str==null || str.length()==0);
    }
}