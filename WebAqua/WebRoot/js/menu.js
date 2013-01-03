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
			alert("KTF���� ���̵�� 8080��Ʈ�� ���� �� �ֽñ� �ٶ��ϴ�.");
			top.location.href = 'http://kt68aqua.magicn.com:8080/index.jsp';
		}
	}
	
	if(AuthType == "") {
		alert("���� ������ �����ϴ�.");
		top.location.href = 'http://kt68aqua.magicn.com:8080/index.jsp';
	}
}

function goAlert() {
	alert("������ ���ѵ� �޴��Դϴ�.");
}

function goUserEdit(menuId, seq, userType, cdma_dev) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userEdit&seq='+seq+'&userType='+userType+'&cdma_dev='+cdma_dev;
}

function goUserInfo(menuId, seq, userType, cdma_dev) {
	top.main.location = '/userInfo.do?menuId_ad='+menuId+'&path_ad=userView&seq='+seq+'&userType='+userType+'&cdma_dev='+cdma_dev;
}

function goUserDelete(menuId, seq, cdma_dev) {
	if(confirm("�����Ͻðڽ��ϱ�?")) {
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


// ���ʸ޴����� �������� ����ϴ� ��ũ��Ʈ

function leftChange(menuId, menu_dev, cdma_dev, path) {
	top.main.location = '/menu.do?menuId='+menuId+'&menu_dev='+menu_dev+'&cdma_dev='+cdma_dev+'&path='+path;
}

function leftChange1(menuId, menu_dev, p_name, cdma_dev, path) {
	top.main.location = '/menu.do?menuId='+menuId+'&menu_dev='+menu_dev+'&p_name='+p_name+'&cdma_dev='+cdma_dev+'&path='+path;
}

function getNextWeek(v,t){ //��¥��Ʈ����, ���ڸ� �Ķ���ͷ� �޴´�
	var str=new Array(); //�迭
	var b=v.split("/"); //��¥�� / �����ڷ� ������ �迭�� ��ȯ
	var c=new Date(b[0],b[1]-1,b[2]); //����Ʈ��ü ����
	var d=c.valueOf()+1000*60*60*24*t; //t����, (������ ����)�� Ÿ�ӽ������� ��´�
	var e=new Date(d); //�Ƿ��ѳ��� ����Ʈ��ü ����
 
	str[str.length]=e.getYear(); //��
	str[str.length]=day2(e.getMonth()+1).toString(); //��
	str[str.length]=day2(e.getDate()).toString(); //��
	return str.join("/"); //�迭�� / �����ڷ� ���� ��Ʈ������ ��ȯ�� ��ȯ
}

function day2(d) {	// 2�ڸ� ���ڷ� ����
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
		intThisYear = datToday.getFullYear();			// ���� ���� ���
	if (intThisMonth == 0) 
		intThisMonth = parseInt(datToday.getMonth())+1;	// �� ���� ������ ���� -1 �� ���� �ŵ��� ����.

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
		intThisYear = datToday.getFullYear();			// ���� ���� ���
	if (intThisMonth == 0) 
		intThisMonth = parseInt(datToday.getMonth())+1;	// �� ���� ������ ���� -1 �� ���� �ŵ��� ����.

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