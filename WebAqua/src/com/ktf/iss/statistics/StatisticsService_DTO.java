/*
 * Created on 2003. 10. 15.
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ktf.iss.statistics;

import java.util.*;
import com.ktf.iss.etc.util.Calculator;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatisticsService_DTO {

	private HashMap values;
	private HashMap numberValues;
	private ArrayList columnNames;
	
	public StatisticsService_DTO() {
		values = new HashMap();
		numberValues = new HashMap();
		columnNames = new ArrayList();
	}
	
	public void setValue(String name, String value) {
		if(!values.containsKey(name)) {
			columnNames.add(name);
		}
		values.put(name, value);
	}
	
	public void setValue(String name, double value) {
		if(!values.containsKey(name)) {
			columnNames.add(name);
		}
		numberValues.put(name, new Double(value));
	}

	public String getValue(String name) {
		String result = (String)values.get(name);
		if(result == null)
			return "";
		else
			return (String)values.get(name);
	}
	
	public Double getNumberValue(String name) {
		Double num = (Double)numberValues.get(name);
		if(num != null)
			return num;
		else
			return new Double(0);
	}

	public int getColumnCount() {
		return values.size();
	}
	
	public  Iterator getColumnNames() {
		return columnNames.iterator();
	}

	public  Object[] getColumnNameArray() {
		return columnNames.toArray();
	}
	
	public String getUserDefinedValue(String expr, String format) {
		StatisticsService_BO bo = StatisticsService_BO.getInstance();
		expr = bo.parseExpression(this, expr);
		String result = (new Calculator(expr)).evaluate();
		if(format != null && format.length() > 0) {
			java.text.DecimalFormat df = new java.text.DecimalFormat(format);
			double doubleValue = Double.parseDouble(result);
			result = df.format(doubleValue);
		}
		
		return result;
	}
}
