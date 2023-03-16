/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.kafka.consumer;

import com.tangv.feature.binlogs.BinlogConsumeHandlerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version MarketPlaceLogisticConsumer.java, v 0.1 2023/3/1 11:55 tang wei Exp $
 * 消费binlog
 * 任何表的binlog kafka消息只需直接调用BinlogConsumeHandlerService的consumeBinlog()方法
 */
@Component
@KafkaListener(id = "tangv", groupId = "tangv_group", autoStartup = "${spring.kafka.consumer.autoStartup}", topics = "canal-binlog")
public class OmsOwnerBinlogConsumer extends AbstractKafkaListener {

    @Resource
    private BinlogConsumeHandlerService binlogConsumeHandlerService;

    @Override
    public boolean autoCommit() {
        return true;
    }

    @Override
    public boolean isRepeateConsume(KafkaComsumePayLoad kafkaComsumePayLoad) {
        return false;
    }

    @Override
    public boolean doBiz(KafkaComsumePayLoad kafkaComsumerPayLoad) {
        //1.拿到消息，解析成模板类
        //2.根据操作类型区分新增、编辑、修改
        String binlog = kafkaComsumerPayLoad.getData();
        return binlogConsumeHandlerService.consumeBinlog(binlog);
    }
}
