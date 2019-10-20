package com.rl.ecps.model;

import java.util.Date;

import lombok.Data;
@Data
public class EbConsoleLog {
    private Long consoleLogId;

    private String entityName;

    private Long entityId;

    private Long userId;

    private String opType;

    private Date opTime;

    private String notes;

    private String tableName;
}