package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cn.model.Task;
import com.cn.util.StringUtil;

@Repository
public class TaskDao extends JdbcDao{
	public final String TASK_APPLY = "任务申请";
	public final String TASK_APPROVE = "任务审批";
	public final String TASK_ALLOCATING = "任务分配";
	public final String TASK_EXECUTE = "任务执行";
	public final String TASK_FINSH = "任务结束";
	
	
	public static String getTaskListSql(boolean isTotal){
		StringBuffer str = new StringBuffer();
		if(isTotal){
			str.append("select count(1) ");
		}else{
			str.append("SELECT t.id,t.title,u.real_name,t.itime,t.grade,t.status,u.id ");
		}
		str.append("FROM t_task t,t_user u, (SELECT task_id FROM t_task_flow WHERE (SELECT isContainId(?,user_id))=1) s ")
		   .append("WHERE t.id=s.task_id AND t.launch_user_id=u.id AND t.status!=3 ");
		if(!isTotal){
			str.append(" GROUP BY t.id ORDER BY t.status ASC,t.itime DESC limit ?,?");
		}
		return str.toString();
	}
	
	public static String getFinishListSql(boolean isTotal){
		StringBuffer str = new StringBuffer();
		if(isTotal){
			str.append("select count(1) ");
		}else{
			str.append("SELECT t.id,t.title,u.real_name,t.itime,t.grade,t.status,u.id ");
		}
		str.append("FROM t_task t,t_user u, (SELECT task_id FROM t_task_flow WHERE (SELECT isContainId(?,user_id))=1) s ")
		   .append("WHERE t.id=s.task_id AND t.launch_user_id=u.id AND t.status=3 ");
		if(!isTotal){
			str.append(" GROUP BY t.id ORDER BY t.status ASC,t.itime DESC limit ?,?");
		}
		return str.toString();
	}
	
	public List<Task> list(Task task,int start) {
		return jdbcTemplate.query(getTaskListSql(false), new Object[]{task.getUserId(),start,task.getPageSize()},new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				int num = 1+index;
				Task task = new Task();
				task.setIndex(num);
				task.setId(rs.getInt(1));
				task.setTitle(rs.getString(2));
				task.setRealName(rs.getString(3));
				task.setItime(rs.getTimestamp(4));
				task.setGrade(rs.getInt(5));
				task.setStatus(rs.getInt(6));
				task.setUserId(rs.getInt(7));
				return task;
			}
		});
	}
	
	public List<Task> finishList(Task task,int start) {
		return jdbcTemplate.query(getFinishListSql(false), new Object[]{task.getUserId(),start,task.getPageSize()},new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				int num = 1+index;
				Task task = new Task();
				task.setIndex(num);
				task.setId(rs.getInt(1));
				task.setTitle(rs.getString(2));
				task.setRealName(rs.getString(3));
				task.setItime(rs.getTimestamp(4));
				task.setGrade(rs.getInt(5));
				task.setStatus(rs.getInt(6));
				task.setUserId(rs.getInt(7));
				return task;
			}
		});
	}
	
	public static final String taskAddSql = "INSERT INTO t_task (title,launch_user_id,launch_user_dp_id,receive_user_id,receive_user_dp_id,grade,content,attachment) VALUES (?,?,?,?,?,?,?,?)" ;
	public int addTask(final Task task) {
		KeyHolder holder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(taskAddSql,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, task.getTitle());
				ps.setInt(2, task.getUserId());
				ps.setInt(3, task.getUserDpId());
				ps.setInt(4, task.getReceiveUserId());
				ps.setInt(5, task.getReceiveDpId());
				ps.setInt(6, task.getGrade());
				ps.setString(7,task.getContent());
				ps.setString(8, task.getAttachment());
				return ps;
			}
		},holder);
		task.setTaskId(holder.getKey().intValue());
		task.setDescribe(TASK_APPLY);
		addTaskFlow(task);
		return holder.getKey().intValue();
	}
	
	public static final String maxTaskFlowId = "SELECT MAX(id) FROM t_task_flow WHERE task_id=? ";
	public static final String updateFlowNextId = "UPDATE t_task_flow SET next_id=? WHERE id=? ";
	public static final String taskFlowAddSql = "INSERT INTO t_task_flow (task_id,user_id,describes,startTime,endTime,COMMENT,next_id,status) VALUES (?,?,?,?,?,?,?,?) ";
	
	
	
	public int getMaxFlowId(int taskFlowId){
		return jdbcTemplate.queryForInt(maxTaskFlowId, new Object[]{taskFlowId});
	}
	/**
	 * 
	 * @param task
	 * @param flag   是否添加 next_id   defalut:ture;
	 * @return
	 */
	public int addTaskFlow(final Task task){
		int flowId = 0;
		if(task.getFlag() == 100){  //分派任务
			flowId = task.getId();//getAllot(task.getTaskId());
		}else{
			flowId = getMaxFlowId(task.getTaskId());
		}
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps=con.prepareStatement(taskFlowAddSql,Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,task.getTaskId());
				ps.setString(2, task.getFlag()==100 ? task.getUserIds():task.getUserId()+"");
				ps.setString(3, task.getDescribe());
				ps.setTimestamp(4, task.getStartTime());
				ps.setTimestamp(5, task.getEndTime());
				ps.setString(6,task.getComment());
				ps.setString(7, StringUtil.isEmpty(task.getNextId()) ? "-1":task.getNextId());
				ps.setInt(8, task.getStatus());
				return ps;
			}
		},holder);
		if(flowId != holder.getKey().intValue()){
			String nextIds = getNextIdById(flowId);
			if(!nextIds.equals("-1")){
				nextIds+=","+holder.getKey();
			}else{
				nextIds = ""+holder.getKey();
			}
			jdbcTemplate.update(updateFlowNextId, new Object[]{nextIds,flowId});
		}
		return holder.getKey().intValue();
	}
	
	/**
	 * 待审批任务列表
	 * @param userId
	 * @return
	 */
	public static final String approvalListSql = "SELECT t.id,t.title,t.launch_user_id,t.launch_user_dp_id,t.grade,t.content,u.real_name,t.itime FROM t_task t,t_user u "
            +" WHERE t.receive_user_id=? AND u.id=t.launch_user_id AND (t.status=0 OR t.status=1) ";
	public List approvalList(int userId) {
		return jdbcTemplate.query(approvalListSql, new Object[]{userId},  new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				Task t = new Task();
				t.setId(rs.getInt(1));
				t.setTitle(rs.getString(2));
				t.setUserId(rs.getInt(3));
				t.setUserDpId(rs.getInt(4));
				t.setGrade(rs.getInt(5));
				t.setContent(rs.getString(6));
				t.setRealName(rs.getString(7));
				t.setItime(rs.getTimestamp(8));
				return t;
			}
		});
	}
	
	public static final String getTaskById = "SELECT t.title,t.launch_user_id,t.launch_user_dp_id,t.grade,t.content,t.status,t.itime,u.real_name,d.dp_name,t.id "
         +" FROM t_task t,t_user u,t_department d WHERE t.id=? AND t.launch_user_id=u.id AND t.launch_user_dp_id=d.id ";
	public Task getTaskById(int id) {
		return jdbcTemplate.query(getTaskById, new Object[]{new Integer(id)}, new ResultSetExtractor<Task>(){
			@Override
			public Task extractData(ResultSet rs) throws SQLException,
					DataAccessException {
			Task t = new Task();
			 if(rs!=null && rs.next()){
			    t.setTitle(rs.getString(1));
			    t.setUserId(rs.getInt(2));
			    t.setUserDpId(rs.getInt(3));
			    t.setGrade(rs.getInt(4));
			    t.setContent(rs.getString(5));
			    t.setStatus(rs.getInt(6));
			    t.setItime(rs.getTimestamp(7));
			    t.setRealName(rs.getString(8));
			    t.setDpName(rs.getString(9));
			    t.setId(rs.getInt(10));
			 }
		     return t;
			}
		});
	}
	
	public static final String addApprovalSql = "INSERT INTO t_task_flow (task_id,user_id,comment) VALUES(?,?,?)";
	public static final String updateReceive = "UPDATE t_task SET receive_user_dp_id=? ,receive_user_id=? WHERE id=? ";
	public int addApproval(Task task) {
		int rs = addTaskFlow(task);
		if(rs>0){  
			jdbcTemplate.update(updateReceive, new Object[]{task.getReceiveDpId(),task.getReceiveUserId(),task.getTaskId()});
		}
		return rs; 
	}
	public static final String flowListSql = " SELECT t.id,t.task_id,t.next_id,(SELECT getMoreUseName(t.user_id)) AS real_name,t.describes,t.startTime,t.endTime,t.status,a.title,t.user_id,t.start "
          +"FROM t_task_flow t, t_task a, t_user u WHERE t.task_id=? AND t.user_id=u.id  AND t.task_id=a.id ORDER BY t.id ASC";
	public List<Task> getFlowList(int id) {
		return jdbcTemplate.query(flowListSql, new Object[]{id}, new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				Task t = new Task();
				t.setId(rs.getInt(1));
				t.setTaskId(rs.getInt(2));
				t.setNextId(rs.getString(3));
				t.setRealName(rs.getString(4).substring(1, rs.getString(4).length()));
				t.setDescribe(rs.getString(5));
				t.setStartTime(rs.getTimestamp(6));
				t.setEndTime(rs.getTimestamp(7));
				t.setStatus(rs.getInt(8));
				t.setTitle(rs.getString(9));
				t.setUserIds(rs.getString(10));
				t.setStart(rs.getInt(11));
				return t;
			}
		});
	}
	
	public static final String isSetTaskAllocatingSql = "SELECT COUNT(*) FROM t_task_flow WHERE task_id=? AND describes=?";
	public boolean isSetTaskAllocating(Task task) {
		int rs = jdbcTemplate.queryForInt(isSetTaskAllocatingSql, new Object[]{task.getId(),TASK_ALLOCATING});
		if(rs < 1){
			return true;
		}
		return false;
	}
	
	public static final String getTaskByFlowIdSql = "SELECT t.id,t.task_id,t.next_id,t.user_id,t.describes,t.startTime,t.endTime,u.dp_id ,t.status FROM t_task_flow t,t_user u WHERE t.id=? AND t.user_id=u.id ";
	public Task getTaskByFlowId(int id) {
		return jdbcTemplate.query(getTaskByFlowIdSql, new Object[] { new Integer(id) }, new ResultSetExtractor<Task>() {
					@Override
					public Task extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						Task t = new Task();
						if (rs != null && rs.next()) {
							t.setId(rs.getInt(1));
							t.setTaskId(rs.getInt(2));
							t.setNextId(rs.getString(3));
							t.setReceiveUserId(rs.getInt(4));
							t.setDescribe(rs.getString(5));
							t.setStartTime(rs.getTimestamp(6));
							t.setEndTime(rs.getTimestamp(7));
							t.setReceiveDpId(rs.getInt(8));
							t.setStatus(rs.getInt(9));
						}
						return t;
					}
				});
	}
	public static final String allocatingEditSave = "UPDATE t_task_flow SET next_id=?, user_id=?, describes=?, startTime=?, endTime=? WHERE id=? ";
	public void allocatingEditSave(Task task) {
		jdbcTemplate.update(allocatingEditSave, new Object[]{
				task.getNextId(),
				task.getUserId(),
				task.getDescribe(),
				task.getStartTime(),
				task.getEndTime(),
				task.getId()
		});
	}
	
	public static final String editTaskStatus = "UPDATE t_task SET STATUS=? WHERE id=? ";
	public void editTaskStatus(int status,int taskId){
		jdbcTemplate.update(editTaskStatus, new Object[]{status,taskId});
	}
	
	public void taskNoPass(int taskId) {
		editTaskStatus(-1,taskId);
	}
	
	public static final String taskDetailSql = "SELECT t.title,t.grade,d.dp_name,u.real_name,t.content,t.status,t.attachment "
             +" FROM t_task t,t_department d,t_user u "
             +" WHERE t.id=? AND t.launch_user_dp_id = d.id AND t.launch_user_id=u.id ";
	public Task taskDetail(int taskId) {
		return jdbcTemplate.query(taskDetailSql, new Object[]{taskId}, new ResultSetExtractor<Task>(){
			@Override
			public Task extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Task t = new Task();
				if(rs!=null && rs.next()){
					t.setTitle(rs.getString(1));
					t.setGrade(rs.getInt(2));
					t.setDpName(rs.getString(3));
					t.setRealName(rs.getString(4));
					t.setContent(rs.getString(5));
					t.setStatus(rs.getInt(6));
					t.setAttachment(rs.getString(7));
				}
				return t;
			}
		});
	}
	
	public static final String taskExecuteSql = " UPDATE t_task_flow SET STATUS=0 WHERE id=? AND user_id=?";
	public int taskExecute(int id, String userId) {
		return jdbcTemplate.update(taskExecuteSql, new Object[]{id,userId});
	}
	
	
	public int taskTotal(int userId) {
		return jdbcTemplate.queryForInt(getTaskListSql(true), new Object[]{userId});
	}
	
	public int finishTotal(int userId){
		return jdbcTemplate.queryForInt(getFinishListSql(true), new Object[]{userId});
	}

	
	public static final String taskCommentSql = "SELECT u.real_name ,f.comment,f.itime FROM t_task_flow f,t_user u "
                     + "WHERE f.user_id=u.id AND f.task_id=? AND f.comment IS NOT NULL ";
	public List<Task> taskComment(int taskId) {
		return jdbcTemplate.query(taskCommentSql, new Object[]{taskId}, new RowMapper<Task>(){
			@Override
			public Task mapRow(ResultSet rs, int index) throws SQLException {
				Task t = new Task();
				t.setRealName(rs.getString(1));
				t.setComment(rs.getString(2));
				t.setItime(rs.getTimestamp(3));
				return t;
			}
		});
	}

	public static final String unHandleSql = "SELECT COUNT(1) FROM t_task WHERE receive_user_id=? AND (status=0 OR status=1)";
	public int getUnHandleTask(int userId) {
		return jdbcTemplate.queryForInt(unHandleSql, new Object[]{userId});
	}

	public static final String isAllotSql = "SELECT COUNT(1) FROM t_task_flow f,t_task t WHERE t.id=f.task_id AND t.id=? AND f.user_id=? AND f.describes=? AND t.status=2 ";
			//"SELECT count(1) FROM t_task_flow WHERE task_id=? AND user_id=? AND describes=? ";
	public int getAllotId(int taskId,int userId) {
		return jdbcTemplate.queryForInt(isAllotSql, new Object[]{taskId,userId,TASK_ALLOCATING});
	}
	
	public static final String getAllotSql = "SELECT id FROM t_task_flow WHERE task_id=? AND describes=? ";
	public int getAllot(int taskId){
		int rs=0;
		try{
			rs = jdbcTemplate.queryForInt(getAllotSql, new Object[]{taskId,TASK_ALLOCATING});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	public static final String getNextIdByIdSql = " SELECT next_id FROM t_task_flow WHERE id=? ";
	public String getNextIdById(int id){
		String rs = "-1";
		try{
			rs = jdbcTemplate.queryForObject(getNextIdByIdSql, String.class, new Object[]{id});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	public int getTaskIdByFlowId(int taskFlowId) {
		return jdbcTemplate.queryForInt("SELECT task_id FROM t_task_flow WHERE id = ? ", new Object[]{taskFlowId});
	}

	public boolean isFinsh(int taskId) {
		int rs = jdbcTemplate.queryForInt("SELECT COUNT(1) FROM t_task_flow WHERE task_id=? AND STATUS=1 AND startTime IS NOT NULL ",new Object[]{taskId});
		return rs < 1?true:false;
	}

	public static final String delTaskSql = "DELETE FROM t_task WHERE id=? ";
	public static final String delTaskFlowSql = "DELETE FROM t_task_flow WHERE task_id=? ";
	public boolean delTask(int id) {
		int rs1= jdbcTemplate.update(delTaskSql, new Object[]{id});
		int rs2= jdbcTemplate.update(delTaskFlowSql, new Object[]{id});
		if(rs1>0 && rs2>0){
			return true;
		}else{
			return false;
		}
	}

	public static final String getUserIds = "SELECT user_id FROM t_task_flow WHERE (SELECT isContainId(?,next_id))=1 AND task_id=? ";
	public String getUserIds(int flowTaskId,int taskId) {
		return jdbcTemplate.queryForObject(getUserIds, new Object[]{flowTaskId,taskId}, String.class);
	}

	private static final String deleteTask = "DELETE FROM t_task_flow WHERE id=? ";
	public int deleteTask(int flowTaskId) {
		return jdbcTemplate.update(deleteTask, new Object[]{flowTaskId});
	}
	
	
	public static final String taskStartSql = " UPDATE t_task_flow SET start=0 WHERE id=? AND user_id=?";
	public int taskStart(int id, String userId) {
		return jdbcTemplate.update(taskStartSql, new Object[]{id,userId});
	}
	
}
