package com.rl.ecps.model;

import lombok.Data;

@Data
public class EbArea {
    private Long ereaId;

    private Long parentId;

    private String ereaName;

    private Short areaLevel;
}