package com.tangv.feature.service;

import com.tangv.feature.consts.RedisConst;
import com.tangv.feature.dao.GoodsMapper;
import com.tangv.feature.enums.CodeType;
import com.tangv.feature.model.entity.Goods;
import com.tangv.feature.util.NumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;

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
        goodsMapper.insertSelective(goods);
        redisTemplate.opsForZSet().add(RedisConst.GOODS_OFFLINE,goods.getId(), LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}