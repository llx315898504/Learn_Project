package com.rl.ecps.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rl.ecps.model.EbCart;

/**
 * 购物车管理接口
 * 
 * @author 86150
 *
 */
public interface EbCartService {
	
	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 * @param quantity
	 */
	public void addCart(HttpServletRequest request,HttpServletResponse response,Long skuId,Integer quantity);
	
	
	
	/**
	 * 查询购物车列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public List<EbCart> selectCartList(HttpServletRequest request,HttpServletResponse response);
	
	
	/**
	 * 移除购物车中的商品
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 */
	public void removeCart(HttpServletRequest request,HttpServletResponse response,Long skuId);
	
	
	/**
	 * 修改购物车中商品的数量
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 * @param modQuantity
	 */
	public void modifyCart(HttpServletRequest request,HttpServletResponse response,Long skuId,Integer modQuantity);
	
	
	/**
	 * 清空购物车
	 * 
	 * @param request
	 * @param response
	 */
	public void clearCart(HttpServletRequest request,HttpServletResponse response);
	
	
	
	/**
	 * 购物车中商品库存校验
	 * 
	 * @param request
	 * @param response
	 */
	public String validCart(HttpServletRequest request,HttpServletResponse response);

}
