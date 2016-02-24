package com.cn.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cn.dao.TaskDao;
import com.cn.model.Task;
import com.cn.model.User;
import com.cn.util.StringUtil;


@Service
public class TaskService {
	@Autowired
	private TaskDao taskDao ;
	
	public List<Task> list(Task task,int start){
		return taskDao.list(task,start);
	}
	public List<Task> finishList(Task task,int start){
		return taskDao.finishList(task, start);
	}

	public boolean addTask(Task task) {
		int rs = taskDao.addTask(task);
		if(rs > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 发起人信息
	 * @param task
	 * @param user
	 */
	public void initiatorData(Task task, User user) {
		task.setUserDpId(user.getDpId());
		task.setUserId(user.getId());
	}

	public List approvalList(int userId) {
		return taskDao.approvalList(userId);
	}

	public Task getTaskById(int id) {
		return taskDao.getTaskById(id);
	}

	
	public void initTaskStatus(Task task){
		if(task.getFlag() == 1){  //审批通过 ，下派任务
			task.setDescribe(taskDao.TASK_ALLOCATING);
		}else{   //审核通过，任务转移
			task.setDescribe(taskDao.TASK_APPROVE);
		}
		if(task.status == -1){ //审核失败
			task.setReceiveDpId(-1);
			task.setReceiveUserId(-1);
			task.setStatus(0);
			taskDao.editTaskStatus(-1,task.getTaskId());
		}else if(task.getFlag() == 1){ //下派任务
			task.setStatus(0); //分任务完成状态
			taskDao.editTaskStatus(2,task.getTaskId());
		}else{ //任务递交
			task.setStatus(0); //分任务完成状态
			taskDao.editTaskStatus(1,task.getTaskId());
		}
	}
	
	public boolean addApproval(Task task) {
		initTaskStatus(task);
		int rs = taskDao.addApproval(task);
		if(rs > 0){
			return true;
		}else{
			return false;
		}
	}

	public List getFlowList(int id) {
		return taskDao.getFlowList(id);
	}

	public boolean addAllocating(Task task) {
		task.setStatus(1);
		int rs = taskDao.addTaskFlow(task);
		if(rs > 0){
			return true;
		}
		return false;
	}

	public void setTaskAllocating(Task task) {
		task.setDescribe(taskDao.TASK_ALLOCATING);
		if(taskDao.isSetTaskAllocating(task)){
			task.setStatus(0);
			taskDao.addTaskFlow(task);
			taskDao.editTaskStatus(2,task.getId());
		}
	}

	public Task getTaskByFlowId(int id) {
		return taskDao.getTaskByFlowId(id);
	}

	public void allocatingEditSave(Task task) {
		 taskDao.allocatingEditSave(task);
	}

	public Task taskDetail(int taskId) {
		return taskDao.taskDetail(taskId);
	}

	public boolean taskExecute(int id, String userId) {
		if(taskDao.taskExecute(id,userId) > 0){
			setTaskFinsh(id);
			return true;
		}
		return false;
	}
	
	public void setTaskFinsh(int taskFlowId){
		int taskId = taskDao.getTaskIdByFlowId(taskFlowId);
		boolean isFinsh = taskDao.isFinsh(taskId);
		if(isFinsh){
			taskDao.editTaskStatus(3,taskId);
		}
	}

	public int taskTotal(int userId) {
		return taskDao.taskTotal(userId);
	}
	public int finishTotal(int userId){
		return taskDao.finishTotal(userId);
	}

	public List taskComment(int taskId) {
		return taskDao.taskComment(taskId);
	}

	public int getUnHandleTask(int userId) {
		return taskDao.getUnHandleTask(userId);
	}

	public boolean isAllot(int taskId,int userId) {
		int rs = taskDao.getAllotId(taskId,userId);
		return  rs>0?true:false;
	}
	
	/**
	 * 分任务是否超时  task.falg == 5//分任务超时
	 * @param task
	 */
	public static void initTaskFlag(List taskList){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for(int i=0; i<taskList.size(); i++){
			Task t = (Task)taskList.get(i);
			if(t.getEndTime()!=null && now.after(t.getEndTime())){  //分任务超时
				t.setFlag(5);
			}
		}
	}

	public boolean delTask(int id) {
		return taskDao.delTask(id);
	}
	
	public static void handleAttachment(Task task,HttpServletRequest res){
		String realPath = res.getSession().getServletContext().getRealPath("/attachment");
		File uploadDir = new File(realPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		MultipartFile attach = task.getAttachmentFile();
		try{
			if(StringUtil.isEmpty(attach.getOriginalFilename())){
				task.setAttachment("");
			}else{
				String newname = StringUtil.getNum()+StringUtil.getSuffix(attach.getOriginalFilename());
				//String newname = StringUtil.getNum()+"_"+attach.getOriginalFilename();
				FileUtils.copyInputStreamToFile(attach.getInputStream(), new File(realPath, newname));
				task.setAttachment("/attachment/"+newname);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getUserIds(int flowTaskId,int taskId){
		return taskDao.getUserIds(flowTaskId,taskId);
	}
	
	/**
	 * 删除某个分任务
	 * @param id
	 * @return
	 */
	public boolean deleteTask(int id) {
		return taskDao.deleteTask(id)>0 ? true:false;
	}
	
	public boolean taskStart(int id,String userId){
		if(taskDao.taskStart(id,userId) > 0){
			return true;
		}
		return false;
	}
}
