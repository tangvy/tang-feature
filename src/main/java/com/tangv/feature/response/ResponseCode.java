package com.tangv.feature.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * author:   tangwei
 * Date:     2020/12/25 14:48
 * Description:
 */
public enum ResponseCode {

   SUCCESS(200,"success"),

    ERROR(500,"error"),

    NOT_FOUND(404,"not found"),
    ;

   @Getter
    private Integer code;

   @Getter
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}