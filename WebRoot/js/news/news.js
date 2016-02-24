function addAffiche(){
		var title=$("#title").val();
		var dpId=$("#dpId").val();
		var content=$("#content").val().trim();
		
		if(title.length < 1){
			alert("请输入公告标题!");
			return false;
		}
		if(dpId==-1){
			alert("请选择公告部门!");
			return false;
		}
		if(content.length <1){
			alert("请输入公告内容!");
			return false;
		}
		return true;
}

/*消息框*/
function alterMsg(msg){
	if(msg && msg != ''){
		alert(msg);
	}
}


/**
 * 反选
 */
function inverse(){
	$("input[name='dpvalueId']").each(function(){
		  $(this).attr("checked",!this.checked);   
	});
	//console.log("asd:="+$("input[name='dpvalueId']:first").attr("checked"));
}

/**
 * 全选
 */
function selectAll(){
	 $("input[name='dpvalueId']").each(function(){
		  $(this).attr("checked",true);
	 });  
}

/**
 * 取消选择
 */
function cancel(){
	$("input[name='dpvalueId']").each(function(){
		 $(this).attr("checked",false);
    }); 
}


