// max year ( max_year = 0 = now year) 
var max_year = 2020;

// min year
var min_year = 2005;

// date format (xxxx-xx-xx)
var date_split_format = "-";

// day of the week 
var yoil = ["��","��","ȭ","��","��","��","��","��"];

// month of day
var mon = [31,28,31,30,31,30,31,31,30,31,30,31];

// start day ( 0: sunday, 1:monday )
var start_day = "0";

// today			
var todayStr  = dateStr(new Date(),1);		

function initCal(target){
	var id = target + "Cal";
	$(function(){		
		var tInput  = $("#" + target).offset();
		var tHeight = $("#" + target).outerHeight();
		
		// �Է°��� ������� �޷��ʱ�ȭ�� �ش� �Է´޿� ��¥�� ����
		var targetVal = $("#" + target).val();		
		if( targetVal == "") targetVal = todayStr;
		
		$(document.body).append($("<div id='"+ id +"' onselectstart='return false'>"));
		$(document).click(function(event){
			if(event.target.id != id && event.target.id != target && $("#" + id).css('display') != "none" ){
				$("#" + id).hide();
			};	
		});
								
		//browser�� ó��
		if ($.browser.msie) {
    }else {
    		function reEnable(){return true;	}		
				function disableselect(e){	
					return false;
				}															
				document.getElementById(id).onmousedown=disableselect;
				document.getElementById(id).onmouseup=reEnable;			
    }
		
    $("#" + id).addClass("divBody");
    $("#" + id).html(makeCal(id, targetVal));
    $("#" + id).click(function(e){
				e.stopPropagation();
		});
		
		//wheel event
		$("#" + id).bind('mousewheel', function(event, delta) {
            if (delta > 0) {moveDate(id, 1);}
				else {	moveDate(id, -1); }
				addHover(id,target);
            return false;
        });


		$("#" + target).attr("readonly", true);
		$("#" + target).click(function(e){
			changeToday(id,target);
			calPosition(target,id);
			$("#" + id).show();
		});	
		
		$("#" + id + "_calYear").attr("value",targetVal.split(date_split_format)[0]);
		$("#" + id + "_calYear").change(function(){			
			changeCal(id,target);
		}).keyup(function(){
			changeCal(id,target);		
		}).mousewheel(function(e, d){			
			this.blur();
		});
		
		$("#" + id + "_calMon").attr("value",eval(targetVal.split(date_split_format)[1]));
		$("#" + id + "_calMon").change(function(){
			changeCal(id,target);
		}).keyup(function(){									
			changeCal(id,target);
		}).mousewheel(function(e, d){			
			this.blur();
		});
		
		addHover(id,target);
			
		$("#" + id + "_left").click(function(e){
			moveDate(id,-1);
			addHover(id,target);
			this.blur();
		}).mouseover(function(){
			$(this).addClass("divHeadOnleft");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnleft");
		});
		
		$("#" + id + "_right").click(function(e){
			moveDate(id,1);
			addHover(id,target);
			this.blur();					
		}).mouseover(function(){
			$(this).addClass("divHeadOnRigth");
		}).mouseout(function(){
			$(this).removeClass("divHeadOnRigth");
		});;
		
		$("#" + id + "tBtn").addClass("tBtn");
		$("#" + id + "tBox").hover(function(){
			$(this).addClass("tBtnOn");
		},function(){
			$(this).removeClass("tBtnOn");
		}).click(function(){
			changeToday(id,target);
		});
		
		$(window).resize(function() {
	    	calPosition(target,id);
	    });
	});
}

function calPosition(target,id){
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

function addHover(id,target){
	$(function(){		
		$("#" + id + "_cal div[value]").hover(function(){
			$(this).addClass("onDay");
		},function(){
			$(this).removeClass("onDay");
		}).click(function(e){
			$("#" + target).attr("value", $(this).attr("value"));
			$("#" + id).hide();
			$("#" + id + " .selDay").removeClass("selDay");
			$(this).addClass("selDay");						
		});				
		
		$("#" + id + "_cal [day='0']").addClass("daySun");
		$("#" + id + "_cal [day='6']").addClass("daySat");
		for(var i=1;i<6;i++) $("#" + id + "_cal [day='" + i + "']").addClass("dayEv");
		$("#" + id + "_cal [value='"+$("#"+target).val() +"']").addClass("selDay");
		$("#" + id + "_cal [value='"+ todayStr + "']").addClass("dayToday");
	});
}

function changeCal(id,target){
	$("#" + id + "_cal").html(makeDate($("#" + id + "_calYear").val(),($("#" + id + "_calMon").val()-1)),id);
	addHover(id,target);
}

function changeToday(id,target){
	var today =  dateStr(new Date(),2);
	$("#" + id + "_cal").html(makeDate(today[0],eval(today[1]-1)),id);
	$("#" + id + "_calMon").attr("value",eval(today[1]));
	$("#" + id + "_calYear").attr("value",today[0]);
	addHover(id,target);
}

function makeCal(id , tValue)
{	 
	 var cal_html = "";	 
	 var tDate = tValue.split(date_split_format);
	 
	 if ((tDate[0] % 4 == 0 && tDate[0] % 100 != 0) || tDate[0] % 400 == 0) mon[1] = 29;
	
	 cal_html += "<table width='100%'><tr><td><div class='divHead'><table class='calCss'><tr><td id='"+ id +"_left' class='divHeadLeft'>��</td><td class='divHeadCenter'><select id='"+id+"_calYear'>";				 
	
	 if( max_year == 0) max_year = tDate[0] ;
	 for(var i=min_year;i<=max_year;i++){	cal_html += "<option value='"+i+"'>" + i + "</option>"; }
	 cal_html += "</select><select id='"+id+"_calMon' style='width:60px'>";
	 for(var i=1;i<13;i++){ 	cal_html += "<option value='"+ i +"'>" + i +"��</option>"; }
	 cal_html += "</select></td><td id='"+ id +"_right' class='divHeadRigth'>��</td></tr></table></div></td></tr><tr><td><div id='" + id + "_cal" + "'>";			
		
	 cal_html += makeDate(eval(tDate[0]),eval(tDate[1]-1),id);	
	 cal_html +="</div></td></tr></table></div>";
	 // �ϴ� today ����
	 //cal_html +="</div></td></tr></table></div><div id='"+id+"tBtn'><span id='"+id+"tBox'>Today</span></div>";

	 return cal_html;	 
}

function makeDate(year,month,id){
	var today;
	var cal_html = "<table class='calCss'><tr>";
	
	// day of the week 
	for (i=start_day;i<eval(start_day)+7;i++){ cal_html += "<td align='center'>"+yoil[i];	}

	day = new Date(year,month,1);

	// before
	if (day.getDay() != start_day) {
	  var startDay = day.getMonth() == 0 ? 0: eval(day.getMonth()-1);
		var maxDay   = day.getDay()== 0 ? 6: eval(day.getDay()-start_day);
								
		cal_html +="<tr>";
	  for (i=maxDay;i>0;i--){
	   		cal_html +="<td><div value='" + dateStr(new Date(year,eval(day.getMonth()-1),(mon[startDay] - (i-1))),1)+"' class = 'dayOther'>" + (mon[startDay] - (i-1))+ "</div></td>";
	 	}
	}	
	
	// day
	for (i=1;i<=mon[day.getMonth()];i++){		
		today 		= new Date(year,month,i); // ���� ��¥�� ���� -> ������ �˱� ���ؼ�.
		// �Ͽ����̸� ���� �ٷ� �Ѿ.
		if (today.getDay() == start_day){ cal_html +="<tr>"; }
		cal_html += "<td><div value='" + dateStr(today,1) +"' day='" + today.getDay() +"' >" + i + "</div></td>";
	}
	
	 // next	 
	 if(((start_day == 0) && today.getDay() != 6) || (start_day == 1) && today.getDay() != 0){	 	 
		 var next_day = 1;		 
		 for (i=today.getDay();i<6+eval(start_day);i++){		 			 
			 cal_html +="<td><div value='" + dateStr(new Date(year,eval(day.getMonth()+1),next_day),1)+"' class='dayOther'>" + next_day++ + "</div></td>";		 
		 }	
	 }
	 
	 return cal_html;
}

function moveDate(id,plus){	
	var month = eval($("#" + id + "_calMon").val());
	var year  = eval($("#" + id + "_calYear").val());
	month += plus;
	
	if( month == 0){
		month = 12;
		year  += plus;
	}else if(month == 13){
		month = 1;
		year  += plus;
	}
	
	if( max_year == 0) max_year = todayStr.split(date_split_format)[0] ;
	if( year < min_year || year > max_year ) return;

	$("#" + id + "_calMon").attr("value",month);
	$("#" + id + "_calYear").attr("value",year);
	$("#" + id + "_cal").html(makeDate(year,month-1),id);
}

	
function dateStr( obj, returnType){
	var y = obj.getFullYear();
	var m = obj.getMonth()+ 1 + "";
	var d = obj.getDate() + "";
	
	if(m.length == 1) m = "0" + m;
	if(d.length == 1) d = "0" + d;
	
	var returnValue = y+ date_split_format + m + date_split_format +d; 
	
	if( returnType == "1"){
		return returnValue;
	}else if( returnType == "2"){
		return 	returnValue.split(date_split_format);	
	}
}

function changeToday(id,target){
	var today =  dateStr(new Date(),2);
	if($("#" + target).val() != ""){
		today = ($("#" + target).val()).split(date_split_format);
	}
	
	$("#" + id + "_cal").html(makeDate(today[0],eval(today[1]-1)),id);
	$("#" + id + "_calMon").attr("value",eval(today[1]));
	$("#" + id + "_calYear").attr("value",today[0]);
	addHover(id,target);
}