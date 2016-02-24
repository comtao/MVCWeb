package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cn.model.Vote;
import com.cn.util.StringUtil;

@Repository
public class VoteDao extends JdbcDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void getAdd(final Vote v){
		final String titleSql="insert  into t_vote(title,v_dpid) values(?,?)";
		KeyHolder holder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(titleSql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, v.getTitle());
				ps.setString(2, v.getDpId());
				return ps;
			}
		},holder);
		String optionsSql="insert into t_vote_option(vote_id,option_con,option_icon,start_time,end_time) values(?,?,?,?,?)";
		
	/*	String [] iconFile=StringUtil.ArrayString(v.getIconFile()).split(",");*/
	/*	String [] options=StringUtil.ArrayString(v.getOptions()).split(",");*/
		String [] iconFile=v.getIconFile();
		int size =v.getOptions().length;
		String file="";
			for (int i = 0; i <size; i++) {
				if(i<iconFile.length){
					file=iconFile[i];
				}else{
					file="";
				}
				jdbcTemplate.update(optionsSql,new Object[]{holder.getKey(),v.getOptions()[i],file,v.getStartTime(),v.getEndTime()});
				
			}
	}
	/**
	 * 投票列表
	 */
	public static String list = "SELECT  v.id AS vid,v.title,o.id,o.option_con,o.option_icon ,o.start_time,o.end_time,o.itime,v.status FROM t_vote v,t_vote_option o "
   +" WHERE v.id =o.vote_id AND ((SELECT isContainId(?,v.v_dpid))=1 OR (SELECT isContainId(0,v.v_dpid))=1) GROUP BY v.id ORDER BY itime DESC ";
	
	/**
	 * 投票列表五条
	 */
	public static String listAjax = "SELECT  v.id AS vid,v.title,o.id,o.option_con,o.option_icon ,o.start_time,o.end_time,o.itime,v.status FROM t_vote v,t_vote_option o "
                           +"WHERE v.id =o.vote_id AND ((SELECT isContainId(?,v.v_dpid))=1 OR (SELECT isContainId(0,v.v_dpid))=1) GROUP BY v.id ORDER BY o.itime DESC LIMIT 0,5";
	/**
	 * 投票详情
	 */
	public static String vote = "SELECT  v.id as vid,v.title,o.id,o.option_con,o.option_icon ,o.start_time,o.end_time,o.itime,o.status as ostatus,v.status as vstatus FROM t_vote v,t_vote_option o WHERE v.id =o.vote_id AND v.id = ?";
	/**
	 * 评论
	 */
	public static String commentList="SELECT us.real_name,c.user_comment,c.itime FROM  t_vote_comment c,t_vote v,t_user us WHERE c.vote_id = v.id AND c.user_id = us.id AND   v.id = ? GROUP BY c.id  ASC";
	/**
	 * 投票
	 */
	public static String toupiao = "insert into t_vote_user(option_id,user_id) values(?,?)";
	
	/**
	 * 统计投票数
	 */
	//public static String sum="  SELECT o.option_con,COUNT(u.user_id) as sum FROM (SELECT id,option_con FROM t_vote_option WHERE vote_id=? ) o LEFT JOIN  t_vote_user u ON o.id=u.option_id GROUP BY o.id";
	public static String sum=" SELECT o.option_con,o.option_icon,COUNT(u.user_id) AS SUM FROM (SELECT id,option_con,option_icon FROM t_vote_option WHERE vote_id=? ) o LEFT JOIN  t_vote_user u ON o.id=u.option_id GROUP BY o.id";
	/**
	 * 是否已投票
	 */
	public static String toupiaoUser=" SELECT COUNT(1) FROM  t_vote_option o,t_vote_user u,t_vote v  WHERE  o.vote_id = v.id AND  u.option_id = o.id AND user_id = ? AND o.vote_id = ?";
	/**
	 * 添加评论
	 */
	public static String insertComment="insert into t_vote_comment(vote_id,user_id,user_comment) values(?,?,?);";
	
	
	
	/**
	 * 主页显示
	 */
	public static String msgVote= "SELECT v.id,v.title,o.id AS optionId FROM t_vote v,t_vote_option o WHERE v.id = o.vote_id AND v.id NOT IN( " +
			" SELECT vote_id FROM t_vote_option  WHERE " +
			" id IN(SELECT option_id FROM t_vote_user WHERE user_id=?) GROUP BY vote_id) AND (SELECT isContainId(?,v.v_dpid))=1 GROUP BY v.id desc " +
			" UNION" +
			" SELECT v.id,v.title,o.id AS optionId FROM t_vote v,t_vote_option o WHERE v.id = o.vote_id AND v.id NOT IN( " +
			" SELECT vote_id FROM t_vote_option  WHERE " +
			"id IN(SELECT option_id FROM t_vote_user WHERE user_id=?) GROUP BY vote_id) AND (SELECT isContainId(0,v.v_dpid))=1 GROUP BY v.id  desc ";
}
