package com.ktf.aqua.db;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class DBDataSource {
	
	private static DataSource ds = null;
	private static DataSource ds1 = null;
	private static DataSource ds2 = null;
	
	static{
		
		try {
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds  = (DataSource)envContext.lookup("jdbc/AQUADataSource");
			ds1 = (DataSource)envContext.lookup("jdbc/AQUADS1");
			ds2 = (DataSource)envContext.lookup("jdbc/AQUADS2");
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}	
	}
	 
	public static Connection getCon(int type) throws SQLException {
		
		/*
		BasicDataSource bds = (BasicDataSourcde) ds;
		System.out.println("MAX ACTIVE : " + bds.getMaxActive());
	    System.out.println("MAX IDLE : "   + bds.getMaxIdle());
	    System.out.println("ACTIVE : " 	   + bds.getNumActive());
	    System.out.println("IDLE : "	   + bds.getNumIdle());
	    */
		
		switch (type){
		case 0:
			return ds.getConnection();
		case 1:
			return ds1.getConnection();
		case 2:
			return ds2.getConnection();
		default:
			return ds.getConnection();
		}
	}
}
