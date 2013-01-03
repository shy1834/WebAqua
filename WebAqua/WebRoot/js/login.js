$(document).ready(function(){
   $("#ID").focus();
   $("#PASSWORD").keydown(function(event){
	   if (event.keyCode == "13") submit_check();
   });
   $("#loginBtn").click(function(event){
	  submit_check();
   }).css("cursor","hand");
   
   myLoader80(Loader1);
});

function submit_check(){
	
	var id = $("#ID").val();
	var pw = $("#PASSWORD").val();
	
	if(id.replace(/ /g, "") == ""){
		alert("아이디를 입력하십시오");
		$("#ID").focus();
		return;
	}
	
	if(pw.replace(/ /g, "") == ""){
		alert("패스워드를 입력하십시오");
		$("#PASSWORD").focus();
		return;
	}
	
	var url = "http://ktflogon.magicn.com/logon/logon.asp?URL="+ doamain_url +"/login.do?cmd=authority";
	document.loginForm.action = url;
	document.loginForm.submit();						
}