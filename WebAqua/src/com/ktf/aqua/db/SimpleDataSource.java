package com.ktf.aqua.db;

import java.sql.*;

import javax.sql.*;

import org.apache.commons.logging.*;
import org.apache.tomcat.dbcp.dbcp.*;


public final class SimpleDataSource {  

 private static Log log = LogFactory.getLog(SimpleDataSource.class);

 private DataSource dataSource;

 private static final String DRIVER_CLASS_NAME;
 private static final String DB_URL;
 private static final String DB_USERNAME;
 private static final String DB_PASSWD;

 static{ // 기본 은 static 초기화 
  DRIVER_CLASS_NAME = "core.log.jdbc.driver.OracleDriver";
  DB_URL			= "jdbc:oracle:thin:@192.168.56.102:1521:orcl";
  DB_USERNAME 		= "aqua";
  DB_PASSWD 		= "aqua";
 }

 private SimpleDataSource(){
  // singleton 으로 작성 ~
  this.dataSource = setupDataSource();
 }


 public static SimpleDataSource getInstance(){
  return SimpleDataSourceHolder.simpleDataSource;
 }

 private DataSource setupDataSource(){
  BasicDataSource dataSource = new BasicDataSource();
  dataSource.setDriverClassName(DRIVER_CLASS_NAME);
  dataSource.setUsername(DB_USERNAME);
  dataSource.setPassword(DB_PASSWD);
  dataSource.setUrl(DB_URL);
  dataSource.setMaxActive(100);
  dataSource.setMaxIdle(20);

  return dataSource;
 }

 public void shutdownDataSource(DataSource ds) throws SQLException{
	  BasicDataSource basicDataSource = (BasicDataSource) ds;
	  basicDataSource.close();
 }

  public static void printDataSourceStats(DataSource ds) throws SQLException {
    BasicDataSource bds = (BasicDataSource) ds;

  //if (log.isInfoEnabled()) {
    System.out.println("MAX ACTIVE : " + bds.getMaxActive());
    System.out.println("MAX IDLE : "   + bds.getMaxIdle());
    System.out.println("ACTIVE : " 	   + bds.getNumActive());
    System.out.println("IDLE : "	   + bds.getNumIdle());
  //}
 }

 public DataSource getDataSource() {
  return dataSource;
 }

 private static class SimpleDataSourceHolder {
  static final SimpleDataSource simpleDataSource = new SimpleDataSource();
 }
}
