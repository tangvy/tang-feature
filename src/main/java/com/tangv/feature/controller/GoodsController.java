package com.tangv.feature.controller;

import com.tangv.feature.dao.GoodsMapper;
import com.tangv.feature.model.entity.Goods;
import com.tangv.feature.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:29
 * Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/insert")
    public String insert(@RequestBody Goods goods) {
        goodsService.insertGoods(goods);
        return "success";
    }

}