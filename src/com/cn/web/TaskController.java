package com.cn.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.model.Task;
import com.cn.model.User;
import com.cn.service.TaskService;
import com.cn.util.CommonUtil;
import com.cn.util.Constants;

/**
 * 任务、审批
 * @author owen
   @date 2014-10-17
 */

@Controller
@RequestMapping(value="/task")
public class TaskController {
	private Logger logger = Logger.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	
	/**
	 * 个人待办任务列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list.html")
	public String taskList(Task task,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		logger.debug("user's name="+user.getRealName());
		
		int total = taskService.taskTotal(user.getId());
		int pageTotal = Constants.getPageTotal(total, task.getPageSize());
		task.setPageNum(Constants.handlePageNum(task.getPageNum(),pageTotal));
		int start = (task.getPageNum()-1)*task.getPageSize();
		
		task.setUserId(user.getId());
		List<Task> list = taskService.list(task,start);
		request.setAttribute("list", list);
		request.setAttribute("task", task);
		request.setAttribute("pageTotal", pageTotal);
		return "personalOffice/task";
	}
	
	
	/**
	 * 已完成任务列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/finishList.html")
	public String finishTask(Task task,HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		int total = taskService.finishTotal(user.getId());
		int pageTotal = Constants.getPageTotal(total, task.getPageSize());
		task.setPageNum(Constants.handlePageNum(task.getPageNum(),pageTotal));
		int start = (task.getPageNum()-1)*task.getPageSize();
		task.setUserId(user.getId());
		List<Task> list = taskService.finishList(task,start);
		request.setAttribute("list", list);
		request.setAttribute("task", task);
		request.setAttribute("pageTotal", pageTotal);
		return "personalOffice/finishList";
	}
	
	/**
	 *删除错误任务
	 */
	@RequestMapping(value="/delTask.html")
	public String delTask(int id,HttpServletRequest request){
		if(taskService.delTask(id)){
			request.setAttribute("msg", "任务删除成功");
			return "success";
		}
		request.setAttribute("msg", "任务删除失败");
		return "error";
	}
	
	/**
	 * 个人待办任务列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/taskAjax.html")
	@ResponseBody
	public Map<String, Object> taskAjax(Task task,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User)request.getSession().getAttribute("user");
		logger.debug("user's name="+user.getRealName());
		
		task.setUserId(user.getId());
		task.setPageSize(5);
		List<Task> list = taskService.list(task,0);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 增加新的任务申请
	 * 可选择提交给谁审批 ，最后一个审批人把任务分解发送给实施人
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add.html")
	public String addTask(HttpServletRequest res){
		return "personalOffice/addTask";
	}
	
	@RequestMapping(value="/addSave.html")
	public String addSaveTask(Task task,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			return "error";
		}
		taskService.initiatorData(task,user);
		task.setStatus(0);
		taskService.handleAttachment(task,request);
		if(taskService.addTask(task)){
			if(user.getId() == task.getReceiveUserId()){  //部门领导人自己发起的任务
				task.setFlag(1);
				taskService.initiatorData(task,user);
				taskService.addApproval(task);
				
				List list = taskService.getFlowList(task.getTaskId());
				taskService.initTaskFlag(list);
				List comment = taskService.taskComment(task.getTaskId());
				Task t1 = taskService.taskDetail(task.getTaskId());
				boolean isAllot= taskService.isAllot(task.getTaskId(),user.getId());
				request.setAttribute("isAllot",isAllot);
				request.setAttribute("tDetail", t1);
				request.setAttribute("list", list);
				request.setAttribute("comment",comment);
				request.setAttribute("taskId", task.getTaskId());
				return "personalOffice/allocating";
			}else{
				request.setAttribute("msg", "任务提交成功！");
			}
		}else{
			request.setAttribute("msg", "任务提交失败!");
		}
		return "success";
	}
	
	/**
	 * 已完成任务列表
	 */
	@RequestMapping(value="/sbmTaskList.html")
	public String submitTaskList(){
		return "personalOffice/sbmTaskList";
	}
	
	
	/**
	 * 待审批列表
	 */
	@RequestMapping(value="approvalList.html")
	public String approvalList(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		List<Task> list = taskService.approvalList(user.getId());
		request.setAttribute("list", list);
		return "personalOffice/approvalList";
	}
	
	/**
	 * 审批页面
	 */
	@RequestMapping(value="approval.html")
	public String approval(int id,HttpServletRequest request){
		logger.debug("approval  id = "+id);
		Task task = taskService.getTaskById(id);
		request.setAttribute("task", task);
		return "personalOffice/approval";
	}
	
	/**
	 * 审批结果
	 */
	@RequestMapping(value="approvalSave.html")
	public String approvalSave(Task task,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			return "error";
		}
		taskService.initiatorData(task,user);
		if(taskService.addApproval(task)){
			request.setAttribute("msg", "审批结果提交成功！");
		}else{
			request.setAttribute("msg", "审批结果提交不失败！");
		}
		if(task.getFlag() == 1){  //下派任务
			List list = taskService.getFlowList(task.getTaskId());
			taskService.initTaskFlag(list);
			List comment = taskService.taskComment(task.getTaskId());
			Task t1 = taskService.taskDetail(task.getTaskId());
			boolean isAllot= taskService.isAllot(task.getTaskId(),user.getId());
			request.setAttribute("isAllot",isAllot);
			request.setAttribute("tDetail", t1);
			request.setAttribute("list", list);
			request.setAttribute("comment",comment);
			request.setAttribute("taskId", task.getTaskId());
			return "personalOffice/allocating";
		}else{
			List<Task> list = taskService.approvalList(user.getId());
			request.setAttribute("list", list);
			return "personalOffice/approvalList";
		}
	}
	
	/**
	 * 任务分配查看
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="allocating.html")
	public String allocating(int id,HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		List list = taskService.getFlowList(id);
		taskService.initTaskFlag(list);
		Task t = taskService.taskDetail(id);
		List comment = taskService.taskComment(id);
		boolean  isAllot= taskService.isAllot(id,user.getId());
		request.setAttribute("tDetail", t);
		request.setAttribute("list", list);
		request.setAttribute("taskId", id);
		request.setAttribute("comment",comment);
		request.setAttribute("isAllot",isAllot);
		return "personalOffice/allocating";
	}
	
	/**
	 * 分任务添加
	 * @param task
	 * @param request
	 * @return
	 */
	@RequestMapping(value="allocatingAdd.html")
	public String allocatingAdd(Task task,HttpServletRequest request){
		if(taskService.addAllocating(task)){
			request.setAttribute("msg", "分任务添加成功！");
		}else{
			request.setAttribute("msg", "分任务添加失败！");
		}
		User user = CommonUtil.getUser(request);
		List list = taskService.getFlowList(task.getTaskId());
		taskService.initTaskFlag(list);
		Task t = taskService.taskDetail(task.getTaskId());
		List comment = taskService.taskComment(task.getTaskId());
		boolean  isAllot= taskService.isAllot(task.getTaskId(),user.getId());
		request.setAttribute("tDetail", t);
		request.setAttribute("list", list);
		request.setAttribute("taskId", task.getTaskId());
		request.setAttribute("comment",comment);
		request.setAttribute("isAllot",isAllot);
		return "personalOffice/allocating";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="allocatingEditSave.html")
	public String allocatingEditSave(Task task,HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		taskService.allocatingEditSave(task);
		List list = taskService.getFlowList(task.getTaskId());
		taskService.initTaskFlag(list);
		List comment = taskService.taskComment(task.getTaskId());
		Task t1 = taskService.taskDetail(task.getTaskId());
		boolean isAllot= taskService.isAllot(task.getTaskId(),user.getId());
		request.setAttribute("isAllot",isAllot);
		request.setAttribute("tDetail", t1);
		request.setAttribute("list", list);
		request.setAttribute("comment",comment);
		request.setAttribute("taskId", task.getId());
		request.setAttribute("list", list);
		return "personalOffice/allocating";
	}
	
	/**
	 * 完成某个分任务
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/taskExecute.html")
	@ResponseBody
	public Map<String,Object> taskExecute(int id,String userId,HttpServletRequest request){
		logger.debug("taskExecute.html    id="+id+"   userId="+userId);
		User user = CommonUtil.getUser(request);
		Map map = new HashMap<String,Object>();
		List userIds = Arrays.asList(userId.split(","));
		if(userIds.contains(user.getId()+"") && id > 0 && taskService.taskExecute(id,userId)){
			map.put("status", 0);
			map.put("msg", "提交任务成功！");
		}else{
			map.put("status", 1);
			map.put("msg", "提交任务失败！");
		}
		return map;
	}
	
	/**
	 * 是否是某个任务参与者
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/isActor.html")
	@ResponseBody
	public Map<String,Object> isActor(int id,String userId,HttpServletRequest request){
		Map<String,Object> map = new HashMap();
		User user = CommonUtil.getUser(request);
		List userIds = Arrays.asList(userId.split(","));
		if(userIds.contains(user.getId()+"")){
			map.put("status", 0);
		}else{
			map.put("status", 1);
		}
		return map;
	}
	
	/**
	 * 删除某个分任务
	 * @param id
	 * @param res
	 * @return
	 */
	@RequestMapping(value="/deleteTask.html")
	@ResponseBody
	public Map<String,Object> deleteTask(int id,int taskId,HttpServletRequest res){
		Map<String,Object> map = new HashMap();
		User user = CommonUtil.getUser(res);
		String userId =  taskService.getUserIds(id, taskId);
		List userIds = Arrays.asList(userId.split(","));
		if(userIds.contains(user.getId()+"")){
			if(taskService.deleteTask(id)){
				map.put("status", 0);
			}else{
				map.put("status", 1);
			}
		}else{
			map.put("status", 1);
		}
		return map;
	}
	
	
	/**
	 * 分任务开始执行
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/taskStart.html")
	@ResponseBody
	public Map<String,Object> taskStart(int id,String userId,HttpServletRequest request){
		User user = CommonUtil.getUser(request);
		Map map = new HashMap<String,Object>();
		List userIds = Arrays.asList(userId.split(","));
		if(userIds.contains(user.getId()+"") && id > 0 && taskService.taskStart(id,userId)){
			map.put("status", 0);
			map.put("msg", "任务开启成功！");
		}else{
			map.put("status", 1);
			map.put("msg", "任务开启失败！");
		}
		return map;
	}
}
