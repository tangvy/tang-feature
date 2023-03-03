/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.binlogs;

import com.tangv.common.enums.BinlogConsumeTableEnum;
import com.tangv.feature.dao.OmsOwnerMapper;
import com.tangv.feature.model.entity.OmsOwner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang wei
 * @version MarketplaceLogisticCityBinlogService.java, v 0.1 2023/3/2 16:30 tang wei Exp $
 * 处理MarketplaceLogisticCity的binlog
 */
@Service
public class OmsOwnerBinlogHandler extends AbstractBinlogConsumeHandler<OmsOwner> {

    @Resource
    private OmsOwnerMapper omsOwnerMapper;

    @Override
    public BinlogConsumeTableEnum consumeTable() {
        return null;
    }

    @Override
    public int insert(OmsOwner insert) {
        return omsOwnerMapper.insert(insert);
    }

    @Override
    public int update(OmsOwner update) {
        return omsOwnerMapper.updateByPrimaryKey(update);
    }

    @Override
    public int delete(OmsOwner delete) {
        return 0;
    }

    @Override
    public OmsOwner convert(String binlogContent) {
        return null;
    }
}
