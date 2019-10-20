package com.rl.ecps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.service.EbFeatureService;
/**
 * 
 * @author 86150
 *
 */
@Service
public class EbFeatureSeviceImpl implements EbFeatureService{

	@Autowired
	private EbFeatureDao ebFeatureDao;

	@Override
	public List<EbFeature> selectCommFeature() {
		return ebFeatureDao.selectCommFeature();
	}

	@Override
	public List<EbFeature> selectSpecFeature() {
		return ebFeatureDao.selectSpecFeature();
	}

	@Override
	public List<EbFeature> qrySelectFeature() {
		return ebFeatureDao.qrySelectFeature();
	}
	
	

}
