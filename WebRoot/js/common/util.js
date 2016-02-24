function setRequest(type,url,data,callback){
	$.ajax({
	      type:type,
	      url: url,
	      dataType: "json",
	      data:data,
	      success: callback
    });
}
function getAjax(url,data,callback){
	setRequest("POST",url,data,callback);
}


