package com.rl.ecps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EbItem {
	
	private Long itemId;

	private String itemName;

	private String itemNo;

	private Long brandId;

	private Long catId;

	private Long tagImgId;

	private Short tagImg;

	private Short isNew;

	private Short isGood;

	private Short isHot;

	private String promotion;

	private Short auditStatus;

	private Short showStatus;

	private String imgs;

	private String keywords;

	private String pageDesc;

	private Short itemRecycle;

	private Date onSaleTime;

	private Date checkTime;

	private Date updateTime;

	private Long updateUserId;

	private Date createTime;

	private Long checkerUserId;

	private String fullPathDeploy;

	private String fullPathDeployOffer;

	private Long originalItemId;

	private Short lastStatus;

	private Long merchantId;

	private Long itemSort;

	private Long sales;

	private Long createUserId;

	private Short simLevel;

	private String giftDesc;

	private String giftImg;

	private String giftShowType;

	private String imgSize1;
	
	private BigDecimal skuPrice;
	
	private List<EbParaValue> paraList;
	
	private EbItemClob itemClob;
	
	private List<EbSku> skuList;
}