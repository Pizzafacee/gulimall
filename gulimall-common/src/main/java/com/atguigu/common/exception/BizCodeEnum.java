package com.atguigu.common.exception;

public enum BizCodeEnum {
    VALID_EXCEPTION(10000, "参数格式校验失败"),
    UNKNOW_EXCEPTION(10001, "系统未知异常");
    private Integer code;

    public Integer getCode() {
        return code;
    }

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;
}
