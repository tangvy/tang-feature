/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author tangwei
 * @version AbstractKafkaListener.java, v 0.1 2022/11/25 10:55 tangwei Exp $
 */
@Slf4j
public abstract class AbstractKafkaListener {

    @KafkaHandler
    public void receive(@Payload byte[] data,
                        @Header(value = KafkaHeaders.RECEIVED_TOPIC, required = false) String receivedTopic,
                        @Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String receivedMessageKey,
                        @Header(value = KafkaHeaders.RECEIVED_TIMESTAMP, required = false) long receivedTimestamp,
                        Acknowledgment ack) {
        KafkaComsumePayLoad kafkaComsumePayLoad =
                buildKafkaComsumePayLoad(data, receivedTimestamp, receivedTopic, receivedMessageKey);
        log.info(" ====> receive message：{}", JSONObject.toJSONString(kafkaComsumePayLoad));
        boolean isRepeateConsume = isRepeateConsume(kafkaComsumePayLoad);
        boolean autoCommit = autoCommit();
        try {
            if (isRepeateConsume) {
                log.warn("messageKey:{}，topic：{}存在重复消息数据-->【{}】", receivedMessageKey,
                        receivedTopic, kafkaComsumePayLoad.getData());
                doAck(ack);
                return;
            }
            if (doBiz(kafkaComsumePayLoad)) {
                doAck(ack);
            }
        } finally {
            if (autoCommit) {
                ack.acknowledge();
                System.out.println("成功");
            }
        }
    }

    /**
     * 是否自动提交offset
     *
     * @return boolean
     */
    public abstract boolean autoCommit();

    /**
     * 是否重复消费，幂等处理
     *
     * @param kafkaComsumePayLoad
     * @return
     */
    public abstract boolean isRepeateConsume(KafkaComsumePayLoad kafkaComsumePayLoad);

    /**
     * 业务处理
     *
     * @param kafkaComsumerPayLoad
     */
    public abstract boolean doBiz(KafkaComsumePayLoad kafkaComsumerPayLoad);

    /**
     * 组装消费参数
     */
    private KafkaComsumePayLoad buildKafkaComsumePayLoad(byte[] data, long receivedTimestamp, String receivedTopic,
                                                         String receivedMessageKey) {
        return KafkaComsumePayLoad.builder()
                .data(new String(data))
                .receivedTimestamp(receivedTimestamp)
                .receivedTopic(receivedTopic)
                .receivedMessageKey(receivedMessageKey)
                .build();
    }

    private void doAck(Acknowledgment ack) {
        //手动确认ack
        if (!autoCommit()) {
            ack.acknowledge();
        }
    }
}
