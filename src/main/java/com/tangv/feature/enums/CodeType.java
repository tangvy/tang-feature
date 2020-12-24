package com.tangv.feature.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author:   tangwei
 * Date:     2020/12/24 14:02
 * Description: 编号类型
 */

@AllArgsConstructor
public enum CodeType {

    GOODS_CODE(0,"商品编号"),
    ;

    @Getter
    private Integer type;

    @Getter
    private String desc;

}