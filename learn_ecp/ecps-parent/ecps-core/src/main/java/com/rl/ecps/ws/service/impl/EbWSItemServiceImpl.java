package com.rl.ecps.ws.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.util.ECPSUtils;
import com.rl.ecps.util.FMutil;
import com.rl.ecps.ws.service.EbWSItemService;
@Service
public class EbWSItemServiceImpl implements EbWSItemService {
	@Autowired
	private EbItemDao itemDao;

	@Override
	public String publishItem(Long itemId, String passWord) throws Exception {
		String result="success";
		Map<String, Object> map=new HashMap<>();
		try {
			if(StringUtils.contains(passWord, ECPSUtils.readProp("ws_passWord"))){
				EbItem item = itemDao.selectItemDetailById(itemId);
				map.put("item", item);
				map.put("path", ECPSUtils.readProp("portal_path"));
				map.put("request_file_path", ECPSUtils.readProp("request_file_path"));
				FMutil.ouputFile("productDetail.ftl", item.getItemId()+".html", map);
			}else{
				result="fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
