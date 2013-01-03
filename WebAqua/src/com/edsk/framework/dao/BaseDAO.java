package com.edsk.framework.dao;
import java.sql.*;

import javax.naming.*;

import org.apache.commons.logging.*;

import com.ktf.aqua.db.*;

/**
 * DAO�� ���� �⺻ ��ü.<br>
 * Database�� �����ϱ� ���� connection get/release,
 * ������ DT, String, int, long, double, Timestamp ��ȸ ���񽺸� �����Ѵ�.<br>
 * DT�� �����Ͽ� ����� ���� �ݵ�� getDataTransferObject �޽�带 ������ �ؾ� �Ѵ�.<br> 
 * 
 * @author mksong
 */
abstract public class BaseDAO {
    protected static Log log = LogFactory.getLog(BaseDAO.class);
    private static FactoryDAO daoFactory=FactoryDAO.getInstance();
    
    /**
     * key �� �ش��ϴ� DAO �ν��Ͻ��� �����Ѵ�.
     * 
     * @param key DAO�� key
     * @return key�� �ش��ϴ� DAO �ν��Ͻ�
     */
    public static BaseDAO lookupInstance(String key) {
        return daoFactory.getDAO(key);
    }
    
    /**
     * DAOBase ������<br>
     * DataSource�� �̸��� �ʱ�ȭ �Ѵ�.
     */
	protected BaseDAO() {
	}

    /**
     * DataSource�κ��� Connection�� ȹ��.
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
     * Query���� ���� ResultSet �� return�Ѵ�.<br>
     * PreparedStatement�� ó���Ǹ� binding�� parameter�� ���� param�� ����
     * ���޵ȴ�.
     * @param query   SQL Query
     * @param param   ��ȸ�� �μ� ����Ʈ
     * @return ��ȸ���
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
     * insert, update, delete query ����
     * @param query insert/update/delete query
     * @return query�� ���� ����� row�� ��
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
     * insert, update, delete query ����
     * @param query insert/update/delete query
     * @param param Parameter
     * @return query�� ���� ����� row�� ��
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
     * insert, update, delete query ����
     * @param query insert/update/delete query
     * @param param Parameters
     * @return query�� ���� ����� row�� ��
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
     * N���� ���� insert, update, delete �Ѵ�.
     * @param query insert
     * @param param update�ϰų� insert�� ����
     * @return insert, update, delete�� ���� �� 
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
     * N���� ���� insert, update, delete �Ѵ�.
     * @param query insert
     * @param param update�ϰų� insert�� ����
     * @return insert, update, delete�� ���� �� 
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
     * insert,update,delete ���� ����
     * @param conn Database Connection
     * @param query insert,update,delete ���� 
     * @return ����� row�� ��
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
	 * insert,update,delete ���� ����
	 * @param conn Database Connection
	 * @param query insert,update,delete ���� 
     * @param param Parameter
	 * @return ����� row�� ��
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
	 * insert,update,delete ���� ����
	 * @param conn Database Connection
	 * @param query insert,update,delete ���� 
	 * @param param Parameters
	 * @return ����� row�� ��
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
	 * insert,update,delete ���� ����
	 * @param stmt Database Statement
	 * @param param Parameter
	 * @return ����� row�� ��
	 */
    protected int executeUpdate(PreparedStatement stmt, Object param) throws SQLException {
        if (param!=null) {
            log.debug("param="+param);
            stmt.setObject(1,param);
        }
        return stmt.executeUpdate();
    }

	/**
	 * insert,update,delete ���� ����
	 * @param stmt Database Statement
	 * @param param Parameters
	 * @return ����� row�� ��
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
     * ���ڿ��� like �˻��� ����� ���·� ��ȯ�Ѵ�.<br>
     *@return ���޵� ���ڿ��� null�̸� "%", null�� �ƴϸ� "%"+���ڿ�.toLowerCase()+"%".
     */
    protected String getLikeKeyword(String str) {
        return (str==null)?"%":"%"+str.toLowerCase()+"%";
    }
    
    protected boolean isNull(String str) {
    	return (str==null || str.length()==0);
    }
}