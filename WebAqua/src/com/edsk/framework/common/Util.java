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
 * 모든 게시판에서 자주 사용될 만한 기능을 모아둔 유틸클래스
 * 
 * @author june - 박창준
 * @이메일 june1024@nate.com
 * @파일명 Util.java
 */
public class Util {
	private static Logger logger;
	
	/**
	 * log4초기화 (보류)
	 */
	public static synchronized void log4jInstance( Class classs) {
		if ( logger == null ) {
			logger = Logger.getLogger( classs.getName() );
		}
	}
	
	/**
	 * log4 반환 (보류)
	 * @return
	 */
	public static Logger getLog4j() {
		return logger;
	}
	
	/**
	 * log4j를 이용하여 log를 남긴다. (보류)
	 * @param str
	 */
	public static void log4j( String str ) {
		logger.info( str );
	}
	
	/**
	 * 파일의 확장자를 추출한다.
	 * <BR>
	 * 예) <BR>
	 * [입력] "<B>abcder.txt</B>" ----> [출력] "<B>.txt</B>" 
	 * 
	 * @param str
	 * @return
	 */
	public static String getFileExtension( String str ) {
		return ( str.lastIndexOf( "." ) > 0 ) ? str.substring( str.lastIndexOf( "." ) ) : str;
	}
	
    /**
     * 문자열의 넙값 검사를 한다.
     * <BR>문자열이 null 또는 white space인 경우에는 "참"을 반환한다.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNull( String str ) {
        return ( str == null || "null".equals( str ) || "".equals( str ) ); 
    }
    
    /**
     * 문자열의 넙값 검사를 한다.
     * <BR>문자열이 null 또는 white space인 경우에는 "거짓"을 반환한다.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNotNull( String str ) {
        return !( str == null || "".equals( str ) );
    }
    
    /**
     * 문자열이 null인 경우에는 whiteSpace를 반환한다.
     * 
     * @param string
     * @return
     */
    public static String nullToEmptyString( String string ) {
    	return isNull( string ) ? "" : string;
    }
    
    /**
     * 요청한 페이지의 URL을 반환한다.
     * 
     * @param req
     * @return
     */
    public static String getURL( HttpServletRequest req ) {
        return req.getRequestURL().toString();
    }
    
    /**
     * 목록의 제목이 max보다 클경우 max 크기만큼만 잘라서 반환한다.
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
     * 문자열을 변환한다.
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
     * 문자열 해당코드로 변환한다..
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
     * 문자열을 euc-kr에서 8859_1로 디코딩 한다.
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
	 * Date 객체의 날짜를 pattern의 형태로 반환한다.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
    public static String getPatternDate( Date date, String pattern ) {
    	return  new SimpleDateFormat( pattern ).format( date );
    }
    
    /**
     * 문자열을 8859_1에서 euc-kr로 인코딩 한다.
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */        
    public static String encode( String str ) 
    {
        return encodeText( str, "8859_1", "euc-kr" );
    }   
 
}

