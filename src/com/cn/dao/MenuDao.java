package com.cn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cn.model.Menu;



@Repository
public class MenuDao extends JdbcDao{
	private Logger logger = Logger.getLogger(MenuDao.class);
	
	public static String list = "select id , menu_name , description, path , parent , visible , order_no from t_menu where 1=1 order by order_no asc" ;

	public List<Menu> menulist(String sql,Object[] params){
		logger.debug(sql);
		return jdbcTemplate.query(sql, params, new RowMapper<Menu>(){
			@Override
			public Menu mapRow(ResultSet rs, int index) throws SQLException {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setName(rs.getString("menu_name"));
				menu.setDesc(rs.getString("description"));
				menu.setPath(rs.getString("path"));
				menu.setParent(rs.getInt("parent"));
				menu.setVisible(rs.getInt("visible"));
				menu.setOrderNo(rs.getString("order_no"));
				return menu;
			}
		});   
	}
	
	/**
	 * 父菜单列表
	 */
	public static String listParent = "select id , menu_name ,description, path , parent , visible , order_no from t_menu where status = 0 and parent = 0 order by order_no asc" ;
	/**
	 * 子菜单列表
	 */
	public static String listSun = "select id , menu_name ,description, path , parent , visible , order_no from t_menu where status = 0 and parent = ? order by order_no asc" ;
	
	
	
	/**
	 * 新增菜单
	 */
	public static String addMenuSql = "insert into t_menu (menu_name,description,path,parent,visible,order_no) values (?,?,?,?,?,?)" ;

	
	/**
	 * 查找菜单
	 */
	public static final String getMenuByIdSql = " SELECT id,menu_name,description,path,parent,visible,order_no FROM t_menu WHERE id=? ";
	public Menu getMenuById(int id) {
		return jdbcTemplate.query(getMenuByIdSql,new Object[]{id},new ResultSetExtractor<Menu>(){
			@Override
			public Menu extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Menu m = new Menu();
				if(rs != null && rs.next()){
					m.setId(rs.getInt(1));
					m.setName(rs.getString(2));
					m.setDesc(rs.getString(3));
					m.setPath(rs.getString(4));
					m.setParent(rs.getInt(5));
					m.setVisible(rs.getInt(6));
					m.setOrderNo(rs.getString(7));
				}
				return m;
			}
		});
	}
	
	/**
	 * 删除菜单
	 */
    public static final String delMenuByIdSql = "delete from t_menu where id = ?";
	public boolean delMenuById(int id) {
		int rs = jdbcTemplate.update(delMenuByIdSql, new Object[]{id});
		return rs > 0?true:false;
	}
	
	/**
	 * 修改菜单
	 */
	public static final String editSaveSql = " UPDATE t_menu SET menu_name=? ,description=? ,path=? ,parent=? ,visible=? ,order_no=? WHERE id=? ";
	public boolean editSave(Menu menu) {
		int rs = jdbcTemplate.update(editSaveSql, new Object[]{menu.getName(),
				menu.getDesc(),menu.getPath(),menu.getParent(),
				menu.getVisible(),menu.getOrderNo(),menu.getId()});
		
		return rs>0?true:false;
	}
}
