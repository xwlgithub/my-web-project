package com.xwl.comserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public enum ExceptionEnum {
    THROW_SERVER(500, "服务器异常"),
    AUTH_NO_AUTHENTION(401,"访问受限,请求未授权"),
    FATLURE(400,"业务异常"),
    SUCCESS(200,"操作成功"),
    MUST_PARAM_IS_NOT_NULL(404,"缺少必要请求参数!");
    private Integer status;
    private String message;
}
