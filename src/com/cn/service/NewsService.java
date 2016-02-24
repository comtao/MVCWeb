package com.cn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.NewsDao;
import com.cn.model.News;
/**
 * 
 * @author ll
 *
 */
@Service
public class NewsService {
	@Autowired
	private NewsDao newsDao;
	/**最新公告
	 * @param dpID
	 * @return
	 */
	public News news(int dpId){
		return newsDao.news(dpId);
	}
	/**
	 * 单个公告
	 * @param dpID
	 * @return
	 */
	public News seeNews(int id){
		return newsDao.seeNews(id);
	}
	/**
	 * 公告列表显示5条
	 * @param dpID
	 * @return
	 */
	public List<News> newsList(int userId,int dpId,int pageSezi){
		return newsDao.newsList(userId,dpId,pageSezi);
	}
	/**
	 * 公司公告列表显示5条
	 * @param dpID
	 * @return
	 */
	public List<News> newsGList(String pdId,int userId ,int pageSezi){
		return newsDao.newsGList(pdId,userId ,pageSezi);
	}
	
	
	/**
	 * 条数
	 * @param dpId
	 * @return
	 */
	public int getSum(String dpId){
		return newsDao.getSum(dpId);
	}
	public List<News> getAll(int userId,int page, int size,News news){
		return newsDao.getAll(userId,page, size, news);
	}
	
	public int getAdd(News news){
		return newsDao.getAdd(news);
	}
	public List<News> getAllList(int page, int size,News news){
		return newsDao.getAllList(page, size, news);
	}
	
	 
	public void addRead(int userId,int notice){
		newsDao.execute(NewsDao.addRead, new Object[]{userId,notice});
	}
	public String selectDpName(String id){
		return newsDao.queryForString(NewsDao.selectDpName, new Object[]{id});
	}
}
