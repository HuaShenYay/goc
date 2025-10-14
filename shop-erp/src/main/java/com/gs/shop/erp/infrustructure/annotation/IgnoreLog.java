package com.gs.shop.erp.infrustructure.annotation;

import java.lang.annotation.*;

/**
 * 忽略日志打印
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023/11/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLog {

}
