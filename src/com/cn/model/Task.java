package com.cn.model;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.cn.util.DateUtil;

public class Task extends Model{
	private int id;
	private int taskId;
	private String nextId;   //后置任务id，   多个以逗号隔开
	private int userId;      //发起人id
	private int userDpId;    //发起人部门id
	private String realName; //发起人真实姓名
	private String dpName;   //部门名称
	private String title;
	private int grade;        //任务级别   0：普通     1：较急      2：加急
	private int receiveDpId;  //受理人部门id
	private int receiveUserId; //受理人id
	private String content;  //任务正文
	private String attachment; //附件
	private MultipartFile attachmentFile;
	private String describe;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp itime;
	private String comment;  //备注
	private int flag; //备用标识
	private String gradeStr; 
	private String shortTime;
	private String userIds;
	private int start;  //分任务开始标识   0：开始， 1：未开始
	
	
	
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public MultipartFile getAttachmentFile() {
		return attachmentFile;
	}
	public void setAttachmentFile(MultipartFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getGradeStr(){
		if(this.grade == 2){
			return "加急";
		}else if(this.grade == 1){
			return "较急";
		}else{
			return "一般";
		}
	}
	
	
	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getDpName() {
		return dpName;
	}


	public void setDpName(String dpName) {
		this.dpName = dpName;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserDpId() {
		return userDpId;
	}
	public void setUserDpId(int userDpId) {
		this.userDpId = userDpId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getNextId() {
		return nextId;
	}
	public void setNextId(String nextId) {
		this.nextId = nextId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getReceiveDpId() {
		return receiveDpId;
	}
	public void setReceiveDpId(int receiveDpId) {
		this.receiveDpId = receiveDpId;
	}
	public int getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(int receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public String getStatusStr(){
		if(this.status == 1){
			return "冻结";
		}else{
			return "正常";
		}
	}
    public void setComment(String comment){
    	this.comment = comment;
    }
	public String getComment() {
		return comment;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	@Override
	public int getPageSize(){
		return 18;
	}
	
	

	public String getShortTime() {
		try {
			shortTime = DateUtil.getDateStr(DateUtil.simple, itime);
		} catch (Exception e) {
			shortTime = "";
		}
		return shortTime;
	}


	public void setShortTime(String shortTime) {
		this.shortTime = shortTime;
	}
	
	
	
	
}
