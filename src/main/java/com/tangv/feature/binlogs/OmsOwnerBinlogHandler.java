/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs;

import com.alibaba.fastjson.JSONObject;
import com.tangv.common.enums.BinlogConsumeTableEnum;
import com.tangv.feature.binlogs.model.OmsOwnerBinlog;
import com.tangv.feature.config.DataBaseType;
import com.tangv.feature.config.DataSourceHolder;
import com.tangv.feature.dao.OmsOwnerSyncMapper;
import com.tangv.feature.model.entity.OmsOwner;
import com.tangv.feature.model.entity.OmsOwnerSync;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version MarketplaceLogisticCityBinlogService.java, v 0.1 2023/3/2 16:30 tang wei Exp $
 * 处理MarketplaceLogisticCity的binlog
 */
@Service
public class OmsOwnerBinlogHandler extends AbstractBinlogConsumeHandler<OmsOwnerSync> {

    @Resource
    private OmsOwnerSyncMapper omsOwnerSyncMapper;

    @Override
    public BinlogConsumeTableEnum consumeTable() {
        return BinlogConsumeTableEnum.OMS_OWNER;
    }

    @Override
    public int insert(OmsOwnerSync insert) {
        DataSourceHolder.setDatasources(DataBaseType.TANG_FEATURE);
        System.out.println(DataSourceHolder.getDataSources());
        int i = omsOwnerSyncMapper.insert(insert);
        DataSourceHolder.remove();
        return i;
    }

    @Override
    public int update(OmsOwnerSync update) {
        DataSourceHolder.setDatasources(DataBaseType.TANG_FEATURE);
        System.out.println(DataSourceHolder.getDataSources());
        int i = omsOwnerSyncMapper.updateByPrimaryKey(update);
        DataSourceHolder.remove();
        return i;
    }

    @Override
    public int delete(OmsOwnerSync delete) {
        DataSourceHolder.setDatasources(DataBaseType.TANG_FEATURE);
        System.out.println(DataSourceHolder.getDataSources());
        int i = omsOwnerSyncMapper.deleteByPrimaryKey(delete.getOwnerId());
        DataSourceHolder.remove();
        return i;
    }

    @Override
    public OmsOwnerSync convert(JSONObject binlogContent) {
        OmsOwnerBinlog omsOwnerBinlog = JSONObject.toJavaObject(binlogContent, OmsOwnerBinlog.class);
        OmsOwnerSync omsOwner = new OmsOwnerSync();
        BeanUtils.copyProperties(omsOwnerBinlog, omsOwner);
        return omsOwner;
    }
}
