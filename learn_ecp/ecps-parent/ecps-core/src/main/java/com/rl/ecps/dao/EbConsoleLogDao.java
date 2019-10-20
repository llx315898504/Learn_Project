package com.rl.ecps.dao;

import com.rl.ecps.model.EbConsoleLog;

public interface EbConsoleLogDao {
	

	/**
	 * 添加业务日志
	 * 
	 * @param log
	 */
	void saveConsoleLog(EbConsoleLog log);
	
	

}
