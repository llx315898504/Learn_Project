package com.my.sql;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlBuilder;

/**
 * 组装动态sql类
 * 
 * 
 * @author Administrator
 *
 */
public class PersonSql {
	
	/**
	 * 动态组装多条件查询用户的sql
	 * 
	 * @param map
	 * @return
	 */
	public String selectPersonByDynamicSql(Map<String, Object> map) {
		// 获取传递的参数列表
		String name = (String) map.get("name");
		Integer gender = (Integer) map.get("gender");
		String address = (String) map.get("address");
		Date birthday = (Date) map.get("birthday");
		SqlBuilder.BEGIN();
		SqlBuilder.SELECT("*");
		SqlBuilder.FROM("t_person");
		if (name!=null) {
			//SqlBuilder不支持${}的写法
			SqlBuilder.WHERE("name like '%" + name + "%'");
		}
		if (address!=null) {
			SqlBuilder.WHERE("address like '%" + address + "%'");
		}
		if (gender != null) {
			SqlBuilder.WHERE("gender=#{gender}");
		}
		if (birthday != null) {
			SqlBuilder.WHERE("birthday<#{birthday}");
		}
		return SqlBuilder.SQL();
	}

}
