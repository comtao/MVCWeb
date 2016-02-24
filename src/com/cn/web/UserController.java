package com.cn.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cn.interceptor.RepeatSubmission;
import com.cn.model.Department;
import com.cn.model.Role;
import com.cn.model.User;
import com.cn.service.DpService;
import com.cn.service.RoleService;
import com.cn.service.UserService;
import com.cn.util.CommonUtil;
import com.cn.util.StringUtil;
/**
 * 员工
 * @author ll
 *
 */
@Controller 
@RequestMapping(value="/User")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService ;
	@Autowired
	private DpService dpService;
	private Logger logger = Logger.getLogger(UserController.class);
	private int SIZE=20;
	
	/**
	 * 根据部门id查找  部门人员
	 * @param request
	 */
	@RequestMapping(value="/getUserByDpId.html")
	@ResponseBody
    public Map<String,Object> getUserByDpId(int dpId,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> list = userService.getUsersByDpId(dpId);
		if(list != null && list.size()>0){
			map.put("status", 0);
			map.put("options", userService.getUsersCheckBoxs(list));
		}else{
			map.put("status", 1);
		}
		return map;
	}
	
	
	/**
	 * 根据部门id查找  部门领导
	 * @param dpId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getLeaderByDpid.html")
	@ResponseBody
	public Map<String,Object> getLeaderByDpid(int dpId,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> list = userService.getLeaderByDpId(dpId);
		if(list != null && list.size()>0){
			map.put("status", 0);
			map.put("options", userService.getUsersOptions(list));
		}else{
			map.put("status", 1);
		}
		return map;
	}
	
	/**
	 * 用户注销 退出
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value="/logout.html")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		response.sendRedirect("/");
		return;
	}
	
	/**
	 *个人信息 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/personalInfo.html")
	@RepeatSubmission(needSaveToken = true)
	public String  personalInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User userId = CommonUtil.getUser(request);	
		User user=userService.personalInfo(userId.getId());
		request.setAttribute("personalInfo", user);
		return "/personnel/personalInfo";
	}
	
	@RequestMapping(value="/editUser.html")
	public String  editUser(User user,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/**
		 * 上传文件路径
		 */
		String path = request.getSession().getServletContext().getContextPath();
		StringBuffer showpath = new StringBuffer(path.trim());
		String realPath = request.getSession().getServletContext().getRealPath("/icon");
		File uploadDir = new File(realPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		showpath.append("/icon");
		MultipartFile icon = user.getIconFile();
		if(StringUtil.isEmpty(icon.getOriginalFilename())){
			user.setIcon("");
		}else{
			String newname = StringUtil.getNum()+StringUtil.getSuffix(icon.getOriginalFilename());
			FileUtils.copyInputStreamToFile(icon.getInputStream(), new File(realPath, newname));
			user.setIcon("/icon/"+newname);
		}
		int i=userService.editUser(user);
		if(i>=1){
			 request.setAttribute("msg", "信息修改成功!");
			 return "/success";
		 }else{
			 request.setAttribute("msg", "信息修改失败!");
			 return "/error";
		 }
	}
	
	
	/**
	 * 部门 职位
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addPersonalInfo.html")
	public String  addPersonalInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 List<Department> dpList=dpService.dpList();
		 List<Role> roleList=roleService.list();
		 request.setAttribute("dpList", dpList);
		 request.setAttribute("roleList", roleList);
		return "/personnel/addPersonalInfo";
	}
	/**
	 * 帐号已存在
	 * @param repName
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/repName.html")
	@ResponseBody
	public boolean  repName(String repName,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		return userService.repName(repName);
	}
	/**
	 * 新增员工
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addUser.html")
	@RepeatSubmission(needSaveToken = true)
	public String  addUser(User user,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if(StringUtil.isEmpty(user.getUserName())){
			request.setAttribute("msg", "登陆账号不能为空!");
			return "/personnel/addPersonalInfo";
		}
		if(StringUtil.isEmpty(user.getPassWord())){
			request.setAttribute("msg", "登陆密码不能为空!");
			return "/personnel/addPersonalInfo";
		}
		if(StringUtil.isEmpty(user.getRealName())){
			request.setAttribute("msg", "真实姓名不能为空!");
			return "/personnel/addPersonalInfo";
		}
		if(user.getDpId()==-1){
			request.setAttribute("msg", "部门不能为空!");
			return "/personnel/addPersonalInfo";
		}
		if(user.getRoleId()==-1){
			request.setAttribute("msg", "职位不能为空!");
			return "/personnel/addPersonalInfo";
		}
		int i=userService.getAdd(user);
		if(i>=1){
			 request.setAttribute("msg", "新增员工成功!");
			 return "/success";
		 }else{
			 request.setAttribute("msg", "新增员工失败!");
			 return "/error";
		 }
	}
	/**
	 * 员工列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/userList.html")
	public String  userList(User user,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int sum = 0;
		int count;
		List<User> list = null;
		// 得到页数
		int page = user.getPageNum();
		// 得到总条数 
		sum = userService.getSum(user);
		// 总页数
		count = sum % SIZE == 0 ? sum / SIZE : sum / SIZE + 1;
		// 越界检查
		if (page > count)
			page = count;
		if (page < 1)
			page = 1;
		/*Date date = new Date();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		timestring = format2.format(date);*/
		// 得到需要展示的数据  
		 list=userService.getList(page, SIZE,user);
		map.put("sum", sum);
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("count", count);
		// 首页 显示的数据
		request.setAttribute("lTime", user.getlTime());
		request.setAttribute("dpName", user.getDpName());
		request.setAttribute("roleName", user.getRoleName());
		request.setAttribute("map", map);
		return  "/personnel/users";
	}
	/**
	 * 修改查看员工信息
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/editSeeUser.html")
	public String  editSeeUser(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user=userService.personalInfo(id);
		 List<Department> dpList=dpService.dpList();
		 List<Role> roleList=roleService.list();
		 request.setAttribute("dpList", dpList);
		 request.setAttribute("roleList", roleList);
		 request.setAttribute("personalInfo", user);
		return  "/personnel/editPersonalInfo";
	}
	/**
	 * 修改员工信息
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/editPersonalInfo.html")
	public String  editPersonalInfo(User user,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int i=userService.getEdit(user);
		System.out.println(i+"------------------------------------");
		if(i>=1){
			 request.setAttribute("msg", "修改员工成功!");
			 return  "/success";
		 }else{
			 request.setAttribute("msg", "修改员工失败!");
			 return "/error";
		 }
		
	}
	/**
	 * 重置密码
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/relpassword.html")
	public String  relpassword(String password,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		int i=0;
		if(!StringUtil.isEmpty(password)){
			i=userService.getRel(password,user.getId());
		}
		if(i>=1){
			 request.setAttribute("msg", "密码修改成功!");
			 return  "/success";
		 }else{
			 request.setAttribute("msg", "密码修改失败!");
			 return "/error";
		 }
		
	}
	/**
	 * 操作界面
	 * @return
	 */
	@RequestMapping(value="/index.html")
	public String main(){
		return "index";
	}
	/**
	 * 通讯录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addressList.html")
	public String  addressList(User user,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int sum = 0;
		int count;
		List<User> list = null;
		// 得到页数
		int page = user.getPageNum();
		// 得到总条数 
		sum = userService.addressListSum(user);
		// 总页数
		count = sum % SIZE == 0 ? sum / SIZE : sum / SIZE + 1;
		// 越界检查
		if (page > count)
			page = count;
		if (page < 1)
			page = 1;
		/*Date date = new Date();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		timestring = format2.format(date);*/
		// 得到需要展示的数据  
		 list=userService.addressList(page, SIZE,user);
		map.put("sum", sum);
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("count", count);
		List<Department> dplist=dpService.dpList();
		request.setAttribute("dplist",dplist);
		// 首页 显示的数据
		request.setAttribute("post", user.getPost());
		request.setAttribute("realName", user.getRealName());
		request.setAttribute("dpName", user.getDpName());
		request.setAttribute("map", map);
		return "/addressList";
	}
	
	
	@RequestMapping(value="rePsw.html")
	public String rePsw(){
		return "system/relpassword";
	}
}
