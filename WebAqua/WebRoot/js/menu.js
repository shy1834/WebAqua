	function goLeftMenu(menuId, path, cdma_dev){
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad='+path+'&cdma_dev='+cdma_dev;
}

function goMenuList(menuId, path, cdma_dev, tcs_min){
	location = '/menu.do?menuId='+menuId+'&path='+path+'&cdma_dev='+cdma_dev;
}

function goCdmaType(cdma_dev){
	top.main.location = '/mainPage.do?cdma_dev='+cdma_dev;
}

function goMain(cdma_dev){
	top.main.location = '/mainPage.do?cdma_dev='+cdma_dev;
}

function BoardPop(seq, gubun) {
	window.open("/boardInfo.do?seq="+seq+"&gubun="+gubun,"","toolbar=0, status=0, scrollbars=yes, location=0, menubar=0, width=665, height=440");
}

String.prototype.replaceAll = function( searchStr, replaceStr )
{
var temp = this;

while( temp.indexOf( searchStr ) != -1 )
{
temp = temp.replace( searchStr, replaceStr );
}

return temp;
}

function Auth_Chk(AuthType, portNo) {
	if(portNo == "80") {
		if(AuthType != "04") {
			alert("KTF직원 아이디는 8080포트로 접속 해 주시기 바랍니다.");
			top.location.href = 'http://kt68aqua.magicn.com:8080/index.jsp';
		}
	}
	
	if(AuthType == "") {
		alert("접속 권한이 없습니다.");
		top.location.href = 'http://kt68aqua.magicn.com:8080/index.jsp';
	}
}

function goAlert() {
	alert("권한이 제한된 메뉴입니다.");
}

function goUserEdit(menuId, seq, userType, cdma_dev) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userEdit&seq='+seq+'&userType='+userType+'&cdma_dev='+cdma_dev;
}

function goUserInfo(menuId, seq, userType, cdma_dev) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userView&seq='+seq+'&userType='+userType+'&cdma_dev='+cdma_dev;
}

function goUserDelete(menuId, seq, cdma_dev) {
	if(confirm("삭제하시겠습니까?")) {
		top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userDelete&seq='+seq+'&cdma_dev='+cdma_dev;
	} 
}

function goMyInfo(userType, cdma_dev) {
	top.main.location = '/userInfo.do?path_ad=userInfo&userType='+userType+'&cdma_dev='+cdma_dev;
}

function goListPage(menuId, strPage, cdma_dev, srhItem, srhName) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userSearch&str_c_page='+strPage+'&cdma_dev='+cdma_dev+'&srhItem='+srhItem+'&srhName='+srhName;
}

function goSearchListPage(menuId, srhItem, srhName, strPage) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userSearch&srhItem='+srhItem+'&srhName='+srhName+'&str_c_page='+strPage;
}

function srhChange(menuId, path, cdma_dev, strPage, srhItem){
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad='+path+'&str_c_page='+strPage+'&cdma_dev='+cdma_dev+'&srhItem='+srhItem;
}


// 왼쪽메뉴에서 공통으로 사용하는 스크립트

function leftChange(menuId, menu_dev, cdma_dev, path) {
	top.main.location = '/menu.do?menuId='+menuId+'&menu_dev='+menu_dev+'&cdma_dev='+cdma_dev+'&path='+path;
}

function leftChange1(menuId, menu_dev, p_name, cdma_dev, path) {
	top.main.location = '/menu.do?menuId='+menuId+'&menu_dev='+menu_dev+'&p_name='+p_name+'&cdma_dev='+cdma_dev+'&path='+path;
}

function getNextWeek(v,t){ //날짜스트링과, 일자를 파라메터로 받는다
	var str=new Array(); //배열
	var b=v.split("/"); //날짜를 / 구분자로 나누어 배열로 변환
	var c=new Date(b[0],b[1]-1,b[2]); //데이트객체 생성
	var d=c.valueOf()+1000*60*60*24*t; //t일후, (음수면 전일)의 타임스탬프를 얻는다
	var e=new Date(d); //의뢰한날의 데이트객체 생성
 
	str[str.length]=e.getYear(); //년
	str[str.length]=day2(e.getMonth()+1).toString(); //월
	str[str.length]=day2(e.getDate()).toString(); //일
	return str.join("/"); //배열을 / 구분자로 합쳐 스트링으로 변환후 반환
}

function day2(d) {	// 2자리 숫자로 변경
	var str = new String();
	
	if (parseInt(d) < 10) {
		str = "0" + parseInt(d);
	} else {
		str = "" + parseInt(d);
	}
	return str;
}

function to_PreMonth(sDate, sPart){
	var arr = sDate.split("/");
	var sYear = arr[0];
	var sMonth = arr[1];
	
	if(parseInt(sMonth) < 10) 
		sMonth = sMonth.replace("0","");

	var intThisYear = parseInt(sYear);
	var intThisMonth = parseInt(sMonth);

	datToday = new Date();

	if (intThisYear == 0) 
		intThisYear = datToday.getFullYear();			// 값이 없을 경우
	if (intThisMonth == 0) 
		intThisMonth = parseInt(datToday.getMonth())+1;	// 월 값은 실제값 보다 -1 한 값이 돼돌려 진다.

	if (intThisMonth == 1) {
		intPrevYear = intThisYear -1;
		intPrevMonth = 12;
	} else {
		intPrevYear = intThisYear;
		intPrevMonth = parseInt(intThisMonth) - 1;
	}

	if (sPart == "1") {
		document.frm.from_date.value = intPrevYear+"/"+day2(intPrevMonth).toString();
	} else if (sPart == "2") {
		document.frm.to_date.value = intPrevYear+"/"+day2(intPrevMonth).toString();
	}
}

function to_NextMonth(sDate, sPart) {
	var arr = sDate.split("/");
	var sYear = arr[0];
	var sMonth = arr[1];
	
	if(parseInt(sMonth) < 10) 
		sMonth = sMonth.replace("0","");

	var intThisYear = parseInt(sYear);
	var intThisMonth = parseInt(sMonth);
	
	datToday = new Date();

	if (intThisYear == 0) 
		intThisYear = datToday.getFullYear();			// 값이 없을 경우
	if (intThisMonth == 0) 
		intThisMonth = parseInt(datToday.getMonth())+1;	// 월 값은 실제값 보다 -1 한 값이 돼돌려 진다.

	if (intThisMonth == 12) {
		intNextYear = intThisYear + 1;
		intNextMonth = 1;
	} else {
		intNextYear = intThisYear;
		intNextMonth = parseInt(intThisMonth) + 1;
	}

	if (sPart == "1") {
		document.frm.from_date.value = intNextYear+"/"+day2(intNextMonth).toString();
	} else if (sPart == "2") {
		document.frm.to_date.value = intNextYear+"/"+day2(intNextMonth).toString();
	}
}

function link_fileDown(File)
{
	top.main.location = "/tstat/Fdownload.jsp?file_name="+File;
}