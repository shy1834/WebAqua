/*
 * Created on 2006. 1. 23.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.edsk.framework.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * ��� �Խ��ǿ��� ���� ���� ���� ����� ��Ƶ� ��ƿŬ����
 * 
 * @author june - ��â��
 * @�̸��� june1024@nate.com
 * @���ϸ� Util.java
 */
public class Util {
	private static Logger logger;
	
	/**
	 * log4�ʱ�ȭ (����)
	 */
	public static synchronized void log4jInstance( Class classs) {
		if ( logger == null ) {
			logger = Logger.getLogger( classs.getName() );
		}
	}
	
	/**
	 * log4 ��ȯ (����)
	 * @return
	 */
	public static Logger getLog4j() {
		return logger;
	}
	
	/**
	 * log4j�� �̿��Ͽ� log�� �����. (����)
	 * @param str
	 */
	public static void log4j( String str ) {
		logger.info( str );
	}
	
	/**
	 * ������ Ȯ���ڸ� �����Ѵ�.
	 * <BR>
	 * ��) <BR>
	 * [�Է�] "<B>abcder.txt</B>" ----> [���] "<B>.txt</B>" 
	 * 
	 * @param str
	 * @return
	 */
	public static String getFileExtension( String str ) {
		return ( str.lastIndexOf( "." ) > 0 ) ? str.substring( str.lastIndexOf( "." ) ) : str;
	}
	
    /**
     * ���ڿ��� �Ұ� �˻縦 �Ѵ�.
     * <BR>���ڿ��� null �Ǵ� white space�� ��쿡�� "��"�� ��ȯ�Ѵ�.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNull( String str ) {
        return ( str == null || "null".equals( str ) || "".equals( str ) ); 
    }
    
    /**
     * ���ڿ��� �Ұ� �˻縦 �Ѵ�.
     * <BR>���ڿ��� null �Ǵ� white space�� ��쿡�� "����"�� ��ȯ�Ѵ�.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNotNull( String str ) {
        return !( str == null || "".equals( str ) );
    }
    
    /**
     * ���ڿ��� null�� ��쿡�� whiteSpace�� ��ȯ�Ѵ�.
     * 
     * @param string
     * @return
     */
    public static String nullToEmptyString( String string ) {
    	return isNull( string ) ? "" : string;
    }
    
    /**
     * ��û�� �������� URL�� ��ȯ�Ѵ�.
     * 
     * @param req
     * @return
     */
    public static String getURL( HttpServletRequest req ) {
        return req.getRequestURL().toString();
    }
    
    /**
     * ����� ������ max���� Ŭ��� max ũ�⸸ŭ�� �߶� ��ȯ�Ѵ�.
     * 
     * @param s - String
     * @param max - int
     * @return
     */
    public synchronized static String formatTitle( String s, int max ) 
    {
        if( s.length() <= max) return s;

        String tmp = null;        
        byte bTmp[] = null;
        String rets = "" ;

        for(int i=0, k=0; i < s.length(); i++) {
            tmp = s.substring( i, i+1 );
            bTmp = tmp.getBytes();
            if( bTmp.length > 1 ) {
                rets += tmp;
                k +=2;
            }  else {
                rets += tmp;
                k ++;
            }

            if( max <= k ) break;
        }
        
        return rets+"...";
    }
    
    
    /**
     * ���ڿ��� ��ȯ�Ѵ�.
     * 
     * @param str - String
     * @param pattern - String
     * @param replace - String
     * @return
     */ 
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ( (e = str.indexOf( pattern, s) ) >= 0 ) {
            result.append( str.substring( s, e ) );
            result.append( replace );
            s = e + pattern.length();
        }
        
        result.append( str.substring( s ) );        
        return result.toString();
    }    

    /**
     * ���ڿ� �ش��ڵ�� ��ȯ�Ѵ�..
     * 
     * @param str - String
     * @param encode - String
     * @param charsetName - String
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String encodeText( String str, String encode, String charsetName )  {
    	String result = null;
    	
    	try {
			result = isNull( str ) ? null : new String( str.getBytes( encode ), charsetName );
    	} catch ( UnsupportedEncodingException e ) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    
    /**
     * ���ڿ��� euc-kr���� 8859_1�� ���ڵ� �Ѵ�.
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode( String str ) 
    {
        return encodeText( str, "euc-kr", "8859_1" ); 
    }

	/**
	 * Date ��ü�� ��¥�� pattern�� ���·� ��ȯ�Ѵ�.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
    public static String getPatternDate( Date date, String pattern ) {
    	return  new SimpleDateFormat( pattern ).format( date );
    }
    
    /**
     * ���ڿ��� 8859_1���� euc-kr�� ���ڵ� �Ѵ�.
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */        
    public static String encode( String str ) 
    {
        return encodeText( str, "8859_1", "euc-kr" );
    }   
 
}

