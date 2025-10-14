package com.gs.shop.erp.infrustructure.util;

import cn.hutool.core.util.StrUtil;
import com.gs.shop.erp.infrustructure.result.ResultCode;

import java.util.Collection;
import java.util.Map;

/**
 * 参数验证工具类
 *
 * @author YourName
 * @version 1.0
 * @date 2025-10-14
 */
public class ParamValidator {

    /**
     * 检查字符串参数是否为空
     *
     * @param param 参数值
     * @param paramName 参数名称
     * @throws IllegalArgumentException 如果参数为空
     */
    public static void notBlank(String param, String paramName) {
        if (StrUtil.isBlank(param)) {
            throw new IllegalArgumentException(String.format("参数 '%s' 不能为空", paramName));
        }
    }

    /**
     * 检查对象参数是否为空
     *
     * @param param 参数值
     * @param paramName 参数名称
     * @throws IllegalArgumentException 如果参数为空
     */
    public static void notNull(Object param, String paramName) {
        if (param == null) {
            throw new IllegalArgumentException(String.format("参数 '%s' 不能为空", paramName));
        }
    }

    /**
     * 检查集合参数是否为空
     *
     * @param param 参数值
     * @param paramName 参数名称
     * @throws IllegalArgumentException 如果参数为空或长度为0
     */
    public static void notEmpty(Collection<?> param, String paramName) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException(String.format("参数 '%s' 不能为空", paramName));
        }
    }

    /**
     * 检查Map参数是否为空
     *
     * @param param 参数值
     * @param paramName 参数名称
     * @throws IllegalArgumentException 如果参数为空或长度为0
     */
    public static void notEmpty(Map<?, ?> param, String paramName) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException(String.format("参数 '%s' 不能为空", paramName));
        }
    }

    /**
     * 检查数组参数是否为空
     *
     * @param param 参数值
     * @param paramName 参数名称
     * @throws IllegalArgumentException 如果参数为空或长度为0
     */
    public static void notEmpty(Object[] param, String paramName) {
        if (param == null || param.length == 0) {
            throw new IllegalArgumentException(String.format("参数 '%s' 不能为空", paramName));
        }
    }
}