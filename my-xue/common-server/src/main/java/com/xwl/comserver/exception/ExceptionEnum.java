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
    NAME_ISHAVA(400, "用户名已存在,请重试"),
    NAME_OR_PSD_ERROR(500, "用户名或密码错误,请核实"),
    AUTH_NO_AUTHENTION(401,"访问受限,请求未授权"),
    TOKEN_IS_ERROR(401,"TOKEN已过期,请重新登录"),
    FATLURE(400,"业务异常"),
    SUCCESS(200,"操作成功"),
    MUST_PARAM_IS_NOT_NULL(404,"缺少必要请求参数!"),
    PSD_IS_NOT_LIKE(400,"两次输入密码不一致");
    private Integer status;
    private String message;
}
