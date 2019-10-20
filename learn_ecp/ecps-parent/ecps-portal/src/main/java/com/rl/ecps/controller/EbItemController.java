package com.rl.ecps.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.util.ECPSUtils;

import net.sf.json.JSONObject;

/**
 * 商品管理
 * 
 * @author 86150
 *
 */
@Controller
@RequestMapping("/item")
public class EbItemController {
	@Autowired
	private EbBrandService brandService;
	@Autowired
	private EbFeatureService ebFeatureService;
	@Autowired
	private EbItemService ebItemService;
	@Autowired
	private EbSkuService ebSkuService;
	
	
	/**
	 * 跳转到商品首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toItemIndex.do")
	public String toIndex(Model model){
		//查询商品的品牌
		List<EbBrand> bList = brandService.selectBrandAll();
		//查询商品的筛选属性
		List<EbFeature> fList= ebFeatureService.qrySelectFeature();
		model.addAttribute("bList", bList);
		model.addAttribute("fList", fList);
		return "index";
	}
	
	/**
	 * 根据索引查询商品记录
	 * 
	 * @param price
	 * @param brandId
	 * @param keyWords
	 * @param paraVals
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/listItem.do")
	public String listItem(String price,Long brandId,String keyWords,String paraVals,Model model) throws Exception{
		model.addAttribute("itemList", ebItemService.selectEbItemByIndex(price, brandId, keyWords, paraVals));
		return "phoneClassification";
	}
	
	
	/**
	 * 根据商品Id查询商品详情
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewItemDetail.do")
	public String viewItemDetail(Long itemId, Model model){
		EbItem item = ebItemService.selectItemDetailById(itemId);
		model.addAttribute("item", item);
		return "productDetail";
	}
	
	
	/**
	 * 根据最小销售单元ID查询最小销售单元数据
	 * 
	 * @param skuId
	 * @param response
	 */
	@RequestMapping("/getSkuById.do")
	public void selectSkuById(Long skuId,HttpServletResponse response){
		EbSku ebSku = ebSkuService.selectSkuByIdWithRedis(skuId);
		JSONObject jo=new JSONObject();
		jo.accumulate("ebSku", ebSku);
		String result=jo.toString();
		ECPSUtils.printAjax(response, result);
	}
	
}