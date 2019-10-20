package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbParaValue {
    private Long paraId;

    private Long itemId;

    private Long featureId;

    private String paraValue;
    
    private String featureName;

}