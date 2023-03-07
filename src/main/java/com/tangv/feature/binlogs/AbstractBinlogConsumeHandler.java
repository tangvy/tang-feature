/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs;

import com.alibaba.fastjson.JSONObject;
import com.tangv.common.enums.BinlogConsumeTableEnum;

/**
 * @author tang wei
 * @version AbstractBinlogSyncHandler.java, v 0.1 2023/3/2 16:31 tang wei Exp $
 * binlog处理器抽象类
 */
public abstract class AbstractBinlogConsumeHandler<T> {

    /**
     * 操作的源表
     *
     * @return BinlogConsumeTableEnum {@link BinlogConsumeTableEnum}
     */
    public abstract BinlogConsumeTableEnum consumeTable();

    /**
     * 新增记录
     *
     * @param insert
     */
    public abstract int insert(T insert);

    /**
     * 修改记录
     *
     * @param update
     */
    public abstract int update(T update);

    /**
     * 删除记录
     *
     * @param delete
     */
    public abstract int delete(T delete);

    /**
     * 将binlog的数据文本转换为需要的数据操作类
     *
     * @param binlogContent
     * @return T
     */
    public abstract T convert(JSONObject binlogContent);
}
