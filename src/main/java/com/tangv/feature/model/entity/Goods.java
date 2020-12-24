package com.tangv.feature.model.entity;

import com.tangv.feature.model.BasePojo;
import lombok.Data;

import javax.persistence.Table;

/**
 * author:   tangwei
 * Date:     2020/12/24 11:00
 * Description: 商品
 */
@Data
@Table(name = "goods")
public class Goods extends BasePojo {

    private String goodsCode;

    private String goodsName;

    private Boolean online;
}