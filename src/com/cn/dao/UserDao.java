package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cn.model.User;
import com.cn.util.StringUtil;
/**
 * 
 * @author ll
 *登录
 */
@Repository
public class UserDao  extends  JdbcDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	/**
	 * 登录
	 * @param sql
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	public User login(Object[] params){
		String loginSql="SELECT id, username , PASSWORD ,real_name,dp_id,role_id ,STATUS,itime FROM t_user WHERE STATUS = 0 AND username = ? AND PASSWORD=? ";
		final User user = new User();
		jdbcTemplate.query(loginSql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				user.setRealName(rs.getString("real_name"));
				user.setDpId(rs.getInt("dp_id"));
				user.setRoleId(rs.getInt("role_id"));
				user.setStatus(rs.getInt("status"));
				user.setItime(rs.getTimestamp("itime"));
			}
		});
		return user;
	}
	public int getSum(User user){
		String sum="SELECT count(1)  FROM t_user_msg,t_user t,t_department dp,t_role r WHERE  user_id = t.id  AND dp.id=t.dp_id AND r.id=t.role_id ";
		StringBuffer sb = new StringBuffer(sum);
		if(user.getId()!=0){
			sb.append(" and t.id = ").append(user.getId()).append(" ");
		}
		if(!StringUtil.isEmpty(user.getRealName())){
			sb.append(" and real_name like '%").append(user.getRealName()).append("%' ");
		}
		if(!StringUtil.isEmpty(user.getlTime())){
			sb.append(" and  t.itime like '%").append(user.getlTime()).append("%' ");
		}
		if(!StringUtil.isEmpty(user.getDpName())){
			sb.append(" and   dp_name like '%").append(user.getDpName()).append("%' ");
		}
		if(!StringUtil.isEmpty(user.getRoleName())){
			sb.append(" and   r.role_name  like '%").append(user.getRoleName()).append("%' ");
		}
		return jdbcTemplate.queryForInt(sb.toString());
	}
	public List<User> getList(int page, int size,User user){
		String sql="SELECT t.id,post,username,real_name,dp_name,t.STATUS,t.itime,r.role_name,t.grade  FROM t_user_msg,t_user t,t_department dp,t_role r WHERE  user_id = t.id  AND dp.id=t.dp_id AND r.id=t.role_id";
		StringBuffer sb = new StringBuffer(sql);
				if(user.getId()!=0){
					sb.append(" and t.id = ").append(user.getId()).append(" ");
				}
				if(!StringUtil.isEmpty(user.getRealName())){
					sb.append(" and real_name like '%").append(user.getRealName()).append("%' ");
				}
				if(!StringUtil.isEmpty(user.getlTime())){
					sb.append(" and  t.itime  like '%").append(user.getlTime()).append("%' ");
				}
				if(!StringUtil.isEmpty(user.getDpName())){
					sb.append(" and   dp_name like '%").append(user.getDpName()).append("%' ");
				}
				if(!StringUtil.isEmpty(user.getRoleName())){
					sb.append(" and   r.role_name  like '%").append(user.getRoleName()).append("%' ");
				}
				sb.append(" GROUP BY t.itime asc LIMIT ?,?");
			return	jdbcTemplate.query(sb.toString(),new Object[]{(page-1)*size,size},new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int index)
							throws SQLException {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setUserName(rs.getString("username"));
						user.setRealName(rs.getString("real_name"));
						user.setItime(rs.getTimestamp("itime"));
						user.setStatus(rs.getInt("status"));
						user.setGrade(rs.getInt("grade"));
						user.setPost(rs.getString("post"));
						user.setDpName(rs.getString("dp_name"));
						user.setRoleName(rs.getString("role_name"));
						return user;
					}
				});
		
	}
	
	private final static String getUsersByDpIdSql = "SELECT id,username,real_name FROM t_user WHERE dp_id=? AND STATUS=0 ORDER BY id ASC ";
	public List<User> getUsersByDpId(int dpId) {
		return jdbcTemplate.query(getUsersByDpIdSql, new Object []{new Integer(dpId)},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUserName(rs.getString("username"));
				u.setRealName(rs.getString("real_name"));
				return u;
			}
		});
	}
	
	
	private final static String getLeaderByDpIdSql = "SELECT id,username,real_name FROM t_user WHERE dp_id=? AND (role_id=3 OR role_id=6) AND STATUS=0 ORDER BY id ASC ";
	public List<User> getLeaderByDpId(int dpId) {
		return jdbcTemplate.query(getLeaderByDpIdSql, new Object []{new Integer(dpId)},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUserName(rs.getString("username"));
				u.setRealName(rs.getString("real_name"));
				return u;
			}
		});
	}
	
	

	/**
	 * 查看个人信息
	 * @param id
	 * @return
	 */
	public User personalInfo(int id){
		final User user = new User();
		String sql=" SELECT t.id,sex,birth,post,email,qq,phone,office_phone,icon,username,PASSWORD,real_name,dp_id,role_id,dp_name,t.STATUS,t.itime,t.grade  FROM t_user_msg,t_user t,t_department dp WHERE  user_id = t.id  AND dp.id=t.dp_id  AND t.id=?";
		jdbcTemplate.query(sql,new Object[]{id}, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				user.setRealName(rs.getString("real_name"));
				user.setDpId(rs.getInt("dp_id"));
				user.setRoleId(rs.getInt("role_id"));
				user.setDpName(rs.getString("dp_name"));
				user.setStatus(rs.getInt("status"));
				user.setItime(rs.getTimestamp("itime"));
				user.setSex(rs.getInt("sex"));
				user.setBirth(rs.getString("birth"));
				user.setPost(rs.getString("post"));
				user.setEmail(rs.getString("email"));
				user.setQq(rs.getString("qq"));
				user.setPhone(rs.getString("phone"));
				user.setOfficePhone(rs.getString("office_phone"));
				user.setIcon(rs.getString("icon"));
				user.setGrade(rs.getInt("grade"));
			}
		});
		return user;
	}
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	public int editUser(User user){
		StringBuffer sql= new StringBuffer("UPDATE t_user_msg SET birth = ?, email = ?,qq= ?,phone = ?,office_phone = ? ");
		if(!StringUtil.isEmpty(user.getIcon())){
			sql.append(" , icon ='").append(user.getIcon()).append("' ");
		}
		sql.append("      WHERE user_id =  ").append(user.getId());
		return jdbcTemplate.update(sql.toString(),new Object[]{user.getBirth(),user.getEmail(),user.getQq(),user.getPhone(),user.getOfficePhone()});
	}
	
	public boolean repName(String userName){
		String sql="SELECT COUNT(1) FROM t_user  WHERE username = ?";
		return jdbcTemplate.queryForInt(sql, new Object[]{userName})>0 ? false:true;
	}
	/**
	 * 添加员工
	 * @param user
	 * @return
	 */
	public int getAdd(final User user){
		final String sql="insert into t_user(username , PASSWORD ,real_name,dp_id,role_id,STATUS,grade) values(?,?,?,?,?,?,?)";
		KeyHolder holder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getPassWord());
				ps.setString(3, user.getRealName());
				ps.setInt(4, user.getDpId());
				ps.setInt(5, user.getRoleId());
				ps.setInt(6, user.getStatus());
				ps.setInt(7, user.getGrade());
				return ps;
			}
		},holder);
		 String sqlMsg="insert  into t_user_msg(user_id,sex,post) values(?,?,?)" ;
		 int i=jdbcTemplate.update(sqlMsg,new Object[]{holder.getKey(),user.getSex(),user.getPost()});
		return i;
	}
	/**
	 * 修改员工信息
	 * @param user
	 * @return
	 */
	public int getEdit(User user){
		String sql="update t_user set username =?,password = ?,real_name = ?, dp_id =? ,role_id = ?,status= ? where id = ?";
		String msgSql="update t_user_msg set sex = ?,post = ?  where  user_id = ?";
		int i =jdbcTemplate.update(msgSql,new Object[]{user.getSex(),user.getPost(),user.getId()});
		jdbcTemplate.update(sql,new Object[]{user.getUserName(),user.getPassWord(),user.getRealName()
				,user.getDpId(),user.getRoleId(),user.getStatus(),user.getId()});
		return i;
	}
	
	
public static String realName = "select id from t_user where real_name=?";
public static String relPassword = "update t_user set password =?  where id = ?";


/**
 * 通讯录
 */
public static String addressList="SELECT t.id,post,real_name,dp_name,post,email,qq,phone,office_phone,icon  FROM t_user_msg,t_user t,t_department dp,t_role r WHERE  user_id = t.id  AND dp.id=t.dp_id AND r.id=t.role_id";
 
	public List<User> addressList(int page, int size,User user){
		StringBuffer sb = new StringBuffer(addressList);
		if(!StringUtil.isEmpty(user.getPost())){
			sb.append("  and post   like '%").append(user.getPost()).append("%'");		
		}
		if(!StringUtil.isEmpty(user.getRealName())){
			sb.append("  and real_name like '%").append(user.getRealName()).append("%'");		
		}
		if(!StringUtil.isEmpty(user.getDpName())){
			sb.append("  and dp_name   like  '%").append(user.getDpName()).append("%'");		
		}
		sb.append("  GROUP BY dp_name,t.id LIMIT ?,?");
		return	jdbcTemplate.query(sb.toString(),new Object[]{(page-1)*size,size},new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int index)
					throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setPost(rs.getString("post"));
				user.setRealName(rs.getString("real_name"));
				user.setDpName(rs.getString("dp_name"));
				user.setPost(rs.getString("post"));
				user.setEmail(rs.getString("email"));
				user.setQq(rs.getString("qq"));
				user.setPhone(rs.getString("phone"));
				user.setOfficePhone(rs.getString("office_phone"));
				user.setIcon(rs.getString("icon"));
				return user;
			}
		});

	}
	public static String addressListSum="SELECT count(1)  FROM t_user_msg,t_user t,t_department dp,t_role r WHERE  user_id = t.id  AND dp.id=t.dp_id AND r.id=t.role_id";
	 
	public int addressListSum(User user) {
			StringBuffer sb = new StringBuffer(addressListSum);
			if(!StringUtil.isEmpty(user.getPost())){
				sb.append("  and post   like  '%").append(user.getPost()).append("%'");		
			}
			if(!StringUtil.isEmpty(user.getRealName())){
				sb.append("  and real_name   like  '%").append(user.getRealName()).append("%'");		
			}
			if(!StringUtil.isEmpty(user.getDpName())){
				sb.append("  and dp_name   like  '%").append(user.getDpName()).append("%'");		
			}
		return jdbcTemplate.queryForInt(sb.toString());
	}
	/**
	 * 通讯录end
	 */
}
