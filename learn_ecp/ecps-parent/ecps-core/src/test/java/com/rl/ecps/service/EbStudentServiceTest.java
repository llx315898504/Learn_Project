package com.rl.ecps.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rl.ecps.model.EbStudent;

@RunWith(value=SpringJUnit4ClassRunner.class)//使用spring和junit整合的运行器
@ContextConfiguration(locations={"classpath:beans.xml"})//指定spring的配置文件的位置
public class EbStudentServiceTest {
	@Autowired
	private EbStudentService ebStudentService;

	@Test
	public void testSave() {
		EbStudent ebStudent = new EbStudent();
		ebStudent.setStdId(100);
		ebStudent.setStdName("张飞");
		ebStudentService.save(ebStudent);
	}

}
