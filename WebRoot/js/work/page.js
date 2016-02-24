/*
 * 翻页相关js函数
 */

function perPage(pn){
	$('#pageNum').val(pn);
	$('#searchForm').submit();
}

function nextPage(pn){
	$('#pageNum').val(pn);
	$('#searchForm').submit();
}
function firstPage(){
	$('#pageNum').val(1);
	$('#searchForm').submit();
}
function lastPage(pn){
	$('#pageNum').val(pn);
	$('#searchForm').submit();
}
function jumpPage(){
	var jp = $('#jumpPage').val();
	$('#pageNum').val(jp);
	$('#searchForm').submit();
}