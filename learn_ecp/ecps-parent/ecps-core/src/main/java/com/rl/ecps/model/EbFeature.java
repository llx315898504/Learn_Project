package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbFeature {
    private Long featureId;

    private Long catId;

    private String featureName;

    private Short isSpec;

    private Short isSelect;

    private Short isShow;

    private String selectValues;

    private Short inputType;

    private Integer featureSort;

}