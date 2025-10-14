package com.gs.shop.erp.constant.enums;


/**
 * 请求方式枚举
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/11
 */
public enum MethodsEnum {
    /**
     * POST 接口类型
     */
    POST("POST"),
    /**
     * DELETE 接口类型
     */
    DELETE("DELETE"),
    /**
     * PATCH 接口类型
     */
    PATCH("PATCH"),
    /**
     * PUT 接口类型
     */
    PUT("PUT"),
    /**
     * GET 接口类型
     */
    GET("GET");
    private final String name;

    MethodsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
