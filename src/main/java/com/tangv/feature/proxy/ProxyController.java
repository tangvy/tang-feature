package com.tangv.feature.proxy;

import com.github.pagehelper.PageInfo;
import com.tangv.feature.model.dto.GoodsSearchDTO;
import com.tangv.feature.model.entity.Goods;
import com.tangv.feature.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:   tangwei
 * Date:     2021/3/12 15:36
 * Description:
 */
@RestController
public class ProxyController {


    @GetMapping("/proxy")
    public PageInfo<Goods> getProxy() {
        GoodsService goodsService = new GoodsService();
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(goodsService);
        GoodsService proxy = (GoodsService)proxyInvocationHandler.getProxy();
        GoodsSearchDTO goodsSearchDTO = new GoodsSearchDTO();
        PageInfo<Goods> page = proxy.getPage(goodsSearchDTO);
        return page;
    }

}