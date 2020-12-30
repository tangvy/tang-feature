package com.tangv.feature.model.dto;

import com.tangv.common.base.entity.PageDTO;
import lombok.Data;

/**
 * author:   tangwei
 * Date:     2020/12/26 11:42
 * Description:
 */

public class GoodsSearchDTO extends PageDTO {

    private String goodsCode;

    private String goodsName;

    private Boolean online;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}