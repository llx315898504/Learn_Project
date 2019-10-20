package com.rl.ecps.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbCart;
import com.rl.ecps.service.EbCartService;
import com.rl.ecps.util.ECPSUtils;

/**
 * 购物车管理
 * 
 * @author 86150
 *
 */
@Controller
@RequestMapping("/cart")
public class EbCartController {
	
	@Autowired
	private EbCartService ebCartService;
	
	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 * @param quantity
	 */
	@RequestMapping("/addCart.do")
	public String addCart(HttpServletRequest request, HttpServletResponse response, Long skuId, Integer quantity){
		ebCartService.addCart(request, response, skuId, quantity);
		return "redirect:listCart.do";
	}
	
	
	/**
	 * 修改购物车中商品中的数量
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 * @param modQuantity
	 * @return
	 */
	@RequestMapping("/modifyCart.do")
	public String modifyCart(HttpServletRequest request, HttpServletResponse response,Long skuId, Integer modQuantity){
		ebCartService.modifyCart(request, response, skuId, modQuantity);
		return "redirect:listCart.do";
	}
	
	
	/**
	 * 移除购物车中的商品
	 * 
	 * @param request
	 * @param response
	 * @param skuId
	 * @return
	 */
	@RequestMapping("/removeCart.do")
	public String removeCart(HttpServletRequest request, HttpServletResponse response,Long skuId){
		ebCartService.removeCart(request, response, skuId);
		return "redirect:listCart.do";
	}
	
	
	/**
	 * 查询购物车列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/listCart.do")
	public String listCart(HttpServletRequest request, HttpServletResponse response,Model model){
		List<EbCart> cartList = ebCartService.selectCartList(request, response);
		//商品数量
		Integer itemNum=0;
		//总金额
		BigDecimal totalPrice=new BigDecimal(0);
		for (EbCart ebCart : cartList) {
			itemNum=itemNum+ebCart.getQuantity();
			totalPrice=totalPrice.add(ebCart.getEbSku().getSkuPrice().multiply(new BigDecimal(ebCart.getQuantity())));
		}
		model.addAttribute("cartList", cartList);
		model.addAttribute("itemNum", itemNum);
		model.addAttribute("totalPrice", totalPrice);
		return "shop/car";
	}
	
	
	/**
	 * 购物车中商品库存校验
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/validCart.do")
	public void validCart(HttpServletRequest request, HttpServletResponse response){
		String result = ebCartService.validCart(request, response);
		ECPSUtils.printAjax(response, result);
	}
	
}