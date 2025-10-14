package com.gs.shop.erp.infrustructure;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.gs.shop.erp.infrustructure.auth.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * 字段填充
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/12/09
 */
@Slf4j
@Configuration
@AutoConfigureAfter(SessionService.class)
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private SessionService sessionService;

    @Override
    public void insertFill(MetaObject metaObject) {
        //获取用户id
        Integer userId = sessionService.getUserId();
        log.debug("mybatis plus start insert fill ....");
        LocalDateTime now = LocalDateTime.now();

        fillValIfNullByName("createTime", now, metaObject, false);
        fillValIfNullByName("updateTime", now, metaObject, false);
        fillValIfNullByName("createUserId", userId, metaObject, false);
        fillValIfNullByName("updateUserId", userId, metaObject, false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取用户id
        Integer userId = sessionService.getUserId();
        log.debug("mybatis plus start update fill ....");
        LocalDateTime now = LocalDateTime.now();

        fillValIfNullByName("updateTime", now, metaObject, true);
        fillValIfNullByName("updateUserId", userId, metaObject, true);
    }

    /**
     * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
     *
     * @param fieldName  属性名
     * @param fieldVal   属性值
     * @param metaObject MetaObject
     * @param isCover    是否覆盖原有值,避免更新操作手动入参
     */
    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        // 1. 没有 get 方法
        if (!metaObject.hasSetter(fieldName)) {
            return;
        }
        // 2. 如果用户有手动设置的值
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        // 3. field 类型相同时设置
        Class<?> getterType = metaObject.getGetterType(fieldName);
        if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
            metaObject.setValue(fieldName, fieldVal);
        }
    }
}
