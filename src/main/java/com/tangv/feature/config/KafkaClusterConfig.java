/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.config;

import com.tangv.feature.kafka.constant.MultikafkaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;

import java.util.Objects;

/**
 * @author tangwei
 * @version KafkaCluster0Config.java, v 0.1 2022/11/22 18:19 tangwei Exp $
 */
@Configuration
public class KafkaClusterConfig {

    @Primary
    @Bean("kafkaTemplate")
    public KafkaTemplate<?, ?> defaultKafkaTemplate(
            @Autowired @Qualifier("clusterProducerFactory") ProducerFactory clusterProducerFactory,
            @Autowired @Qualifier("clusterProducerListener")
                    ProducerListener clusterProducerListener) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(clusterProducerFactory);
        kafkaTemplate.setProducerListener(clusterProducerListener);
        kafkaTemplate.setDefaultTopic(clusterKafkaProperties().getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    @Bean("clusterProducerListener")
    public ProducerListener<Object, Object> clusterProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean("clusterProducerFactory")
    public ProducerFactory<?, ?> clusterProducerFactory(
            @Autowired @Qualifier("clusterKafkaProperties") KafkaProperties clusterKafkaProperties) {
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(
                clusterKafkaProperties.buildProducerProperties());
        String transactionIdPrefix = clusterKafkaProperties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean("kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory clusterKafkaListenerContainerFactory(
            @Autowired @Qualifier("clusterKafkaProperties") KafkaProperties clusterKafkaProperties,
            @Autowired @Qualifier("clusterConsumerFactory") ConsumerFactory clusterConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(clusterConsumerFactory);
        concurrentKafkaListenerContainerFactory.setConcurrency(
                Objects.isNull(clusterKafkaProperties.getListener().getConcurrency()) ?
                        Runtime.getRuntime().availableProcessors() :
                        clusterKafkaProperties.getListener().getConcurrency());
        concurrentKafkaListenerContainerFactory.getContainerProperties().setAckMode(
                Objects.isNull(clusterKafkaProperties.getListener().getAckMode()) ?
                        ContainerProperties.AckMode.MANUAL_IMMEDIATE :
                        clusterKafkaProperties.getListener().getAckMode());
        return concurrentKafkaListenerContainerFactory;
    }

    @Primary
    @Bean("clusterConsumerFactory")
    public ConsumerFactory clusterConsumerFactory(
            @Autowired @Qualifier("clusterKafkaProperties") KafkaProperties clusterKafkaProperties) {
        return new DefaultKafkaConsumerFactory(clusterKafkaProperties.buildConsumerProperties());
    }

    @Primary
    @Bean("clusterKafkaProperties")
    @ConfigurationProperties(prefix = "spring.kafka")
    public KafkaProperties clusterKafkaProperties() {
        KafkaProperties kafkaProperties = new KafkaProperties();
        return kafkaProperties;
    }

}
