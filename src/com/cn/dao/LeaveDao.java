package com.cn.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cn.model.Leave;
import com.cn.model.Task;
import com.cn.util.StringUtil;



@Repository
public class LeaveDao extends JdbcDao{
	
	/**
	 * 添加请假单
	 */
	public static String getAddLeave="insert into t_leave(user_id,l_status,l_start,l_end,l_reason,l_di_id) values(?,?,?,?,?,?) ";
	/**
	 * 查看个人请假单
	 */
	 public static String getList="SELECT l.id,l.l_status,l.l_reason,l.l_start,l.l_end,l.l_di_id ,u.dp_id,u.id AS userid,u.real_name,l_remarks,l.status,l.itime,m.post ,d.dp_name" +
							 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
							 		" WHERE l.user_id = ?  " +
							 		" AND u.id=l.user_id  " +
							 		" AND m.user_id = u.id  " +
							 		" AND d.id=u.dp_id" +
							 		" GROUP BY itime DESC";
	 /**
	  * 部门主管
	  */
	public static String getDirector = "SELECT u.id,u.real_name,r.role_name FROM t_role r,t_user u WHERE  r.id = u.role_id AND u.dp_id = ? AND u.role_id=3 ";
			
	public static String getDpUserName="select real_name from t_user where id = ?";
	/**
	 * 请假单详情
	 */
	 public static String searchLeave="SELECT l.id,l.l_status,l.l_reason,l.l_start,l.l_end,l.l_di_id ,u.dp_id,u.real_name,l_remarks,l.status,l.itime,m.post ,d.dp_name" +
	 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
	 		" WHERE l.user_id = ?  " +
	 		" AND u.id=l.user_id  " +
	 		" AND m.user_id = u.id  " +
	 		" AND d.id=u.dp_id" +
	 		" AND l.id = ?";
	 /**
	  * 请假记录 record
	  */
	 public static String arrendance="SELECT  l.l_status ,l.l_start,l.l_end,l.l_di_id ,u.real_name  ,l.itime,m.post ," +
	 		" TIMESTAMPDIFF(DAY ,l.l_start,l.l_end) AS levae_d  ,TIMESTAMPDIFF(HOUR ,l.l_start,l.l_end)%24 AS levae_h" +
	 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
	 		" WHERE  u.id=l.user_id  " +
	 		" AND m.user_id = u.id  " +
	 		" AND d.id=u.dp_id" +
	 		" AND l.status = 1";
	public List<Map<String, Object>> getAttenList(int page,int size,int  userId, String itime) {
		 StringBuffer sb = new StringBuffer(arrendance);
		 if(userId!=0){
			 sb.append("  and  l.user_id =").append(userId).append(" ");
		 }
		 if(!StringUtil.isEmpty(itime)){
			 sb.append("  and  l.l_start like '%").append(itime).append("%'");
		 }
		 sb.append(" GROUP BY itime DESC limit ?,?");
		return jdbcTemplate.queryForList(sb.toString(),new Object[]{(page-1)*size,size});
	}
	
	 public static String getSum="SELECT  count(1) " +
		 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
		 		" WHERE  u.id=l.user_id  " +
		 		" AND m.user_id = u.id  " +
		 		" AND d.id=u.dp_id" +
		 		" AND l.status = 1";
	public int getSum(int userId, String itime) {
		 StringBuffer sb = new StringBuffer(getSum);
		 if(userId!=0){
			 sb.append("   and l.user_id = ").append(userId).append(" ");
		 }
		 if(!StringUtil.isEmpty(itime)){
			 sb.append("   and l.l_start like '%").append(itime).append("%'");
		 }
		return jdbcTemplate.queryForInt(sb.toString());
	}
	
	
	 public static String getSumR="SELECT  count(1) " +
		 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
		 		" WHERE  u.id=l.user_id  " +
		 		" AND m.user_id = u.id  " +
		 		" AND d.id=u.dp_id";
	/**
	 * 请假记录总条数
	 * @param leave
	 * @return
	 */
	public int getSum(Leave leave) {
		 StringBuffer sb = new StringBuffer(getSumR);
		 if(leave.getId()!=0){
			 sb.append("   and l.id = ").append(leave.getId()).append(" ");
		 }
		 if(leave.getUserId()!=0){
			 sb.append("   and l.user_id = ").append(leave.getUserId()).append(" ");
		 }
		 if(!StringUtil.isEmpty(leave.getStart())){
			 sb.append("   and l.l_start like '%").append(leave.getStart()).append("%'");
		 }
		return jdbcTemplate.queryForInt(sb.toString());
	}
	
	/**
	 * 查看 请假单
	 */
	 public static String getAttenList="SELECT l.id,l.l_status,l.l_reason,l.l_start,l.l_end,l.l_di_id ,u.dp_id ,u.id AS userid,u.real_name,l_remarks,l.status,l.itime,m.post ,d.dp_name" +
							 		" FROM t_leave l,t_user u,t_user_msg m ,t_department d" +
							 		" WHERE  u.id=l.user_id  " +
							 		" AND m.user_id = u.id  " +
							 		" AND d.id=u.dp_id";
	/**
	 * 请假记录
	 * @param page
	 * @param size
	 * @param leave
	 * @return
	 */
	public List<Map<String, Object>> getAttenList(int page, int size,
			Leave leave) {
		 StringBuffer sb = new StringBuffer(getAttenList);
		 if(leave.getId()!=0){
			 sb.append("   and l.id = ").append(leave.getId()).append(" ");
		 }
		if(leave.getUserId()!=0){
			 sb.append("   and l.user_id = ").append(leave.getUserId()).append(" ");	 
		 }
		if(!StringUtil.isEmpty(leave.getStart())){
			 sb.append("   and l.l_start like '%").append(leave.getStart()).append("%'");
		 }
		 sb.append(" GROUP BY itime DESC limit ?,?");
		return jdbcTemplate.queryForList(sb.toString(),new Object[]{(page-1)*size,size});
	}	
	 
	 
	 /**
	  * 请假审批
	  */
	public static String getApprovaList = "SELECT l.id,l.l_status,l.user_id,l.l_reason,l.l_start,l.l_end,l.l_di_id ,u.dp_id,u.real_name,l_remarks,l.status,l.itime,m.post ,d.dp_name " +
			" FROM t_leave l,t_user u,t_user_msg m ,t_department d " +
			" WHERE l_di_id= ? " +
			" AND u.id=l.user_id   " +
			" AND m.user_id = u.id   " +
			" AND d.id=u.dp_id   ";
	public List<Map<String, Object>> getApprovaList(int page, int size,Leave leave) {
		 StringBuffer sb = new StringBuffer(getApprovaList);
		 if(!StringUtil.isEmpty(leave.getRealName())){
			 sb.append("   and real_name like '%").append(leave.getRealName()).append("%'");
		 }
		 sb.append(" GROUP  BY l.itime   DESC LIMIT ?,?");
		return jdbcTemplate.queryForList(sb.toString(),new Object[]{leave.getUserId(),(page-1)*size,size});
	}
	
	public int getApprovaSum(int userId,String realName) {
		String sql ="SELECT count(1) " +
				" FROM t_leave l,t_user u,t_user_msg m ,t_department d " +
				" WHERE l_di_id= ?  " +
				" AND u.id=l.user_id   " +
				" AND m.user_id = u.id   " +
				" AND d.id=u.dp_id   "; 
		 StringBuffer sb = new StringBuffer(sql);
		 if(!StringUtil.isEmpty(realName)){
			 sb.append("   and real_name like '%").append(realName).append("'%");
		 }
		return jdbcTemplate.queryForInt(sb.toString(),new Object[]{userId});
	}
	
	
	public static String getApprovaEdit="update t_leave set  l_remarks = ? ,status = ? where id = ?";
	
	
	public int getUnHandleApply(int userId) {
		return jdbcTemplate.queryForInt("SELECT COUNT(1) FROM t_leave WHERE l_di_id=? AND STATUS=0 ", new Object[]{userId});
	}

	public static String getUnHandleListSql = "SELECT u.real_name,l.l_start,l.l_end,l.l_status FROM t_user u, t_leave l WHERE l.user_id=u.id AND l.status=0 AND l.l_di_id=? ";
	public List<Map<String,Object>> getUnHandleList(int userId) {
		return jdbcTemplate.queryForList(getUnHandleListSql, new Object[]{userId});
	} 
}
