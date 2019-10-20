package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.EbShipAddr;

/**
 * 收货地址管理接口
 * 
 * @author 86150
 *
 */
public interface EbShipAddrService {
	
	/**
	 * 根据用户Id查询收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public List<EbShipAddr> selectAddressByUserId(Long userId);
	
	
	/**
	 * 根据用户Id从缓存中查询收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public EbShipAddr selectAddrByIdWithRedis(Long userId);
	
	
	/**
	 * 根据用户Id从缓存中查询收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public List<EbShipAddr> selectAddrByUserIdWithRedis(Long userId);
	
	
	
	/**
	 * 根据收货地址Id查询收货地址信息
	 * 
	 * @param shipAddrId
	 * @return
	 */
	public EbShipAddr selectAddrById(Long shipAddrId);
	
	
	/**
	 * 新增或修改用户收货地址
	 * 
	 * @param addr
	 */
	public void saveOrUpdateAddr(EbShipAddr addr);
	
	
	/**
	 * 根据用户Id和地址Id删除收货地址
	 * 
	 * @param shipAddrId
	 * @param userId
	 */
	public void deleteAddrById(Long shipAddrId,Long userId);

}