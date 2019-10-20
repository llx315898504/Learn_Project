package com.rl.ecps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EbSku {
    private Long skuId;

    private Long itemId;

    private String sku;

    private BigDecimal skuPrice;

    private Short showStatus;

    private Integer stockInventory;

    private Integer skuUpperLimit;

    private String location;

    private String skuImg;

    private Integer skuSort;

    private String skuName;

    private BigDecimal marketPrice;

    private Date createTime;

    private Date updateTime;

    private Long createUserId;

    private Long updateUserId;

    private Long originalSkuId;

    private Short lastStatus;

    private Long merchantId;

    private Short skuType;

    private Long sales;

    private String resCode;

    private Integer packId;
    
    private List<EbSpecValue> ebSpecList;
    
    private EbItem ebItem;
}