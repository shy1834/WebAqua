/*
 * Created on 2003-09-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.lang.reflect.*;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetNumberValue extends TagSupport{
	
	
	private String name;			// 데이터를 가지고 있는 객체명
	private String property;		// 테이터 칼럼명
	private String columnName;		// 칼럼 네임을 가지고 있는 빈의 객체 변수명
	private String columnProperty;	// 칼럼 네임을 가지고 있는 빈의 속성명
	
	public GetNumberValue(){
	}
	
	public void setName(String name){
		this.name = name;		
	}//end of setName();
	
	public void setProperty(String property){
		this.property = property;
	}//end of setId
	
	public String getName(){
		return name;
	}
	
	public String getProperty(){
		return property;
	}
	
	public Double getResult(){

		Double result = null;
		Object dto = null;
		String fieldName = "";
		
		try {
			if(property != null)
				fieldName = property;
			else if(columnName != null && columnProperty != null){
				Object colObject = pageContext.findAttribute(columnName);
				String methodName = "get" + columnProperty.substring(0, 1).toUpperCase()
								 + columnProperty.substring(1);
				Method method = colObject.getClass().getMethod(methodName, new Class[0]);
				fieldName = (String)method.invoke(colObject, new Object[0]);				
			}

			dto = pageContext.findAttribute(name);
			if(dto != null) {
				Class[] paramType = {String.class};
				Method method = dto.getClass().getMethod("getNumberValue", paramType);
				Object[] param = {fieldName};
				result = (Double)method.invoke(dto, param);
			}
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return result;
	}//end fo getResult
	
	public int doStartTag() throws JspException{
		try{
			pageContext.getOut().print(getResult());
		}catch(Exception e){
			e.printStackTrace();	
		}
		return SKIP_BODY;
	}//end of doStartTag
	

	public int doEndTag() throws JspException{
			return SKIP_BODY;
	}//end of doEndTag;
	
	/**
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @return
	 */
	public String getColumnProperty() {
		return columnProperty;
	}

	/**
	 * @param string
	 */
	public void setColumnName(String string) {
		columnName = string;
	}

	/**
	 * @param string
	 */
	public void setColumnProperty(String string) {
		columnProperty = string;
	}

}//end of class