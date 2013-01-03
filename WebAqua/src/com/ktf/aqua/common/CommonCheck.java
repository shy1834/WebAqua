package com.ktf.aqua.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CommonCheck {
	
	//sessionCheck => cookieCheck
	public String[] sessionCheck(HttpServletRequest request) throws UnsupportedEncodingException{
	  
	  String[] resultStr = new String[6];
	  Cookie[] cookies = request.getCookies();
	  
	  for (int i = 0; i < cookies.length; i++) {
		   Cookie thisCookie = cookies[i];
		   if (thisCookie.getName().equals("userLevel")) {
			   resultStr[0] = thisCookie.getValue();
		   }
		   if (thisCookie.getName().equals("userType")) {
			   resultStr[1] = thisCookie.getValue();
		   }
		   if (thisCookie.getName().equals("userName")) {
			   resultStr[2] = URLDecoder.decode(thisCookie.getValue(), "euc-kr");
		   }
		   if (thisCookie.getName().equals("userId")) {
			   resultStr[3] = thisCookie.getValue();
		   }
		   if (thisCookie.getName().equals("cpId")) {
			   resultStr[4] = thisCookie.getValue();
		   }
	  }
	  
	  if( resultStr[0] == null){
		  resultStr[5] = "false";
	  }else{
		  resultStr[5] = "true";
	  }
			
	  return resultStr;
	}
}
