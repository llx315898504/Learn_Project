package com.rl.ecps.service;

import java.util.List;

import com.rl.ecps.model.EbArea;

/**
 * 区域地址管理接口
 * 
 * @author 86150
 *
 */
public interface EbAreaService {
	
	  /**
	    * 根据区域的父节点id查询区域信息
	    * 
	    * @param pid
	    * @return
	    */
	   public List<EbArea> selectAreaByPid(long pid);

}
