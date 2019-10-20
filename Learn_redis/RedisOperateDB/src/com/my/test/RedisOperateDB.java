package com.my.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.my.model.Employee;
import com.my.model.Team;
import com.my.util.HiberUtils;

public class RedisOperateDB {

	@Test
	public void exportTable() {
		//创建hibernate的配置对象
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//创建导出对象
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
	
	/**
	 * 
	 */
	@Test
	public void addEmp(){
		SessionFactory factory = HiberUtils.getSessionFactory();
		Session session = factory.openSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		
		try {
			
			Team team=new Team();
			team.setName("美女对");
			team.setAddress("云南");
			session.save(team);
			Employee emp=new Employee();
			emp.setName("甄姬");
			emp.setAge(30);
			emp.setGender("女");
			emp.setTeam(team);
			//保存emp
			session.save(emp);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally{
			session.close();
		}
		
	}
	
	
	/**
	 *查询球队信息（单表查询）
	 *
	 */
	@Test
	public void queryredisDB(){
		Jedis js=new Jedis("192.168.49.128", 6379);
		List<String> list=js.lrange("team:team_id", 0, -1);
		List<Team> teams=new ArrayList<Team>();
		for(String team_id:list){
			String name=js.hget("team:"+team_id, "name");
			String address=js.hget("team:"+team_id, "address");
			Team team=new Team();
			team.setTeamId(new Integer(Integer.valueOf(team_id)));
			team.setName(name);
			team.setAddress(address);
			teams.add(team);
			js.close();
		}
		System.out.println(teams);
	}
	
	
	
	/**
	 * 根据队员信息查询球队信息（多对一的方式）
	 *
	 */
	@Test
	public void queryredisDBManyTOOne(){
		Jedis js=new Jedis("192.168.49.128", 6379);
			String empId=js.hget("emp:100", "empid");
			String name=js.hget("emp:100", "name");
			String gender=js.hget("emp:100", "gender");
			String age=js.hget("emp:100", "age");
			String teamID=js.hget("emp:100", "team_id");
			Employee employee=new Employee();
			employee.setAge(new Integer(Integer.valueOf(age)));
			employee.setEmpId(new Integer(Integer.valueOf(empId)));
			employee.setName(name);
			employee.setGender(gender);
			String Tname=js.hget("team:"+teamID, "name");
			String address=js.hget("team:"+teamID, "address");
			Team team=new Team();
			team.setTeamId(new Integer(Integer.valueOf(teamID)));
			team.setName(Tname);
			team.setAddress(address);
			employee.setTeam(team);
			System.out.println(employee);
			js.close();
		}
	
	
	
	/**
	 * 根据球队信息查询球员信息（一对多的方式）
	 *
	 */
	@Test
	public void queryredisDBOneTOMany(){
		Jedis js=new Jedis("192.168.49.128", 6379);
		String tname=js.hget("team:2", "name");
		String address=js.hget("team:2", "address");
		Team team=new Team();
		team.setTeamId(new Integer(2));
		team.setName(tname);
		team.setAddress(address);
		List<String> list=js.lrange("temp:2:emp", 0, -1);
		Set<Employee>set=new HashSet<Employee>();
		for(String empid:list) {
			System.out.println(empid);
			String empId=js.hget("emp:"+empid, "empid");
			String name=js.hget("emp:"+empid, "name");
			String gender=js.hget("emp:"+empid, "gender");
			String age=js.hget("emp:"+empid, "age");
			Employee employee=new Employee();
			employee.setAge(new Integer(Integer.valueOf(age)));
			employee.setEmpId(new Integer(Integer.valueOf(empId)));
			employee.setName(name);
			employee.setGender(gender);
			System.out.println(employee);
			set.add(employee);
		}
		System.out.println(team);
		team.setEmpSet(set);
		System.out.println(team);
		js.close();
}
	/**
	 * 将关系型数据库的数据同步到redis数据库中
	 * 
	 */
	@Test
	public void importRedis(){
		SessionFactory factory = HiberUtils.getSessionFactory();
		Session session = factory.openSession();
		Jedis jedis = new Jedis("192.168.49.128", 6379);
		try {
			Team team = (Team) session.get(Team.class, 1);
			jedis.hset("team:"+team.getTeamId(), "team_id", team.getTeamId()+"");
			jedis.hset("team:"+team.getTeamId(), "name", team.getName());
			jedis.hset("team:"+team.getTeamId(), "address", team.getAddress());
			Set<Employee> empSet = team.getEmpSet();
			for(Employee emp : empSet){
				jedis.hset("emp:"+emp.getEmpId(), "emp_id", emp.getEmpId()+"");
				jedis.hset("emp:"+emp.getEmpId(), "name", emp.getName());
				jedis.hset("emp:"+emp.getEmpId(), "gender", emp.getGender());
				jedis.hset("emp:"+emp.getEmpId(), "age", emp.getAge()+"");
				jedis.lpush("team:1:emp",emp.getEmpId()+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			jedis.close();
		}
		
	}
	
	
}
