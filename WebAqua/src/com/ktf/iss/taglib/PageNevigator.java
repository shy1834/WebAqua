/******************************************************************************
 * ClassName    : PageNevigator
 * Author       : 안대훈 (ssanpy@koreadotcom.com)
 * Create Date  : 2003/10/06
 ==============================================================================
 *      Explanation
 ------------------------------------------------------------------------------
 *  페이지 네비게이터 태그라이브러리
 ==============================================================================
 *      Revision History
 ------------------------------------------------------------------------------
 * Date         | Name              | Comment
 ------------------------------------------------------------------------------
 * 
 ******************************************************************************/
package com.ktf.iss.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class PageNevigator extends TagSupport{
	
	private String 	total_rec_cnt; 		//총 데이터 건수
	private String 	MAX_LINE;   		//한페이지당 데이터수
	private String 	cur_pageno; 		//현재 페이지 넘버
	private String 	func_nm;  			//자바스크립트 function 명
	private String  name;       		//attribute 명

	/**
	 * @return
	 */
	public String getCur_pageno() {
		return cur_pageno;
	}

	/**
	 * @return
	 */
	public String getFunc_nm() {
		return func_nm;
	}

	/**
	 * @return
	 */
	public String getMAX_LINE() {
		return MAX_LINE;
	}

	/**
	 * @return
	 */
	public String getTotal_rec_cnt() {
		return total_rec_cnt;
	}

	/**
	 * @param i
	 */
	public void setCur_pageno(String i) {
		cur_pageno = i;
	}

	/**
	 * @param string
	 */
	public void setFunc_nm(String string) {
		func_nm = string;
	}

	/**
	 * @param i
	 */
	public void setMAX_LINE(String i) {
		MAX_LINE = i;
	}

	/**
	 * @param i
	 */
	public void setTotal_rec_cnt(String i) {
		total_rec_cnt = i;
	}
	
	public int doStartTag() throws JspException{
		
		try{
			pageContext.getOut().print(getNavigator(name));
		}catch(Exception e){
			System.out.println("IssTag.doStartTag :"+e);	
		}
		return SKIP_BODY;	
	}
	
	public String getNavigator(String name){
		
		//HashMap map = (HashMap)pageContext.findAttribute(name);			
		int   total_rec_cnt_int 	= Integer.parseInt((String)pageContext.findAttribute(total_rec_cnt));
		int   MAX_LINE_int			= Integer.parseInt((String)pageContext.findAttribute(MAX_LINE));
		int   cur_pageno_int    	= Integer.parseInt((String)pageContext.findAttribute(cur_pageno));
		String func_nm_str   	 	= func_nm;
		
		/*
		int   total_rec_cnt_int = Integer.parseInt(total_rec_cnt);
		int   MAX_LINE_int		= Integer.parseInt(MAX_LINE);
		int   cur_pageno_int	= Integer.parseInt(cur_pageno);
		String func_nm_str		= func_nm;
		*/
		
		int	  page_cnt				= total_rec_cnt_int / MAX_LINE_int;
		
		if (total_rec_cnt_int % MAX_LINE_int > 0){
			page_cnt++;
		}
		
		String result 			= "";
		if(page_cnt >1){	
			int total_page_cnt 		= 1;
	
			if(total_rec_cnt_int % MAX_LINE_int > 0){
				total_page_cnt = ( total_rec_cnt_int / MAX_LINE_int ) + 1;
			}else{
				total_page_cnt = ( total_rec_cnt_int / MAX_LINE_int );
			}
				
			if(cur_pageno_int != 1){
				result = result + "<a href='javascript:" + func_nm_str + "( 1)' style='border:0' onfocus='this.blur()'><img src='/img/pre_2.gif' border='0' align=absmiddle></a>";
			}else{
				//result = result + "<img src='/img/pre_2.gif' border='0' align=absmiddle>";
				result = result + " ";
			}
			
			int k = ((cur_pageno_int-1)/10)*10;
			if(cur_pageno_int <= 10){
				//result = result + "<img src='/img/pre.gif' hspace='5' border=0 align=absmiddle>";
				result = result + " ";
			}else{
				result = result + "<a href='javascript:" + func_nm_str + "( " + k + ")'  style='border:0' onfocus='this.blur()'><img src='/img/pre.gif' hspace='5' border=0 align=absmiddle></a> "; 
			}
		
			int m = ((cur_pageno_int - 1) / 10) * 10 + 1;
			for(int i = m; i < m + 10 && i <= total_page_cnt; i++){
				
				if(i == cur_pageno_int){
					result = result + "<a href='#' styleClass='bbs_pageon' ><strong>" + i + "</strong></a>";
				}else{
					result = result + "<a href='javascript:" + func_nm_str + "( " + i + " )' >" + i + "</a>";
				}
				
				if((i < (m + 10 - 1)) && i < total_page_cnt){
					result = result + " " ;
				}
			}
			
			if(total_page_cnt / 10 > ( cur_pageno_int - 1 ) / 10 && total_page_cnt > k+10){
				int k10 = k + 1 + 10;
				result = result + "<a href = 'javascript:" + func_nm_str + "( " + k10+ " )' style='border:0' onfocus='this.blur()'><img src='/img/next.gif' hspace='5' border='0' align=absmiddle ></a>";
			}else{
				//result = result + "<img src='/img/next.gif' hspace='5' border='0' align=absmiddle >";
				result = result + " ";
			}
			
			if(cur_pageno_int != total_page_cnt && total_page_cnt != 0){
				result = result + "<a href='javascript:" + func_nm_str + "( " + total_page_cnt + ")' style='border:0' onfocus='this.blur()'><img src='/img/next_2.gif' border='0' align=absmiddle></a>";
			}else{
				//result = result + "<img src='/img/next_2.gif' border='0' align=absmiddle>";
				result = result + " ";
			}
		}//if
		return result;
	}
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}
