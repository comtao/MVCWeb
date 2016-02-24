package com.cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cn.dao.DpDao;
import com.cn.model.Department;
/**
 * 部门
 * @author ll
 *
 */
@Repository
public class DpService {
	@Autowired
	private DpDao dpDao;
	public List<Department> dpList(){
		return dpDao.dpList();
	}
	public int repName(String repName){
		return dpDao.repName(repName);
	}
	public int addDp(Department dp){
		return dpDao.addDp(dp);
	}
	public int editDp(Department dp){
		return dpDao.editDp(dp);
	}
}
