/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs;

import com.alibaba.fastjson.JSONObject;
import com.tangv.common.enums.BinlogConsumeTableEnum;
import com.tangv.common.enums.BinlogOperationTypeEnum;
import com.tangv.feature.binlogs.model.BinlogMessage;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tang wei
 * @version BinlogServiceFactory.java, v 0.1 2023/3/2 16:38 tang wei Exp $
 * 统一处理binlog
 */
@Component
public class BinlogConsumeHandlerService implements ApplicationContextAware {

    /**
     * key: 操作源表
     * value: 具体的binlog处理器
     */
    private static Map<BinlogConsumeTableEnum, AbstractBinlogConsumeHandler> binlogHandlerMap = new ConcurrentHashMap<>();

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 将所有的binlog处理器放在内存Map中
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AbstractBinlogConsumeHandler> binlogConsumeHandlerMap = applicationContext.getBeansOfType(AbstractBinlogConsumeHandler.class);
        binlogConsumeHandlerMap.values().forEach(handler -> binlogHandlerMap.put(handler.consumeTable(), handler));
    }

    /**
     * 根据binlog操作的表名匹配到具体处理类
     *
     * @param binlogConsumeTableEnum
     * @return AbstractBinlogConsumeHandler
     */
    public AbstractBinlogConsumeHandler getBinlogConsumeHandler(BinlogConsumeTableEnum binlogConsumeTableEnum) {
        return binlogHandlerMap.get(binlogConsumeTableEnum);
    }

    /**
     * 处理binlog
     * 会根据源表名路由到具体的处理类  {@link BinlogConsumeTableEnum}
     * 操作类型（INSERT、UPDATE、DELETE）   {@link BinlogOperationTypeEnum}
     * 任何表的binlog kafka消息只需直接调用consumeBinlog()方法
     *
     * @param binlog
     * @return boolean
     */
    public boolean consumeBinlog(String binlog) {
        //1.将binlog消息按类模板解析成集合
        BinlogMessage binlogMessage = JSONObject.parseObject(binlog, BinlogMessage.class);
            //操作类型
            String operation = binlogMessage.getType();
            //操作源表
            String tableName = binlogMessage.getTable();
            //具体数据变更

        List<JSONObject> dataList = binlogMessage.getData();

        BinlogOperationTypeEnum binlogOperationTypeEnum = BinlogOperationTypeEnum.getByType(operation);
        if (binlogOperationTypeEnum == null) {
            //出现预期之外的类型，直接不处理
            return true;
        }
        //2.根据源表名匹配到具体的处理器
        AbstractBinlogConsumeHandler binlogConsumeHandler = getBinlogConsumeHandler(BinlogConsumeTableEnum.getByTableName(tableName));

        transactionTemplate.executeWithoutResult(status -> {
            for (JSONObject eachData : dataList) {
                //将具体数据变更解析成类模板
                Object convertEntity = binlogConsumeHandler.convert(eachData);
                //3.根据操作类型将数据变更执行到数据库
                int execute = executeChange(binlogConsumeHandler, binlogOperationTypeEnum, convertEntity);
                if (execute <= 0) {
                    throw new RuntimeException("执行SQL发生错误");
                }
            }
        });
        return true;
    }

    /**
     * 根据操作类型将数据变更执行到数据库
     * 当前的操作类型有（INSERT/UPDATE/DELETE）{@link BinlogOperationTypeEnum}
     *
     * @param binlogConsumeHandler
     * @param binlogOperationTypeEnum
     * @param convertEntity
     * @return int
     */
    private int executeChange(AbstractBinlogConsumeHandler binlogConsumeHandler, BinlogOperationTypeEnum binlogOperationTypeEnum, Object convertEntity) {
        int execute = 0;
        switch (binlogOperationTypeEnum) {
            case INSERT:
                execute = binlogConsumeHandler.insert(convertEntity);
                break;
            case UPDATE:
                execute = binlogConsumeHandler.update(convertEntity);
                break;
            case DELETE:
                execute = binlogConsumeHandler.delete(convertEntity);
                break;
            default:
                ;
        }
        return execute;
    }
}
