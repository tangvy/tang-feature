package com.tangv.feature.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tangwei
 * @version KafkaComsumePayLoad.java, v 0.1 2022/11/22 18:19 tangwei Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaComsumePayLoad implements Serializable {

    private static final long serialVersionUID = -464956395226763748L;

    /**
     * 业务数据（序列化成字符串）
     */
    private String data;

    /**
     * 消费主题
     */
    private String receivedTopic;

    /**
     * 消息索引
     */
    private String receivedMessageKey;

    /**
     * 时间戳
     */
    private long receivedTimestamp;

}
