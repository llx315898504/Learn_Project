package com.rl.ecps.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.common.Page;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.query.ItemReqVo;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.util.ECPSUtils;

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
	private EbItemService ebItemService;
	@Autowired
	private EbBrandService brandService;
	@Autowired
	private EbFeatureService featureService;
	
	/**
	 * 条件查询商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryItemByCondtion.do")
	public String queryItemByCondtion(ItemReqVo itemReqVo,Model model){
		if(itemReqVo.getPageNo()==null){
			itemReqVo.setPageNo(1);
		}
		//查询商品列表
		Page page = ebItemService.queryItemByCondtion(itemReqVo);
		//查询商品品牌列表
		List<EbBrand> bList=brandService.selectBrandAll();
		model.addAttribute("page", page);
		model.addAttribute("bList",bList);
		model.addAttribute("ReqVo",itemReqVo);
		return "item/list";
	}
	
	
	/**
	 * 跳转到添加商品的页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddItem.do")
	public String addItem(Model model) {
		// 查询商品品牌列表
		List<EbBrand> bList = brandService.selectBrandAll();
		//查询商品的普通属性列表
		List<EbFeature> commFeatureList = featureService.selectCommFeature();
		//查询商品的特殊属性列表
		List<EbFeature> specFeatureList = featureService.selectSpecFeature();
		model.addAttribute("bList", bList);
		model.addAttribute("commFeatureList", commFeatureList);
		model.addAttribute("specFeatureList", specFeatureList);
		return "item/addItem";
	}
	
	/**
	 * 新增商品
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/addItem.do")
	public String addBrand(EbItem ebItem,EbItemClob ebItemClob ,Integer divNum,HttpServletRequest request) {
		//生成商品的编号
		ebItem.setItemNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		//设置商品基本数据
		//默认是待审核，未上架
		ebItem.setAuditStatus(new Short("0"));
		ebItem.setShowStatus(new Short("1"));
		ebItem.setCreateTime(new Date());
		ebItem.setUpdateTime(new Date());
		//接收第三个table参数
		//从后台获取商品普通属性的集合，这个集合正是table_3中所展示的属性
		List<EbFeature> commFeatureList = featureService.selectCommFeature();
		List<EbParaValue> ebParaList = new ArrayList<EbParaValue>();
		for (EbFeature ebFeature : commFeatureList) {
			//获取属性id(table_3文本域中的name属性的值)
			Long featureId=ebFeature.getFeatureId();
			if(ebFeature.getInputType()==3){
				String[] paraValues = request.getParameterValues(featureId+"");
				if(paraValues!=null&&paraValues.length>0){
					String paraValue="";
					for (String value : paraValues) {
						paraValue=paraValue+value+",";
					}
					//去掉最后一个空格（巧妙的去空格方法）
					paraValue=paraValue.substring(0, paraValue.length()-1);
					EbParaValue ebParaValue=new EbParaValue();
					ebParaValue.setFeatureId(featureId);
					ebParaValue.setParaValue(paraValue);
					ebParaList.add(ebParaValue);
				}
			}else{
				//获取单选和下拉的值
				String paraVal=request.getParameter(featureId+"");
				if(StringUtils.isNotBlank(paraVal)){
					//创建商品属性值的表对象
					EbParaValue ebParaValue=new EbParaValue();
					ebParaValue.setFeatureId(featureId);
					ebParaValue.setParaValue(paraVal);
					ebParaList.add(ebParaValue);
					
				}
			}
		}
		//接收第四个table参数
		//查询商品的特殊属性列表
		List<EbFeature> specFeatureList = featureService.selectSpecFeature();
		List<EbSku> ebSkuList =new ArrayList<EbSku>();
		for (int i = 0; i <=divNum; i++) {
			//获取必填字段(若必填字段为空，说明页面存在删除div的操作，divNum不连续)
			String skuPrice=request.getParameter("skuPrice"+i);
			String stockInventory=request.getParameter("stockInventory"+i);
			//判断divNum没有断档的情况
			if(StringUtils.isNotBlank(skuPrice)&&StringUtils.isNotBlank(stockInventory)){
				String skuType=request.getParameter("skuType"+i);
				String showStatus=request.getParameter("showStatus"+i);
				String sort=request.getParameter("sort"+i);
				String marketPrice=request.getParameter("marketPrice"+i);
				String skuUpperLimit=request.getParameter("skuUpperLimit"+i);
				String sku=request.getParameter("sku"+i);
				String location=request.getParameter("location"+i);
				EbSku ebSku=new EbSku();
				//设置对象的值时一定要判断是否为空，防止转换出错
				ebSku.setSkuPrice(new BigDecimal(skuPrice));
				ebSku.setStockInventory(new Integer(stockInventory));
				if(StringUtils.isNotBlank(skuType)){
					ebSku.setSkuType(new Short(skuType));
				}
				if(StringUtils.isNotBlank(showStatus)){
					ebSku.setShowStatus(new Short(showStatus));
				}
				if(StringUtils.isNotBlank(sort)){
					ebSku.setSkuSort(new Integer(sort));
				}
				if(StringUtils.isNotBlank(marketPrice)){
					ebSku.setMarketPrice(new BigDecimal(marketPrice));
				}
				if(StringUtils.isNotBlank(skuUpperLimit)){
					ebSku.setSkuUpperLimit(new Integer(skuUpperLimit));
				}
				ebSku.setSku(sku);
				ebSku.setLocation(location);
				List<EbSpecValue> EbSpecList = new ArrayList<EbSpecValue>();
				//遍历商品的特殊属性
				for (EbFeature ebFeature : specFeatureList) {
					//获得特殊属性的id
					Long featureId=ebFeature.getFeatureId();
					String specValue=request.getParameter(featureId+"specradio"+i);
				    EbSpecValue ebSpecValue=new EbSpecValue();
				    ebSpecValue.setFeatureId(featureId);
				    ebSpecValue.setSpecValue(specValue);
				    EbSpecList.add(ebSpecValue);
				}
				ebSku.setEbSpecList(EbSpecList);
				ebSkuList.add(ebSku);
			}
		}
		ebItemService.saveItem(ebItem, ebItemClob, ebParaList, ebSkuList);
		return "redirect:queryItemByCondtion.do?showStatus=1&auditStatus=1";
	}
	
	/**
	 * 商品审核列表
	 * 
	 * @param itemReqVo
	 * @param model
	 * @return
	 */
	@RequestMapping("/qryAuditList.do")
	public String qryAuditList(ItemReqVo itemReqVo,Model model){
		if(itemReqVo.getPageNo()==null){
			itemReqVo.setPageNo(1);
		}
		//查询商品列表
		Page page = ebItemService.queryItemByCondtion(itemReqVo);
		//查询商品品牌列表
		List<EbBrand> bList=brandService.selectBrandAll();
		model.addAttribute("page", page);
		model.addAttribute("bList",bList);
		model.addAttribute("ReqVo",itemReqVo);
		return "item/listAudit";
	}
	
	
	/**
	 * 商品审核
	 * 
	 * @param itemId
	 * @param auditStatus
	 * @param notes
	 * @return
	 */
	@RequestMapping("/auditItem.do")
	public String auditItem(Long itemId, Short auditStatus, String notes) {
		ebItemService.auditItem(itemId, auditStatus, notes);
		return "redirect:qryAuditList.do?showStatus=1&auditStatus=0";
	}
	
	/**
	 * 商品上下架
	 * 
	 * @param itemId
	 * @param showStatus
	 * @param notes
	 * @return
	 */
	@RequestMapping("/showItem.do")
	public String showItem(Long itemId, Short showStatus, String notes) {
		ebItemService.showItem(itemId, showStatus, notes);
        Short flag=1;
        if(showStatus==1){
        	flag=0;
        }
		return "redirect:queryItemByCondtion.do?showStatus="+flag+"&auditStatus=1";
	}
	
	@RequestMapping("/publishItem.do")
	public void publishItem(long itemId,PrintWriter out){
		String result=ebItemService.publishItem(itemId, ECPSUtils.readProp("ws_passWord"));
		out.write(result);
	}
}
