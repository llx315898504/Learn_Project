package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbSpecValue {
    private Long specId;

    private Long skuId;

    private Long featureId;

    private String specValue;
}