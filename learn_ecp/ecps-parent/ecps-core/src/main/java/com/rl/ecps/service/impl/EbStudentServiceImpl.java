package com.rl.ecps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbStudentDao;
import com.rl.ecps.model.EbStudent;
import com.rl.ecps.service.EbStudentService;

@Service
public class EbStudentServiceImpl implements EbStudentService {
	@Autowired
	private EbStudentDao ebStudentDao;

	public void save(EbStudent ebStudent) {
		ebStudentDao.save(ebStudent);
	}

}
