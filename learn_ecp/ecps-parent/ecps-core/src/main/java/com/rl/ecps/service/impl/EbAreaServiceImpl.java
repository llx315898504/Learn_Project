package com.rl.ecps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbAreaDao;
import com.rl.ecps.model.EbArea;
import com.rl.ecps.service.EbAreaService;
@Service
public class EbAreaServiceImpl implements EbAreaService {
	
	@Autowired
	private EbAreaDao dao;

	@Override
	public List<EbArea> selectAreaByPid(long pid) {
		return dao.selectAreaByPid(pid);
	}

}
