package com.rl.ecps.model;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class EbOrderDetail {
    private Long orderDetailId;

    private Long orderId;

    private Long itemId;

    private String itemName;

    private String itemNo;

    private Long skuId;

    private String sku;

    private String skuImg;

    private String skuName;

    private Integer skuCatType;

    private String skuSpec;

    private BigDecimal marketPrice;

    private BigDecimal skuDiscount;

    private BigDecimal skuPrice;

    private Long offerGroupId;

    private String offerGroupName;

    private Short offerType;

    private String shortName;

    private Long offerId;

    private String offerName;

    private String offerNo;

    private String shortName2;

    private Short offerTerm;

    private BigDecimal commitMonthly;

    private BigDecimal prepaid;

    private Short period;

    private BigDecimal refundMonthly;

    private BigDecimal refund1stMonth;

    private BigDecimal refundLastMonth;

    private Short orderDetailType;

    private Long merchantId;

    private Integer quantity;

    private BigDecimal price;

    private String seriescode;

    private String offerGroupNo;

    private Short promoType;

    private String condId;

    private String productId;

    private BigDecimal paymentPrice;

    private BigDecimal purchasePrice;

    private Long simcardSuitId;

    private String simcardPackageId;
}