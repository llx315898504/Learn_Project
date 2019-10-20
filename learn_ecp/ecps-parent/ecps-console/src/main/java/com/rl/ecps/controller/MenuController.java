package com.rl.ecps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 * 
 * @author 86150
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	
	/**
	 * 跳转到商品管理首页
	 * @return
	 */
	@RequestMapping("/toItemIndex.do")
	public String toIndex(){
		return "item/index";
	}
	
	/**
	 * 跳转到学生管理首页
	 * @return
	 */
	@RequestMapping("/toStudentIndex.do")
	public String toStudentIndex(){
		return "student/index";
	}
	
	/**
	 * 跳转到课程管理首页
	 * @return
	 */
	@RequestMapping("/toCourseIndex.do")
	public String toCourseIndex(){
		return "course/index";
	}
	
	/**
	 * 跳转到订单管理首页
	 * @return
	 */
	@RequestMapping("/toOrderIndex.do")
	public String toOrderIndex(){
		return "order/index";
	}
	
	/**
	 * 跳转到号卡管理首页
	 * @return
	 */
	@RequestMapping("/toSimcardIndex.do")
	public String toSimcardIndex(){
		return "simcard/index";
	}
	
	/**
	 * 跳转到代客下单管理首页
	 * @return
	 */
	@RequestMapping("/toValetorderIndex.do")
	public String toValetorderIndex(){
		return "valetorder/index";
	}
	
	/**
	 * 跳转到会员管理首页
	 * @return
	 */
	@RequestMapping("/toPtluserIndex.do")
	public String toPtluserIndex(){
		return "ptluser/index";
	}
	
	/**
	 * 跳转到合作伙伴管理首页
	 * @return
	 */
	@RequestMapping("/toRelationalIndex.do")
	public String toRelationalIndex(){
		return "relational/index";
	}
	
	/**
	 * 跳转到支付管理首页
	 * @return
	 */
	@RequestMapping("/toPaymentIndex.do")
	public String toPaymentIndex(){
		return "payment/index";
	}
	
	/**
	 * 跳转到广告管理首页
	 * @return
	 */
	@RequestMapping("/toAdvertisementIndex.do")
	public String toAdvertisementIndex(){
		return "advertisement/index";
	}
	
	/**
	 * 跳转到促销活动管理首页
	 * @return
	 */
	@RequestMapping("/toPromotionIndex.do")
	public String toPromotionIndex(){
		return "promotion/index";
	}
	
	/**
	 * 跳转到系统配置管理首页
	 * @return
	 */
	@RequestMapping("/toContentsetIndex.do")
	public String toContentsetIndex(){
		return "contentset/index";
	}
	
	/**
	 * 跳转到报表管理首页
	 * @return
	 */
	@RequestMapping("/toReportIndex.do")
	public String toReportIndex(){
		return "report/index";
	}
	
	
	/**
	 * 跳转到管理系统主页
	 * @return
	 */
	@RequestMapping("/toMainMenu.do")
	public String toMainMenu(){
		return "mainMenu";
	}
	
	
}
