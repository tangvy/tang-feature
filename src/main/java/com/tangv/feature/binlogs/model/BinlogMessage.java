/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tang wei
 * @version BinlogMessage.java, v 0.1 2023/3/2 15:07 tang wei Exp $
 */
@Data
public class BinlogMessage implements Serializable {

    private static final long serialVersionUID = -1132129963667790864L;

    private Integer id;

    private String database;

    private String table;

    /**
     * {@link com.tangv.common.enums.BinlogOperationTypeEnum}
     */
    private String type;

    private Long es;

    private Long ts;

    private String gtid;

    private boolean isDdl;

    private String sql;

    private List<String> pkNames;

    private List<JSONObject> data;

    private Object mysqlType;

    private List<Object> old;


}
