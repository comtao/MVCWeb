package com.cn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cn.model.News;
import com.cn.util.StringUtil;

/**
 * 
 * @author ll 公告
 * 
 */
@Repository
public class NewsDao extends JdbcDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取最新公告
	 * 
	 * @param pdid
	 *            部门ID
	 * @return
	 */
	public News news(int pdid) {
		String sql = "SELECT id ,title,dp_id,content,STATUS,itime FROM t_notice WHERE STATUS = 0 ";
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" AND (SELECT isContainId("+pdid+",dp_id))=1 GROUP BY itime  DESC LIMIT 0,1");
		final News news = new News();
		jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setDpId(rs.getString("dp_id"));
				news.setContent(rs.getString("content"));
				news.setStatus(rs.getInt("status"));
				news.setItime(rs.getTimestamp("itime"));
			}
		});
		return news;
	}

	/**
	 * 获取单条公告
	 * 
	 * @param pdid
	 *            部门ID
	 * @return
	 */
	public News seeNews(int id) {
		String sql = "SELECT id ,title,dp_id,content,STATUS,itime FROM t_notice WHERE STATUS = 0 AND id =? ";
		final News news = new News();
		jdbcTemplate.query(sql, new Object[] { id, }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setDpId(rs.getString("dp_id"));
				news.setContent(rs.getString("content"));
				news.setStatus(rs.getInt("status"));
				news.setItime(rs.getTimestamp("itime"));
			}
		});
		return news;
	}

	/**
	 * 首页显示公告列表
	 * 
	 * @param pdid
	 * @param pageSize
	 * @return
	 */
	public List<News> newsList(int userId,int pdid, int pageSize) {
		String sql = "SELECT n.id ,n.title,n.dp_id,n.content,n.STATUS,n.itime,COUNT(r.id) as readInt FROM t_notice n LEFT JOIN " +
				" ( SELECT id, notice_id FROM t_notice_read WHERE user_id=?) r" +
				" ON n.id=r.notice_id   ";
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" where (SELECT isContainId("+pdid+",n.dp_id))=1 ")
		  .append(pdid>0?" AND (SELECT isContainId(0,n.dp_id))=0 ":" ")
		  .append(" GROUP BY n.itime  DESC  LIMIT 0,?");
		return jdbcTemplate.query(sb.toString(), new Object[] {userId,pageSize },
				new RowMapper<News>() {
					@Override
					public News mapRow(ResultSet rs, int index)
							throws SQLException {
						News news = new News();
						news.setId(rs.getInt("id"));
						news.setTitle(rs.getString("title"));
						news.setDpId(rs.getString("dp_id"));
						news.setContent(rs.getString("content"));
						news.setStatus(rs.getInt("status"));
						news.setItime(rs.getTimestamp("itime"));
						news.setReadInt(rs.getInt("readInt"));
						return news;
					}
				});
	}
	/**
	 * 公司公告
	 * @param userId
	 * @param pdid
	 * @param pageSize
	 * @return
	 */
	public List<News> newsGList(String pdid,int userId, int pageSize) {
		String sql = "SELECT n.id ,n.title,n.dp_id,n.content,n.STATUS,n.itime,COUNT(r.id) AS readInt FROM t_notice n LEFT JOIN  " +
				" ( SELECT id, notice_id FROM t_notice_read WHERE user_id=?) r " +
				" ON n.id=r.notice_id " ;
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" where (select isContainId("+pdid+",n.dp_id))=1 GROUP BY n.itime DESC ").append(
				" UNION" +
				" SELECT n.id ,n.title,n.dp_id,n.content,n.STATUS,n.itime,COUNT(r.id) AS readInt FROM t_notice n LEFT JOIN " +
				"  ( SELECT id, notice_id FROM t_notice_read WHERE user_id=?) r" +
				" ON n.id=r.notice_id  " +
				" WHERE (isContainId(0,n.dp_id))=1 GROUP BY n.itime desc LIMIT 0,?");
		return jdbcTemplate.query(sb.toString(), new Object[] {userId,userId,pageSize },
				new RowMapper<News>() {
					@Override
					public News mapRow(ResultSet rs, int index)
							throws SQLException {
						News news = new News();
						news.setId(rs.getInt("id"));
						news.setTitle(rs.getString("title"));
						news.setDpId(rs.getString("dp_id"));
						news.setContent(rs.getString("content"));
						news.setStatus(rs.getInt("status"));
						news.setItime(rs.getTimestamp("itime"));
						news.setReadInt(rs.getInt("readInt"));
						return news;
					}
				});
	}
	
	/**
	 * 得到公告条数
	 * 
	 * @param dpId
	 * @return
	 */
	public int getSum(String dpId) {
		StringBuffer sql = new StringBuffer(
				"select count(1)  from t_notice where STATUS=0 ");
		if (!StringUtil.isEmpty(dpId)) {
			sql.append(" and (SELECT isContainId("+dpId+",dp_id))=1 ");
		} else {
			sql.append(" ");
		}
		return jdbcTemplate.queryForInt(sql.toString());
	}

	/**
	 * 个人所有
	 * @param page
	 * @param size
	 * @param n
	 * @return
	 * */
	public List<News> getAll(int userId,int page, int size, News n) {
		final List<News> list = new ArrayList<News>();  
		StringBuffer sql = new StringBuffer(
				" SELECT n.id ,n.title,n.dp_id,d.dp_name,n.content,n.STATUS,n.itime,COUNT(r.id) as readInt FROM t_notice n  LEFT JOIN  "
						+ "( SELECT id, notice_id FROM t_notice_read WHERE user_id=?) r "
						+ "ON n.id=r.notice_id  LEFT JOIN t_department d  ON d.id =n.dp_id  WHERE n.STATUS = 0 ");
		if (!StringUtil.isEmpty(n.getDpId())) {
			sql.append(" and (SELECT isContainId("+n.getDpId()+",n.dp_id))=1 ");
			if(Integer.parseInt(n.getDpId())>0){
				sql.append(" and (SELECT isContainId(0,n.dp_id))=0 ");
			}
		}
		if (!StringUtil.isEmpty(n.getDatetime())) {
			sql.append(" AND n.itime like '%").append(n.getDatetime().trim())
					.append("%' ");
		}
		sql.append(" GROUP BY n.id DESC LIMIT ?,?");
		jdbcTemplate.query(sql.toString(), new Object[] {userId,
				(page - 1) * size, size }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setDpId(rs.getString("dp_id"));
				news.setDpName(rs.getString("dp_name"));
				news.setContent(rs.getString("content"));
				news.setStatus(rs.getInt("status"));
				news.setItime(rs.getTimestamp("itime"));
				news.setReadInt(rs.getInt("readInt"));
				list.add(news);
			}
		});
		return list;
	}

	/**
	 * 所有
	 * 
	 * @param page
	 * @param size
	 * @param n
	 * @return
	 */
	public List<News> getAllList(int page, int size, News n) {
		final List<News> list = new ArrayList<News>(); // order by ost_status
														// asc,ost_time asc
		StringBuffer sql = new StringBuffer(
				" SELECT n.id ,title,dp_id,d.dp_name,content,n.STATUS,n.itime FROM t_notice n, t_department d WHERE n.STATUS = 0 AND d.id =dp_id    ");
		if (!StringUtil.isEmpty(n.getDpId())) {
			sql.append(" and (SELECT isContainId("+n.getDpId()+",dp_id))=1 ");
		}
		if (!StringUtil.isEmpty(n.getDatetime())) {
			sql.append(" AND n.itime like '%").append(n.getDatetime().trim())
					.append("%' ");
		}
		sql.append(" GROUP BY n.itime DESC LIMIT ?,?");
		jdbcTemplate.query(sql.toString(), new Object[] { (page - 1) * size,
				size }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setDpId(rs.getString("dp_id"));
				news.setDpName(rs.getString("dp_name"));
				news.setContent(rs.getString("content"));
				news.setStatus(rs.getInt("status"));
				news.setItime(rs.getTimestamp("itime"));
				list.add(news);
			}
		});
		return list;
	}

	public int getAdd(News news) {
		String sql = "INSERT INTO t_notice (title,dp_id,content,STATUS) VALUES(?,?,?,?)";
		int i = jdbcTemplate.update(sql,
				new Object[] { news.getTitle(), news.getDpId(),
						news.getContent().trim(), news.getStatus() });
		return i;
	}

	public static String addRead = "insert into t_notice_read(user_id,notice_id) values(?,?)";
	public static String selectDpName = "SELECT dp_name FROM t_department WHERE id = ?";
}
