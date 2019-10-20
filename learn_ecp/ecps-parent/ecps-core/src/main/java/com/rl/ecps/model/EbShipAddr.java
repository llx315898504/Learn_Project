package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbShipAddr {
    private Long shipAddrId;

    private Long ptlUserId;

    private String shipName;

    private String province;

    private String city;

    private String district;

    private String zipCode;

    private String addr;

    private String phone;

    private Short defaultAddr;

    private String fixedPhone;
}