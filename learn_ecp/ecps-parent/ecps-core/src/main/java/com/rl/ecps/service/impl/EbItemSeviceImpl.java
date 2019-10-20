package com.rl.ecps.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.common.Page;
import com.rl.ecps.dao.EbConsoleLogDao;
import com.rl.ecps.dao.EbItemClobDao;
import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.dao.EbParaValueDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbConsoleLog;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.query.ItemReqVo;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.stub.EbWSItemService;
import com.rl.ecps.stub.EbWSItemServiceService;
import com.rl.ecps.util.ECPSUtils;
/**
 * 商品接口
 * 
 * @author 86150
 *
 */
@Service
public class EbItemSeviceImpl implements EbItemService {

	@Autowired
	private EbItemDao ebItemDao;
	@Autowired
	private EbItemClobDao ebItemClobDao;
	@Autowired
	private EbParaValueDao ebParaValueDao;
	@Autowired
	private EbSkuDao ebSkuDao;
	@Autowired
	private EbConsoleLogDao ebConsoleLogDao;
	
	@Override
	public Page queryItemByCondtion(ItemReqVo itemReqVo) {
		//分页查询对象
		Page page=new Page();
		//查询总记录数
		page.setTotalCount(ebItemDao.queryItemByCondtionCount(itemReqVo));
		page.setPageNo(itemReqVo.getPageNo());
		itemReqVo.setStartNum(page.getStartNum());
		itemReqVo.setEndNum(page.getEndNum());
		//查询商品列表
		page.setResultlist(ebItemDao.queryItemByCondtion(itemReqVo));
		return page;
	}

	@Override
	public void saveItem(EbItem ebItem, EbItemClob ebItemClob, List<EbParaValue> ebParaList, List<EbSku> ebSkuList) {
		//添加商品信息
		ebItemDao.saveItem(ebItem);
		ebItemClob.setItemId(ebItem.getItemId());
		//添加商品大字段数据
		ebItemClobDao.saveClobItem(ebItemClob);
		//添加商品普通属性
		ebParaValueDao.saveParaValue(ebParaList, ebItem.getItemId());
		// 添加最小销售单元数据
		ebSkuDao.saveEbSku(ebSkuList, ebItem.getItemId());
	}

	@Override
	public void auditItem(Long itemId, Short auditStatus, String itemNote) {
		EbItem ebItem=new EbItem();
		ebItem.setItemId(itemId);
		ebItem.setAuditStatus(auditStatus);
		ebItem.setCheckTime(new Date());
		ebItemDao.updateEbItem(ebItem);
		EbConsoleLog log=new EbConsoleLog();
		log.setEntityId(itemId);
		log.setEntityName("商品表");
		log.setNotes(itemNote);
		log.setOpTime(new Date());
		log.setOpType("审核");
		log.setTableName("EB_ITEM");
		ebConsoleLogDao.saveConsoleLog(log);
	}
	
	
	@Override
	public void showItem(Long itemId, Short showStatus, String itemNote) {
		EbItem ebItem=new EbItem();
		ebItem.setItemId(itemId);
		ebItem.setShowStatus(showStatus);
		ebItem.setCheckTime(new Date());
		ebItemDao.updateEbItem(ebItem);
		EbConsoleLog log=new EbConsoleLog();
		log.setEntityId(itemId);
		log.setEntityName("商品表");
		log.setNotes(itemNote);
		log.setOpTime(new Date());
		log.setOpType("上下架");
		log.setTableName("EB_ITEM");
		ebConsoleLogDao.saveConsoleLog(log);
	}

	@Override
	public List<EbItem> selectEbItemByIndex(String price,Long brandId, String keyWords, String paraVals) throws Exception {
		HttpSolrClient sc = ECPSUtils.getHttpSolrClient();
		List<EbItem>  ebItemList=new ArrayList<EbItem>();
		SolrQuery sq=new SolrQuery();
		//价格筛选
		if(StringUtils.isNotBlank(price)){
		  String priceArea []=price.split("-");
		  sq.set("fq", "sku_price:["+priceArea[0]+" TO "+priceArea[1]+" ]");
		}
		//
		String querySql="*:*";
		//品牌筛选
		if(brandId!=null){
			querySql="brand_id:"+brandId;
		}
		//关键字筛选
		if(StringUtils.isNotBlank(keyWords)){
			if("*:*".equals(querySql)){
				querySql="item_keywords:"+keyWords;
			}else{
				querySql=querySql+" AND item_keywords:"+keyWords;
			}
		}
		//属性筛选
		if(StringUtils.isNotBlank(paraVals)){
			String pv[]=paraVals.split(",");
			String pvSql="";
			for (String paraVal : pv) {
				pvSql=pvSql+"para_vals:"+paraVal+" AND ";
			}
			//去掉最后一个“AND”
			pvSql=pvSql.substring(0,pvSql.lastIndexOf(" AND "));
			if("*:*".equals(querySql)){
				querySql=pvSql;
			}else{
				querySql=querySql+" AND "+pvSql;
			}
		}
		//按照Id逆序排序
		sq.setSort("id", ORDER.desc);
		//设置高亮
		sq.setHighlight(true);
		sq.addHighlightField("item_name");
		sq.addHighlightField("promotion");
		sq.setHighlightSimplePre("<font color='red'>");
		sq.setHighlightSimplePost("</font>");
		//设置查询条件
		sq.setQuery(querySql);
		//查询索引库
		QueryResponse response = sc.query(sq);
		//获得查询结果集
		SolrDocumentList documentList = response.getResults();
		//遍历结果集
		for (SolrDocument solrDocument : documentList) {
			String itemId=(String) solrDocument.getFieldValue("id");
			String itemName=(String) solrDocument.getFieldValue("item_name");
			Long brandId1=(Long) solrDocument.getFieldValue("brand_id");
			String promotion=(String) solrDocument.getFieldValue("promotion");
			String imgs=(String) solrDocument.getFieldValue("imgs");
			//返回高亮数据处理
			Map<String, Map<String, List<String>>>  hlMap = response.getHighlighting();
			if(hlMap != null){
				Map<String, List<String>> listMap = hlMap.get(itemId);
				if(listMap != null){
					List<String> iList = listMap.get("item_name");
					if(iList != null && iList.size() > 0){
						String hlStr = "";
						for(String hl : iList){
							hlStr = hlStr + hl;
						}
						itemName = hlStr;
					}
					List<String> pList = listMap.get("promotion");
					if(pList != null && pList.size() > 0){
						String hlStr = "";
						for(String hl : pList){
							hlStr = hlStr + hl;
						}
						promotion = hlStr;
					}
				}
			}
			String skuPrice=solrDocument.getFieldValue("sku_price").toString();
			skuPrice=skuPrice.substring(1,skuPrice.length()-1);
			EbItem ebItem=new EbItem();
			ebItem.setItemId(Long.parseLong(itemId));
			ebItem.setItemName(itemName);
			ebItem.setBrandId(brandId1);
			ebItem.setImgs(imgs);
			ebItem.setSkuPrice(new BigDecimal(skuPrice));
			ebItem.setPromotion(promotion);
			ebItemList.add(ebItem);
		}
		return ebItemList;
	}

	@Override
	public EbItem selectItemDetailById(Long itemId) {
		return ebItemDao.selectItemDetailById(itemId);
	}

	@Override
	public String publishItem(Long itemId, String passWord) {
		//创建服务访问点集合对象
		EbWSItemServiceService ebWSItemServiceService=new EbWSItemServiceService();
		//调用get加上服务访问点的name(EbWSItemServicePort),即getEbWSItemServicePort()获取服务访问类
		EbWSItemService ebWSItemService = ebWSItemServiceService.getEbWSItemServicePort();
		return ebWSItemService.publishItem(itemId, passWord);
	}
}