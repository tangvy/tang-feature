/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.kafka.producer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

/**
 * @author tangwei
 * @version AbstractKafkaProducer.java, v 0.1 2022/11/24 14:25 tangwei Exp $
 */
@Slf4j
public abstract class AbstractKafkaProducer implements ApplicationContextAware {

    private KafkaTemplate kafkaTemplate;

    //todo 可以增加重试次数、超时时间、失败写入DB

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.kafkaTemplate = applicationContext.getBean("kafkaTemplate", KafkaTemplate.class);
    }

    /**
     * 发送异步消息
     */
    public void sendAsync(String topic, Object key, Object message) {
        ListenableFuture sendFuture = sendFuture(topic, key, JSONObject.toJSONString(message));
        ListenableFutureCallback listenableFutureCallback = new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                onAsyncFailure(ex);
            }

            @Override
            public void onSuccess(Object result) {
                this.onAsyncSuccess(result);
            }

            /**
             * 成功回调
             */
            public void onAsyncSuccess(Object result) {
                log.info(" ====> send success, result: {}", JSONObject.toJSONString(result));
            }

            /**
             * 失败回调
             */
            public void onAsyncFailure(Throwable ex) {
                log.error(" ====> send failed", ex);
            }
        };
        sendFuture.addCallback(listenableFutureCallback);
    }

    /**
     * 发送同步消息
     */
    public void sendSync(String topic, Object key, Object message) {
        ListenableFuture sendFuture = sendFuture(topic, key, message);
        try {
            sendFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(String.format("Kafka-Message-err: send to topic %s err, key is %s", topic, key),
                    e);
        }
    }

    /**
     * 发送消息，获得一个结果
     */
    public ListenableFuture sendFuture(String topic, Object key, Object message) {
        String jsonMsg = JSONObject.toJSONString(message);
        log.info(" ====> send topic: {}, key: {}, message: {}", topic, key, jsonMsg);
        ListenableFuture sendFuture = kafkaTemplate.send(topic, key, jsonMsg);
        return sendFuture;
    }
}
