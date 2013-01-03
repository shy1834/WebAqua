function goToHome(){
	parent.frames['left'].location.href = '/WebAqua/left.do';
	location.href = "/WebAqua/center.do";
}
function goToStatistics(){
	location.href = "/WebAqua/statistics/stat_grp_ifr.do";
}
function goToConfig(){
	location.href = "/WebAqua/config/cfg_cp_inquiry.do";
}

function goToSystem(){
	location.href = "/WebAqua/system/sys_my_info_inquiry.do";
}
function goToFault(){
	location.href = "/WebAqua/fault/falt_status_inquiry.do";
}
function goToSubMenu(url){
	location.href=url;
}
function goToModi(url){
	parent.frames['main'].location.href = url;
	parent.frames['left'].location.href = '/WebAqua/left.do?menu_group_id=MGRP0017';
}