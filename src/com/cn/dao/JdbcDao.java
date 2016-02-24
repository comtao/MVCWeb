package com.cn.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class JdbcDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	public void execute(String sql,Object []params){
		jdbcTemplate.update(sql, params);
	}
	
	public int queryForInt(String sql,Object []params){
		return jdbcTemplate.queryForInt(sql, params);
	}
	
	public String queryForString(String sql,Object []params){
		return jdbcTemplate.queryForObject(sql, params,String.class);
	}
	
	public Map<String,Object> queryForMap(String sql,Object[] params){
		return jdbcTemplate.queryForMap(sql,params);
	}
	
	public int executeSql(String sql,Object []params){
		return jdbcTemplate.update(sql,params);
	}
	
	public List<Map<String,Object>> list(String sql, Object[] params){
		return jdbcTemplate.queryForList(sql,params);
	}
	public List<Map<String, Object>> list(String sql){
		return jdbcTemplate.queryForList(sql);
	}
}
