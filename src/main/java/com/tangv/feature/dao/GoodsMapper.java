package com.tangv.feature.dao;

import com.tangv.feature.model.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:00
 * Description:
 */
@Mapper
public interface GoodsMapper extends tk.mybatis.mapper.common.Mapper<Goods> {

    void switchOnline(@Param("online") Boolean online, @Param("ids") Collection<Long> ids, @Param("modifyTime") LocalDateTime modifyTime);

}