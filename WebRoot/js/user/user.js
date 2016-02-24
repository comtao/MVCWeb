/*消息框*/
function alterMsg(msg){
	if(msg && msg != ''){
		alert(msg);
	}
}
function addUser(){
	if(!userNameVerify){
		return false;
	}
	if(!passWordVerify){
		return false;
	}
	if(!realNameVerify){
		return false;
	}
	if(!dpIdVerify){
		return false;
	}
	if(!postVerify){
		return false;
	}
	return true;
}

function userNameFocus(){
	$("#userNameInfo").html( "<font color='red'>用户登录帐号!</font>");
}
function userNameVerify(path){
	var repName=$("#userName").val();
	if(repName.length < 1){
		$("#userNameInfo").html( "<font color='red'>请填写帐号!</font>");
		return false;
	}else{
		$.post(path+'/User/repName.html',{repName:repName},function(data){
			if(data){
				$("#userNameInfo").html( "<font color='green'>通过!</font>");
				return true;
			}else{
				$("#userNameInfo").html( "<font color='red'>帐号已存在!</font>");
				return false;
			}
		}) ;
	}
	return true;
}
function passWordFocus(){
	$("#passWordInfo").html( "<font color='red'>用户登录密码!</font>");
}
function passWordVerify(){
	var passWord=$("#passWord").val();
	if(passWord.length < 1){
		$("#passWordInfo").html( "<font color='red'>请填写密码!</font>");
		return false;
	}else{
		$("#passWordInfo").html( "<font color='green'>通过!</font>");
		return true;
	}
}
function realNameFocus(){
	$("#realNameInfo").html( "<font color='red'>员工真实姓名!</font>");
}
function realNameVerify(){
	var realName=$("#realName").val();
	if(realName.length < 1){
		$("#realNameInfo").html( "<font color='red'>请填写员工姓名!</font>");
		return false;
	} else{
		$("#realNameInfo").html( "<font color='green'>通过!</font>");
		return true;
	}
}
function dpIdVerify(){
	var dpId=$("#dpId").val();
	if(dpId==-1){
		$("#dpIdInfo").html( "<font color='red'>请选择部门!</font>");
		return false;
	}
	return true;
}
function postFocus(){
	$("#postInfo").html( "<font color='red'>员工职位名称!</font>");
}
function postVerify(){
	var post=$("#post").val();
	if(post.length < 1){
		$("#postInfo").html( "<font color='red'>请输入职位名称!</font>");
		return false;
	}else{
		$("#postInfo").html( "<font color='green'>通过!</font>");
		return true;
	}
}


function repPassword(){
	var password=$("#passWord").val();
	var reppassword=$("#repPassWord").val();
	if(password!=reppassword){
		$("#passInfo").html("<font color=red>两次密码不一致</font>");
		return false ;
	}
	$("#passInfo").html("<font color=green>通过</font>");
	return true ;
}
