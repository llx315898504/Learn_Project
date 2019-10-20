package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbBrand {

	private Long brandId;
	private String brandName;
	private String brandDesc;
	private String imgs;
	private String website;
	private Integer brandSort;
}