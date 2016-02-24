package com.cn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.LeaveDao;
import com.cn.model.Leave;

@Service
public class LeaveService {
	@Autowired
	private LeaveDao leaveDao;
	/**
	 * 添加请假单
	 * @param leave
	 * @return
	 */
	public int getAdd(Leave l){
		 return leaveDao.executeSql(LeaveDao.getAddLeave, new Object[]{l.getUserId(),l.getLevaeStatus(),l.getStart(),l.getEnd(),l.getReason().trim(),l.getDiId()});
	}
	/**
	 * 查看个人请假单列表
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getList(int id) {
		return leaveDao.list(LeaveDao.getList, new Object[]{id});
	}
	/**
	 * 审批人
	 * @param dpId
	 * @return
	 */
	public List<Map<String, Object>> director(int dpId){
		try {
			return leaveDao.list(LeaveDao.getDirector, new Object[]{dpId});
		} catch (Exception e) {
			return null;
		}
		
	}
	public String realname(String userId){
		try {
			return leaveDao.queryForString(LeaveDao.getDpUserName, new Object[]{userId});
		} catch (Exception e) {
			return "尚未审批";
		}
		
	}
	
	public int dpId(int userId){
		return leaveDao.queryForInt(LeaveDao.getDirector, new Object[]{userId});
	}
	public Map<String, Object> searchLeave(int id,int userId){
		return leaveDao.queryForMap(LeaveDao.searchLeave, new Object[]{userId,id});
	}
	
	public List<Map<String, Object>> getAttenList(int page,int size, int userId, String itime){
		return leaveDao.getAttenList(page,size,userId,itime);
	}
	
	public int getSum(int userId, String itime){
		return leaveDao.getSum(userId,itime);
	}
	public List<Map<String, Object>> getleaveRecord(int page, int size,
			Leave leave) {
		return leaveDao.getAttenList(page,size,leave);
	}
	public int getSum(Leave leave) {
		// TODO Auto-generated method stub
		return leaveDao.getSum(leave);
	}
	public List<Map<String, Object>> getApprovaList(int page, int size, 
			Leave leave) {
		return leaveDao.getApprovaList(page, size, leave);
	}
	public int getApprovaSum(int userId,String realName) {
		return leaveDao.getApprovaSum(userId,realName);
	}
	public int getApprovaEdit(Leave leave) {
		return leaveDao.executeSql(LeaveDao.getApprovaEdit, new Object[]{leave.getRemarks(),leave.getStatus(),leave.getId()});
	}
	
	/**
	 * 未处理请假申请
	 * @param id
	 * @return
	 */
	public int getUnHandleApply(int userId) {
		return leaveDao.getUnHandleApply(userId);
	}
	public List getUnHandleList(int userId) {
		return leaveDao.getUnHandleList(userId);
	}
}	
