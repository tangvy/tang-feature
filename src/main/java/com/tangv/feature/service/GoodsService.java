package com.tangv.feature.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangv.common.enums.CodeType;
import com.tangv.common.response.Response;
import com.tangv.common.util.DateUtil;
import com.tangv.common.util.NumUtil;
import com.tangv.feature.annotation.EagleEye;
import com.tangv.feature.annotation.TargetDataSource;
import com.tangv.feature.config.DataBaseType;
import com.tangv.feature.consts.RedisConst;
import com.tangv.feature.dao.GoodsMapper;
import com.tangv.feature.model.dto.GoodsSearchDTO;
import com.tangv.feature.model.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.util.Collection;
import java.util.List;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:30
 * Description:
 */

@Service
@TargetDataSource(DataBaseType.TANG_FEATURE1)
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //@TargetDataSource(DataBaseType.TANG_FEATURE1)
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

    @EagleEye("获取商品列表")
    public PageInfo<Goods> getPage(GoodsSearchDTO goodsSearchDTO) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        Page<Goods> page = PageHelper.startPage(goodsSearchDTO.getPage(), goodsSearchDTO.getSize());
        try {
            goodsMapper.getGoodsList(goodsSearchDTO);
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transaction);
        }
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(page.getResult());
        return goodsPageInfo;
    }

        public void test(){
        System.out.println("---------------进入test方法-----------");
    }

    public static void main(String[] args) {
        String s = "rfdv-2323-[]-fdv-2233-cvd";
        int i = s.indexOf("-");
        System.out.println(i);
        System.out.println(s.lastIndexOf("-"));
    }
}