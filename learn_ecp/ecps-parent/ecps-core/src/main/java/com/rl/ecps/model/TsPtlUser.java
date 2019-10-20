package com.rl.ecps.model;

import java.util.Date;

import lombok.Data;

@Data
public class TsPtlUser {
    private Long ptlUserId;

    private String username;

    private Short gender;

    private String password;

    private String nickname;

    private String realName;

    private String phone;

    private String resiProv;

    private String resiCity;

    private String resiDist;

    private String addr;

    private Integer zipCode;

    private Date loginTime;

    private Short status;

    private String email;

    private Long groupId;

    private String birthday;

    private String qqNum;

    private String msnNum;

    private String cellphone;

    private String introInfo;

    private Long loginCount;

    private Date registerTime;

    private Short isMobileClient;
}