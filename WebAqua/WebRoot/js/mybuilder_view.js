/********************************************************************************
 * 작 성 일 : 2007년 10월 26일 
 * 작 성 자 : (주)한국이디에스 - 이해중 대리
 * 작성방법 : 마이빌더 화일의 분산처리된 부분을 하나의 화일로 일괄
 *            처리하였음.
 * 비고     : 본 화일을 마이빌더 사용화일의 헤더 부분에 아래와 같이 
 *            삽입한 후 해당 함수를 호출하여 사용한다. 
 *  ex) <script type="text/javascript" src="/js/mybuilder_view.js"></script>
 ********************************************************************************/

/* 마이빌더 화일의 객체를 불러 설치작업을 한다. 80은 포트 번호 */
function myLoader80(Loader1)
{	
	 Loader1.WindowTitle = "마이빌더 설치";
	 Loader1.DownloadMessage = "실행에 필요한 파일들을 내려받고 있습니다.";
	 Loader1.RegisterMessage = "다음 파일(들)은 레지스트리에 등록되어야 정상적으로 실행됩니다.\n이어서 표시되는 대화창에 관리자 암호를 입력하여 설치를 마무리 하십시오.";
	 Loader1.ViewerTitle = "마이빌더 뷰어";
	 Loader1.ChartTitle = "차트 라이브러리";
	 Loader1.RegisterThis = false;
	 
	 /* Local test line */
	 //var ret = window.Loader1.LoadEx("http://localhost:8080/report/Biin");
	 //var ret = window.Loader1.LoadEx("http://kt68aqua.magicn.com:80/report/Biin");
	 
	 /* Real server line */
	 var ret = window.Loader1.LoadEx("http://221.148.242.7/report/Biin");
	 
	 if(ret == 1){
		 alert("정상적으로 설치 되었습니다.");
	 }	  
}

/* 마이빌더가 사용된 화면에서 아래의 함수를 호출하여 화면에 출력 한다.   */
/* 실질적인 사용 MyBuilder 선택(DB 설정은 Config.inf) */
function mySet(obj)
{   
	//Local test line - protected line
	//obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://kt68aqua.magicn.com/report/Files/", "");
	//obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://localhost:8080/report/Files/", "");
	 
	//Real server execution line - protected line
	obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://221.148.242.7/report/Files/", "(Server)");
    obj.View1.FileName = obj.file_name.value;//fileName;
    obj.View1.Embed = true;
    obj.View1.ToolBar = false;  		
	obj.View1.StatusBar = false;
}

/* 마이빌더의 화면에 조화여부 */
function mySet_Embed(obj, val)
{   
    obj.View1.Embed = val;
}

/* 마이빌더 사용 도구의 화면 표시여부 */
function mySet_Toolbar(obj, val)
{
    obj.View1.ToolBar = val;
}    

/* 마이빌더 상태바 표시 여부 */    
function mySet_Status(obj, val)
{
	obj.View1.StatusBar = val;
}		

/* 마이빌더 기본키등의 사용 여부 */
function mySet_Command(obj)
{
    //사용가능한 명령
    //INSERT:추가, MODIFY:수정, DELETE:삭제, UPDATE:확인,
    //BUILD:작성, COPY:복사, PASTE:붙이기,
    //PREVIEW:미리보기, PRINT:인쇄, MAIL:메일보내기, EXCEL:엑셀로 보내기,
    //FIRST:첫쪽, LAST:끝쪽, PREV:이전쪽, NEXT:다음쪽,
    //FIT:전체보기, NORMAL:표준보기, ZOOMIN:확대, ZOOMOUT: 축소
    //USER1~9:사용자정의
    obj.View1.Command(cmd.value);
    obj.View1.Command(FIT);
}	

