package com.tangv.feature.dao;

import com.tangv.feature.model.entity.OmsOwnerSync;
import org.apache.ibatis.annotations.Mapper;

public interface OmsOwnerSyncMapper {

    int deleteByPrimaryKey(Integer ownerId);

    int insert(OmsOwnerSync record);

    int insertSelective(OmsOwnerSync record);

    OmsOwnerSync selectByPrimaryKey(Integer ownerId);

    int updateByPrimaryKeySelective(OmsOwnerSync record);

    int updateByPrimaryKey(OmsOwnerSync record);
}