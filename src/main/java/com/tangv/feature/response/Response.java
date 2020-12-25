package com.tangv.feature.response;

/**
 * author:   tangwei
 * Date:     2020/12/25 14:46
 * Description:
 */
public class Response<T> {

    private T data;

    private Integer code;

    private String message;

    public Response(T data) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.data = data;
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public Response(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Response() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public static <T> Response<T> success() {
        return new Response();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}