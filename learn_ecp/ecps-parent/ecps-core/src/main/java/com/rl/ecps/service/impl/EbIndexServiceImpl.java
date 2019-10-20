package com.rl.ecps.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.service.EbIndexService;
import com.rl.ecps.util.ECPSUtils;

@Service
public class EbIndexServiceImpl implements EbIndexService {
	
	@Autowired
	private EbItemDao ebItemDao;
	

	@Override
	public void importIndex() throws Exception{
		//查询商品的集合（用于首页筛选查询）
		List<EbItem> eBItemList = ebItemDao.selectIsSelectItemList();
		//创建solr客户端对象
        HttpSolrClient httpSolrClient = ECPSUtils.getHttpSolrClient();
        for (EbItem ebItem : eBItemList) {
        	SolrInputDocument sd = new SolrInputDocument();
			sd.addField("id", ebItem.getItemId());
			sd.addField("item_name", ebItem.getItemName());
			sd.addField("sku_price", ebItem.getSkuPrice().floatValue());
			sd.addField("item_keywords", ebItem.getKeywords());
			sd.addField("imgs", ebItem.getImgs());
			sd.addField("promotion", ebItem.getPromotion());
			sd.addField("brand_id", ebItem.getBrandId());
			String paraVals = "";
			for(EbParaValue para : ebItem.getParaList()){
				paraVals = paraVals + para.getParaValue() + " ";
			}
			sd.addField("para_vals", paraVals);
			httpSolrClient.add(sd);
		}
        httpSolrClient.commit();
	}

	@Override
	public void addtIndex() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteIndex() {
		// TODO Auto-generated method stub

	}

}
