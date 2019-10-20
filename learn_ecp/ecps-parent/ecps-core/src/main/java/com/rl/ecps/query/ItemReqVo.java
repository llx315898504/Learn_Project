package com.rl.ecps.query;

import lombok.Data;

@Data
public class ItemReqVo {

	private String auditStatus;

	private String showStatus;

	private String itemName;

	private Long brandId;

	private Integer pageNo;
	
	//开始行号
	private Integer startNum;
	//结束行号
	private Integer endNum;

}
