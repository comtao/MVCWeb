/*消息框*/
function alterMsg(msg){
	if(msg && msg != ''){
		alert(msg);
	}
}

var str=1;
var value='{ABCDEFGHIJKLMNOPQRSTUVWXYZ}';
function addOptions(){
	str=str+1;
var op="<div id="+value[str]+"><span style='margin-right: 10px'>选项"+value[str]+"</span><input class='form-control' type='text' name=options value='"+value[str]+"' ><input type='file' name=file value=''> <a href='javascript:void(0);' onclick=\"removeOptions('"+value[str]+"');\"><i class='icon-trash'></i> </a></div>";
	$("#options").append(op);
}


function removeOptions(id){
	str=1;
	$("#"+id).remove();
}



function toupiao(path,id){
	var optionsId=$("input[name='optionsId']:checked").val();
	$.post(path+'/Vote/toupiao.html',{id:id,optionsId:optionsId},function(data){
		var list=data.list;
		var str ="";
		var sum = data.sum;
		for(var i in list){
			str+='<b>'+list[i].option_con+'</b>' +
				'<b style="background: none repeat scroll 0 0 #CCCCCC;width: 400px;display: block;" >' +
		'<i style="background: none repeat scroll 0 0 #cec;display: block; text-align:center;width: '+Math.floor(list[i].sum/sum*100).toFixed(1)+'%;">' +
				''+list[i].sum+'<font color="#8A8A8A">('+(list[i].sum/sum*100).toFixed(1)+'%)</font></i></b>';
			
		}
		$("#well").html(str);
	});
}


function comment(path,id){
	var comment = $("#com").val();
	$.post(path+'/Vote/comment.html',{id:id,comment:comment},function(data){
		var list=data.list;
		var str ="";
		for(var i in list){
			str+='<p><b style="color: #1874CD;">'+list[i].real_name+'</b>&nbsp;&nbsp;&nbsp;'+list[i].user_comment+'<br>' +
					'<font  color="#6E6E6E">'+timeFormat(list[i].itime)+'</font></p>';
		}
		$("#comments").html(str);
		$("#com").val("");
	});
}




function addVote(){
	var title = $("#title").val();
/*	var options=document.getElementsByName("options");*/
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(title <1){
		alert("请输入投票标题!");
		return false;
	}
  if ($("input:checkbox[name='dpvalueId']:checked").length < 1) {
		alert("最少选择一个部门!");
		return false;
	}
    
	if(startTime <1){
		alert("请输入开始时间!");
		return false;
	}
	if(endTime <1){
		alert("请输入结束时间!");
		return false;
	}
	
	return true;
}


 


/**格式化日期**/
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};
function timeFormat(time){
	var t=new Date(time);    
	return t.format("MM-dd  hh:mm:ss");
}
function formatDate(time)   {    
	var t=new Date(time);    
	return t.format("yyyy-MM-dd  hh:mm:ss");
}   
/**end格式化日期**/    