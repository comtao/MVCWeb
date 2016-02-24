/* 消息框 */
function alterMsg(msg) {
	if (msg && msg != '') {
		alert(msg);
	}
}
/**
 * 公告
 * 
 * @param {}
 *            pdId 0公司公告,!=0 其他部门公告
 * @param {}
 *            path
 */
function news(dpId, path) {
	$.post(path + "/News/news.html", {
				dpId : dpId
			}, function(data) {
				$("#title").html(data.news.title);
				$("#content").html(data.news.content);
				$("#itime").html(data.news.shortTime);
				var str = "";
				var list = data.newsList;
				for (var i in list) {
					if (list[i].readInt < 1) {
						str += "<tr style='cursor:pointer;' onclick=seeNews('"
								+ list[i].id
								+ "');  ><td  >"
								+ list[i].id + "<img src='/images/hot.gif'   ></img></td><td>" + list[i].title
								+ "</td><td>" + list[i].shortTime
								+ "</td></tr>";
					} else {
						str += "<tr style='cursor:pointer;' onclick=seeNews('"
								+ list[i].id + "');  ><td  > " + list[i].id
								+ "</td><td>" + list[i].title + "</td><td>"
								+ list[i].shortTime + "</td></tr>";
					}
				}
				$("#newsList").html(str);
				var href = '<a href="' + path + '/News/affiche.html?dpId='
						+ dpId + '">更多...</a>';
				$("#newsHref").html(href);
			});
}
function seeNews(id) {
	window.location.href = "/News/seeNews.html?id=" + id;
}

function voteAjax(dpid,path) {
	$.post(path + "/Vote/ajaxVote.html", {dpId:dpid},function(data) {
				var str = "";
				var list = data.list;
				for (var i in list) {
					str += "<tr style='cursor:pointer;' onclick=seeVote('"
							+ list[i].vid + "','"+ list[i].id + "');  ><td  > " + list[i].id
							+ "</td><td>" + list[i].title + "</td><td>" + list[i].start_time + "</td></tr>";
				}
				$("#voteList").html(str);
				var href = '<a href="' + path
						+ '/Vote/listVote.html">更多...</a>';
				$("#voteHref").html(href);
			});
}

function seeVote(vid,id) {
	window.location.href = "/Vote/vote.html?id="+vid+"&optionsId="+id+"";
}

function taskAjax(path) {
	$.post(path + "/task/taskAjax.html", function(data) {
				var str = "";
				var list = data.list;
				for (var i in list) {
					str += "<tr style='cursor:pointer;' onclick=seeTask('"
							+ list[i].id + "');  ><td  > " + list[i].id
							+ "</td><td>" + list[i].title + "</td><td>" + list[i].shortTime + "</td></tr>";
				}
				$("#taskList").html(str);
				var href = '<a href="' + path
						+ '/task/list.html">更多...</a>';
				$("#taskHref").html(href);
			});
}

function seeTask(id) {
	window.location.href = "/task/allocating.html?id="+id;
}