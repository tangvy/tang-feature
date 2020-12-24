package com.tangv.feature.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * author:   tangwei
 * Date:     2020/12/24 18:08
 * Description: 定时任务类型
 */
@AllArgsConstructor
public enum ScheduleTaskTypeEnum {

    OFFLINE_GOODS(0,"下线商品"),;

    @Getter
    private Integer type;

    @Getter
    private String desc;

}