package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbArea;

public interface EbAreaDao {
	
   /**
    * 根据区域的父节点id查询区域信息
    * 
    * @param pid
    * @return
    */
   public List<EbArea> selectAreaByPid(long pid);
}
