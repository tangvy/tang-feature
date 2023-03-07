/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tang wei
 * @version MarketPlaceLogisticBinlog.java, v 0.1 2023/3/2 19:06 tang wei Exp $
 */
@Data
public class OmsOwnerBinlog  implements Serializable {

    @JsonProperty(value = "owner_id")
    private Integer ownerId;

    @JsonProperty(value = "owner_code")
    private String ownerCode;

    @JsonProperty(value = "owner_name")
    private String ownerName;

    @JsonProperty(value = "gmt_create")
    private Date gmtCreate;

    @JsonProperty(value = "gmt_modify")
    private Date gmtModify;
}
