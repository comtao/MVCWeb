package com.cn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cn.model.Department;
import com.cn.web.DpController;

/**
 * 部门
 * @author ll
 *
 */
@Service
public class DpDao extends JdbcDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Department> dpList(){
		String sql="SELECT id , dp_name,STATUS,itime FROM t_department where status = 0";
		return jdbcTemplate.query(sql, new RowMapper<Department>(){
			@Override
			public Department mapRow(ResultSet rs, int index) throws SQLException {
				Department dp =new Department();
				dp.setId(rs.getInt("id"));
				dp.setDpName(rs.getString("dp_name"));
				dp.setStatus(rs.getInt("status"));
				dp.setItime(rs.getTimestamp("itime"));
				return dp;
			}
		});
	}
	public int repName(String repName){
		StringBuffer sql= new StringBuffer("SELECT COUNT(1) FROM t_department  WHERE dp_name like '" );
		sql.append(repName.trim()).append("'");
		return jdbcTemplate.queryForInt(sql.toString());
	}
	public int  addDp(Department dp){
		String sql="insert into t_department(dp_name) values(?)";
		return jdbcTemplate.update(sql,new Object[]{dp.getDpName()});
	}
	
	public int  editDp(Department dp){
		String sql="update t_department set dp_name=? where id = ?";
		return jdbcTemplate.update(sql,new Object[]{dp.getDpName(),dp.getId()});
	}
}
