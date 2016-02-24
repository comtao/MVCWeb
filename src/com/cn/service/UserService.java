package com.cn.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.UserDao;
import com.cn.model.User;
/**
 * 
 * @author ll
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao ;
	
	public List<User> getUsersByDpId(int dpId){
		return userDao.getUsersByDpId(dpId);
	}
	
	public List<User> getLeaderByDpId(int dpId){
		return userDao.getLeaderByDpId(dpId);
	}
	
	public User login(Object[] params){
		return userDao.login(params);
	}
	
	public String getUsersCheckBoxs(List<User> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("&nbsp;");
		for(User u:list){
			sb.append("<label><input name1='receiveUserId' type='checkbox' value='")
			  .append(u.getId()).append("'/>").append(u.getRealName()).append("</label>");
			/*sb.append("<option value='").append(u.getId()).append("'>")
			.append(u.getRealName()).append("</option>");*/
		}
		return sb.toString();
	}

	public String getUsersOptions(List<User> list) {
		StringBuffer sb = new StringBuffer();
		for(User u:list){sb.append("<option value='").append(u.getId()).append("'>")
			.append(u.getRealName()).append("</option>");
		}
		return sb.toString();
	}
	public User personalInfo(int id){
		return userDao.personalInfo(id);
	}
	public int editUser(User user){
		return userDao.editUser(user);
	}
	public boolean repName(String userName){
		return userDao.repName(userName);
	}
	public int getAdd(User user){
		return userDao.getAdd(user);
	}
	public List<User> getList(int page, int size,User user){
		return userDao.getList(page,size,user);
	}
	public int getEdit(User user){
		return userDao.getEdit(user);
	}
	public Map<String, Object> realName(String relaName)  {
		try {
			return userDao.queryForMap(UserDao.realName , new Object[]{relaName});
		} catch (Exception e) {
			return null;
		}
	}
	public int getRel(String password,int id) {
		return userDao.executeSql(UserDao.relPassword, new Object[]{password,id});
	}
	public int getSum(User user){
		return userDao.getSum(user);
	}
	public List<User> addressList(int page,int size,User user){
		return userDao.addressList(page, size, user);
	}
	public int addressListSum(User user){
		return userDao.addressListSum(user);
	}
}
