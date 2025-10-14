package com.gs.shop.erp.infrustructure.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应状态码
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {
    /**
     * 一切ok
     */
    SUCCESS(200, "一切ok"),
    /**
     * 未登录
     */
    NOT_LOGIN(403, "未登录"),
    /**
     * 处理失败
     */
    RESOLVE_FAILED(407, "处理失败"),
    /**
     * 系统执行出错
     */
    SYSTEM_EXECUTION_ERROR(500, "系统执行出错"),
    /**
     * 用户请求参数错误
     */
    PARAM_ERROR(400, "用户请求参数错误"),
    /**
     * 请求资源不存在
     */
    RESOURCE_NOT_FOUND(404, "请求资源不存在"),
    /**
     * 请求必填参数为空
     */
    PARAM_IS_NULL(410, "请求必填参数为空");

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private int code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }
}
