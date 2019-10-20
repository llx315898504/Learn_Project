package com.rl.ecps.service;

import java.util.Map;

import com.rl.ecps.model.TsPtlUser;

/**
 * 用户管理接口
 * 
 * @author 86150
 *
 */
public interface TsPtlUserService {
	
	/**
	 * 根据用户名和密码查询用户记录
	 * 
	 * @param map
	 * @return
	 */
	public  TsPtlUser selectUserByNameAndPassword(Map<String, Object> map);
}
