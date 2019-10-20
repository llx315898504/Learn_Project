package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbCart {
	
	private Long skuId;
	
	private Integer quantity;
	
	private EbSku ebSku;

}
