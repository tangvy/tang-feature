package com.tangv.feature.dao;

import com.tangv.feature.model.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:00
 * Description:
 */
@Mapper
public interface GoodsMapper extends tk.mybatis.mapper.common.Mapper<Goods> {
}