// max year ( max_year_mon = 0 = now year) 
var max_year_mon = 2020;

// min year
var min_year_mon = 2005;

// date format (xxxx-xx-xx)
var date_split_format_mon = "-";
						
// today			
var dayObj	  	  = new Date();
var todayStr_year = dayObj.getFullYear();
var todayStr_mon  = ((dayObj.getMonth()+101)+"").substring(1,3);
var todayStr_full  = todayStr_year+date_split_format_mon+todayStr_mon;			

// firefox drag						
var omitformtags=["input", "textarea", "select"]
omitformtags=omitformtags.join("|")			
function disableselect(e){
	if (omitformtags.indexOf(e.target.tagName.toLowerCase())==-1) return false 
}			

function reEnable(){return true	}			
if ( typeof document.onselectstart!="undefined")
	document.onselectstart=new Function ("return false")
else{
	document.onmousedown=disableselect
	document.onmouseup=reEnable
}
// firefox drag end;			

function initCalMon(target){
	var id = target + "CalMon";
	$(function(){		
		var tInput  = $("#" + target).offset();
		var tHeight = $("#" + target).outerHeight();
		
		// �Է°��� ������� �޷��ʱ�ȭ�� �ش� �Է´޿� ��¥�� ����
		var targetVal = $("#" + target).val();							
		if( targetVal == "") targetVal = todayStr_full;
						
		$(document.body).append($("<div id='"+ id +"'>"));
		$(document).click(function(event){
			if(event.target.id != id && event.target.id != target && $("#" + id).css('display') != "none" ){
				$("#" + id).hide();
			};	
		});;
	
		//browser�� ó��
    if ($.browser.msie) {
    }
    else {
    }
		
    $("#" + id).addClass("divBody");
    $("#" + id).html(makeCalMon(id, targetVal));
    $("#" + id + "_cal div[value]").addClass("dayEvMon");
    
    $("#" + id).click(function(e){
			e.stopPropagation();
		});
		
		//wheel event
		$("#" + id).bind('mousewheel', function(event, delta) {
        if (delta > 0) {moveMon(id, 1);}
				else {	moveMon(id, -1); }
				addHoverMon(id,target);
            return false;
        });

		$("#" + target).attr("readonly", true);
		$("#" + target).click(function(e){
			calMonPosition(target,id);									
			if($("#"+target).val() != ""){
				var subYear = $("#"+target).val();														
				$("#"+id+"_calYear").val(subYear.substring(0,4));				
			}else{				
				$("#"+id+"_calYear").val(todayStr_year);
			}
			addHoverMon(id,target);
			$("#" + id).show();
		});	
		
		$("#" + id + "_calYear").attr("value",targetVal.split(date_split_format_mon)[0]);
		$("#" + id + "_calYear").change(function(){															
			addHoverMon(id,target);
		}).keyup(function(){
			addHoverMon(id,target);
		}).mousewheel(function(e, d){			
			this.blur();
		});
			
		// top option	
		$("#" + id + "_left").click(function(e){
			moveMon(id,-1);
			addHoverMon(id,target);
			this.blur();
		}).mouseover(function(){
			$(this).addClass("divHeadOnleft");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnleft");
		});
		
		$("#" + id + "_right").click(function(e){
			moveMon(id,1);
			addHoverMon(id,target);
			this.blur();					
		}).mouseover(function(){
			$(this).addClass("divHeadOnRigth");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnRigth");
		});
		
				// date option
		$("#" + id + "_cal div[value]").hover(function(){
			$(this).addClass("onMon");
		},function(){
			$(this).removeClass("onMon");
		}).click(function(e){						
			$("#" + target).attr("value", $("#" + id + "_calYear").val() + date_split_format_mon + $(this).attr("value"));
			$("#" + id).hide();
			$("#" + id + " .selMon").removeClass("selMon");
			$(this).addClass("selMon");
		});								
		addHoverMon(id,target);
		
		$(window).resize(function() {
	    	calMonPosition(target,id);
	    });
	});
}

function calMonPosition(target,id){
	var tInput  = $("#" + target).offset();
	var tHeight = $("#" + target).outerHeight();
	var tWidth  = $("#" + target).outerWidth();
	
	var calHeight = $("#" + id).outerHeight();
	var calWidth  = $("#" + id).outerWidth();
	
	if( tInput != null){
		//$("#" + id).css({"top":tInput.top+tHeight , "left":tInput.left});
		$("#" + id).css({"top":tInput.top-calHeight+tHeight, "left":tInput.left+tWidth});
	}
}

function addHoverMon(id,target){
	$(function(){		
		$("#" + id + " .selMon").removeClass("selMon");
		$("#" + id + " .MonToday").removeClass("MonToday");											
		
		// ���ô� ǥ��
		if($("#"+target).val() != ""){
				var subYear = $("#"+target).val();														
				if($("#"+id+"_calYear").val() == subYear.substring(0,4)){
					$("#" + id + "_cal [value='"+ subYear.substring(5,7) +"']").addClass("selMon");														
		}}
		
		// ����� ǥ��
		if($("#"+id+"_calYear").val() == todayStr_year){
			$("#" + id + "_cal [value='"+ todayStr_mon +"']").addClass("MonToday");														
		}					
	});
}	

function makeCalMon(id , tValue)
{
	 var today;
	 var cal_html = "";				 
	 var tDate = tValue.split(date_split_format_mon);
	 
	 cal_html += "<table width='100%'><tr><td><div class='divHead'><table class='calCss'><tr><td id='"+ id +"_left' class='divHeadLeft'>��</td><td class='divHeadCenter'><select id='"+id+"_calYear'>";				 				
	 if( max_year_mon == 0) max_year_mon = tDate[0] ;
	 for(var i=min_year_mon;i<=max_year_mon;i++){	cal_html += "<option value='"+i+"'>" + i + "</option>"; }
	 cal_html += "</select></td><td id='"+ id +"_right' class='divHeadRigth'>��</td></tr></table></div></td></tr><tr><td><div id='" + id + "_cal" + "'><table class='calCss'><tr>";							 

   for (i=1;i<=12;i++){			
		var dValue = i;
		if( i < 10 ) dValue = "0" + dValue;									
		cal_html += "<td><div value='" + dValue +"'>" + i + "��</div></td>";
		if (i % 4 == "0"){ cal_html +="</tr><tr>"; }
	 }					
	 				 				 
	 cal_html +="</div></td></tr></table></div>";
	 			 
	 return cal_html;	 
}			

function moveMon(id,plus){					
	var year  = eval($("#" + id + "_calYear").val());															
	year += plus;					
	if( max_year_mon == 0) max_year_mon = todayStr_full.split(date_split_format_mon)[0] ;
	if( year < min_year_mon || year > max_year_mon ) return;			
	$("#" + id + "_calYear").attr("value",year);				
}

function dateStrMon( obj ){
	var y = obj.getFullYear();
	var m = obj.getMonth()+ 1 + "";	
	
	if(m.length == 1) m = "0" + m;	
	
	var returnValue = y+ date_split_format + m ; 		
	return 	returnValue.split(date_split_format);		
}
			
			
function changeMon(id,target){
	var today =  dateStrMon(new Date());
	
	if($("#" + target).val() != ""){
		today = ($("#" + target).val()).split(date_split_format);
	}	
	
	$("#" + id + "_cal").html(makeCalMon(id,today.join(date_split_format)));	
	$("#" + id + "_calYear").attr("value",today[0]);
	addHoverMon(id,target);
}	