

//getAjax(url,data,callback)

function changeDp(path){
	getAjax(path+"/User/getUserByDpId.html",{dpId:$("#receiveDpId").val()},function(data){
		if(data.status == 0){
			$("#receiveUserId").html(data.options);
		}else{
			$("#receiveUserId").html("<option value='-1'>--error--</option>");
		}
	});
}


function addFlowCheck(){
	var c = $(document.getElementsByName("describe")).val();
	if(c == ''){
		alert("任务描述不能为空");
		return false;
	}
	var i='';
	$("input[name1='receiveUserId']").each(function(){
	  if ('checked' == $(this).attr("checked")) {
		i+=$(this).attr('value')+',';
	  }
	});
	if(i!=''){
		i=i.substring(0, i.length-1);
		$("#rUserId").val(i);
	}else{
		alert("至少选择一个实施人");
		return false;
	}
	return true;
}


function receiveDp(path){
	getAjax(path+"/User/getLeaderByDpid.html",{dpId:$("#receiveDpId").val()},function(data){
		if(data.status == 0){
			$("#receiveUserId").html(data.options);
		}else{
			$("#receiveUserId").html("<option value='-1'>--error--</option>");
		}
	});
}

function approvalShift(){
	var r = $("#isShift").attr("checked");
	if(r == 'checked'){
		$("#dpIdTr").show();
		$("#userIdTr").show();
		$("#submitType").val("提交审核");
		$("#flag").val(0);
	}else{
		$("#dpIdTr").hide();
		$("#userIdTr").hide();
		$("#submitType").val("下派任务");
		$("#flag").val(1);
	}
}

function refuseTask(){
	$("#taskStatus").val(-1);
	$("#flag").val(0);
	$("#form_data").submit();
}

/*function taskExcute(path,id,userId){ 
    if(window.confirm('你确定已完成此任务？！')){
    	getAjax(path+"/task/taskExecute.html",{id:id,userId:userId},function(data){
    		alert(data.msg);
    		if(data.status==0){
    			location.reload(); 
    		}
    	});
    }
    
    if(window.confirm('确定下派此任务？！')){
    	getAjax(path+"/task/isActor.html",{id:id,userId:userId},function(data){
    		if(data.status==1){
    			alert("分派任务失败！");
    		}else{
    			$("#id").val(id);
    	    	$("#aEditDiv").click();
    		}
    	});
    }
}*/

/**
 * 开启任务
 * @param path
 * @param id
 * @param userId
 */
function startTask(path,id,userId){
	getAjax(path+"/task/taskStart.html",{id:id,userId:userId},function(data){
		alert(data.msg);
		if(data.status==0){
			location.reload(); 
		}
	});
}

/**
 * 完成分任务
 * @param path
 * @param id
 * @param userId
 */
function finshTask(path,id,userId){
	if(window.confirm('你确定已完成此任务？!')){
		getAjax(path+"/task/taskExecute.html",{id:id,userId:userId},function(data){
			alert(data.msg);
			if(data.status==0){
				location.reload(); 
			}
		});
	}
}


/**
 * 下派分任务
 * @param path
 * @param id
 * @param userId
 */
function handTask(path,id,userId){
	getAjax(path+"/task/isActor.html",{id:id,userId:userId},function(data){
		if(data.status==1){
			alert("分派任务失败！");
		}else{
			$("#id").val(id);
	    	$("#aEditDiv").click();
		}
	});
}

/**
 * 删除分任务
 * @param path
 * @param id
 */
function deleteTask(path,id,taskId){
	if(window.confirm('你删除此任务？！')){
		getAjax(path+"/task/deleteTask.html",{id:id,taskId:taskId},function(data){
			if(data.status==0){
				location.reload(); 
			}else{
				alert("分任务删除失败！");
			}
		});
	}
}

function initDpOptions(path){
	getAjax(path+"/dp/getDpOptions.html",{},function(data){
		var str = "<option value='-1'>--请选择部门--</option>";
		for(var i in data.dps){
			str += "<option value='"+data.dps[i].id+"'>"+data.dps[i].dpName+"</option>";
		}
		$("#receiveDpId").html(str);
	});
}
















