package com.cn.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.model.Department;
import com.cn.model.User;
import com.cn.model.Vote;
import com.cn.service.DpService;
import com.cn.service.VoteService;
import com.cn.util.CommonUtil;
import com.cn.util.StringUtil;

@Controller
@RequestMapping(value="/Vote")
public class VoteController {
	@Autowired
	private VoteService voteService;
	@Autowired
	private DpService dpService;
	
	@RequestMapping(value="/addVote.html")
	public String  addVote(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user= CommonUtil.getUser(request);
		List<Department> list=dpService.dpList();
		Department dp =new Department();
		int dpId = user.getDpId();
		 for (int i = 0; i < list.size(); i++) {
			if(dpId==11||dpId==0){
				request.setAttribute("dPlist", list);
			}else if(dpId==list.get(i).getId()){
				dp= list.get(i); 
				request.setAttribute("dp", dp);
			}
		}
		return "/vote/addVote";
	}
	
	/**
	 * 添加投票选项
	 * @param v
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/add.html")
	public String  add(Vote v ,String [] dpvalueId,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
		MultipartFile[] icon = v.getFile();
		String[] str=new String[icon.length];
		for (int i = 0; i < icon.length; i++) {
			if(!StringUtil.isEmpty(icon[i].getOriginalFilename())){
				String newname = StringUtil.getNum()+StringUtil.getSuffix(icon[i].getOriginalFilename());
				FileUtils.copyInputStreamToFile(icon[i].getInputStream(), new File(realPath, newname));
				str[i]="/icon/"+newname; 
			}else{
				str[i]=" "; 	
			}
		}	
		String  dp=StringUtil.ArrayString(dpvalueId);
		v.setDpId(dp);
		v.setIconFile(str);
		voteService.getAdd(v); 
		request.setAttribute("msg", "投票添加成功!");
		return "/success";
	}
	
	/**
	 *  查看所有投票
	 * @param v
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/listVote.html")
	public String  listVote(Vote v ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = CommonUtil.getUser(request);
		List<Map<String,Object>> list = voteService.getList(user.getDpId()+"");
		request.setAttribute("VoteList", list);
		return "/vote/listVote";
	}
	
	/**
	 * 主页显示五条投票
	 * @param v
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/ajaxVote.html")
	@ResponseBody
	public Map<String,Object>  ajaxVote(Vote v ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = voteService.getListAjax(v.getDpId());
		map.put("list", list);
		return map;
	}
	/**
	 * 投票
	 * @param v
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/vote.html")
	public String  vote(Vote v ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User u = (User) request.getSession().getAttribute("user");
		v.setUserID(u.getId());
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = voteService.getList(v.getId());
		List<Map<String,Object>> listcom = voteService.commentList(v.getId());
		int f=voteService.toupiaoUser(v);
			map.put("s", f); 
		List<Map<String,Object>> listU = voteService.getSum(v.getId());
		int sum = 0;
		for (int i = 0; i < listU.size(); i++) {
			sum+=Integer.parseInt(listU.get(i).get("sum").toString());
		}
		map.put("sum", sum);
		map.put("listU", listU);
		map.put("list", list);
		map.put("listcom", listcom);
		request.setAttribute("map", map);
		return "/vote/vote";
	}
	/**
	 * 
	 * @param v
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/toupiao.html")
	@ResponseBody
	public Map<String,Object>  toupiao(Vote v ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String, Object>();
		User u = (User) request.getSession().getAttribute("user");
		v.setUserID(u.getId());
		voteService.toupiao(v);
		List<Map<String,Object>> list = voteService.getSum(v.getId());
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum+=Integer.parseInt(list.get(i).get("sum").toString());
		}
		map.put("sum", sum);
		map.put("list", list);
		return map;
	}
	
	@RequestMapping(value="/comment.html")
	@ResponseBody
	public Map<String,Object>  comment(Vote v ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String, Object>();
		User u = (User) request.getSession().getAttribute("user");
		v.setUserID(u.getId());
		voteService.insertComment(v);
		List<Map<String,Object>> listcom = voteService.commentList(v.getId());
		map.put("list", listcom);
		return map;
	}
	
		
}
