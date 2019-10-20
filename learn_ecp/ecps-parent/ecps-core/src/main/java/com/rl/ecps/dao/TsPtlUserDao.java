package com.rl.ecps.dao;

import java.util.Map;

import com.rl.ecps.model.TsPtlUser;

public interface TsPtlUserDao {
	
	/**
	 * 根据用户名和密码查询用户记录
	 * 
	 * @param map
	 * @return
	 */
	public  TsPtlUser selectUserByNameAndPassword(Map<String, Object> map);

}
