package com.rl.ecps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EbOrder {
	private Long orderId;

	private Long ptlUserId;

	private String username;

	private String orderNum;

	private Short payment;

	private Short payPlatform;

	private Short delivery;

	private Short isConfirm;

	private BigDecimal orderSum;

	private BigDecimal shipFee;

	private Short isPaid;

	private Short orderState;

	private Short paymentCash;

	private Long distriId;

	private Short deliveryMethod;

	private String paymentNo;

	private Date orderTime;

	private Date payTime;

	private Date depositTime;

	private Date successTime;

	private Date updateTime;

	private Short srvType;

	private String selfCollectSite;

	private Long selfCollectSiteId;

	private Short isDeleted;

	private Short isDisplay;

	private String notes;

	private String shipName;

	private String province;

	private String city;

	private String district;

	private String zipCode;

	private String addr;

	private String phone;

	private Short payable;

	private String company;

	private Short contents;

	private Short isCall;

	private String deliveryNo;

	private String areaCode;

	private String areaName;

	private Short isPrint;

	private Date crmCallsTime;

	private Short isOfferRelease;

	private String jobNum;

	private Short orderType;

	private String fixedPhone;

	private String attachFile;

	private Long returnType;

	private Long seckillId;

	private Short orderSource;

	private String orderExt1;

	private String orderExt2;

	private String orderExt3;

	private String orderExt4;

	private String orderExt5;

	private List<EbOrderDetail> detailList;
}