package com.my.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.my.model.Person;
import com.my.model.QueryCondition;
import com.my.sql.PersonSql;

public interface PersonDao {
	

 @Select("select * from t_person t where t.id=#{id}")
 @Results(value={@Result(column="id",property="id",id=true),
		@Result(column="name",property="name"),
		@Result(column="gender",property="gender"),
		@Result(column="age",property="age"),
		@Result(column="address",property="address"),
		@Result(column="birthday",property="birthday")
 })
 public  Person  queryPersonById(Integer id);
 
 @Select("select * from t_person t where t.gender=#{gender} and t.birthday<#{birthday}")
 @Results(value={@Result(column="id",property="id",id=true),
		@Result(column="name",property="name"),
		@Result(column="gender",property="gender"),
		@Result(column="age",property="age"),
		@Result(column="address",property="address"),
		@Result(column="birthday",property="birthday")
 })
 public  List<Person>  queryPersonByParam(QueryCondition qc);
 
 
 @Select("select * from t_person t where t.name like '%${name}%'")
 @Results(value={@Result(column="id",property="id",id=true),
		@Result(column="name",property="name"),
		@Result(column="gender",property="gender"),
		@Result(column="age",property="age"),
		@Result(column="address",property="address"),
		@Result(column="birthday",property="birthday")
 })
 public  List<Person>  queryPersonByName(QueryCondition qc);
 
 @Insert("insert into t_person(id,name,age,gender,address,birthday)"+
 "values(#{id},#{name},#{age},#{gender},#{address},#{birthday}) ")
 @SelectKey(before = false, keyProperty = "id", resultType =Integer.class, statement = {"select  last_insert_id()"})
 public  void addPerson(Person p);
 
 @Update("update t_person t set t.name=#{name}," +
 		"t.age=#{age},t.address=#{address}  where t.id=#{id}")
 public void updatePerson(Person p);
 
 @Delete("delete from t_person where id=#{id}")
 public  void  deletePerson(Integer id);
 
 @SelectProvider(method = "selectPersonByDynamicSql", type =PersonSql.class)
 @Results(value={@Result(column="id",property="id",id=true),
			@Result(column="name",property="name"),
			@Result(column="gender",property="gender"),
			@Result(column="age",property="age"),
			@Result(column="address",property="address"),
			@Result(column="birthday",property="birthday")
	 })//@Results不能做关联查询
 public List<Person> selectPersonByDynamic(Map<String, Object> map);
 
 @Select("SELECT  * from  t_person p,t_order o WHERE p.id=o.id and p.id=#{id}")
 @ResultMap("com.my.mapper.PersonMapper.selectOrderByPersonIdRM")
 //注解开发如果多表关联查询还要依赖配置文件
 public Person  selectOrderByPersonId(Integer id);
}
