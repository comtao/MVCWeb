package com.cn.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.model.Department;
import com.cn.model.Leave;
import com.cn.model.News;
import com.cn.model.User;
import com.cn.service.LeaveService;
import com.cn.service.UserService;
import com.cn.util.CommonUtil;
import com.cn.util.StringUtil;

@Controller
@RequestMapping(value="/Leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private UserService userService;
	
	private int SIZE = 20;
	/**
	 * 个人请假单列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/leaveList.html")
	public String  leaveList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user=(User) request.getSession().getAttribute("user");
		 List<Map<String, Object>> list = leaveService.getList(user.getId());
		 List<Map<String, Object>> map=leaveService.director(user.getDpId());
		 request.setAttribute("list", list);
		 if(map!=null){
			 request.setAttribute("map", map);
		 }
		 return "/personnel/leave";
	}
	/**
	 * 添加请假单
	 * @param leave
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addLeave.html")
	public String  addLeave(Leave leave,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user=(User) request.getSession().getAttribute("user");
		 List<Map<String, Object>> list = leaveService.getList(user.getId());
		 List<Map<String, Object>> map=leaveService.director(user.getDpId());
		 request.setAttribute("list", list);
		 if(map!=null){
			 request.setAttribute("map", map);
		 }
		leave.setUserId(user.getId());
		if(StringUtil.isEmpty(leave.getReason())){
			request.setAttribute("msg", "请输入请假缘由!");
			 return "/personnel/leave";
		}
		if(StringUtil.isEmpty(leave.getStart())){
			request.setAttribute("msg", "请输入请假开始时间!");
			return "/personnel/leave";
		}
		if(StringUtil.isEmpty(leave.getEnd())){
			request.setAttribute("msg", "请输入请假结束时间!");
			return "/personnel/leave";
		}
		if(leave.getLevaeStatus()==0){
			request.setAttribute("msg", "请选择请假类型!");
			return "/personnel/leave";
		}
		 int i =leaveService.getAdd(leave);
		 if(i>=1){
			 request.setAttribute("msg", "请假申请添加成功!");
			 return "/success";
		 }else{
			 request.setAttribute("msg", "请假申请添加失败!");
			 return "/error";
		 }
	}
	/**
	 * 请假单详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/searchLeave.html")
	@ResponseBody
	public Map<String, Object>  searchLeave(int id,int dpId,int userId,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> mapput = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map=leaveService.searchLeave(id,userId);
		String dpUserId=map.get("l_di_id").toString();
		String  dpUserName=leaveService.realname(dpUserId);
		mapput.put("map", map);
		mapput.put("dpUserName", dpUserName);
		 return mapput;
	}
	/**
	 * 员工考勤
	 * @param leave
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/attendance.html")
	public String  attendance(Leave leave,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int sum = 0;
		int userId=0;
		int count;
		List<Map<String, Object>> list = null;
		// 得到页数
		int page = leave.getPageNum();
		// 得到总条数 
		if(!StringUtil.isEmpty(leave.getRealName())){
			Map<String, Object> userMap=userService.realName(leave.getRealName());
			if(userMap!=null){
				userId=(Integer) userMap.get("id");
			}
		}else{
			userId=0;
		}
		sum = leaveService.getSum(userId,leave.getStart());
		// 总页数
		count = sum % SIZE == 0 ? sum / SIZE : sum / SIZE + 1;
		// 越界检查
		if (page > count)
			page = count;
		if (page < 1)
			page = 1;
		list =leaveService.getAttenList(page, SIZE,userId,leave.getStart());
		map.put("sum", sum);
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("userId", userId);
		map.put("count", count);
		// 首页 显示的数据
		request.setAttribute("start",leave.getStart());
		request.setAttribute("map", map);
		 return "/personnel/attendance";
	}
	
	/**
	 * 请假总记录
	 * @param leave
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/leaveRecord.html")
	public String  leaveRecord(Leave leave,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int sum = 0;
		int count;
		List<Map<String, Object>> list = null;
		// 得到页数
		int page = leave.getPageNum();
		// 得到总条数 
		if(!StringUtil.isEmpty(leave.getRealName())){
			Map<String, Object> userMap=userService.realName(leave.getRealName());
			if(userMap!=null){
				leave.setUserId((Integer) userMap.get("id"));
			}else{
				leave.setUserId(-1);
			}
		}else{
			leave.setUserId(0);
		}
		System.out.println(leave.getUserId()+"------------------------------");
		sum = leaveService.getSum(leave);
		// 总页数
		count = sum % SIZE == 0 ? sum / SIZE : sum / SIZE + 1;
		// 越界检查
		if (page > count)
			page = count;
		if (page < 1)
			page = 1;
		list =leaveService.getleaveRecord(page, SIZE,leave);
		System.out.println(sum +"++++"+page+"++++"+SIZE+"-----------------------------------");
		map.put("sum", sum);
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("userId", leave.getUserId());
		map.put("count", count);
		// 首页 显示的数据
		request.setAttribute("start",leave.getStart());
		request.setAttribute("map", map);
		 return "/personnel/leaveRecord";
	}
	
	
	
	/**
	 * 个人审批请假单列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/leaveApprovaList.html")
	public String  leaveApprovaList(Leave leave,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user=(User) request.getSession().getAttribute("user");
		 Map<String,Object> map = new HashMap<String,Object>();
			int sum = 0;
			int userId=user.getId();
			leave.setUserId(userId);
			int count;
			List<Map<String, Object>> list = null;
			// 得到页数
			int page = leave.getPageNum();
			// 得到总条数 
			sum = leaveService.getApprovaSum(userId,leave.getRealName());
			// 总页数
			count = sum % SIZE == 0 ? sum / SIZE : sum / SIZE + 1;
			// 越界检查
			if (page > count)
				page = count;
			if (page < 1)
				page = 1;
			list =leaveService.getApprovaList(page, SIZE,leave);
			map.put("sum", sum);
			map.put("page", page);
			map.put("size", SIZE);
			map.put("list", list);
			map.put("count", count);
			// 首页 显示的数据
			request.setAttribute("realName",leave.getRealName());
			request.setAttribute("map", map);
		 return "/personalOffice/leaveApprovaList";
	}
	/**
	 * 个人审批请假单列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/leaveApprovaEdit.html")
	public String  leaveApprovaEdit(Leave leave,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int i =leaveService.getApprovaEdit(leave);
		if(i>=1){
			 request.setAttribute("msg", "审批成功!");
			 return "/success";
		 }else{
			 request.setAttribute("msg", "审批失败!");
			 return "/error";
		 }
	}
	
	/**
	 * 请假详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/leaveDetail.html")
	public String leaveDetail(int id,int userId,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=leaveService.searchLeave(id,userId);
		request.setAttribute("leave", map);
		return "personalOffice/leaveDetail";
	}
}
