package com.cn.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.model.Department;
import com.cn.service.DpService;
import com.cn.util.StringUtil;

/**
 * 部门
 * @author ll
 *
 */
@Controller
@RequestMapping(value="/dp")
public class DpController {
	
	@Autowired
	private DpService dpService;
	
	/**
	 * 所有部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/dpList.html")
	public String dpList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		List<Department> list=dpService.dpList();
		request.setAttribute("list",list);
		return "/personnel/department";
	}
	/**
	 * 部门是否已存在
	 * @param repName
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/dpRep.html")
	@ResponseBody
	public int dpRep(String repName,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		 int i = dpService.repName(repName);
		return i;
		
	}
	/**
	 * 添加部门
	 * @param dp
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addDp.html")
	public String addDp(Department dp,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			int i=0;
			if(!StringUtil.isEmpty(dp.getDpName())){
				i=	dpService.addDp(dp);
			}else{
				List<Department> list=dpService.dpList();
				request.setAttribute("list",list);
				request.setAttribute("msg", "部门名称不能为空!");
				return "/personnel/department";
			}
			if(i>=1){
				 request.setAttribute("msg", "部门添加成功!");
				 return "/success";
			 }else{
				 request.setAttribute("msg", "部门添加失败!");
				 return "/error";
			 }
	}
	/**
	 * 修改部门
	 * @param dp
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/editDp.html")
	public String editDp(Department dp,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			int i=0;
			if(!StringUtil.isEmpty(dp.getDpName())){
				i=	dpService.editDp(dp);
			}else{
				List<Department> list=dpService.dpList();
				request.setAttribute("list",list);
				request.setAttribute("msg", "部门名称不能为空!");
				return "/personnel/department";
			}
			if(i>=1){
				 request.setAttribute("msg", "部门修改成功!");
				 return "/success";
			 }else{
				 request.setAttribute("msg", "部门修改失败!");
				 return "/error";
			 }
	}
	
	/**
	 * 所有部门
	 * @return
	 */
	@RequestMapping(value="/getDpOptions.html")
	@ResponseBody
	public Map<String,Object> getDpOptions(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Department> list=dpService.dpList();
		map.put("dps", list);
		return map;
	}
}
