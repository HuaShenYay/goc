package com.gs.shop.erp.infrustructure.util;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.gs.shop.erp.infrustructure.model.AbstractQuery;

/**
 * 查询工具
 *
 * @author Guo Shuai
 * @version 1.0
 * @date 2023-11-15
 */
public class QueryUtil {

    @SuppressWarnings("all")
    public static <T> LambdaQueryChainWrapper<T> build(AbstractQuery<T> abstractYuanManQuery) {
        //返回
        return Opt.ofNullable(abstractYuanManQuery).orElse(ReflectUtil.newInstance(abstractYuanManQuery.getClass())).build();
    }
}