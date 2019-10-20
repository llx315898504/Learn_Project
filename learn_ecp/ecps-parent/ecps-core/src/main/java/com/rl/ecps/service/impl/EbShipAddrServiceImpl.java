package com.rl.ecps.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.service.EbShipAddrService;
import com.rl.ecps.util.ECPSUtils;

import redis.clients.jedis.Jedis;

@Service
public class EbShipAddrServiceImpl implements EbShipAddrService {
    
	@Autowired
	private EbShipAddrDao dao;
	
	@Override
	public List<EbShipAddr> selectAddressByUserId(Long userId) {
		return dao.selectAddressByUserId(userId);
	}

	@Override
	public EbShipAddr selectAddrById(Long shipAddrId) {
		return dao.selectAddrById(shipAddrId);
	}

	@Override
	public void saveOrUpdateAddr(EbShipAddr addr) {
		if (addr.getDefaultAddr()==1) {
			dao.updateDefaultAddrByUserId(addr.getPtlUserId());
		}
		if (addr.getShipAddrId() == null) {
			dao.insert(addr);
		} else {
			dao.update(addr);
		}
	}

	@Override
	public void deleteAddrById(Long shipAddrId, Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("shipAddrId", shipAddrId);
		map.put("userId", userId);
		dao.deleteAddrById(map);
	}
	
	@Override
	public List<EbShipAddr> selectAddrByUserIdWithRedis(Long userId) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		List<EbShipAddr> addrList = new ArrayList<EbShipAddr>();
		List<String> addrIds = jedis.lrange("user:"+userId+":addrList", 0, -1);
		for(String addrId : addrIds){
			String shipName = jedis.hget("user:"+userId+":addr:" +addrId, "shipName");
			String province = jedis.hget("user:"+userId+":addr:" +addrId, "province");
			String city = jedis.hget("user:"+userId+":addr:" +addrId, "city");
			String district = jedis.hget("user:"+userId+":addr:" +addrId, "district");
			String addr = jedis.hget("user:"+userId+":addr:" +addrId, "addr");
			String zipCode = jedis.hget("user:"+userId+":addr:" +addrId, "zipCode");
			String phone = jedis.hget("user:"+userId+":addr:" +addrId, "phone");
			String defaultAddr = jedis.hget("user:"+userId+":addr:" +addrId, "defaultAddr");
			EbShipAddr addrObj = new EbShipAddr();
			addrObj.setShipAddrId(new Long(addrId));
			addrObj.setShipName(shipName);
			addrObj.setProvince(province);
			addrObj.setCity(city);
			addrObj.setDistrict(district);
			addrObj.setAddr(addr);
			addrObj.setZipCode(zipCode);
			addrObj.setPhone(phone);
			addrObj.setDefaultAddr(new Short(defaultAddr));
			addrList.add(addrObj);
			
		}
		
		
		return addrList;
	}

	@Override
	public EbShipAddr selectAddrByIdWithRedis(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Override
	public EbShipAddr selectAddrByIdWithRedis(Long userId, Long addrId) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis(ECPSUtils.readProp("redis_ip"), new Integer(ECPSUtils.readProp("redis_port")));
		String shipName = jedis.hget("user:"+userId+":addr:" +addrId, "shipName");
		String province = jedis.hget("user:"+userId+":addr:" +addrId, "province");
		String city = jedis.hget("user:"+userId+":addr:" +addrId, "city");
		String district = jedis.hget("user:"+userId+":addr:" +addrId, "district");
		String addr = jedis.hget("user:"+userId+":addr:" +addrId, "addr");
		String zipCode = jedis.hget("user:"+userId+":addr:" +addrId, "zipCode");
		String phone = jedis.hget("user:"+userId+":addr:" +addrId, "phone");
		String defaultAddr = jedis.hget("user:"+userId+":addr:" +addrId, "defaultAddr");
		String provText = jedis.hget("user:"+userId+":addr:" +addrId, "provText");
		String cityText = jedis.hget("user:"+userId+":addr:" +addrId, "cityText");
		String distText = jedis.hget("user:"+userId+":addr:" +addrId, "distText");
		EbShipAddr addrObj = new EbShipAddr();
		addrObj.setShipAddrId(new Long(addrId));
		addrObj.setShipName(shipName);
		addrObj.setProvince(province);
		addrObj.setCity(city);
		addrObj.setDistrict(district);
		addrObj.setAddr(addr);
		addrObj.setZipCode(zipCode);
		addrObj.setPhone(phone);
		addrObj.setDefaultAddr(new Short(defaultAddr));
		addrObj.setProvince(provText);
		addrObj.setCity(cityText);
		addrObj.setDistrict(distText);
		return addrObj;
	}*/

}
