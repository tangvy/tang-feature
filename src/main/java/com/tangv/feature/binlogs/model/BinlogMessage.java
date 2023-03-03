/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs.model;

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

    private String topic;

    private String partition;

    private Long offset;

    private Long timestamp;

    private String timestampType;

    private List headers;

    private String key;

    @JsonProperty(value = "value")
    private BinlogValue binlogValue;

    @Data
    public static class BinlogValue {

        @JsonProperty(value = "data")
        private BinlogContentData binlogContentData;

        @JsonProperty(value = "metadata")
        private BinlogMetaData binlogMetaData;

    }

    @Data
    public static class BinlogMetaData {

        private String timestamp;

        @JsonProperty(value = "record-type")
        private String recordType;

        private String operation;

        @JsonProperty(value = "partition-key-type")
        private String partitionKeyType;

        @JsonProperty(value = "schema-name")
        private String schemaName;

        @JsonProperty(value = "table-name")
        private String tableName;
    }

    @Data
    public static class BinlogContentData {

        @JsonProperty(value = "_doc")
        private String change;

    }
}
