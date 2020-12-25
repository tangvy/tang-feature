package com.tangv.feature.schedule;

import cn.hutool.core.collection.CollectionUtil;
import com.tangv.common.util.DateUtil;
import com.tangv.feature.consts.RedisConst;
import com.tangv.feature.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * author:   tangwei
 * Date:     2020/12/24 18:19
 * Description:
 */

@Slf4j
@Component
public class GoodsOfflineTask implements Runnable {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void run() {
        Set<Long> goodsIdSet = redisTemplate.opsForZSet().rangeByScore(RedisConst.GOODS_OFFLINE, Long.MIN_VALUE, DateUtil.getTimeStemp(LocalDateTime.now()));
        if (CollectionUtil.isNotEmpty(goodsIdSet)) {
            log.debug("商品：{}已下架",goodsIdSet.toString());
            goodsService.stackingGoods(goodsIdSet);
            Iterator<Long> iterator = goodsIdSet.iterator();
            while (iterator.hasNext()) {
                redisTemplate.opsForZSet().remove(RedisConst.GOODS_OFFLINE, iterator.next());
            }
        }
    }
}