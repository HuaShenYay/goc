package com.gs.shop.erp.infrustructure.annotation;

import org.springframework.stereotype.Service;
import java.lang.annotation.*;

/**
 * 服务注解
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023/11/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface AutoService {

    boolean notFoundThrowException() default true;

    String customPageQueryMethod() default "";

    String customListQueryMethod() default "";
}
