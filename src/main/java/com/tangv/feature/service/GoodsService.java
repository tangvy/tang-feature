package com.tangv.feature.service;

import com.tangv.feature.dao.GoodsMapper;
import com.tangv.feature.enums.CodeType;
import com.tangv.feature.model.entity.Goods;
import com.tangv.feature.util.NumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:30
 * Description:
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public void insertGoods(Goods goods) {
        goods.setGoodsCode(NumUtil.getCode(CodeType.GOODS_CODE));
        goodsMapper.insertSelective(goods);
    }

}