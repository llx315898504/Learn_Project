package com.rl.ecps.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.service.EbBrandService;

/**
 * 品牌管理
 * 
 * @author 86150
 *
 */
@Controller
@RequestMapping("/brand")
public class EbBrandController {
	
	@Autowired
	private EbBrandService brandService;
	
	/**
	 * 查询品牌
	 * @return
	 */
	@RequestMapping("/listBrand.do")
	public String listBrand(Model model){
		List<EbBrand> bList = brandService.selectBrandAll();
		model.addAttribute("bList", bList);
		return "item/listbrand";
	}
	
	
	/**
	 * 跳转到添加品牌的页面
	 * @return
	 */
	@RequestMapping("/toAddBrand.do")
	public String toAddBrand(){
		return "item/addbrand";
	}
	
	/**
	 * 验证品牌的名称的重复性
	 * @param brandName
	 * @param out
	 */
	@RequestMapping("/validBrandName.do")
	public void validBrandName(String brandName, PrintWriter out){
		List<EbBrand> bList = brandService.selectBrandByName(brandName);
		String flag = "yes";
		if(bList.size() > 0){
			flag = "no";
		}
		out.write(flag);
	}
	
	/**
	 * 品牌添加
	 * @param brand
	 * @return
	 */
	@RequestMapping("/addBrand.do")
	public String addBrand(EbBrand brand){
		brandService.saveBrand(brand);
		return "redirect:listBrand.do";
	}
	
	/**
	 * 根据品牌ID删除品牌记录
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/deleteBrandById.do")
	public String deleteBrandById(Long brandId){
		brandService.deleteBrandById(brandId);
		return "redirect:listBrand.do";
	}
	
	/**
	 * 更新品牌记录
	 * 
	 * @param ebBrand
	 * @return
	 */
	@RequestMapping("/updateBrand.do")
	public String updateBrand(EbBrand ebBrand){
		brandService.updateBrand(ebBrand);
		return "redirect:listBrand.do";
	}
	
	
	/**
	 * 跳转到添加品牌的页面
	 * @return
	 */
	@RequestMapping("/toEditBrand.do")
	public String toEditBrand(Long brandId,Model model){
		EbBrand ebBrand = brandService.selectBrandById(brandId);
		model.addAttribute("brand", ebBrand);
		return "item/editbrand";
	}

}
