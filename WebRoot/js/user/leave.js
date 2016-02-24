/*消息框*/
function alterMsg(msg){
	if(msg && msg != ''){
		alert(msg);
	}
}



function addLevae(){
	var lStatus = $("#lStatus").val();
	var reason = $("#reason").val().trim();
	var start = $("#start").val();
	var end = $("#end").val();
	if(lStatus==-1){
		alert("请选择请假类型!");
		return false;
	}
	if(reason.length <1){
		alert("请输入请假缘由!");
		return false;
	}
	if(start.length <1){
		alert("请输入请假开始时间!");
		return false;
	}
	if(end.length <1){
		alert("请输入请假结束时间!");
		return false;
	}
	if(start.length >1&&end.length >1){
		if(end<start){
			alert("请输入正确的请假时间!");
			return false;
		}
	}
	return true;
}


function searchAjax(path,id,dpId,userId){
	$.post(path+"/Leave/searchLeave.html",{id:id,dpId:dpId,userId:userId},function(data){
		$("#nameAjax").val(data.map.real_name);
		var levaeStatus=data.map.l_status;
		var levaeString="";
		if(levaeStatus==1){
			levaeString="事假";
		}else if(levaeStatus==2){
			levaeString="病假";
		}else if(levaeStatus==3){
			levaeString="婚假";
		}else if(levaeStatus==4){
			levaeString="产假";
		}else if(levaeStatus==5){
			levaeString="丧假";
		}else if(levaeStatus==6){
			levaeString="工伤";
		}else if(levaeStatus==7){
			levaeString="年休假";
		}else{
			levaeString="其他";
		} 
		
		$("#editId").val(id);
		$("#lStatusAjax").text(levaeString);
		$("#reasonAjax").val(data.map.l_reason);
		$("#startAjax").val(data.map.l_start);
		$("#endAjax").val(data.map.l_end);
		$("#bAjax").val(data.map.l_remarks);
		$("#dpAjax").val(data.dpUserName);
	});
}
