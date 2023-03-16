package com.tangv.feature.controller;

import com.github.pagehelper.PageInfo;
import com.tangv.common.enums.DataBaseType;
import com.tangv.common.response.Response;
import com.tangv.feature.config.DataSourceHolder;
import com.tangv.feature.model.dto.GoodsSearchDTO;
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
    public Response insert(@RequestBody Goods goods) {
        DataSourceHolder.setDatasources(DataBaseType.TANG_FEATURE1);
        goodsService.insertGoods(goods);
        return Response.success();
    }

    @PostMapping("/goodsPage")
    public Response<PageInfo<Goods>> getPage(@RequestBody GoodsSearchDTO goodsSearchDTO) {
        return Response.success(goodsService.getPage(goodsSearchDTO));
    }

}