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
			request.setAttribute("errMsg", "�α��� �� �̿밡���մϴ�.");
			return mapping.findForward("sessionError");
		}
		*/
		
		/* session logic
		HttpSession s = request.getSession();
		String AuthType = (String)s.getAttribute("userLevel");		
		*/
		
		//���� �������³��� cookie logic => session logic		
		CommonCheck common = new CommonCheck();
		String[] resultStr = common.sessionCheck(request); 
		
		String AuthType = resultStr[0];

		// �˻�Ÿ�� : ajax�� �ʿ��� ��츸 �����Ҽ� �ֵ��� ������ �д�.
		// 		- 1 : ��ü
		// 		- 2 : �󼼺з�����
		//		- 3 : �����ͱ��� ����.
		int searchType = Integer.parseInt((String)request.getParameter("type"));

		//plf_dev ���� �����´�.
		pForm.setPlf_dev((String)((HashMap<String,String>)manager.getSearchOption("2", pForm.getMenuType(), pForm.getMenu1(), "")).get("plf_code"));

//����
System.out.println("SearchOptionAction : searchType = " + searchType);
System.out.println("SearchOptionAction : pForm.getMenuType() = " + pForm.getMenuType());
System.out.println("SearchOptionAction : pForm.getMenu1() = " + pForm.getMenu1());

		//1. 2���޴��� �����´�.
		List<SelectForm> sMenu = null;
		if(searchType < 2){			
			sMenu = (List<SelectForm>)manager.getSearchType("3", pForm.getMenuType(), pForm.getMenu1(), "", "", AuthType);
			request.setAttribute("subMenuList", sMenu);
		}		
		
		try{
			
			List<SelectForm> detailList = null;
			if(searchType < 3){
				//2. 2���޴���(�Ķ����)�� ���� ��� 1.���� ���ѳ����� ù��° id�� �����´�.
				if(pForm.getMenu2() == null){
					pForm.setMenu2(((SelectForm)sMenu.get(0)).getId());
				} 

				//3. �󼼺з��� �� ��Ÿ �������� �����´�.
				String detailType = (String)((HashMap<String,String>)manager.getSearchOption("3", pForm.getMenuType(), pForm.getMenu2(), "")).get("detail_type");

//����				
System.out.println("SearchOptionAction : detailType = " + detailType);	
				
				detailList = (List<SelectForm>)manager.getSearchType("4", "", detailType, "", "", AuthType);
				request.setAttribute("detailMenuList", detailList );	
			}
			
			if(searchType < 4){		
				//4. �����ͱ��а��� �����´� (N�� �����ͱ��а��� ���°��)
				//�󼼺з����� ������� �� ����Ʈ ù��° ���� �����´�. 
				if(pForm.getMenu_dev() == null){
					pForm.setMenu_dev(((SelectForm)detailList.get(0)).getId());
				}

//����				
System.out.println("SearchOptionAction : pForm.getMenu_dev() = " + pForm.getMenu_dev());
				
				HashMap<String,String> etcHash = (HashMap<String,String>)manager.getSearchOption("4", "", pForm.getMenu_dev(), "");
	
				String dataDev  	= (String)etcHash.get("data_dev_type");		// �����ͱ���
				String svcYN		= (String)etcHash.get("svc_yn"); 			// ����
				String urlYN		= (String)etcHash.get("url_yn");			// URL�Է�â 
				String detailYN	 	= (String)etcHash.get("detail_yn");			// �󼼰˻�
				String sortType 	= (String)etcHash.get("sort_type");			// �˻�����
				String sCountYN 	= (String)etcHash.get("scount_yn");			// 
				//String dateType 	= (String)etcHash.get("date_type");			// ��¥����
				//String dateTypeYN	= (String)etcHash.get("date_type_yn");		// �˻�����
				
				//�����ͱ��а�
				if(dataDev.equals("N") == false){
					request.setAttribute("dataDevList", manager.getSearchType("5", "", dataDev, "", "", ""));
				}
				
				//���񽺰��� �����´�.
				if(svcYN.equals("N") == false){
					request.setAttribute("svcList1", manager.getSearchType("6", "", pForm.getMenu2(), "", "", ""));
					request.setAttribute("svcList2", manager.getSearchType("7", "", pForm.getMenu2(), "", "", ""));
				}
	
				//�󼼰˻� 
				if(detailYN.equals("N") == false){
					request.setAttribute("detailSearch", manager.getSearchType("8", "", detailYN, "", "", ""));
				}
				
				//�˻�����
				if(sortType.equals("N") == false){
					request.setAttribute("sortOption", manager.getSearchType("9", "", sortType, "" , "", ""));
				}
				
				//url�˻�â 
				if(urlYN.equals("Y") == true){
					request.setAttribute("urlShow", "show");
				}
				
				//�˻��Ǽ�
				if( sCountYN.equals("N") ){
					request.setAttribute("searchCount", "dontshow");
				}
				
				//�����󼼷α�, TCS/Ư������ڹ�ȣ �ÿ� �÷��� �ڵ带 �����ش�.
				if(pForm.getPlf_dev().equals("2") || pForm.getPlf_dev().equals("3")){
					// �˻�ȭ�鼱���� �˻�Ÿ���� 1�ϰ�쿡�� ���ش�.
					if(searchType == 1){
						//TCS/Ư������ڹ�ȣ�ÿ� �˻�ȭ�鼱���� �����ش�.
						if(pForm.getPlf_dev().equals("3")){
							List<SelectForm> minTypeList = new ArrayList<SelectForm>();
							minTypeList.add(new SelectForm("1", "Ư������ڹ�ȣ"));
							minTypeList.add(new SelectForm("2", "TCS��ȣ�˻�"));
							request.setAttribute("minTypeList", minTypeList);		
						}
					}
					
					List<SelectForm> plfList = (List<SelectForm>)manager.getSearchType("10", "", "", pForm.getMenu_dev(), "", "");
					request.setAttribute("plfList", plfList);
					
					if(pForm.getPlf_name() == null){
						pForm.setPlf_name((String)plfList.get(0).getId());
					}
					
					//�����󼼷α׿� TCS/Ư������ڹ�ȣ�� ���񽺸� ���� ���Ѵ�.
					request.setAttribute("svcList1", manager.getSearchType("6", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
					request.setAttribute("svcList2", manager.getSearchType("7", "", pForm.getMenu2(),pForm.getMenu_dev(),pForm.getPlf_name(), ""));
					
					//����� MIN��ȣ �Է�â�� �����ش�. 
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
