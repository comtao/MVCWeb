/*消息框*/
function alterMsg(msg){
	if(msg && msg != ''){
		alert(msg);
	}
}

function login(){
	var user=$("#username").val();
	var pwd=$("#password").val();
	if (user == '' || pwd == '') {
		alert('帐号或密码不能为空');
		return false;
	}
	return true;
}