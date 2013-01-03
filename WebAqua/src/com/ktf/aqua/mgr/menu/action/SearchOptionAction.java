package com.ktf.aqua.mgr.menu.action;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.ktf.aqua.common.CommonCheck;
import com.ktf.aqua.mgr.menu.*;
import com.ktf.aqua.mgr.menu.form.*;

public class SearchOptionAction extends Action {

	public ActionForward execute( ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {

		MenuManager manager = MenuManager.instance();		
		ParamForm pForm = (ParamForm)form;
		
		/* session check
		CommonCheck common = new CommonCheck();
		if(common.sessionCheck(request) == false){
			request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
			return mapping.findForward("sessionError");
		}
		*/
		
		/* session logic
		HttpSession s = request.getSession();
		String AuthType = (String)s.getAttribute("userLevel");		
		*/
		
		//권한 가져오는내용 cookie logic => session logic		
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request); 
		
		String AuthType = resultStr[0];

		// 검색타입 : ajax시 필요한 경우만 실행할수 있도록 구분을 둔다.
		// 		- 1 : 전체
		// 		- 2 : 상세분류이하
		//		- 3 : 데이터구분 이하.
		int searchType = Integer.parseInt((String)request.getParameter("type"));

		//plf_dev 값을 가져온다.
		pForm.setPlf_dev((String)((HashMap<String,String>)manager.getSearchOption("2", pForm.getMenuType(), pForm.getMenu1(), "")).get("plf_code"));

//삭제
System.out.println("SearchOptionAction : searchType = " + searchType);
System.out.println("SearchOptionAction : pForm.getMenuType() = " + pForm.getMenuType());
System.out.println("SearchOptionAction : pForm.getMenu1() = " + pForm.getMenu1());

		//1. 2차메뉴를 가져온다.
		List<SelectForm> sMenu = null;
		if(searchType < 2){			
			sMenu = (List<SelectForm>)manager.getSearchType("3", pForm.getMenuType(), pForm.getMenu1(), "", "", AuthType);
			request.setAttribute("subMenuList", sMenu);
		}		
		
		try{
			
			List<SelectForm> detailList = null;
			if(searchType < 3){
				//2. 2차메뉴값(파라미터)이 없을 경우 1.에서 구한내용중 첫번째 id를 가져온다.
				if(pForm.getMenu2() == null){
					pForm.setMenu2(((SelectForm)sMenu.get(0)).getId());
				} 

				//3. 상세분류값 및 기타 설정값을 가져온다.
				String detailType = (String)((HashMap<String,String>)manager.getSearchOption("3", pForm.getMenuType(), pForm.getMenu2(), "")).get("detail_type");

//삭제				
System.out.println("SearchOptionAction : detailType = " + detailType);	
				
				detailList = (List<SelectForm>)manager.getSearchType("4", "", detailType, "", "", AuthType);
				request.setAttribute("detailMenuList", detailList );	
			}
			
			if(searchType < 4){		
				//4. 데이터구분값을 가져온다 (N은 데이터구분값이 없는경우)
				//상세분류값이 없을경우 위 리스트 첫번째 값을 가져온다. 
				if(pForm.getMenu_dev() == null){
					pForm.setMenu_dev(((SelectForm)detailList.get(0)).getId());
				}

//삭제				
System.out.println("SearchOptionAction : pForm.getMenu_dev() = " + pForm.getMenu_dev());
				
				HashMap<String,String> etcHash = (HashMap<String,String>)manager.getSearchOption("4", "", pForm.getMenu_dev(), "");
	
				String dataDev  	= (String)etcHash.get("data_dev_type");		// 데이터구분
				String svcYN		= (String)etcHash.get("svc_yn"); 			// 서비스
				String urlYN		= (String)etcHash.get("url_yn");			// URL입력창 
				String detailYN	 	= (String)etcHash.get("detail_yn");			// 상세검색
				String sortType 	= (String)etcHash.get("sort_type");			// 검색조건
				String sCountYN 	= (String)etcHash.get("scount_yn");			// 
				//String dateType 	= (String)etcHash.get("date_type");			// 날짜조건
				//String dateTypeYN	= (String)etcHash.get("date_type_yn");		// 검색조건
				
				//데이터구분값
				if(dataDev.equals("N") == false){
					request.setAttribute("dataDevList", manager.getSearchType("5", "", dataDev, "", "", ""));
				}
				
				//서비스값을 가져온다.
				if(svcYN.equals("N") == false){
					request.setAttribute("svcList1", manager.getSearchType("6", "", pForm.getMenu2(), "", "", ""));
					request.setAttribute("svcList2", manager.getSearchType("7", "", pForm.getMenu2(), "", "", ""));
				}
	
				//상세검색 
				if(detailYN.equals("N") == false){
					request.setAttribute("detailSearch", manager.getSearchType("8", "", detailYN, "", "", ""));
				}
				
				//검색조건
				if(sortType.equals("N") == false){
					request.setAttribute("sortOption", manager.getSearchType("9", "", sortType, "" , "", ""));
				}
				
				//url검색창 
				if(urlYN.equals("Y") == true){
					request.setAttribute("urlShow", "show");
				}
				
				//검색건수
				if( sCountYN.equals("N") ){
					request.setAttribute("searchCount", "dontshow");
				}
				
				//오류상세로그, TCS/특정사용자번호 시에 플랫폼 코드를 보여준다.
				if(pForm.getPlf_dev().equals("2") || pForm.getPlf_dev().equals("3")){
					// 검색화면선택은 검색타입이 1일경우에만 해준다.
					if(searchType == 1){
						//TCS/특정사용자번호시에 검색화면선택을 보여준다.
						if(pForm.getPlf_dev().equals("3")){
							List<SelectForm> minTypeList = new ArrayList<SelectForm>();
							minTypeList.add(new SelectForm("1", "특정사용자번호"));
							minTypeList.add(new SelectForm("2", "TCS번호검색"));
							request.setAttribute("minTypeList", minTypeList);		
						}
					}
					
					List<SelectForm> plfList = (List<SelectForm>)manager.getSearchType("10", "", "", pForm.getMenu_dev(), "", "");
					request.setAttribute("plfList", plfList);
					
					if(pForm.getPlf_name() == null){
						pForm.setPlf_name((String)plfList.get(0).getId());
					}
					
					//오류상세로그와 TCS/특정사용자번호는 서비스를 따로 구한다.
					request.setAttribute("svcList1", manager.getSearchType("6", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
					request.setAttribute("svcList2", manager.getSearchType("7", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
					
					//사용자 MIN번호 입력창을 보여준다. 
					request.setAttribute("minShow", "show");
				}
			}
			
			if(searchType > 3){		
				request.setAttribute("svcList1", manager.getSearchType("6", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
				request.setAttribute("svcList2", manager.getSearchType("7", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
			}
			
		}catch(Exception e){}			

		request.setAttribute("searchType", searchType);
		request.setAttribute("pForm", pForm);
		return mapping.findForward("success");
	}
}
