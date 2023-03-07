package com.tangv.feature.dao;

import com.tangv.feature.model.entity.OmsOrder;
import org.apache.ibatis.annotations.Mapper;

public interface OmsOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKeyWithBLOBs(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}