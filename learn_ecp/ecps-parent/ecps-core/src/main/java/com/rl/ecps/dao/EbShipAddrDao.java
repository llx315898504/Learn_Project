package com.rl.ecps.dao;

import java.util.List;
import java.util.Map;

import com.rl.ecps.model.EbShipAddr;

public interface EbShipAddrDao {
	
	/**
	 * 根据用户Id查询收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public List<EbShipAddr> selectAddressByUserId(Long userId);
	
	
	/**
	 * 根据收货地址Id查询收货地址信息
	 * 
	 * @param shipAddrId
	 * @return
	 */
	public EbShipAddr selectAddrById(Long shipAddrId);
	
	
	/**
	 * 新增用户收货地址
	 * 
	 * @param addr
	 */
	public void insert(EbShipAddr addr);
	
	
	/**
	 * 修改用户收货地址
	 * 
	 * @param addr
	 */
	public void update(EbShipAddr addr);
	
	
	/**
	 * 根据用户id设置默认收货地址
	 * 
	 * @param userId
	 */
	public void updateDefaultAddrByUserId(Long userId);
	
	
	/**
	 * 根据用户Id和地址Id删除收货地址
	 * 
	 * @param map
	 */
	public void deleteAddrById(Map<String , Object> map);
	
	
	/**
	 * 查询所有的收货地址
	 * 
	 * 
	 * @return
	 */
	public List<EbShipAddr> selectAllAddress();


}
