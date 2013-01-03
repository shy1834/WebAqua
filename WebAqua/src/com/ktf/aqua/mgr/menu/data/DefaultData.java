package com.ktf.aqua.mgr.menu.data;

import java.util.*;

import com.ktf.aqua.mgr.menu.form.*;

public class DefaultData {
	
	final private static List<SelectForm> timeHour;
	final private static List<SelectForm> timeMin;
	
	static{
		
		timeHour = new ArrayList<SelectForm>();
		for(int i=0;i<24;i++){
			String temp = "";
			if(i<10){ 
				temp = "0" + i; 
			}else{ 
				temp = i + ""; 
			}
			timeHour.add(new SelectForm(temp , temp));
		}
		
		timeMin = new ArrayList<SelectForm>();
		for(int i=0;i<60;i+=5){
			String temp = "";
			if(i<10){ 
				temp = "0" + i; 
			}else{ 
				temp = i + ""; 
			}
			timeMin.add(new SelectForm(temp , temp));
		}
	}

	public static List<SelectForm> getTimehour() {
		return timeHour;
	}
	
	public static List<SelectForm> getTimemin() {
		return timeMin;
	}
}
