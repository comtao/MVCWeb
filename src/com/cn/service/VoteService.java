package com.cn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.VoteDao;
import com.cn.model.Vote;

@Service
public class VoteService {
	
	@Autowired
	private VoteDao voteDao;
	
	public void getAdd(final Vote v){
		voteDao.getAdd(v);
	}
	
	public List<Map<String,Object>> getList(String dpId){
		return voteDao.list(VoteDao.list,new Object[]{dpId});
	}
	public List<Map<String,Object>> getListAjax(String dpId){
		return voteDao.list(VoteDao.listAjax,new Object[]{dpId});
	}
	
	public List<Map<String,Object>> getList(int id){
		return voteDao.list(VoteDao.vote,new Object[]{id});
	}

	public List<Map<String, Object>> commentList(int id) {
		return voteDao.list(VoteDao.commentList,new Object[]{id});
	}

	public void toupiao(Vote v) {
		voteDao.execute(VoteDao.toupiao,new Object[]{v.getOptionsId(),v.getUserID()});
	}
	
	public List<Map<String,Object>> getSum(int id){
		return voteDao.list(VoteDao.sum,new Object[]{id});
	}
	public int toupiaoUser(Vote v) {
		return voteDao.queryForInt(VoteDao.toupiaoUser,new Object[]{v.getUserID(),v.getId()});
	}

	public void insertComment(Vote v) {
		voteDao.execute(VoteDao.insertComment,new Object[]{v.getId(),v.getUserID(),v.getComment()});
	}
	
	public List<Map<String,Object>> getMsgVote(int id,String dpId){
		return voteDao.list(VoteDao.msgVote,new Object[]{id,dpId,id});
	}
	 
}
