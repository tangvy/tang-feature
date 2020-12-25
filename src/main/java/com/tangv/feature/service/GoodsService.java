package com.tangv.feature.service;

import cn.hutool.core.collection.CollectionUtil;
import com.tangv.feature.consts.RedisConst;
import com.tangv.feature.dao.GoodsMapper;
import com.tangv.feature.enums.CodeType;
import com.tangv.feature.model.entity.Goods;
import com.tangv.feature.util.DateUtil;
import com.tangv.feature.util.NumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.Collection;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:30
 * Description:
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    public void insertGoods(Goods goods) {
        goods.setGoodsCode(NumUtil.getCode(CodeType.GOODS_CODE));
        goods.setStackingTime(LocalDateTime.now());
        goodsMapper.insertSelective(goods);
        redisTemplate.opsForZSet().add(RedisConst.GOODS_OFFLINE,goods.getId(), DateUtil.getTimeStemp(goods.getStackingTime().plusMinutes(5L)));
    }

    @Transactional(rollbackFor = Exception.class)
    public void stackingGoods(Collection<Long> goodsIds) {
        if (CollectionUtil.isNotEmpty(goodsIds)) {
            goodsMapper.switchOnline(Boolean.FALSE,goodsIds,LocalDateTime.now());
        }
    }
}