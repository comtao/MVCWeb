package com.cn.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cn.util.DateUtil;

/**
 * @author ll
 *角色信息
 */
public class User  extends Model{
	private int id;
	private String userName;
	private String passWord;
	private String realName;//真实姓名
	private int  dpId;   //部门编号
	private String dpName;
	private int grade;
	private int chatState;  //聊天室状态  0：登录中，1：在线，2：掉线，3离开
	private String chatId;  //长连接id,由comet4j自行分配
	
	
	
	
	public int getChatState() {
		return chatState;
	}
	public void setChatState(int chatState) {
		this.chatState = chatState;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getDpName() {
		return dpName;
	}
	public void setDpName(String dpName) {
		this.dpName = dpName;
	}
	private int roleId;	 //职位编号
	private String roleName; 
	private int status;
	private Timestamp itime;
	private String userTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getDpId() {
		return dpId;
	}
	public void setDpId(int dpId) {
		this.dpId = dpId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	
	public String getUserTime() {
		try {
			userTime = DateUtil.getDateStr(DateUtil.simple, itime);
		} catch (Exception e) {
			userTime = "";
		}
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	private int sex;
	private String birth;
	private String post;
	private String email;
	private String qq;
	private String phone;
	private String officePhone;
	private String sexString;
	private MultipartFile iconFile;
	private String icon;
	
	public MultipartFile getIconFile() {
		return iconFile;
	}
	public void setIconFile(MultipartFile iconFile) {
		this.iconFile = iconFile;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getSexString() {
		if(sex==0){
			sexString="女";
		}else if(sex==1){
			sexString="男";
		}
		return sexString;
	}
	public void setSexString(String sexString) {
		this.sexString = sexString;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private String lTime;
	public String getlTime() {
		return lTime;
	}
	public void setlTime(String lTime) {
		this.lTime = lTime;
	}
	
	private List<Integer> rmId ;
	public List<Integer> getRmId() {
		return rmId;
	}
	public void setRmId(List<Integer> rmId) {
		this.rmId = rmId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
