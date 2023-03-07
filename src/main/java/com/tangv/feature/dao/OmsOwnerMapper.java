package com.tangv.feature.dao;

import com.tangv.feature.model.entity.OmsOwner;
import org.apache.ibatis.annotations.Mapper;

public interface OmsOwnerMapper {

    int deleteByPrimaryKey(Integer ownerId);

    int insert(OmsOwner record);

    int insertSelective(OmsOwner record);

    OmsOwner selectByPrimaryKey(Integer ownerId);

    int updateByPrimaryKeySelective(OmsOwner record);

    int updateByPrimaryKey(OmsOwner record);
}