package com.cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.MenuDao;
import com.cn.model.Menu;


@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> list(String sql,Object[] params){
		return menuDao.menulist(sql, params);
	}
	
	/**
	 * 新增菜单
	 * @param params
	 */
	public void addMenu(Object[] params){
		menuDao.executeSql(MenuDao.addMenuSql, params);
	}
	
	public Menu getMenuById(int id){
		return menuDao.getMenuById(id);
	}

	public boolean delMenuById(int id) {
		return menuDao.delMenuById(id);
	}

	public boolean editSave(Menu menu) {
		return menuDao.editSave(menu);
	}
	
	

}
