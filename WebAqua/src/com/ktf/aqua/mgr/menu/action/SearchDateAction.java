package com.ktf.aqua.mgr.menu.action;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.ktf.aqua.mgr.menu.*;

public class SearchDateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		String menuDev 		= request.getParameter("menu_dev");
		String dateType  	= "";	
		String dateTypeYN	= "";
		
		if(menuDev.equals("undefined")){
			dateType 	= "N";
			dateTypeYN 	= "N";
		}else{
			MenuManager manager = MenuManager.instance();		
			HashMap<String,String> etcHash = (HashMap<String,String>)manager.getSearchOption("4", "", menuDev, "");
		
			dateType 	= (String)etcHash.get("date_type");			//날짜조건
			dateTypeYN	= (String)etcHash.get("date_type_yn");		//검색조건
		}
		
		request.setAttribute("dateType", dateType);
		request.setAttribute("dayTypeShow", dateTypeYN);
				
		/*
		String paramDateType = request.getParameter("dateType");
		
		if( paramDateType == null ){
			if(dateType.equals("1")){
				paramDateType = "1";
			}else if(dateType.equals("2") || dateType.equals("3")){
				paramDateType = "2";
			}else if(dateType.equals("4")){
				paramDateType = "4";
			}
		}else{
			if(paramDateType.equals("1") || paramDateType.equals("5")){
				dateType = "1";
			}else if(paramDateType.equals("2")){
				dateType = "3";
			}else if(paramDateType.equals("4")){
				dateType = "4";
			}
		}
		*/
		
		/*
		 * dateType 5:5분별
		 * 		    1:시간별
		 * 		    2:일별
		 * 		    3:주별
		 * 		    4:월별 
		 
		ParamForm pForm = new ParamForm();		 
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		final SimpleDateFormat sdf2 = new SimpleDateFormat("HH"); 
		
		Calendar cal = Calendar.getInstance();
		
		if(paramDateType.equals("5") || dateType.equals("1")){
			pForm.setTo_date(sdf.format(cal.getTime()));
			pForm.setFrom_date(sdf.format(cal.getTime()));
			pForm.setTo_time("00");
			cal.add(Calendar.HOUR,-1);
			pForm.setFrom_time(sdf2.format(cal.getTime()));
			
			List<SelectForm> hourList = new ArrayList<SelectForm>();
			for(int i=0;i<24;i++){
				String temp = "";
				if( i<10 ){ temp = "0" + i; }
				else{ temp = i + ""; }
				
				hourList.add(new SelectForm(temp , temp));
			}
			request.setAttribute("hourList", hourList);
		}else if(paramDateType.equals("2")){
			cal.add(Calendar.DATE,-1);
			pForm.setFrom_date(sdf.format(cal.getTime()));				
			cal.add(Calendar.DATE,-7);
			pForm.setTo_date(sdf.format(cal.getTime()));
		}else if(paramDateType.equals("3")){
				int weekDay = cal.get(Calendar.DAY_OF_WEEK);
				if(weekDay != 1 ){
					weekDay -= 1; 			
				}
				cal.add(Calendar.DATE, -(weekDay));
				pForm.setFrom_date(sdf.format(cal.getTime()));
				cal.add(Calendar.DATE, -6);
				pForm.setTo_date(sdf.format(cal.getTime()));
		}else if(paramDateType.equals("4")){
			cal.add(Calendar.MONTH, -1);
			pForm.setTo_date(sdf1.format(cal.getTime()));
			pForm.setFrom_date(sdf1.format(cal.getTime()));
		pForm.setDayType(paramDateType);
		pForm.setDayTypeForm(dateType);
		
		request.setAttribute("pForm", pForm);
		}
		*/
		
		return mapping.findForward("success");
	}
}
