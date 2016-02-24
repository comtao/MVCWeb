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
import com.cn.model.News;
import com.cn.model.User;
import com.cn.service.DpService;
import com.cn.service.NewsService;
import com.cn.util.CommonUtil;
import com.cn.util.StringUtil;

/**
 * 
 * @author ll
 *
 */
@Controller
@RequestMapping(value = "/News")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private DpService  dpService;
	public  static  int SIZE = 30;
	/**
	 * 公告
	 * @param dpId 部门编号 =0公司 !=0 其他部门
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/news.html")
	@ResponseBody
	public Map<String,Object> companyNews(int dpId,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		News news=newsService.news(dpId);
		List<News> list=newsService.newsList(user.getId(),dpId,5);
		map.put("news", news);
		map.put("newsList", list);
		return map;
	}
	
	/**
	 * 查看个人部门历史公告及公司历史公告
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/affiche.html")
	public String affiche(News news,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtil.isEmpty(news.getDpId())){
			news.setDpId("0");
		}
		int sum = 0;
		int count;
		List<News> list = null;
		// 得到页数
		int page = news.getPageNum();
		// 得到总条数 
		sum = newsService.getSum(news.getDpId());
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
		list = newsService.getAll(user.getId(),page, SIZE,news);
		map.put("sum", sum);
		map.put("dpId", news.getDpId());
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("count", count);
		// 首页 显示的数据
		request.setAttribute("datetime", news.getDatetime());
		request.setAttribute("map", map);
		return "/personalOffice/affiche";
	}
	/**
	 * 公告详情
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/seeNews.html")
	public String  lookNews(int id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User) request.getSession().getAttribute("user");
		News news=newsService.seeNews(id);
		 Map<String, Object>  map = new HashMap<String, Object>();;
			String [] iconFile=news.getDpId().split(",");
			for (int i = 0; i < iconFile.length; i++) {
				map.put(iconFile[i], newsService.selectDpName(iconFile[i]));
			}
		newsService.addRead(user.getId(), id);
		request.setAttribute("news", news);
		request.setAttribute("map", map);
		return "/news/seeAffiche";
	}
	/**
	 * 添加公告
	 * @param news
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addAffiche.html")
	public String  addAffiche(News news,String [] dpvalueId ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
		String dp=StringUtil.ArrayString(dpvalueId);
		if(StringUtil.isEmpty(news.getTitle())){	
			request.setAttribute("msg", "请输入公告标题!");
			return "/news/seeAffiche";
		}
		if(StringUtil.isEmpty(dp)){
			request.setAttribute("msg", "请选择公告部门!");
			return "/news/seeAffiche";
		}else{
			news.setDpId(dp);
		}
		if(StringUtil.isEmpty(news.getContent())){
			request.setAttribute("msg", "请选择标题内容!");
			return "/news/seeAffiche";
		}
		 int i=newsService.getAdd(news);
		 if(i>=1){
			 request.setAttribute("msg", "公告发布成功!");
			 return "/success";
		 }else{
			 request.setAttribute("msg", "公告发布失败!");
			 return "/error";
		 }
		
	}
	
	/**
	 * 查看所有部门历史公告及公司历史公告
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/historyNews.html")
	public String historyNews(News news,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapDpNameKey = new HashMap<String,Object>();
		int sum = 0;
		int count;
		List<News> list = null;
		// 得到页数
		int page = news.getPageNum();
		// 得到总条数 
		sum = newsService.getSum(news.getDpId());
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
		list = newsService.getAllList(page, SIZE,news);
		map.put("sum", sum);
		map.put("dpId", news.getDpId());
		map.put("page", page);
		map.put("size", SIZE);
		map.put("list", list);
		map.put("count", count);
		List<Department> dplist=dpService.dpList();
		String dpname="";
		for (int i =0;i<list.size();i++) {
			String [] dpIDd=list.get(i).getDpId().split(","); 
			 for (int j = 0; j < dpIDd.length; j++) {
				 dpname+= newsService.selectDpName(dpIDd[j])+"  ";
				}
			 mapDpNameKey.put(""+list.get(i).getId(), dpname);
			 dpname="";
		}
		request.setAttribute("dplist",dplist);
		// 首页 显示的数据
		request.setAttribute("datetime", news.getDatetime());
		request.setAttribute("mapDpNameKey", mapDpNameKey);
		request.setAttribute("map", map);
		return "/news/historyNews";
	}
	/**
	 * 公告
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addSeeNews.html")
	public String  addSeeNews(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
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
		return "/news/addAffiche";
	}
}
