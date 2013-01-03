var firstFlag = true;
var sDateType = "";

function init(){	
	
	sel_menu1();	
	getContent(); 

	/*
	// 스마트서비스 예외처리
	if($("#menuType").val() == "1" ){		
		// 올레캐치캐치,올레톡일 경우 해당 mvf파일을 보여준다.
		if("11500.11600".indexOf($("#menu1").val()) > -1 ){
			// 시간값 셋팅
			dateTypeChange(2);
			dateInputInit(2);
			
			$("#searchBtn").click(function() {
				$("#file_name").val("tstat/smart_tcp_co.mvf");
				OnOpen();			
			});
		}
	}else{
		$("#searchBtn").click(function() {
			getContent(true);
		});
	}
	*/
	
	$("#searchBtn").click(function() {
		getContent(true);
	});
	
	// smart메뉴 1차메뉴처리
	/*
	if($("#menuType").val() == "1" ){
		$("#menu1").mousedown(function(e){			
			sMenu1SelectClick();
		});
		
		menu1SelInit();
	}
	*/
}

//1차메뉴
function sel_menu1(){

	$.ajax({
		type:"POST",
		url: "searchOpt.do",
		data: "menu1="+ $("#menu1").val()+"&type=1"+
			  "&menuType="+$("#menuType").val() +
			  "&type=1",			  
		success:function(html){
			$("#content").html(html);
						
			//스마트서비스 예외처리.			
			if($("#menuType").val() == "1"){
				//올레날씨, 네비, 마켓일 경우에만 2차메뉴 레이아웃 처리를 해준다.
				if("11100.11300.11400".indexOf($("#menu1").val()) > -1){
					$("#searchType").show();
					$("#smenu2").show();
					$("#detailoption").show();
					$("#menu2").mousedown(function(e){			
						sMenuSelectClick();
					});					
					menu2SelInit();
				}else if("11500.11600".indexOf($("#menu1").val()) > -1){
					//올레캐치캐치,올레톡일 경우 해당 mvf파일을 보여준다.	
					//시간값 셋팅
					$("#smenu2").hide();
					$("#detailoption").hide();
				}   
			}

			//일일상황일지일때만 보여준다.
			if($("#menu1 option:selected").text() == "일일상황일지"){
				$("#daily_report").show();
				$("#smenu2").hide();
			}else{
				$("#daily_report").hide();
			}
			
			//2차메뉴 SELECT박스 처리			
			if($("#menuType").val() == "2"){
				$("#menu2").mousedown(function(e){			
					sMenuSelectClick();
				});
				
				menu2SelInit();
			}
			
			//Quality 
			//2차메뉴 SELECT박스 처리			
			if($("#menuType").val() == "3"){				
				if($("#menu1").val() == "5" || $("#menu1").val() == "4"){
					$("#smenu2").hide();					
					$("#detailoption").hide();
				}else{					
					$("#smenu2").show();					
					$("#detailoption").show();
				}
			}
			getCalendar("");
		}
	});
}

// 2차메뉴
function sel_menu2(){ 
	$.ajax({
		type:"POST",
		url: "searchOpt.do",
		data:"menu1="+ $("#menu1").val()+
			 "&menu2="+ $("#menu2").val()+
			 "&menu_name="+ $("#menu2 option:selected").text() +
			 "&menuType="+$("#menuType").val() +
			 "&type=2",
		success:function(html){		
			$("#content2").html(html);	
			getCalendar("");
		}
	});
}

// 상세분류
function sel_menu3(){
	$.ajax({
		type:"POST",
		url: "searchOpt.do",
		data:"menu1="+ $("#menu1").val()+
			 "&menu2="+ $("#menu2").val() + 
			 "&menu_dev=" + $("#menu_dev").val() + 
			 "&menuType="+$("#menuType").val() +
			 "&type=3",
		success:function(html){			
			$("#content3").html(html);
			getCalendar("");			
		}
	});
}	

// 서비스
function sel_menu4(){
	$.ajax({		
		type:"POST",
		url: "searchOpt.do",
		data: "menu1="+ $("#menu1").val()+
		 	  "&menu2="+ $("#menu2").val() + 
			  "&plf_name="+ $("#plf_name").val()+
			  "&menu_dev="+ $("#menu_dev").val()+
			  "&menuType="+$("#menuType").val()+
			  "&type=4",				 
		success:function(html){
			$("#content4").html(html);	
			
			if($("#plf_name").val() == "11000") sel_menu_smart();
		}
	});
}

// 일자
function getCalendar(radio){

	var param = "menu1="+ $("#menu1").val() + 
				"&menu2=" + $("#menu2").val() +
				"&menu_dev=" + $("#menu_dev").val();
	
	if(radio != ""){
		param += "&dateType=" + radio;
	}
	
	$.ajax({
		type:"POST",
		url: "searchDate.do",
		data: param,				  
		success:function(html){
			$("#content5").html(html);	
		}
	});
}

function sel_menu_smart(){
	// smart서비스일경우에만 동작한다.
	//alert($("#plf_name").val());
	if($("#plf_name").val() == "11000" ){
		var param = "l4="+ $("#svc_dev").val();		
		$.ajax({
		type:"POST",
		url: "/include/search_smart.jsp",
		data: param,				  
		success:function(html){
			//alert(html);
		$("#smart_l4").html(html);	
		}
		});
	}
}

// mvf
function getContent(flag){	
	// 품질분석 > 로그 검색은 아래 관리자만  검색가능하도록 체크
	if(flag == true && $("#menuType").val() == "3" && $("#menu1").val() == "2"){		
		if( userId != "sbk1126" && userId != "kymj016" && userId != "mybang74" 
			&&userId != "ahmax"){
			alert("검색권한이 없습니다.");
			return;
		}
	}
	
	// 날짜유효성 체크
	if(checkDate() == false){
		return;
	}
	
	$.ajax({
		type:"POST",
		url: "searchMvf.do",
		data: "menu_dev=" + $("#menu_dev").val() +
				"&menu1="+ $("#menu1").val() + 
				"&menu2=" + $("#menu2").val() +
				"&menuType="+$("#menuType").val(),				  
		success:function(html){
			$("#content6").html(html);
			OnOpen();

			// 최초메뉴 타이틀 설정
			if( firstFlag == false){
				$("#main_title").html($("#main_title1").html());
				$("#sub_title").html($("#sub_title1").html());
				$("#detail_title1").html($("#menu_dev option:selected").text());
				$("#detail_title2").html($("#menu_dev option:selected").text());
				var menu1_title = $("#menu1_title").html();
				var menu_dev	= $("#menu_dev option:selected").text();
				if(menu1_title.indexOf(menu_dev) > -1){
					$("#menu1_title").html("");
					$("#menu1_title1").html("");
				}
			}
			
			firstFlag = false;
		}
	});
}

function modifyMonth(id,num){
	var dateVal   = $("#" + id).val();
	var date 	  = dateVal.split("-");
	var changeDate = new Date(date[0],eval(date[1])-1,1);
	
	changeDate.setMonth(changeDate.getMonth()+eval(num));
	var monthStr = changeDate.getMonth()+1;		
	if( eval(monthStr) < 10) monthStr = "0" + monthStr;
	$("#" + id).val(changeDate.getFullYear()+"-"+ monthStr);	
}

function dayTypeChange(obj){
	getCalendar($(":input:radio[name=dayType]:checked").val());
}

function mvfDateFormat(date,time){	
	if( date == null){
		date = "";
	}

	date = date.replace(/-/g,"");
	var dateType = $("input[name='dayType']:checked").val();
	
	if( sDateType == "10" || (sDateType == "11" && dateType == "5")){
		date += time + $("#from_min").val() + "00";
	}else if( dateType != "4" 
		&& $("#menu1 option:selected").text() != "일일상황일지" 
		&& $("#menu1 option:selected").text() != "서비스별입력" ) {
		if( dateType == "2" || dateType == "3"){
			date += "000000";		
		}else{
			date += (time + "0000");
		}		
	}	
	return date;
}

// 날짜검색 초기화
function dateInit(dayTypeShow,dateType){	
	// 날짜검색타입을 보여줄지를 설정
	if(dayTypeShow == "Y"){
		$("#dateTypeSel").show();		
	}else{
		$("#dateTypeSel").hide();
	}
	
	// 중간필터
	if( dateType == "11") dateType = "7";
	
	dateInputInit(dateType);
	dateTypeChange(dateType);
	
	if( "1.2.3.4.5".indexOf(dateType) > -1 ){
		if(dateType == "5") dateType = "0";
		document.getElementsByName("dayType")[dateType].checked = true;	
	}else{
		document.getElementsByName("dayType")[2].checked = true;
	}
}

function dateInputInit(val){
	/*
	 *  5 5분별 // 5분별 시간별은 같음  
		1 시간별
		3 주별
		4 월별
		// -------------여기까지는 선택
		
		2 일별
		6 일별검색 서비스 둘다 하루전날짜셋팅
		7 일별검색 1개에 하루전꺼
		8 일별검색 1개에 2틀전꺼
		9 5분검색없음 
		10 일별검색 1개에 시간		 
		// -------------여기까지는 초기화
		
		11 URL 검색
	 */

	var date_now  = new Date(); 
	var date_now1 = new Date();
	
	if( val =="5" || val == "10"){			// 5분별  
		$("#from_date").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)) + "-" + dateFormat(date_now.getDate()));
		//$("#to_date").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)) + "-" + dateFormat(date_now.getDate()));		
		$("#from_hour").val("00");
		$("#from_min").val("00");
		$("#to_hour").val(dateFormat(date_now.getHours()-1));
		
 		if( val == "10" || sDateType == "11"){		// 로그용 & URL별
			$("#from_hour").val(dateFormat(date_now.getHours()-1));
		}	
 		
	}else if(val == "1" ){					// 시간별
		date_now1.setDate(date_now.getDate()-2);		
		$("#from_date").val(date_now1.getYear() + "-" + dateFormat(eval(date_now1.getMonth()+1)) + "-" + dateFormat(date_now1.getDate()));
		$("#to_date").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)) + "-" + dateFormat(date_now.getDate()));		
		$("#from_hour").val("00");
		$("#to_hour").val(dateFormat(date_now.getHours()-1));
	}else if(val == "2" || val == "6" || val == "7" || val == "8" || val == "9" ){	
		if( val == "6" || val == "7" || sDateType == "11"){	// 서비스별 or 검색1개에 하루전
			date_now.setDate(date_now.getDate()-1);
			date_now1.setDate(date_now1.getDate()-1);
		}else if( val == "2" || val == "9" ){	// 일별
			date_now.setDate(date_now.getDate()-8);
			date_now1.setDate(date_now1.getDate()-1);		
		}else if(val == "8"){					// 검색1개에 2틀전
			date_now.setDate(date_now.getDate()-2);
			date_now1.setDate(date_now1.getDate()-2);
		}
		
		$("#from_date").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)) + "-" + dateFormat(date_now.getDate()));
		$("#to_date").val(date_now1.getYear() + "-" + dateFormat(eval(date_now1.getMonth()+1)) + "-" + dateFormat(date_now1.getDate()));		
	}else if(val == "3"){		
		if( date_now.getDay() == 0  ){
		    date_now.setDate(date_now.getDate()- 6);    
	   	}else{	
			date_now.setDate(date_now.getDate() -6 - date_now1.getDay());      
			date_now1.setDate(date_now1.getDate()-date_now1.getDay());    
		}
		$("#from_date").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)) + "-" + dateFormat(date_now.getDate()));
		$("#to_date").val(date_now1.getYear() + "-" + dateFormat(eval(date_now1.getMonth()+1)) + "-" + dateFormat(date_now1.getDate()));
	}else if(val == "4"){
		$("#from_month").val(date_now.getYear() + "-" + dateFormat(eval(date_now.getMonth()+1)));
		$("#to_month").val(date_now1.getYear() + "-" + dateFormat(eval(date_now1.getMonth()+1)));
	}
}

function dateFormat(val){
	if(val == -1){ val = "00";}
	else if(val < 10){ val = "0" + val; } 
	return val;
}

// 날짜검색 초기화1
function dateTypeChange(val){
	/*
	 *  5 5분별 // 5분별 시간별은 같음  
		1 시간별
		3 주별
		4 월별
		// -------------여기까지는 선택
		
		2 일별
		6 일별검색 서비스 둘다 하루전날짜셋팅
		7 일별검색 1개에 하루전꺼
		8 일별검색 1개에 2틀전꺼
		9 5분검색없음 
		10 일별검색 1개에 시간		 
		// -------------여기까지는 초기화
		
		11 URL 검색
	 */
	if( val == "5" || val == "10"){
		$("#from_date").show();
		$("#from_hour").show();
		
		if( val == "10" || sDateType == "11"){	// 로그용
			
			$("#to_date").hide();
			$("#to_hour").hide();
			$("#day-").hide();		
			
			if( sDateType == "10" || (sDateType == "11" && val == "5")){	// URL
				$("#from_min").show();
			}else{
				$("#from_min").hide();
			}					
		}else{
			//$("#to_date").show();
			$("#to_date").hide();
			$("#to_hour").show();
			$("#day-").show();
			$("#from_min").hide();
		}
		
		$("#to_month").hide();
		$("#from_month").hide();
	}else if(val == "1"){
		$("#from_date").show();
		$("#from_hour").show();
		$("#to_date").show();
		$("#to_hour").show();
		$("#day-").show();
		$("#from_min").hide();
		$("#from_month").hide();
		$("#to_month").hide();
	}else if(val == "2" || val == "3" || val == "6" || val == "9"){
		$("#to_date").show();
		$("#from_date").show();
		$("#to_hour").hide();
		$("#from_hour").hide();
		$("#to_month").hide();
		$("#from_month").hide();
		$("#day-").show();
		$("#from_min").hide();
		
		// online 예외처리 
		if ( val == "9" || sDateType == "9"){
			$("#minType").hide();
		}else{
			$("#minType").show();
		}		
	}else if(val == "4"){
		$("#to_date").hide();
		$("#from_date").hide();
		$("#to_hour").hide();
		$("#from_hour").hide();
		$("#to_month").show();
		$("#from_month").show();
		$("#day-").show();		
		$("#from_min").hide();
	}else if(val == "7" || val == "8"){
		$("#to_date").hide();
		$("#from_date").show();
		$("#to_hour").hide();
		$("#from_hour").hide();
		$("#to_month").hide();
		$("#from_month").hide();
		$("#day-").hide();		
		$("#from_min").hide();
	}
	
	// URL 처리용
	if( sDateType == "11"){	
		$("#to_date").hide();
		$("#to_hour").hide();		
		$("#to_month").hide();
		$("#day-").hide();	
		$("#monthType").hide();				
	}else{
		$("#monthType").show();
	}
}

// CP일때 세부검색항목에 따라 세부검색항목에 대한 내용을 바꿔주기 위한 함수
function cp_change( obj ){
	
	//if($("#userLevel").val() != "4"){
	//	return;
	//}else{
		if(obj.value == "3" || obj.value == "4" ||  obj.value == "5" || obj.value == "6" || obj.value == "7"){
			$("#cpspan").html("<input type='text' id='from_low' class='tf_none' style='width:55px'/> - <input type='text' id='to_high' class='tf_none' style='width:55px'/> ");
		}else{
			$("#cpspan").html("<input type='text' id='input_val1' class='tf_none' />");
		}
	//}		
}

function checkDate(){

	var dayType = $("input[name='dayType']:checked").val();
	var startDay = $("#from_date").val();
	var endDay 	 = $("#to_date").val();

	//검색기간이 1개일때는 체크하지 않는다.
	if("7.8.10.11".indexOf(sDateType) > -1){
			
			/* 2011.012.17 날짜검색 000
			var nDate = new Date();
			var nDateStr = nDate.getYear() + dateFormat(eval(nDate.getMonth()+1)) + dateFormat(nDate.getDate()) + dateFormat(nDate.getHours());
			var iDate = ($("#from_date").val() + dateFormat($("#from_hour").val())).replace(/-/g,"");	

			if(nDateStr <= iDate){
				alert("현재시간 이전으로 검색하십시오.");
				return false;
			}
			*/
		
		if(sDateType == "11" && dayType == "3"){
			
			var startDayStr = startDay.split("-");
			var start_day  = new Date(startDayStr[0],startDayStr[1]-1,startDayStr[2]);
			
			if(start_day.getDay() != 1){
				alert("시작일을 월요일로 선택하십시오.");
				return false;
			}
		}
		
		if(sDateType == "10"){
			
			/*
			var checkStr = $("#client_val").val();
			
			if(checkStr.replace(/ /g,"") == ""){
				alert("Client IP는 필수입력사항입니다.");
				return false;
			}
			*/
		}
		
		//주간에 URL검색일 경우 URL입력필수
		if(sDateType == "11" && dayType == "3" ){
			
			var checkStr = $("#url_val").val();
			
			if(checkStr.replace(/ /g,"") == ""){
				alert("URL검색은 필수입력사항입니다.");
				return false;
			}
		}
		return true;
	}
	
	if(dayType == "3"){
		
		var startDayStr = startDay.split("-");
		var endDayStr	= endDay.split("-");
		var start_day  = new Date(startDayStr[0],startDayStr[1]-1,startDayStr[2]);
		var end_day    = new Date(endDayStr[0],endDayStr[1]-1,endDayStr[2]);
		
		if(start_day.getDay() != 1){
			alert("시작일을 월요일로 선택하십시오.");
			return false;
		}
		
		if(end_day.getDay() != 0){
			alert("종료일을 일요일로 선택하십시오.");
			return false;
		}
		
		if((end_day.getTime() - start_day.getTime())/(1000*60*60*24) > 13){
			alert("주검색은 2주를 초과 할 수 없습니다.");
			return false;
		}
	}else if(dayType == "4"){
		
		startDay = $("#from_month").val();
		endDay   = $("#to_month").val();
		
		if( endDay.replace(/-/g,"") - startDay.replace(/-/g,"") > 1){
			alert("월검색은 2달을 초과 할 수 없습니다.");
			return false;
		}
	}else if(dayType == "5"){
		startDay += $("#from_hour").val();
		endDay	  = $("#from_date").val() + $("#to_hour").val();
		//endDay	 = $("#to_hour").val();
	}else if( dayType == "1"){
		if(endDay.replace(/-/g,"") - startDay.replace(/-/g,"") > 2){
			alert("시간검색은 3일을 초과 할 수 없습니다.");
			return false;
		}
	}
	
	//일별 7일이상 제한
	if(dayType == "2" && $("#menuType").val() != 4){
		
		var startDayStr = startDay.split("-");
		var endDayStr	= endDay.split("-");
		var start_day  = new Date(startDayStr[0],startDayStr[1]-1,startDayStr[2]);
		var end_day    = new Date(endDayStr[0],endDayStr[1]-1,endDayStr[2]);
		
		if((end_day.getTime() - start_day.getTime())/(1000*60*60*24) > 13){
			alert("일검색은 14일을 초과 할 수 없습니다.");
			return false;
		}
	}
	
	if( startDay.replace(/-/g,"") > endDay.replace(/-/g,"")){
		alert("시작일이 종료일보다 빠릅니다.");
		return false;
	}
	
	return true;
}

function OnOpen(){
	
 	mySet(document.form1);	
 	
 	//월별일경우 월달력에 내용을 일반달력으로 이동시킴
 	if($("input[name='dayType']:checked").val() == "4" ){
 		$("#to_date").val($("#to_month").val());
 		$("#from_date").val($("#from_month").val());
 	}
 	
 	document.form1.View1.Param("menu1")        =$("#menu1").val();	 //Think Smart용
 	document.form1.View1.Param("menu2")        =$("#menu2").val();	 //Think Smart용
 	document.form1.View1.Param("menu_dev")     =$("#menu_dev").val();//Think Smart용
 	
	document.form1.View1.Param("cdma_dev")     =$("#cdma_dev").val();
	document.form1.View1.Param("svc_dev")      =$("#svc_dev").val();
	document.form1.View1.Param("svc_dev_1")    =$("#svc_dev_1").val();          
	document.form1.View1.Param("data_plf")     =$("#plf_dev").val();    
	document.form1.View1.Param("plf_name")     =$("#plf_name").val();   
	
	document.form1.View1.Param("to_date")      =$("#to_date").val();          
	document.form1.View1.Param("to_dt")        = mvfDateFormat($("#to_date").val(),$("#to_hour").val()) ;  
	
	// 5분,시간별처리
	if( $("input[name='dayType']:checked").val() == "5"){
		document.form1.View1.Param("to_date")  =$("#from_date").val();          
		
		var temp_to_dt        = mvfDateFormat($("#from_date").val(),$("#to_hour").val()) ;
		temp_to_dt = temp_to_dt.substring(0,10) + "5959";		
		document.form1.View1.Param("to_dt")    = temp_to_dt; 		
	}

	document.form1.View1.Param("from_date")    =$("#from_date").val();
	document.form1.View1.Param("from_dt")      = mvfDateFormat($("#from_date").val(),$("#from_hour").val()) ;
	//alert($("#from_date").val() + "//" + $("#from_hour").val() + "//" + mvfDateFormat($("#from_date").val(),$("#from_hour").val()));
	
	document.form1.View1.Param("day")          =$("input[name='dayType']:checked").val();
	document.form1.View1.Param("data_cate")    =$("#data_dev").val();          
	document.form1.View1.Param("data_dev_val") =$("#data_dev_val").val();                    
	
	document.form1.View1.Param("service_sort") =$("#svc_sort").val();            
	document.form1.View1.Param("sort_")        =$("input[name='sort_dev']:checked").val();               
	document.form1.View1.Param("srh_cnt")      = "0";          
	document.form1.View1.Param("rnk_")         = "0";	//어디다 쓸까..              
	
	document.form1.View1.Param("url_val")      =$("#url_val").val();            
	document.form1.View1.Param("select_list")  =$("#select_list1").val();   
	document.form1.View1.Param("select_list1") =$("#select_list1").val();   
	document.form1.View1.Param("input_val")    =$("#input_val1").val();             
	document.form1.View1.Param("input_val1")   =$("#input_val1").val();             
	
	document.form1.View1.Param("min_type")     =$("#min_type").val();;
	document.form1.View1.Param("from_low")     ="";          
	document.form1.View1.Param("to_high")      ="";          
	
	document.form1.View1.Param("sFlag")        ="1";         
	
	document.form1.View1.Param("from_low")     =$("#from_low").val();
	document.form1.View1.Param("to_high")      =$("#to_high").val();
	
	document.form1.View1.Param("min_val")      =$("#min_val").val();
	
	// page,download구분자
	document.form1.View1.Param("path")         =$("#path").val();      
	document.form1.View1.Param("cp_id")        =$("#cpId").val();      
	
	// log
	document.form1.View1.Param("server_val")   =$("#server_val").val();
	document.form1.View1.Param("client_val")   =$("#client_val").val();
	
	document.form1.View1.Param("svc_smart")    =$("#svc_smart").val();
	
	/* 사용안함
	document.form1.View1.Param("iData_dev")    =$("#data_dev").val();          
	document.form1.View1.Param("iMenu_dev")    =$("#menu_dev").val();          
	document.form1.View1.Param("iSvc_dev")     =$("#svc_dev").val();          
	document.form1.View1.Param("iSvc_dev_1")   =$("#svc_dev_1").val();          
	document.form1.View1.Param("svc_nm")       ="";
	*/
	
 	document.form1.View1.Run();
}

/*
 * 일지관련
*/
//첨부파일생성
function createReport() {
	var fromDateVal = $("#to_date").val();	
	var sFeatures = "height=390,width=850,status=yes,toolbar=no,menubar=no,location=no";
	window.open("/daily_report/rept_tmpl_inquiry_cus.do?date_fld="+fromDateVal.replace(/-/g,""), null, sFeatures);
}

//첨부파일리스트
function listReport() {
	var sFeatures = "height=390,width=850,status=yes,toolbar=no,menubar=no,location=no";
	window.open("/daily_report/rept_inquiry_cus.do", null, sFeatures);
}

//2차메뉴 처리
function menu2SelInit(){
	
	$(document).click(function(event){
		if(event.target.id != "sMenu" && event.target.id != "menu2" && event.target.id != "sMenuHead" ){
			$("#sMenu").hide();
			$("#menu2").attr("disabled", false);	//false 활성화, true 비활성화
		};		
	});	
	
	var sMenu = document.getElementById("menu2");
	var sHtml = "<div id='sMenuHead' class='sMenuHead'>프로토콜통계</div>";
	for(i=0;i<sMenu.options.length;i++){
		if(sMenu.options[i].text == "첫메뉴접속" ){
			sHtml += "<div id='sMenuHead' class='sMenuHead'>화면단위통계</div>";
		}
		sHtml += "<div id='sMenuContents' class='sMenuContents' value='"+ sMenu.options[i].value 
		+"' onclick='sMenuClick(this)' "
		+ "onmouseover=\"this.style.backgroundColor='#0080ff';this.style.color='white'\" " 
		+ "onmouseout=\"this.style.backgroundColor='white';this.style.color='black'\"> - " + sMenu.options[i].text + "</div>";
	}

	$("#sMenu").contents().find("#iSmenu").html(sHtml);	
}

function sMenuClick(obj){

	$("#menu2").val(obj);
	$("#sMenu").hide();
	$("#menu2").attr("disabled", false);
	sel_menu2();
}

function sMenuSelectClick(){
	
	$("#menu2").attr("disabled", true);
	$("#sMenu").show();
	
	var tInput  = $("#menu2").offset();
	var tHeight = $("#menu2").outerHeight();
	var sHeight = $("#sMenu").contents().find("#iSmenu").outerHeight();

	$("#sMenu").css({"top":tInput.top + tHeight, "left":tInput.left});	
	$("#sMenu").css({"height":sHeight});
}

/*
 * 스마트 1차메뉴 처리
 */
function menu1SelInit(){
	$(document).click(function(event){
		if(event.target.id != "sMenu1" && event.target.id != "menu1" && event.target.id != "sMenuHead" ){
			$("#sMenu1").hide();
			$("#menu1").attr("disabled", false);
		};		
	});	
	
	var sMenu = document.getElementById("menu1");
	var sHtml = "<div id='sMenuHead' class='sMenuHead'>프로토콜통계</div>";
	
	for(i=0;i<sMenu.options.length;i++){
		//if(sMenu.options[i].text == "첫메뉴접속" ){
		if(i == 3 ){
			sHtml += "<div id='sMenuHead' class='sMenuHead'>프로토콜통계</div>";
		}
		sHtml += "<div id='sMenuContents' class='sMenuContents' value='"+ sMenu.options[i].value 
		+"' onclick='sMenu1Click(this)' "
		+ "onmouseover=\"this.style.backgroundColor='#0080ff';this.style.color='white'\" " 
		+ "onmouseout=\"this.style.backgroundColor='white';this.style.color='black'\"> - " + sMenu.options[i].text + "</div>";
	}
	
	$("#sMenu1").contents().find("#iSmenu1").html(sHtml);		
}

function sMenu1Click(obj){
	$("#menu1").val(obj);
	$("#sMenu1").hide();
	$("#menu1").attr("disabled", false);
	sel_menu1();
}

function sMenu1SelectClick(){
	$("#menu1").attr("disabled", true);
	$("#sMenu1").show();
	
	var tInput  = $("#menu1").offset();
	var tHeight = $("#menu1").outerHeight();
	var sHeight = $("#sMenu1").contents().find("#iSmenu1").outerHeight();

	$("#sMenu1").css({"top":tInput.top + tHeight, "left":tInput.left});	
	$("#sMenu1").css({"height":sHeight});
}
