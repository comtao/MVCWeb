package com.cn.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.model.News;
import com.cn.model.Task;
import com.cn.model.User;
import com.cn.service.LeaveService;
import com.cn.service.NewsService;
import com.cn.service.TaskService;
import com.cn.service.VoteService;
import com.cn.util.CommonUtil;


/**
 * 消息处理
 * @author owen
   @date 2014-10-29
 */
@Controller
@RequestMapping(value="/msg")
public class MsgController {
	
    private Logger logger = Logger.getLogger(MsgController.class);
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private VoteService voteService;
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping(value="/msgList.html")
	public String msgList(HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		logger.debug("mgsController  userName="+user.getRealName());
		List<Task> approvalTasks = taskService.approvalList(user.getId());
		List<News> newsList = newsService.newsGList(user.getDpId()+"",user.getId(), 10);//公告
		for (int i = newsList.size()-1; i >=0; i--) {
				if(newsList.get(i).getReadInt()>=1){
					newsList.remove(i);
				}
		}
		List leaveList = leaveService.getUnHandleList(user.getId());
		List<Map<String,Object>> voteList=voteService.getMsgVote(user.getId(),user.getDpId()+"");
		request.setAttribute("approvalTasks", approvalTasks);
		request.setAttribute("newsList", newsList);
		request.setAttribute("voteList", voteList);
		request.setAttribute("leaveList", leaveList);
		
		return "msgList";
	}
	
	@RequestMapping(value="/getMsgNum.html")
	@ResponseBody
	public Map<String,Object> getMsgNum(HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		int newMsg = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		if(user == null || user.getId() < 1){
			newMsg = 0;
		}else{
			newMsg += taskService.getUnHandleTask(user.getId());
			newMsg += leaveService.getUnHandleApply(user.getId());  //请假申请
			List<Map<String,Object>> voteList=voteService.getMsgVote(user.getId(),user.getDpId()+"");//投票
			List<News> newsList = newsService.newsGList(user.getDpId()+"",user.getId(),10);
			for (int i = newsList.size()-1; i >=0; i--) {
				if(newsList.get(i).getReadInt()>=1){
					newsList.remove(i);
				}
			}
			newMsg += newsList.size() ; //未读公告
			newMsg += voteList.size() ;//未投票
		}
		map.put("msgNum", newMsg);  //未处理总信息数
		return map;
	}
}
